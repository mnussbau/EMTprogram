package hatzalahGui;

import java.sql.*;

import javax.swing.JOptionPane;

import hatzalahData.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class AddSymptomWindow extends Stage{
	private Scene mainScene;
	private TextField symptomName;
	private Connection dbConnection;
	
	public AddSymptomWindow(Stage mainWindow, Connection connection) {
		mainScene = mainWindow.getScene();
		dbConnection = connection;
		BorderPane borderLayout = new BorderPane();
		borderLayout.setPrefSize(250, 100);
		borderLayout.setStyle("-fx-background-color: Azure");
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10.0));
		grid.add(new Label("Symtom Name:"), 0, 0);
		symptomName = new TextField();
		grid.add(symptomName, 0, 1);
		borderLayout.setTop(grid);

		
		Button okButton = new Button("OK");
		okButton.setStyle("-fx-background-color: Lavender");
		okButton.setOnAction(new OkButton_click(dbConnection));
		Button backButton = new Button("Back");
		backButton.setStyle("-fx-background-color: Lavender");
		backButton.setOnAction(e -> mainWindow.setScene(mainScene));
		HBox hbox = new HBox();
		hbox.getChildren().addAll(okButton,backButton);
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
			this.dbConnection =connection;
		}
		@Override
		public void handle(ActionEvent arg0) {
			if(symptomName.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "You need to fill in all the fields");
				return;
			}
			String symptom = symptomName.getText();
			if(symptom.length()>45) {
				JOptionPane.showMessageDialog(null, "Symptom name too is long.");
				return;
			}	
			try {
				SymptomIO.addSymptom(dbConnection, symptom);
				symptomName.clear();
				JOptionPane.showMessageDialog(null, "Record Added");
			}catch(SQLException ex) {
				JOptionPane.showMessageDialog(null, "could not add symptom");			
			}
			
		}
		
	}
}
