package hatzalahGui;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import hatzalahData.BranchData;
import hatzalahData.BusIO;
import hatzalahData.PurchaseIO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class AddBusWindow {
	
		private TextField VIN;
		private DatePicker datePurchased;
		private DatePicker lastMaintained;
		private TextField price;
		private ComboBox<String> branchName;
		private ArrayList<String> newBranches = new ArrayList<>();
		Scene mainScene;

		public AddBusWindow(Stage mainWindow, Connection db) {
			mainScene = mainWindow.getScene();
			BorderPane borderLayout = new BorderPane();
			borderLayout.setPrefSize(300, 300);
			GridPane grid = new GridPane();
			grid.autosize();
			grid.add(new Label("VIN:"), 0, 0);
			VIN = new TextField();
			grid.add(VIN, 1, 0);
			grid.add(new Label("Date Puchased: "), 0, 2);
			datePurchased = new DatePicker();
			datePurchased.setValue(LocalDate.now());
			datePurchased.setDayCellFactory(picker -> new DateCell() {
				public void updateItem(LocalDate date, boolean empty) {
					super.updateItem(date, empty);
					LocalDate today = LocalDate.now();
					setDisable(empty || date.compareTo(today) > 0);
				}
			});
			grid.add(datePurchased, 1, 2);
			grid.add(new Label("Last Maintained:"), 0, 4);
			lastMaintained = new DatePicker();
			grid.add(lastMaintained, 1, 4);
			grid.add(new Label("Price:"), 0, 6);
			price = new TextField();
			grid.add(price, 1, 6);
			grid.add(new Label("Branch Name:"), 0, 8);
			try {
				BranchData.getBranch(db).stream().forEach(b -> newBranches.add(b.getBranchName()));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "error getting branches");
			}
			branchName = new ComboBox<String>(FXCollections.observableArrayList(newBranches));
			branchName.setStyle("-fx-background-color: Lavender");
			grid.add(branchName, 1, 8);
			GridPane.setMargin(VIN,  new Insets(5, 5, 5, 5));
			GridPane.setMargin(datePurchased,  new Insets(5, 5, 5, 5));
			GridPane.setMargin(lastMaintained,  new Insets(5, 5, 5, 5));
			GridPane.setMargin(price,  new Insets(5, 5, 5, 5));
			GridPane.setMargin(branchName,  new Insets(5, 5, 5, 5));
			Button okButton = new Button("OK");
			okButton.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
			okButton.setOnAction(new AddBusController(db));
			Button backButton = new Button("Back");
			backButton.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
			backButton.setOnAction(e -> mainWindow.setScene(mainScene));
			HBox hbox = new HBox();
			hbox.getChildren().addAll(okButton, backButton);
			HBox.setMargin(okButton, new Insets(2, 2, 2, 2));
			HBox.setMargin(backButton, new Insets(2, 2, 2, 2));
			borderLayout.setBottom(hbox);
			borderLayout.setTop(grid);
			
			borderLayout.setStyle("-fx-background-color: Azure");
			borderLayout.setPadding(new Insets(5,5,5,5));
			
			Scene thisScene = new Scene(borderLayout);
			mainWindow.setScene(thisScene);

			
		}
		class AddBusController implements EventHandler<ActionEvent>{
			private Connection db;
			public AddBusController(Connection db) {
				this.db = db;
			}
			@Override
			public void handle(ActionEvent event) {
				
				String Vin = VIN.getText();
				LocalDate purchase = datePurchased.getValue();
				LocalDate maintained = lastMaintained.getValue();
				String p =price.getText();
				String branch = branchName.getValue();
				
				
				
				
				try {
				if(VIN.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Please fill in a VIN.");
				}
				else if(price.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Please fill in a price.");
				}
				else if(branchName.getValue().isBlank()) {
					String s = (String) JOptionPane.showInputDialog(null, "please choose a branch.", "Choose a branch",
							JOptionPane.PLAIN_MESSAGE, null, branchName.getItems().toArray(), "");
					if ((s != null) && (s.length() > 0)) {
						
						return;
					}
				}
				else if(VIN.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Please fill in a VIN.");
				}
				if(VIN.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Please fill in a VIN.");
				}
				   BusIO.AddBusData(db, Vin, 
						   purchase, maintained, p, branch);
				   JOptionPane.showMessageDialog(null,"Bus added");
				}
				catch (SQLException ex) {
					System.out.println(ex.getMessage()); 
					JOptionPane.showMessageDialog(null,"error occurred");
				}
				catch (DateTimeParseException ex1) {
					JOptionPane.showMessageDialog(null, "error - check dates");
				}
				catch (Exception ex2) {
					JOptionPane.showMessageDialog(null, "error occurred");
				}
				
			}
			
			
			
		}
}
