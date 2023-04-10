package buildings;


import logic.Player;
import type.BuildingType;


public abstract class Building {
	private BuildingType type;
	private Player owner;
	
	public Building(BuildingType type) {
		this.type = type;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public BuildingType getType() {
		return type;
	}
	
	public void setType(BuildingType type) {
		this.type = type;
	}
	
	
}
