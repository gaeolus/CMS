package org.resl.cms.CMS_Slave.gui.view;

import org.resl.cms.CMS_Slave.gui.MainApp;
import org.resl.cms.CMS_Slave.gui.model.Config;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ConfigOverviewController {
    @FXML
    private TableView<Config> configTable;
    @FXML
    private TableColumn<Config, String> configColumn;
    @FXML
    private TableColumn<Config, String> valueColumn;

    @FXML
    private Label configLabel;
    @FXML
    private Label valueLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ConfigOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        configColumn.setCellValueFactory(cellData -> cellData.getValue().configurationProperty());
        valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        configTable.setItems(mainApp.getConfigData());
    }
    
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditConfig() {
        Config selectedConfig = configTable.getSelectionModel().getSelectedItem();
        if (selectedConfig != null) {
            boolean okClicked = mainApp.showConfigEditDialog(selectedConfig);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Config Selected");
            alert.setContentText("Please select a configuration in the table.");

            alert.showAndWait();
        }
    }
}