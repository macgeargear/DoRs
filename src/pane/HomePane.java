package pane;

import components.AmountSelector;
import components.Button.CustomButton;
import config.Config;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;

public class HomePane extends VBox {

	private Button playButton;
	private Button startButton;
	private HBox amountSelector;
	private Background background;
	private Button selectChoice;
	
	public HomePane() {
//		setup pane

		// TODO: Setup Background
		String imagePath = ClassLoader.getSystemResource("images/background.png").toString();
		ControlPane.getInstance().setHomePane(this);
		this.setupBackgroundImage(imagePath);
		this.setBackground(background);
		this.selectChoice = null;

		setAlignment(Pos.CENTER);
		setSpacing(20);

		// setup text
		Text welcomeText = new Text("DoR project");
		welcomeText.setFont(new Font(Config.WELCOME_FONT));

		this.initPlayButton();
		this.initStartButton();
		StackPane.setMargin(welcomeText, new Insets(Config.HOME_MARGIN));
		this.getChildren().addAll(welcomeText, playButton, amountSelector, startButton);
	}

	private void initPlayButton() {
		// set style
		playButton = new CustomButton("Select Mode");
		// setSelectPlayerAction
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

		startButton = new CustomButton("Start");
		startButton.setVisible(false);
		startButton.setDisable(true);
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

	private void setupBackgroundImage(String imagePath) {
		Image image = new Image(imagePath);
		BackgroundSize backgroundSize = new BackgroundSize(Config.BACKGROUND_IMAGE_HEIGHT,
				Config.BACKGROUND_IMAGE_WIDTH, true, true, true, false);
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		this.background = new Background(backgroundImage);
	}

	public Button getSelectChoice() {
		return selectChoice;
	}

	public void setSelectChoice(Button selectChoice) {
		this.selectChoice = selectChoice;
	}

	public Button getStartButton() {
		return startButton;
	}
	
	
	
}
