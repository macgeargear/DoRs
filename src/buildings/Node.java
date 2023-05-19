package buildings;

import java.util.ArrayList;

import logic.GamePlay;
import logic.Player;
import type.BuildingType;
import type.MaterialType;
import utils.Utilities;
import utils.getTotalOwners;

public class Node extends Building {
	private int score;
	private ArrayList<Edge> sideEdges;

	public Node(BuildingType type) {
		super(type);
		sideEdges = new ArrayList<Edge>();
		for (int i = 0; i < 4; ++i) {
			sideEdges.add(null);
		}
	}

	public int getScore() {
		return this.score;
	}

	public ArrayList<Edge> getSideEdges() {
		return this.sideEdges;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void destroy() {
		if(this.getType() != BuildingType.CITY) {			
			this.setType(BuildingType.EMPTYHOUSE);
			this.setOwner(null);
			this.setScore(0);
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

	public void destroySideEdge(int position) {
		this.sideEdges.get(position).destroy();
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
		if (currentPlayer.getMaterialPack(MaterialType.ROCK).getAmount() >= 1
				&& currentPlayer.getMaterialPack(MaterialType.WOOD).getAmount() >= 1
				&& currentPlayer.getMaterialPack(MaterialType.WATER).getAmount() >= 1) {
			if (this.getOwner() == null && Utilities.haveSideEdge(this)) {
				return true;
			}else if(this.getOwner() != null && this.getOwner().equals(currentPlayer)) {
				if(this.getType() != BuildingType.CITY) {
					return true;
				}
			}
		}

		// edit later
		return false;
	}

}
