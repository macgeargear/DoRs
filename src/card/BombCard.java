package card;

import buildings.Building;
import buildings.Place;
import logic.Player;
import material.Map;
import type.BuildingType;
import type.CardType;
import type.MaterialType;
import utils.Utilities;

public class BombCard extends EffectCard {
	public BombCard() {
		super(CardType.BOMB);
	}

	@Override
	public boolean canPlay(Place place) {
//		if map can not use bomb
		if (place == null || place instanceof Map) {
			return false;
		}

//		if place is CITY, SUPERROAD or EMPTY can not use bomb
		BuildingType type = ((Building) place).getType();
		if (type == BuildingType.CITY || type == BuildingType.SUPERROAD || type == BuildingType.EMPTYHOUSE
				|| type == BuildingType.EMPTYROAD) {
			return false;
		}

//		check material
		Player currentPlayer = Utilities.getCurrentPlayer();
		if (currentPlayer.countMaterial(MaterialType.SAND) >= 1
				&& currentPlayer.countMaterial(MaterialType.GUNPOWDER) >= 1) {
			return true;
		}
		return false;
	}

	@Override
	public void play(Place place) {
		((Building) place).destroy();
		Player currentPlayer = Utilities.getCurrentPlayer();
		currentPlayer.getMaterialPack(MaterialType.SAND).decrease(1);
		currentPlayer.getMaterialPack(MaterialType.GUNPOWDER).decrease(2);
		place.setActive(false);
		Utilities.getCurrentPlayer().removeEffect(this);
	}

}
