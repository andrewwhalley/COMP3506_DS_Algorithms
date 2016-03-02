package a2;

/**
 * An exception indicating that the organism has grown out of bounds of the grid.
 */

@SuppressWarnings("serial")
public class OutOfBoundsException extends RuntimeException {

	public OutOfBoundsException() {
		super();
	}

	public OutOfBoundsException(String s) {
		super(s);
	}

}
