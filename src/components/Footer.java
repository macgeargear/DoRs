package components;

import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.*;
import logic.GamePlay;
import pane.ControlPane;

public class Footer extends HBox {
	private Button buyCardButton;
	private Button marketButton;
	private Button rollDiceButton;
	private Button buyNodeButton;
	private Button buyEdgeButton;
	
	public Footer() {
		this.setPrefHeight(Config.Footer_HEIGHT);
	
		// init
		this.initBuyCardButton();
		this.initMarketButton();
		this.initRollDiceButton();
		this.initBuyEdgeButton();
		this.initBuyNodeButton();

		this.setAlignment(Pos.CENTER);
		HBox.setMargin(this.rollDiceButton, new Insets(20));
		
		this.getChildren().addAll(this.buyEdgeButton, this.buyNodeButton, this.buyCardButton, this.rollDiceButton, this.marketButton);
	}
	
	
	private void initBuyCardButton() {
		this.buyCardButton = new CustomButton("Buy Card");
		this.buyCardButton.setFont(Font.font(20));
	}
	
	private void initMarketButton() {
		this.marketButton = new CustomButton("Market");
		this.marketButton.setFont(Font.font(20));
		this.marketButton.setOnAction(e -> {
			ControlPane.getInstance().showMarketScene();
		});
	
	}
	private void initRollDiceButton() {
		this.rollDiceButton = new CustomButton("Roll");
		this.rollDiceButton.setPrefHeight(100);
		this.rollDiceButton.setPrefWidth(100);
		
		this.rollDiceButton.setOnAction(e->{
			GamePlay instance = GamePlay.getInstance();
			if(instance.rollDice()) {
				int number = instance.getRollNumber();
				HeaderGame gameHeader = ControlPane.getInstance().getGameHeader();
				gameHeader.updateDiceNumber();
			}
			
		});
	}
	
	private void initBuyNodeButton() {
		this.buyNodeButton = new CustomButton("Buy Node");
		this.buyCardButton.setFont(Font.font(20));
	}
	
	private void initBuyEdgeButton() {
		this.buyEdgeButton = new CustomButton("Buy Edge");
		this.buyCardButton.setFont(Font.font(20));
	}
}
