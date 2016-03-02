package a2;

import java.io.*;
import java.util.*;

/**
 * Class OrganismReader contains a method for reading the description of an
 * organism from a file and creating an object of class Organism with the
 * information from that file.
 */

public class OrganismReader {

	/**
	 * 
	 * PRE: File f contains one or more lines of the form:
	 * "cellType1 = cellType2 cellType3 branchAngle", each denoting a cell
	 * division rule; followed by a blank line; and then one line containing a
	 * single ASCII character, denoting a default ASCII character representation
	 * for cells; followed by zero or more lines of the form: "cellType = c",
	 * that define explicit ASCII character labels for cells.
	 * 
	 * In this representation, "cellType1","cellType2","cellType3" and
	 * "cellType" represent any strings that match regular expression
	 * "[a-zA-Z0-9]*", branchAngle is either "0", "90", "-90" or "180", and "c"
	 * is a single ASCII character.
	 * 
	 * A division rule "cellType1 = cellType2 cellType3 branchAngle" denotes the
	 * fact that a cell with type "cellType1" can divide into a left child cell
	 * with type "cellType2", which assumes the cell orientation of its parent,
	 * and a right child of type "cellType3", which assumes the cell orientation
	 * of its parent rotated by "branchAngle" degrees.
	 * 
	 * There can be at most one cell-division rule for each cell-type, and at
	 * most one explicit label given for any cell-type.
	 * 
	 * 
	 * For example, a valid file might contain:
	 * 
	 * Z = s G 0 
	 * G = s G 0
	 * 
	 * o
	 * s = ~
	 * 
	 * In that file there are two cell-division rules, a default character label
	 * of 'o' for cells, and an explicit label of '~' for cells of type "s".
	 * 
	 * POST: If there are no errors reading File f, and File f is correctly
	 * formatted, then this method creates and returns a new Organism to which
	 * all of the cell division rules in File f have been added; the zygote has
	 * been set to be the precursor cell from the first cell-division rule in
	 * the file; the default cell label has been set to be that defined in the
	 * file; and the cell labels explicitly given in the file have been added.
	 * An IOException is thrown if there is a problem reading from File f, and a
	 * InvalidRuleSetException is thrown if the file format of f is incorrect.
	 * 
	 */
	public static Organism createOrganismFromFile(File f) throws IOException,
			InvalidRuleSetException {
		Organism g = new Organism(); // a new empty organism
		Scanner in = new Scanner(f); // scanner for reading the file
		int lineNumber = 1; // the number of the line being read
		try {
			// read first cell division rule, add to organism, and set zygote
			if (!in.hasNextLine()) {
				throw new InvalidRuleSetException(
						"The division rules must contain at least one rule");
			}
			String line = in.nextLine();// the next line
			String zygote = readDivisionRule(line, g, lineNumber);
			g.setZygote(zygote);
			lineNumber++;
			line = (in.hasNextLine() ? in.nextLine() : null);
			// read division rules, adding them to the organism, until an empty
			// line is found
			while (line != null && !line.isEmpty()) {
				readDivisionRule(line, g, lineNumber);
				lineNumber++;
				line = (in.hasNextLine() ? in.nextLine() : null);
			}
			// check that an empty line has been found
			if (line == null) {
				throw new InvalidRuleSetException(
						"Input file contains no labels for cells");
			}
			// read default label, adding it to the organism
			if (!in.hasNextLine()) {
				throw new InvalidRuleSetException(
						"The division rules must contain a default label");
			}
			line = in.nextLine();
			lineNumber++;
			readDefaultLabel(line, g, lineNumber);
			// read labels, adding them to the organism, until end of scanner
			// reached
			while (in.hasNext()) {
				line = in.nextLine();
				lineNumber++;
				readLabel(line, g, lineNumber);
			}
		} finally {
			in.close();
		}

		return g;
	}

	/**
	 * POST: Reads division rule from the String line, and adds it to the
	 * Organism g. If the division rule is incorrectly formatted in the line, or
	 * the organism already contains a division rule for the precursor cell in
	 * the rule then an InvalidRuleSetException is thrown. Returns the name of
	 * the precursor cell of the division rule.
	 */
	private static String readDivisionRule(String line, Organism g,
			int lineNumber) throws InvalidRuleSetException {
		Scanner ls = new Scanner(line); // a scanner for line

		String precursor = readValidCellType(ls, lineNumber);
		readEqualSign(ls, lineNumber);
		String leftChild = readValidCellType(ls, lineNumber);
		String rightChild = readValidCellType(ls, lineNumber);
		int branchAngle = readBranchAngle(ls, lineNumber);
		if (ls.hasNext()) {
			throw new InvalidRuleSetException("Cell division rule on line: "
					+ lineNumber + " contains extraneous information");
		}

		ls.close();
		if (g.containsDivisionRule(precursor)) {
			throw new InvalidRuleSetException(
					"Duplicate cell division rule detected for " + precursor
							+ " on line " + lineNumber);
		}
		g.addDivisionRule(precursor, leftChild, rightChild, branchAngle);
		return precursor;
	}

