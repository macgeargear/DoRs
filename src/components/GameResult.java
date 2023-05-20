package components;

import java.util.ArrayList;

import config.Config;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.util.Pair;
import logic.GamePlay;
import pane.ControlPane;

public class GameResult extends Popup{
	
	private VBox content;
	public GameResult() {
		content = new VBox();
		content.setPrefSize(Config.GAMERESULT_WIDTH, Config.GAMERESULT_HEIGHT);
		content.setSpacing(Config.SMALL_LINE_SPACING);
		content.setAlignment(Pos.CENTER);
		content.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(Config.BORDER_RADIUS), null)));
		
		this.initHeader();
		this.initContent();
		this.centerOnScreen();
		this.initFooter();
		this.getContent().add(content);
	}
	
	private void initHeader() {
		Text header = new Text("Ranking");
		header.setFont(new Font(Config.MEDIUM_FONT));
		content.getChildren().add(header);
	}
	
	public void initContent() {
		content.setAlignment(Pos.CENTER);
		int cnt = 1;
		GamePlay gameInstance = GamePlay.getInstance();
		ArrayList<Pair<String, Integer>> result = gameInstance.getResult();
		System.out.println(result);
		for(Pair<String, Integer> player: result) {
			HBox rank = new HBox();
			rank.setAlignment(Pos.CENTER);
			rank.setSpacing(Config.SMALL_LINE_SPACING);
			
			Font fontSize = new Font(Config.SMALL_FONT);
			if(cnt == 1) {
				fontSize = new Font(Config.MEDIUM_FONT);
			}
			Text rankNumber = new Text(Integer.toString(cnt));
			rankNumber.setFont(fontSize);
			Text name = new Text(player.getKey().toString());
			name.setFont(fontSize);
			Text score = new Text("" + (-player.getValue().intValue()));
			score.setFont(fontSize);
			
			cnt++;
			rank.getChildren().addAll(rankNumber, name, score);
			content.getChildren().add(rank);
		}
	}
	
	private void initFooter() {
		Button quit = new Button("Quit");
		GameResult thisPopup = this;
		quit.setOnAction(e->{
			ControlPane.getInstance().showHomeScene();
			thisPopup.hide();
		});
		content.getChildren().add(quit);
	}
}
