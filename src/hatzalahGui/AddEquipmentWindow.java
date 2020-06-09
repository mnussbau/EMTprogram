package hatzalahGui;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import hatzalahData.EquipmentIO;
import hatzalahData.SymptomIO;
import hatzalahGui.AddSymptomWindow.OkButton_click;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddEquipmentWindow {
	private Scene mainScene;
	private TextField equipNameTxtBx;
	
	public AddEquipmentWindow(Stage mainWindow, Connection c) {
		mainScene = mainWindow.getScene();

		BorderPane borderLayout = new BorderPane();
		borderLayout.setStyle("-fx-background-color: Azure");
		GridPane grid = new GridPane();
		grid.add(new Label("Equipment Name:"), 0, 0);
		equipNameTxtBx = new TextField();
		grid.add(equipNameTxtBx, 0, 1);
		borderLayout.setTop(grid);

		Button okButton = new Button("OK");
		okButton.setStyle("-fx-background-color: Lavender");
		okButton.setOnAction(new OkButton_click(c));
		Button backButton = new Button("Back");
		backButton.setStyle("-fx-background-color: Lavender");
		backButton.setOnAction(e -> mainWindow.setScene(mainScene));
		HBox hbox = new HBox();
		hbox.getChildren().addAll(okButton, backButton);
		HBox.setMargin(okButton, new Insets(2, 2, 2, 2));
		HBox.setMargin(backButton, new Insets(2, 2, 2, 2));
		borderLayout.setBottom(hbox);
		Scene scene = new Scene(borderLayout);
		mainWindow.setScene(scene);
		mainWindow.sizeToScene();

	}

	class OkButton_click implements EventHandler<ActionEvent> {
		private Connection dbConnection;

		public OkButton_click(Connection connection) {
			this.dbConnection = connection;
		}

		@Override
		public void handle(ActionEvent arg0) {
			if (equipNameTxtBx.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "You need to fill in all the fields");
				return;
			}
			String equipName = equipNameTxtBx.getText();
			if (equipName.length() > 45) {
				JOptionPane.showMessageDialog(null, "Symptom name too is long.");
				return;
			}
			try {
				EquipmentIO.addEquipment(dbConnection, equipName);
				equipNameTxtBx.clear();
				JOptionPane.showMessageDialog(null, "Record Added");
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "error occurred - " + ex.getMessage());
			}

		}

	}
}
