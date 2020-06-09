package hatzalahGui;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import hatzalahData.DonationIO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddDonationWindow {
	private TextField donorPhoneNum;
	private TextField amount;
	private DatePicker date;
	private TextField branchName;
	Scene mainScene;
	
	public AddDonationWindow(Stage mainWindow, Connection dbconnection) {
		mainScene = mainWindow.getScene();
		BorderPane layout = new BorderPane();
		GridPane grid = new GridPane();
		grid.add(new Label("Phone Number"), 0, 0);
		donorPhoneNum = new TextField();
		grid.add(donorPhoneNum, 0, 1);
		grid.add(new Label("Amount"), 1, 0);
		amount = new TextField();
		grid.add(amount, 1, 1);
		grid.add(new Label("Date"), 2, 0);
		date = new DatePicker();
		date.setValue(LocalDate.now());
		date.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				setDisable(empty || date.compareTo(today) > 0);
			}
		});
		grid.add(date, 2, 1);
		grid.add(new Label("Branch Name"), 3, 0);
		branchName = new TextField();
		grid.add(branchName, 3, 1);
		
		Button okButton = new Button("OK");
		okButton.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
		okButton.setOnAction(e -> {
			if(!(donorPhoneNum.getText().isBlank() || amount.getText().isBlank() || branchName.getText().isBlank())) {
				try {
					DonationIO.addDonationData(donorPhoneNum.getText(), amount.getText(), date.getValue(), branchName.getText(), dbconnection);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}else {
				JOptionPane.showMessageDialog(null, "Please fill in all fields");
			}
		});
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
