package utils;

import java.util.ArrayList;
import java.util.function.BiFunction;

import buildings.Building;
import buildings.Edge;
import buildings.Node;
import components.PlayerContainer;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import logic.GamePlay;
import logic.Player;
import pane.ControlPane;
import type.BuildingType;

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
		if (node == null) {
			return false;
		}
		Player currentPlayer = getCurrentPlayer();
		for (Edge edge : node.getSideEdges()) {
			if (edge != null && edge.getOwner() != null && edge.getOwner().equals(currentPlayer)) {
				return true;
			}
		}
		return false;
	}

	public static int buyNodeCondition(Node node) {
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = getCurrentPlayer();
		if ((currentPlayer.getNodeCount() < 1 && gameInstance.getCurrentRound() == -2)
				|| (currentPlayer.getNodeCount() < 2 && gameInstance.getCurrentRound() == -1)) {
			if (node.getOwner() == null) { // no owner
				return 1;
			}
			return 0;
		} else if (gameInstance.getCurrentRound() > 0) {
			if(node.getOwner() == null && node.canUpgrade()) {
				return 1;
			}else if(node.getOwner() != null && node.getOwner().equals(currentPlayer) && node.canUpgrade()) {
				return 2;
			}
		}
		return 0;
	}

	public static int buyEdgeCondition(Edge edge) {
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = getCurrentPlayer();
		if (!canCreateEdge(edge)) {
			return 0;
		} else if ((currentPlayer.getEdgeCount() < 2 && gameInstance.getCurrentRound() == -2)
				|| (currentPlayer.getEdgeCount() < 4 && gameInstance.getCurrentRound() == -1)) {
			if (edge.getOwner() == null) {
				return 1;
			}
			return 0;
		} else if (gameInstance.getCurrentRound() > 0) {
			if(edge.getOwner() == null && edge.canUpgrade()) {
				return 1;
			}else if(edge.getOwner() != null && edge.getOwner().equals(currentPlayer) && edge.canUpgrade()) {
				return 2;
			}
		}
		return 0;
	}

	public static boolean canCreateEdge(Edge edge) {
		Player currentPlayer = getCurrentPlayer();
		if ((edge.getStartNode().getOwner() != null && edge.getStartNode().getOwner().equals(currentPlayer))
				|| (edge.getEndNode().getOwner() != null && edge.getEndNode().getOwner().equals(currentPlayer))) {
			return true;
		} else if (haveSideEdge(edge.getStartNode()) || haveSideEdge(edge.getEndNode())) {
			return true;
		}
		return false;
	}
	
	public static boolean canEndTurn() {
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = getCurrentPlayer();
		int currentRound = gameInstance.getCurrentRound();
		
		if(currentRound == -2 && currentPlayer.getNodeCount() == 1 && currentPlayer.getEdgeCount() == 2) {
			return true;
		}else if(currentRound == -1 && currentPlayer.getNodeCount() == 2 && currentPlayer.getEdgeCount() == 4) {
			return true;
		}else if(currentRound > 0) {
			return true;
		}
		
		return false;
	}
	
	public static int countBuildingType(Player player, BuildingType type) {
		GamePlay gameInstance = GamePlay.getInstance();
		int count = 0;
		for(Building building : gameInstance.getAllNodes()) {
			if(building.getOwner() != null && building.getOwner().equals(player) && building.getType() == type) {
				count++;
			}
		}
		for(Building building : gameInstance.getAllEdges()) {
			if(building.getOwner() != null && building.getOwner().equals(player) && building.getType() == type) {
				count++;
			}
		}
		return count;
	}
	
	public static void updateCard() {
		for(PlayerContainer container: ControlPane.getInstance().getAllPlayerContainers()) {
			container.updateCount();
		}
		ControlPane.getInstance().getCardPopup().getShowMaterialCard().updateLabel();
	}
}
