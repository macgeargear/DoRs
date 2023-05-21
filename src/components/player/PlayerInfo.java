package components.player;

import config.Config;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import logic.Player;

public class PlayerInfo extends BorderPane {

	private Player p1;
	private Player p2;
	private boolean isLeft;

	public PlayerInfo(boolean isLeft, Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.isLeft = isLeft;

		setPrefSize(Config.SIDE_BOARD_WIDTH, Config.SIDE_BOARD_HEIGH);
		setBackground(new Background(new BackgroundFill(Config.BackGroundColor, null, null)));
		setPadding(new Insets(Config.SMALL_PADDING));
		initTop();
		initBottom();
	}

	private void initTop() {
		PlayerContainer topContainer = new PlayerContainer(p1, true, isLeft);

		setTop(topContainer);
	}

	private void initBottom() {
		if (p2 == null)
			return;
		PlayerContainer botContainer = new PlayerContainer(p2, false, isLeft);

		setBottom(botContainer);
	}
}
