package org.resl.gs1.cms.gui.view;

import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HistoryEditDialogController extends Application {

	public static void main(String[] args) throws Exception { 
		launch(args); 
	}

	@Override 
	public void start(final Stage stage) throws Exception {
		Label label = new Label(WORDS);
		label.setWrapText(true);
		label.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 20; -fx-text-fill: black;");

		StackPane layout = new StackPane();
		layout.setStyle("-fx-background-color: white; -fx-padding: 10;");
		layout.getChildren().setAll(label);

		stage.setTitle("Love Me Not");
		stage.setScene(new Scene(layout));
		stage.show();
	}

	// creates a triangle.
	private static final String WORDS = 
			"Love not me for comely grace,\n" +
					"For my pleasing eye or face,\n" +
					"Nor for any outward part,\n" +
					"No, nor for my constant heart,\n" +
					"For these may fail, or turn to ill.\n" +
					"So thou and I must sever.\n" +
					"Keep therefore a true woman’s eye,\n" +
					"And love me still, but know not why,\n" +
					"So hast thou the same reason still\n" +
					"To doat upon me ever.";
}