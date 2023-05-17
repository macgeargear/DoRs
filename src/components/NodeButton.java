package components;

import buildings.Node;
import config.Config;
import javafx.scene.control.Button;
import logic.GamePlay;
import type.BuildingType;

public class NodeButton extends Button {
	private Node node;
	public NodeButton(Node node) {
		this.node = node;

		setPrefSize(Config.NODE_WIDTH, Config.NODE_HEIGH);
		this.setupSyle();
	}
	
	public void setupSyle() {
		setStyle("-fx-font-size: 22px;" + "-fx-background-radius: 10px;" + "-fx-background-color: " + this.getColor() + ";");
	}
	
	private String getColor() {
		BuildingType type = node.getType();
		if(type == BuildingType.EMPTYHOUSE) {
			return Config.EMPTY;
		}
		
		GamePlay instance = GamePlay.getInstance();
		int playerIndex = instance.findPlayerIndex(node.getOwner());
		if(playerIndex == 0) {
			return Config.P1;
		}else if(playerIndex == 1) {
			return Config.P2;
		}else if(playerIndex == 2) {
			return Config.P3;
		}
		return Config.P4;
	}
}
