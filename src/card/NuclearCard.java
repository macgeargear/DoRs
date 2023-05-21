package card;

import buildings.Place;
import logic.GamePlay;
import logic.Player;
import material.Map;
import type.CardType;
import type.MaterialType;
import utils.Utilities;

public class NuclearCard extends EffectCard {

	public NuclearCard() {
		super(CardType.NUCLEAR);
	}

	@Override
	public boolean canPlay(Place place) {
//		check place is map and it active
		if (place == null || !(place instanceof Map) || !place.isActive()) {
			return false;
		}

//		check material
		Player currentPlayer = Utilities.getCurrentPlayer();
		if (currentPlayer.countMaterial(MaterialType.SAND) >= 1
				&& currentPlayer.countMaterial(MaterialType.GUNPOWDER) >= 2) {
			return true;
		}
		return false;
	}

	@Override
	public void play(Place place) {
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = gameInstance.getAllPlayers().get(gameInstance.getCurrentPlayer());
		currentPlayer.getMaterialPack(MaterialType.SAND).decrease(1);
		currentPlayer.getMaterialPack(MaterialType.GUNPOWDER).decrease(2);

		place.setActive(false);
		Utilities.getCurrentPlayer().removeEffect(this);
	}

}
