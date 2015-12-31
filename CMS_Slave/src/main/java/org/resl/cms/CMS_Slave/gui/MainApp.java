package org.resl.cms.CMS_Slave.gui;

import java.io.IOException;

import org.resl.cms.CMS_Slave.gui.model.Config;
import org.resl.cms.CMS_Slave.gui.view.ConfigOverviewController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    	configData.add(new Config("Server IP", "1234"));
    	configData.add(new Config("Server Port", "1234"));
    	configData.add(new Config("Business Location", "1234"));
    	configData.add(new Config("Write Point", "1234"));
    	configData.add(new Config("Company Prefix", "1234"));
    	configData.add(new Config("Item Reference", "1234"));
    	configData.add(new Config("Location Reference", "1234"));
    	configData.add(new Config("Service Reference", "1234"));
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
		launch(args);
	}
}