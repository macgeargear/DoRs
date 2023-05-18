package components.Material;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MaterialCard extends VBox {
	private Text label;

	public MaterialCard(Color color, String text) {
		this.setPrefHeight(120);
		this.setPrefWidth(180);
		this.setBackground(new Background(new BackgroundFill(color,new CornerRadii(10),null)));
//		this.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.3), 8, 0, 0, 0);");
		this.setPadding(new Insets(16));
		
		this.initLabel(text);
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
		this.label.setFont(Font.font(20));
	}
	
}
