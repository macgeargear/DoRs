package pane.popup;

import java.util.ArrayList;
import components.BuyCardContainer;

import components.Button.ExitButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import logic.GamePlay;
import material.Material;
import material.MaterialPack;
import pane.ControlPane;
import utils.Utilities;

public class BuyCardPopup extends Popup {
	private VBox popupContent;
	private HBox controlRate;
	private Button closeButton;
	private ArrayList<MaterialPack> allMaterials;
	private ArrayList<Integer> allAmounts;
	private ArrayList<BuyCardContainer> allBuyCardContainers;
	private Button reset;
	private Button confirm;

	public BuyCardPopup() {
		allBuyCardContainers = new ArrayList<BuyCardContainer>();
		this.centerOnScreen();
		this.initContent();
		this.initFooter();
		
		
		this.getContent().add(popupContent);
//		this.updateAmount();
		ControlPane.getInstance().setBuyCardPopup(this);
	}
	
	private void initFooter() {
		HBox footer = new HBox();
		footer.setAlignment(Pos.CENTER);
		footer.setSpacing(20);
		
		reset = new Button("Reset");
		confirm = new Button("Confirm");
		confirm.setDisable(true);
		reset.setDisable(true);
		
		reset.setOnAction(e->{
			this.resetValue();
			Utilities.updateCard();
		});
		
		confirm.setOnAction(e->{
			this.confirm();
			Utilities.updateCard();
			confirm.setDisable(true);
			GamePlay.getInstance().draw();
			System.out.println(Utilities.getCurrentPlayer().getAllEffectCards().size());
		});
		
		footer.getChildren().addAll(reset, confirm);
		popupContent.getChildren().add(footer);
	}
	
	private void initContent() {
		Label messageLabel = new Label("See Your Card");
		messageLabel.setFont(Font.font(32));
		this.allMaterials = Utilities.getCurrentPlayer().getAllMaterials();
		this.allAmounts = new ArrayList<Integer>();
		
		this.popupContent = new VBox();
		this.popupContent.setAlignment(Pos.CENTER);
		this.popupContent.setPrefHeight(600);
		this.popupContent.setPrefWidth(360);
		this.popupContent.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(14), null)));
		Text header = new Text("Buy Effect Card");
		header.setFont(Font.font(32));
		popupContent.getChildren().add(header);
		
		this.controlRate = new HBox();
		this.controlRate.setPrefHeight(500);
		this.controlRate.setPrefWidth(360);
		this.controlRate.setAlignment(Pos.CENTER);
		
		HBox.setMargin(this.controlRate, new Insets(20));
		this.controlRate.setPadding(new Insets(40));
		
		this.closeButton = new ExitButton("X");
		this.closeButton.setOnAction(e -> {
			this.resetValue();
			Utilities.updateCard();
			this.hide();
		});
//		Utilities.updateCard();
		for (MaterialPack material : this.allMaterials) {
			BuyCardContainer card = new BuyCardContainer(material.getType().getType(), material.getAmount());
			this.allBuyCardContainers.add(card);
			HBox.setMargin(card, new Insets(20));
			this.controlRate.getChildren().add(card);
		}
		

		this.controlRate.getChildren().addAll(closeButton, messageLabel);	
		popupContent.getChildren().add(controlRate);
	}	
	
	public void updateAmount() {
		int sum = 0;
		for(BuyCardContainer card: allBuyCardContainers) {
			card.updateAmount();
			sum += card.getNumber();
		}
		if(sum == 0) {
			reset.setDisable(true);
		}else {
			reset.setDisable(false);
		}
		if(sum == 3) {
			confirm.setDisable(false);
			for(BuyCardContainer card: allBuyCardContainers) {
				card.setDisableIncrease();
			}
		}
	}	
	
	private void resetValue() {
		for(BuyCardContainer card: allBuyCardContainers) {
			card.resetValue();
		}
	}
	
	private void confirm() {
		for(BuyCardContainer card: allBuyCardContainers) {
			card.confirm();
		}
	}

}
