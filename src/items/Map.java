package items;

import type.MaterialType;

public class Map extends Material{
	private boolean isActive;

	public Map(MaterialType type) {
		super(type);
	}
	
	public void changeStatus() {}
	public Point getPosition() {}
	
	public boolean getisActive() {
		return this.isActive;
	}

}
