package components.game;

import pane.popup.ExitPopup;
import card.BombCard;
import card.NuclearCard;
import card.StrongerCard;
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
import logic.Player;
import pane.ControlPane;
import type.MaterialType;
import utils.Utilities;

public class HeaderGame extends HBox {

	private int roundCount;
	private Text roundAmount;
	private Text rollNumber;
	private ExitPopup exitPopup;

	public HeaderGame() {
		roundCount = GamePlay.getInstance().getCurrentRound();

		setAlignment(Pos.CENTER);
		setSpacing(20);
		setPadding(new Insets(10));
		setPrefSize(Config.SCREEN_WIDTH, 50);
		setBackground(new Background(new BackgroundFill(Color.web("#777777"), CornerRadii.EMPTY, Insets.EMPTY)));
		initRollText();
		initAddAllMaterial();
		initRoundText();
		initExitButton();
		ControlPane.getInstance().setGameHeader(this);
	}

	private void initAddAllMaterial() {
		Button addAllBtn = new Button("+");

		addAllBtn.setOnAction(e -> {
			Player currentPlayer = Utilities.getCurrentPlayer();
			currentPlayer.increaseMaterial(MaterialType.WOOD, 1);
			currentPlayer.increaseMaterial(MaterialType.WATER, 1);
			currentPlayer.increaseMaterial(MaterialType.ROCK, 1);
			currentPlayer.increaseMaterial(MaterialType.SAND, 1);
			currentPlayer.increaseMaterial(MaterialType.GUNPOWDER, 1);
			currentPlayer.addEffect(new BombCard());
			currentPlayer.addEffect(new StrongerCard());
			currentPlayer.addEffect(new NuclearCard());
			Utilities.updateCard();
		});

		getChildren().add(addAllBtn);
	}

	private void initRollText() {
		rollNumber = new Text(Integer.toString(GamePlay.getInstance().getRollNumber()));
		rollNumber.setFont(new Font(16));
		HBox rollNumberDisplay = new HBox();
		rollNumberDisplay.setAlignment(Pos.CENTER);
		Text rollText = new Text("Number : ");
		rollText.setFont(new Font(16));
		rollNumberDisplay.getChildren().addAll(rollText, rollNumber);
		setHgrow(rollNumberDisplay, Priority.ALWAYS);
		getChildren().add(rollNumberDisplay);
	}

	private void initRoundText() {
		HBox roundDisplay = new HBox();
		roundDisplay.setAlignment(Pos.CENTER);
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

	public void updateDiceNumber() {
		rollNumber.setText(Integer.toString(GamePlay.instance.getRollNumber()));
	}
}
