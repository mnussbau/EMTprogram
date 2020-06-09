package hatzalahGui;

import java.awt.Container;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import hatzalahData.BranchData;
import hatzalahData.PurchaseIO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class AddPurchaseWindow {
	/*
	 * Equipment name 
	 * Branch name 
	 * Qty 
	 * Purchase date (n) 
	 * price
	 */
	private TextField equipmentName;
	private ComboBox<String> branchName;
	private TextField qty;
	private DatePicker purchaseDate;
	private TextField price;
	private ArrayList<String> newBranches = new ArrayList<>();
	private Scene mainScene;
	;

	public AddPurchaseWindow(Stage mainWindow, Connection db) {
		mainScene = mainWindow.getScene();
		BorderPane layout = new BorderPane();
		layout.setPrefSize(500, 100);
		GridPane grid = new GridPane();
		grid.add(new Label("Equipment Name"), 0, 0);
		equipmentName = new TextField();
		grid.add(equipmentName, 0, 1);
		grid.add(new Label("Branch Name"), 1, 0);
		
		try {
			BranchData.getBranch(db).stream().forEach(b -> newBranches.add(b.getBranchName()));
		} catch (SQLException e1) {
			
			JOptionPane.showMessageDialog(null, "error getting branches");
		}
		branchName = new ComboBox<String>(FXCollections.observableArrayList(newBranches));
		branchName.setStyle("-fx-background-color: Lavender");
		grid.add(branchName, 1, 1);
		grid.add(new Label("Quantity"), 2, 0);
		qty = new TextField();
		grid.add(qty, 2, 1);
		grid.add(new Label("Purchase Date"), 3, 0);
		purchaseDate = new DatePicker();
		purchaseDate.setValue(LocalDate.now());
		grid.add(purchaseDate, 3, 1);
		grid.add(new Label("Price"), 4, 0);
		price = new TextField();
		grid.add(price, 4, 1);
		GridPane.setMargin(equipmentName,  new Insets(5, 5, 5, 5));
		GridPane.setMargin(branchName,  new Insets(5, 5, 5, 5));
		GridPane.setMargin(qty,  new Insets(5, 5, 5, 5));
		GridPane.setMargin(purchaseDate,  new Insets(5, 5, 5,5));
		GridPane.setMargin(price,  new Insets(5, 5, 5, 5));
		
		Button okButton = new Button("OK");
		okButton.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
		okButton.setOnAction(new AddPurchaseController(db));
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
	
	

class AddPurchaseController implements EventHandler<ActionEvent>{
	private Connection db;
	public AddPurchaseController(Connection db) {
		this.db = db;
	}
	@Override
	public void handle(ActionEvent event) {
		String equipment = equipmentName.getText();
		String branch = branchName.getValue();
		String quantity =qty.getText();
		LocalDate purchase = purchaseDate.getValue();
		String p = price.getText();
		
		try {
		   PurchaseIO.AddPurchaseData(db, equipment, branch, quantity,
				   purchase, p);
		   JOptionPane.showMessageDialog(null,"Purchase added");
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
