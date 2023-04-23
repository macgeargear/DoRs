package buildings;

import utils.getTotalOwners;
import logic.GamePlay;
import logic.Player;
import type.BuildingType;

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
		if (this.getType().equals(BuildingType.EMPTYROAD)) {
			this.setType(BuildingType.ROAD);
			GamePlay gameInstance = GamePlay.getInstance();
			this.setOwner(gameInstance.getAllPlayers().get(gameInstance.getCurrentPlayer()));
		} else if (this.getType().equals(BuildingType.ROAD)) {
			this.setType(BuildingType.SUPERROAD);
		}
	}

	public boolean canDestroy() {
		if (this.getType().equals(BuildingType.EMPTYROAD) || this.getType().equals(BuildingType.SUPERROAD)) {
			return false;
		}
		return true;
	}

	public boolean canUpgrade() {
		GamePlay gameInstance = GamePlay.getInstance();
		Player currentPlayer = gameInstance.getAllPlayers().get(gameInstance.getCurrentPlayer());
		if (this.getOwner() != null || this.getType().equals(BuildingType.SUPERROAD)
				|| !(startNode.getOwner().equals(currentPlayer) || endNode.getOwner().equals(currentPlayer))) {
			return false;
		}
		return true;
	}

	public Node getEndNode() {
		return this.endNode;
	}

	public Node getStartNode() {
		return this.startNode;
	}

}
