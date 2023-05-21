package components.game;

import pane.popup.ExitPopup;
import components.button.FooterButton;
import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GamePlay;
import pane.ControlPane;

public class HeaderGame extends HBox {

	private int roundCount;
	private Text roundAmount;
	private ExitPopup exitPopup;

	public HeaderGame() {
		roundCount = GamePlay.getInstance().getCurrentRound();

		setAlignment(Pos.CENTER);
		setSpacing(20);
		setPadding(new Insets(10));
		setPrefSize(Config.SCREEN_WIDTH, 50);
		setBackground(new Background(new BackgroundFill(Color.web("#777777"), CornerRadii.EMPTY, Insets.EMPTY)));
		initRoundText();
		initExitButton();
		ControlPane.getInstance().setGameHeader(this);
	}

	private void initRoundText() {
		HBox roundDisplay = new HBox();
		roundDisplay.setAlignment(Pos.CENTER);
		roundDisplay.setBackground(Config.bg(Color.WHITE, new CornerRadii(Config.BORDER_RADIUS)));
		Text roundText = new Text("Round : ");
		roundText.setFont(new Font(16));
		roundAmount = new Text("Prepare 1");
		roundAmount.setFont(new Font(16));
		roundDisplay.getChildren().addAll(roundText, roundAmount);
		setHgrow(roundDisplay, Priority.ALWAYS);
		getChildren().add(roundDisplay);
	}

	private void initExitButton() {
		Button exitButton = new FooterButton("X");
		exitButton.setPrefHeight(20);
		exitButton.setPrefWidth(20);
		exitButton.setBackground(new Background(new BackgroundFill(Config.BackGroundColor, new CornerRadii(12), null)));
		exitButton.setText("X");
		setAlignment(Pos.CENTER_RIGHT);
		setHgrow(exitButton, Priority.ALWAYS);

		exitButton.setOnAction(e -> {
			this.exitPopup = new ExitPopup();
			this.exitPopup.show(ControlPane.getInstance().getStage());

		});

		getChildren().add(exitButton);
	}

	public void updateRoundCount() {
		roundCount = GamePlay.getInstance().getCurrentRound();
		if (roundCount == -2) {
			roundAmount.setText("Prepare 1");
			;
		} else if (roundCount == -1) {
			roundAmount.setText("Prepare 2");
		} else {
			roundAmount.setText(Integer.toString(roundCount));
		}
	}

}
