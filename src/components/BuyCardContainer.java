package components;

import components.Button.FooterButton;
import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
//		this.setMaxWidth(100);
//		this.setMaxHeight(100);
		this.setBackground(new Background(new BackgroundFill(Utilities.getColor(type), new CornerRadii(12), null)));
		this.initMaterialCard();
		this.initFooter();
		
		this.titleContainer.setPadding(new Insets(Config.MEDIUM_MARGIN));
		this.footer.setPadding(new Insets(0,0,Config.LARGE_PADDING,0));
		
		this.setTop(titleContainer);
		this.setBottom(footer);
		this.updateAmount();
//		this.getChildren().addAll(this.titleText, this.amountText, this.footer);
	}
	
	private void initMaterialCard() {
		this.titleContainer = new VBox();
		this.titleContainer.setAlignment(Pos.CENTER);
		this.titleText = new Text(String.valueOf(type));
		this.titleText.setFont(Font.font(Config.MEDIUM_FONT));
		this.amountText = new Text(String.valueOf(amount));
		this.amountText.setFont(Font.font(Config.MEDIUM_FONT));
		this.titleContainer.getChildren().addAll(this.titleText, this.amountText);
	}
	
	private void initButtons() {
		this.decreaseButton = new Button("-");
		this.decreaseButton.setFont(Font.font(Config.SMALL_FONT));
		this.decreaseButton.setPadding(new Insets(Config.SMALL_FONT));
		this.decreaseButton.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(Config.BORDER_RADIUS), null)));
		
		this.increaseButton = new Button("+");
		this.increaseButton.setFont(Font.font(Config.SMALL_FONT));
		this.increaseButton.setPadding(new Insets(Config.SMALL_FONT));
		this.increaseButton.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(Config.BORDER_RADIUS), null)));
	}
	
	private void initFooter() {
		this.footer = new HBox();
		this.numberText = new Text(""+number);
		HBox.setMargin(numberText, new Insets(Config.SMALL_MARGIN));
		this.numberText.setFont(Font.font(Config.MEDIUM_FONT));
		
		this.initButtons();
		this.decreaseButton.setOnAction(e -> {
			Utilities.getCurrentPlayer().increaseMaterial(type, 1);
			number--;
			this.numberText.setText(""+this.number);
			Utilities.updateCard();
		});
		this.increaseButton.setOnAction(e -> {
//			Utilities.updateCard();
			Utilities.getCurrentPlayer().decreaseMaterial(type,1);
			number++;
			this.numberText.setText(""+this.number);
			Utilities.updateCard();
			
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
	
	public void setDisableIncrease() {
		this.increaseButton.setDisable(true);
	}
	
	public void resetValue() {
		while(number > 0) {
			Utilities.getCurrentPlayer().increaseMaterial(type, 1);
			number--;
		}
		this.numberText.setText("0");
	}
	
	public void confirm() {
		this.number = 0;
		this.numberText.setText("0");
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
