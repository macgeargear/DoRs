package pane;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GamePlay;
import logic.Marketplace;
import material.Material;
import type.MaterialType;

import java.util.ArrayList;

import components.Button.CustomButton;
import components.Material.MaterialExchange;
import javafx.geometry.Insets;

public class MarketPane extends BorderPane {

	private Text title;
	private Button backButton;
	private ArrayList<MaterialExchange> allExchanges;
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
		allExchanges = new ArrayList<MaterialExchange>();
		this.setPadding(new Insets(20));

		this.initTitle();
		this.initExchanges();
		this.initBackButton();

		for (MaterialExchange exchange : allExchanges) {
			VBox.setMargin(exchange, new Insets(20));
		}

		this.setLeft(backButton);
		this.setTop(title);
		this.setCenter(exchangeContainer);
	}

	private void initExchanges() {
		GamePlay gameInstance = GamePlay.getInstance();
		Marketplace marketplace = gameInstance.getMarketplace();
		ArrayList<ArrayList<Material>> tradeList = marketplace.getTradeList();
		ArrayList<Integer> exchangeRate = marketplace.getExchangeRate();
		int idx = 0;

		for (ArrayList<Material> trade : tradeList) {
			Color firstColor = Color.BLACK, secondColor = Color.BLACK;
			Material firstMaterial = trade.get(0), secondMaterial = trade.get(1);
			if (firstMaterial.getType() == MaterialType.WOOD) {
				firstColor = Color.GREENYELLOW;
			} else if (firstMaterial.getType() == MaterialType.WATER) {
				firstColor = Color.SKYBLUE;
			} else if (firstMaterial.getType() == MaterialType.ROCK) {
				firstColor = Color.GRAY;
			} else if (firstMaterial.getType() == MaterialType.SAND) {
				firstColor = Color.SANDYBROWN;
			} else if (firstMaterial.getType() == MaterialType.GUNPOWDER) {
				firstColor = Color.BEIGE;
			}

			if (secondMaterial.getType() == MaterialType.WOOD) {
				secondColor = Color.GREENYELLOW;
			} else if (secondMaterial.getType() == MaterialType.WATER) {
				secondColor = Color.SKYBLUE;
			} else if (secondMaterial.getType() == MaterialType.ROCK) {
				secondColor = Color.GRAY;
			} else if (secondMaterial.getType() == MaterialType.SAND) {
				secondColor = Color.SANDYBROWN;
			} else if (secondMaterial.getType() == MaterialType.GUNPOWDER) {
				secondColor = Color.BEIGE;
			}
			allExchanges.add(new MaterialExchange("" + firstMaterial.getType(), exchangeRate.get(idx), firstColor,
					"" + secondMaterial.getType(), secondColor));
			idx++;
		}

		this.exchangeContainer = new VBox();
		for (MaterialExchange exchange : allExchanges) {
			exchangeContainer.getChildren().add(exchange);
		}
	}

	private void initBackButton() {
		this.backButton = new CustomButton("back");
		this.backButton.setFont(Font.font(18));
		this.backButton.setLineSpacing(20);

		this.backButton.setOnAction(e -> {
			ControlPane.getInstance().backToGameScene();
		});
	}

	private void initTitle() {
		this.title = new Text("Exchange Material Market");
		this.title.setFont(Font.font(40));
		this.title.setTextAlignment(TextAlignment.CENTER);
	}

}