package components;

import buildings.Node;
import config.Color;
import config.Config;
import javafx.scene.control.Button;
import logic.GamePlay;
import type.BuildingType;
import type.MaterialType;

public class NodeButton extends Button {
	private Node node;
	public NodeButton(Node node) {
		this.node = node;

		setPrefSize(Config.MAP_WIDTH, Config.MAP_HEIGH);
		setStyle("-fx-font-size: 22px;" + "-fx-background-radius: 10px;" + "-fx-background-color: #" + this.getColor() + ";");
	}
	
	private Color getColor() {
		BuildingType type = node.getType();
		if(type == BuildingType.EMPTYHOUSE) {
			return Color.EMPTY;
		}
		
		GamePlay instance = GamePlay.getInstance();
		int playerIndex = instance.findPlayerIndex(node.getOwner());
		if(playerIndex == 0) {
			return Color.P1;
		}else if(playerIndex == 1) {
			return Color.P2;
		}else if(playerIndex == 2) {
			return Color.P3;
		}
		return Color.P4;
	}
}
