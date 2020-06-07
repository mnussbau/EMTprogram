package hatzalahGui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddSymptomsToCall {
	Scene scene;
	BorderPane bp = new BorderPane();

	public AddSymptomsToCall(String string, String string2, TextField city, String string3,
			String string4, String string5, String string6, String string7, String string8, String string9,
			Stage mainWindow) {
		
		
		Scene originalScene = mainWindow.getScene();
		GridPane gp = new GridPane();
		gp.add(new Label("How many symptoms would you like to add?"), 0, 0);
		TextField numSymptoms = new TextField();
		gp.add(numSymptoms, 1, 0);
		Button addSymptomsBtn = new Button("Add Symptoms");
		gp.add(addSymptomsBtn, 3, 0);
		addSymptomsBtn.setStyle("-fx-background-color: Lavender");
		
		GridPane gridPane = new GridPane();
		addSymptomsBtn.setOnAction(e -> {
			
			
			int row = 0;
			for(int i = 0; i < Integer.parseInt(numSymptoms.getText()); i++) {
				Label lbl = new Label("Symptom " + (i + 1));
				TextField txt = new TextField();
				gridPane.add(lbl, 0, ++row);
				gridPane.add(txt, 1, row);
				
			}
			bp.setCenter(gridPane);
			mainWindow.sizeToScene();
		});
		
		
		HBox hbox = new HBox();
		Button okButton = new Button("Ok");
		Button backButton = new Button("Back");
		backButton.setStyle("-fx-background-color: Lavender");
		okButton.setStyle("-fx-background-color: Lavender");
		backButton.setOnAction(e -> mainWindow.setScene(originalScene));
		hbox.getChildren().addAll(okButton, backButton);
		HBox.setMargin(okButton, new Insets(2, 2, 2, 2));
		HBox.setMargin(backButton, new Insets(2, 2, 2, 2));
		
		bp.setTop(gp);
		bp.setCenter(gridPane);
		bp.setBottom(hbox);
		scene = new Scene(bp);
		bp.setStyle("-fx-background-color: Azure");
		mainWindow.setScene(scene);
	}

}
