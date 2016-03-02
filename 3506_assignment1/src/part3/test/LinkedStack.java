package part3.test;

import adt.EmptyStackException;
import adt.IStack;
import java.util.LinkedList;

/**
 * An implementation of a IStack based on java.util.LinkedList.
 * 
 * @param <E>, the type of the objects to be stored in the queue.
 */

public class LinkedStack<E> implements IStack<E>{

	LinkedList<E> list;
	
	/**
	 * Constructs an empty stack.
	 */
	public LinkedStack() {
		list = new LinkedList<E>();
	}
	
	@Override
	public void push(E element) {
		list.addFirst(element);
	}

	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		return list.removeFirst();
	}

	@Override
	public E top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		return list.getFirst();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

}
