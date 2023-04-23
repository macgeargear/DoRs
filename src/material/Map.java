package material;

import java.util.ArrayList;

import buildings.Node;
import buildings.Place;
import type.MaterialType;

public class Map extends Place {
	private Material type;
	private ArrayList<Node> sideNode;
	private int number;
	

	public Map(Material type) {
		this.type = type;
		this.setNumber(0);
		this.sideNode = new ArrayList<Node>();
	}
	
	public void produce() {
		// TODO
		if (this.isActive()) {
			for (Node node : this.sideNode) {
				node.getOwner().addMaterial(this.type);
			}
		}
	}
	
	public void setSideNode(int position, Node node) {
		// TODO
		this.sideNode.set(position, node);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		if (number < 0 || number > 6) return;
		this.number = number;
	}

	public ArrayList<Node> getSideNode() {
		return sideNode;
	}

}
