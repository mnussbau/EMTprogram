package GUIHomework;

import java.time.LocalDate;
import java.time.Period;
import javafx.scene.*;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GUIMain extends Application {
	private TextField age_txt;
	private Label output;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Calculate Age");
		primaryStage.setHeight(200);
		primaryStage.setWidth(600);

		Label ageLabel = new Label(" Enter your birthdate in the format yyyy-mm-dd: ");
		age_txt = new TextField();
		Button calculateButton = new Button("Calculate Age");
		ageLabel.setTextFill(Color.WHITE);
		ageLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		calculateButton.setTextFill(Color.DARKTURQUOISE);
		calculateButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		GridPane gridLayout = new GridPane();

		// add the controls to the layout
		gridLayout.add(ageLabel, 0, 1);
		gridLayout.add(age_txt, 1, 1);
		gridLayout.add(calculateButton, 1, 2);
		Platform.isSupported(ConditionalFeature.INPUT_METHOD);
		
		//Actions 
		//age_txt.setOnKeyTyped - what i did that doesn't always work
		age_txt.setOnMouseClicked(e -> {
			if (output != null) {
				gridLayout.getChildren().remove(output);
			}
		});

		calculateButton.setOnAction(e -> {
			// extract data from text fields
			// place that information into some type of visible control
			// should really be checking for nulls...
			LocalDate dob = LocalDate.parse(age_txt.getText());
			LocalDate today = LocalDate.now();
			int year = today.getYear() - dob.getYear();
			if (today.getMonthValue() < dob.getMonthValue()) {
				year--;
			} else if (today.getMonthValue() == dob.getMonthValue() && today.getDayOfMonth() < dob.getDayOfMonth()) {
				year--;
			}
			output = new Label(" You are " + year + " years old");
			output.setTextFill(Color.WHITE);
			output.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
			gridLayout.add(output, 0, 3);
		});

		//styling the grid
		gridLayout.setVgap(25);
		gridLayout.setHgap(25);
		gridLayout.setStyle("-fx-background: lightcoral;");
		// instantiate a scene connecting to layout
		Scene firstScene = new Scene(gridLayout);
		primaryStage.setScene(firstScene);

		// add the scene to the window
		primaryStage.show();

	}

}
