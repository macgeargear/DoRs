package components;

import java.util.ArrayList;

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
		content.setPrefSize(200, 300);
		content.setSpacing(15);
		content.setAlignment(Pos.CENTER);
		content.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(12), null)));
		
		this.initHeader();
		this.initContent();
		this.centerOnScreen();
		this.initFooter();
		this.getContent().add(content);
	}
	
	private void initHeader() {
		Text header = new Text("Ranking");
		header.setFont(new Font(24));
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
			rank.setSpacing(10);
			
			Font fontSize = new Font(16);
			if(cnt == 1) {
				fontSize = new Font(20);
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
