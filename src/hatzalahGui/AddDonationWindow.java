package hatzalahGui;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import hatzalahBusiness.Branch;
import hatzalahData.BranchData;
import hatzalahData.DonationIO;
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
	private TextField donorPhoneNum;
	private TextField amount;
	private DatePicker date;
	private ComboBox<String> branchName;
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
		grid.add(branchName, 3, 1);
		
		Button okButton = new Button("OK");
		okButton.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
		okButton.setOnAction(e -> {
			if(!(donorPhoneNum.getText().isBlank() || amount.getText().isBlank() || branchName.getValue().equals("Add Branch"))) {
				try {
					DonationIO.addDonationData(donorPhoneNum.getText(), amount.getText(), date.getValue(), branchName.getValue(), dbconnection);
					JOptionPane.showMessageDialog(null, "You successfully made a donation!");
					clearInputs();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "couldn't add donation");
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

	private void clearInputs() {
		donorPhoneNum.clear();
		amount.clear();	
	}

}
