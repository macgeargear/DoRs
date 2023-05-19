package pane.popup;

import java.util.ArrayList;
import components.BuyCardCardContainer;

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
import material.Material;
import material.MaterialPack;
import pane.ControlPane;
import utils.Utilities;

public class BuyCardPopup extends Popup {
	private HBox popupContent;
	private Button closeButton;
	private ArrayList<MaterialPack> allMaterials;
	private ArrayList<Integer> allAmounts;

	public BuyCardPopup() {
		this.centerOnScreen();
		this.initContent();
		
		
		this.getContent().add(popupContent);
		ControlPane.getInstance().setEffectCardPopup(this);
	}
	
	private void initContent() {
		Label messageLabel = new Label("See Your Card");
		messageLabel.setFont(Font.font(32));
		this.allMaterials = Utilities.getCurrentPlayer().getAllMaterials();
		this.allAmounts = new ArrayList<Integer>();
		
		this.popupContent = new HBox();
		this.popupContent.setPrefHeight(500);
		this.popupContent.setPrefWidth(360);
		this.popupContent.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(14), null)));
		this.popupContent.setAlignment(Pos.CENTER);
		
		HBox.setMargin(this.popupContent, new Insets(20));
		this.popupContent.setPadding(new Insets(40));
		
		this.closeButton = new ExitButton("X");
		this.closeButton.setOnAction(e -> {
			this.hide();
		});
//		Utilities.updateCard();
		for (MaterialPack material : this.allMaterials) {
			BuyCardCardContainer card = new BuyCardCardContainer(material.getType().getType(), material.getAmount());
			HBox.setMargin(card, new Insets(20));
			this.popupContent.getChildren().add(card);
		}
		

		this.popupContent.getChildren().addAll(closeButton, messageLabel);	
	}
	
	

	

	
	
	public void updateAmount() {
		int idx = 0;
		this.allMaterials = Utilities.getCurrentPlayer().getAllMaterials();
		for (MaterialPack material : this.allMaterials) {
			this.allAmounts.set(idx, material.getAmount());
			idx++;
		}
	}	
	
	

}
