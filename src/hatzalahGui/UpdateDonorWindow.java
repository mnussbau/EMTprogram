package hatzalahGui;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import hatzalahBusiness.Address;
import hatzalahBusiness.Donor;
import hatzalahData.AddressIO;
import hatzalahData.DonorIO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class UpdateDonorWindow {
	Stage mainWindow;
	Scene originalScene;
	Donor donorToUpdate;
	String phoneNum;
	Connection db;
	TextField fnameTxtBx;
	TextField lnameTxtBx;
	TextField phoneNumberTxtBx;
	TextField streetAddressTxtBx;
	ComboBox<String> stateCmboBx;
	TextField cityTxtBx;
	TextField zipTxtBx;
	Address address;

	public UpdateDonorWindow(Stage mainWindow, Connection db) {

		this.mainWindow = mainWindow;
		this.db = db;
		originalScene = mainWindow.getScene();

		phoneNum = JOptionPane.showInputDialog(null, "Please enter the donor phone number.");
		if (phoneNum == null) {
			mainWindow.setScene(originalScene);
			return;
		}
		try {
			donorToUpdate = DonorIO.getDonorData(db, phoneNum);
		} catch (SQLException e) {
			mainWindow.setScene(originalScene);
			JOptionPane.showMessageDialog(null, "Could not find the donor");
			return;
		}
		if (donorToUpdate == null) {
			mainWindow.setScene(originalScene);
			JOptionPane.showMessageDialog(null, "Phone number not found.");
			return;
		}

		BorderPane layout = new BorderPane();
		GridPane grid = new GridPane();

		grid.autosize();
		grid.add(new Label("First Name"), 0, 0);
		fnameTxtBx = new TextField(donorToUpdate.getDonor_fname());
		fnameTxtBx.setDisable(true);
		grid.add(fnameTxtBx, 0, 1);
		grid.add(new Label("Last Name"), 1, 0);
		lnameTxtBx = new TextField(donorToUpdate.getDonor_lname());
		grid.add(lnameTxtBx, 1, 1);
		grid.add(new Label("Phone Number"), 2, 0);
		phoneNumberTxtBx = new TextField(donorToUpdate.getDonor_phone_num());
		grid.add(phoneNumberTxtBx, 2, 1);
		try {
			address = AddressIO.getAddress(db, donorToUpdate.getDonor_addr_id());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Information did not load properly.");
			mainWindow.setScene(originalScene);
		}
		grid.add(new Label("Street Address"), 0, 2);
		streetAddressTxtBx = new TextField(address.getAddrStreet());
		grid.add(streetAddressTxtBx, 0, 3);
		grid.add(new Label("City"), 1, 2);
		cityTxtBx = new TextField(address.getAddrCity());
		grid.add(cityTxtBx, 1, 3);
		String[] listOfStates = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
				"IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
				"NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV",
				"WI", "WY" };
		stateCmboBx = new ComboBox<String>(FXCollections.observableArrayList(listOfStates));
		stateCmboBx.setValue(address.getAddrState());
		stateCmboBx.resize(150, 150);
		stateCmboBx.setStyle("-fx-background-color: Lavender");
		grid.add(new Label("State"), 2, 2);
		grid.add(stateCmboBx, 2, 3);
		zipTxtBx = new TextField(address.getZip());
		grid.add(new Label("Zip"), 3, 2);
		grid.add(zipTxtBx, 3, 3);

		Button okButton = new Button("OK");
		okButton.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
		okButton.setOnAction(new OkButton_click(db));
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

	}

	class OkButton_click implements EventHandler<ActionEvent> {
		Connection db;

		public OkButton_click(Connection db) {
			this.db = db;
		}

		@Override
		public void handle(ActionEvent event) {
			if (foundEmptyFields()) {
				JOptionPane.showMessageDialog(null, "All fields must be filled in.");
				return;
			}
			String identifyingPhoneNum = donorToUpdate.getDonor_phone_num();
			String lname = lnameTxtBx.getText();
			String newPhoneNum = phoneNumberTxtBx.getText();
			if (phoneNumberTxtBx.getText().length() != 10) {
				JOptionPane.showMessageDialog(null, "Check phone number");
				return;
			}
			String addrStrt = streetAddressTxtBx.getText();
			String addrCity = cityTxtBx.getText();
			String addrState = stateCmboBx.getValue();
			String addrZip = zipTxtBx.getText();

			try {
				DonorIO.updateDonorData(db, identifyingPhoneNum, lname, newPhoneNum, addrStrt, addrCity, addrState,
						addrZip);
				JOptionPane.showMessageDialog(null, "Donor information changed.");
				mainWindow.setScene(originalScene);

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error. Could not update.");
			}

		}

		private boolean foundEmptyFields() {
			if (lnameTxtBx.getText().isBlank() || phoneNumberTxtBx.getText().isBlank()
					|| streetAddressTxtBx.getText().isBlank() || cityTxtBx.getText().isBlank()
					|| stateCmboBx.getValue() == null || zipTxtBx.getText().isBlank()) {
				return true;
			}
			return false;
		}

	}
}
