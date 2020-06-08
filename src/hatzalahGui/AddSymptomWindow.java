package hatzalahGui;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import javax.swing.JOptionPane;

import hatzalahData.SymptomIO;
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

public class AddSymptomWindow extends Stage{
	private Scene mainScene;
	private TextField symptomName;
	private Connection dbConnection;
	private Label alertLabel;
	
	public AddSymptomWindow(Stage mainWindow, Connection connection) {
		mainScene = mainWindow.getScene();
		dbConnection = connection;
		BorderPane borderLayout = new BorderPane();
		borderLayout.setStyle("-fx-background-color: Azure");
		GridPane grid = new GridPane();
		grid.add(new Label("Symtom Name:"), 0, 0);
		symptomName = new TextField();
		grid.add(symptomName, 0, 1);
		alertLabel = new Label("Error");
		alertLabel.setVisible(false);
		grid.add(alertLabel, 0, 2);
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
		private Connection dbconnection;
		
		public OkButton_click(Connection connection) {
			dbConnection =connection;
		}
		@Override
		public void handle(ActionEvent arg0) {
			alertLabel.setVisible(false);
			if(symptomName.getText().isBlank()) {
				alertLabel.setText("You need to fill in all the fields");
				alertLabel.setVisible(true);
				return;
			}
			String symptom = symptomName.getText();
			if(symptom.length()>45) {
				alertLabel.setText("Symptom name too is long.");
				alertLabel.setVisible(true);
				return;
			}
			
			try {
				SymptomIO.addSymptom(dbConnection, symptom);
				symptomName.clear();
				alertLabel.setText("Record Added");
				alertLabel.setVisible(true);
			}catch(SQLException ex) {
				alertLabel.setText("error occurred - "+ ex.getMessage());
				alertLabel.setVisible(true);
			}
			
		}
		
	}
}
