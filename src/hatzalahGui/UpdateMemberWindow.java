package hatzalahGui;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import hatzalahBusiness.Address;
import hatzalahBusiness.Branch;
import hatzalahBusiness.Credential;
import hatzalahBusiness.Job;
import hatzalahBusiness.Member;
import hatzalahData.AddressIO;
import hatzalahData.BranchData;
import hatzalahData.CredentialIO;
import hatzalahData.JobIO;
import hatzalahData.MemberIO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;

public class UpdateMemberWindow {
	/*
	 * @memberId varchar(5),
	 * 
	 * @LName varchar(45),
	 * 
	 * @MaritalStatus char,
	 * 
	 * @BranchName varchar(45),
	 * 
	 * @Addr_Street varchar(45),
	 * 
	 * @addr_City varchar(45),
	 * 
	 * @Addr_State varchar(2),
	 * 
	 * @Addr_Zip varchar(5),
	 * 
	 * @CredentialName varchar(45),
	 * 
	 * @JobName varchar(45),
	 * 
	 * @PhoneNumber varchar(10),
	 * 
	 * @status char
	 * 
	 */

	private TextField memberId;
	private TextField fname;
	private TextField lname;
	private TextField phoneNum;
	private TextField bday;
	private ComboBox<Character> maritalStatus;

	private ComboBox<String> branchName; // should be a dropdown
	private TextField dateJoined;
	private ComboBox<String> credentialName; // should be a dropdown
	private ComboBox<String> jobName; // should be a dropdown

	private ComboBox<Character> status;

	private TextField street;
	private TextField city;
	private ComboBox<String> state;
	private TextField zip;

	private Scene originalScene;
	Connection dbconnection;

