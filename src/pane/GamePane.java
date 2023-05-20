package pane;

import java.util.ArrayList;

import components.Footer;
import components.GameBoard;
import components.HeaderGame;
import components.PlayerInfo;
import javafx.scene.layout.BorderPane;
import logic.GamePlay;
import logic.Player;

public class GamePane extends BorderPane {

	private HeaderGame header;

	public GamePane() {
		header = new HeaderGame();

		this.initGameInterface();

		setTop(header);
	}

	private void initGameInterface() {
		GameBoard gameBoard = new GameBoard();

		GamePlay gameInstance = GamePlay.getInstance();
		ArrayList<Player> allPlayer = gameInstance.getAllPlayers();
		PlayerInfo left = new PlayerInfo(true, allPlayer.get(0), allPlayer.size() >= 3 ? allPlayer.get(2) : null);
		PlayerInfo right = new PlayerInfo(false, allPlayer.get(1), allPlayer.size() >= 4 ? allPlayer.get(3) : null);

		setCenter(gameBoard);
		setLeft(left);
		setRight(right);
		setBottom(new Footer());
	}
}
