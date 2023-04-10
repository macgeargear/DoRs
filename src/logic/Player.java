package logic;

import java.util.ArrayList;

import buildings.Building;
import card.EffectCard;
import entities.Entity;
import material.Material;
import material.MaterialPack;

public class Player {
	private String name;
	private ArrayList<Entity> allEntities;
	private ArrayList<EffectCard> allEffectCards;
	private ArrayList<MaterialPack> allMaterials;
	private ArrayList<Building> allBuilding;
	
	public Player(String name) {}
	
	public void addBuilding(Building newBuilding) {}
	public void addEffect(EffectCard card) {}
	public void addEntity(Entity newEntity) {}
	public void removeEntity(Entity entity) {}
	public boolean canDraw() {return true;}
	public int countMaterial(Material material) {return 0;}

	public String getName() {
		return name;
	}

	public ArrayList<Entity> getAllEntities() {
		return allEntities;
	}

	public ArrayList<EffectCard> getAllEffectCards() {
		return allEffectCards;
	}

	public ArrayList<MaterialPack> getAllMaterials() {
		return allMaterials;
	}

	public ArrayList<Building> getAllBuilding() {
		return allBuilding;
	}

	
}
