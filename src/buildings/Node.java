package buildings;

import java.util.ArrayList;

import logic.Player;
import type.BuildingType;
import utils.getTotalOwners;

public class Node extends Building implements Upgradeable, Destroyable {
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
		this.sideEdges.get(position) = edge;
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
		if ((this.getType().equals(BuildingType.EMPTYHOUSE) && this.getSideEdges().size() > 1)
				|| (this.getType().equals(BuildingType.CITY))) {
			return false;
		}
		return true;
	}

}
