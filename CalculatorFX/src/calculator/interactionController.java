package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class interactionController {

	@FXML
	private TextField outputField;
	
	private Operation currentCalculation = new Operation();
	private boolean isSecondNumber = false;
	
	public void onNumberPressed(ActionEvent event) {
		Button pressedButton = (Button) event.getSource();
		String chosenNumber = pressedButton.getText();
		System.out.println("Debug: "+chosenNumber);
		outputField.appendText(chosenNumber);
	}
	
	public void onSignPressed(ActionEvent event) {
		Button pressedButton = (Button) event.getSource();
		try {
			switch (pressedButton.getId()) {
			case "sum":{
				prepareOperation(Operation.SUM);
				outputField.setText("");
				break;
			}
			case "subtraction":{
				prepareOperation(Operation.SUB);
				outputField.setText("");
				break;
			}
			case "multiplication":{
				prepareOperation(Operation.MUL);
				outputField.setText("");
				break;
			}
			case "division":{
				prepareOperation(Operation.DIV);
				outputField.setText("");
				break;
			}
			}
		}
		catch (InvalidNumberException error) {
			
		}
	}
	
	public void onEqualsButtonPressed() {
		double result = 0;
		try {
			result = currentCalculation.compute();
			currentCalculation = new Operation();
			currentCalculation.setFirstNumber(Double.toString(result));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputField.setText(Double.toString(result));
	}
	
	private void prepareOperation(byte sign) throws InvalidNumberException {
		if(isSecondNumber) {
			currentCalculation.setFirstNumber(outputField.getText());
			currentCalculation.setSign(sign);
			onEqualsButtonPressed();
		}
		else {
			currentCalculation.setFirstNumber(outputField.getText());
			currentCalculation.setSign(sign);
			isSecondNumber = true;
		}
	}
	
}
