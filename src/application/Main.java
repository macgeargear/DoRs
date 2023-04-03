package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
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

	// Override the start method in the Application class
	@Override public void start(Stage primaryStage) { // Create a scene and place a button in the scene
		Group root = new Group();
		Scene scene = new Scene(root, 600, 600, Color.BEIGE);

		Stage stage = new Stage();
		stage.setResizable(false);
		
		Text text=  new Text();
		text.setText("Test Game Krub Eiei");
		text.setX(50);
		text.setY(50);
		text.setFont(Font.font("JetBrains Mono NL", 50));
		text.setFill(Color.DARKSLATEGREY);
		
		Line line = new Line();
		line.setStartX(200);
		line.setStartY(200);
		line.setEndX(500);
		line.setEndY(200);
		line.setStrokeWidth(5);
		line.setStroke(Color.RED);
		line.setOpacity(0.5);
		line.setRotate(45);
		
		Rectangle rectangle = new Rectangle();
		rectangle.setX(100);
		rectangle.setY(100);
		rectangle.setWidth(100);
		rectangle.setHeight(100);
		rectangle.setFill(Color.BLUE);
		rectangle.setStrokeWidth(5);
		rectangle.setStroke(Color.BLACK);
		
		Polygon tri = new Polygon();
		tri.getPoints().setAll(
				200.0, 200.0,
				300.0, 300.0,
				200.0, 300.0
				);
		tri.setFill(Color.YELLOW);

		Circle c = new Circle();
		c.setCenterX(350);
		c.setCenterY(350);
		c.setRadius(50);
		c.setFill(Color.ORANGE);
		
		/** @ADDIMAGE
		Image image=  new Image('pizza.png');
		ImageView imageView = new ImageView(image);
		imageView.setX(400);
		imageView.setY(400);
		*/

		// add elements to root node
		root.getChildren().add(text);
		root.getChildren().add(line);
		root.getChildren().add(rectangle);
		root.getChildren().add(tri);
		root.getChildren().add(c);
		


		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

