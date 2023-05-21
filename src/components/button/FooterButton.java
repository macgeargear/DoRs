package components.button;

import config.Config;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class FooterButton extends Button {
	private Background bg = new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(12), null));
	private Background bg_hover = new Background(
			new BackgroundFill(Color.DEEPPINK, new CornerRadii(Config.BORDER_RADIUS), null));

	public FooterButton(String text, Paint bg, Paint bg_hover) {
		super(text);
		this.bg = new Background(new BackgroundFill(bg, new CornerRadii(Config.BORDER_RADIUS), null));
		this.bg_hover = new Background(new BackgroundFill(bg_hover, new CornerRadii(Config.BORDER_RADIUS), null));
		this.setBackground(this.bg);
		this.setPrefHeight(Config.BUTTON_HEIGHT);
		this.setPadding(new Insets(Config.SMALL_PADDING));
		this.setOnMouseEntered(e -> {
			this.setBackground(this.bg_hover);
		});

		this.setOnMouseExited(e -> {
			this.setBackground(this.bg);
		});

	}

	public FooterButton(String text) {
		super(text);
		this.setBackground(bg);
		this.setPrefHeight(Config.BUTTON_HEIGHT);
		this.setPadding(new Insets(Config.SMALL_PADDING));
		this.setOnMouseEntered(e -> {
			this.setBackground(this.bg_hover);
		});

		this.setOnMouseExited(e -> {
			this.setBackground(this.bg);
		});
	}

}
