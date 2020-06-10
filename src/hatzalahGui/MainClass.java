package hatzalahGui;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import hatzalahBusiness.Branch;
import hatzalahData.BranchData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainClass extends Application {
	private Connection dbconnection;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage mainWindow) throws Exception {

		String url = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS;databaseName=Hatzolah;integratedSecurity=true";
		dbconnection = DriverManager.getConnection(url);
		if (dbconnection == null) {
			JOptionPane.showMessageDialog(null, "Could not connect to database");
		}
		dbconnection.setAutoCommit(false);
		mainWindow.setHeight(500);
		mainWindow.setWidth(600);
		mainWindow.setTitle("Hatzolah");
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: Azure");

		Menu addMenu = new Menu("Add");
		Menu updateMenu = new Menu("Update");
		Menu viewMenu = new Menu("Branch Info");

		MenuItem addBranchMenuItem = new MenuItem("Branch");
		addBranchMenuItem.setOnAction(e -> {
			new AddBranchWindow(mainWindow, dbconnection);
		});
		MenuItem addBusMenuItem = new MenuItem("Bus");
		addBusMenuItem.setOnAction(e -> {
			new AddBusWindow(mainWindow, dbconnection);
		});
		MenuItem addCallMenuItem = new MenuItem("Call");
		addCallMenuItem.setOnAction(e -> {
			new AddCallWindow(mainWindow, dbconnection);
		});
		MenuItem addCredentialsMenuItem = new MenuItem("Credential");
		addCredentialsMenuItem.setOnAction(e -> {
			new AddCredentialsWindow(mainWindow, dbconnection);
		});
		MenuItem addDonationMenuItem = new MenuItem("Donation");
		addDonationMenuItem.setOnAction(e -> {
			new AddDonationWindow(mainWindow, dbconnection);
		});
		MenuItem addDonorMenuItem = new MenuItem("Donor");
		addDonorMenuItem.setOnAction(e -> {
			new AddDonorWindow(mainWindow, dbconnection);
		});
		MenuItem addEqupmentMenuItem = new MenuItem("Equipment");
		addEqupmentMenuItem.setOnAction(e -> {
			new AddEquipmentWindow(mainWindow, dbconnection);
		});
		MenuItem addJobTitleMenuItem = new MenuItem("Job Title");
		addJobTitleMenuItem.setOnAction(e -> {
			new AddJobTitleWindow(mainWindow, dbconnection);
		});
		MenuItem addMemberMenuItem = new MenuItem("Member");
		addMemberMenuItem.setOnAction(e -> {
			new AddMemberWindow(mainWindow, dbconnection);
		});
		MenuItem addPurchaseEquipmentMenuItem = new MenuItem("Purchase Equipment");
		addPurchaseEquipmentMenuItem.setOnAction(e -> {
			new AddPurchaseWindow(mainWindow, dbconnection);
		});
		MenuItem addSymptomMenuItem = new MenuItem("Symptom");
		addSymptomMenuItem.setOnAction(e -> {
			new AddSymptomWindow(mainWindow, dbconnection);
		});
		addMenu.getItems().addAll(addBranchMenuItem, addBusMenuItem, addCallMenuItem, addCredentialsMenuItem,
				addDonationMenuItem, addDonorMenuItem, addEqupmentMenuItem, addJobTitleMenuItem, addMemberMenuItem,
				addPurchaseEquipmentMenuItem, addSymptomMenuItem);

		MenuItem updateBusInfoMenuItem = new MenuItem("Bus Info");
		updateBusInfoMenuItem.setOnAction(e -> {
			new UpdateBusWindow(mainWindow, dbconnection);
		});
		MenuItem updateDonorInfoMenuItem = new MenuItem("Donor Info");
		updateDonorInfoMenuItem.setOnAction(e -> {
			new UpdateDonorWindow(mainWindow, dbconnection);
		});
		MenuItem memberInfoMenuItem = new MenuItem("Member Info");
		memberInfoMenuItem.setOnAction(e -> {
			new UpdateMemberWindow(mainWindow, dbconnection);
		});
		updateMenu.getItems().addAll(updateBusInfoMenuItem, updateDonorInfoMenuItem, memberInfoMenuItem);

		MenuBar theMenuBar = new MenuBar();
		theMenuBar.setStyle("-fx-background-color: Lavender");
		theMenuBar.getMenus().addAll(addMenu, updateMenu, viewMenu);
		pane.setTop(theMenuBar);
		ImageView imageView = new ImageView();
		Image image1 = new Image(new FileInputStream("src\\hatzalahGui\\Hatzalah Logo.png"));
		imageView.setImage(image1);
		mainWindow.getIcons().add(image1);
		pane.setCenter(imageView);
		Scene theScene = new Scene(pane, 600, 500);
		mainWindow.setScene(theScene);
		mainWindow.sizeToScene();
		mainWindow.show();
		
		
		theMenuBar.setOnMouseEntered(d -> {
			try {
				viewMenu.getItems().clear();
				BranchData.getBranch(dbconnection).forEach(branch -> {
					MenuItem i = new MenuItem(branch.getBranchName());
					i.setOnAction(e -> new ViewBranchWindow(branch, mainWindow, dbconnection));
					viewMenu.getItems().add(i);
				});
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "error occurred - " + e.getMessage());
			}
		});

	}

}
