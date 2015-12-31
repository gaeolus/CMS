package org.resl.cms.CMS_Slave.gui.view;

import org.resl.cms.CMS_Slave.gui.MainApp;
import org.resl.cms.CMS_Slave.gui.model.Config;

import javafx.fxml.FXML;
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
}