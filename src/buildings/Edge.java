package buildings;

import utils.Utilities;
import logic.GamePlay;
import logic.Player;
import type.BuildingType;
import type.MaterialType;

public class Edge extends Building {
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
		Player currentPlayer = Utilities.getCurrentPlayer();

		if (GamePlay.getInstance().getCurrentRound() > 0) {
			currentPlayer.decreaseMaterial(MaterialType.ROCK, 1);
			currentPlayer.decreaseMaterial(MaterialType.SAND, 1);
			currentPlayer.decreaseMaterial(MaterialType.WATER, 1);
		}
		if (this.getOwner() == null) {
			this.setType(BuildingType.ROAD);
			this.setOwner(currentPlayer);
			currentPlayer.increaseEdgeCount(1);
		} else {
			this.setType(BuildingType.SUPERROAD);
		}
	}

	public boolean canDestroy() {
		if (this.getType() == BuildingType.EMPTYROAD || this.getType() == BuildingType.SUPERROAD) {
			return false;
		}
		return true;
	}

	public boolean canUpgrade() {
		Player currentPlayer = Utilities.getCurrentPlayer();
		if (currentPlayer.countMaterial(MaterialType.ROCK) >= 1
				&& currentPlayer.countMaterial(MaterialType.SAND) >= 1
				&& currentPlayer.countMaterial(MaterialType.WATER) >= 1) {
			if (this.getOwner() == null && Utilities.canCreateEdge(this)) {
				return true;
			}
		}
		return false;
	}

	public Node getEndNode() {
		return this.endNode;
	}

	public Node getStartNode() {
		return this.startNode;
	}

}
