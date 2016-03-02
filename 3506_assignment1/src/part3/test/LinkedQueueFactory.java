package part3.test;

import adt.Queue;
import adt.QueueFactory;

/**
 * An implementation of QueueFactory that creates and returns LinkedQueues.
 */
public class LinkedQueueFactory implements QueueFactory {

	@Override
	public <E> Queue<E> createQueue() {
		return new LinkedQueue<E>();
	}

}
