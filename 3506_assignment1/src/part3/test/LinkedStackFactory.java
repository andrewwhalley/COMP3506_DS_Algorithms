package part3.test;

import adt.IStack;
import adt.IStackFactory;

/**
 * An implementation of IStackFactory that creates and returns LinkedStacks.
 */
public class LinkedStackFactory implements IStackFactory {

	@Override
	public <E> IStack<E> createStack() {
		return new LinkedStack<E>();
	}

}
