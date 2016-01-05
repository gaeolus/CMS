package org.resl.cms.CMS_Slave.gui.view;

import org.resl.cms.CMS_Slave.gui.MainApp;
import org.resl.cms.CMS_Slave.gui.model.Config;
import org.resl.cms.CMS_Slave.interfaceback.InterfaceBack;
import org.resl.cms.CMS_Slave.model.KeyType;

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
     
    public static TableColumn<Config, String> valueColumncopy;
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
    
    public static TableColumn<Config, String> getValueColumn(){
    	return valueColumncopy;
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
        valueColumncopy = valueColumn;
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
    
    /**
	 * Called when the user clicks the registration button. Opens a dialog to show
	 * history details of the system.
	 */
	@FXML
	private void handleRegistration() {
		try{
			InterfaceBack back=new InterfaceBack();
			String ip = valueColumn.getCellData(0);
			String port = valueColumn.getCellData(1);
			String bizLocation = valueColumn.getCellData(2);
			String writePoint = valueColumn.getCellData(3);
			back.configure(ip, port, bizLocation, writePoint);
			String status=back.register();
			
			mainApp.showStatus(status);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Called when the user clicks the issue button. Opens a dialog to show
	 * history details of the system.
	 */
	@FXML
	private void handleIssue() {
		InterfaceBack back = new InterfaceBack();
		KeyType key=new KeyType("gtin",Integer.parseInt(valueColumn.getCellData(4)),Integer.parseInt(valueColumn.getCellData(5)));
		
		String status=back.issue(key);
		mainApp.showStatus(status);
	}
	
	/**
	 * Called when the user clicks the history button. Opens a dialog to show
	 * history details of the system.
	 */
	@FXML
	private void handleHistory() {
		//String history = "History view!";
		InterfaceBack back = new InterfaceBack();
		System.out.println(back.historyGeneralLog());
		System.out.println(back.historySpecificLog());
		String history = back.historyGeneralLog() + "\n" + back.historySpecificLog();
		
		/*System.out.println("GeneralLog History");
		status=back.historyGeneralLog();
		System.out.println(status);
		System.out.println("SpecificLog History");
		status=back.historySpecificLog();*/
		mainApp.showStatus(history);
	}
	
	/**
	 * Called when the user clicks the request button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleRequest() {
	    boolean okClicked = mainApp.showRequestDialog();
	}
}