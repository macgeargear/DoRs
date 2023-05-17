package pane;

import components.AmountSelector;
import components.CustomButton;
import components.Footer;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HomePane extends VBox {

	private Button playButton;
	private Button startButton;
	private Button buyNodeButton;
	private Button buyEdgeButton;
	private HBox amountSelector;
	private Background background;

	public HomePane() {
//		setup pane

		// TODO: Setup Background
		String imagePath = ClassLoader.getSystemResource("images/background.png").toString();
		this.setupBackgroundImage(imagePath);
		this.setBackground(background);

		setAlignment(Pos.CENTER);
		setSpacing(20);

		// setup text
		Text welcomeText = new Text("DoR project");
		welcomeText.setFont(new Font(60));

		this.initPlayButton();
		this.initStartButton();
		StackPane.setMargin(welcomeText, new Insets(80));
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
				ControlPane.getInstance().showGameScene();
			}
		});
	}
	

	private void initStartButton() {
		amountSelector = new AmountSelector();

		startButton = new CustomButton("Start");
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

	private void setupBackgroundImage(String imagePath) {
		Image image = new Image(imagePath);
		// new BackgroundSize(width, height, widthAsPercentage, heightAsPercentage,
		// contain, cover)
		BackgroundSize backgroundSize = new BackgroundSize(1000, 600, true, true, true, false);
		// new BackgroundImage(image, repeatX, repeatY, position, size)
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		// new Background(images...)
		this.background = new Background(backgroundImage);
	}
}
