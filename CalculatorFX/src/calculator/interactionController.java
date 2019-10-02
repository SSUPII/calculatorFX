package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class interactionController {

	@FXML
	private Label outputLabel;
	
	@FXML
	private Label operationPreview;

	@FXML
	private Label errorNotifierLabel;

	private Operation currentCalculation = new Operation();
	private boolean isSecondNumber = false;

	public void onMainButtonPressed(ActionEvent event) {
		Button pressedButton = (Button) event.getSource();
		String chosenChar = pressedButton.getText();
		if(outputLabel.getText()!="Infinity")
			outputLabel.setText(outputLabel.getText()+chosenChar);
		else
			outputLabel.setText(chosenChar);
	}

	public void onSignPressed(ActionEvent event) {
		if(outputLabel.getText()!="Infinity") {
			Button pressedButton = (Button) event.getSource();
			try {
				switch (pressedButton.getId()) {
				case "sum":{
					prepareOperation(Operation.SUM);
					operationPreview.setText(outputLabel.getText()+" +");
					outputLabel.setText("");
					break;
				}
				case "subtraction":{
					prepareOperation(Operation.SUB);
					operationPreview.setText(outputLabel.getText()+" -");
					outputLabel.setText("");
					break;
				}
				case "multiplication":{
					prepareOperation(Operation.MUL);
					operationPreview.setText(outputLabel.getText()+" *");
					outputLabel.setText("");
					break;
				}
				case "division":{
					prepareOperation(Operation.DIV);
					operationPreview.setText(outputLabel.getText()+" /");
					outputLabel.setText("");
					break;
				}
				}
			}
			catch (InvalidNumberException error) {
	
			}
		}
	}

	public void onEqualsButtonPressed() {
		if(outputLabel.getText()!="Infinity") {
			double result = 0;
			result = calculate();
			outputLabel.setText(Double.toString(result));
			operationPreview.setText("");
		}
	}

	private void prepareOperation(byte sign) throws InvalidNumberException {
		if(isSecondNumber) {
			currentCalculation.setSecondNumber(outputLabel.getText());
			calculate(sign);
		}
		else {
			currentCalculation.setFirstNumber(outputLabel.getText());
			currentCalculation.setSign(sign);
			isSecondNumber = true;
		}
	}

	public void onBackspaceButtonPressed() {
		if(outputLabel.getText()!="Infinity") {
			try {
				String newValue = "";
				for(int n = 0;outputLabel.getText().length()-1>n;n++)
					newValue += outputLabel.getText().charAt(n);
				outputLabel.setText(newValue);
			}
			catch (Exception e) {
				errorNotifierLabel.setText("Error - Nothing to delete");
				errorNotifierLabel.setTextFill(Color.web("#FF3333"));
			}
		}
		else
			onClearButtonPressed();
	}

	public void onClearButtonPressed() {
		currentCalculation = new Operation();
		isSecondNumber = false;
		outputLabel.setText("");

		operationPreview.setText("");
		
		errorNotifierLabel.setText("Ready");
		errorNotifierLabel.setTextFill(Color.web("#AAAAAA"));
	}

	public void onClearEntryButtonPressed() {
		//CE stands for Clear Entry
		outputLabel.setText("");
	}

	public void onInvertButtonPressed() {
		Double newNumber = Double.parseDouble(outputLabel.getText())*(-1);

		outputLabel.setText(Double.toString(newNumber));
	}

	private double calculate() {
		double result = 0;
		try {
			currentCalculation.setSecondNumber(outputLabel.getText());

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
		double result = Double.parseDouble(outputLabel.getText());
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
