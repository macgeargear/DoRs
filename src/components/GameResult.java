package components;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Pair;
import logic.GamePlay;
import pane.ControlPane;

public class GameResult extends VBox{
	private VBox content;
	public GameResult() {
		setPrefSize(500, 500);
		setAlignment(Pos.CENTER);
		this.initHeader();
		this.initContent();
		this.initFooter();
	}
	
	private void initHeader() {
		Text header = new Text("Ranking");
		header.setFont(new Font(24));
		getChildren().add(header);
	}
	
	public void initContent() {
		content = new VBox();
		content.setAlignment(Pos.CENTER);
		int cnt = 1;
		GamePlay gameInstance = GamePlay.getInstance();
		ArrayList<Pair<String, Integer>> result = gameInstance.getResult();
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
			Text score = new Text(player.getValue().toString());
			score.setFont(fontSize);
			
			cnt++;
			rank.getChildren().addAll(rankNumber, name, score);
			content.getChildren().add(rank);
		}
		getChildren().add(content);
	}
	
	private void initFooter() {
		Button quit = new Button("Quit");
		getChildren().add(quit);
		quit.setOnAction(e->{
			ControlPane.getInstance().showHomeScene();
		});
	}
}
