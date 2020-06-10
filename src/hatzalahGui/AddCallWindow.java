package hatzalahGui;

import java.sql.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import hatzalahBusiness.Equipment;
import hatzalahBusiness.Member;
import hatzalahBusiness.Symptom;
import hatzalahData.BranchData;
import hatzalahData.CallIO;
import hatzalahData.EquipmentIO;
import hatzalahData.MemberIO;
import hatzalahData.SymptomIO;

import java.time.*;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.Callback;

public class AddCallWindow extends Stage {
	Connection dbConnection;
	ComboBox<String> branchNameComboBx;
	DatePicker dateOfCallDtPckr;
	TextField streetAddressTxtBx;
	ComboBox<String> stateCmBx;
	TextField cityTxtBx;
	TextField zipTxtBx;
	TextField fnameTxtBx;
	TextField lnameTxtBx;
	TextField ageTxtBx;
	ComboBox<Character> transferedCmboBx;
	TextField VINtxtBx;
	TextArea notesTxtArea;
	Scene mainScene;
	ListView<String> symptomsView;
	ListView<String> membersView;
	ListView<String> equipmentView;

	public AddCallWindow(Stage mainWindow, Connection db) {
		mainScene = mainWindow.getScene();
		dbConnection = db;
		Label vinLabel = new Label("VIN:");
		vinLabel.setVisible(false);
		Character yesNo[] = { 'Y', 'N' };
		transferedCmboBx = new ComboBox<Character>(FXCollections.observableArrayList(yesNo));
		transferedCmboBx.setStyle("-fx-background-color: Lavender");
		transferedCmboBx.setOnAction(e -> {
			if (transferedCmboBx.getValue() == 'Y') {
				VINtxtBx.setVisible(true);
				vinLabel.setVisible(true);
			} else {
				VINtxtBx.setVisible(false);
				vinLabel.setVisible(false);
			}
		});
		transferedCmboBx.setValue('N');

		BorderPane borderLayout = new BorderPane();
		borderLayout.setStyle("-fx-background-color: Azure");
		GridPane grid = new GridPane();
		grid.add(new Label("Branch Name:"), 0, 0);
		try {
			branchNameComboBx = new ComboBox<>(FXCollections.observableArrayList(BranchData.getBranch(dbConnection)
					.stream().map(b -> b.getBranchName()).collect(Collectors.toList())));
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Cannot connect to database.");
			mainWindow.setScene(mainScene);
		}
		grid.add(branchNameComboBx, 0, 1);
		grid.add(new Label("Date of Call: "), 1, 0);
		dateOfCallDtPckr = new DatePicker();
		dateOfCallDtPckr.setValue(LocalDate.now());

		dateOfCallDtPckr.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				setDisable(empty || date.compareTo(today) > 0);
			}
		});
		grid.add(dateOfCallDtPckr, 1, 1);
		grid.add(new Label("Street Address: "), 0, 4);
		streetAddressTxtBx = new TextField();
		grid.add(streetAddressTxtBx, 0, 5);
		grid.add(new Label("City:"), 1, 4);
		cityTxtBx = new TextField();
		grid.add(cityTxtBx, 1, 5);
		grid.add(new Label("State:"), 2, 4);
		String[] listOfStates = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
				"IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
				"NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV",
				"WI", "WY" };
		stateCmBx = new ComboBox<String>(FXCollections.observableArrayList(listOfStates));
		stateCmBx.resize(150, 150);
		stateCmBx.setStyle("-fx-background-color: Lavender");
		stateCmBx.setValue("NY");
		grid.add(stateCmBx, 2, 5);
		grid.add(new Label("Zip:"), 3, 4);
		zipTxtBx = new TextField();
		grid.add(zipTxtBx, 3, 5);
		grid.add(new Label("First Name:"), 0, 2);
		fnameTxtBx = new TextField();
		grid.add(fnameTxtBx, 0, 3);
		grid.add(new Label("Last Name"), 1, 2);
		lnameTxtBx = new TextField();
		grid.add(lnameTxtBx, 1, 3);
		grid.add(new Label("Age:"), 2, 2);
		ageTxtBx = new TextField();
		grid.add(ageTxtBx, 2, 3);
		grid.add(new Label("Transferred?"), 2, 0);
		grid.add(transferedCmboBx, 2, 1);
		grid.add(vinLabel, 3, 0);
		VINtxtBx = new TextField();
		VINtxtBx.setVisible(false);
		grid.add(VINtxtBx, 3, 1);
		grid.add(new Label("Notes:"), 0, 10);

		symptomsView = new ListView<>();
		symptomsView.setMaxHeight(75);
		symptomsView.setMaxWidth(150);
		Button addSymptomBtn = new Button("Add Symptom");
