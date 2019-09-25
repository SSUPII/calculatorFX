package calculator;

public class Operation {
	
	//Error codes
	private final byte EMPTY = -1;
	private final byte LIMIT_REACHED = -2;
	private final byte MULTIPLE_POINTS = -3;
	private final byte INVALID_DECIMAL_PART = -4;
	private final byte VALID = 1;
	
	//Constants for sign
	public static final byte NO_SIGN = -1;
	public static final byte SUM = 0;
	public static final byte SUB = 1;
	public static final byte DIV = 2;
	public static final byte MUL = 3;
	
	public static final int DEFAULT_MAX_DIGITS = 16;
	
	private String firstNumber;
	private String secondNumber;
	private int maxDigits = DEFAULT_MAX_DIGITS;
	
	private byte sign = NO_SIGN;
	
	public Operation() {
		
	}
	public Operation(int limit) {
		this.maxDigits = limit;
		
	}
	
	void setFirstNumber(String newNumber) throws InvalidNumberException{
		byte validationState = isValid(newNumber);
		if(validationState == 1)
			firstNumber = newNumber;
		else
			throw new InvalidNumberException("Number is invalid: Code "+validationState);
	}
	
	void setSecondNumber(String newNumber) throws InvalidNumberException{
		byte validationState = isValid(newNumber);
		if(validationState == 1)
			secondNumber = newNumber;
		else
			throw new InvalidNumberException("Number is invalid: Code "+validationState);
	}
	
	void setSign(byte newSign) {
		this.sign = newSign;
	}
	
	private byte isValid(String number) {
		
		//check if empty
		if(number.length() < 1) return EMPTY;
		
		//check if exceeds digits cap
		int maxDigitsCheck = maxDigits;
		if(number.contains(".")) maxDigitsCheck++;
		if(number.startsWith("-")) maxDigitsCheck++;
			
		if(number.length() > maxDigitsCheck)
			return LIMIT_REACHED;
		
		//check if there are more than 1 point
		int points = 0;
		for(int i = 0;number.length()>i;i++) {
			if(number.charAt(i)=='.')
				points++;
		}
		
		if(points > 1) return MULTIPLE_POINTS;
		
		//check if after a point there is nothing or a non-numerical character
		for(int i = 0;number.length()>i;i++) {
			if(number.charAt(i)=='.') {
				try {
					if(number.charAt(i+1) < 48 || number.charAt(i+1) > 57)
						return INVALID_DECIMAL_PART;
				}
				catch(Exception error){
					return INVALID_DECIMAL_PART;
				}
			}
		}
		
		//everything is valid
		return VALID;
			
	}
	
	public double compute() throws InvalidOperationException{
		double result = 0;
		
		switch (sign) {
		case NO_SIGN:{
			throw new InvalidOperationException("Sign was not specified");
		}
		case SUM:{
			result = Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber);
			break;
		}
		case SUB:{
			result = Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber);
			break;
		}
		case DIV:{
			result = Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber);
			break;
		}
		case MUL:{
			result = Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber);
			break;
		}
		
		}
		
		return result;
	}
}
