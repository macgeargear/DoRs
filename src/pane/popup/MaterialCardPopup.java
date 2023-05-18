package pane.popup;

import java.util.ArrayList;

import org.w3c.dom.Text;

import components.Button.ExitButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import material.Material;
import material.MaterialPack;
import utils.Utilities;

public class MaterialCardPopup extends Popup {
	private VBox popupContent;
	private Button closeButton;
	private ArrayList<MaterialPack> allMaterials;
	private ArrayList<Label> allLabels;
	
	public MaterialCardPopup() {
		this.allLabels = new ArrayList<Label>();
		this.centerOnScreen();
		this.initContent();

		this.getContent().add(popupContent);
	}

	private void initContent() {
		Label messageLabel = new Label("This is Yout Material Cards");
		messageLabel.setFont(Font.font(32));
		this.allMaterials = Utilities.getCurrentPlayer().getAllMaterials();

		this.popupContent = new VBox();
		this.popupContent.setPrefHeight(768);
		this.popupContent.setPrefWidth(600);
		this.popupContent.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(14), null)));
		this.popupContent.setAlignment(Pos.CENTER);

		this.closeButton = new ExitButton("X");
		this.closeButton.setAlignment(Pos.TOP_LEFT);
		this.closeButton.setOnAction(e -> {
			this.hide();
		});
		this.popupContent.getChildren().addAll(closeButton, messageLabel);
		for (MaterialPack material : this.allMaterials) {
			Label newLabel = new Label("" + material.getAmount());
			VBox card = initCard(newLabel, material.getType());
			allLabels.add(newLabel);
//			System.out.println("" + material.getAmount() + " " + material.getType().getType());
			this.popupContent.getChildren().add(card);
		}

		VBox.setMargin(closeButton, new Insets(24));
	}

	private VBox initCard(Label amount, Material type) {
		VBox card = new VBox();
			card.setPrefSize(100, 100);
			card.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(12), null)));
		VBox.setMargin(card, new Insets(24));
		HBox titleCard = new HBox();
		Label typeLabel = new Label(type.getType().toString());
		titleCard.getChildren().addAll(typeLabel, amount);

		card.getChildren().addAll(titleCard);
		return card;
	}
	
	public void updateLabel() {
		int idx = 0;
		this.allMaterials = Utilities.getCurrentPlayer().getAllMaterials();
		for (MaterialPack material : this.allMaterials) {
			allLabels.get(idx).setText("" + material.getAmount());
			idx++;
		}
	}
}
