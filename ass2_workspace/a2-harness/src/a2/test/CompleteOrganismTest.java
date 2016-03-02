package a2.test;

/**
 * Tests for the Organism class.
 */

import java.io.*;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Test;

import a2.Organism;
import a2.OrganismReader;
import a2.OutOfBoundsException;

public class CompleteOrganismTest {

	final String LS = System.getProperty("line.separator");

	// ////////////// lineageTreePreorderTraversal TESTS ////////////

	/** Basic tests of the method lineageTreePreorderTraversal */
	@Test(timeout = 5000)
	public void testPreorderTraversalGivenTests() {
		// Given: Example from handout: all cells differentiated after 3
		// generations
		testPreorderTraversal("rules0.txt", "expected0_pre.txt", 3);
		// Given: No cellular rotations: infinitely recursive
		testPreorderTraversal("rules1.txt", "expected1_pre.txt", 5);
		// Given: One 90 and -90 degree rotation, infinitely recursive
		testPreorderTraversal("rules2.txt", "expected2_pre.txt", 4);
		// Given: lizard example, infinitely recursive tail
		testPreorderTraversal("rules3.txt", "expected3_pre.txt", 6);
		// Given: example with overwriting: one rule, infinitely recursive
		testPreorderTraversal("rules5.txt", "expected5_pre.txt", 6);
	}

	/** Rotation tests for the method lineageTreePreorderTraversal */
	@Test(timeout = 5000)
	public void testPreorderTraversalRotations() {
		// Test 90 degree relative rotations and placements
		testPreorderTraversal("rules6.txt", "expected6_pre.txt", 5);
		// Test -90 degree relative rotations and placements
		testPreorderTraversal("rules7.txt", "expected7_pre.txt", 5);
		// Test 180 degree relative rotations and placements
		testPreorderTraversal("rules8.txt", "expected8_pre.txt", 5);
	}

	/** Test special cases for lineageTreePreorderTraversal */
	@Test(timeout = 5000)
	public void testSpecialCasesPreorderTraversal() {
		// k=0
		testPreorderTraversal("rules3.txt", "expected15_pre.txt", 0);
		// k=1
		testPreorderTraversal("rules3.txt", "expected16_pre.txt", 1);
		// unused rules + unused labels
		// this.generateRules(10, new File("rules17.txt"));
		testPreorderTraversal("rules17.txt", "expected17_pre.txt", 5);
		// complete lineage tree
		testPreorderTraversal("rules13.txt", "expected13_pre.txt", 8);

	}

	/** Check location bounds are irrelevant for lineageTreePreorderTraversal */
	@Test(timeout = 5000)
	public void testPreorderTraversalBoundsIrrelevant() {
		// boundary case to the right
		testPreorderTraversal("rules9.txt", "expected9_pre.txt", 11);
		// boundary case up
		testPreorderTraversal("rules10.txt", "expected10_pre.txt", 11);
		// boundary case down
		testPreorderTraversal("rules11.txt", "expected11_pre.txt", 11);
		// boundary case left
		testPreorderTraversal("rules12.txt", "expected12_pre.txt", 11);
	}

	// ///////// lineageTreeFinalCellTraversal TESTS /////////////

	/** Basic tests of the method lineageTreeFinalCellTraversal */
	@Test(timeout = 5000)
	public void testFinalCellTraversalGivenTests() {
		testFinalCellTraversal("rules0.txt", "expected0_final.txt", 3);
		testFinalCellTraversal("rules1.txt", "expected1_final.txt", 5);
		testFinalCellTraversal("rules2.txt", "expected2_final.txt", 4);
		testFinalCellTraversal("rules3.txt", "expected3_final.txt", 6);
		testFinalCellTraversal("rules5.txt", "expected5_final.txt", 6);
	}

	/** Rotation tests for method lineageTreeFinalCellTraversal */
	@Test(timeout = 5000)
	public void testFinalCellTraversalRotations() {
		testFinalCellTraversal("rules6.txt", "expected6_final.txt", 5);
		testFinalCellTraversal("rules7.txt", "expected7_final.txt", 5);
		testFinalCellTraversal("rules8.txt", "expected8_final.txt", 5);
	}

	/** Test special cases for lineageTreeFinalCellTraversal */
	@Test(timeout = 5000)
	public void testSpecialCasesFinalCellTraversal() {
		// k=0
		testFinalCellTraversal("rules3.txt", "expected15_final.txt", 0);
		// k=1
		testFinalCellTraversal("rules3.txt", "expected16_final.txt", 1);
		// unused rules + unused labels
		testFinalCellTraversal("rules17.txt", "expected17_final.txt", 5);
		// complete lineage tree
		testFinalCellTraversal("rules13.txt", "expected13_final.txt", 8);
	}

	/** Check location bounds are irrelevant for lineageTreeFinalCellTraversal */
	@Test(timeout = 5000)
	public void testFinalCellTraversalBoundsIrrelevant() {
		// boundary case to the right
		testFinalCellTraversal("rules9.txt", "expected9_final.txt", 11);
		// boundary case up
		testFinalCellTraversal("rules10.txt", "expected10_final.txt", 11);
		// boundary case down
		testFinalCellTraversal("rules11.txt", "expected11_final.txt", 11);
		// boundary case left
		testFinalCellTraversal("rules12.txt", "expected12_final.txt", 11);
	}

	// ////////////// getFinalOrganism TESTS ////////////

