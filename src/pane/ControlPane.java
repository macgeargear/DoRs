package pane;

import java.util.ArrayList;

import components.Button.*;
import components.Footer;
import components.HeaderGame;
import components.PlayerContainer;
import config.Config;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.GamePlay;
import pane.popup.CardPopup;
import pane.popup.BuyCardPopup;
import pane.popup.MarketPopup;

public class ControlPane {

	private static ControlPane instance = null;
	private ArrayList<PlayerContainer> allPlayerContainers;
	private HeaderGame gameHeader;
	private Footer footer;
	private NodeButton selectNode;
	private EdgeButton selectEdge;
	private MapButton selectMap;
	private Scene gameScene;
	private Scene homeScene;
	private CardPopup cardPopup;
	private MarketPopup marketPopup;
	private BuyCardPopup buyCardPopup;

	private Stage stage;

	public ControlPane(Stage stage) {
		if (instance == null)
			this.instance = this;
		this.selectEdge = null;
		this.selectNode = null;
		this.selectMap = null;
		this.stage = stage;
		this.gameHeader = new HeaderGame();
		this.allPlayerContainers = new ArrayList<PlayerContainer>();

		GamePlay.getInstance(2);

		homeScene = new Scene(new HomePane(), Config.SCREEN_WIDTH, Config.HOMEPANE_HEIGHT);
		gameScene = new Scene(new GamePane(), Config.SCREEN_WIDTH, Config.SCREEN_HEIGH);

		this.showHomeScene();
	}

	public void addPlayerContainer(PlayerContainer container) {
		allPlayerContainers.add(container);
	}

	public static ControlPane getInstance() {
		return instance;
	}

	public static ControlPane getInstance(Stage stage) {
		if (instance == null) {
			instance = new ControlPane(stage);
		}
		return instance;
	}

	public void showHomeScene() {
		stage.setScene(homeScene);
	}

	public void showGameScene() {
		GamePlay.getInstance(2);
		gameScene = new Scene(new GamePane(), Config.SCREEN_WIDTH, Config.SCREEN_HEIGH);
		stage.setScene(gameScene);
	}

	public void backToGameScene() {
		stage.setScene(gameScene);
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

	public Footer getFooter() {
		return footer;
	}

	public void setFooter(Footer footer) {
		this.footer = footer;
	}

	public NodeButton getSelectNode() {
		return selectNode;
	}

	public void setSelectNode(NodeButton selectNode) {
		this.selectNode = selectNode;
	}

	public EdgeButton getSelectEdge() {
		return selectEdge;
	}

	public void setSelectEdge(EdgeButton selectEdge) {
		this.selectEdge = selectEdge;
	}

	public MapButton getSelectMap() {
		return selectMap;
	}

	public void setSelectMap(MapButton selectMap) {
		this.selectMap = selectMap;
	}

	public CardPopup getCardPopup() {
		return cardPopup;
	}

	public void setCardPopup(CardPopup cardPopup) {
		this.cardPopup = cardPopup;
	}

	public void resetSelect() {
		if (selectEdge != null) {
			selectEdge.resetSize();
			selectEdge = null;
		}
		if (selectNode != null) {
			selectNode.resetSize();
			selectNode = null;
		}
		if (selectMap != null) {
			selectMap.resetSize();
			selectMap = null;
		}

		footer.setBuyNodeDisable(true);
		footer.setBuyEdgeDisable(true);
	}

	public MarketPopup getMarketPopup() {
		return marketPopup;
	}

	public void setMarketPopup(MarketPopup marketPopup) {
		this.marketPopup = marketPopup;
	}

	public BuyCardPopup getBuyCardPopup() {
		return buyCardPopup;
	}

	public void setBuyCardPopup(BuyCardPopup buyCardPopup) {
		this.buyCardPopup = buyCardPopup;
	}

}
