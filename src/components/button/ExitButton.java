package components.button;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ExitButton extends Button {
	private Background bg = new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(12), null));
	private Background bg_hover = new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(12), null));

	public ExitButton(String text) {
		// TODO Auto-generated constructor stub
		super(text);
		this.setBackground(bg);
		this.setPadding(new Insets(14));
		this.setOnMouseEntered(e -> {
			this.setBackground(this.bg_hover);
		});

		this.setOnMouseExited(e -> {
			this.setBackground(this.bg);
		});
	}

}
