package part3;

import adt.*;

/*
 * PART 3 (QUESTION 5a): Implement method question5 according to its
 * specification in the most time efficient way that you can. If you want, you
 * may use the instance variable "qFactory" to create instances of the adt.Queue
 * ADT, and instance variable "sFactory" to create instances of the adt.IStack
 * ADT to help you.
 * 
 * You must implement this method "from scratch" using only methods provided by
 * the adt.Position, adt.BinaryTree, adt.Queue, adt.QueueFactory, adt.IStack,
 * adt.IStackFactory interfaces to help you.
 * 
 * The use of any Java libraries or any other libraries is forbidden. That
 * precludes, for instance, the use of any Java Collections. This means that NO
 * IMPORT CLAUSES ARE ALLOWED, other than those already present.
 * 
 * You may write supporting methods, but they must be declared private and
 * included in this file. Similarly, if you choose to write any supporting
 * classes, then they must be written as private nested classes and included in
 * this file.
 * 
 * To help you get your solution right, you should write you own JUnit tests in
 * part3.test.Question5Test (some basic tests are included to get you started). Tests
 * that you write will not be marked.
 * 
 */

public class Question5 {

	// factory that may be used to instantiate queue implementations
	QueueFactory qFactory;
	// factory that may be used to instantiate stack implementations
	IStackFactory sFactory;

	/**
	 * Creates a new instance of Question4 class with factory f that may be used
	 * to instantiate an implementation of the Queue interface.
	 */
	public Question5(QueueFactory qf, IStackFactory sf) {
		qFactory = qf;
		sFactory = sf;
	}

	/**
	 * Given a tree t with nodes p1 and p2, this method calculates and returns
	 * the the unique node p3 satisfying all of the following three conditions:
	 * 
	 * (1) p3 is either equal to p1 or it is an ancestor of p1
	 * 
	 * (2) p3 is either equal to p2 or it is an ancestor of p2
	 * 
	 * (3) p3 is unique the node with the greatest depth that satisfies both
	 * conditions (1) and (2)
	 * 
	 * The method does not modify in any way the parameter t.
	 * 
	 */
	public <E> Position<E> question5(BinaryTree<E> t, Position<E> p1,
			Position<E> p2) {
		// find the lowest common ancestor (LCA) between two nodes 

		// Create a pair of stacks to record the parents of each position
		final IStack<Position<E>> firstStack = sFactory.createStack();
		final IStack<Position<E>> secondStack = sFactory.createStack();
		// Setup temporary positions to iterate through parents
		Position<E> tempPos1 = p1;
		Position<E> tempPos2 = p2;
		// initialise a return position and set it to root
		Position<E> retPos = t.root();
		// If one of them is the root branch, then it is the LCA
		if (t.isRoot(p1) || t.isRoot(p2)) {
			return retPos;
		}
		// If the positions are equal, then they are both the LCA
		if (p1.equals(p2)) {
			return p1;
		}
		// Iterate through the tree until we reach root from both initial 
		// positions. Initial positions could be on different levels, so
		// if one node reaches root before the other we should do nothing
		// with that node ==> hence the if statements
		while (tempPos1 != t.root() && tempPos2 != t.root()) {
			// If the parent is null, we have reached the root, don't want
			// to cause errors so do nothing if it is root
			if (t.parent(tempPos1) != null) {
				// Store the parent in a FILO storage medium
				firstStack.push(t.parent(tempPos1));
				// Move up the tree
				tempPos1 = t.parent(tempPos1);
			}
			if (t.parent(tempPos2) != null) {
				secondStack.push(t.parent(tempPos2));
				tempPos2 = t.parent(tempPos2);
			}
		}
		// Iterate over the stacks until the top elements aren't equal.
		// This will imply that we have now entered seperate branches and
		// hence the last element of the stacks that were equal will be the
		// Lowest Common Ancestor
		while (true) {
			if (!(firstStack.top().equals(secondStack.top()))) {
				break;
			}
			tempPos1 = firstStack.pop();
			tempPos2 = secondStack.pop();
		}
		retPos = tempPos1;
		return retPos;
	}

}
