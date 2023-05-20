package card;

import buildings.Building;
import buildings.Edge;
import buildings.Node;
import buildings.Place;
import logic.GamePlay;
import logic.Player;
import type.CardType;
import type.MaterialType;
import utils.Utilities;

public class StrongerCard extends EffectCard {

	public StrongerCard() {
		super(CardType.STRONGER);
	}

	@Override
	public boolean canPlay(Place place) {
		Player currentPlayer = Utilities.getCurrentPlayer();
		if ((place instanceof Node || place instanceof Edge)
				&& (((Building) (place)).getOwner() == null || ((Building) (place)).getOwner().equals(currentPlayer))) {
			return true;
		}
		return false;
	}

	@Override
	public void play(Place place) {
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = gameInstance.getAllPlayers().get(gameInstance.getCurrentPlayer());
		currentPlayer.increaseMaterial(MaterialType.ROCK, 1);
		currentPlayer.increaseMaterial(MaterialType.WATER, 1);
		if(place instanceof Edge) {
			currentPlayer.increaseMaterial(MaterialType.SAND, 1);			
		}else {
			currentPlayer.increaseMaterial(MaterialType.WOOD, 1);
		}
		((Building) place).upgrade();
	}

}
