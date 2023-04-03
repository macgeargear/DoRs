package items;

import type.MaterialType;

public class MaterialPack extends Material {
	private MaterialType type;
	private int amount;
	
	public MaterialPack(MaterialType type) {
		super(type);
	}
	public MaterialPack(MaterialType type, int amount) {
		super(type);
		this.setAmount(amount);
	}
	
	public void increase(int number) {
		this.setAmount(this.getAmount() + number);
	}
	
	public void decrease(int number) {
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
