package buildings;

public class Node extends Building implements Upgradeable {
	private Point positoin;
	private int score;
	
	public Node(BuildingType type, Point positoin) {
		super(type);
		this.setPositoin(position);
	}

	public Point getPositoin() {
		return positoin;
	}

	public void setPositoin(Point positoin) {
		this.positoin = positoin;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
