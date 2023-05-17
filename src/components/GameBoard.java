package components;

import java.util.ArrayList;

import buildings.Edge;
import buildings.Node;
import config.Config;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.GamePlay;
import material.Map;
import type.BuildingType;

public class GameBoard extends GridPane{
	private ArrayList<Button> mapButtons;
	private ArrayList<Button> nodeButtons;
	private ArrayList<Rectangle> edgeButtons;
	
	public GameBoard() {
		mapButtons = new ArrayList<Button>();
		nodeButtons = new ArrayList<Button>();
		edgeButtons = new ArrayList<Rectangle>();
		
		setPrefSize(Config.BOARD_WIDTH, Config.BOARD_HEIGH);
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
//		setHgap(25); // Set horizontal gap between cells
//        setVgap(25); // Set vertical gap between cells

        GamePlay instance = GamePlay.getInstance();
        ArrayList<Map> allMaps = instance.getAllMaps();
        ArrayList<Node> allNodes = instance.getAllNodes();
        ArrayList<Edge> allEdges = instance.getAllEdges();
        
        for(int i=0;i<5;++i) {
        	for(int j=0;j<5;++j) {
        		
//        		setup map
        		Map map = allMaps.get(i*5 + j);
        		Button mapButton = new MapButton(map);
        		mapButtons.add(mapButton);
        		add(mapButton, 2*i+1, 2*j+1);

//        		setup node
        		Button nodeTopLeft = new NodeButton(map.getSideNodes().get(0));
        		nodeButtons.add(nodeTopLeft);
        		add(nodeTopLeft, 2*i, 2*j);
        		
        		if(j == 4) {
        			Button nodeBotLeft = new NodeButton(map.getSideNodes().get(2));
            		nodeButtons.add(nodeBotLeft);
            		add(nodeBotLeft, 2*i, 2*j+2);	
        		}
        		
        		if(i == 4) {
        			Button nodeTopRight = new NodeButton(map.getSideNodes().get(1));
            		nodeButtons.add(nodeTopRight);
            		add(nodeTopRight, 2*i+2, 2*j);
            		if(j == 4) {
            			Button nodeBotRight = new NodeButton(map.getSideNodes().get(3));
                		nodeButtons.add(nodeBotRight);
                		add(nodeBotRight, 2*i+2, 2*j+2);
            		}
        		}
        		
//        		setup edge
        		Rectangle topEdge = new EdgeButton(map.getSideNodes().get(0).getSideEdges().get(1), 0);
        		edgeButtons.add(topEdge);
        		add(topEdge, 2*i+1, 2*j);
        		
        		Rectangle leftEdge = new EdgeButton(map.getSideNodes().get(0).getSideEdges().get(2), 1);
        		edgeButtons.add(leftEdge);
        		add(leftEdge, 2*i, 2*j+1);
        		
        		if(j == 4) {
        			Rectangle botEdge = new EdgeButton(map.getSideNodes().get(3).getSideEdges().get(3), 0);					
					edgeButtons.add(botEdge);
					add(botEdge, 2*i+1, 2*j+2);
				}
				if(i == 4) {					
					Rectangle rightEdge = new EdgeButton(map.getSideNodes().get(3).getSideEdges().get(0), 1);
					edgeButtons.add(rightEdge);
					add(rightEdge, 2*i+2, 2*j+1);
				}
        		
        	}
        }
        
        for (Rectangle edge : edgeButtons) {
    		setValignment(edge, VPos.CENTER);
    		setHalignment(edge, HPos.CENTER);
		}
	}
}
