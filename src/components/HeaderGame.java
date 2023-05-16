package components;

import config.Config;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GamePlay;

public class HeaderGame extends HBox {
	
	private int roundCount;
	private Text roundAmount;
	
	public HeaderGame() {
		roundCount = GamePlay.getInstance().getCurrentRound();

		setAlignment(Pos.CENTER);
		setSpacing(20);
		setPrefSize(Config.SCREEN_WIDTH, 30);
		setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY)));
		initRoundText();
		initExitButton();
	}
	
	private void initRoundText() {
		HBox roundDisplay = new HBox();
		roundDisplay.setAlignment(Pos.CENTER);
		Text roundText = new Text("Round : ");
		roundAmount = new Text(Integer.toString(roundCount));
		roundDisplay.getChildren().addAll(roundText, roundAmount);
		getChildren().add(roundDisplay);
	}
	
	private void initExitButton() {
		Button exitButton = new Button();
		exitButton.setText("X");
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
			}
		});
		
		getChildren().add(exitButton);
	}
	
	public void increaseRoundCount() {
		roundCount = GamePlay.getInstance().getCurrentRound();
		roundAmount.setText(Integer.toString(roundCount));
	}
}
