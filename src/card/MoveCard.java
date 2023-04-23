package card;

import buildings.Node;
import buildings.Place;
import entities.Entity;
import logic.GamePlay;
import logic.Player;
import material.Material;
import type.CardType;
import type.EntityType;
import type.MaterialType;

public class MoveCard extends EffectCard {

	public MoveCard() {
		super(CardType.MOVE);
	}

	@Override
	public boolean canPlay(Place place) {
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = gameInstance.getAllPlayers().get(gameInstance.getCurrentPlayer());
		if (place instanceof Node && ((Node) (place)).getOwner().equals(currentPlayer)) {
			return true;
		}
		return false;
	}

	@Override
	public void play(Place place) {
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = gameInstance.getAllPlayers().get(gameInstance.getCurrentPlayer());
		for(Entity target: gameInstance.getAllEntities()) {
			if(target.getOwner().equals(currentPlayer) && target.getType().equals(EntityType.PLAYER)) {
				target.setPosition((Node)(place));
			}
		}
	}

}
