package entities;

import buildings.Node;

public interface Moveable {
	public boolean canMove(Node node);
	public void moveTo(Node node);
}
