package components.Button;

import config.Config;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import pane.ControlPane;
import pane.HomePane;

public class CustomButton extends Button {

	public CustomButton(String text) {

		this.setText(text);
		this.setPrefHeight(40);
		this.setPrefWidth(340);
		this.setVisible(true);
		this.setStyle(Config.initialButtonStyle);
		this.setPrefWidth(USE_COMPUTED_SIZE);
		this.setFont(Font.font(32));

		HomePane homePane = ControlPane.getInstance().getHomePane();
		
		this.setOnMouseMoved(e -> {
			this.setStyle(Config.onMouseMoveButtonStyle);
		});

		this.setOnMouseExited(e -> {
			if(homePane.getSelectChoice() == null || !homePane.getSelectChoice().equals(this)) {				
				this.setStyle(Config.initialButtonStyle);
			}
		});
	
	}
}
