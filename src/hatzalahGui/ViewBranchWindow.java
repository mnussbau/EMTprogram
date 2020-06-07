package hatzalahGui;


import hatzalahBusiness.Branch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewBranchWindow {
	
	public ViewBranchWindow(Branch b, Stage mainWindow) {
		Scene originalScene = mainWindow.getScene();
		
		GridPane gp = new GridPane();
		gp.add(new Label("" + b.getBranchId()), 0, 0);
		gp.add(new Label(b.getBranchName()), 1, 0);
		gp.add(new Label("" + b.getYearEstablished()), 2, 0);
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> {
			mainWindow.setScene(originalScene);
		});
		gp.add(backButton, 0, 2);
		
		Scene scene = new Scene(gp);
		mainWindow.setScene(scene);
		
	}

}
