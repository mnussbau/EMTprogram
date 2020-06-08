package hatzalahGui;

import java.sql.Connection;
import java.time.LocalDate;

import hatzalahGui.AddCallWindow.addCallData;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	private TextField branchName;
	private TextField qty;
	private DatePicker purchaseDate;
	private TextField price;
	private Scene mainScene;

	public AddPurchaseWindow(Stage mainWindow, Connection db) {
		mainScene = mainWindow.getScene();
		BorderPane layout = new BorderPane();
		GridPane grid = new GridPane();
		grid.autosize();
		grid.add(new Label("Equipment Name"), 0, 0);
		equipmentName = new TextField();
		grid.add(equipmentName, 0, 1);
		grid.add(new Label("Branch Name"), 1, 0);
		branchName = new TextField();
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
