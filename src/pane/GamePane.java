package pane;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GamePane extends VBox {

	private Button exitButton;
	private VBox game;

	public GamePane() {
		setAlignment(Pos.CENTER);
		Text welcomeText = new Text("Let's Play!");
		this.initExitButton();
		getChildren().addAll(welcomeText, exitButton);
	}

	private void initExitButton() {
		exitButton = new Button();
		exitButton.setText("Play");
	}
}
