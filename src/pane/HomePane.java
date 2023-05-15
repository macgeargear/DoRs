package pane;

import components.AmountSelector;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HomePane extends VBox {
	
	private Button playButton;
	private Button startButton;
	private HBox amountSelector;
	
	public HomePane() {
//		setup pane
		setAlignment(Pos.CENTER);
		setSpacing(20);
		
//		setup text
		Text welcomeText = new Text("DOR project");
		welcomeText.setFont(new Font(24));
		
		this.initPlayButton();
		this.initStartButton();
		getChildren().addAll(welcomeText, playButton, amountSelector, startButton);
	}
	
	private void initPlayButton() {
		playButton = new Button();
		playButton.setText("Play");
		playButton.setVisible(true);
		playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				playButton.setVisible(false);
				amountSelector.setVisible(true);
				startButton.setVisible(true);
//				ControlPane.getInstance().showGameScene();
			}
		});
	}
	
	private void initStartButton() {
		amountSelector = new AmountSelector();
		
		startButton = new Button();
		startButton.setText("Start");
		startButton.setVisible(false);
		startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				startButton.setVisible(false);
				amountSelector.setVisible(false);
				playButton.setVisible(true);
				ControlPane.getInstance().showGameScene();
			}
		});
	}
}
