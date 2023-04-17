package logic;

import java.util.ArrayList;

import buildings.Building;
import card.EffectCard;
import entities.Entity;
import material.Material;
import material.MaterialPack;

public class Player {
	private String name;
	private ArrayList<EffectCard> allEffectCards;
	private ArrayList<MaterialPack> allMaterials;
	
	public Player(String name) {}
	
	public void addEffect(EffectCard card) {}
	public void addMaterial(Material material) {}
	public void removeEffect(EffectCard card) {}
	public void removeMaterial(Material material) {}
	public int countMaterial(Material material) {return 0;}

	public String getName() {
		return name;
	}

	public ArrayList<EffectCard> getAllEffectCards() {
		return allEffectCards;
	}

	public ArrayList<MaterialPack> getAllMaterials() {
		return allMaterials;
	}

	
}
