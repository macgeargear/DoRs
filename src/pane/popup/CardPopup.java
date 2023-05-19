package pane.popup;

import javafx.stage.Popup;
import pane.ControlPane;
import components.Button.ExitButton;
import components.Button.FooterButton;
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

public class CardPopup extends Popup {
	private Label label;
	private Button showMaterialCardButton;
	private Button showEffectCardButton;
	private VBox popupContent;
	private MaterialCardPopup showMaterialCard;
	private EffectCardPopup showEffectCard;
	private Button closeButton;

	public CardPopup() {
		this.centerOnScreen();
		this.initShowEffectCardButton();
		this.initShowMaterialButton();
		this.initContent();
		this.getPopupContent().getChildren().addAll(this.showEffectCardButton, this.showMaterialCardButton);

		this.getContent().add(popupContent);
		ControlPane.getInstance().setCardPopup(this);
	}

	private void initShowMaterialButton() {
		this.showMaterialCardButton = new FooterButton("Show MaterialCard", Color.BROWN, Config.FooterColor);
		this.showMaterialCard = new MaterialCardPopup();
		this.showMaterialCardButton.setOnAction(e -> {
			if (this.showMaterialCard == null) {
				this.showMaterialCard.show(ControlPane.getInstance().getStage());
			} else {
				if (this.showMaterialCard.isShowing()) {
					this.showMaterialCard.hide();
				} else {
					this.showMaterialCard.show(ControlPane.getInstance().getStage());
				}
			}
		});
	}

	private void initShowEffectCardButton() {
		this.showEffectCardButton = new FooterButton("Show EffectCard", Color.GOLD, Config.FooterColor);
		this.showEffectCard = new EffectCardPopup();
		this.showEffectCardButton.setOnAction(e -> {
			if (this.showEffectCard == null) {
				this.showEffectCard.show(ControlPane.getInstance().getStage());
			} else {
				if (this.showEffectCard.isShowing()) {
					this.showEffectCard.hide();
				} else {
					this.showEffectCard.show(ControlPane.getInstance().getStage());
				}
			}
		});
	}

	private void initContent() {
		Label messageLabel = new Label("See Your Card");
		messageLabel.setFont(Font.font(32));

		this.popupContent = new VBox(10);
		this.popupContent.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(14), null)));
		this.popupContent.setPrefHeight(200);
		this.popupContent.setPrefWidth(400);
		this.popupContent.setAlignment(Pos.CENTER);

		this.closeButton = new ExitButton("X");
		this.closeButton.setOnAction(e -> {
			this.hide();
		});

		HBox title = new HBox();
		this.closeButton.setAlignment(Pos.TOP_CENTER);

		HBox.setMargin(messageLabel, new Insets(0, 0, 0, this.popupContent.getPrefWidth() / 6));
		VBox.setMargin(title, new Insets(12));
		VBox.setMargin(closeButton, new Insets(12));
		VBox.setMargin(this.showEffectCardButton, new Insets(12));
		VBox.setMargin(this.showMaterialCardButton, new Insets(12));

		title.getChildren().addAll(closeButton, messageLabel);
		this.popupContent.getChildren().addAll(title);
	}

	public Label getLabel() {
		return label;
	}

	public Button getShowMaterialCardButton() {
		return showMaterialCardButton;
	}

	public Button getShowEffectCardButton() {
		return showEffectCardButton;
	}

	public VBox getPopupContent() {
		return popupContent;
	}

	public MaterialCardPopup getShowMaterialCard() {
		return showMaterialCard;
	}

	public void setShowMaterialCard(MaterialCardPopup showMaterialCard) {
		this.showMaterialCard = showMaterialCard;
	}


}
