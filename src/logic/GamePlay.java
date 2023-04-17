package logic;

import java.util.ArrayList;


import buildings.Building;
import buildings.Edge;
import buildings.Node;
import card.EffectCard;
import entities.Entity;
import material.Map;

public class GamePlay {
	public static GamePlay instance;
	private int roundAmount;
	private int currentRound;
	private int currentPlayer;
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
	}
	
	public GamePlay(int roundAmount) {
		this.roundAmount = roundAmount;
		this.currentRound = 0;
		this.currentPlayer = 0;
		this.marketplace = new Marketplace();
		this.allPlayers = new ArrayList<Player>();
		this.allNodes = new ArrayList<Node>();
		this.allEdges = new ArrayList<Edge>();
		this.allMaps = new ArrayList<Map>();
		this.cardInDeck = new ArrayList<EffectCard>();
		this.allEntities = new ArrayList<Entity>();
	}
	
	public void draw() {}
	
	public void goToNextPlayer() {}
	
	public void rollDice() {}
	
	public void trade(Player p1, Player p2) {}
	
	public void addEntity(Entity entity) {}
	
	public void addPlayer(Player player) {}
	
	public void removeEntity(Entity entity) {}
	
	public void bribe(Entity target) {}
	
	public boolean canBribe(Entity target) {
		return false;
	}
	
	public GamePlay getInstance() {
		if(this.instance == null) {
			this.instance = new GamePlay();
		}
		return this.instance;
	}
	
	public GamePlay getInstance(int roundAmount) {
		if(this.instance == null) {
			this.instance = new GamePlay(roundAmount);
		}
		return this.instance;
	}
	
	public ArrayList<Player> getResult(){
		return new ArrayList<Player>();
	}

	public int getRoundAmount() {
		return roundAmount;
	}

	public int getCurrentRound() {
		return currentRound;
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
