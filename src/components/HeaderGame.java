package components;

import components.Button.CustomButton;

import config.Config;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
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
import utils.Utilities;

public class HeaderGame extends HBox {

	private int roundCount;
	private Text roundAmount;
	private Text rollNumber;

	public HeaderGame() {
		roundCount = GamePlay.getInstance().getCurrentRound();

		setAlignment(Pos.CENTER);
		setSpacing(20);
		setPadding(new Insets(10));
		setPrefSize(Config.SCREEN_WIDTH, 50);
		setBackground(new Background(new BackgroundFill(Color.web("#777777"), CornerRadii.EMPTY, Insets.EMPTY)));
		initRollText();
		initRoundText();
		initExitButton();
		ControlPane.getInstance().setGameHeader(this);
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
		Button exitButton = new Button("X");
		exitButton.setPrefHeight(20);
		exitButton.setPrefWidth(20);
		exitButton.setBackground(new Background(new BackgroundFill(Config.BackGroundColor, new CornerRadii(12), null)));
		exitButton.setText("X");
		setAlignment(Pos.CENTER_RIGHT);
		setHgrow(exitButton, Priority.ALWAYS);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Utilities.alertGenerate(AlertType.CONFIRMATION, "Exit", "Sure to leave?", ()->{Utilities.exitGame();});
			}
		});
		
		getChildren().add(exitButton);
	}
	
	public void updateRoundCount() {
		roundCount = GamePlay.getInstance().getCurrentRound();
		if(roundCount == -2) {
			roundAmount.setText("Prepare 1");;
		}else if(roundCount == -1) {
			roundAmount.setText("Prepare 2");
		}else {
			roundAmount.setText(Integer.toString(roundCount));			
		}
	}
	
	public void updateDiceNumber() {
		rollNumber.setText(Integer.toString(GamePlay.instance.getRollNumber()));
	}
}
