package card;

import buildings.Building;
import buildings.Edge;
import buildings.Place;
import logic.GamePlay;
import logic.Player;
import material.Map;
import type.BuildingType;
import type.CardType;
import type.MaterialType;
import utils.Utilities;

public class StrongerCard extends EffectCard {

	public StrongerCard() {
		super(CardType.STRONGER);
	}

	@Override
	public boolean canPlay(Place place) {
//		if map can not use stronger
		if (place == null || place instanceof Map) {
			return false;
		}

//		if place is CITY or SUPERROAD can not use
		BuildingType type = ((Building) place).getType();
		if (type == BuildingType.CITY || type == BuildingType.SUPERROAD) {
			return false;
		}

//		check owner
		Player currentPlayer = Utilities.getCurrentPlayer();
		Building building = (Building) place;
		if (building.getOwner() != null && !building.getOwner().equals(currentPlayer)) {
			return false;
		}

		return true;
	}

	@Override
	public void play(Place place) {
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = gameInstance.getAllPlayers().get(gameInstance.getCurrentPlayer());
		currentPlayer.increaseMaterial(MaterialType.ROCK, 1);
		currentPlayer.increaseMaterial(MaterialType.WATER, 1);
		if (place instanceof Edge) {
			currentPlayer.increaseMaterial(MaterialType.SAND, 1);
		} else {
			currentPlayer.increaseMaterial(MaterialType.WOOD, 1);
		}
		((Building) place).upgrade();
		Utilities.getCurrentPlayer().removeEffect(this);
	}

}
