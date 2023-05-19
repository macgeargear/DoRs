package logic;

import java.util.ArrayList;
import card.*;
import java.util.Arrays;

import card.EffectCard;
import material.Material;
import material.MaterialPack;
import type.CardType;
import type.MaterialType;

public class Player {
	private String name;
	private int nodeCount, edgeCount;
	private ArrayList<EffectCard> allEffectCards;
	private ArrayList<MaterialPack> allMaterials;
	
	public Player(String name) {
		this.name = name;
		this.nodeCount = 0;
		this.edgeCount = 0;
		this.allMaterials = new ArrayList<MaterialPack>();
		this.allEffectCards = new ArrayList<EffectCard>();
		
		Material wood = new Material(MaterialType.WOOD);
		Material water = new Material(MaterialType.WATER);
		Material rock = new Material(MaterialType.ROCK);
		Material sand = new Material(MaterialType.SAND);
		Material gunpowder = new Material(MaterialType.GUNPOWDER);
		
		EffectCard bombCard = new BombCard();
		EffectCard nuclearCard = new NuclearCard();
		EffectCard strongerCard = new StrongerCard();

		this.allEffectCards.addAll(Arrays.asList(bombCard, nuclearCard, strongerCard));
		
		ArrayList<Material> allMaterial = new ArrayList<Material>();
		allMaterial.addAll(Arrays.asList(wood, water, rock, sand, gunpowder));
		for (Material material : allMaterial) {
			allMaterials.add(new MaterialPack(material));
		}
		
		
	}
	
	public void addEffect(EffectCard card) {}
	
	public void increaseMaterial(MaterialType material, int amount) {
		for(MaterialPack pack: allMaterials) {
			if(pack.getType().getType().equals(material)) {
				pack.increase(amount);
			}
		}
	}
	
	public void decreaseMaterial(MaterialType material, int amount) {
		for(MaterialPack pack: allMaterials) {
			if(pack.getType().getType().equals(material)) {
				pack.decrease(amount);
			}
		}
	}
	
	public void removeEffect(EffectCard card) {}
	public void removeMaterial(Material material) {}
	public int countMaterial(Material material) {
		return this.getMaterialPack(material.getType()).getAmount();
	}

	public MaterialPack getMaterialPack(MaterialType material) {
		for(MaterialPack pack: this.allMaterials) {
			if(pack.getType().getType().equals(material)) {
				return pack;
			}
		}
		return new MaterialPack(null);
	}
	
	public int getMaterialCount() {
		int count = 0;
		for (MaterialPack pack : allMaterials) {
			count += pack.getAmount();
		}
		return count;
	}
	
	public void increaseNodeCount(int amount) {
		this.nodeCount += amount;
	}
	
	public void increaseEdgeCount(int amount) {
		this.edgeCount += amount;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<EffectCard> getAllEffectCards() {
		return allEffectCards;
	}

	public ArrayList<MaterialPack> getAllMaterials() {
		return allMaterials;
	}

	public int getNodeCount() {
		return nodeCount;
	}

	public int getEdgeCount() {
		return edgeCount;
	}

}
