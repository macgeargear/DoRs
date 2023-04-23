package buildings;

import java.util.ArrayList;

import logic.GamePlay;
import logic.Player;
import type.BuildingType;
import utils.getTotalOwners;

public class Node extends Building {
	private int score;
	private ArrayList<Edge> sideEdges;

	public Node(BuildingType type) {
		super(type);
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
		this.setType(BuildingType.EMPTYHOUSE);
		this.setOwner(null);
		this.setScore(0);
	}

	public void upgrade() {
		if (!this.getType().equals(BuildingType.CITY)) {
			if (this.getType().equals(BuildingType.EMPTYHOUSE)) {
				int owners = utils.getTotalOwners.fromSideEdges(this.getSideEdges());
				if (owners == 1) {
					this.setType(BuildingType.HOUSE);
				}
				if (this.getType().equals(BuildingType.HOUSE)) {
					this.setType(BuildingType.TOWER);
				}
				if (this.getType().equals(BuildingType.TOWER)) {
					this.setType(BuildingType.CITY);
				}


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
		if ((this.getType().equals(BuildingType.EMPTYHOUSE) && this.checkSideEdges() == false)
				|| (this.getType().equals(BuildingType.CITY))) {
			return false;
		}
//		edit later
		return true;
	}
	
	public boolean checkSideEdges() {
		GamePlay gameInstance = GamePlay.getInstance();
		Player p = gameInstance.getAllPlayers().get(gameInstance.getCurrentPlayer());
		for(Edge edge: sideEdges) {
			if(edge.getOwner().equals(p)) {
				return true;
			}
		}
		return false;
	}

}
