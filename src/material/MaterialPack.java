package material;

import type.MaterialType;

public class MaterialPack {
	private MaterialType type;
	private int amount;

	public MaterialPack(MaterialType type) {
		this.type = type;
		this.setAmount(0);
	}

	public MaterialPack(MaterialType type, int amount) {
		this.type = type;
		this.setAmount(amount);
	}

	public void increase(int number) {
		this.setAmount(this.getAmount() + number);
	}

	public void decrease(int number) {
		if (this.getAmount() - number < 0)
			return;
		this.setAmount(this.getAmount() - number);
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return this.amount;
	}

	public MaterialType getType() {
		return this.type;
	}

}
