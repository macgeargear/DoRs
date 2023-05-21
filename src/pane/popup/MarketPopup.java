package pane.popup;

import java.util.ArrayList;

import components.button.CustomButton;
import components.material.MaterialExchange;
import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import logic.GamePlay;
import pane.ControlPane;

public class MarketPopup extends Popup {
	private VBox popupContent;
	private Text amount;
	private ArrayList<MaterialExchange> allExchanges;

	public MarketPopup() {
		allExchanges = new ArrayList<MaterialExchange>();
		this.initContent();
		this.centerOnScreen();
		this.getContent().add(popupContent);
		this.updateExchange();
		ControlPane.getInstance().setMarketPopup(this);
	}

	private void initContent() {
		this.popupContent = new VBox();
		this.initBackButton();
		this.initTitle();
		this.initExchanges();
		this.popupContent.setPadding(new Insets(Config.SMALL_PADDING));
		this.popupContent.setBackground(
				new Background(new BackgroundFill(Color.WHITE, new CornerRadii(Config.BORDER_RADIUS), null)));

		for (MaterialExchange exchange : allExchanges) {
			VBox.setMargin(exchange, new Insets(Config.MEDIUM_MARGIN));
		}
	}

	private void initExchanges() {

		VBox exchangeContainer = new VBox();

		for (int i = 0; i < 5; ++i) {
			MaterialExchange newExchange = new MaterialExchange(i);
			allExchanges.add(newExchange);
			exchangeContainer.getChildren().add(newExchange);
		}

		this.popupContent.getChildren().add(exchangeContainer);
	}

	private void initBackButton() {
		Button backButton = new CustomButton("back");
		backButton.setFont(Font.font(18));

		backButton.setOnAction(e -> {
			this.hide();
		});
		this.popupContent.getChildren().add(backButton);
	}

	private void initTitle() {
		HBox header = new HBox();
		header.setAlignment(Pos.CENTER);

		Text title = new Text("Exchange Material Market");
		this.amount = new Text(" " + GamePlay.getInstance().getMarketplace().getAmount());
		title.setFont(Font.font(Config.LARGE_FONT));
		this.amount.setFont(Font.font(Config.LARGE_FONT));

		header.getChildren().addAll(title, amount);
		title.setTextAlignment(TextAlignment.CENTER);

		this.popupContent.getChildren().add(header);
	}

	public void updateExchange() {
		this.amount.setText(" " + GamePlay.getInstance().getMarketplace().getAmount());
		for (MaterialExchange exchange : allExchanges) {
			exchange.updateExchangeStatus();
		}
	}
}
