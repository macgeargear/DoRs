package pane;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GamePane extends VBox {

	private Button exitButton;

	public GamePane() {
		setAlignment(Pos.CENTER);
		Text welcomeText = new Text("Let's Play!");
		this.initExitButton();
		getChildren().addAll(welcomeText, exitButton);
	}

	private void initExitButton() {
		exitButton = new Button();
		exitButton.setText("Exit");
		exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				ControlPane.getInstance().showHomeScene();
			}
		});
	}
}
