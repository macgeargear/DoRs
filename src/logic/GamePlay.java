package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import buildings.Edge;
import buildings.Node;
import card.BombCard;
import card.EffectCard;
import card.NuclearCard;
import card.StrongerCard;
import entities.Entity;
import javafx.util.Pair;
import material.Map;
import material.Material;
import type.BuildingType;
import type.MaterialType;
import utils.Utilities;

public class GamePlay {
	public static GamePlay instance;
	private int roundAmount;
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
	private ArrayList<EffectCard> cardInDeck;
	private ArrayList<Entity> allEntities;

	public GamePlay() {
		this.roundAmount = 10;
		this.currentRound = 0;
		this.currentPlayer = 0;
		this.marketplace = new Marketplace();
		this.allPlayers = new ArrayList<Player>();
		this.allNodes = new ArrayList<Node>();
		this.allEdges = new ArrayList<Edge>();
		this.allMaps = new ArrayList<Map>();
		this.cardInDeck = new ArrayList<EffectCard>();
		this.allEntities = new ArrayList<Entity>();

		this.initMaps();
	}

	public GamePlay(int playerAmount) {
		this.isRoll = false;
		this.roundAmount = 10;
		this.currentRound = -2;
		this.currentPlayer = 0;
		this.playerAmount = playerAmount;
		this.rollNumber = 0;
		this.marketplace = new Marketplace();
		this.allPlayers = new ArrayList<Player>();
		this.allNodes = new ArrayList<Node>();
		this.allEdges = new ArrayList<Edge>();
		this.allMaps = new ArrayList<Map>();
		this.cardInDeck = new ArrayList<EffectCard>();
		this.allEntities = new ArrayList<Entity>();

		for (int i = 0; i < playerAmount; ++i) {
			allPlayers.add(new Player("P" + Integer.toString(i)));
		}

		this.initMaps();
		this.initNodes();
	}

	private void initMaps() {
		Material wood = new Material(MaterialType.WOOD);
		Material water = new Material(MaterialType.WATER);
		Material rock = new Material(MaterialType.ROCK);
		Material sand = new Material(MaterialType.SAND);
		Material gunpowder = new Material(MaterialType.GUNPOWDER);

//		ArrayList<Material> allMaterial = new ArrayList<Material>();
//		allMaterial.addAll(Arrays.asList(wood, water, rock, sand, gunpowder));
		ArrayList<Material> allMaterial = Utilities.getAllMaterials();
		
		for (Material material : allMaterial) {
			for (int i = 0; i < 5; ++i) {
				Map newMap = new Map(material);
				allMaps.add(newMap);
			}
		}

		Collections.shuffle(allMaps);
	}

	private void initNodes() {
		for (int i = 0; i < 36; ++i) {
			Node newNode = new Node(BuildingType.EMPTYHOUSE);
			allNodes.add(newNode);
		}

		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				int index = i * 5 + j;
				Map map = allMaps.get(index);

				Node topLeftNode = allNodes.get(index + i), topRightNode = allNodes.get(index + i + 6),
						botLeftNode = allNodes.get(index + i + 1), botRightNode = allNodes.get(index + i + 7);

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
		if(target == 0) {
			currentPlayer.addEffect(new BombCard());
		}else if(target == 1) {
			currentPlayer.addEffect(new NuclearCard());
		}else {
			currentPlayer.addEffect(new StrongerCard());
		}
	}

	public boolean goToNextPlayer() {
		if (!isRoll && currentRound > 0)
			return false;
		if (currentRound > 0)
			isRoll = false;
		if (currentRound != -1)
			currentPlayer++;
		else
			currentPlayer--;
		if (currentPlayer == playerAmount && currentRound != -2) {
			currentPlayer = 0;
			currentRound++;
		} else if (currentRound == -2 && currentPlayer == playerAmount) {
			currentRound++;
			currentPlayer--;

//			currentRound = 1;
//			currentPlayer = 0;
		} else if (currentPlayer == -1 && currentRound == -1) {
			currentPlayer++;
			currentRound = 1;
			isRoll = false;
		}
		return true;
	}

	public boolean rollDice() {
		if (isRoll)
			return false;
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

	public void trade(Player p1, Player p2) {
	}

	public void addEntity(Entity entity) {
	}

	public void addPlayer(Player player) {
	}

	public void removeEntity(Entity entity) {
	}

	public void bribe(Entity target) {
	}

	public boolean canBribe(Entity target) {
		return false;
	}

	public static GamePlay getInstance() {
		if (instance == null) {
			instance = new GamePlay();
		}
		return instance;
	}

	public static GamePlay getInstance(int roundAmount) {
		instance = new GamePlay(roundAmount);
		return instance;
	}

	public ArrayList<Pair<String, Integer>> getResult() {
		ArrayList<Pair<String, Integer>> result = new ArrayList<Pair<String, Integer>>();
		ArrayList<BuildingType> allBuildingType = new ArrayList<BuildingType>();
		allBuildingType.addAll(Arrays.asList(BuildingType.HOUSE, BuildingType.TOWER, BuildingType.CITY, BuildingType.ROAD, BuildingType.SUPERROAD));
		Player longestRoadPlayer = Utilities.getLongestRoadPlayer();
		
		for (Player player : allPlayers) {
			int score = 0;
			for(BuildingType type: allBuildingType) {
				int typeCount = Utilities.countBuildingType(player, type);
				if(type == BuildingType.TOWER || type == BuildingType.SUPERROAD) {
					score += typeCount*3;
				}else if(type == BuildingType.CITY) {
					score += typeCount*5;
				}else {
					score += typeCount;
				}
				System.out.println(typeCount);
			}
			if(longestRoadPlayer != null && longestRoadPlayer.equals(player)) {
				score += 7;
			}
//			System.out.println(score);
			result.add(new Pair<String, Integer>(player.getName(), -score));
		}
		
		Collections.sort(result, Comparator.comparingInt(Pair::getValue));
		instance = null;
		return result;
	}

	public int getRoundAmount() {
		return roundAmount;
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

	public ArrayList<Entity> getAllEntities() {
		return allEntities;
	}

}
