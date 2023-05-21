package application;

import javafx.application.Application;
import javafx.stage.Stage;
import pane.ControlPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) { // Create a scene and place a button in the scene
		new ControlPane(primaryStage);
		primaryStage.setTitle("DoR");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
