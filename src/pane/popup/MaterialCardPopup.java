package pane.popup;

import java.util.ArrayList;

import components.button.ExitButton;
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
import material.MaterialPack;
import type.MaterialType;
import utils.Utilities;

public class MaterialCardPopup extends Popup {
	private VBox popupContent;
	private ArrayList<MaterialPack> allMaterials;
	private ArrayList<Label> allLabels;

	public MaterialCardPopup() {
		this.allLabels = new ArrayList<Label>();
		this.centerOnScreen();
		this.initContent();

		this.getContent().add(popupContent);
	}

	private void initContent() {
		Label messageLabel = new Label("This is Your Material Cards");
		messageLabel.setFont(Font.font(32));
		this.allMaterials = Utilities.getCurrentPlayer().getAllMaterials();

		this.popupContent = new VBox();
		this.popupContent.setPrefHeight(768);
		this.popupContent.setPrefWidth(400);
		this.popupContent.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(14), null)));
		this.popupContent.setAlignment(Pos.CENTER);

		Button closeButton = new ExitButton("X");
		closeButton.setAlignment(Pos.TOP_LEFT);
		closeButton.setOnAction(e -> {
			this.hide();
		});
		this.popupContent.getChildren().addAll(closeButton, messageLabel);
		for (MaterialPack material : this.allMaterials) {
			Label newLabel = new Label(" " + material.getAmount());
			newLabel.setFont(Font.font(20));
			VBox card = initCard(newLabel, material.getType());
			allLabels.add(newLabel);
			this.popupContent.getChildren().add(card);
		}

		VBox.setMargin(closeButton, new Insets(24));
	}

	private VBox initCard(Label amount, MaterialType type) {
		VBox card = new VBox();
		Paint color = Utilities.getColor(type);

		card.setBackground(new Background(new BackgroundFill(color, new CornerRadii(12), null)));
		card.setAlignment(Pos.CENTER);
		card.setPrefWidth(60);
		card.setPrefHeight(60);

		HBox titleCard = new HBox();
		Label typeLabel = new Label(type.toString());

		VBox.setMargin(card, new Insets(24));
		VBox.setMargin(titleCard, new Insets(24));

		typeLabel.setAlignment(Pos.CENTER);
		typeLabel.setFont(Font.font(20));

		titleCard.getChildren().addAll(typeLabel, amount);

		card.getChildren().addAll(titleCard);
		return card;
	}

	public void updateLabel() {
		int idx = 0;
		this.allMaterials = Utilities.getCurrentPlayer().getAllMaterials();
		for (MaterialPack material : this.allMaterials) {
			allLabels.get(idx).setText(" " + material.getAmount());
			idx++;
		}
	}
}
