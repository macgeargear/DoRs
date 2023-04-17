package logic;

import java.util.ArrayList;


import buildings.Building;
import card.EffectCard;
import material.Map;

public class GamePlay {
	public static GamePlay instance;
	private ArrayList<Player> allPlayers;
	private ArrayList<Building> allBuilding;
	private ArrayList<Map> allMaps;
	private ArrayList<EffectCard> cardInDeck;
	private int currentPlayer;
	
	GamePlay() {}
	
	public void drawCard() {}
	public void goToNextPlayer() {}
	public int rollDice() {
		return 1 + (int)(Math.random() * 6);
	}
	public void trade(Player p1, Player p2) {}
	public void addBuilding(Building newBuilding) {}
	public void addPlayer(Player player) {}
	
	public static GamePlay getInstance() {}
	public ArrayList<Player> getResult() {}
	public int getCurrentPlayer() {}
	public ArrayList<Player> getAllPlayer() {}
	public ArrayList<Building> getAllBuilding() {}
	public ArrayList<Map> getAllMap() {}
	
}
