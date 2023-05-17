package components;

import config.Config;import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.Player;

public class PlayerInfo extends BorderPane {
	
	private Player p1;
	private Player p2;
	
	public PlayerInfo(int isLeft, Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		
		setPrefSize(Config.SIDE_BOARD_WIDTH, Config.SIDE_BOARD_HEIGH);
		setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
//		setFillWidth(true);
		setPadding(new Insets(20));
		initTop();
		initBottom();
	}
	
	private void initTop() {
		HBox top = new HBox();
		top.setPrefWidth(Config.SIDE_BOARD_WIDTH);
		
		Text name = new Text(p1.getName());
		top.getChildren().add(name);
		
		setTop(top);
//		getChildren().add(top);
	}
	
	private void initBottom() {
		if(p2 == null) return ;
		HBox bottom = new HBox();
		bottom.setPrefWidth(Config.SIDE_BOARD_WIDTH);
		
		Text name = new Text(p1.getName());
		bottom.getChildren().add(name);
		
		setBottom(bottom);
//		getChildren().add(bottom);
	}
}
