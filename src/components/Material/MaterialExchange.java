package components.Material;

import components.Button.CustomButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MaterialExchange extends HBox {
	private Button exchangeButton;
	private Text amount;
	
	private MaterialCard sourceCard;
	private MaterialCard targetCard;
	
	
	public MaterialExchange(String source, int amount, Color sourceColor, String target, Color targetColor) {
		this.initAmountText(amount);
		this.initExchangeButton();

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
	}
	
	public void initAmountText(int amount) {
		this.amount = new Text("X "+String.valueOf(amount));
		this.amount.setFont(Font.font(40));
	}
}