	public UpdateMemberWindow(Stage mainWindow, Connection dbconnection) {
		this.dbconnection = dbconnection;
		originalScene = mainWindow.getScene();
		BorderPane layout = new BorderPane();
		GridPane grid = new GridPane();

		String idLabel = "Please enter your id";
		String input = JOptionPane.showInputDialog(null, idLabel);

		try {
			Member memberToUpdate = MemberIO.getMember(input, dbconnection);
			grid.add(new Label("Member ID"), 0, 0);
			memberId = new TextField();
			memberId.setText(memberToUpdate.getMemberId());
			memberId.setDisable(true);
			grid.add(memberId, 0, 1);
			grid.add(new Label("First Name"), 1, 0);
			fname = new TextField();
			fname.setText(memberToUpdate.getFname());
			fname.setDisable(true);
			grid.add(fname, 1, 1);
			grid.add(new Label("Last Name"), 2, 0);
			lname = new TextField();
			lname.setText(memberToUpdate.getLname());
			grid.add(lname, 2, 1);
			grid.add(new Label("Phone Number"), 3, 0);
			phoneNum = new TextField();
			phoneNum.setText(memberToUpdate.getMember_phone_num());
			grid.add(phoneNum, 3, 1);
			grid.add(new Label("DOB"), 4, 0);
			bday = new TextField();
			bday.setText(memberToUpdate.getBday().toString());
			bday.setDisable(true);
			grid.add(bday, 4, 1);
			grid.add(new Label("Marital Status"), 5, 0);
			Character[] ms = { 'S', 'M', 'D', 'W' };
			maritalStatus = new ComboBox<Character>((FXCollections.observableArrayList(ms)));
			maritalStatus.setValue(memberToUpdate.getMaritalStatus());
			maritalStatus.setStyle(
					"-fx-background-color: Lavender;-fx-border-color: MEDIUMPURPLE;-fx-border-width: 1 1 1 1;");
			maritalStatus.setMaxWidth(150);
			grid.add(maritalStatus, 5, 1);
			grid.add(new Label("Branch Name"), 0, 2);
			branchName = new ComboBox<String>(FXCollections.observableArrayList(BranchData.getBranch(dbconnection)
					.stream().map(b -> b.getBranchName()).collect(Collectors.toList())));
			branchName.setValue(BranchData.getBranchName(dbconnection, memberToUpdate.getBranch_id()));
			branchName.setMaxWidth(150);
			branchName.setDisable(true);
			grid.add(branchName, 0, 3);
			grid.add(new Label("Date Joined"), 1, 2);
			dateJoined = new TextField();
			dateJoined.setText(memberToUpdate.getDateJoined().toString());
			dateJoined.setDisable(true);
			grid.add(dateJoined, 1, 3);
			grid.add(new Label("Credential"), 2, 2);
			credentialName = new ComboBox<String>(FXCollections.observableArrayList(CredentialIO
					.getCredentials(dbconnection).stream().map(c -> c.getDescription()).collect(Collectors.toList())));
			credentialName.setValue(CredentialIO.getCredentialName(dbconnection, memberToUpdate.getCredential_id()));
			credentialName.setMaxWidth(174);
			grid.add(credentialName, 2, 3);
			grid.add(new Label("Job Name"), 3, 2);
			jobName = new ComboBox<String>(FXCollections.observableArrayList(
					JobIO.getJobs(dbconnection).stream().map(j -> j.getDescription()).collect(Collectors.toList())));
			jobName.setValue(JobIO.getJobName(dbconnection, memberToUpdate.getJob_id()));
			jobName.setMaxWidth(150);
			grid.add(jobName, 3, 3);
			grid.add(new Label("Status"), 4, 2);
			Character[] active = { 'A', 'I' };
			status = new ComboBox<Character>(FXCollections.observableArrayList(active));
			status.setValue(memberToUpdate.getActive_status());
			status.setMaxWidth(150);
			status.setStyle("-fx-background-color: Lavender;-fx-border-color: MEDIUMPURPLE;-fx-border-width: 1 1 1 1;");
			grid.add(status, 4, 3);
			grid.add(new Label("Street Address"), 0, 4);
			Address address = AddressIO.getAddress(dbconnection, memberToUpdate.getAddress_id());
			street = new TextField();
			street.setText(address.getAddrStreet());
			grid.add(street, 0, 5);
			grid.add(new Label("City"), 1, 4);
			city = new TextField();
			city.setText(address.getAddrCity());
			grid.add(city, 1, 5);
			grid.add(new Label("State"), 2, 4);

			String[] listOfStates = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL",
					"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
					"NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
					"VA", "WA", "WV", "WI", "WY" };
			state = new ComboBox<String>(FXCollections.observableArrayList(listOfStates));
			state.setValue(address.getAddrState());
			state.resize(150, 150);
			state.setStyle("-fx-background-color: Lavender;-fx-border-color: MEDIUMPURPLE;-fx-border-width: 1 1 1 1;");
			grid.add(state, 2, 5);
			grid.add(new Label("Zip"), 3, 4);
			zip = new TextField();
			zip.setText(address.getZip());
			grid.add(zip, 3, 5);

			Button okButton = new Button("OK");
			okButton.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
			okButton.setOnAction(e -> {
				if (!(fname.getText().isBlank() || lname.getText().isBlank() || phoneNum.getText().isBlank()  
						|| branchName.getValue().equals("Add Branch")
						|| credentialName.getValue().equals("Add Credential") || jobName.getValue().equals("Add Job")
						|| street.getText().isBlank() || city.getText().isBlank() || zip.getText().isBlank())) {
					if(phoneNum.getText().length() == 10) {
					try {
						MemberIO.updateMemberData(memberId.getText(), lname.getText(),
								maritalStatus.getValue(), street.getText(), city.getText(), state.getValue(),
								zip.getText(), credentialName.getValue(), jobName.getValue(), phoneNum.getText(),
								status.getValue(), dbconnection);
						JOptionPane.showMessageDialog(null, memberId.getText() + " successfully updated!");
						clearInputs();
						mainWindow.setScene(originalScene);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					}else {
						JOptionPane.showMessageDialog(null, "Please enter 10 digits for the phone number");
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

		} catch (SQLException e) {
			if (input != null)
				JOptionPane.showMessageDialog(null, e.getMessage());
			mainWindow.setScene(originalScene);
		}

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