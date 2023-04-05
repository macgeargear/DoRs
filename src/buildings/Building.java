package buildings;

import java.util.ArrayList;

import type.BuildingType;

public abstract class Building {
	private BuildingType type;
	
	public Building(BuildingType type) {
		this.setType(type);
	}
	
	public ArrayList<Point>getPosition() {}
	
	
	public BuildingType getType() {
		return this.type;
	}
	
	public void setType(BuildingType type) {
		this.type = type;
	}
	
}
