package pane;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControlPane {
	
	private static ControlPane instance = null;
	private Scene gameScene;
	private Scene homeScene;
	private Stage stage;
	
	public ControlPane(Stage stage) {
		this.stage = stage;

		gameScene = new Scene(new GamePane(), 1000, 600);
		homeScene = new Scene(new HomePane(), 1000, 600);	
		this.showHomeScene();
//		this.showGameScene();
	}
	
	public static ControlPane getInstance() {
		return instance;
	}
	
	public static ControlPane getInstance(Stage stage) {
		if(instance == null) {
			instance = new ControlPane(stage);
		}
		return instance;
	}
	
	public void showHomeScene() {
		stage.setScene(homeScene);
	}
	
	public void showGameScene() {
		stage.setScene(gameScene);
	}
}
