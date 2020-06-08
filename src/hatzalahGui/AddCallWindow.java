package hatzalahGui;

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

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
	TextField branchName;
	DatePicker dateOfCall;
	TextField streetAddress;
	ComboBox<String> state;
	TextField city;
	TextField zip;
	TextField fname;
	TextField lname;
	TextField age;
	ComboBox<Character> transferedCmboBx;
	TextField VIN;
	TextArea notes;
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
				VIN.setVisible(true);
				vinLabel.setVisible(true);
			} else {
				VIN.setVisible(false);
				vinLabel.setVisible(false);
			}
		});
		transferedCmboBx.setValue('N');

		BorderPane borderLayout = new BorderPane();
		borderLayout.setStyle("-fx-background-color: Azure");
		GridPane grid = new GridPane();
		grid.add(new Label("Branch Name:"), 0, 0);
		branchName = new TextField();
		grid.add(branchName, 0, 1);
		grid.add(new Label("Date of Call: "), 1, 0);
		dateOfCall = new DatePicker();
		dateOfCall.setValue(LocalDate.now());

		dateOfCall.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				setDisable(empty || date.compareTo(today) > 0);
			}
		});
		grid.add(dateOfCall, 1, 1);
		grid.add(new Label("Street Address: "), 0, 4);
		streetAddress = new TextField();
		grid.add(streetAddress, 0, 5);
		grid.add(new Label("City:"), 1, 4);
		city = new TextField();
		grid.add(city, 1, 5);
		grid.add(new Label("State:"), 2, 4);
		String[] listOfStates = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
				"IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
				"NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV",
				"WI", "WY" };
		state = new ComboBox<String>(FXCollections.observableArrayList(listOfStates));
		state.resize(150, 150);
		state.setStyle("-fx-background-color: Lavender");
		state.setValue("NY");
		grid.add(state, 2, 5);
		grid.add(new Label("Zip:"), 3, 4);
		zip = new TextField();
		grid.add(zip, 3, 5);
		grid.add(new Label("First Name:"), 0, 2);
		fname = new TextField();
		grid.add(fname, 0, 3);
		grid.add(new Label("Last Name"), 1, 2);
		lname = new TextField();
		grid.add(lname, 1, 3);
		grid.add(new Label("Age:"), 2, 2);
		age = new TextField();
		grid.add(age, 2, 3);
		grid.add(new Label("Transferred?"), 2, 0);
		grid.add(transferedCmboBx, 2, 1);
		grid.add(vinLabel, 3, 0);
		VIN = new TextField();
		VIN.setVisible(false);
		grid.add(VIN, 3, 1);
		grid.add(new Label("Notes:"), 0, 10);

		symptomsView = new ListView<>();
		symptomsView.setMaxHeight(50);
		symptomsView.setMaxWidth(150);
		Button addSymptomBtn = new Button("Add Symptom");
		addSymptomBtn.setOnAction(new addSymptomToCall_click(db));
		addSymptomBtn.setStyle("-fx-background-color: Lavender");
		grid.add(new Label("Symptoms"), 0, 6);
		grid.add(symptomsView, 0, 7);
		grid.add(addSymptomBtn, 0, 8);

		membersView = new ListView<>();
		membersView.setMaxHeight(50);
		membersView.setMaxWidth(174);
		Button addMemberBtn = new Button("Add Member");
		addMemberBtn.setOnAction(new AddMemberToCall_click(db));
		addMemberBtn.setStyle("-fx-background-color: Lavender");
		grid.add(new Label("Members"), 1, 6);
		grid.add(membersView, 1, 7);
		grid.add(addMemberBtn, 1, 8);

		equipmentView = new ListView<>();
		equipmentView.setMaxHeight(50);
		equipmentView.setMaxWidth(150);
		Button addEquipBtn = new Button("Add Equipment");
		addEquipBtn.setOnAction(new AddEquipToCall_click(db));
		addEquipBtn.setStyle("-fx-background-color: Lavender");
		grid.add(new Label("Equipment"), 2, 6);
		grid.add(equipmentView, 2, 7);
		grid.add(addEquipBtn, 2, 8);

		notes = new TextArea();
		notes.setMaxHeight(100);

		Label alertLabel = new Label("You need to fill in all fields");
		alertLabel.setVisible(false);
		Button okButton = new Button("OK");
		okButton.setStyle("-fx-background-color: Lavender");
		okButton.setOnAction(new addCallData());
		borderLayout.setTop(grid);
		Button backButton = new Button("Back");
		backButton.setStyle("-fx-background-color: Lavender");
		backButton.setOnAction(e -> mainWindow.setScene(mainScene));
		HBox hbox = new HBox();
		hbox.getChildren().addAll(okButton, backButton, alertLabel);
		HBox.setMargin(okButton, new Insets(2, 2, 2, 2));
		HBox.setMargin(backButton, new Insets(2, 2, 2, 2));
		borderLayout.setCenter(new HBox(notes));
		borderLayout.setBottom(hbox);
		Scene scene = new Scene(borderLayout);
		mainWindow.setScene(scene);
		mainWindow.sizeToScene();
