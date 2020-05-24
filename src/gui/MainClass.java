

package gui;




import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainClass extends Application{
    private Stage mainWindow;  //save reference to the main window
    private Stage customerWindow;
    
	

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage mainWindow) throws Exception {
		this.mainWindow = mainWindow;
		this.mainWindow.setTitle("Hatzolah");
	    //use a BorderPane to split window into 5 sections
		BorderPane pane = new BorderPane();
		//set up each menu
		Menu addMenu = new Menu("Add");
		Menu updateMenu = new Menu("Update");
		Menu viewMenu = new Menu("View");
		
		//set up menu items for the Add menu
		MenuItem addBranchMenuItem = new MenuItem("Branch"); //new window will open
		addBranchMenuItem.setOnAction(new AddBranchHandler());
		//addMenu.getItems().add(addBranchMenuItem);//new window will open
		MenuItem addBusMenuItem = new MenuItem("Bus");
		//addMenu.getItems().add(addBusMenuItem);
		addBusMenuItem.setOnAction(new AddBusHandler());
		MenuItem addCallMenuItem = new MenuItem("Call"); //new window will open
		addCallMenuItem.setOnAction(new AddCallHandler());
		MenuItem addCredentialsMenuItem = new MenuItem("Credential"); //new window will open
		addCredentialsMenuItem.setOnAction(new AddCredentialsHandler());
		MenuItem addDonationMenuItem = new MenuItem("Donation"); //new window will open
		addDonationMenuItem.setOnAction(new AddDonationHandler());
		MenuItem addDonorMenuItem = new MenuItem("Donor"); //new window will open
		addDonorMenuItem.setOnAction(new AddDonorHandler());
		MenuItem addJobTitleMenuItem = new MenuItem("Job Title"); //new window will open
		addJobTitleMenuItem.setOnAction(new AddJobTitleHandler());
		MenuItem addMemberMenuItem = new MenuItem("Member"); //new window will open
		addMemberMenuItem.setOnAction(new AddMemberHandler());
		MenuItem addPurchaseEquipmentMenuItem = new MenuItem("Purchase Equipment"); //new window will open
		addPurchaseEquipmentMenuItem.setOnAction(new AddPurchaseEquipmentHandler());
		MenuItem addSymptomMenuItem = new MenuItem("Symptom"); //new window will open
		addSymptomMenuItem.setOnAction(new AddSymptomHandler());
		addMenu.getItems().addAll(addBranchMenuItem, addBusMenuItem, addCallMenuItem, addCredentialsMenuItem, addDonationMenuItem, 
				addDonorMenuItem, addJobTitleMenuItem, addMemberMenuItem, addPurchaseEquipmentMenuItem, addSymptomMenuItem);
		//set up menu items for the employee menu
		MenuItem updateBusInfoMenuItem = new MenuItem("Bus Info"); //new window will open
		updateBusInfoMenuItem.setOnAction(new UpdateBusInfoHandler());
		MenuItem updateDonorInfoMenuItem = new MenuItem("Donor Info"); //new window will open
		updateDonorInfoMenuItem.setOnAction(new UpdateDonorInfoHandler());
		MenuItem memberInfoMenuItem = new MenuItem("Member Info"); //new window will open
		memberInfoMenuItem.setOnAction(new UpdateMemberInfoHandler());
		updateMenu.getItems().addAll(updateBusInfoMenuItem,updateDonorInfoMenuItem, memberInfoMenuItem);
		
		
		//set up list customers menu item
		
		MenuBar theMenuBar = new MenuBar();
		theMenuBar.getMenus().addAll(addMenu,updateMenu,viewMenu);
		pane.setTop(theMenuBar);
		Scene theScene = new Scene(pane, 500,500);
		this.mainWindow.setScene(theScene);
		this.mainWindow.show();
		
	}
	
	  
	   
	   
	   
	  
	   
	   class AddBranchHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			new AddBranchWindow();
			
		}
		   
	   }
	   
	  
	   
	   class AddBusHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent arg0) {
				new AddBusWindow();
				
			}
			   
		   }
	   
	   class AddCallHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent arg0) {
				new AddCallWindow();
				
			}
			   
		   }
	   
	   class AddCredentialsHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent arg0) {
				new AddCredentialsWindow();
				
			}
			   
		   } 
	   
	   class AddDonationHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent arg0) {
				new AddDonationWindow();
				
			}
			   
		   } 
	   class AddDonorHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent arg0) {
				new AddCredentialsWindow();
				
			}
			   
		   } 
	   class AddJobTitleHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent arg0) {
				new AddJobTitleWindow();
				
			}
			   
		   } 
	   class AddMemberHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent arg0) {
				new AddMemberWindow();
				
			}
			   
		   } 
	   
	   class AddPurchaseEquipmentHandler implements  EventHandler<ActionEvent>{
		   @Override
			public void handle(ActionEvent arg0) {
				new AddPurchaseWindow();
				
			}
	   }
	   
	   class AddSymptomHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent arg0) {
				new AddSymptomWindow();
				
			}
			   
		   } 
	   
	   class UpdateBusInfoHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent arg0) {
				new UpdateBusWindow();
				
			}
			   
		   } 
	   class UpdateDonorInfoHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent arg0) {
				new UpdateDonorWindow();
				
			}
			   
		   } 
	   
	   class UpdateMemberInfoHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent arg0) {
				new UpdateMemberWindow();
				
			}
			   
		   } 
	  
}
