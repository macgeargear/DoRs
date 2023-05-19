package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import buildings.Building;
import buildings.Edge;
import buildings.Node;
import card.EffectCard;
import components.PlayerContainer;
import config.Config;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import logic.GamePlay;
import logic.Player;
import material.Material;
import pane.ControlPane;
import type.BuildingType;
import type.CardType;
import type.MaterialType;

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
			if (node.getOwner() == null && node.canUpgrade()) {
				return 1;
			} else if (node.getOwner() != null && node.getOwner().equals(currentPlayer) && node.canUpgrade()) {
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
			if (edge.getOwner() == null && edge.canUpgrade()) {
				return 1;
			} else if (edge.getOwner() != null && edge.getOwner().equals(currentPlayer) && edge.canUpgrade()) {
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

		if (currentRound == -2 && currentPlayer.getNodeCount() == 1 && currentPlayer.getEdgeCount() == 2) {
			return true;
		} else if (currentRound == -1 && currentPlayer.getNodeCount() == 2 && currentPlayer.getEdgeCount() == 4) {
			return true;
		} else if (currentRound > 0) {
			return true;
		}

		return false;
	}

	public static int countBuildingType(Player player, BuildingType type) {
		GamePlay gameInstance = GamePlay.getInstance();
		int count = 0, all = 0;
		for (Building building : gameInstance.getAllNodes()) {
			if (building.getOwner() != null && building.getOwner().equals(player) && building.getType() == type) {
				count++;
			}
			all++;
		}
		for (Building building : gameInstance.getAllEdges()) {
			if (building.getOwner() != null && building.getOwner().equals(player) && building.getType() == type) {
				count++;
			}
			all++;
		}
		System.out.println(all);
		return count;
	}

	public static void updateCard() {
		ControlPane paneInstance = ControlPane.getInstance();
		for (PlayerContainer container : paneInstance.getAllPlayerContainers()) {
			container.updateCount();
		}
		paneInstance.getCardPopup().getShowMaterialCard().updateLabel();
		paneInstance.getBuyCardPopup().updateAmount();
		paneInstance.getMarketPopup().updateExchange();
		
	}

	public static ArrayList<Material> getAllMaterials() {
		Material wood = new Material(MaterialType.WOOD);
		Material water = new Material(MaterialType.WATER);
		Material rock = new Material(MaterialType.ROCK);
		Material sand = new Material(MaterialType.SAND);
		Material gunpowder = new Material(MaterialType.GUNPOWDER);

		ArrayList<Material> allMaterial = new ArrayList<Material>();
		allMaterial.addAll(Arrays.asList(wood, water, rock, sand, gunpowder));

		return allMaterial;
	}
	
	public static int getEffectCard(CardType type) {
		int cnt=0;
		for (EffectCard card : Utilities.getCurrentPlayer().getAllEffectCards()) {
			if (card.getType() == type) {
				cnt++;
			}
		}
		return cnt;
	}
	
	

	public static Paint getColor(MaterialType type) {
		if (type == MaterialType.WOOD) {
			return Config.WoodColor;
		} else if (type == MaterialType.WATER) {
			return Config.WaterColor;
		} else if (type == MaterialType.ROCK) {
			return Config.RockColor;
		} else if (type == MaterialType.SAND) {
			return Config.SandColor;
		} else if (type == MaterialType.GUNPOWDER) {
			return Config.GunPowderColor;
		}
		return Color.BLACK;
	}
	

	public static int longestPathByEdge(Edge edge) {
		GamePlay gameInstance = GamePlay.getInstance();

//		BFS
//		init
		ArrayList<Edge> allEdges = gameInstance.getAllEdges();
		ArrayList<Boolean> visit = new ArrayList<Boolean>(
				Collections.nCopies(gameInstance.getAllEdges().size(), false));
		Queue<Edge> queue = new LinkedList<>();
		queue.offer(edge);
		int cnt = 0;

		while (!queue.isEmpty()) {
			cnt++;
			Queue<Edge> newQueue = new LinkedList<>();
			while (!queue.isEmpty()) {
				Edge currentEdge = queue.poll();
				int currentIndex = allEdges.indexOf(currentEdge);

				if (visit.get(currentIndex)) {
					continue;
				}
				visit.set(currentIndex, true);

				Node startNode = currentEdge.getStartNode();
				Node endNode = currentEdge.getEndNode();
				ArrayList<Node> nodes = new ArrayList<Node>(Arrays.asList(startNode, endNode));

				for (Node node : nodes) {
					if (node == null)
						continue;
					for (Edge nextEdge : node.getSideEdges()) {
						int nextEdgeIndex = allEdges.indexOf(nextEdge);
						if (!visit.get(nextEdgeIndex) && nextEdge.getOwner() != null
								&& nextEdge.getOwner().equals(currentEdge.getOwner())) {
							newQueue.offer(nextEdge);
						}
					}
				}
			}
			queue = newQueue;
		}

		return cnt;
	}

	public static Player getLongestRoadPlayer() {
		GamePlay gameInstance = GamePlay.getInstance();
		int maxPath = 0, amountPlayer = 0;
		Player longestRoadPlayer = new Player("NONAME");
		for (Player player : gameInstance.getAllPlayers()) {
			int maxPathByPlayer = 0;
			for (Edge edge : gameInstance.getAllEdges()) {
				if (edge.getOwner() != null && edge.getOwner().equals(player)) {
					int maxPathByThisEdge = longestPathByEdge(edge);
					maxPathByPlayer = Math.max(maxPathByThisEdge, maxPathByPlayer);
				}
			}
			if(maxPathByPlayer > maxPath) {
				maxPath = maxPathByPlayer;
				longestRoadPlayer = player;
				amountPlayer = 1;
			}else if(maxPathByPlayer == maxPath) {
				amountPlayer++;
			}
		}
		if(amountPlayer > 1) {
			return null;
		}
		return longestRoadPlayer;
	}
}
