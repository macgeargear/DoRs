package components.Material;

import config.Config;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import type.MaterialType;
import utils.Utilities;

public class MaterialCard extends VBox {
	private Text label;

	public MaterialCard(MaterialType material) {
		this.setBackground(new Background(
				new BackgroundFill(Utilities.getColor(material), new CornerRadii(Config.BORDER_RADIUS), null)));
		this.setPadding(new Insets(Config.SMALL_PADDING));

		this.initLabel("" + material);
		this.initHover();
		this.getChildren().add(label);
	}

	private void initHover() {
		this.setOnMouseEntered(e -> {
			this.setScaleX(1.15);
			this.setScaleY(1.15);
		});

		this.setOnMouseExited(e -> {
			this.setScaleX(1.0);
			this.setScaleY(1.0);
		});
	}

	private void initLabel(String text) {
		this.label = new Text(text);
		this.label.setFont(Font.font(Config.MEDIUM_FONT));
	}

	public void setMaterial(MaterialType material) {
		this.setBackground(new Background(
				new BackgroundFill(Utilities.getColor(material), new CornerRadii(Config.BORDER_RADIUS), null)));
		this.label.setText("" + material);
	}

}
