package pane.popup;

import components.Button.CustomButton;
import components.Material.MaterialExchange;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

public class MarketPopup extends Popup {
	private VBox popupContent;
	private Text title;
	private Button backButton;
	private MaterialExchange exchange1;
	private MaterialExchange exchange2;
	private MaterialExchange exchange3;
	private MaterialExchange exchange4;
	private MaterialExchange exchange5;
	
	private VBox exchangeContainer;


	public MarketPopup() {
		this.centerOnScreen();
		
		this.initContent();
		this.getContent().add(popupContent);
	}
	
	private void initContent() {
		this.popupContent = new VBox();
		this.initTitle();
		this.initExchanges();
		this.initBackButton();
		this.popupContent.setPadding(new Insets(20));
		this.popupContent.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(12),null)));

		VBox.setMargin(exchange1, new Insets(20));
		VBox.setMargin(exchange2, new Insets(20));
		VBox.setMargin(exchange3, new Insets(20));
		VBox.setMargin(exchange4, new Insets(20));
		VBox.setMargin(exchange5, new Insets(20));

//		this.popupContent.setLeft(backButton);
//		this.popupContent.setTop(title);
//		this.popupContent.setCenter(exchangeContainer);
//		this.popupContent.getChildren().addAll(backButton, title, exchangeContainer);
		this.popupContent.getChildren().addAll(backButton, title, exchangeContainer);
	}
	
	private void initExchanges() {
		this.exchangeContainer = new VBox();
		this.exchange1 = new MaterialExchange("wood", 3, Color.GREENYELLOW, "water", Color.SKYBLUE);
		this.exchange2 = new MaterialExchange("rock", 2, Color.GRAY, "wood", Color.BURLYWOOD);
		this.exchange3 = new MaterialExchange("gunpowder", 1, Color.BEIGE, "sand", Color.SANDYBROWN);
		this.exchange4 = new MaterialExchange("sand", 1, Color.SANDYBROWN, "gunpowder", Color.BEIGE);
		this.exchange5 = new MaterialExchange("water", 4, Color.SKYBLUE, "rock", Color.GRAY);
		
		this.exchangeContainer.getChildren().addAll(exchange1, exchange2, exchange3, exchange4, exchange5);
	}

	private void initBackButton() {
		this.backButton = new CustomButton("back");
		this.backButton.setFont(Font.font(18));
		
		this.backButton.setOnAction(e->{			
			this.hide();
		});
	}
	
	private void initTitle() {
		this.title = new Text("Exchange Material Market");
		this.title.setFont(Font.font(40));	
		this.title.setTextAlignment(TextAlignment.CENTER);
	}
}
