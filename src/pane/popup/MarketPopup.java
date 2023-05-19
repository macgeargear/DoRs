package pane.popup;

import java.util.ArrayList;

import components.Button.CustomButton;
import components.Material.MaterialExchange;
import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import logic.GamePlay;
import logic.Marketplace;
import material.Material;
import pane.ControlPane;
import type.MaterialType;

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
		GamePlay gameInstance = GamePlay.getInstance();
		Marketplace marketplace = gameInstance.getMarketplace();
//		ArrayList<ArrayList<Material>> tradeList = marketplace.getTradeList();
//		ArrayList<Integer> exchangeRate = marketplace.getExchangeRate();
//		int idx = 0;
//
//		for (ArrayList<Material> trade : tradeList) {
//			Paint firstColor = Color.BLACK, secondColor = Color.BLACK;
//			Material firstMaterial = trade.get(0), secondMaterial = trade.get(1);
//			if (firstMaterial.getType() == MaterialType.WOOD) {
//				firstColor = Config.WoodColor;
//			} else if (firstMaterial.getType() == MaterialType.WATER) {
//				firstColor = Config.WaterColor;
//			} else if (firstMaterial.getType() == MaterialType.ROCK) {
//				firstColor = Config.RockColor;
//			} else if (firstMaterial.getType() == MaterialType.SAND) {
//				firstColor = Config.SandColor;
//			} else if (firstMaterial.getType() == MaterialType.GUNPOWDER) {
//				firstColor = Config.GunPowderColor;
//			}
//
//			if (secondMaterial.getType() == MaterialType.WOOD) {
//				secondColor = Config.WoodColor;
//			} else if (secondMaterial.getType() == MaterialType.WATER) {
//				secondColor = Config.WaterColor;
//			} else if (secondMaterial.getType() == MaterialType.ROCK) {
//				secondColor = Config.RockColor;
//			} else if (secondMaterial.getType() == MaterialType.SAND) {
//				secondColor = Config.SandColor;
//			} else if (secondMaterial.getType() == MaterialType.GUNPOWDER) {
//				secondColor = Config.GunPowderColor;
//			}
//			allExchanges.add(new MaterialExchange("" + firstMaterial.getType(), exchangeRate.get(idx), firstColor,
//					"" + secondMaterial.getType(), secondColor, idx));
//			idx++;
//		}
//
//		this.exchangeContainer = new VBox();
//		for (MaterialExchange exchange : allExchanges) {
//			exchangeContainer.getChildren().add(exchange);
//		}

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
