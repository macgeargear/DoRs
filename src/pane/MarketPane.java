package pane;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GamePlay;
import logic.Marketplace;
import material.Material;
import type.MaterialType;
import utils.Utilities;

import java.util.ArrayList;

import components.Button.CustomButton;
import components.Material.MaterialExchange;
import javafx.geometry.Insets;

public class MarketPane extends BorderPane {

	private Text title;
	private Text amount;
	private Button backButton;
	private ArrayList<MaterialExchange> allExchanges;

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
		
		HBox top = new HBox();
		top.getChildren().addAll(title, amount);
		
		this.setLeft(backButton);
		this.setTop(top);
		this.setCenter(exchangeContainer);
		this.updateExchange();
		
		ControlPane.getInstance().setMarketPane(this);
	}

	private void initExchanges() {
		GamePlay gameInstance = GamePlay.getInstance();
		Marketplace marketplace = gameInstance.getMarketplace();
		ArrayList<ArrayList<Material>> tradeList = marketplace.getTradeList();
		ArrayList<Integer> exchangeRate = marketplace.getExchangeRate();
		int idx = 0;

		for (ArrayList<Material> trade : tradeList) {
//			Color firstColor = Color.BLACK, secondColor = Color.BLACK;
			Material firstMaterial = trade.get(0), secondMaterial = trade.get(1);
			Paint firstColor = Utilities.getColor(firstMaterial.getType());
			Paint secondColor = Utilities.getColor(firstMaterial.getType());
//			if (firstMaterial.getType() == MaterialType.WOOD) {
//				firstColor = Color.GREENYELLOW;
//			} else if (firstMaterial.getType() == MaterialType.WATER) {
//				firstColor = Color.SKYBLUE;
//			} else if (firstMaterial.getType() == MaterialType.ROCK) {
//				firstColor = Color.GRAY;
//			} else if (firstMaterial.getType() == MaterialType.SAND) {
//				firstColor = Color.SANDYBROWN;
//			} else if (firstMaterial.getType() == MaterialType.GUNPOWDER) {
//				firstColor = Color.BEIGE;
//			}
//
//			if (secondMaterial.getType() == MaterialType.WOOD) {
//				secondColor = Color.GREENYELLOW;
//			} else if (secondMaterial.getType() == MaterialType.WATER) {
//				secondColor = Color.SKYBLUE;
//			} else if (secondMaterial.getType() == MaterialType.ROCK) {
//				secondColor = Color.GRAY;
//			} else if (secondMaterial.getType() == MaterialType.SAND) {
//				secondColor = Color.SANDYBROWN;
//			} else if (secondMaterial.getType() == MaterialType.GUNPOWDER) {
//				secondColor = Color.BEIGE;
//			}
			allExchanges.add(new MaterialExchange("" + firstMaterial.getType(), exchangeRate.get(idx), firstColor,
					"" + secondMaterial.getType(), secondColor, idx));
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
		this.amount = new Text(" "+GamePlay.getInstance().getMarketplace().getAmount());
		this.title.setFont(Font.font(40));
		this.amount.setFont(Font.font(40));
		this.title.setTextAlignment(TextAlignment.CENTER);
	}
	
	public void updateExchange() {
		this.amount.setText(" "+GamePlay.getInstance().getMarketplace().getAmount());
		for(MaterialExchange exchange: allExchanges) {
			exchange.updateExchangeStatus();
		}
	}

}