package hatzalahGui;

import java.sql.Connection;

import hatzalahBusiness.Branch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewBranchWindow {
	Scene originalScene;
	Scene mainScene;
	Connection dbConnection;

	public ViewBranchWindow(Branch b, Stage mainWindow, Connection db) {
		originalScene = mainWindow.getScene();

		mainScene = mainWindow.getScene();
		dbConnection = db;
		BorderPane borderLayout = new BorderPane();
		borderLayout.setStyle("-fx-background-color: Azure");
		GridPane grid = new GridPane();
		grid.add(new Label("Branch Name:"), 0, 0);
		Label bName = new Label(b.getBranchName());
		// bName.setDisable(true);
		bName.setStyle("-fx-border-color:Lavender; -fx-padding:3px;");
		grid.add(bName, 0, 1);
		grid.add(new Label("Branch Initial:"), 1, 0);
		Label bIni = new Label(b.getBranchInitial());
		bIni.setStyle("-fx-border-color:Lavender; -fx-padding:3px;");
		grid.add(bIni, 1, 1);
		grid.add(new Label("Year Established"), 2, 0);
		grid.add(new Label("Branch Initial:"), 1, 0);
		Label yrEs = new Label(b.getYearEstablished() + "");
		yrEs.setStyle("-fx-border-color:Lavender; -fx-padding:3px;");
		grid.add(yrEs, 2, 1);
		
		grid.setPadding(new Insets(5.0));
		grid.setVgap(4.0);
		grid.setHgap(4.0);
		borderLayout.setTop(grid);

		Label membercount = new Label("4");
		membercount.setStyle("-fx-border-color:Lavender; -fx-padding:3px;");
		Label busCount = new Label("4");
		busCount.setStyle("-fx-border-color:Lavender; -fx-padding:3px;");
		Label avgCall = new Label("4");
		avgCall.setStyle("-fx-border-color:Lavender; -fx-padding:3px;");
		Label donationCount = new Label("4");
		donationCount.setStyle("-fx-border-color:Lavender; -fx-padding:3px;");
		Label donorCount = new Label("4");
		donorCount.setStyle("-fx-border-color:Lavender; -fx-padding:3px;");
		Label totalDonation = new Label("4");
		totalDonation.setStyle("-fx-border-color:Lavender; -fx-padding:3px;");

		Button backButton = new Button("Back");
		backButton.setStyle("-fx-background-color: Lavender");
		backButton.setOnAction(e -> mainWindow.setScene(originalScene));
		
		GridPane grid2 = new GridPane();
		grid2.setPadding(new Insets(5.0));
		grid2.setVgap(4.0);
		grid2.setHgap(4.0);
		grid2.add(new Label("Number Of Members:"), 0, 0);
		grid2.add(membercount, 1, 0);
		grid2.add(new Label("Number of Buses:"), 0, 1);
		grid2.add(busCount, 1, 1);
		grid2.add(new Label("Average calls per year:"), 0, 2);
		grid2.add(avgCall, 1, 2);
		grid2.add(new Label("Number of donors:"), 0, 3);
		grid2.add(donorCount, 1, 3);
		grid2.add(new Label("Number of donations:"), 0, 4);
		grid2.add(donationCount, 1, 4);
		grid2.add(new Label("Total donations:"), 0, 5);
		grid2.add(totalDonation, 1,5);
		grid2.add(backButton, 0, 6);
		//HBox.setMargin(backButton, new Insets(2, 2, 2, 2));
		borderLayout.setBottom(grid2);
		Scene scene = new Scene(borderLayout);
		mainWindow.setScene(scene);
		mainWindow.sizeToScene();

	}

}