//		addSymptomBtn.setStyle("-fx-border-color: black; -fx-border-width: 0 3 3 0;");
		addSymptomBtn.setOnAction(new addSymptomToCall_click(dbConnection));
		addSymptomBtn.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
		grid.add(new Label("Symptoms"), 0, 6);
		grid.add(symptomsView, 0, 7);
		grid.add(addSymptomBtn, 0, 8);

		membersView = new ListView<>();
		membersView.setMaxHeight(75);
		membersView.setMaxWidth(174);
		Button addMemberBtn = new Button("Add Member");
		addMemberBtn.setOnAction(new AddMemberToCall_click(dbConnection));
		addMemberBtn.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
		grid.add(new Label("Members"), 1, 6);
		grid.add(membersView, 1, 7);
		grid.add(addMemberBtn, 1, 8);

		equipmentView = new ListView<>();
		equipmentView.setMaxHeight(75);
		equipmentView.setMaxWidth(150);
		Button addEquipBtn = new Button("Add Equipment");
		addEquipBtn.setOnAction(new AddEquipToCall_click(dbConnection));
		addEquipBtn.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
		grid.add(new Label("Equipment"), 2, 6);
		grid.add(equipmentView, 2, 7);
		grid.add(addEquipBtn, 2, 8);

		notesTxtArea = new TextArea();
		notesTxtArea.setMaxHeight(100);

		Label alertLabel = new Label("You need to fill in all fields");
		alertLabel.setVisible(false);
		Button okButton = new Button("OK");
		okButton.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
		okButton.setOnAction(new addCallData(dbConnection));
		borderLayout.setTop(grid);
		Button backButton = new Button("Back");
		backButton.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
		backButton.setOnAction(e -> mainWindow.setScene(mainScene));
		HBox hbox = new HBox();
		hbox.getChildren().addAll(okButton, backButton, alertLabel);
		HBox.setMargin(okButton, new Insets(2, 2, 2, 2));
		HBox.setMargin(backButton, new Insets(2, 2, 2, 2));
		borderLayout.setCenter(new HBox(notesTxtArea));
		borderLayout.setBottom(hbox);
		Scene scene = new Scene(borderLayout);
		mainWindow.setScene(scene);
		mainWindow.sizeToScene();

	}

	class addCallData implements EventHandler<ActionEvent> {
		Connection db;

		public addCallData(Connection db) {
			this.db = db;

		}

		private boolean canParseInt(String number) {
			try {
				Integer.parseInt(number);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		}

		private boolean dataHasBlanks() {
			return branchNameComboBx.getValue() == null || fnameTxtBx.getText().isBlank() || lnameTxtBx.getText().isBlank()
					|| ageTxtBx.getText().isBlank() || streetAddressTxtBx.getText().isBlank()
					|| cityTxtBx.getText().isBlank() || stateCmBx.getValue() == null || zipTxtBx.getText().isBlank();
		}
		@Override
		public void handle(ActionEvent event) {
			if (dataHasBlanks()) {
				JOptionPane.showMessageDialog(null, "All fields must be filled out.");
				return;
			}
			if (transferedCmboBx.getValue() == 'Y' && VINtxtBx.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "Vin cannot be blank if the patient was transfered.");
				return;
			}
			if(transferedCmboBx.getValue() =='N') {
				VINtxtBx.clear();
			}
			
			String branchName = branchNameComboBx.getValue();
			LocalDate callReceived = dateOfCallDtPckr.getValue();
			String fname = fnameTxtBx.getText();
			String lname = lnameTxtBx.getText();
			if (!canParseInt(ageTxtBx.getText())) {
				JOptionPane.showMessageDialog(null, "Age must be a number.");
				return;
			}
			int age = Integer.parseInt(ageTxtBx.getText());
			String addrStreet = streetAddressTxtBx.getText();
			String addrCity = cityTxtBx.getText();
			String addrState = stateCmBx.getValue();
			if (zipTxtBx.getText().length() != 5 || !canParseInt(zipTxtBx.getText())) {
				JOptionPane.showMessageDialog(null, "Please check the zipcode");
				return;
			}
			String zip = zipTxtBx.getText();
			char transfered = transferedCmboBx.getValue();
			String vin = VINtxtBx.getText();
			String notes = notesTxtArea.getText();
			List<String> symptoms = symptomsView.getItems();
			List<String> members = membersView.getItems();
			List<String> equipment = equipmentView.getItems();
			try {
				CallIO.addCall(db, branchName, callReceived, fname, lname, age, addrStreet, addrCity, addrState, zip,
						transfered, vin, notes, symptoms, members, equipment);
			} catch (SQLException sqlE) {
				JOptionPane.showMessageDialog(null, "A problem occurred: " + sqlE.getMessage());
			}
		}

	}

	class addSymptomToCall_click implements EventHandler<ActionEvent> {
		Connection db;
		List<String> currentSymptoms;

		public addSymptomToCall_click(Connection db) {
			this.db = db;
			this.currentSymptoms = symptomsView.getItems();
		}

		@Override
		public void handle(ActionEvent arg0) {
			List<String> allSymptoms;
			try {
				allSymptoms = SymptomIO.getSymptoms(dbConnection).stream().map(s -> s.getSymptomDesc())
						.collect(Collectors.toList());
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Something went wrong." + ex.getMessage());
				return;
			}

			currentSymptoms.forEach(s -> allSymptoms.remove(s));
			String s = (String) JOptionPane.showInputDialog(null, "please choose a symptom.", "Choose a symptom",
					JOptionPane.PLAIN_MESSAGE, null, allSymptoms.toArray(), "");
			if ((s != null) && (s.length() > 0)) {
				symptomsView.getItems().add(s);
				return;
			}
		}

	}

	class AddMemberToCall_click implements EventHandler<ActionEvent> {
		Connection db;
		List<String> currentMembers;

		public AddMemberToCall_click(Connection db) {
			this.db = db;
			this.currentMembers = membersView.getItems();
		}

		@Override
		public void handle(ActionEvent arg0) {
			List<String> allMembers;
			Integer branchId;
			String branchName = branchNameComboBx.getValue();
			System.out.println(branchName);
			try {
				branchNameComboBx.setDisable(true);
				branchId = BranchData.getBranchId(db, branchName);
				if (branchId == null) {
					JOptionPane.showMessageDialog(null, "Please choose a branch name before adding members.");
					branchNameComboBx.setDisable(false);
					return;
				}
				allMembers = MemberIO.getMembers(db).stream().filter(m -> m.getBranch_id() == branchId)
						.map(m -> m.getMemberId() + ": " + m.getFname() + " " + m.getLname())
						.collect(Collectors.toList());
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Something went wrong." + ex.getMessage());
				branchNameComboBx.setDisable(false);
				return;
			}

			currentMembers.forEach(m -> allMembers.remove(m));
			String s = (String) JOptionPane.showInputDialog(null, "please choose a member.", "Choose a member",
					JOptionPane.PLAIN_MESSAGE, null, allMembers.toArray(), "");
			if ((s != null) && (s.length() > 0)) {
				membersView.getItems().add(s);
				return;
			}
		}
	}

	class AddEquipToCall_click implements EventHandler<ActionEvent> {
		Connection db;
		List<String> currentEquipment;

		public AddEquipToCall_click(Connection db) {
			this.db = db;
			this.currentEquipment = equipmentView.getItems();
		}

		@Override
		public void handle(ActionEvent arg0) {
			List<String> allEquipment;
			try {
				allEquipment = EquipmentIO.getEquipment(db).stream().map(e -> e.getEquip_desc())
						.collect(Collectors.toList());
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Something went wrong." + ex.getMessage());
				return;
			}
			currentEquipment.forEach(m -> allEquipment.remove(m));
			String s = (String) JOptionPane.showInputDialog(null, "please choose an equipment.", "Choose an equipment",
					JOptionPane.PLAIN_MESSAGE, null, currentEquipment.toArray(), "");
			if ((s != null) && (s.length() > 0)) {
				equipmentView.getItems().add(s);
				return;
			}
		}
	}
}