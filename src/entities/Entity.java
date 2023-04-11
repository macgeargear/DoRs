package entities;

import java.util.ArrayList;

import buildings.Node;
import logic.Player;
import material.Material;
import type.EntityType;

public class Entity implements Moveable {
	private EntityType type;
	private boolean isMove;
	private int duration;
	private Node position;
	private Player owner;
	private ArrayList<Material> wantMaterials;
	
	public Entity(EntityType type, Player owner, int duration, ArrayList<Material> wantMaterials) {
		
	}

	@Override
	public boolean canMove(Node node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void moveTo(Node node) {
		// TODO Auto-generated method stub
		
	}

	public EntityType getType() {
		return type;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = Math.max(0, duration);
	}

	public Node getPosition() {
		return position;
	}

	public void setPosition(Node position) {
		this.position = position;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public ArrayList<Material> getWantMaterials() {
		return wantMaterials;
	}

	public void setWantMaterials(ArrayList<Material> wantMaterials) {
		this.wantMaterials = wantMaterials;
	}



	
}
