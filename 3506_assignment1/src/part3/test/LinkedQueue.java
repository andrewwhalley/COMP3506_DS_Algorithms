package part3.test;

import adt.Queue;
import adt.EmptyQueueException;
import java.util.LinkedList;

/**
 * An implementation of a Queue based on java.util.LinkedList.
 * 
 * @param <E>, the type of the objects to be stored in the queue.
 */

public class LinkedQueue<E> implements Queue<E> {

	java.util.LinkedList<E> list;

	/**
	 * Constructs an empty queue.
	 */
	public LinkedQueue() {
		list = new LinkedList<E>();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public E front() throws EmptyQueueException {
		if (list.isEmpty()) {
			throw new EmptyQueueException("No front element.");
		}
		return list.getFirst();
	}

	@Override
	public void enqueue(E element) {
		list.addLast(element);
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if (list.isEmpty()) {
			throw new EmptyQueueException("No elements to dequeue.");
		}
		return list.removeFirst();
	}

}
