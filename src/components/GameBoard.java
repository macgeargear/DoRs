package components;

import java.util.ArrayList;

import buildings.Edge;
import buildings.Node;
import config.Config;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import logic.GamePlay;
import material.Map;

public class GameBoard extends GridPane{
	private ArrayList<Button> mapButtons;
	private ArrayList<Button> nodeButtons;
	
	public GameBoard() {
		mapButtons = new ArrayList<Button>();
		nodeButtons = new ArrayList<Button>();
		
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
        		
        		Map map = allMaps.get(i*5 + j);
        		Button mapButton = new MapButton(map);
        		mapButtons.add(mapButton);
        		
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
        		
        		add(mapButton, 2*i+1, 2*j+1);
        	}
        }
	}
}