	/**
	 * PRE: Input parameters are non-null.
	 * 
	 * POST: Read and returns a valid cell name from Scanner ls; advancing the
	 * Scanner to the next token. An InvalidRuleSetException is thrown if there
	 * is no next token on ls or the next string on ls does not match the
	 * pattern "[a-zA-Z0-9]*".
	 * 
	 */
	private static String readValidCellType(Scanner ls, int lineNumber)
			throws InvalidRuleSetException {
		if (!ls.hasNext()) {
			throw new InvalidRuleSetException("Line " + lineNumber
					+ " is missing a cell name");
		}
		String cell = ls.next();
		if (!cell.matches("[a-zA-Z0-9]*")) {
			throw new InvalidRuleSetException("Line " + lineNumber
					+ " contains invalid cell name: " + cell);
		}
		return cell;
	}

	/**
	 * PRE: Input parameters are non-null.
	 * 
	 * POST: Reads an equal sign from Scanner ls; advancing the Scanner to the
	 * next token. An InvalidRuleSetException is thrown if there is no next
	 * token on ls or the next string on ls is not "=".
	 * 
	 */
	private static void readEqualSign(Scanner ls, int lineNumber)
			throws InvalidRuleSetException {
		if (!ls.hasNext()) {
			throw new InvalidRuleSetException(lineNumber
					+ ": equal sign expected. No more data on line.");
		}
		String e = ls.next();
		if (!e.equals("=")) {
			throw new InvalidRuleSetException(lineNumber
					+ ": equal sign expected but was: " + e);
		}
	}

	/**
	 * POST: Reads and returns a branch angle from Scanner ls; advancing the
	 * Scanner to the next token. An InvalidRuleSetException is thrown if there
	 * is no next token on ls or the next token on ls is not an integer equal to
	 * either "0", "90", "-90" or "180".
	 */
	private static int readBranchAngle(Scanner ls, int lineNumber)
			throws InvalidRuleSetException {
		if (!ls.hasNextInt()) {
			throw new InvalidRuleSetException("Cell division rule on line "
					+ lineNumber + " is missing a branch angle");
		}
		int angle = ls.nextInt();
		if (angle != 0 && angle != -90 && angle != 90 && angle != 180) {
			throw new InvalidRuleSetException("Cell division rule on line "
					+ lineNumber + " has an invalid branch angle: " + angle);
		}
		return angle;
	}

	/**
	 * POST: Reads default cell label from String line and adds it to the
	 * Organism g. If the line containing the default cell label is incorrectly
	 * formatted then an InvalidRuleSetException is thrown.
	 */
	private static void readDefaultLabel(String line, Organism g, int lineNumber)
			throws InvalidRuleSetException {
		Scanner ls = new Scanner(line); // a scanner for line
		char c = readCellLabel(ls, g, lineNumber);

		if (ls.hasNext()) {
			throw new InvalidRuleSetException("Default cell label on line: "
					+ lineNumber + "contains extraneous information");
		}

		ls.close();
		g.addDefaultCellLabel(c);
	}

	/**
	 * POST: Reads the cell label from String line and adds it to the Organism
	 * g. If the line containing the default cell labeling is incorrectly
	 * formatted, or the cell-type named already has an explicit label in
	 * Organism g then an InvalidRuleSetException is thrown.
	 */
	private static void readLabel(String line, Organism g, int lineNumber)
			throws InvalidRuleSetException {
		Scanner ls = new Scanner(line); // a scanner for line
		String cell = readValidCellType(ls, lineNumber);
		readEqualSign(ls, lineNumber);
		char c = readCellLabel(ls, g, lineNumber);

		if (ls.hasNext()) {
			throw new InvalidRuleSetException("Cell labelling on line: "
					+ lineNumber + "contains extraneous information");
		}

		if (g.containsExplicitCellLabel(cell)) {
			throw new InvalidRuleSetException("Duplicate cell label for cell"
					+ cell + " detected on line " + lineNumber);
		}

		ls.close();
		g.addCellLabel(cell, c);
	}

	/**
	 * POST: Reads and returns a single-character cell label from Scanner ls;
	 * advancing the Scanner to the next token. An InvalidRuleSetException is
	 * thrown if there is no next token on ls or the next token on ls is not a
	 * single ASCII character.
	 */
	private static char readCellLabel(Scanner ls, Organism g, int lineNumber)
			throws InvalidRuleSetException {
		if (!ls.hasNext()) {
			throw new InvalidRuleSetException("Cell label on line "
					+ lineNumber + " is missing");
		}
		String label = ls.next();
		if (!label.matches("\\p{ASCII}*") || label.length() != 1) {
			throw new InvalidRuleSetException("Cell label on line "
					+ lineNumber + " is not properly formatted: " + label);
		}
		return label.charAt(0);
	}
}
