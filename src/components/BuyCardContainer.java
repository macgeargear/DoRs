package components;

import components.Button.FooterButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import material.MaterialPack;
import type.MaterialType;
import utils.Utilities;

public class BuyCardContainer extends BorderPane {
	private MaterialType type;
	private int amount;
	private int number;
	
	private Text titleText;
	private Text amountText;
	private HBox footer;
	private Button increaseButton;
	private Text numberText;
	private Button decreaseButton;
	
	private VBox titleContainer;

	public BuyCardContainer(MaterialType type, int amount) {
		this.type = type;
		this.amount = amount;
		this.setPrefHeight(100);
		this.setPrefWidth(100);
		this.setBackground(new Background(new BackgroundFill(Utilities.getColor(type), new CornerRadii(12), null)));
		this.initMaterialCard();
		this.initFooter();
		
		this.titleContainer.setPadding(new Insets(20));
		this.footer.setPadding(new Insets(0,0,30,0));
		
		this.setTop(titleContainer);
		this.setBottom(footer);
		this.updateAmount();
//		this.getChildren().addAll(this.titleText, this.amountText, this.footer);
	}
	
	private void initMaterialCard() {
		this.titleContainer = new VBox();
		this.titleText = new Text(String.valueOf(type));
		this.titleText.setFont(Font.font(40));
		this.amountText = new Text(String.valueOf(amount));
		this.amountText.setFont(Font.font(40));
		this.titleContainer.getChildren().addAll(this.titleText, this.amountText);
	}
	
	private void initFooter() {
		this.footer = new HBox();
		this.numberText = new Text(""+number);
		this.numberText.setFont(Font.font(20));
		
		this.decreaseButton = new FooterButton("-");
		this.decreaseButton.setFont(Font.font(12));
		this.decreaseButton.setPadding(new Insets(12));
		this.decreaseButton.setOnAction(e -> {
//			Utilities.updateCard();
			Utilities.getCurrentPlayer().increaseMaterial(type, 1);
			number--;
			this.numberText.setText(""+this.number);
			this.updateAmount();
		});
		this.increaseButton = new FooterButton("+");
		this.increaseButton.setFont(Font.font(12));
		this.increaseButton.setPadding(new Insets(12));
		this.increaseButton.setOnAction(e -> {
//			Utilities.updateCard();
			Utilities.getCurrentPlayer().decreaseMaterial(type,1);
			number++;
			this.numberText.setText(""+this.number);
			this.updateAmount();
		});
		
		this.footer.setAlignment(Pos.BOTTOM_CENTER);
		this.footer.getChildren().addAll(this.decreaseButton, this.numberText, this.increaseButton);
	}
	
	
	public void updateAmount() {
		amount = Utilities.getCurrentPlayer().getMaterialPack(this.getType()).getAmount();
		this.amountText.setText(""+amount);
		if(amount == 0) {
			this.increaseButton.setDisable(true);
		}else {
			this.increaseButton.setDisable(false);
		}
		
		if(number == 0) {
			this.decreaseButton.setDisable(true);
		}else {
			this.decreaseButton.setDisable(false);
		}
	}

	public MaterialType getType() {
		return type;
	}

	public int getAmount() {
		return amount;
	}

	public int getNumber() {
		return number;
	}

	public Text getTitleText() {
		return titleText;
	}

	public Text getAmountText() {
		return amountText;
	}

	public HBox getFooter() {
		return footer;
	}

	public Button getIncreaseButton() {
		return increaseButton;
	}

	public Text getNumberText() {
		return numberText;
	}

	public Button getDecreaseButton() {
		return decreaseButton;
	}

	public VBox getTitleContainer() {
		return titleContainer;
	}
	
}
