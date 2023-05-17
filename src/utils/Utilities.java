package utils;

import java.util.function.BiFunction;

import buildings.Edge;
import buildings.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import logic.GamePlay;
import logic.Player;
import pane.ControlPane;

public class Utilities {
	public static void alertGenerate(Alert.AlertType alertType, String title, String content, Runnable methodYes) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(content);

		ButtonType buttonTypeYes = new ButtonType("Yes");
		ButtonType buttonTypeNo = new ButtonType("No");

		alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

		alert.showAndWait();

		if (alert.getResult().equals(buttonTypeYes)) {
			// User clicked Yes
			methodYes.run();
		}
	}
	
	public static void exitGame() {
		GamePlay.getInstance().getResult();
		ControlPane.getInstance().showHomeScene();
	}
	
	public static Player getCurrentPlayer() {
		GamePlay instance = GamePlay.getInstance();
		Player currentPlayer = instance.getAllPlayers().get(instance.getCurrentPlayer());
		return currentPlayer;
	}
	
	public static boolean haveSideEdge(Node node) {
		Player currentPlayer = getCurrentPlayer();
		for(Edge edge: node.getSideEdges()) {
			if(edge != null && edge.getOwner().equals(currentPlayer)) {
				return true;
			}
		}
		return false;
	}
}
