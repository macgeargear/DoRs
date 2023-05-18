package components.Button;

import config.Config;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class RollDice extends Button {

	private Background bg = new Background(new BackgroundFill(Config.DiceColor, new CornerRadii(16), null));
	private Background bg_hover = new Background(new BackgroundFill(Color.DARKRED, new CornerRadii(16), null));

	public RollDice(String text) {
		super(text);
		this.setPadding(new Insets(30));
		this.setMinHeight(100);
		this.setMinWidth(100);
		this.setFont(Font.font(30));
		this.setBackground(bg);
		
		this.setOnMouseEntered(e -> {
			this.setBackground(this.bg_hover);
			this.setTextFill(Color.WHITE);
		});

		this.setOnMouseExited(e -> {
			this.setBackground(this.bg);
		});
	}
}
