package pane.popup;

import java.util.ArrayList;
import components.BuyCardContainer;
import components.button.ExitButton;
import components.button.FooterButton;
import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
	private ArrayList<BuyCardContainer> allBuyCardContainers;
	private Button reset;
	private Button confirm;
	
	public BuyCardPopup() {
		allBuyCardContainers = new ArrayList<BuyCardContainer>();
		this.centerOnScreen();
		this.initContent();
		this.initFooter();

		this.getContent().add(popupContent);
		ControlPane.getInstance().setBuyCardPopup(this);
	}

	private void initFooter() {
		HBox footer = new HBox();
		footer.setAlignment(Pos.CENTER);

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
		});

		HBox.setMargin(confirm, new Insets(Config.SMALL_MARGIN));
		HBox.setMargin(reset, new Insets(Config.SMALL_MARGIN));
		footer.getChildren().addAll(reset, confirm);
		this.popupContent.setBottom(footer);

	}

	private void initContent() {
		ArrayList<MaterialPack> allMaterials = Utilities.getCurrentPlayer().getAllMaterials();

		this.popupContent = new BorderPane();
		this.popupContent.setPadding(new Insets(18));
		this.popupContent.setPrefHeight(400);
		this.popupContent.setPrefWidth(800);
		BorderPane.setAlignment(this.popupContent, Pos.CENTER);
		this.popupContent.setBackground(
				new Background(new BackgroundFill(Color.WHITE, new CornerRadii(Config.BORDER_RADIUS), null)));
		Text header = new Text("Buy Effect Card");
		header.setFont(Font.font(Config.LARGE_FONT));

		HBox controlRate = new HBox();
		controlRate.setPrefHeight(Config.BUYCARD_POPUP_HEIGHT);
		controlRate.setPrefWidth(Config.BUYCARD_POPUP_WIDTH);
		controlRate.setAlignment(Pos.CENTER);

		HBox.setMargin(controlRate, new Insets(Config.MEDIUM_MARGIN));
		controlRate.setPadding(new Insets(Config.LARGE_PADDING));

		Button closeButton = new ExitButton("X");
		closeButton.setOnAction(e -> {
			this.resetValue();
			Utilities.updateCard();
			this.hide();
		});

		for (MaterialPack material : allMaterials) {
			BuyCardContainer card = new BuyCardContainer(material.getType(), material.getAmount());
			this.allBuyCardContainers.add(card);
			HBox.setMargin(card, new Insets(Config.SMALL_MARGIN));
			card.setPadding(new Insets(Config.LARGE_PADDING));
			controlRate.getChildren().add(card);
		}
		
		this.popupContent.setTop(header);
		this.popupContent.setTop(closeButton);

		BorderPane.setAlignment(closeButton, Pos.TOP_LEFT);
		BorderPane.setAlignment(header, Pos.TOP_CENTER);
		
		this.popupContent.setCenter(controlRate);


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
