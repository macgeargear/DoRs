package card;

import buildings.Place;
import type.CardType;

public class RobberCard extends EffectCard {
	
	public RobberCard() {
		super(CardType.ROBBER);
	}

	@Override
	public boolean canPlay(Place place) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void play(Place place) {
		// TODO Auto-generated method stub
		
	}

}
