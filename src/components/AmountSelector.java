package components;

import components.Button.CustomButton;
import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import logic.GamePlay;
import pane.ControlPane;
import pane.HomePane;

public class AmountSelector extends HBox {
	public AmountSelector() {
		setAlignment(Pos.CENTER);
		setVisible(false);
		setSpacing(20);

		for (int amount = 2; amount <= 4; ++amount) {
			getChildren().add(createChoice(amount));
		}

	}

	private Button createChoice(int amount) {
		Button newButton = new CustomButton(String.valueOf(amount));
		newButton.setStyle(Config.initialButtonStyle);
		newButton.setPadding(new Insets(20));
		newButton.setPrefWidth(68);
		newButton.setFont(Font.font(32));
		newButton.setOnMouseClicked(e -> {
			HomePane homePane = ControlPane.getInstance().getHomePane();
			Button prevChoice = homePane.getSelectChoice();
			GamePlay.getInstance(amount);

			if (prevChoice != null) {
				prevChoice.setStyle(Config.initialButtonStyle);
			}

			homePane.setSelectChoice(newButton);
			homePane.getStartButton().setDisable(false);
		});
		return newButton;
	}
}
