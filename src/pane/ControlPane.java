package pane;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControlPane extends Pane {
	
	private static ControlPane instance = null;
	private GamePane gamePane;
	private HomePane homePane;
	private Stage stage;
	
	public ControlPane(Stage stage) {
		gamePane = new GamePane();
		homePane = new HomePane();
		
		this.stage = stage;
		showHomePane();
		this.stage.show();
	}
	
	public static ControlPane getInstance() {
		return instance;
	}
	
	public static ControlPane getInstance(Stage stage) {
		if(instance == null) {
			instance = new ControlPane(stage);
		}
		return instance;
	}
	
	public void showHomePane() {
		stage.setScene(new Scene(homePane, 1000, 600));
	}
	
	public void showGamePane() {
		stage.setScene(new Scene(gamePane, 1000, 600));
	}
}
