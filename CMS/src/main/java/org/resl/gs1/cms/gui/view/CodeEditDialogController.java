package org.resl.gs1.cms.gui.view;

import org.resl.gs1.cms.gui.model.GS1Code;
import org.resl.gs1.cms.interfaceback.InterfaceBack;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CodeEditDialogController {

    @FXML
    private TextField codeField;
    @FXML
    private TextField prefixField;
    @FXML
    private TextField referenceField;

    private Stage dialogStage;
    private GS1Code code;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setCode(GS1Code code) {
        this.code = code;

        codeField.setText(code.getCodeType());
        prefixField.setText(code.getPrefix());
        referenceField.setText(code.getReference());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	InterfaceBack interfaceBack=new InterfaceBack();
        	if (codeField.getText().trim().equals("GTIN")){
        		interfaceBack.add(Integer.parseInt(prefixField.getText()), Integer.parseInt(referenceField.getText()), 0, 0);
        	}else if (codeField.getText().trim().equals("GLN")){
        		interfaceBack.add(Integer.parseInt(prefixField.getText()), 0, Integer.parseInt(referenceField.getText()), 0);
        	}else if (codeField.getText().trim().equals("GSRN")){
        		interfaceBack.add(Integer.parseInt(prefixField.getText()), 0, 0, Integer.parseInt(referenceField.getText()));
        	}else{
        		System.out.println("Code type invalid (need to be GTIN, GLN, or GSRN");
        	}
        	
            code.setCodeType(codeField.getText());
            code.setPrefix(prefixField.getText());
            code.setReference(referenceField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (codeField.getText() == null || codeField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (prefixField.getText() == null || prefixField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (referenceField.getText() == null || referenceField.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
