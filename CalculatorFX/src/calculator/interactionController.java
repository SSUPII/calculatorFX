package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class interactionController {

	@FXML
	private TextField outputField;
	
	@FXML
	private Label errorNotifierLabel;
	
	private Operation currentCalculation = new Operation();
	private boolean isSecondNumber = false;
	
	public void onMainButtonPressed(ActionEvent event) {
		Button pressedButton = (Button) event.getSource();
		String chosenChar = pressedButton.getText();
		outputField.appendText(chosenChar);
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
		result = calculate();
		outputField.setText(Double.toString(result));
	}
	
	private void prepareOperation(byte sign) throws InvalidNumberException {
		if(isSecondNumber) {
			currentCalculation.setSecondNumber(outputField.getText());
			calculate(sign);
		}
		else {
			currentCalculation.setFirstNumber(outputField.getText());
			currentCalculation.setSign(sign);
			isSecondNumber = true;
		}
	}
	
	public void onBackspaceButtonPressed() {
		try {
			String newValue = outputField.getText(0,outputField.getText().length()-1);
			outputField.setText(newValue);
		}
		catch (Exception e) {
			errorNotifierLabel.setText("Error - Nothing to delete");
			errorNotifierLabel.setTextFill(Color.web("#FF3333"));
		}
	}
	
	private double calculate() {
		double result = 0;
		try {
			currentCalculation.setSecondNumber(outputField.getText());
			
			result = currentCalculation.compute();
			currentCalculation = new Operation();
			isSecondNumber = false;
			
		} catch (Exception e) {
			errorNotifierLabel.setText("Error - "+e.getMessage());
			errorNotifierLabel.setTextFill(Color.web("#FF3333"));
			
		}
		
		return result;
	}
	
	private double calculate(byte newSign) {
		double result = Double.parseDouble(outputField.getText());
		try {
			result = currentCalculation.compute();
			currentCalculation = new Operation();
			isSecondNumber = false;
			
		} catch (Exception e) {
			errorNotifierLabel.setText("Error - "+e.getMessage());
			errorNotifierLabel.setTextFill(Color.web("#FF3333"));
			
		}
		
		return result;
	}
	
}
