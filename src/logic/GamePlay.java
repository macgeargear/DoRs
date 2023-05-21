package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import buildings.Edge;
import buildings.Node;
import card.BombCard;
import card.NuclearCard;
import card.StrongerCard;
import config.Config;
import javafx.util.Pair;
import material.Map;
import type.BuildingType;
import type.MaterialType;
import utils.Utilities;

public class GamePlay {
	public static GamePlay instance;
	private int currentRound;
	private int playerAmount;
	private int currentPlayer;
	private int rollNumber;
	private boolean isRoll;
	private Marketplace marketplace;
	private ArrayList<Player> allPlayers;
	private ArrayList<Node> allNodes;
	private ArrayList<Edge> allEdges;
	private ArrayList<Map> allMaps;

	public GamePlay() {
		this.playerAmount = Config.DEFAULT_PLAYER_AMOUNT;
		this.initGamePlay();
	}

	public GamePlay(int playerAmount) {
		this.playerAmount = playerAmount;
		this.initGamePlay();
	}

	private void initGamePlay() {
		this.isRoll = false;
		this.currentRound = Config.START_ROUND;
		this.currentPlayer = 0;
		this.rollNumber = 0;
		this.marketplace = new Marketplace();
		this.allPlayers = new ArrayList<Player>();
		this.allNodes = new ArrayList<Node>();
		this.allEdges = new ArrayList<Edge>();
		this.allMaps = new ArrayList<Map>();

		this.initPlayer();
		this.initMaps();
		this.initNodes();
	}

	private void initPlayer() {
		for (int i = 0; i < playerAmount; ++i) {
			allPlayers.add(new Player("P" + Integer.toString(i)));
		}
	}

	private void initMaps() {
		ArrayList<MaterialType> allMaterial = Utilities.getAllMaterials();

		for (MaterialType material : allMaterial) {
			for (int i = 0; i < 5; ++i) {
				Map newMap = new Map(material);
				allMaps.add(newMap);
			}
		}

		Collections.shuffle(allMaps);
	}

	private void initNodes() {
		for (int i = 0; i < (int) Math.pow(Config.SIDE_MAP_AMOUNT + 1, 2); ++i) {
			Node newNode = new Node(BuildingType.EMPTYHOUSE);
			allNodes.add(newNode);
		}

		for (int i = 0; i < Config.SIDE_MAP_AMOUNT; ++i) {
			for (int j = 0; j < Config.SIDE_MAP_AMOUNT; ++j) {
				int index = i * Config.SIDE_MAP_AMOUNT + j;
				Map map = allMaps.get(index);

				Node topLeftNode = allNodes.get(index + i),
						topRightNode = allNodes.get(index + i + Config.SIDE_MAP_AMOUNT + 1),
						botLeftNode = allNodes.get(index + i + 1),
						botRightNode = allNodes.get(index + i + Config.SIDE_MAP_AMOUNT + 2);

//				top left
				map.setSideNode(0, topLeftNode);
//				top right
				map.setSideNode(1, topRightNode);
//				bottom left
				map.setSideNode(2, botLeftNode);
//				bottom right
				map.setSideNode(3, botRightNode);

//				init edge
//				must have
				Edge topEdge = new Edge(BuildingType.EMPTYROAD, topLeftNode, topRightNode);
				allEdges.add(topEdge);
				topLeftNode.setSideEdge(1, topEdge);
				topRightNode.setSideEdge(3, topEdge);

				Edge leftEdge = new Edge(BuildingType.EMPTYROAD, topLeftNode, botLeftNode);
				allEdges.add(leftEdge);
				topLeftNode.setSideEdge(2, leftEdge);
				botLeftNode.setSideEdge(0, leftEdge);

//				some condition
				if (j == 4) {
					Edge botEdge = new Edge(BuildingType.EMPTYROAD, botLeftNode, botRightNode);
					allEdges.add(botEdge);
					botLeftNode.setSideEdge(1, botEdge);
					botRightNode.setSideEdge(3, botEdge);
				}
				if (i == 4) {
					Edge rightEdge = new Edge(BuildingType.EMPTYROAD, topRightNode, botRightNode);
					allEdges.add(rightEdge);
					topRightNode.setSideEdge(2, rightEdge);
					botRightNode.setSideEdge(0, rightEdge);
				}
			}
		}
	}

