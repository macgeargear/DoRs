package buildings;

import java.util.ArrayList;

import logic.GamePlay;
import logic.Player;
import type.BuildingType;
import utils.Utilities;
import utils.getTotalOwners;

public class Node extends Building {
	private int score;
	private ArrayList<Edge> sideEdges;

	public Node(BuildingType type) {
		super(type);
		sideEdges = new ArrayList<Edge>();
		for(int i=0;i<4;++i) {
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
		this.setType(BuildingType.EMPTYHOUSE);
		this.setOwner(null);
		this.setScore(0);
	}

	public void upgrade() {
		GamePlay instance = GamePlay.getInstance();
		Player currentPlayer = Utilities.getCurrentPlayer();
		
//		prepare phase
		if(instance.getCurrentRound() < 0) {
			if(this.getType() == BuildingType.EMPTYHOUSE) {
				this.setType(BuildingType.HOUSE);
				this.setOwner(currentPlayer);
			}
			return ;
		}
		
//		game phase
		if (!this.getType().equals(BuildingType.CITY)) {
			if (this.getType().equals(BuildingType.EMPTYHOUSE)) {
				boolean haveSideEdge = Utilities.haveSideEdge(this);
				if (haveSideEdge) {
					this.setType(BuildingType.HOUSE);
				}
				
			}else if (this.getType().equals(BuildingType.HOUSE)) {
				this.setType(BuildingType.TOWER);
			}
			else if (this.getType().equals(BuildingType.TOWER)) {
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
