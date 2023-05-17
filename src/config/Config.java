package config;

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
	
	/**
	 * TODO: 
	 * 1.Create Player Selected Button
	 * 2.Create Start Game Button
	 */

	// Screen
	public static final int HOMEPANE_HEIGHT = 678;
	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGH = 750;
	
	// Board
	public static final int BOARD_WIDTH = 800;
	public static final int BOARD_HEIGH = 500;
	public static final int SIDE_BOARD_WIDTH = 200;
	public static final int SIDE_BOARD_HEIGH = 700;
	
	// Map
	public static final int MAP_WIDTH = 70;
	public static final int MAP_HEIGH = 70;
	
	// Footer
	public static final int Footer_HEIGHT = 80;
}
