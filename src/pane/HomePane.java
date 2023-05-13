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
		getChildren().addAll(welcomeText, playButton, amountSelector);
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
//				ControlPane.getInstance().showGameScene();
			}
		});
	}
	
	private void initStartButton() {
		amountSelector = new AmountSelector();
	}
}
