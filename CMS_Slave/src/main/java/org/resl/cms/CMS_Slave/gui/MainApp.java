package org.resl.cms.CMS_Slave.gui;

import java.io.IOException;

import org.resl.cms.CMS_Slave.backend.Persist;
import org.resl.cms.CMS_Slave.gui.model.Config;
import org.resl.cms.CMS_Slave.gui.view.ConfigEditDialogController;
import org.resl.cms.CMS_Slave.gui.view.ConfigOverviewController;
import org.resl.cms.CMS_Slave.gui.view.IssueDialogController;
import org.resl.cms.CMS_Slave.gui.view.RequestDialogController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	/**
     * The data as an observable list of Persons.
     */
    private ObservableList<Config> configData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
    	configData.add(new Config("Server IP", "192.168.0.2"));
    	configData.add(new Config("Server Port", "8080"));
    	configData.add(new Config("Business Location", "KAIST"));
    	configData.add(new Config("Write Point", "N1"));
    	configData.add(new Config("Company Prefix", "0614141"));
    	configData.add(new Config("Item Reference", "112345"));
    	configData.add(new Config("Location Reference", "12345"));
    	configData.add(new Config("Service Reference", "1234567890"));
    }

    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Config> getConfigData() {
        return configData;
    }

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("CMS System - Client");

		initRootLayout();

		showSystemOverview();
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showSystemOverview() {
		try {
			// Load system overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SystemOverview.fxml"));
			AnchorPane systemOverview = (AnchorPane) loader.load();

			// Set system overview into the center of root layout.
			rootLayout.setCenter(systemOverview);
			
			// Give the controller access to the main app.
	        ConfigOverviewController controller = loader.getController();
	        controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		Persist persist=new Persist();
		persist.dropDatabase();
		persist.createDatabase();
		persist.createTable();
		launch(args);
	}
	
	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 * 
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showConfigEditDialog(Config config) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/ConfigEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Configuration");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        ConfigEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setConfig(config);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	/**
	 * Opens a dialog to show history. The dialog is shown until
	 * the user closes it.
	 * 
	 * @param person the person object to be edited
	 */
	public void showStatus(String status){
		try{			
			Label label = new Label(status);
			label.setWrapText(true);
			label.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 20; -fx-text-fill: black;");

			StackPane layout = new StackPane();
			layout.setStyle("-fx-background-color: white; -fx-padding: 10;");
			layout.getChildren().setAll(label);
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Status");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(layout);
			dialogStage.setScene(scene);
			
			dialogStage.showAndWait();			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens a dialog to show history. The dialog is shown until
	 * the user closes it.
	 * 
	 * @param person the person object to be edited
	 */
	public void showHistory(String history){
		try{			
			Label label = new Label(history);
			label.setWrapText(true);
			label.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 20; -fx-text-fill: black;");

			StackPane layout = new StackPane();
			layout.setStyle("-fx-background-color: white; -fx-padding: 10;");
			layout.getChildren().setAll(label);
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("History");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(layout);
			dialogStage.setScene(scene);
			
			dialogStage.showAndWait();			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 * 
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showRequestDialog() {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/RequestDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Code Request");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        RequestDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 * 
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showIssueDialog() {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/IssueDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Code Issue");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        IssueDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}