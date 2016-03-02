package part2.test;

/**
 * Very basic tests for the Tract class. A much more extensive
 * test suite will be performed for assessment of your code, but this should get
 * you started.
 */

import org.junit.Assert;
import org.junit.Test;

import part2.Tract;

public class TractTest {

	final String LS = System.getProperty("line.separator"); 
	
	@Test
	public void basicTractTest() {
		Tract t = new Tract(1, 1, Tract.EAST); // turkey at (1S,1E) facing east
		t.buildMound(); // mound built at (1S,1E)
		t.dashForward(); // run to (1S,2E)
		t.turnTurkeyLeft(); t.turnTurkeyLeft(); // face west
		t.dashForward(); // run to (1S,1E)
		t.turnTurkeyLeft(); // face south
		t.dashForward(); // run to (2S,1E)
		t.buildMound(); // mound built at (2,1)
		String actual = t.toString();
		String expected = 
		" _  _  _ " + LS + 
		" _  M  _ " + LS + 
		" _ |M| _ " + LS + 
		"DIRECTION: " + "south" + LS;
		Assert.assertEquals(expected, actual);
		
	}
}
