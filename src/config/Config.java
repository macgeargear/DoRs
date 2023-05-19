package config;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Config {

	/**
	 * this file is for configuration of our GUI
	 */
	public static String homeBackground = "-fx-background-image: url(\"https://assetsio.reedpopcdn.com/catan-dawn-humankind-artwork-2.png?width=1200&height=1200&fit=bounds&quality=70&format=jpg&auto=webp\"); " + 
    "-fx-background-repeat: no-repeat;"+
    "-fx-background-size: 500 500;"+
    "-fx-background-position: center center;";

	public static String baseStyle = "-fx-background-radius: 12px; " + "-fx-background-color: #D9D9D9;";
	public static String initialButtonStyle = "-fx-background-radius: 12px; " + "-fx-background-color: #D9D9D9;";
	public static String onMouseMoveButtonStyle = "-fx-background-radius: 12px; " + "-fx-background-color: #E3A8A8;";
	public static String onMouseExitedButtonStyle = "-fx-background-radius: 12px; " + "-fx-background-color: #D9D9D9;";
	
	public static final int HOMEPANE_HEIGHT = 678;

	
	/**
	 * This is Layout Config for any Screen
	 */

	// Entire Screen
	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGH = 750;

	// EffectCard Popup Width
	public static final int EFFECTCARD_POPUP_WIDTH = 400;
	public static final int BUYCARD_POPUP_HEIGHT = 500;
	public static final int BUYCARD_POPUP_WIDTH = 360;
	public static final int VIDEO_POPUP_HEIGHT = 600;
	public static final int VIDEO_POPUP_WIDTH = 1000;
	
	
	// Size
	public static final int BUTTON_HEIGHT = 30;
	public static final int BORDER_RADIUS = 12;
	
	public static final int DICE_SIZE = 100;

	public static final int TINY_MARGIN = 8;
	public static final int SMALL_MARGIN = 12;
	public static final int MEDIUM_MARGIN = 24;
	public static final int LARGE_MARGIN = 40;
	public static final int HOME_MARGIN = 80;

	public static final int SMALL_PADDING = 12;
	public static final int MEDIUM_PADDING = 40;
	public static final int LARGE_PADDING = 40;
	
	public static final int WELCOME_FONT = 60;
	public static final int LARGE_FONT = 32;
	public static final int MEDIUM_FONT = 20;
	public static final int SMALL_FONT = 12;
	
	public static final int CARD_SIZE = 20;
	
	// Board
	public static final int BOARD_WIDTH = 800;
	public static final int BOARD_HEIGH = 500;
	public static final int SIDE_BOARD_WIDTH = 200;
	public static final int SIDE_BOARD_HEIGH = 700;
	
	
	// Map
	public static final int MAP_WIDTH = 70;
	public static final int MAP_HEIGH = 70;
	public static final int NODE_WIDTH = 30;
	public static final int NODE_HEIGH = 30;
	

	// Footer
	public static final int Footer_HEIGHT = 80;

	// Node color
	// String
	public static final String EMPTY = "#ABB2B9";
	public static final String P1 = "#E67E22";
	public static final String P2 = "#E74C3C";
	public static final String P3 = "#8E44AD";
	public static final String P4 = "#3498DB";
	public static final String WOOD = "#A4D77B"; // light green
	public static final String WATER = "#7CD8EC"; // sky
	public static final String ROCK = "#0E8468"; // green
	public static final String SAND = "#E3A8A8"; // pink
	public static final String GUNPOWDER = "#D9D9D9"; // grey
	
	// Paint
	public static final Paint BackGroundColor = Color.web("#C3FFFB");

	public static final Paint WoodColor = Color.web(Config.WOOD);
	public static final Paint RockColor = Color.web(Config.ROCK);
	public static final Paint GunPowderColor = Color.web(Config.GUNPOWDER);
	public static final Paint SandColor = Color.web(Config.SAND);
	public static final Paint WaterColor = Color.web(Config.WATER);
	
	public static final Paint DiceColor = Color.web("#E28686");
	public static final Paint FooterColor = Color.MISTYROSE;
	
	// Video path
	public static final String ATOMIC = "res/video/atomic.mp4";
	public static final String EXPLOSION = "res/video/explosion.mp4";

	
}
