package pane.popup;

import java.io.File;

import config.Config;
import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Popup;
import pane.ControlPane;

public class VideoPathPopup extends Popup {
	private String path;
	private MediaView mediaView;
	private MediaPlayer mediaPlayer;
	private VBox content;
	private StackPane video;
	
	public VideoPathPopup(String path) {
		this.path = path;
		this.initMedia();
		show(ControlPane.getInstance().getStage());
		
		mediaPlayer.play();
		mediaPlayer.setOnEndOfMedia(()->{
			hide();
		});
	}
	
	private void initMedia() {
		Media media = new Media(path);
		mediaPlayer = new MediaPlayer(media);
		mediaView = new MediaView(mediaPlayer);
		
		content = new VBox();
		video = new StackPane();

		content.setPrefSize(Config.VIDEO_POPUP_WIDTH, Config.VIDEO_POPUP_HEIGHT);
		content.setBackground(new Background(new BackgroundFill(Config.DiceColor, null, null)));
		
		HBox header = new HBox();
		header.setAlignment(Pos.CENTER_RIGHT);
		
		Button exitButton = new Button("X");
		header.getChildren().add(exitButton);
		
		video.getChildren().add(mediaView);
		
		content.getChildren().addAll(header, video);
		
		getContent().add(content);
		
		mediaView.setFitWidth(Config.VIDEO_POPUP_WIDTH);
		mediaView.setFitHeight(Config.VIDEO_POPUP_HEIGHT-50);
		
		exitButton.setOnAction(e->{
			mediaPlayer.stop();
			this.hide();
		});
		
	}
	
}
