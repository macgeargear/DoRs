package pane;

import java.util.ArrayList;

import components.HeaderGame;
import components.PlayerContainer;
import config.Config;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.GamePlay;

public class ControlPane {
	
	private static ControlPane instance = null;
	private ArrayList<PlayerContainer> allPlayerContainers;
	private HeaderGame gameHeader;
	private Scene gameScene;
	private Scene homeScene;
	private Scene marketScene;
	
	private Stage stage;
	
	
	public ControlPane(Stage stage) {
		if(instance == null) this.instance = this;
		this.stage = stage;
		this.gameHeader = new HeaderGame();
		this.allPlayerContainers = new ArrayList<PlayerContainer>();
		GamePlay.getInstance(4);

		homeScene = new Scene(new HomePane(), Config.SCREEN_WIDTH, Config.HOMEPANE_HEIGHT);	
		gameScene = new Scene(new GamePane(), Config.SCREEN_WIDTH, Config.SCREEN_HEIGH);
		marketScene = new Scene(new MarketPane(), Config.SCREEN_HEIGH , Config.SCREEN_HEIGH);
		// TODO: showMaterialCardScene, showMaterialCardScene;
//		this.showHomeScene();
//		this.showMarketScene();
		this.showGameScene();

	}
	
	public void addPlayerContainer(PlayerContainer container) {
		allPlayerContainers.add(container);
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
	
	public void backToGameScene() {
		stage.setScene(gameScene);
		stage.centerOnScreen();
	}
	
	public void showMarketScene() {
		marketScene = new Scene(new MarketPane(), Config.SCREEN_HEIGH, Config.SCREEN_HEIGH);
		stage.setScene(marketScene);
		stage.centerOnScreen();
	}
	
	public Scene getGameScene() {
		return gameScene;
	}

	public Scene getHomeScene() {
		return homeScene;
	}

	public ArrayList<PlayerContainer> getAllPlayerContainers() {
		return allPlayerContainers;
	}

	public HeaderGame getGameHeader() {
		return gameHeader;
	}

	public void setGameHeader(HeaderGame gameHeader) {
		this.gameHeader = gameHeader;
	}
	
	public Stage getStage() {
		return this.stage;
	}
}
