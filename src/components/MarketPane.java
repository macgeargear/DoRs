package components;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Insets;

public class MarketPane extends BorderPane {

	private Text title;
	private Button backButton;
	private MaterialExchange exchange1;
	private MaterialExchange exchange2;
	private MaterialExchange exchange3;
	private MaterialExchange exchange4;
	private MaterialExchange exchange5;
	
	private VBox exchangeContainer;

	/**
	 * wood -> water rock -> wood gunpowder -> sand sand -> gunpowder water -> rock
	 */

	public MarketPane() {
		this.setPadding(new Insets(20));
	
			

		this.initTitle();
		this.initExchanges();
		this.initBackButton();


		VBox.setMargin(exchange1, new Insets(20));
		VBox.setMargin(exchange2, new Insets(20));
		VBox.setMargin(exchange3, new Insets(20));
		VBox.setMargin(exchange4, new Insets(20));
		VBox.setMargin(exchange5, new Insets(20));

		this.setLeft(backButton);
		this.setTop(title);
//		this.backButton.setAlignment(Pos.TOP_LEFT);
		this.setCenter(exchangeContainer);
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
		this.backButton.setLineSpacing(20);
	}
	
	private void initTitle() {
		this.title = new Text("Exchange Material Market");
		this.title.setFont(Font.font(40));	
		this.title.setTextAlignment(TextAlignment.CENTER);
	}

}