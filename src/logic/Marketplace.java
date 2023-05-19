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
		this.reMarket();
	}
	
	public void reMarket() {
		this.tradeList = new ArrayList<ArrayList<Material>>();
		this.exchangeRate = new ArrayList<Integer>();
		this.amount = 20;
		Random random = new Random();
		ArrayList<Material> allMaterials = Utilities.getAllMaterials();
		
		for(int i=0;i<5;++i) {
			int num1 = random.nextInt(5), num2 = random.nextInt(5);
			while(num1 == num2) {
				num2 = random.nextInt(5);
			}
			tradeList.add(new ArrayList<Material>(Arrays.asList(allMaterials.get(num1), allMaterials.get(num2))));
			exchangeRate.add(random.nextInt(3)+1);
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
	
	public boolean canTrade(int idx){
		if(idx >= 5 || amount <= 0) return false;
	
		Player currentPlayer = Utilities.getCurrentPlayer();
		Material target = tradeList.get(idx).get(0);
		if(currentPlayer.countMaterial(target.getType()) >= exchangeRate.get(idx)) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Material> getTradeListByIdx(int idx){
		return tradeList.get(idx);
	}
	
	public ArrayList<ArrayList<Material>> getTradeList() {
		return tradeList;
	}

	public ArrayList<Integer> getExchangeRate() {
		return exchangeRate;
	}

	public int getAmount() {
		return amount;
	}
	
	
}
