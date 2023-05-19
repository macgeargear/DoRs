package components.Button;

import config.Config;
import javafx.scene.control.Button;
import material.Map;
import pane.ControlPane;
import type.MaterialType;
import utils.Utilities;

public class MapButton extends Button {
	private Map map;
	
	public MapButton(Map map) {
		this.map = map;
		
		setText(Integer.toString(map.getNumber()));
		setPrefSize(Config.MAP_WIDTH, Config.MAP_HEIGH);
		this.setupSyle();
//		setBackground(new Background(new BackgroundFill(this.getColor(), null, null)));
		this.initOnHover();
		this.initOnAction();
	}
	
	private void initOnAction() {
		MapButton thisMap = this;
		setOnAction(e -> {
			setScaleX(1.2);
			setScaleY(1.2);
			ControlPane paneInstance = ControlPane.getInstance();
			
			paneInstance.resetSelect();
			paneInstance.setSelectMap(thisMap);
			Utilities.updateCard();
		});
	}
	
	private void initOnHover() {
		ControlPane paneInstance = ControlPane.getInstance();
		MapButton thisButton = this;
		
		setOnMouseEntered(event -> {
            setScaleX(1.2);
            setScaleY(1.2);
        });
		
		setOnMouseExited(event -> {
			if(paneInstance.getSelectMap() == null || !paneInstance.getSelectMap().equals(thisButton)) {
				setScaleX(1.0);
				setScaleY(1.0);	
			}
        });
	}
	
	public void setupSyle() {
		setStyle("-fx-font-size: 22px;" + "-fx-background-radius: 10px;" + "-fx-background-color: " + this.getColor() + ";");
	}
	
	private String getColor() {
		MaterialType type = map.getType().getType();
		if(type == MaterialType.WOOD) {
			return Config.WOOD;
		}else if(type == MaterialType.WATER) {
			return Config.WATER;
		}else if(type == MaterialType.ROCK) {
			return Config.ROCK;
		}else if(type == MaterialType.SAND) {
			return Config.SAND;
		}
		return Config.GUNPOWDER;
	}
	
	public Map getMap() {
		return map;
	}

	public void resetSize() {
		setScaleX(1.0);
        setScaleY(1.0);
	}
}
