package pane.popup;

import java.io.File;

import config.Config;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Popup;
import pane.ControlPane;

public class VideoPathPopup extends Popup {
	private String path;
	private MediaView mediaView;
	
	public VideoPathPopup(String path) {
		this.path = path;
		this.initMedia();
		show(ControlPane.getInstance().getStage());
		System.out.println("showw");
		mediaView.getMediaPlayer().play();
//		mediaView.getMediaPlayer().statusProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue == MediaPlayer.Status.STOPPED) {
//                hide();
//                System.out.println("Video playback ended.");
//            }
//        });
	}
	
	private void initMedia() {
		Media media = new Media(new File(path).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaView = new MediaView(mediaPlayer);
		getContent().add(mediaView);
		
		mediaView.setFitWidth(Config.VIDEO_POPUP_WIDTH);
		mediaView.setFitHeight(Config.VIDEO_POPUP_HEIGHT);
		
	}
	
}