//		this.initModality(Modality.APPLICATION_MODAL);
//		this.show();
	}

	class addCallData implements EventHandler<ActionEvent> {
		String branchName;
		LocalDate callreceived;
		String streetaddress;
		String city;
		String state;
		String zip;
		String fname;
		String lname;
		Integer age;
		Character choice;
		String vin2;
		Connection dbConnection;

		@Override
		public void handle(ActionEvent event) {
			// (String branchName, LocalDate callreceived, String streetaddress, String
			// city,
			// String state, String zip, String fname, String lname, Integer age, Character
			// choice, String vin2)
			// throws SQLException, Exception {

			// branchname
			// callreceived date
			// address - whhole address state city zip street
			// fname, lastname, age,
			// transferred Y or no
			// vin
			// notes

			try {
				dbConnection.setAutoCommit(false);
				CallableStatement cStatement;

				String sql = "{call usp_addCall(?,?,?,?,?,?,?,?,?,?,?,?)}";
				cStatement = dbConnection.prepareCall(sql);
				cStatement.setString(1, branchName);
				cStatement.setString(2, streetaddress);
				cStatement.setString(3, city);
				cStatement.setString(4, state);
				cStatement.setString(5, zip);
				cStatement.setString(6, fname);
				cStatement.setString(7, lname);
				cStatement.setInt(8, age);
				cStatement.setString(9, "" + choice);
				cStatement.setString(10, null);
				cStatement.setDate(11, java.sql.Date.valueOf(callreceived.toString()));
				cStatement.setString(12, null);
				ResultSet rs = cStatement.executeQuery();

				// cStatement.execute();
				System.out.println(cStatement.getUpdateCount());
				// cStatement.getMoreResults();
				System.out.println("all is well about to commit");

				dbConnection.commit();

				if (rs.next()) {
					System.out.println("Equipment ID: " + rs.getString("newCallId"));
				}

				System.out.println("committed the transaction");
			} catch (SQLException sqlE) {
				System.out.println("problem occurred " + sqlE.getMessage());
				// dbConnection.rollback();

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
			List<String> allSymptoms = new ArrayList<>();
			try {
				Statement statement = dbConnection.createStatement();
				ResultSet rs = statement.executeQuery("select Symptom_desc from SYMPTOM");
				while (rs.next()) {
					allSymptoms.add(rs.getString("Symptom_desc"));
				}
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
			List<String> allMembers = new ArrayList<>();
			int branchId;
			try {
				branchName.setDisable(true);
				CallableStatement stmt = db.prepareCall("select branch_id from branch where branch_name = ?;");
				stmt.setString(1, branchName.getText());
				ResultSet set = stmt.executeQuery();
				if (set.next()) {
					branchId = set.getInt("branch_id");
				} else {
					branchName.setDisable(false);
					JOptionPane.showMessageDialog(null,
							"Branch name not valid. Please input valid branch name to add a member");
					return;
				}

				Statement statement = dbConnection.createStatement();
				ResultSet rs = statement
						.executeQuery("select member_id+': '+fname+' '+lname as member from MEMBER where branch_id = "
								+ branchId + ";");

				while (rs.next()) {
					allMembers.add(rs.getString("member"));
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Something went wrong." + ex.getMessage());
				System.out.println(ex.getStackTrace().toString());
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
			List<String> allEquipment = new ArrayList<>();
			try {
				Statement statement = dbConnection.createStatement();
				ResultSet rs = statement.executeQuery("select Equip_desc from EQUIPMENT;");
				while (rs.next()) {
					allEquipment.add(rs.getString("Equip_desc"));
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Something went wrong." + ex.getMessage());
				System.out.println(ex.getStackTrace().toString());
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