package pane.popup;

import java.util.ArrayList;
import java.util.Arrays;

import buildings.Place;
import card.EffectCard;
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
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import logic.Player;
import pane.ControlPane;
import type.CardType;
import utils.Utilities;

public class EffectCardPopup extends Popup {
	private VBox popupContent;
	private Button closeButton;
	private ArrayList<CardType> allEffects;
	private ArrayList<Label> allLabels;
	private ArrayList<Button> allUseEffectButton;

	public EffectCardPopup() {
		this.allUseEffectButton = new ArrayList<Button>();
		this.allLabels = new ArrayList<Label>();
		this.centerOnScreen();
		this.initContent();
		this.updateLabel();

		this.getContent().add(popupContent);
	}

	private void initContent() {
		Label messageLabel = new Label("This is Yout Effect Cards");
		messageLabel.setFont(Font.font(Config.LARGE_FONT));
		this.allEffects = new ArrayList<CardType>(Arrays.asList(CardType.BOMB, CardType.NUCLEAR, CardType.STRONGER));

		this.popupContent = new VBox();
//		this.popupContent.setPrefHeight(768);
		this.popupContent.setPrefWidth(Config.EFFECTCARD_POPUP_WIDTH);
		this.popupContent.setBackground(
				new Background(new BackgroundFill(Color.WHITE, new CornerRadii(Config.BORDER_RADIUS), null)));
		this.popupContent.setAlignment(Pos.CENTER);

		this.closeButton = new ExitButton("X");
		this.closeButton.setAlignment(Pos.TOP_LEFT);
		this.closeButton.setOnAction(e -> {
			this.hide();
		});
		this.popupContent.getChildren().addAll(closeButton, messageLabel);
		for (CardType effect : this.allEffects) {
			Label newLabel = new Label(" " + effect);
			Button useEffectButton = new FooterButton("Use");
			HBox.setMargin(useEffectButton, new Insets(0, 0, 0, 2 * Config.LARGE_MARGIN));
			BorderPane card = initCard(newLabel, effect, useEffectButton);
			newLabel.setFont(Font.font(Config.MEDIUM_FONT));
			allLabels.add(newLabel);
			allUseEffectButton.add(useEffectButton);

			useEffectButton.setOnAction(e -> {
				ControlPane paneInstance = ControlPane.getInstance();
				Player currentPlayer = Utilities.getCurrentPlayer();
				EffectCard effectCard = currentPlayer.getFirstEffectCardType(effect);
				Place place = Utilities.getSelectPlace();
				effectCard.play(place);

				if (effect != CardType.STRONGER) {
					if (paneInstance.getSelectEdge() != null) {
						paneInstance.getSelectEdge().setDisable(true);
						paneInstance.getSelectEdge().setupSyle();
					} else if (paneInstance.getSelectNode() != null) {
						paneInstance.getSelectNode().setDisable(true);
						paneInstance.getSelectNode().setupSyle();
					} else if (paneInstance.getSelectMap() != null) {
						paneInstance.getSelectMap().setDisable(true);
					}
				} else {
					if (paneInstance.getSelectEdge() != null) {
						paneInstance.getSelectEdge().setupSyle();
					} else if (paneInstance.getSelectNode() != null) {
						paneInstance.getSelectNode().setupSyle();
					}
				}
				useEffectButton.setDisable(true);
				paneInstance.resetSelect();
				Utilities.updateCard();
				if (effect == CardType.NUCLEAR) {
					new VideoPathPopup(Config.ATOMIC);
				} else if (effect == CardType.BOMB) {
					new VideoPathPopup(Config.EXPLOSION);
				}
			});

			this.popupContent.getChildren().add(card);
		}

		VBox.setMargin(closeButton, new Insets(Config.MEDIUM_MARGIN));
	}

	private BorderPane initCard(Label amount, CardType type, Button button) {
		BorderPane card = new BorderPane();
		card.setPadding(new Insets(Config.SMALL_PADDING));
		card.setBackground(
				new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(Config.BORDER_RADIUS), null)));
//		card.setAlignment(Pos.CENTER);
		button.setDisable(true);
		card.setPrefWidth(Config.CARD_SIZE);
		card.setPrefHeight(Config.CARD_SIZE);

		HBox titleCard = new HBox();
		Text typeLabel = new Text(type.toString());

		VBox.setMargin(card, new Insets(Config.MEDIUM_MARGIN));
		VBox.setMargin(titleCard, new Insets(Config.MEDIUM_MARGIN));
		typeLabel.setTextAlignment(TextAlignment.CENTER);
		typeLabel.setFont(Font.font(Config.MEDIUM_FONT));

		titleCard.getChildren().addAll(typeLabel, amount, button);
		card.setLeft(typeLabel);
		BorderPane.setAlignment(typeLabel, Pos.CENTER_LEFT);
		card.setCenter(amount);
		card.setRight(button);
//		card.getChildren().addAll(titleCard);
		return card;
	}

	public void updateLabel() {
		int idx = 0;
		for (CardType effect : this.allEffects) {
			allLabels.get(idx).setText(" " + Utilities.countEffectCard(effect));
			allUseEffectButton.get(idx).setDisable(Utilities.getCurrentPlayer().getFirstEffectCardType(effect) == null
					|| !Utilities.getCurrentPlayer().getFirstEffectCardType(effect).canPlay(Utilities.getSelectPlace()));
			idx++;
		}
	}
}
