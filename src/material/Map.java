package material;

import java.util.ArrayList;
import java.util.Random;

import buildings.Node;
import buildings.Place;
import type.MaterialType;

public class Map extends Place {
	private MaterialType type;
	private ArrayList<Node> sideNodes;
	private int number;

	public Map(MaterialType type) {
		Random random = new Random();
		this.type = type;
		this.number = random.nextInt(6) + 1;
		this.sideNodes = new ArrayList<Node>();
		for (int i = 0; i < 4; ++i) {
			sideNodes.add(null);
		}
	}

	public void produce() {
		if (this.isActive()) {
			for (Node node : this.sideNodes) {
				if (node.getOwner() != null) {
					node.getOwner().increaseMaterial(this.type, 1);
				}
			}
		}
	}

	public void setSideNode(int position, Node node) {
		this.sideNodes.set(position, node);
	}

	public int getNumber() {
		return number;
	}

	public ArrayList<Node> getSideNodes() {
		return sideNodes;
	}

	public MaterialType getType() {
		return type;
	}

}
