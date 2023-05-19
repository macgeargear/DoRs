package components;

import config.Config;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import type.MaterialType;
import utils.Utilities;

public class EffectCard extends VBox {
	private MaterialType type;
	private int amount;
	
	private Text titleText;
	private Text amountText;

	public EffectCard(MaterialType type, int amount) {
		this.setAlignment(Pos.CENTER);
		this.setBackground(new Background(new BackgroundFill(Utilities.getColor(type), new CornerRadii(12), null)));

		
	}
	
	private void initTitle() {
		this.titleText = new Text(String.valueOf(type));
		this.titleText.setFont(Font.font(40));
	}
	
	private getColor()
}
