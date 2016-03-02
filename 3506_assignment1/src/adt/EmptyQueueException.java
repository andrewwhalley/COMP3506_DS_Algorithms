package adt;

@SuppressWarnings("serial")
public class EmptyQueueException extends RuntimeException {

	public EmptyQueueException() {
		super();
	}

	public EmptyQueueException(String s) {
		super(s);
	}

}
