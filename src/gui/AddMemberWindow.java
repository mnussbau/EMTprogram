package gui;


	//hello

import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.*;
import java.sql.SQLException;
import javafx.event.*;

	public class AddMemberWindow extends Stage{
	      TextField custNameField;
	      TextField custStreetField;
	      TextField custCityField;
	      TextField custStateField;
	      TextField custZipField;
	      TextField salesRepField;
	      
		public AddMemberWindow() {
			this.setTitle("Add Member");
			//set up BorderPane for the scene
			BorderPane pane = new BorderPane();
			//set up the grid to allow user to enter customer data
			GridPane gridLayout = new GridPane();
			Label enterCustName = new Label ("Member Name:");
			custNameField = new TextField ();
			Label enterStreet = new Label("Street:");
			custStreetField = new TextField();
			Label enterCity = new Label ("City:");
			custCityField = new TextField();
			Label enterState = new Label("State:");
			//should be a drop down selection list
			custStateField = new TextField();
			Label enterZip = new Label ("ZipCode:");
			custZipField = new TextField();
			Label enterSalesRep = new Label("Marital Status");
			//should be a drop down list 
			salesRepField = new TextField();
			//now add the controls to the grid layout
			
			gridLayout.add(enterCustName, 0,0);
			gridLayout.add(custNameField, 0, 1);
			gridLayout.add(enterStreet, 1, 0);
			gridLayout.add(custStreetField, 1, 1);
			gridLayout.add(enterCity, 2, 0);
			gridLayout.add(custCityField, 2, 1);
			gridLayout.add(enterState, 3, 0);
			gridLayout.add(custStateField, 3, 1);
			gridLayout.add(enterZip, 4, 0);
			gridLayout.add(custZipField, 4, 1);
			gridLayout.add(enterSalesRep,5, 0);
			gridLayout.add(salesRepField, 5, 1);
			
			//now add the grid to middle of the border
			pane.setCenter(gridLayout);
			
			//now set up the buttons
			Button okButton = new Button("OK");
			Button cancelButton = new Button("CANCEL");
			
			okButton.setOnAction(new AddCustomerHandler(this));//pass reference to the window to access controls
			cancelButton.setOnAction(e-> this.close()); //close the window
			//set up HBox for the Buttons
			HBox hbox = new HBox();
			hbox.getChildren().addAll(okButton,cancelButton);
			
			//add the button to the bottom
			pane.setBottom(hbox);
			
			
			
			Scene theScene = new Scene(pane);
			
			//associate the window and the scene
			this.setScene(theScene);
			this.show();
					
			
		}
		
		class AddCustomerHandler implements EventHandler<ActionEvent>{
	          Stage parentWindow;
			public AddCustomerHandler (Stage parentWindow) {
				this.parentWindow = parentWindow; //keep a reference
			}
			@Override
			public void handle(ActionEvent event) {
				String custName , custStreet=null,custCity=null,custState=null,zip=null;
				Double custBalance=0.0,creditLim=0.0;
				Integer repNum=null;
				custName = custNameField.getText(); //extract data entered by user on the screen
				custStreet = custStreetField.getText();
				custCity = custCityField.getText();
				custState = custStateField.getText();
				zip = custZipField.getText();
				repNum = Integer.valueOf(salesRepField.getText());
				
				System.out.println("cust name info " + custName);
				System.out.println("cust street " + custStreet);
				//set up a Customer
				Customer aCustomer = new Customer(custName,custStreet,custCity,custState,zip, repNum);
				//debug the data
				System.out.println(aCustomer);
				//ask the CustomerIO to store the new Customer data
				try {
					Credentials theCredentials = LoginWindow.displayLoginWindow();
				    CustomerIO.addCustomer(theCredentials, aCustomer);
				    System.out.println("Back in the View --- record was added");
				    AlertBox.display("REQUEST STATUS", "Record Added");
				    
				    parentWindow.close();
				    
				}
				catch(SQLException exception) {
					AlertBox.display("REQUEST STATUS","Add Customer Unsuccessful - SQL Exception" + exception.getMessage());
				}
				
			}
			
		}

	}


}
