package components;

import components.Button.FooterButton;
import components.Button.RollDice;
import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import logic.GamePlay;
import logic.Player;
import pane.ControlPane;
import pane.popup.CardPopup;
import pane.popup.MarketPopup;
import utils.Utilities;

public class Footer extends HBox {
	private Button buyCardButton;
	private Button marketButton;
	private Button rollDiceButton;
	private Button buyNodeButton;
	private Button buyEdgeButton;
	private Button showCardButton;
	private Button endTurnButton;

	private CardPopup cardPopup;

	private MarketPopup marketPopup;


	public Footer() {
		this.setPrefHeight(Config.Footer_HEIGHT);

		this.initBuyCardButton();
		this.initMarketButton();
		this.initRollDiceButton();
		this.initBuyEdgeButton();
		this.initBuyNodeButton();
		this.initEndTurnButton();
		this.initShowCardButton();

		this.setAlignment(Pos.CENTER);
		HBox.setMargin(this.rollDiceButton, new Insets(20));
		HBox.setMargin(this.buyCardButton, new Insets(8));
		HBox.setMargin(this.buyEdgeButton, new Insets(8));
		HBox.setMargin(this.buyNodeButton, new Insets(8));
		HBox.setMargin(this.marketButton, new Insets(8));

		this.getChildren().addAll(showCardButton, marketButton, buyCardButton, rollDiceButton, buyEdgeButton,
				buyNodeButton, endTurnButton);

		ControlPane.getInstance().setFooter(this);
	}

	private void initBuyCardButton() {
		this.buyCardButton = new FooterButton("Buy Card");
	}

	private void initMarketButton() {
		this.marketButton = new FooterButton("Market");
		this.marketPopup = new MarketPopup();
		this.marketButton.setOnAction(e -> {
			if (this.marketPopup == null) {
				this.marketPopup.show(ControlPane.getInstance().getStage());
			} else {
				if (this.marketPopup.isShowing()) {
					this.marketPopup.hide();
				} else {
					this.marketPopup.show(ControlPane.getInstance().getStage());
				}
			}

		});

	}

	private void initRollDiceButton() {

		this.rollDiceButton = new RollDice("Roll");
		this.rollDiceButton.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, null, null)));
		this.rollDiceButton.setDisable(true);
		this.rollDiceButton.setOnAction(e -> {
			GamePlay instance = GamePlay.getInstance();
			if (instance.rollDice()) {
				HeaderGame gameHeader = ControlPane.getInstance().getGameHeader();
				gameHeader.updateDiceNumber();
				this.setRollDiceButton(instance.getRollNumber());
				rollDiceButton.setDisable(true);

				if (Utilities.canEndTurn()) {
					this.endTurnButton.setDisable(false);
				}
				Utilities.updateCard();
			}
		});

	}

	private void initBuyNodeButton() {
		this.buyNodeButton = new FooterButton("Buy Node");
		this.buyNodeButton.setDisable(true);

		this.buyNodeButton.setOnAction(e -> {
			ControlPane paneInstance = ControlPane.getInstance();
			paneInstance.getSelectNode().getNode().upgrade();
			paneInstance.getSelectNode().setupSyle();
			this.buyNodeButton.setDisable(true);
			if (Utilities.canEndTurn()) {
				this.endTurnButton.setDisable(false);
			}

			Utilities.updateCard();

		});
	}

	private void initBuyEdgeButton() {
		this.buyEdgeButton = new FooterButton("Buy Edge");
		this.buyEdgeButton.setDisable(true);

		this.buyEdgeButton.setOnAction(e -> {
			ControlPane paneInstance = ControlPane.getInstance();
			paneInstance.getSelectEdge().getEdge().upgrade();
			paneInstance.getSelectEdge().setupSyle();
			this.buyEdgeButton.setDisable(true);
			if (Utilities.canEndTurn()) {
				this.endTurnButton.setDisable(false);
			}

			Utilities.updateCard();

		});
	}

	private void initEndTurnButton() {
		this.endTurnButton = new FooterButton("End turn");
		this.endTurnButton
				.setBackground(new Background(new BackgroundFill(Color.INDIANRED, new CornerRadii(12), null)));
		this.endTurnButton.setDisable(true);

		this.endTurnButton.setOnAction(e -> {
			GamePlay gameInstance = GamePlay.getInstance();
			ControlPane paneInstance = ControlPane.getInstance();
			Player prevPlayer = Utilities.getCurrentPlayer();

			if (gameInstance.goToNextPlayer()) {
				Player currentPlayer = Utilities.getCurrentPlayer();
				for (PlayerContainer container : paneInstance.getAllPlayerContainers()) {
					if (container.getP().equals(prevPlayer) || container.getP().equals(currentPlayer)) {
						if (!prevPlayer.equals(currentPlayer)) {
							container.toggleColorNameContainer();
						}
					}
				}
			}
//			update round
			paneInstance.getGameHeader().updateRoundCount();
			if (gameInstance.getCurrentRound() > 0) {
				this.rollDiceButton.setDisable(false);
				this.rollDiceButton.setText("Roll");
			}
			this.endTurnButton.setDisable(true);
			paneInstance.resetSelect();

			gameInstance.getMarketplace().reMarket();
			Utilities.updateCard();
			if (gameInstance.getCurrentRound() == 5) {
				GameResult gameResult = new GameResult();
				gameResult.show(paneInstance.getStage());

			}
		});
	}

	private void initShowCardButton() {
		this.cardPopup = new CardPopup();
//		cardPopup.show(ControlPane.getInstance().getStage());
		this.showCardButton = new FooterButton("Show Card");
		this.endTurnButton
				.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, new CornerRadii(12), null)));
		this.showCardButton.setOnAction(e -> {
			if (cardPopup == null) {
				cardPopup = new CardPopup();
				cardPopup.show(ControlPane.getInstance().getStage());
			} else {
				if (cardPopup.isShowing()) {
					cardPopup.hide();
				} else {
					cardPopup.show(ControlPane.getInstance().getStage());
				}
			}
		});
	}

	public Button getRollDiceButton() {
		return rollDiceButton;
	}

	public void setRollDiceButton(int number) {
		this.rollDiceButton.setText(String.valueOf(number));
	}

	public void setBuyNodeDisable(boolean isDisable) {
		this.buyNodeButton.setDisable(isDisable);
	}

	public void setBuyEdgeDisable(boolean isDisable) {
		this.buyEdgeButton.setDisable(isDisable);
	}

	public Button getBuyCardButton() {
		return buyCardButton;
	}

	public Button getBuyEdgeButton() {
		return buyEdgeButton;
	}

	public Button getBuyNodeButton() {
		return buyNodeButton;
	}

}
