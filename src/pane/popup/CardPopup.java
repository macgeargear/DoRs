package pane.popup;

import javafx.stage.Popup;
import config.Config;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

public class CardPopup extends Popup {
	private Label label;
	private Button showMaterialCardButton;
	private Button showEffectCardButton;

	public CardPopup() {
		this.sizeToScene();
		this.label = new Label("This is your card");
		this.label.setStyle(" -fx-background-color: white;");
		
		this.initShowMaterialButton();
		this.initShowEffectCardButton();
		this.getContent().addAll(label, showMaterialCardButton, showEffectCardButton);
	}
	
	private  void initShowMaterialButton() {
		this.showMaterialCardButton = new Button("Show MaterialCard");
		this.showMaterialCardButton.setBackground(new Background(new BackgroundFill(Config.BackGroundColor, new CornerRadii(12), null)));
	}

	private void initShowEffectCardButton() {
		this.showEffectCardButton = new Button("Show EffectCard");
		this.showEffectCardButton.setBackground(new Background(new BackgroundFill(Config.BackGroundColor, new CornerRadii(12), null)));
	}
}
