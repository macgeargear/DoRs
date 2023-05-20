package buildings;

import java.util.ArrayList;

import logic.GamePlay;
import logic.Player;
import type.BuildingType;
import type.MaterialType;
import utils.Utilities;

public class Node extends Building {
	private ArrayList<Edge> sideEdges;

	public Node(BuildingType type) {
		super(type);
		sideEdges = new ArrayList<Edge>();
		for (int i = 0; i < 4; ++i) {
			sideEdges.add(null);
		}
	}

	public void destroy() {
		if(this.getType() != BuildingType.CITY) {			
			this.setType(BuildingType.EMPTYHOUSE);
			this.setOwner(null);
		}
	}

	public void upgrade() {
		Player currentPlayer = Utilities.getCurrentPlayer();
		
		if(GamePlay.getInstance().getCurrentRound() > 0) {			
			currentPlayer.decreaseMaterial(MaterialType.ROCK, 1);
			currentPlayer.decreaseMaterial(MaterialType.WOOD, 1);
			currentPlayer.decreaseMaterial(MaterialType.WATER, 1);
		}
		if (this.getOwner() == null) {
			this.setType(BuildingType.HOUSE);
			this.setOwner(currentPlayer);
			currentPlayer.increaseNodeCount(1);
		} else {
			if (this.getType() == BuildingType.HOUSE) {
				this.setType(BuildingType.TOWER);
			} else {
				this.setType(BuildingType.CITY);
			}
		}
	}

	public void setSideEdge(int position, Edge edge) {
		this.sideEdges.set(position, edge);
	}

	@Override
	public boolean canDestroy() {
		if (this.getType().equals(BuildingType.EMPTYHOUSE) || this.getType().equals(BuildingType.CITY)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean canUpgrade() {
		Player currentPlayer = Utilities.getCurrentPlayer();
		if (currentPlayer.countMaterial(MaterialType.ROCK) >= 1
				&& currentPlayer.countMaterial(MaterialType.WOOD) >= 1
				&& currentPlayer.countMaterial(MaterialType.WATER) >= 1) {
			if (this.getOwner() == null && Utilities.haveSideEdge(this)) {
				return true;
			}else if(this.getOwner() != null && this.getOwner().equals(currentPlayer)) {
				if(this.getType() != BuildingType.CITY) {
					return true;
				}
			}
		}

		return false;
	}
	
	public ArrayList<Edge> getSideEdges() {
		return this.sideEdges;
	}

}
