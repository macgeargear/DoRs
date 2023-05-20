package components.Material;

import components.Button.CustomButton;
import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GamePlay;
import logic.Marketplace;
import type.MaterialType;
import utils.Utilities;

public class MaterialExchange extends BorderPane {
	private Button exchangeButton;
	private Text amount;
	private int idx;
	
	private MaterialCard sourceCard;
	private MaterialCard targetCard;
	
	
	public MaterialExchange(int idx) {
		Marketplace marketplace = GamePlay.getInstance().getMarketplace();
		this.idx = idx;
		
		this.initAmountText(marketplace.getExchangeRate().get(idx));
		this.initExchangeButton();
		
		MaterialType firstMaterial = marketplace.getTradeListByIdx(idx).get(0).getType();
		MaterialType secondMaterial = marketplace.getTradeListByIdx(idx).get(1).getType();
		
		this.sourceCard = new MaterialCard(firstMaterial);
		this.targetCard = new MaterialCard(secondMaterial);

		HBox.setMargin(sourceCard, new Insets(Config.TINY_MARGIN));
		HBox.setMargin(targetCard, new Insets(Config.TINY_MARGIN));
		HBox.setMargin(this.amount, new Insets(Config.TINY_MARGIN));
		HBox.setMargin(exchangeButton, new Insets(Config.TINY_MARGIN));
		HBox exchangeRate = new HBox();
		VBox amountContainer = new VBox();
		amountContainer.getChildren().add(amount);
		amountContainer.setAlignment(Pos.CENTER);
		exchangeRate.getChildren().addAll(sourceCard, amountContainer, targetCard);
		
		sourceCard.setAlignment(Pos.CENTER);
		targetCard.setAlignment(Pos.CENTER);

		this.setLeft(exchangeRate);
		this.setRight(exchangeButton);
		BorderPane.setAlignment(exchangeButton, Pos.CENTER_RIGHT);
	}
	
	public void initExchangeButton() {
		this.exchangeButton = new CustomButton("Exchange");
		this.exchangeButton.setFont(Font.font(Config.SMALL_FONT));
		
		this.exchangeButton.setOnAction(e->{
			Marketplace marketplace = GamePlay.getInstance().getMarketplace();
			marketplace.trade(idx);
			Utilities.updateCard();
		});
	}
	
	public void initAmountText(int amount) {
		this.amount = new Text("X "+String.valueOf(amount));
		this.amount.setFont(Font.font(Config.LARGE_FONT));
	}
	
	public void updateExchangeStatus() {
		Marketplace marketplace = GamePlay.getInstance().getMarketplace();
		MaterialType firstMaterial = marketplace.getTradeListByIdx(idx).get(0).getType();
		MaterialType secondMaterial = marketplace.getTradeListByIdx(idx).get(1).getType();
		
		this.sourceCard.setMaterial(firstMaterial);
		this.targetCard.setMaterial(secondMaterial);
		this.amount.setText("X "+marketplace.getExchangeRate().get(idx));
		this.exchangeButton.setDisable(!marketplace.canTrade(idx));
		
	}
}
