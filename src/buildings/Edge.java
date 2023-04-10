package buildings;


import utils.getTotalOwners;

import type.BuildingType;

public class Edge extends Building implements Upgradeable, Destroyable {
	private Node startNode;
	private Node endNode;

	public Edge(BuildingType type, Node startNode, Node endNode) {
		super(type);
		this.startNode = startNode;
		this.endNode = endNode;
	}

	public void destroy() {
		if (!this.getType().equals(BuildingType.SUPERROAD)) {
			this.setType(BuildingType.EMPTYROAD);
			this.setOwner(null);
		}
	}

	public void upgrade() {
		// TODO 
	}

	public boolean canDestroy() {
		if (this.getType().equals(BuildingType.EMPTYROAD) || this.getType().equals(BuildingType.SUPERROAD)) {
			return false;
		}
		return true;
	}
	
	public boolean canUpgrade() {
		// do something
	}

	public Node getEndNode() {
		return this.endNode;
	}

	public Node getStartNode() {
		return this.startNode;
	}

}