	/** Basic tests of the method getFinalOrganism */
	@Test(timeout = 5000)
	public void testOrgansimOriginalTests() {
		testOrganism("rules0.txt", "expected0_organism.txt", 3);
		testOrganism("rules1.txt", "expected1_organism.txt", 5);
		testOrganism("rules2.txt", "expected2_organism.txt", 4);
		testOrganism("rules3.txt", "expected3_organism.txt", 6);
		testOrganism("rules5.txt", "expected5_organism.txt", 6);
	}

	/** Rotation tests for method getFinalOrganism */
	@Test(timeout = 5000)
	public void testOrgansimRotations() {
		testOrganism("rules6.txt", "expected6_organism.txt", 20);
		testOrganism("rules7.txt", "expected7_organism.txt", 20);
		testOrganism("rules8.txt", "expected8_organism.txt", 20);
	}

	/** Check OutOfBoundsExceptions for getFinalOrganism */
	@Test(timeout = 5000)
	public void testOrgansimBounds() {
		// TEST in bounds, boundary cases
		// boundary case to the right
		testOrganism("rules9.txt", "expected9_organism.txt", 10);
		// boundary case up
		testOrganism("rules10.txt", "expected10_organism.txt", 10);
		// boundary case down
		testOrganism("rules11.txt", "expected11_organism.txt", 10);
		// boundary case left
		testOrganism("rules12.txt", "expected12_organism.txt", 10);

		// TEST out of bounds cases
		int count = 0;
		try {
			// boundary case to the right
			testOrganism("rules9.txt", "expected9_organism.txt", 11);
		} catch (OutOfBoundsException e) {
			count++;
		}
		try {
			// boundary case to the up
			testOrganism("rules10.txt", "expected10_organism.txt", 11);
		} catch (OutOfBoundsException e) {
			count++;
		}
		try {
			// boundary case to the down
			testOrganism("rules11.txt", "expected11_organism.txt", 11);
		} catch (OutOfBoundsException e) {
			count++;
		}
		try {
			// boundary case to the left
			testOrganism("rules12.txt", "expected12_organism.txt", 11);
		} catch (OutOfBoundsException e) {
			count++;
		}
		Assert.assertEquals("Out of bounds exception not thrown", 4, count);
	}

	/** Special cases and large tests for method getFinalOrganism */
	@Test(timeout = 20000)
	public void testOrgansimSpecialCases() {
		// k=0
		testOrganism("rules3.txt", "expected15_organism.txt", 0);
		// k=1
		testOrganism("rules3.txt", "expected16_organism.txt", 1);
		// most rules unused + most labels unused
		testOrganism("rules17.txt", "expected17_organism.txt", 4);
		// large lineage tree
		testOrganism("rules13.txt", "expected20_organism.txt", 25);
	}

	// //////// Helper methods /////////////////

	/**
	 * Creates an Organism g from file inputName, and checks that method
	 * g.getFinalOrganism(k) returns the organism as depicted in file
	 * outputName.
	 */
	private void testOrganism(String inputName, String outputName, int k) {
		File input = new File(inputName);
		File output = new File(outputName);
		try {
			Organism g = OrganismReader.createOrganismFromFile(input);
			String expected = readFromFile(output);
			char[][] actualGrid = g.getFinalOrganism(k);
			String actual = gridToString(actualGrid);
			Assert.assertEquals(expected, actual);
		} catch (IOException e) {
			Assert.fail("IOException");
		}
	}


	/**
	 * Returns a string representation of grid.
	 */
	private String gridToString(char[][] grid) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				s.append(grid[i][j]);
			}
			if (i != grid.length - 1) {
				s.append(LS);
			}
		}
		return s.toString();
	}

	/**
	 * Test that the lineagePreorderTraversal of the organism created from file
	 * inputName, is the same as that given in file outputName.
	 */
	private void testPreorderTraversal(String inputName, String outputName,
			int k) {
		File input = new File(inputName);
		File output = new File(outputName);
		try {
			Organism g = OrganismReader.createOrganismFromFile(input);
			String expected = readFromFile(output);
			String actual = g.lineageTreePreorderTraversal(k);
			Assert.assertEquals(expected, actual);
		} catch (IOException e) {
			Assert.fail("IOException");
		}
	}


	/**
	 * Test that the lineageTreeFinalCellTraversal of the organism created from
	 * file inputName, is the same as that given in file outputName.
	 */
	private void testFinalCellTraversal(String inputName, String outputName,
			int k) {
		File input = new File(inputName);
		File output = new File(outputName);
		try {
			Organism g = OrganismReader.createOrganismFromFile(input);
			String expected = readFromFile(output);
			String actual = g.lineageTreeFinalCellTraversal(k);
			Assert.assertEquals(expected, actual);
		} catch (IOException e) {
			Assert.fail("IOException");
		}
	}



	/**
	 * Helper method to read a file and return its contents as a string.
	 * 
	 * @param f
	 *            the File from which to read
	 * @return the contents of the file.
	 * @throws IOException
	 */
	private String readFromFile(File f) throws IOException {
		StringBuilder sb = new StringBuilder();
		Scanner scanner = new Scanner(f);
		try {
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
				if (scanner.hasNextLine()) {
					sb.append(LS);
				}
			}
		} finally {
			scanner.close();
		}
		return sb.toString();
	}

	/**
	 * Helper method to write a string to a file.
	 * 
	 * @param f
	 *            the File to write to
	 * @throws IOException
	 */
	private void writeToFile(File f, String s) throws IOException {
		PrintWriter pw = new PrintWriter(f);
		try {
			pw.print(s);
		} finally {
			pw.close();
		}
	}


}
