package hatzalahGui;

import java.awt.Component;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import hatzalahBusiness.Branch;
import hatzalahBusiness.Credential;
import hatzalahBusiness.Job;
import hatzalahData.BranchData;
import hatzalahData.CredentialIO;
import hatzalahData.JobIO;
import hatzalahData.MemberIO;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddMemberWindow extends Stage {

	/*
	 * Fname LName Date joined (n) Bday Marital status Branch name Addr street addr
	 * city Addr state Addr zip Credential name Job name Phone number status
	 */

	private TextField fname;
	private TextField lname;
	private TextField phoneNum;
	private DatePicker bday;
	private ComboBox<Character> maritalStatus;

	private ComboBox<String> branchName; // should be a dropdown
	private DatePicker dateJoined;
	private ComboBox<String> credentialName; // should be a dropdown
	private ComboBox<String> jobName; // should be a dropdown

	private ComboBox<Character> status;

	private TextField street;
	private TextField city;
	private ComboBox<String> state;
	private TextField zip;

	private Scene originalScene;

	public AddMemberWindow(Stage mainWindow, Connection dbconnection) {
		originalScene = mainWindow.getScene();
		BorderPane layout = new BorderPane();
		GridPane grid = new GridPane();
		grid.autosize();
		grid.add(new Label("First Name"), 0, 0);
		fname = new TextField();
		grid.add(fname, 0, 1);
		grid.add(new Label("Last Name"), 1, 0);
		lname = new TextField();
		grid.add(lname, 1, 1);
		grid.add(new Label("Phone Number"), 2, 0);
		phoneNum = new TextField();
		grid.add(phoneNum, 2, 1);
		grid.add(new Label("DOB"), 3, 0);
		bday = new DatePicker();
		bday.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				setDisable(empty || date.compareTo(today) > 0);
			}
		});
		grid.add(bday, 3, 1);
		grid.add(new Label("Marital Status"), 4, 0);
		Character[] ms = { 'S', 'M', 'D', 'W' };
		maritalStatus = new ComboBox<Character>((FXCollections.observableArrayList(ms)));
		maritalStatus.setValue('M');
		maritalStatus
				.setStyle("-fx-background-color: Lavender;-fx-border-color: MEDIUMPURPLE;-fx-border-width: 1 1 1 1;");
		maritalStatus.setMaxWidth(150);
		grid.add(maritalStatus, 4, 1);
		grid.add(new Label("Branch Name"), 0, 2);
		ArrayList<Branch> branches = null;
		try {
			branches = BranchData.getBranch(dbconnection);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
		String[] branchNames = new String[branches.size()];
		for (int i = 0; i < branches.size(); i++) {
			branchNames[i] = branches.get(i).getBranchName();
		}
		branchName = new ComboBox<String>(FXCollections.observableArrayList(branchNames));
		branchName.setStyle("-fx-background-color: Lavender;-fx-border-color: MEDIUMPURPLE;-fx-border-width: 1 1 1 1;");
		if (branchNames.length > 0) {
			branchName.setValue(branchNames[0]);
		} else {
			branchName.setValue("Add Branch");
		}
		branchName.setMaxWidth(150);
		grid.add(branchName, 0, 3);
		grid.add(new Label("Date Joined"), 1, 2);
		dateJoined = new DatePicker();
		dateJoined.setValue(LocalDate.now());
		dateJoined.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				setDisable(empty || date.compareTo(today) > 0);
			}
		});
		grid.add(dateJoined, 1, 3);

		ArrayList<Credential> credentials = null;
		try {
			credentials = CredentialIO.getCredentials(dbconnection);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] credentialDescriptions = new String[credentials.size()];
		for (int i = 0; i < credentials.size(); i++) {
			credentialDescriptions[i] = credentials.get(i).getDescription();
		}
		grid.add(new Label("Credential Name"), 2, 2);
		credentialName = new ComboBox<String>(FXCollections.observableArrayList(credentialDescriptions));
		credentialName.setMaxWidth(150);
		credentialName
				.setStyle("-fx-background-color: Lavender;-fx-border-color: MEDIUMPURPLE;-fx-border-width: 1 1 1 1;");
		if (credentialDescriptions.length > 0) {
			credentialName.setValue(credentialDescriptions[0]);
		} else {
			credentialName.setValue("Add Credential");
		}
		grid.add(credentialName, 2, 3);
		grid.add(new Label("Job Name"), 3, 2);
		ArrayList<Job> jobs = null;
		try {
			jobs = JobIO.getJobs(dbconnection);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] jobDescriptions = new String[jobs.size()];
		for (int i = 0; i < jobs.size(); i++) {
			jobDescriptions[i] = jobs.get(i).getDescription();
		}
		jobName = new ComboBox<String>(FXCollections.observableArrayList(jobDescriptions));
		jobName.setMaxWidth(174);
		jobName.setStyle("-fx-background-color: Lavender;-fx-border-color: MEDIUMPURPLE;-fx-border-width: 1 1 1 1;");
		if (credentialDescriptions.length > 0) {
			jobName.setValue(jobDescriptions[0]);
		} else {
			jobName.setValue("Add Job");
		}
		grid.add(jobName, 3, 3);
		grid.add(new Label("Status"), 4, 2);
		Character[] active = { 'A', 'I' };
		status = new ComboBox<Character>(FXCollections.observableArrayList(active));
		status.setValue('A');
		status.setMaxWidth(150);
		status.setStyle("-fx-background-color: Lavender;-fx-border-color: MEDIUMPURPLE;-fx-border-width: 1 1 1 1;");
		grid.add(status, 4, 3);

		grid.add(new Label("Street Address"), 0, 4);
		street = new TextField();
		grid.add(street, 0, 5);
		grid.add(new Label("City"), 1, 4);
		city = new TextField();
		grid.add(city, 1, 5);
		String[] listOfStates = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
				"IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
				"NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV",
				"WI", "WY" };
		state = new ComboBox<String>(FXCollections.observableArrayList(listOfStates));
		state.setValue("NY");
		state.resize(150, 150);
		state.setStyle("-fx-background-color: Lavender;-fx-border-color: MEDIUMPURPLE;-fx-border-width: 1 1 1 1;");
		grid.add(new Label("State"), 2, 4);
		grid.add(state, 2, 5);
		zip = new TextField();
		grid.add(new Label("Zip"), 3, 4);
		grid.add(zip, 3, 5);

		Button okButton = new Button("OK");
		okButton.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
		okButton.setOnAction(e -> {
			if (!(fname.getText().isBlank() || lname.getText().isBlank() || phoneNum.getText().isBlank()
					|| branchName.getValue().equals("Add Branch") || credentialName.getValue().equals("Add Credential")
					|| jobName.getValue().equals("Add Job") || street.getText().isBlank() || city.getText().isBlank()
					|| zip.getText().isBlank())) {
				String memberId;
				try {
					memberId = MemberIO.addMemberData(fname.getText(), lname.getText(), phoneNum.getText(),
							bday.getValue(), maritalStatus.getValue(),

							branchName.getValue(), dateJoined.getValue(), credentialName.getValue(), jobName.getValue(),
							status.getValue(), street.getText(), city.getText(), state.getValue(), zip.getText(),
							dbconnection);
					JOptionPane.showMessageDialog(null, "Your member id is " + memberId);
					clearInputs();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			} else {
				JOptionPane.showInternalMessageDialog(null, "Please fill out all fields");
			}
		});
		Button backButton = new Button("Back");
		backButton.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
		backButton.setOnAction(e -> mainWindow.setScene(originalScene));
		HBox hbox = new HBox();
		hbox.getChildren().addAll(okButton, backButton);
		HBox.setMargin(okButton, new Insets(2, 2, 2, 2));
		HBox.setMargin(backButton, new Insets(2, 2, 2, 2));
		layout.setTop(grid);
		layout.setCenter(hbox);
		layout.setStyle("-fx-background-color: Azure");
		Scene thisScene = new Scene(layout);
		mainWindow.setScene(thisScene);
		mainWindow.sizeToScene();

	}

	private void clearInputs() {
		fname.clear();
		lname.clear();
		phoneNum.clear();
		street.clear();
		city.clear();
		zip.clear();
		
	}

}
