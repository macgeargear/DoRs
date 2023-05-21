package material;

import type.MaterialType;

public class MaterialPack {
	private MaterialType type;
	private int amount;

	public MaterialPack(MaterialType type) {
		this.type = type;
		this.amount = 0;
	}

	public MaterialPack(MaterialType type, int amount) {
		this.type = type;
		this.amount = amount;
	}

	public void increase(int number) {
		if (number < 0) {
			return;
		}
		amount += number;
	}

	public void decrease(int number) {
		if (this.getAmount() - number < 0) {
			return;			
		}
		amount -= number;
	}

	public int getAmount() {
		return this.amount;
	}

	public MaterialType getType() {
		return this.type;
	}

}
