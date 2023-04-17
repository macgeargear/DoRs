package logic;

import java.util.ArrayList;

import material.Material;
import material.MaterialPack;

public class Marketplace {
	private int amount;
	private ArrayList<ArrayList<Material>> tradeList;
	private ArrayList<Integer> exchangeRate;
	
	public Marketplace() {
		this.tradeList = new ArrayList<ArrayList<Material>>();
		this.exchangeRate = new ArrayList<Integer>();
		this.random();
	}
	
	public void random() {
		this.amount = 20;
//		random everything
		return ;
	}
	
	public void trade(ArrayList<MaterialPack> materials, int target, int number) {
//		process
		return ;
	}
	
	public ArrayList<Integer> canTrade(ArrayList<MaterialPack> materials){
		return new ArrayList<Integer>();
	}
}
