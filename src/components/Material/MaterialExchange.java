package components.Material;

import components.Button.CustomButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GamePlay;
import logic.Marketplace;
import pane.ControlPane;
import utils.Utilities;

public class MaterialExchange extends HBox {
	private Button exchangeButton;
	private Text amount;
	private int idx;
	
	private MaterialCard sourceCard;
	private MaterialCard targetCard;
	
	
	public MaterialExchange(String source, int amount, Color sourceColor, String target, Color targetColor, int idx) {
		this.initAmountText(amount);
		this.initExchangeButton();
		this.idx = idx;
		this.sourceCard = new MaterialCard(sourceColor, source);
		this.targetCard = new MaterialCard(targetColor, target);

		HBox.setMargin(sourceCard, new Insets(8));
		HBox.setMargin(targetCard, new Insets(8));
		HBox.setMargin(this.amount, new Insets(8));
		HBox.setMargin(exchangeButton, new Insets(8));

		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(sourceCard, this.amount, targetCard, exchangeButton);
	}
	
	public void initExchangeButton() {
		this.exchangeButton = new CustomButton("Exchange");
		this.exchangeButton.setFont(Font.font(18));
		
		this.exchangeButton.setOnAction(e->{
			Marketplace marketplace = GamePlay.getInstance().getMarketplace();
			marketplace.trade(idx);
			Utilities.updateCard();
		});
	}
	
	public void initAmountText(int amount) {
		this.amount = new Text("X "+String.valueOf(amount));
		this.amount.setFont(Font.font(40));
	}
	
	public void updateExchangeStatus() {
		GamePlay gameInstance = GamePlay.getInstance();
		this.exchangeButton.setDisable(!gameInstance.getMarketplace().canTrade(idx));
	}
}
