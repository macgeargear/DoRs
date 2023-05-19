package card;

import buildings.Place;
import logic.GamePlay;
import logic.Player;
import material.Map;
import type.CardType;
import type.MaterialType;

public class NuclearCard extends EffectCard {

	public NuclearCard() {
		super(CardType.NUCLEAR);
	}

	@Override
	public boolean canPlay(Place place) {
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = gameInstance.getAllPlayers().get(gameInstance.getCurrentPlayer());
		if (place instanceof Map && place.isActive() == true
				&& currentPlayer.countMaterial(MaterialType.SAND) >= 3
				&& currentPlayer.countMaterial(MaterialType.GUNPOWDER) >= 5) {
			return true;
		}
		return false;
	}

	@Override
	public void play(Place place) {
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = gameInstance.getAllPlayers().get(gameInstance.getCurrentPlayer());
		currentPlayer.getMaterialPack(MaterialType.SAND).decrease(3);
		currentPlayer.getMaterialPack(MaterialType.GUNPOWDER).decrease(5);
		
		place.setActive(false);
	}

}
