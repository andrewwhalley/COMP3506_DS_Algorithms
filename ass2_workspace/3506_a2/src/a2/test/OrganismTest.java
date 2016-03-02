package a2.test;

/**
 * Very basic tests for the Organism class. A much more extensive
 * test suite will be performed for assessment of your code, but this should get
 * you started.
 */

import java.io.*;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Test;

import a2.Organism;
import a2.OrganismReader;

public class OrganismTest {

	final String LS = System.getProperty("line.separator");

	/** Basic tests of the method lineageTreePreorderTraversal */
	@Test
	public void testPreorderTraversal() {
		testPreorderTraversal("rules0.txt", "expected0_pre.txt", 3);
		testPreorderTraversal("rules1.txt", "expected1_pre.txt", 5);
		testPreorderTraversal("rules2.txt", "expected2_pre.txt", 4);
		testPreorderTraversal("rules3.txt", "expected3_pre.txt", 6);
		testPreorderTraversal("rules5.txt", "expected5_pre.txt", 6);
	}

	/** Basic tests of the method lineageTreeFinalCellTraversal */
	@Test
	public void testFinalCellTraversal() {
		testFinalCellTraversal("rules0.txt", "expected0_final.txt", 3);
		testFinalCellTraversal("rules1.txt", "expected1_final.txt", 5);
		testFinalCellTraversal("rules2.txt", "expected2_final.txt", 4);
		testFinalCellTraversal("rules3.txt", "expected3_final.txt", 6);
		testFinalCellTraversal("rules5.txt", "expected5_final.txt", 6);
	}

	/** Basic tests of the method getFinalOrganism */
	@Test
	public void testOrgansim() {
		testOrganism("rules0.txt", "expected0_organism.txt", 3);
		testOrganism("rules1.txt", "expected1_organism.txt", 5);
		testOrganism("rules2.txt", "expected2_organism.txt", 4);
		testOrganism("rules3.txt", "expected3_organism.txt", 6);
		testOrganism("rules5.txt", "expected5_organism.txt", 6);
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

}
