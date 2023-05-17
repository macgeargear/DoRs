package components;

import java.util.ArrayList;

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
	public GameBoard() {
		setPrefSize(Config.BOARD_WIDTH, Config.BOARD_HEIGH);
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		setHgap(25); // Set horizontal gap between cells
        setVgap(25); // Set vertical gap between cells
        
        ArrayList<Map> allMaps = GamePlay.getInstance().getAllMaps();
        for(int i=0;i<5;++i) {
        	for(int j=0;j<5;++j) {
        		Button map = new MapButton(allMaps.get(i*5 + j));
        		add(map, 2*i+1, 2*j+1);
        	}
        }
	}
}
