package pane.popup;

import components.Button.CustomButton;
import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import utils.Utilities;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ExitPopup extends Popup {

	public ExitPopup() {
		createExitPopup();
	}

	private void createExitPopup() {
		// Create popup content
		VBox popupContent = new VBox();
		popupContent.setAlignment(Pos.CENTER);
		popupContent.setPadding(new Insets(Config.MEDIUM_PADDING));

		popupContent.setBackground(Config.bg(Color.WHITE, new CornerRadii(Config.BORDER_RADIUS)));
		popupContent.setPrefHeight(220);
		popupContent.setPrefWidth(280);

		String imagePath = ClassLoader.getSystemResource("images/cry.jpeg").toString();
		ImageView imageView = new ImageView(new Image(imagePath));
		imageView.setFitHeight(600);
		imageView.setFitHeight(600);

		Text messageLabel = new Text("Are you sure you want to exit?");
		messageLabel.setFont(Font.font(Config.LARGE_FONT));
		Button confirmButton = new CustomButton("Exit");
		Button cancelButton = new CustomButton("Cancel");
		HBox btnContainer = new HBox();
		btnContainer.setAlignment(Pos.CENTER);
		btnContainer.getChildren().addAll(confirmButton, cancelButton);
		HBox.setMargin(btnContainer, new Insets(20));
		HBox.setMargin(cancelButton, new Insets(20));

		confirmButton.setOnAction(e -> {
			// Perform any exit actions here if needed
			Utilities.exitGame();
			this.hide();
		});

		cancelButton.setOnAction(e -> hide());

		popupContent.getChildren().addAll(messageLabel, imageView, btnContainer);
		getContent().add(popupContent);
	}
}
