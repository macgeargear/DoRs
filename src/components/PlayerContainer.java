package components;

import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GamePlay;
import logic.Player;
import pane.ControlPane;

public class PlayerContainer extends VBox{
	private Player p;
	private boolean isTop, isLeft, toggle;
	private Text name;
	private Text effectCardCount;
	private Text materialCardCount;
	private HBox nameContainer;
	
	public PlayerContainer(Player p, boolean isTop, boolean isLeft) {
		this.p = p;
		this.isTop = isTop;
		this.isLeft = isLeft;
		this.toggle = false;
		setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(12), null)));
		setAlignment(Pos.CENTER);
		setPadding(new Insets(20));
		setSpacing(10);
		setPrefWidth(Config.SIDE_BOARD_WIDTH);
		this.initContainer();
		ControlPane.getInstance().addPlayerContainer(this);
		if(GamePlay.getInstance().getAllPlayers().get(GamePlay.getInstance().getCurrentPlayer()).equals(p)) {
			this.toggleColorNameContainer();
		}
	}
	
	private void initContainer(){
		nameContainer = new HBox();
		name = new Text(p.getName());
		nameContainer.getChildren().add(name);
		nameContainer.setPrefWidth(Config.SIDE_BOARD_WIDTH);
		nameContainer.setPadding(new Insets(10));
		
		nameContainer.setBorder(new Border(new BorderStroke(
                Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2)
        )));
		nameContainer.setAlignment(Pos.CENTER);
		nameContainer.setStyle("-fx-background-color: white; -fx-background-radius: 10px;");
		if(isTop) {
			getChildren().add(nameContainer);
		}
		this.initCount();
		if(!isTop) {
			getChildren().add(nameContainer);	
		}
	}
	
	private void initCount() {
		materialCardCount = new Text(Integer.toString(p.getMaterialCount()));
		effectCardCount = new Text(Integer.toString(p.getAllEffectCards().size()));
		
		
//		effect card
		HBox containerEffect = new HBox();
		Rectangle effectCard = new Rectangle(30, 50, Color.GOLD);
		
		effectCardCount.setFont(new Font(20));
		containerEffect.setAlignment(isLeft ? Pos.CENTER_LEFT: Pos.CENTER_RIGHT);
		containerEffect.setSpacing(5);
		effectCard.setArcWidth(10);
		effectCard.setArcHeight(10);
		if(isLeft) {
			containerEffect.getChildren().addAll(effectCard, effectCardCount);			
		}else {
			containerEffect.getChildren().addAll(effectCardCount, effectCard);				
		}
		
//		material card
		HBox containerMaterial = new HBox();
		Rectangle materialCard = new Rectangle(30, 50, Color.BROWN);
		
		materialCardCount.setFont(new Font(20));
		containerMaterial.setAlignment(isLeft ? Pos.CENTER_LEFT: Pos.CENTER_RIGHT);
		containerMaterial.setSpacing(5);
		materialCard.setArcHeight(10);
		materialCard.setArcWidth(10);
		if(isLeft) {
			containerMaterial.getChildren().addAll(materialCard, materialCardCount);			
		}else {
			containerMaterial.getChildren().addAll(materialCardCount, materialCard);			
		}
		
		if(isTop) {
			getChildren().addAll(containerEffect, containerMaterial);			
		}else {
			getChildren().addAll(containerMaterial, containerEffect);
		}
	}
	
	public void toggleColorNameContainer() {
		if(toggle) {
			nameContainer.setStyle("-fx-background-color: white; -fx-background-radius: 10px;");
		}else {
			nameContainer.setStyle("-fx-background-color: lightgreen; -fx-background-radius: 10px;");
		}
		toggle = !toggle;
	}

	public void updateCount() {
		effectCardCount.setText(Integer.toString(p.getAllEffectCards().size()));
		materialCardCount.setText(Integer.toString(p.getMaterialCount()));
	}
	
	public Player getP() {
		return p;
	}
	
}
