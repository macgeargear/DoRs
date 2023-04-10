package material;

import java.util.ArrayList;

import buildings.Node;
import type.MaterialType;

public class Map extends Material {
	private Material type;
	private ArrayList<Node> sideNode;
	private int number;
	

	public Map(MaterialType type) {
		super(type);
		this.setNumber(0);
		this.sideNode = new ArrayList<Node>();
	}
	
	public void produce() {
		// TODO
	}
	
	public void setSideNode(int position, Node node) {
		// TODO
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
