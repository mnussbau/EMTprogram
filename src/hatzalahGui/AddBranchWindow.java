package hatzalahGui;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import hatzalahData.BranchData;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddBranchWindow extends Stage {
	private TextField branchNameTxtBx;
	private TextField yearEstTxtBx;
	private TextField initialTxtBx;
	private Scene mainScene;

	public AddBranchWindow(Stage mainWindow,Connection db) {
		mainScene = mainWindow.getScene();
		BorderPane borderLayout = new BorderPane();
		borderLayout.setStyle("-fx-background-color: Azure");
		GridPane grid = new GridPane();
		grid.add(new Label("Branch Name:"), 0, 0);
		branchNameTxtBx = new TextField();
		grid.add(branchNameTxtBx, 0, 1);
		grid.add(new Label("Year Established"), 1, 0);
		yearEstTxtBx = new TextField();
		grid.add(yearEstTxtBx, 1, 1);
		grid.add(new Label("Branch Initial"), 2, 0);
		initialTxtBx = new TextField();
		grid.add(initialTxtBx, 2, 1);
		borderLayout.setTop(grid);

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
		private Connection dbConnection;

		public OkButton_click(Connection connection) {
			this.dbConnection = connection;
		}

		@Override
		public void handle(ActionEvent arg0) {
			if (branchNameTxtBx.getText().isBlank() || yearEstTxtBx.getText().isBlank()
					|| initialTxtBx.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "You need to fill in all the fields");
				return;
			}
			String branchName = branchNameTxtBx.getText();
			if (branchName.length() > 45) {
				JOptionPane.showMessageDialog(null, "Branch name too is long.");
				return;
			}
			String initial = initialTxtBx.getText();
			if (initial.length() > 2) {
				JOptionPane.showMessageDialog(null, "Branch initial too is long.");
				return;
			}
			String yearEst = yearEstTxtBx.getText();
			if (!canParseInt(yearEst) || yearEst.length() != 4) {
				JOptionPane.showMessageDialog(null, "Please check the year.");
				return;
			}

			try {
				BranchData.addBranch(dbConnection, branchName, yearEst, initial);
				branchNameTxtBx.clear();
				JOptionPane.showMessageDialog(null, "Branch Added.");
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "error occurred - " + ex.getMessage());
			}

		}

		private boolean canParseInt(String yearEst) {
			try {
				Integer.parseInt(yearEst);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}

	}
}
