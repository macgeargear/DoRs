package card;

import buildings.Building;
import buildings.Edge;
import buildings.Node;
import buildings.Place;
import logic.GamePlay;
import logic.Player;
import material.Material;
import type.BuildingType;
import type.CardType;
import type.MaterialType;

public class BombCard extends EffectCard {
	public BombCard() {
		super(CardType.BOMB);
	}

	@Override
	public boolean canPlay(Place place) {
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = gameInstance.getAllPlayers().get(gameInstance.getCurrentPlayer());
		if ((place instanceof Node || place instanceof Edge) && ((Building) (place)).getOwner().equals(currentPlayer)
				&& currentPlayer.countMaterial(new Material(MaterialType.SAND)) >= 1
				&& currentPlayer.countMaterial(new Material(MaterialType.GUNPOWDER)) >= 2
				&& !(((Building)place).getType().equals(BuildingType.EMPTYHOUSE) || ((Building)place).getType().equals(BuildingType.EMPTYROAD))) {
			return true;
		}
		return false;
	}

	@Override
	public void play(Place place) {
		((Building)place).destroy();
		
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = gameInstance.getAllPlayers().get(gameInstance.getCurrentPlayer());
		currentPlayer.getMaterialPack(new Material(MaterialType.SAND)).decrease(1);
		currentPlayer.getMaterialPack(new Material(MaterialType.GUNPOWDER)).decrease(2);
	}

}
