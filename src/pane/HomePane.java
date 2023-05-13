package pane;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HomePane extends VBox {
	private Button playButton;
	public HomePane() {
		setAlignment(Pos.CENTER);
		setSpacing(20);
		Text welcomeText = new Text("DOR project");
		this.initPlayButton();
		getChildren().addAll(welcomeText, playButton);
	}
	
	private void initPlayButton() {
		playButton = new Button();
		playButton.setText("Play");
		
		playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ControlPane.getInstance().showGamePane();
			}
		});
	}
}
