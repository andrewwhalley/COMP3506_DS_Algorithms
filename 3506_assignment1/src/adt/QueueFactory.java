package adt;

/**
 * An interface with a method that creates and returns a Queue.
 */
public interface QueueFactory {

	/** Create and return a Queue*/
	public <E> Queue<E> createQueue();
	
	
}
