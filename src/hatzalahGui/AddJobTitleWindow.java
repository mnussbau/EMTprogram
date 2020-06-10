package hatzalahGui;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import hatzalahData.JobIO;
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

public class AddJobTitleWindow {
	
		private TextField jobName;
		Scene mainScene;

		public AddJobTitleWindow(Stage mainWindow, Connection db) {
			mainScene = mainWindow.getScene();
			BorderPane borderLayout = new BorderPane();
			borderLayout.setPrefSize(300, 100);
			GridPane grid = new GridPane();
			grid.add(new Label("Job Name"), 0, 0);
			jobName = new TextField();
			grid.add(jobName, 1, 0);
			GridPane.setMargin(jobName,  new Insets(5, 5, 5, 5));
			Button okButton = new Button("OK");
			okButton.setStyle("-fx-background-color: Lavender;-fx-border-color: Teal; -fx-border-width: 1 1 1 1;");
			okButton.setOnAction(new AddJobTitleController(db));
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
		
		class AddJobTitleController implements EventHandler<ActionEvent>{
			private Connection db;
			public AddJobTitleController(Connection db) {
				this.db = db;
			}
			
			 @Override
			 public void handle(ActionEvent event){
				 String job = jobName.getText();
				 if(jobName.getText().isBlank()) {
					 JOptionPane.showMessageDialog(null, "Please fill in a value.");
					 return;
				 }
				 try {
					 JobIO.AddJobData(db, job);
					 
					 jobName.clear();
				 }
				 catch (SQLException ex) {
						
						JOptionPane.showMessageDialog(null,"error occurred");
						jobName.clear();
					}
					catch (DateTimeParseException ex1) {
						JOptionPane.showMessageDialog(null, "error - check dates");
						jobName.clear();
					}
					catch (Exception ex2) {
						JOptionPane.showMessageDialog(null, "error occurred");
						jobName.clear();
					}
			 }
			
		}
		
		
}
