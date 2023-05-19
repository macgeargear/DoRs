package pane.popup;

import java.util.ArrayList;
import java.util.Arrays;

import components.Button.ExitButton;
import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import type.CardType;
import utils.Utilities;

public class EffectCardPopup extends Popup {
	private VBox popupContent;
	private Button closeButton;
	private ArrayList<CardType> allEffects;
	private ArrayList<Label> allLabels;

	public EffectCardPopup() {
		this.allLabels = new ArrayList<Label>();
		this.centerOnScreen();
		this.initContent();
		this.updateLabel();
		
		this.getContent().add(popupContent);
	}

	private void initContent() {
		Label messageLabel = new Label("This is Yout Effect Cards");
		messageLabel.setFont(Font.font(32));
		this.allEffects = new ArrayList<CardType>(Arrays.asList(CardType.BOMB, CardType.NUCLEAR, CardType.STRONGER));
		
		this.popupContent = new VBox();
//		this.popupContent.setPrefHeight(768);
		this.popupContent.setPrefWidth(Config.EFFECTCARD_POPUP_WIDTH);
		this.popupContent.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(Config.BORDER_RADIUS), null)));
		this.popupContent.setAlignment(Pos.CENTER);

		this.closeButton = new ExitButton("X");
		this.closeButton.setAlignment(Pos.TOP_LEFT);
		this.closeButton.setOnAction(e -> {
			this.hide();
		});
		this.popupContent.getChildren().addAll(closeButton, messageLabel);
		for (CardType effect : this.allEffects) {
			Label newLabel = new Label(" " + effect);
			newLabel.setFont(Font.font(Config.MEDIUM_FONT));
			VBox card = initCard(newLabel, effect);
			allLabels.add(newLabel);
			this.popupContent.getChildren().add(card);
		}

		VBox.setMargin(closeButton, new Insets(Config.MEDIUM_MARGIN));
	}

	private VBox initCard(Label amount, CardType type) {
		VBox card = new VBox();
		
		card.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(Config.BORDER_RADIUS), null)));
		card.setAlignment(Pos.CENTER);
		card.setPrefWidth(Config.CARD_SIZE);
		card.setPrefHeight(Config.CARD_SIZE);

		HBox titleCard = new HBox();
		Label typeLabel = new Label(type.toString());

		VBox.setMargin(card, new Insets(Config.MEDIUM_MARGIN));
		VBox.setMargin(titleCard, new Insets(Config.MEDIUM_MARGIN));

		typeLabel.setAlignment(Pos.CENTER);
		typeLabel.setFont(Font.font(Config.MEDIUM_FONT));

		titleCard.getChildren().addAll(typeLabel, amount);

		card.getChildren().addAll(titleCard);
		return card;
	}

	public void updateLabel() {
		int idx = 0;
		for (CardType effect : this.allEffects) {
			allLabels.get(idx).setText(" " + Utilities.countEffectCard(effect));
			idx++;
		}
	}
}
