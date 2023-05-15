package components;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import logic.GamePlay;

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
		Button newButton = new Button();
		newButton.setText(Integer.toString(amount));
		newButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				GamePlay.getInstance(amount);
			}

		});
		return newButton;
	}
}
