package logic;

import java.util.ArrayList;

import card.EffectCard;
import material.MaterialPack;
import type.CardType;
import type.MaterialType;
import utils.Utilities;

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

		ArrayList<MaterialType> allMaterial = Utilities.getAllMaterials();
		for (MaterialType material : allMaterial) {
			allMaterials.add(new MaterialPack(material));
		}

	}

	public void addEffect(EffectCard card) {
		allEffectCards.add(card);
	}

	public void increaseMaterial(MaterialType material, int amount) {
		for (MaterialPack pack : allMaterials) {
			if (pack.getType() == material) {
				pack.increase(amount);
			}
		}
	}

	public void decreaseMaterial(MaterialType material, int amount) {
		for (MaterialPack pack : allMaterials) {
			if (pack.getType() == material) {
				pack.decrease(amount);
			}
		}
	}

	public void removeEffect(CardType type) {
		for(EffectCard card: allEffectCards) {
			if(card.getType() == type) {
				allEffectCards.remove(card);
				return ;
			}
		}
	}

	public int countMaterial(MaterialType material) {
		return this.getMaterialPack(material).getAmount();
	}

	public MaterialPack getMaterialPack(MaterialType material) {
		for (MaterialPack pack : this.allMaterials) {
			if (pack.getType() ==  material) {
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
