package calculator;

public class InvalidOperationException extends Exception {

	private static final long serialVersionUID = 7073634137507178572L;
	
	public InvalidOperationException(String message) {
		super(message);
	}
}
