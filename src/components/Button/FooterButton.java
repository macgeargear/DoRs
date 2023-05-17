package components.Button;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class FooterButton extends Button {
	private Background bg = new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(12), null));
	private Background bg_hover = new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(12), null));

	public FooterButton(String text) {
		super(text);
		this.setBackground(bg);
		this.setPrefHeight(30);
		this.setPadding(new Insets(12));
		this.setLineSpacing(20);
		this.setOnMouseEntered(e -> {
			this.setBackground(this.bg_hover);
		});

		this.setOnMouseExited(e -> {
			this.setBackground(this.bg);
		});
	}

}
