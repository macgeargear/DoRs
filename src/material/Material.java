package material;

import type.MaterialType;

public class Material {
	private MaterialType type;
	
	public Material(MaterialType type) {
		this.type = type;
	}
	
	public MaterialType getType() {
		return this.type;
	}
}
