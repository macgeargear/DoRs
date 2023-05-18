package pane.popup;

import java.util.ArrayList;

import components.Button.ExitButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import material.MaterialPack;
import utils.Utilities;

public class EffectCardPopup extends Popup {
	private VBox popupContent;
	private Button closeButton;
	private ArrayList<MaterialPack> allMaterials;

	public EffectCardPopup() {
		this.centerOnScreen();
		this.initContent();
		this.allMaterials = Utilities.getCurrentPlayer().getAllMaterials();
		
		this.getContent().add(popupContent);
	}
	
	private void initContent() {
		Label messageLabel = new Label("See Your Card");
		messageLabel.setFont(Font.font(32));
		
		this.popupContent = new VBox();
		this.popupContent.setPrefHeight(500);
		this.popupContent.setPrefWidth(360);
		this.popupContent.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(14), null)));
		this.popupContent.setAlignment(Pos.CENTER);
		
		this.closeButton = new ExitButton("X");
		this.closeButton.setOnAction(e -> {
			this.hide();
		});
		

		this.popupContent.getChildren().addAll(closeButton, messageLabel);	
	}
	

}
