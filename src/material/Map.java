package material;

import java.util.ArrayList;
import java.util.Random;

import buildings.Node;
import buildings.Place;
import type.MaterialType;

public class Map extends Place {
	private Material type;
	private ArrayList<Node> sideNodes;
	private int number;
	

	public Map(Material type) {
		Random random = new Random();
		this.type = type;
		this.setNumber(random.nextInt(6)+1);
		this.sideNodes = new ArrayList<Node>();
		for(int i=0;i<4;++i) {
			sideNodes.add(null);
		}
	}
	
	public void produce() {
		// TODO
		if (this.isActive()) {
			for (Node node : this.sideNodes) {
				if(node.getOwner() != null) {
					node.getOwner().increaseMaterial(this.type.getType(), 1);					
				}
			}
		}
	}
	
	public void setSideNode(int position, Node node) {
		// TODO
		this.sideNodes.set(position, node);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		if (number < 0 || number > 6) return;
		this.number = number;
	}

	public ArrayList<Node> getSideNodes() {
		return sideNodes;
	}

	public Material getType() {
		return type;
	}

}