	public int findPlayerIndex(Player p) {
		return allPlayers.indexOf(p);
	}

	public void draw() {
		Random random = new Random();
		int target = random.nextInt(3);
		Player currentPlayer = Utilities.getCurrentPlayer();
		if (target == 0) {
			currentPlayer.addEffect(new BombCard());
		} else if (target == 1) {
			currentPlayer.addEffect(new NuclearCard());
		} else {
			currentPlayer.addEffect(new StrongerCard());
		}
	}

	public boolean goToNextPlayer() {
//		can not go to next player
		if (!isRoll && currentRound > 0)
			return false;

		if (currentRound > 0)
			isRoll = false;

		if (currentRound != Config.PREPARE_ROUND_2) {
//			prepare phase reverse case
			currentPlayer++;
		} else {
//			normal case
			currentPlayer--;
		}

//		end round condition
		if (currentPlayer == playerAmount && currentRound != Config.PREPARE_ROUND_1) {
//			normal round
			currentPlayer = 0;
			currentRound++;
		} else if (currentRound == Config.PREPARE_ROUND_1 && currentPlayer == playerAmount) {
//			prepare phase 1
			currentRound++;
			currentPlayer--;
		} else if (currentPlayer == -1 && currentRound == Config.PREPARE_ROUND_2) {
//			prepare phase 2
			currentPlayer++;
			currentRound = 1;
			isRoll = false;
		}
		return true;
	}

	public boolean rollDice() {
//		already roll the dice
		if (isRoll) {
			return false;
		}

		isRoll = true;
		Random random = new Random();
		rollNumber = random.nextInt(6) + 1;

		for (Map map : allMaps) {
			if (map.getNumber() == rollNumber) {
				map.produce();
			}
		}

		return true;
	}

	public static GamePlay getInstance() {
		if (instance == null) {
			instance = new GamePlay();
		}
		return instance;
	}

	public static GamePlay getInstance(int playerAmount) {
		instance = new GamePlay(playerAmount);
		return instance;
	}

	public ArrayList<Pair<String, Integer>> getResult() {
		ArrayList<Pair<String, Integer>> result = new ArrayList<Pair<String, Integer>>();
		ArrayList<BuildingType> allBuildingType = new ArrayList<BuildingType>();
		allBuildingType.addAll(Arrays.asList(BuildingType.HOUSE, BuildingType.TOWER, BuildingType.CITY,
				BuildingType.ROAD, BuildingType.SUPERROAD));
		Player longestRoadPlayer = Utilities.getLongestRoadPlayer();

		for (Player player : allPlayers) {
			int score = 0;
//			House and Road score SCORE_1
//			Tower and Super road SCORE_2
//			City score SCORE_3
//			have longest road score SCORE_LONGEST_PATH (if have only 1 player)
			for (BuildingType type : allBuildingType) {
				int typeCount = Utilities.countBuildingType(player, type);
				if (type == BuildingType.TOWER || type == BuildingType.SUPERROAD) {
					score += typeCount * Config.SCORE_2;
				} else if (type == BuildingType.CITY) {
					score += typeCount * Config.SCORE_3;
				} else {
					score += typeCount * Config.SCORE_1;
				}
			}
			if (longestRoadPlayer != null && longestRoadPlayer.equals(player)) {
				score += Config.SCORE_LONGEST_PATH;
			}
			result.add(new Pair<String, Integer>(player.getName(), -score));
		}

		Collections.sort(result, Comparator.comparingInt(Pair::getValue));
//		end this game
		instance = null;
		return result;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public int getRollNumber() {
		return rollNumber;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public Marketplace getMarketplace() {
		return marketplace;
	}

	public ArrayList<Player> getAllPlayers() {
		return allPlayers;
	}

	public ArrayList<Node> getAllNodes() {
		return allNodes;
	}

	public ArrayList<Edge> getAllEdges() {
		return allEdges;
	}

	public ArrayList<Map> getAllMaps() {
		return allMaps;
	}

}
