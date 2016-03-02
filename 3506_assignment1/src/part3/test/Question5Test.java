package part3.test;

import org.junit.Assert;
import org.junit.Test;

import adt.*;
import part3.Question4;
import part3.Question5;

/**
 * Basic tests for the Question5.question5 method. A much more extensive test
 * suite will be performed for assessment of your code, but this should get you
 * started.
 */

public class Question5Test {

	// A factory for creating instances of queues
	QueueFactory qFactory = new LinkedQueueFactory();
	// A factory for creating  instances of stacks
	IStackFactory sFactory = new LinkedStackFactory();
	// A factory for creating  instances of trees
	BinaryTreeFactory tFactory = new LinkedBinaryTreeFactory();

	/** A really simple test of the question5 method */
	@Test
	public void test() {
		// create a simple binary tree
		BinaryTree<String> tree = new LinkedBinaryTree<String>();
		Position<String> n1 = tree.addRoot("a");
		Position<String> n2 = tree.insertLeft(n1, "b");
		Position<String> n3 = tree.insertRight(n1, "c");
		Position<String> n4 = tree.insertLeft(n2, "d");
		Position<String> n5 = tree.insertRight(n2, "e");
		Position<String> n6 = tree.insertLeft(n3, "f");
		Position<String> n7 = tree.insertRight(n3, "g");
		Position<String> n8 = tree.insertLeft(n6, "h");

		// run method on first simple example
		Position<String> expected = n3;
		Question5 q5 = new Question5(qFactory, sFactory);
		Position<String> actual = q5.question5(tree, n8, n7);
		// check expected and actual results
		Assert.assertEquals(expected, actual);

		// run method on next simple example
		expected = n1;
		actual = q5.question5(tree, n5, n6);
		// check expected and actual results
		Assert.assertEquals(expected, actual);

	}

}
