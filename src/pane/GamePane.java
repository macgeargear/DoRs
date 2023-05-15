package pane;

import components.HeaderGame;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GamePane extends VBox {

	private Button exitButton;
	private HeaderGame header;

	public GamePane() {
		header = new HeaderGame();
		
		setAlignment(Pos.CENTER);
		this.initExitButton();
		getChildren().addAll(header, exitButton);
	}

	private void initExitButton() {
		exitButton = new Button();
		exitButton.setText("Exit");
		exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				header.increaseRoundCount();
//				ControlPane.getInstance().showHomeScene();
			}
		});
	}
}
