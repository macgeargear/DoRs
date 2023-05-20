package application;

import javafx.application.Application;
import javafx.stage.Stage;
import pane.ControlPane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.*;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) { // Create a scene and place a button in the scene
		ControlPane instance = ControlPane.getInstance(primaryStage);

		primaryStage.setTitle("DoR");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
