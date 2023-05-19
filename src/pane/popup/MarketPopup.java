package pane.popup;

import java.util.ArrayList;

import components.Button.CustomButton;
import components.Material.MaterialExchange;
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
	private HBox header;
	private Text title;
	private Text amount;
	private Button backButton;
	private ArrayList<MaterialExchange> allExchanges;
	private VBox exchangeContainer;

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
		this.initTitle();
		this.initExchanges();
		this.initBackButton();
		this.popupContent.setPadding(new Insets(12));
		this.popupContent.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(12), null)));

		for (MaterialExchange exchange : allExchanges) {
			VBox.setMargin(exchange, new Insets(20));
		}

		this.popupContent.getChildren().addAll(backButton, header, exchangeContainer);
	}

	private void initExchanges() {

		this.exchangeContainer = new VBox();
		
		for(int i=0;i<5;++i) {
			MaterialExchange newExchange = new MaterialExchange(i);
			allExchanges.add(newExchange);
			exchangeContainer.getChildren().add(newExchange);
		}
	}

	private void initBackButton() {
		this.backButton = new CustomButton("back");
		this.backButton.setFont(Font.font(18));

		this.backButton.setOnAction(e -> {
			this.hide();
		});
	}

	private void initTitle() {
		this.header = new HBox();
		this.header.setAlignment(Pos.CENTER);
		
		this.title = new Text("Exchange Material Market");
		this.amount = new Text(" " + GamePlay.getInstance().getMarketplace().getAmount());
		this.title.setFont(Font.font(40));
		this.amount.setFont(Font.font(40));
		
		this.header.getChildren().addAll(title, amount);
		this.title.setTextAlignment(TextAlignment.CENTER);
	}

	public void updateExchange() {
		this.amount.setText(" " + GamePlay.getInstance().getMarketplace().getAmount());
		for (MaterialExchange exchange : allExchanges) {
			exchange.updateExchangeStatus();
		}
	}
}
