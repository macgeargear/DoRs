package card;

import java.util.ArrayList;

import buildings.Node;
import buildings.Place;
import entities.Entity;
import logic.GamePlay;
import logic.Player;
import material.Material;
import type.CardType;
import type.EntityType;
import type.MaterialType;

public class KnightCard extends EffectCard {

	public KnightCard() {
		super(CardType.KNIGHT);
	}

	@Override
	public boolean canPlay(Place place) {
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = gameInstance.getAllPlayers().get(gameInstance.getCurrentPlayer());
		if (place instanceof Node && ((Node) (place)).getOwner().equals(currentPlayer)
				&& currentPlayer.countMaterial(new Material(MaterialType.WOOD)) >= 1
				&& currentPlayer.countMaterial(new Material(MaterialType.ROCK)) >= 1
				&& currentPlayer.countMaterial(new Material(MaterialType.WATER)) >= 1) {
			return true;
		}
		return false;
	}

	@Override
	public void play(Place place) {
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = gameInstance.getAllPlayers().get(gameInstance.getCurrentPlayer());
		Entity newEntity = new Entity(EntityType.KNIGHT, currentPlayer, 3, new ArrayList<Material>());
		newEntity.setPosition((Node) place);
		gameInstance.addEntity(newEntity);

		currentPlayer.getMaterialPack(new Material(MaterialType.WOOD)).decrease(1);
		currentPlayer.getMaterialPack(new Material(MaterialType.ROCK)).decrease(1);
		currentPlayer.getMaterialPack(new Material(MaterialType.WATER)).decrease(1);
	}

}
