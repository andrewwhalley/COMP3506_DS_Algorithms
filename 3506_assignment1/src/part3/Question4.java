package part3;

import adt.*;

/*
 * PART 3 (QUESTION 4a): Implement method levelOrderTraversal according to its
 * specification. You should aim to make your method run in O(n) time, where n
 * is the number of nodes in the tree. You may use the instance variable
 * "qFactory" to create instances of the adt.Queue ADT to help you.
 * 
 * You must implement this method yourself, "from scratch", using only methods
 * provided by the adt.Position, adt.BinaryTree, adt.Queue and adt.QueueFactory
 * interfaces.
 * 
 * This means that you may not use any of the Java libraries, or any other
 * libraries. (That precludes, for instance, the use of any Java Collections.)
 * This means that NO IMPORT CLAUSES ARE ALLOWED, other than those already
 * present.
 * 
 * You may write supporting methods, but they must be declared private and
 * included in this file. Similarly, if you choose to write any supporting
 * classes, then they must be written as private nested classes and included in
 * this file.
 * 
 * To help you get your solution right, you should write you own JUnit tests in
 * part3.test.Question4Test (some basic tests are included to get you started). Tests
 * that you write will not be marked.
 * 
 */

public class Question4 {

	// the factory that will be used to instantiate queue implementations if you
	// need them
	QueueFactory qFactory;

	/**
	 * Creates a new instance of Question4 class with factory f that may be used
	 * to instantiate Queues.
	 */
	public Question4(QueueFactory f) {
		qFactory = f;
	}

	/**
	 * Without modifying the parameter tree, this method returns a queue of the
	 * nodes in tree, ordered (in ascending order) by the level-ordering
	 * function "p" defined as follows:
	 * 
	 * p(v) = 1, if v == tree.root();
	 * 
	 * p(v) = 2p(u), for any nodes v and u in tree, such that t.left(u) = v;
	 * 
	 * p(v) = 2p(u) +1, for any nodes v and u in tree, such that t.right(u) = v
	 * 
	 * @return a queue of nodes in tree, ordered by the level-ordering function
	 * 
	 */
	public <E> Queue<Position<E>> levelOrderTraversal(BinaryTree<E> tree) {
		/* 
		 * Create two new queues, one to store final results and one to store
		 * the children of each node one by one as we go. 
		 */
		final Queue<Position<E>> returnQueue = qFactory.createQueue();
		final Queue<Position<E>> tempQueue = qFactory.createQueue();
		// Begin at root
		Position<E> storeNode = tree.root();
		// Iterate through the tree until we run out of valid nodes
		while (storeNode != null) {
			// Store the current node we are investigating in the return queue
			returnQueue.enqueue(storeNode);
			// If a node has a left child on that level, store it for later
			// investigation
			if (tree.hasLeft(storeNode)) {
				tempQueue.enqueue(tree.left(storeNode));
			}
			// If a node has a right child on that level, store it for later
			// investigation
			if (tree.hasRight(storeNode)) {
				tempQueue.enqueue(tree.right(storeNode));
			}
			// If there is a child node we haven't explored yet, set it up
			if (tempQueue.size() > 0) {
				storeNode = tempQueue.dequeue();
			} else {
				// no more children nodes.
				storeNode = null;
			}
		}
		return returnQueue; 
	}

}
