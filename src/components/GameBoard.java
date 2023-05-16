package components;

import config.Config;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class GameBoard extends GridPane{
	public GameBoard() {
		setPrefSize(Config.BOARD_WIDTH, Config.BOARD_HEIGH);
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		setHgap(10); // Set horizontal gap between cells
        setVgap(10); // Set vertical gap between cells

        // Add components to the grid
        Label label1 = new Label("Label 1");
        setRowIndex(label1, 0); // Set row index for label1
        setColumnIndex(label1, 0); // Set column index for label1
        getChildren().add(label1);

        Label label2 = new Label("Label 2");
        setRowIndex(label2, 1); // Set row index for label2
        setColumnIndex(label2, 1); // Set column index for label2
        getChildren().add(label2);
	}
}
