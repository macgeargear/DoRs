package card;

import type.CardType;

public abstract class EffectCard {
	private CardType type;
	
	public EffectCard(CardType type) {
		this.type = type;
	}
	
	public abstract boolean canPlay();
	public abstract void play();
}
