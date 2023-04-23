package card;

import buildings.Place;
import type.CardType;

public abstract class EffectCard {
	private CardType type;
	
	public EffectCard(CardType type) {
		this.type = type;
	}
	
	public abstract boolean canPlay(Place place);
	public abstract void play(Place place);
}
