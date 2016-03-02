package part3.test;

import org.junit.Assert;
import org.junit.Test;

import adt.*;
import part3.Question4;

/**
 * Basic tests for the levelOrderTraversal method from class Question4. A much
 * more extensive test suite will be performed for assessment of your code, but
 * this should get you started.
 */

public class Question4Test {

	// A factory that we will use to create instances of queues
	QueueFactory qFactory = new LinkedQueueFactory();
	// A factory that we will use to create instances of trees
	BinaryTreeFactory tFactory = new LinkedBinaryTreeFactory();

	/** A really simple test of the levelOrderTraversal method */
	@Test
	public void test() {
		// create a simple binary tree and expected result of test
		Queue<Position<String>> expected = qFactory.<Position<String>>createQueue();
		BinaryTree<String> tree = tFactory.<String>createBinaryTree();
		Position<String> n1 = tree.addRoot("a");
		Position<String> n2 = tree.insertLeft(n1, "b");
		Position<String> n3 = tree.insertRight(n1, "c");
		Position<String> n4 = tree.insertLeft(n2, "d");
		Position<String> n5 = tree.insertRight(n2, "e");
		Position<String> n6 = tree.insertLeft(n3, "f");
		Position<String> n7 = tree.insertRight(n3, "g");
		expected.enqueue(n1);
		expected.enqueue(n2);
		expected.enqueue(n3);
		expected.enqueue(n4);
		expected.enqueue(n5);
		expected.enqueue(n6);
		expected.enqueue(n7);

		// run test and record actual result
		Question4 q4 = new Question4(qFactory);
		Queue<Position<String>> actual = q4.levelOrderTraversal(tree);

		// check expected and actual results
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < actual.size(); i++) {
			Assert.assertEquals(expected.dequeue(), actual.dequeue());

		}

	}

}
