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
		
		ControlPane.getInstance().setFooter(this);
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
		this.rollDiceButton.setBackground(new Background(new BackgroundFill(Color.MISTYROSE,null,null)));
		this.rollDiceButton.setDisable(true);
		this.rollDiceButton.setOnAction(e->{
			GamePlay instance = GamePlay.getInstance();
			if(instance.rollDice()) {
				HeaderGame gameHeader = ControlPane.getInstance().getGameHeader();
				gameHeader.updateDiceNumber();
				rollDiceButton.setDisable(true);
			}
		});

	}
	
	private void initBuyNodeButton() {
		this.buyNodeButton = new FooterButton("Buy Node");
		this.buyNodeButton.setDisable(true);
		
		this.buyNodeButton.setOnAction(e->{
			ControlPane paneInstance = ControlPane.getInstance();
			paneInstance.getSelectNode().getNode().upgrade();
			paneInstance.getSelectNode().setupSyle();
		});
	}
	
	private void initBuyEdgeButton() {
		this.buyEdgeButton = new FooterButton("Buy Edge");
		this.buyEdgeButton.setDisable(true);
		
		this.buyEdgeButton.setOnAction(e->{
			ControlPane paneInstance = ControlPane.getInstance();
			paneInstance.getSelectEdge().getEdge().upgrade();
			paneInstance.getSelectEdge().setupSyle();
		});
	}
	
	
	private void initEndTurnButton() {
		this.endTurnButton = new FooterButton("End turn");
		this.endTurnButton.setBackground(new Background(new BackgroundFill(Color.INDIANRED, new CornerRadii(12), null)));
		this.endTurnButton.setDisable(true);
	}
	
	private void initShowCardButton() {
		this.showCardButton = new FooterButton("Show Card");
		this.endTurnButton.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, new CornerRadii(12), null)));
	}
	
	public void setBuyNodeDisable(boolean isDisable) {
		this.buyNodeButton.setDisable(isDisable);
	}
	
	public void setBuyEdgeDisable(boolean isDisable) {
		this.buyEdgeButton.setDisable(isDisable);
	}

	public Button getBuyCardButton() {
		return buyCardButton;
	}

	public Button getBuyEdgeButton() {
		return buyEdgeButton;
	}

	public Button getBuyNodeButton() {
		return buyNodeButton;
	}
	
}
