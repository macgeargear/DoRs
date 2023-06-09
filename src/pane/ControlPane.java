package pane;

import config.Config;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.GamePlay;

public class ControlPane {
	
	private static ControlPane instance = null;
	private Scene gameScene;
	private Scene homeScene;
	private Stage stage;
	
	public ControlPane(Stage stage) {
		this.stage = stage;
		GamePlay.getInstance(4);

		homeScene = new Scene(new HomePane(), Config.SCREEN_WIDTH, Config.SCREEN_HEIGH);	
		gameScene = new Scene(new GamePane(), Config.SCREEN_WIDTH, Config.SCREEN_HEIGH);
//		this.showHomeScene();
		this.showGameScene();
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
		gameScene = new Scene(new GamePane(), Config.SCREEN_WIDTH, Config.SCREEN_HEIGH);
		stage.setScene(gameScene);
	}

	public Scene getGameScene() {
		return gameScene;
	}

	public Scene getHomeScene() {
		return homeScene;
	}
	
}
