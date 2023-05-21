package pane.popup;

import components.button.FooterButton;
import config.Config;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Popup;
import javafx.util.Duration;
import pane.ControlPane;

public class VideoPathPopup extends Popup {
	private MediaPlayer mediaPlayer;

	public VideoPathPopup(String path) {
		this.initMedia(path);
	}

	public void play() {
		show(ControlPane.getInstance().getStage());
		mediaPlayer.play();
	}

	private void initMedia(String path) {
		Media media = new Media(path);
		mediaPlayer = new MediaPlayer(media);
		MediaView mediaView = new MediaView(mediaPlayer);

		VBox content = new VBox();

		content.setPrefSize(Config.VIDEO_POPUP_WIDTH, Config.VIDEO_POPUP_HEIGHT);
		content.setBackground(new Background(new BackgroundFill(Config.DiceColor, null, null)));

		HBox header = new HBox();
		header.setAlignment(Pos.CENTER_RIGHT);

		FooterButton exitButton = new FooterButton("X");
		header.getChildren().add(exitButton);

		content.getChildren().addAll(header, mediaView);

		getContent().add(content);

		content.setAlignment(Pos.CENTER);

		mediaView.setFitWidth(Config.VIDEO_POPUP_WIDTH);
		mediaView.setFitHeight(Config.VIDEO_POPUP_HEIGHT - 50);

		exitButton.setOnAction(e -> {
			mediaPlayer.seek(Duration.ZERO);
			mediaPlayer.stop();
			this.hide();
		});

		mediaPlayer.setOnEndOfMedia(() -> {
			hide();
		});

	}

}
