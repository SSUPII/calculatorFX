package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class interactionController {

	@FXML
	private TextField outputField;
	
	public void onNumberPressed(ActionEvent event) {
		Button pressedButton = (Button) event.getSource();
		String chosenNumber = pressedButton.getText();
		System.out.println("Debug: "+chosenNumber);
		outputField.setText(outputField.getText()+chosenNumber);
	}
}
