package hatzalahGui;

import java.sql.Connection;
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
import javafx.stage.Stage;

public class AddDonorWindow {
	/*
	 * Fname
	 *  Name 
	 *  Phone number
	 *   Addr street 
	 *   Addr city 
	 *   Addr state 
	 *   Addr zip
	 */

	TextField fname;
	TextField lname;
	TextField phoneNumber;
	TextField streetAddress;
	ComboBox<String> state;
	TextField city;
	TextField zip;
	Scene mainScene;
	
	
	public AddDonorWindow(Stage mainWindow, Connection dbconnection) {
		mainScene = mainWindow.getScene();
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
		phoneNumber = new TextField();
		grid.add(phoneNumber, 2, 1);
		grid.add(new Label("Street Address"), 0, 2);
		streetAddress = new TextField();
		grid.add(streetAddress, 0, 3);
		grid.add(new Label("City"), 1, 2);
		city = new TextField();
		grid.add(city, 1, 3);
		String[] listOfStates = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
				"IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
				"NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV",
				"WI", "WY" };
		state = new ComboBox<String>(FXCollections.observableArrayList(listOfStates));
		state.setValue("NY");
		state.resize(150, 150);
		state.setStyle("-fx-background-color: Lavender");
		grid.add(new Label("State"), 2, 2);
		grid.add(state, 2, 3);
		zip = new TextField();
		grid.add(new Label("Zip"), 3, 2);
		grid.add(zip, 3, 3);
		
		Button okButton = new Button("OK");
		okButton.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
//		okButton.setOnAction(new addCallData());
		Button backButton = new Button("Back");
		backButton.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
		backButton.setOnAction(e -> mainWindow.setScene(mainScene));
		HBox hbox = new HBox();
		hbox.getChildren().addAll(okButton, backButton);
		HBox.setMargin(okButton, new Insets(2, 2, 2, 2));
		HBox.setMargin(backButton, new Insets(2, 2, 2, 2));
		
		layout.setTop(grid);
		layout.setCenter(hbox);
		layout.setStyle("-fx-background-color: Azure");
		Scene thisScene = new Scene(layout);
		mainWindow.setScene(thisScene);
		
	}
	
}
