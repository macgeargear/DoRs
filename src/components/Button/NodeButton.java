package components.Button;

import buildings.Node;
import config.Config;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import logic.GamePlay;
import pane.ControlPane;
import type.BuildingType;
import utils.Utilities;

public class NodeButton extends Button {
	private Node node;

	public NodeButton(Node node) {
		this.node = node;

		setPrefSize(Config.NODE_WIDTH, Config.NODE_HEIGH);
		this.setupSyle();
		this.initShapeFx();
		this.initOnHover();
		this.initOnAction();
	}

	private void initOnAction() {
		NodeButton thisNode = this;
		setOnAction(e -> {
			setScaleX(1.5);
			setScaleY(1.5);
			ControlPane paneInstance = ControlPane.getInstance();

			paneInstance.resetSelect();
			paneInstance.setSelectNode(thisNode);
//			System.out.println(node.getSideEdges());
			if (Utilities.buyNodeCondition(node) == 1) {
				paneInstance.getFooter().setBuyNodeDisable(false);
				paneInstance.getFooter().getBuyNodeButton().setText("Buy Node");
			} else if (Utilities.buyNodeCondition(node) == 2) {
				paneInstance.getFooter().setBuyNodeDisable(false);
				paneInstance.getFooter().getBuyNodeButton().setText("Upgrade Node");
			} else {
				paneInstance.getFooter().setBuyNodeDisable(true);
				paneInstance.getFooter().getBuyNodeButton().setText("Buy Node");
			}
			Utilities.updateCard();
		});
	}

	private void initOnHover() {
		ControlPane paneInstance = ControlPane.getInstance();
		NodeButton thisButton = this;

		setOnMouseEntered(event -> {
			setScaleX(1.5);
			setScaleY(1.5);
		});

		setOnMouseExited(event -> {
			if (paneInstance.getSelectNode() == null || !paneInstance.getSelectNode().equals(thisButton)) {
				setScaleX(1.0);
				setScaleY(1.0);
			}
		});
	}

	public void setupSyle() {
		setStyle("-fx-background-color: " + this.getColor() + ";");
		this.initShapeFx();
	}

	private String getColor() {
		BuildingType type = node.getType();
		if (!node.isActive()) {
			return "#000000";
		}
		if (type == BuildingType.EMPTYHOUSE) {
			return Config.EMPTY;
		}

		GamePlay instance = GamePlay.getInstance();
		int playerIndex = instance.findPlayerIndex(node.getOwner());
		if (playerIndex == 0) {
			return Config.P1;
		} else if (playerIndex == 1) {
			return Config.P2;
		} else if (playerIndex == 2) {
			return Config.P3;
		}
		return Config.P4;
	}

	public void initShapeFx() {
		BuildingType type = node.getType();
		if (type == BuildingType.EMPTYHOUSE || type == BuildingType.HOUSE) {
			setShape(new Circle(15));
		} else if (type == BuildingType.TOWER) {
			setShape(new Polygon(50.0, 0.0, 0.0, 100.0, 100.0, 100.0));
		} else {
			setShape(new Rectangle(100, 100));
		}
	}

	public void resetSize() {
		setScaleX(1.0);
		setScaleY(1.0);
	}

	public Node getNode() {
		return node;
	}

}
