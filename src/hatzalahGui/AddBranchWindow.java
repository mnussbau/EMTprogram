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

public class AddBranchWindow extends Stage {
	private TextField branchName;
	private TextField orgExemptID;

	public AddBranchWindow() {
		BorderPane borderLayout = new BorderPane();
		GridPane grid = new GridPane();
		grid.add(new Label("Branch Name:"), 0, 0);
		branchName = new TextField();
		grid.add(branchName, 1, 0);
		grid.add(new Label("Exempt ID: "), 0, 1);
		orgExemptID = new TextField();
		grid.add(orgExemptID, 1, 1);
		borderLayout.setCenter(grid);
		Button okButton = new Button("OK");
//		okButton.setOnAction(new AddOrgController(dbConnection));
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
