package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import config.Config;
import type.MaterialType;
import utils.Utilities;

public class Marketplace {
	private int amount;
	private ArrayList<ArrayList<MaterialType>> tradeList;
	private ArrayList<Integer> exchangeRate;
	
	public Marketplace() {
		this.reMarket();
	}
	
	public void reMarket() {
		this.tradeList = new ArrayList<ArrayList<MaterialType>>();
		this.exchangeRate = new ArrayList<Integer>();
		this.amount = Config.TRADE_AMOUNT;
		Random random = new Random();
		ArrayList<MaterialType> allMaterials = Utilities.getAllMaterials();
		int materialsSize = allMaterials.size();
		
		for(int i=0;i<Config.NUMBER_TRADE_LIST;++i) {
			int num1 = random.nextInt(materialsSize), num2 = random.nextInt(materialsSize);
			while(num1 == num2) {
				num2 = random.nextInt(materialsSize);
			}
			tradeList.add(new ArrayList<MaterialType>(Arrays.asList(allMaterials.get(num1), allMaterials.get(num2))));
			exchangeRate.add(random.nextInt(Config.MAX_RATE)+1);
		}
//		random everything
		return ;
	}
	
	public void trade(int target) {
		if(amount == 0) return ;
		Player currentPlayer = Utilities.getCurrentPlayer();
		currentPlayer.decreaseMaterial(tradeList.get(target).get(0), exchangeRate.get(target));
		currentPlayer.increaseMaterial(tradeList.get(target).get(1), 1);
		amount--;
		return ;
	}
	
	public boolean canTrade(int idx){
		if(idx >= Config.NUMBER_TRADE_LIST || amount <= 0) return false;
	
		Player currentPlayer = Utilities.getCurrentPlayer();
		MaterialType target = tradeList.get(idx).get(0);
		if(currentPlayer.countMaterial(target) >= exchangeRate.get(idx)) {
			return true;
		}
		return false;
	}
	
	public ArrayList<MaterialType> getTradeListByIdx(int idx){
		return tradeList.get(idx);
	}

	public ArrayList<Integer> getExchangeRate() {
		return exchangeRate;
	}

	public int getAmount() {
		return amount;
	}
	
	
}
