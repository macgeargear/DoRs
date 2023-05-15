package components;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import logic.GamePlay;

public class HeaderGame extends HBox {
	
	private int roundCount;
	private Text roundAmount;
	
	public HeaderGame() {
		roundCount = GamePlay.getInstance().getCurrentRound();

		setAlignment(Pos.CENTER);
		setSpacing(20);
		initRoundText();
	}
	
	private void initRoundText() {
		HBox roundDisplay = new HBox();
		Text roundText = new Text("Round : ");
		roundAmount = new Text(Integer.toString(roundCount));
		roundDisplay.getChildren().addAll(roundText, roundAmount);
		getChildren().add(roundDisplay);
	}
	
	private void initExitButton() {
		
	}
	
	public void increaseRoundCount() {
		roundCount = GamePlay.getInstance().getCurrentRound();
		roundAmount.setText(Integer.toString(roundCount));
	}
}
