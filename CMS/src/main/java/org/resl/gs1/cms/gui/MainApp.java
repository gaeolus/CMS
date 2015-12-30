package org.resl.gs1.cms.gui;

import java.io.IOException;

import org.resl.gs1.cms.gui.model.GS1Code;
import org.resl.gs1.cms.gui.view.CodeEditDialogController;
import org.resl.gs1.cms.gui.view.SystemOverviewController;

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

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("CMS System - Server");

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
	 * Shows the system overview inside the root layout.
	 */
	public void showSystemOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SystemOverview.fxml"));
			AnchorPane systemOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(systemOverview);

			// Give the controller access to the main app.
			SystemOverviewController controller = loader.getController();
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

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<GS1Code> gs1CodeData = FXCollections.observableArrayList();

	/**
	 * Constructor
	 */
	public MainApp() {
		// Add some sample data
		gs1CodeData.add(new GS1Code("GTIN", "1234", "1234"));
		gs1CodeData.add(new GS1Code("GLN", "something", "1234"));
		gs1CodeData.add(new GS1Code("GSRN", "hello", "how r u"));
	}

	/**
	 * Returns the data as an observable list of Persons. 
	 * @return
	 */
	public ObservableList<GS1Code> getCodeData() {
		return gs1CodeData;
	}

	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 * 
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showCodeEditDialog(GS1Code code) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CodeEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Code");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			CodeEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setCode(code);

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
	public void showHistoryEditDialog(String history){
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
}