package hatzalahGui;

import java.sql.*;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import hatzalahData.BusIO;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class UpdateBusWindow extends Stage {
	private Scene mainScene;
	private TextField vinTxtBx;
	private DatePicker pickerDate;

	public UpdateBusWindow(Stage mainWindow, Connection db) {

		mainScene = mainWindow.getScene();
		BorderPane borderLayout = new BorderPane();
		borderLayout.setStyle("-fx-background-color: Azure");
		GridPane grid = new GridPane();
		grid.add(new Label("VIN:"), 0, 0);
		vinTxtBx = new TextField();
		grid.add(vinTxtBx, 0, 1);
		borderLayout.setTop(grid);
		pickerDate = new DatePicker();
		pickerDate.setValue(LocalDate.now());
		pickerDate.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				setDisable(empty || date.compareTo(today) > 0);
			}
		});
		grid.add(new Label("Last Updated:"), 1, 0);
		grid.add(pickerDate, 1, 1);

		Button okButton = new Button("OK");
		okButton.setStyle("-fx-background-color: Lavender");
		okButton.setOnAction(new OkButton_click(db));
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
		private Connection db;

		public OkButton_click(Connection db) {
			this.db = db;
		}

		@Override
		public void handle(ActionEvent event) {
			if (vinTxtBx.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "You need to fill in all the fields");
				return;
			}
			String vin = vinTxtBx.getText();
			LocalDate lastUpdated = pickerDate.getValue();

			try {
				BusIO.UpdateBusData(db, vin, lastUpdated);
				JOptionPane.showMessageDialog(null, "Record updated.");
				vinTxtBx.clear();
				pickerDate.setValue(LocalDate.now());
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,  "error occurred - "+ e.getMessage());			
			}

		}

	}

}
