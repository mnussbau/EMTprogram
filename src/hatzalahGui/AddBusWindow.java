package hatzalahGui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddBusWindow extends Stage{
	
		private TextField VIN;
		private TextField datePurchased;
		private TextField lastMaintained;
		private TextField price;
		private TextField branchName;

		public AddBusWindow() {
			BorderPane borderLayout = new BorderPane();
			GridPane grid = new GridPane();
			grid.add(new Label("VIN:"), 0, 0);
			VIN = new TextField();
			grid.add(VIN, 1, 0);
			grid.add(new Label("Date Puchased: "), 0, 1);
			datePurchased = new TextField();
			grid.add(datePurchased, 1, 1);
			grid.add(new Label("Last Maintained:"), 0, 2);
			lastMaintained = new TextField();
			grid.add(lastMaintained, 1, 2);
			grid.add(new Label("Price:"), 0, 3);
			price = new TextField();
			grid.add(price, 1, 3);
			grid.add(new Label("Branch Name:"), 0, 4);
			branchName = new TextField();
			grid.add(branchName, 1, 4);
			borderLayout.setCenter(grid);
			Button okButton = new Button("OK");
//			okButton.setOnAction(new AddOrgController(dbConnection));
			Button backButton = new Button("Back");
			backButton.setOnAction(e -> this.close());
			HBox hbox = new HBox();
			hbox.getChildren().addAll(okButton, backButton);
			borderLayout.setBottom(hbox);
			Scene scene = new Scene(borderLayout);

			this.setScene(scene);
			this.initModality(Modality.APPLICATION_MODAL);
			this.show();
		}

}
