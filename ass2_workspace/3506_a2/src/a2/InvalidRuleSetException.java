package a2;

/** An exception indicating an invalid rule set. */

@SuppressWarnings("serial")
public class InvalidRuleSetException extends RuntimeException {

	public InvalidRuleSetException() {
		super();
	}

	public InvalidRuleSetException(String s) {
		super(s);
	}

}
