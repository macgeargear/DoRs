package pane.popup;

import java.util.ArrayList;
import java.util.Arrays;

import card.EffectCard;
import components.Button.ExitButton;
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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import material.Material;
import material.MaterialPack;
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
		messageLabel.setFont(Font.font(32));
		this.allEffects = new ArrayList<CardType>(Arrays.asList(CardType.BOMB, CardType.NUCLEAR, CardType.STRONGER));
		
		this.popupContent = new VBox();
		this.popupContent.setPrefHeight(768);
		this.popupContent.setPrefWidth(400);
		this.popupContent.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(14), null)));
		this.popupContent.setAlignment(Pos.CENTER);

		this.closeButton = new ExitButton("X");
		this.closeButton.setAlignment(Pos.TOP_LEFT);
		this.closeButton.setOnAction(e -> {
			this.hide();
		});
		this.popupContent.getChildren().addAll(closeButton, messageLabel);
		for (CardType effect : this.allEffects) {
			Label newLabel = new Label(" " + effect);
			Button useEffectButton = new Button("Use");
			newLabel.setFont(Font.font(20));
			VBox card = initCard(newLabel, effect, useEffectButton);
			allLabels.add(newLabel);
			allUseEffectButton.add(useEffectButton);
			this.popupContent.getChildren().add(card);
		}

		VBox.setMargin(closeButton, new Insets(24));
	}

	private VBox initCard(Label amount, CardType type, Button button) {
		VBox card = new VBox();
		
		card.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(12), null)));
		card.setAlignment(Pos.CENTER);
		card.setPrefWidth(60);
		card.setPrefHeight(60);
		
		HBox titleCard = new HBox();
		Label typeLabel = new Label(type.toString());

		VBox.setMargin(card, new Insets(24));
		VBox.setMargin(titleCard, new Insets(24));
		
		button.setDisable(true);
		typeLabel.setAlignment(Pos.CENTER);
		typeLabel.setFont(Font.font(20));

		titleCard.getChildren().addAll(typeLabel, amount, button);
			
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
