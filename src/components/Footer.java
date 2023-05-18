package components;

import components.Button.CustomButton;
import components.Button.FooterButton;
import components.Button.RollDice;
import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
	private Button showCardButton;
	private Button endTurnButton;
	
	public Footer() {
		this.setPrefHeight(Config.Footer_HEIGHT);
	
		this.initBuyCardButton();
		this.initMarketButton();
		this.initRollDiceButton();
		this.initBuyEdgeButton();
		this.initBuyNodeButton();
		this.initEndTurnButton();
		this.initShowCardButton();

		this.setAlignment(Pos.CENTER);
		HBox.setMargin(this.rollDiceButton, new Insets(20));
		HBox.setMargin(this.buyCardButton, new Insets(8));
		HBox.setMargin(this.buyEdgeButton, new Insets(8));
		HBox.setMargin(this.buyNodeButton, new Insets(8));
		HBox.setMargin(this.marketButton, new Insets(8));
		
		this.getChildren().addAll(showCardButton, marketButton, buyCardButton, rollDiceButton, buyEdgeButton, buyNodeButton, endTurnButton);
	}
	
	
	private void initBuyCardButton() {
		this.buyCardButton = new FooterButton("Buy Card");
	}
	
	private void initMarketButton() {
		this.marketButton = new FooterButton("Market");
//		this.marketButton.setFont(Font.font(16));
		this.marketButton.setOnAction(e -> {
			ControlPane.getInstance().showMarketScene();
		});
	
	}
	private void initRollDiceButton() {

		this.rollDiceButton = new RollDice("Roll");

		this.rollDiceButton.setOnAction(e->{
			GamePlay instance = GamePlay.getInstance();
			if(instance.rollDice()) {
				int number = instance.getRollNumber();
				HeaderGame gameHeader = ControlPane.getInstance().getGameHeader();
				gameHeader.updateDiceNumber();
				this.setRollDiceButton(number);
			}
			
		});


	}
	
	private void initBuyNodeButton() {
		this.buyNodeButton = new FooterButton("Buy Node");
	}
	
	private void initBuyEdgeButton() {
		this.buyEdgeButton = new FooterButton("Buy Edge");
	}
	
	
	private void initEndTurnButton() {
		this.endTurnButton = new FooterButton("End turn");
		this.endTurnButton.setBackground(new Background(new BackgroundFill(Color.INDIANRED, new CornerRadii(12), null)));
	}
	
	private void initShowCardButton() {
		this.showCardButton = new FooterButton("Show Card");
		this.endTurnButton.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, new CornerRadii(12), null)));
	}


	public Button getRollDiceButton() {
		return rollDiceButton;
	}


	public void setRollDiceButton(int number) {
		this.rollDiceButton.setText(String.valueOf(number));
	}
	
	
	
}
