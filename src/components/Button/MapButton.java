package components.Button;

import config.Config;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import material.Map;
import type.MaterialType;

public class MapButton extends Button {
	private Map map;
	
	public MapButton(Map map) {
		this.map = map;
		
		setText(Integer.toString(map.getNumber()));
		setPrefSize(Config.MAP_WIDTH, Config.MAP_HEIGH);
		this.setupSyle();
//		setBackground(new Background(new BackgroundFill(this.getColor(), null, null)));
//		this.initOnHover();
	}
	
	private void initOnHover() {
		setOnMouseEntered(event -> {
            setScaleX(1.2);
            setScaleY(1.2);
        });
		
		setOnMouseExited(event -> {
            setScaleX(1.0);
            setScaleY(1.0);
        });
	}
	
	public void setupSyle() {
		setStyle("-fx-font-size: 22px;" + "-fx-background-radius: 10px;" + "-fx-background-color: #" + this.getColor() + ";");
	}
	
	private String getColor() {
		MaterialType type = map.getType().getType();
		if(type == MaterialType.WOOD) {
			return "A4D77B";
		}else if(type == MaterialType.WATER) {
			return "7CD8EC";
		}else if(type == MaterialType.ROCK) {
			return "0E8468";
		}else if(type == MaterialType.SAND) {
			return "E3A8A8";
		}
		return "D9D9D9";
	}
}
