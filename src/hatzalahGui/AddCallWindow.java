package hatzalahGui;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddCallWindow extends Stage {
	TextField branchName;
	TextField callReceived;
	TextField streetAddress;
	TextField state;
	TextField city;
	TextField zip;
	TextField fname;
	TextField lname;
	TextField age;
	ComboBox<Character> choice;
	TextField VIN;
	

	public AddCallWindow() {
		Character yesNo[] = {'Y', 'N'};
		choice = new ComboBox<Character>(FXCollections.observableArrayList(yesNo));
		choice.setValue('N');
		
		BorderPane borderLayout = new BorderPane();
		GridPane grid = new GridPane();
		grid.add(new Label("Branch Name:"), 0, 0);
		branchName = new TextField();
		grid.add(branchName, 0, 1);
		grid.add(new Label("Date of Call: "), 1, 0);
		callReceived = new TextField();
		grid.add(callReceived, 1, 1);
		grid.add(new Label("Transferred?"), 2, 0);
		grid.add(choice, 2, 1);
		borderLayout.setCenter(grid);
		Button okButton = new Button("OK");
//	okButton.setOnAction(new AddOrgController(dbConnection));
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> this.close());
		HBox hbox = new HBox();
		hbox.getChildren().addAll(okButton, backButton);
		borderLayout.setBottom(hbox);
		Scene scene = new Scene(borderLayout);
		this.setScene(scene);
		this.initModality(Modality.APPLICATION_MODAL);
		this.show();
		String vin = null;
		int id =-1;
		try {
			addCallData("myBranch", LocalDate.now(), "456789", "Brooklyn", "NY",
					"11230", "Chaya", "Zitwer", 20, 'N', vin, id);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("THE ID IS THIS NUMBER: "+id);
	}
	
	
	public static void addCallData(String branchName, LocalDate callreceived, String streetaddress, String city, String state, String zip, String fname, String lname, Integer age, Character choice, String vin2, Integer id) throws SQLException, Exception {
		String url = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS01;databaseName=Hatzolah;integratedSecurity=true";
		Connection dbConnection = DriverManager.getConnection(url);

		//branchname
		//callreceived date
		//address - whhole address state city zip street
		//fname, lastname, age, 
		//transferred Y or no
		//vin
		//notes
		
		try {
			dbConnection.setAutoCommit(false);
			CallableStatement cStatement;

			String sql = "{call usp_addCall(?,?,?,?,?,?,?,?,?,?,?,?)}";
			cStatement = dbConnection.prepareCall(sql);
			
			// set up the parameters to the procedure
			/*
			 * @branchName varchar(45),
@addr_street varchar(30),
@addr_city varchar(30),
@addr_state varchar(2),
@addr_zip varchar(5), 
@fname varchar(20),
@lname varchar(30), 
@age int, 
@Transferred char, 
@VIN varchar(17), --could be null
@date date,
@notes varchar(1000), --(nullable)
@newCallId int output
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
			//cStatement.setInt(13, id);
			ResultSet rs = cStatement.executeQuery();
			
			//cStatement.execute();
			System.out.println(cStatement.getUpdateCount());
			//cStatement.getMoreResults();
			System.out.println("all is well about to commit");

			dbConnection.commit();
			
			
			
			System.out.println("INSIDE THIS METHOD THE ID IS: "+id);
			if(rs.next()) {
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
