package components.Button;

import buildings.Node;
import config.Config;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import logic.GamePlay;
import type.BuildingType;

public class NodeButton extends Button {
	private Node node;
	public NodeButton(Node node) {
		this.node = node;

		setPrefSize(Config.NODE_WIDTH, Config.NODE_HEIGH);
		this.setupSyle();
		this.initShapeFx();
		this.initOnHover();
	}
	
	private void initOnHover() {
		setOnMouseEntered(event -> {
            setScaleX(1.5);
            setScaleY(1.5);
        });
		
		setOnMouseExited(event -> {
            setScaleX(1.0);
            setScaleY(1.0);
        });
	}
	
	public void setupSyle() {
		setStyle("-fx-background-color: " + this.getColor() + ";");
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
	
	public void initShapeFx() {
		BuildingType type = node.getType();
		if(type == BuildingType.EMPTYHOUSE || type == BuildingType.HOUSE) {
			setShape(new Circle(15));
		}else if(type == BuildingType.TOWER) {
			setShape(new Polygon(
	                15.0, 0.0,
	                0.0, 30.0,
	                30.0, 30.0
	        ));
		}else {
			setShape(new Rectangle(100, 100));
		}
	}
}
