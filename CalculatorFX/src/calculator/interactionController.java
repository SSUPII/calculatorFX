package calculator;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class interactionController {

	public void onNumberPressed(ActionEvent event) {
		Button pressedButton = (Button) event.getSource();
		System.out.println("Debug: "+pressedButton.getText());
		
	}
}
