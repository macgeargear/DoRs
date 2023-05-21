package utils;

import java.util.ArrayList;
import java.util.Arrays;
import buildings.Building;
import buildings.Edge;
import buildings.Node;
import buildings.Place;
import card.EffectCard;
import components.player.PlayerContainer;
import config.Config;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import logic.GamePlay;
import logic.Player;
import pane.ControlPane;
import pane.popup.VideoPathPopup;
import type.BuildingType;
import type.CardType;
import type.MaterialType;

public class Utilities {
	public static final VideoPathPopup ATOMIC_VIDEO = new VideoPathPopup(Config.ATOMIC);
	public static final VideoPathPopup EXPLOSION_VIDEO = new VideoPathPopup(Config.EXPLOSION);

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
		if (!canCreateEdge(edge) && !(edge.getOwner() != null && edge.getOwner().equals(currentPlayer))) {
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
		paneInstance.getCardPopup().getShowEffectCard().updateLabel();
		paneInstance.getBuyCardPopup().updateAmount();
		paneInstance.getMarketPopup().updateExchange();

	}

	public static ArrayList<MaterialType> getAllMaterials() {
		ArrayList<MaterialType> allMaterial = new ArrayList<MaterialType>();
		allMaterial.addAll(Arrays.asList(MaterialType.values()));
		return allMaterial;
	}

	public static int countEffectCard(CardType type) {
		int cnt = 0;
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

	public static int dfs(boolean[] visited, ArrayList<Edge> allEdges, Edge edge, Node prevNode) {
		if (edge == null) {
			return 0;
		}

		int edgeIndex = allEdges.indexOf(edge);
//		check visit
		if (visited[edgeIndex]) {
			return 0;
		}

//		make it as visited
		visited[edgeIndex] = true;

//		find next edge that can go
		int maxDistance = 1;
		ArrayList<Node> nodes = new ArrayList<>(Arrays.asList(edge.getStartNode(), edge.getEndNode()));
		for (Node node : nodes) {
			if (node == null || (prevNode != null && node.equals(prevNode))) {
				continue;
			}

			for (Edge nextEdge : node.getSideEdges()) {
				if (nextEdge == null || visited[allEdges.indexOf(nextEdge)] || nextEdge.getOwner() == null
						|| !nextEdge.getOwner().equals(edge.getOwner())) {
					continue;
				}
				maxDistance = Math.max(maxDistance, dfs(visited, allEdges, nextEdge, node) + 1);
			}

		}

//		make it back
		visited[edgeIndex] = false;

		return maxDistance;
	}

	public static Player getLongestRoadPlayer() {
//		init
		GamePlay gameInstance = GamePlay.getInstance();
		ArrayList<Edge> allEdges = gameInstance.getAllEdges();

		int maxPath = 0, amountPlayer = 0, edgeAmount = allEdges.size();

		boolean[] visited = new boolean[edgeAmount];

//		process
		Player longestRoadPlayer = new Player("NONAME");
		for (Player player : gameInstance.getAllPlayers()) {
			int maxPathByPlayer = 0;
			for (Edge edge : gameInstance.getAllEdges()) {
				if (edge.getOwner() != null && edge.getOwner().equals(player)) {
					int maxPathByThisEdge = dfs(visited, allEdges, edge, null);
					maxPathByPlayer = Math.max(maxPathByThisEdge, maxPathByPlayer);
				}
			}
			if (maxPathByPlayer > maxPath) {
				maxPath = maxPathByPlayer;
				longestRoadPlayer = player;
				amountPlayer = 1;
			} else if (maxPathByPlayer == maxPath) {
				amountPlayer++;
			}
		}
		if (amountPlayer > 1) {
			return null;
		}
		return longestRoadPlayer;
	}

	public static Place getSelectPlace() {
		ControlPane paneInstance = ControlPane.getInstance();
		if (paneInstance.getSelectEdge() != null) {
			return paneInstance.getSelectEdge().getEdge();
		} else if (paneInstance.getSelectNode() != null) {
			return paneInstance.getSelectNode().getNode();
		} else if (paneInstance.getSelectMap() != null) {
			return paneInstance.getSelectMap().getMap();
		}
		return null;
	}
	
}
