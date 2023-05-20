package pane.popup;

import java.util.ArrayList;
import components.BuyCardContainer;

import components.Button.ExitButton;
import components.Button.FooterButton;
import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import logic.GamePlay;
import material.MaterialPack;
import pane.ControlPane;
import utils.Utilities;

public class BuyCardPopup extends Popup {
	private BorderPane popupContent;
	private HBox controlRate;
	private Button closeButton;
	private ArrayList<MaterialPack> allMaterials;
	private ArrayList<Integer> allAmounts;
	private ArrayList<BuyCardContainer> allBuyCardContainers;
	private Button reset;
	private Button confirm;
	private Text header;
	private HBox footer;

	public BuyCardPopup() {
		allBuyCardContainers = new ArrayList<BuyCardContainer>();
		this.centerOnScreen();
		this.initContent();
		this.initFooter();
		this.popupContent.setPadding(new Insets(18));

		this.getContent().add(popupContent);
//		this.updateAmount();
//		this.popupContent.setLeft(closeButton);
		this.popupContent.setBottom(footer);
		this.popupContent.setTop(header);
		this.popupContent.setTop(closeButton);

		BorderPane.setAlignment(closeButton, Pos.TOP_LEFT);
		BorderPane.setAlignment(header, Pos.TOP_CENTER);

		this.popupContent.setCenter(controlRate);
		ControlPane.getInstance().setBuyCardPopup(this);
	}

	private void initFooter() {
		this.footer = new HBox();
		footer.setAlignment(Pos.CENTER);
//		footer.setSpacing(20);

		reset = new FooterButton("Reset");
		confirm = new FooterButton("Confirm");
		confirm.setDisable(true);
		reset.setDisable(true);

		reset.setOnAction(e -> {
			this.resetValue();
			Utilities.updateCard();
		});

		confirm.setOnAction(e -> {
			this.confirm();
			Utilities.updateCard();
			confirm.setDisable(true);
			GamePlay.getInstance().draw();
			System.out.println(Utilities.getCurrentPlayer().getAllEffectCards().size());
		});

		this.footer.getChildren().addAll(reset, confirm);

//		popupContent.getChildren().add(footer);
	}

	private void initContent() {
//		Label messageLabel = new Label("See Your Card");
//		messageLabel.setFont(Font.font(32));
		this.allMaterials = Utilities.getCurrentPlayer().getAllMaterials();
		this.allAmounts = new ArrayList<Integer>();

		this.popupContent = new BorderPane();
		this.popupContent.setPrefHeight(400);
		this.popupContent.setPrefWidth(800);
		BorderPane.setAlignment(this.popupContent, Pos.CENTER);
		this.popupContent.setBackground(
				new Background(new BackgroundFill(Color.WHITE, new CornerRadii(Config.BORDER_RADIUS), null)));
		this.header = new Text("Buy Effect Card");
		this.header.setFont(Font.font(Config.LARGE_FONT));
//		popupContent.getChildren().add(header);

		this.controlRate = new HBox();
		this.controlRate.setPrefHeight(Config.BUYCARD_POPUP_HEIGHT);
		this.controlRate.setPrefWidth(Config.BUYCARD_POPUP_WIDTH);
		this.controlRate.setAlignment(Pos.CENTER);

		HBox.setMargin(this.controlRate, new Insets(Config.MEDIUM_MARGIN));
		this.controlRate.setPadding(new Insets(Config.LARGE_PADDING));

		this.closeButton = new ExitButton("X");
		this.closeButton.setOnAction(e -> {
			this.resetValue();
			Utilities.updateCard();
			this.hide();
		});
//		Utilities.updateCard();
		for (MaterialPack material : this.allMaterials) {
			BuyCardContainer card = new BuyCardContainer(material.getType(), material.getAmount());
			this.allBuyCardContainers.add(card);
			HBox.setMargin(card, new Insets(Config.SMALL_MARGIN));
			card.setPadding(new Insets(Config.LARGE_PADDING));
			this.controlRate.getChildren().add(card);
		}

//		this.controlRate.getChildren().addAll(closeButton, messageLabel);	
	}

	public void updateAmount() {
		int sum = 0;
		for (BuyCardContainer card : allBuyCardContainers) {
			card.updateAmount();
			sum += card.getNumber();
		}
		if (sum == 0) {
			reset.setDisable(true);
		} else {
			reset.setDisable(false);
		}
		if (sum == 3) {
			confirm.setDisable(false);
			for (BuyCardContainer card : allBuyCardContainers) {
				card.setDisableIncrease();
			}
		}
	}

	private void resetValue() {
		for (BuyCardContainer card : allBuyCardContainers) {
			card.resetValue();
		}
	}

	private void confirm() {
		for (BuyCardContainer card : allBuyCardContainers) {
			card.confirm();
		}
	}

}
