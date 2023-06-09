package components;

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
		setStyle("-fx-font-size: 22px;" + "-fx-background-radius: 10px;" + "-fx-background-color: #" + this.getColor() + ";");
//		setBackground(new Background(new BackgroundFill(this.getColor(), null, null)));
	}
	
	private String getColor() {
		MaterialType type = map.getType().getType();
		if(type == MaterialType.WOOD) {
			return "90ee90ff";
		}else if(type == MaterialType.WATER) {
			return "0000ffff";
		}else if(type == MaterialType.ROCK) {
			return "808080ff";
		}else if(type == MaterialType.SAND) {
			return "ffffe0ff";
		}
		return "ffa500ff";
	}
}
