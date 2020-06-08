package hatzalahGui;

import java.sql.Connection;
import java.time.LocalDate;

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
import javafx.stage.Stage;

public class AddDonationWindow {
	/*
	 * Donor phone number 
	 * Amt 
	 * Date 
	 * Branch name
	 */
	private TextField fname;
	private TextField lname;
	private TextField donorPhoneNum;
	private TextField amount;
	private DatePicker date;
	private TextField branchName;
	Scene mainScene;
	
	public AddDonationWindow(Stage mainWindow, Connection dbconnection) {
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
		donorPhoneNum = new TextField();
		grid.add(donorPhoneNum, 2, 1);
		grid.add(new Label("Amount"), 0, 2);
		amount = new TextField();
		grid.add(amount, 0, 3);
		grid.add(new Label("Date"), 1, 2);
		date = new DatePicker();
		date.setValue(LocalDate.now());
		date.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				setDisable(empty || date.compareTo(today) > 0);
			}
		});
		grid.add(date, 1, 3);
		grid.add(new Label("Branch Name"), 2, 2);
		branchName = new TextField();
		grid.add(branchName, 2, 3);
		
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
