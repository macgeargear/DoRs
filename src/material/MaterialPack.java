package material;

import type.MaterialType;

public class MaterialPack {
	private Material type;
	private int amount;
	
	public MaterialPack(Material type) {
		this.type = type;
		this.setAmount(0);
	}
	public MaterialPack(Material type, int amount) {
		this.type = type;
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
	
	public Material getType() {
		return this.type;
	}
	
}
