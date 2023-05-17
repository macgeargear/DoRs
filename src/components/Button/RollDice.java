package components.Button;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class RollDice extends Button {

	private Background bg = new Background(new BackgroundFill(Color.RED, new CornerRadii(12), null));
	private Background bg_hover = new Background(new BackgroundFill(Color.DARKRED, new CornerRadii(12), null));

	public RollDice(String text) {
		super(text);
		
		this.setShape(new Circle(50));
		this.setPadding(new Insets(30));
		this.setFont(Font.font(24));
		
		this.setOnMouseEntered(e -> {
			this.setBackground(this.bg_hover);
			this.setTextFill(Color.WHITE);
		});

		this.setOnMouseExited(e -> {
			this.setBackground(this.bg);
		});
	}
}
