package components.Button;

import buildings.Edge;
import config.Config;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.GamePlay;
import pane.ControlPane;
import type.BuildingType;
import utils.Utilities;

public class EdgeButton extends Rectangle {
	private Edge edge;
	private int axis;

	public EdgeButton(Edge edge, int axis) {
		this.edge = edge;
		this.axis = axis;

		this.setupSyle();
		this.initShapeFx();
		this.initOnHover();
		this.initOnAction();
	}

	private void initOnAction() {
		EdgeButton thisEdge = this;
		setOnMouseClicked(e -> {
			if (axis == 1) {
				setScaleX(1.5);
			} else {
				setScaleY(1.5);
			}

			ControlPane paneInstance = ControlPane.getInstance();

			paneInstance.resetSelect();
			paneInstance.setSelectEdge(thisEdge);
//			System.out.println(edge.getStartNode() +  " " + edge.getEndNode());
			if (Utilities.buyEdgeCondition(edge) == 1) {
				paneInstance.getFooter().setBuyEdgeDisable(false);
				paneInstance.getFooter().getBuyEdgeButton().setText("Buy Edge");
			} else if (Utilities.buyEdgeCondition(edge) == 2) {
				paneInstance.getFooter().setBuyEdgeDisable(false);
				paneInstance.getFooter().getBuyEdgeButton().setText("Upgrade Edge");
			} else {
				paneInstance.getFooter().setBuyEdgeDisable(true);
				paneInstance.getFooter().getBuyEdgeButton().setText("Buy Edge");
			}
			Utilities.updateCard();
		});
	}

	private void initOnHover() {
		ControlPane paneInstance = ControlPane.getInstance();
		EdgeButton thisEdge = this;

		setOnMouseEntered(event -> {
			if (axis == 1) {
				setScaleX(1.5);
			} else {
				setScaleY(1.5);
			}
		});

		setOnMouseExited(event -> {
			if (paneInstance.getSelectEdge() == null || !paneInstance.getSelectEdge().equals(thisEdge)) {
				setScaleX(1.0);
				setScaleY(1.0);
			}
		});
	}

	public void setupSyle() {
		setFill(Color.web(this.getColor()));
		if (edge.getType() == BuildingType.SUPERROAD) {
			setStrokeWidth(2);
			setStroke(Color.BLACK);
		} else {
			setStroke(null);
		}
	}

	private String getColor() {
		BuildingType type = edge.getType();
		if (!edge.isActive()) {
			return "black";
		}
		if (type == BuildingType.EMPTYROAD) {
			return Config.EMPTY;
		}

		GamePlay instance = GamePlay.getInstance();
		int playerIndex = instance.getPlayerIndex(edge.getOwner());
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
		if (axis == 0) {
			setWidth(Config.MAP_WIDTH);
			setHeight(5);
//			setPrefSize(Config.MAP_WIDTH, 5);			
		} else {
			setWidth(5);
			setHeight(Config.MAP_HEIGH);
//			setPrefSize(5, Config.MAP_HEIGH);
		}
//		setShape(new Rectangle(Config.NODE_WIDTH, Config.NODE_HEIGH));
	}

	public void resetSize() {
		setScaleX(1.0);
		setScaleY(1.0);
	}

	public Edge getEdge() {
		return edge;
	}

}
