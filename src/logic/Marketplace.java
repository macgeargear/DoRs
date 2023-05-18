package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import material.Material;
import utils.Utilities;

public class Marketplace {
	private int amount;
	private ArrayList<ArrayList<Material>> tradeList;
	private ArrayList<Integer> exchangeRate;
	
	public Marketplace() {
		this.tradeList = new ArrayList<ArrayList<Material>>();
		this.exchangeRate = new ArrayList<Integer>();
		this.reMarket();
	}
	
	public void reMarket() {
		this.amount = 20;
		Random random = new Random();
		ArrayList<Material> allMaterials = Utilities.getAllMaterials();
		for(int i=0;i<5;++i) {
			int num1 = random.nextInt(5), num2 = random.nextInt(5);
			while(num1 == num2) {
				num2 = random.nextInt(5);
			}
			tradeList.add(new ArrayList<Material>(Arrays.asList(allMaterials.get(num1), allMaterials.get(num2))));
			exchangeRate.add(random.nextInt(5)+1);
		}
//		random everything
		return ;
	}
	
	public void trade(int target) {
		if(amount == 0) return ;
		Player currentPlayer = Utilities.getCurrentPlayer();
		currentPlayer.decreaseMaterial(tradeList.get(target).get(0).getType(), exchangeRate.get(target));
		currentPlayer.increaseMaterial(tradeList.get(target).get(1).getType(), 1);
		amount--;
		return ;
	}
	
	public ArrayList<Integer> canTrade(){
		Player currentPlayer = Utilities.getCurrentPlayer();
		ArrayList<Integer> canIndex =  new ArrayList<Integer>();
		for(int i=0;i<5;++i) {
			Material target = tradeList.get(i).get(0);
			if(currentPlayer.countMaterial(target) > exchangeRate.get(i)) {
				canIndex.add(i);
			}
		}
		return canTrade();
	}

	public ArrayList<ArrayList<Material>> getTradeList() {
		return tradeList;
	}

	public ArrayList<Integer> getExchangeRate() {
		return exchangeRate;
	}
	
	
}
