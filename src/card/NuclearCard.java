package card;

import buildings.Building;
import buildings.Edge;
import buildings.Node;
import buildings.Place;
import logic.GamePlay;
import logic.Player;
import material.Map;
import material.Material;
import type.BuildingType;
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
				&& currentPlayer.countMaterial(new Material(MaterialType.SAND)) >= 3
				&& currentPlayer.countMaterial(new Material(MaterialType.GUNPOWDER)) >= 5) {
			return true;
		}
		return false;
	}

	@Override
	public void play(Place place) {
		place.handleIsActive();
	}

}
