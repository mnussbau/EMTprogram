package hatzalahGui;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddCallWindow extends Stage {
	TextField branchName;
	DatePicker dateOfCall;
	TextField streetAddress;
	TextField state;
	TextField city;
	TextField zip;
	TextField fname;
	TextField lname;
	TextField age;
	ComboBox<Character> choice;
	TextField VIN;

	public AddCallWindow() { //you would pass it the mainWindow from main if you want it to be in the same Stage
		Label vinLabel = new Label("VIN:");
		vinLabel.setVisible(false);
		Character yesNo[] = { 'Y', 'N' };
		choice = new ComboBox<Character>(FXCollections.observableArrayList(yesNo));
		choice.setOnAction(e -> {
			if (choice.getValue() == 'Y') {
				VIN.setVisible(true);
				vinLabel.setVisible(true);
			} else {
				VIN.setVisible(false);
				vinLabel.setVisible(false);
			}
		});
		choice.setValue('N');

		BorderPane borderLayout = new BorderPane();
		borderLayout.setStyle("-fx-background-color: Azure");
		GridPane grid = new GridPane();
		grid.add(new Label("Branch Name:"), 0, 0);
		branchName = new TextField();
		grid.add(branchName, 0, 1);
		grid.add(new Label("Date of Call: "), 1, 0);
		dateOfCall = new DatePicker();
		dateOfCall.setValue(LocalDate.now());
		grid.add(dateOfCall, 1, 1);
		grid.add(new Label("Street Address: "), 0, 4);
		streetAddress = new TextField();
		grid.add(streetAddress, 0, 5);
		grid.add(new Label("City:"), 1, 4);
		city = new TextField();
		grid.add(city, 1, 5);
		grid.add(new Label("State:"), 2, 4);
		state = new TextField();
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
		grid.add(choice, 2, 1);
		grid.add(vinLabel, 3, 0);
		VIN = new TextField();
		VIN.setVisible(false);
		grid.add(VIN, 3, 1);

		borderLayout.setCenter(grid);
		Button okButton = new Button("OK");
		okButton.setStyle("-fx-background-color: Lavender");
		okButton.setOnAction(e -> {
			System.out.println(branchName.getText().isBlank());
			if (!(branchName.getText().isBlank()|| streetAddress.getText().isBlank() || city.getText().isBlank()
					|| state.getText().isBlank()|| zip.getText().isBlank() || fname.getText().isBlank() || lname.getText().isBlank() || 
					age.getText().isBlank())) {
				try {
					addCallData(branchName.getText(), dateOfCall.getValue(), streetAddress.getText(), city.getText(),
							state.getText(), zip.getText(), fname.getText(), lname.getText(),
							Integer.parseInt(age.getText()), choice.getValue(), VIN.getText());
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		Button backButton = new Button("Back");
		backButton.setStyle("-fx-background-color: Lavender");
		backButton.setOnAction(e -> this.close());
		HBox hbox = new HBox();
		hbox.getChildren().addAll(okButton, backButton);
		HBox.setMargin(okButton, new Insets(2, 2, 2, 2));
		HBox.setMargin(backButton, new Insets(2, 2, 2, 2));

		borderLayout.setBottom(hbox);
		Scene scene = new Scene(borderLayout);
		this.setScene(scene);
		this.initModality(Modality.APPLICATION_MODAL);
		this.show();
	}

	public static void addCallData(String branchName, LocalDate callreceived, String streetaddress, String city,
			String state, String zip, String fname, String lname, Integer age, Character choice, String vin2)
			throws SQLException, Exception {
		String url = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS01;databaseName=Hatzolah;integratedSecurity=true";
		Connection dbConnection = DriverManager.getConnection(url);

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

			// set up the parameters to the procedure
			/*
			 * @branchName varchar(45),
			 * 
			 * @addr_street varchar(30),
			 * 
			 * @addr_city varchar(30),
			 * 
			 * @addr_state varchar(2),
			 * 
			 * @addr_zip varchar(5),
			 * 
			 * @fname varchar(20),
			 * 
			 * @lname varchar(30),
			 * 
			 * @age int,
			 * 
			 * @Transferred char,
			 * 
			 * @VIN varchar(17), --could be null
			 * 
			 * @date date,
			 * 
			 * @notes varchar(1000), --(nullable)
			 * 
			 * @newCallId int output
			 */
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
			// cStatement.setInt(13, id);
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
			dbConnection.rollback();
			throw sqlE;

		}

	}

}
