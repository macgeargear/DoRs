package card;

import java.util.ArrayList;

import buildings.Building;
import buildings.Edge;
import buildings.Node;
import buildings.Place;
import entities.Entity;
import logic.GamePlay;
import logic.Player;
import material.Material;
import type.CardType;
import type.EntityType;
import type.MaterialType;

public class StrongerCard extends EffectCard {

	public StrongerCard() {
		super(CardType.STRONGER);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canPlay(Place place) {
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = gameInstance.getAllPlayers().get(gameInstance.getCurrentPlayer());
		if ((place instanceof Node || place instanceof Edge) && ((Building)(place)).getOwner().equals(currentPlayer)) {
			return true;
		}
		return false;
	}

	@Override
	public void play(Place place) {
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = gameInstance.getAllPlayers().get(gameInstance.getCurrentPlayer());
		currentPlayer.addMaterial(new Material(MaterialType.ROCK));
		currentPlayer.addMaterial(new Material(MaterialType.SAND));
		currentPlayer.addMaterial(new Material(MaterialType.WATER));
		((Building)place).upgrade();
	}


}
