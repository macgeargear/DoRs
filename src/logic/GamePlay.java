package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import buildings.Building;
import buildings.Edge;
import buildings.Node;
import card.EffectCard;
import entities.Entity;
import material.Map;
import material.Material;
import type.MaterialType;

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
		
		this.initMaps();
	}
	
	public GamePlay(int playerAmount) {
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
		
		for(int i=0;i<playerAmount;++i) {			
			allPlayers.add(new Player("P"+Integer.toString(i)));
		}
		
		this.initMaps();
	}
	
	private void initMaps() {
		
		
		Material wood = new Material(MaterialType.WOOD);
		Material water = new Material(MaterialType.WATER);
		Material rock = new Material(MaterialType.ROCK);
		Material sand = new Material(MaterialType.SAND);
		Material gunpowder = new Material(MaterialType.GUNPOWDER);
		
		ArrayList<Material> allMaterial = new ArrayList<Material>();
		allMaterial.addAll(Arrays.asList(wood, water, rock, sand, gunpowder));
		
		for (Material material : allMaterial) {
			for(int i=0;i<5;++i) {
				Map newMap = new Map(material);
				allMaps.add(new Map(material));
			}
		}
		
		Collections.shuffle(allMaps);
	}
	
	public void draw() {}
	
	public void goToNextPlayer() {
		
	}
	
	public void rollDice() {}
	
	public void trade(Player p1, Player p2) {}
	
	public void addEntity(Entity entity) {}
	
	public void addPlayer(Player player) {}
	
	public void removeEntity(Entity entity) {}
	
	public void bribe(Entity target) {}
	
	public boolean canBribe(Entity target) {
		return false;
	}
	
	public static GamePlay getInstance() {
		if(instance == null) {
			instance = new GamePlay();
		}
		return instance;
	}
	
	public static GamePlay getInstance(int roundAmount) {
		instance = new GamePlay(roundAmount);
		return instance;
	}
	
	public ArrayList<Player> getResult(){
		instance = null;
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
