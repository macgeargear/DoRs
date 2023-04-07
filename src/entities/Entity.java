package entities;

import type.EntityType;

public class Entity implements Moveable {
	private EntityType type;
	private Point position;
	
	public Entity(EntityType type) {}

	@Override
	public boolean canMove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean moveTo(Point p) {
		// TODO Auto-generated method stub
		return false;
	}

}
