package hatzalahGui;

import java.awt.List;
import java.io.FileInputStream;
import java.util.ArrayList;

import hatzalahBusiness.Branch;
import hatzalahData.BranchData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainClass extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage mainWindow) throws Exception {
		mainWindow.setHeight(500);
		mainWindow.setWidth(600);
		mainWindow.setTitle("Hatzolah");
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: Azure");
		
		Menu addMenu = new Menu("Add");
		Menu updateMenu = new Menu("Update");
		Menu viewMenu = new Menu("View");

		MenuItem addBranchMenuItem = new MenuItem("Branch");
		addBranchMenuItem.setOnAction(e -> {
			new AddBranchWindow();
		});
		MenuItem addBusMenuItem = new MenuItem("Bus");
		addBusMenuItem.setOnAction(e -> {
			new AddBusWindow();
		});
		MenuItem addCallMenuItem = new MenuItem("Call");
		addCallMenuItem.setOnAction(e -> {
			new AddCallWindow(mainWindow);
		});
		MenuItem addCredentialsMenuItem = new MenuItem("Credential");
		addCredentialsMenuItem.setOnAction(e -> {
			new AddCredentialsWindow();
		});
		MenuItem addDonationMenuItem = new MenuItem("Donation");
		addDonationMenuItem.setOnAction(e -> {
			new AddDonationWindow();
		});
		MenuItem addDonorMenuItem = new MenuItem("Donor");
		addDonorMenuItem.setOnAction(e -> {
			new AddDonorWindow();
		});
		MenuItem addJobTitleMenuItem = new MenuItem("Job Title");
		addJobTitleMenuItem.setOnAction(e -> {
			new AddJobTitleWindow();
		});
		MenuItem addMemberMenuItem = new MenuItem("Member");
		addMemberMenuItem.setOnAction(e -> {
			new AddMemberWindow();
		});
		MenuItem addPurchaseEquipmentMenuItem = new MenuItem("Purchase Equipment");
		addPurchaseEquipmentMenuItem.setOnAction(e -> {
			new AddPurchaseWindow();
		});
		MenuItem addSymptomMenuItem = new MenuItem("Symptom");
		addSymptomMenuItem.setOnAction(e -> {
			new AddSymptomWindow();
		});
		addMenu.getItems().addAll(addBranchMenuItem, addBusMenuItem, addCallMenuItem, addCredentialsMenuItem,
				addDonationMenuItem, addDonorMenuItem, addJobTitleMenuItem, addMemberMenuItem,
				addPurchaseEquipmentMenuItem, addSymptomMenuItem);
		
		MenuItem updateBusInfoMenuItem = new MenuItem("Bus Info");
		updateBusInfoMenuItem.setOnAction(e -> {
			new UpdateBusWindow();
		});
		MenuItem updateDonorInfoMenuItem = new MenuItem("Donor Info");
		updateDonorInfoMenuItem.setOnAction(e -> {
			new UpdateDonorWindow();
		});
		MenuItem memberInfoMenuItem = new MenuItem("Member Info");
		memberInfoMenuItem.setOnAction(e -> {
			new UpdateMemberWindow();
		});
		updateMenu.getItems().addAll(updateBusInfoMenuItem, updateDonorInfoMenuItem, memberInfoMenuItem);
		
		ArrayList<Branch> branches = BranchData.getBranch();
		for(int i = 0; i < branches.size(); i++) {
			int x = i;
			MenuItem branchItem = new MenuItem(branches.get(i).getBranchName());
			branchItem.setOnAction(e ->{
				new ViewBranchWindow(branches.get(x), mainWindow);
				
			});
			viewMenu.getItems().add(branchItem);
		}

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

	}

}
