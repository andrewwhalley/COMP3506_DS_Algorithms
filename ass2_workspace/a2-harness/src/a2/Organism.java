package a2;

/**
 * An instance of this class keeps track of the type of the first cell of an
 * organism (the zygote), the organism's cell-division rules, and
 * ASCII-character labels for cells. Cell types either have an explicit label,
 * or a default label, which are used for printing a representation of the
 * organism during a specified state of its growth.
 * 
 * A cell division rule for given cell type describes how a cell of that type
 * may divide into left and right child cells. The division rule defines the
 * cell-type of the left child and the cell-type of the right child, as well as
 * the branchAngle (either 0, 90, -90 or 180 degrees) of the right child. Cells
 * have an orientation, and the orientation of a cell is either 0, 90, 180, or
 * 270 degrees. The left child of a cell always assumes the orientation of its
 * parent, while the orientation of the right child is (r + branchAngle +
 * 360)%360, where r is the orientation of the parent, and branchAngle is
 * specified by the cell division rule.
 * 
 * A cell that has a type for which there is no cell-division rule is referred
 * to as a differentiated cell; and a cell that has the ability to divide
 * according to a cell-division rule is referred to as a precursor cell. There
 * is at most one cell-division rule for a cell-type.
 * 
 * The cell-type of the zygote and cell-division rules define the organism's
 * lineage tree: a complete history of all the cells in an organism. Each node
 * in such a tree denotes a cell that has a cell type, an orientation, and a
 * location -- defined for simplicity as a pair of integer-valued coordinates.
 * The cell type of the root node is that of the zygote, and has an orientation
 * of 0 degrees, and a location (0,0). Each node in the tree either has zero
 * children, if it is a differentiated cell (i.e. one without a cell-division
 * rule); or it has two children, defined by the (only) division rule for the
 * node's cell type: the left child has the same orientation and location as the
 * parent, and the right child has the orientation of the parent rotated by the
 * number of degrees specified by the cell-division rule (as above).
 * 
 * The location of the right child is specified as (x + dx(o), y + dy(o)), where
 * (x,y) is the location of the parent, o is the orientation of the right child,
 * 
 * dx(0) = 1, dx(90) = 0, dx(180) = -1, dx(270) = 0, and
 * 
 * dy(0) = 0, dy(90) = -1, dy(180) = 0, dy(270) = 1 .
 * 
 * The lineage tree of the organism after k generations is the lineage tree of
 * the organism restricted to a depth of k, (the root node has depth zero), and
 * represents the history of all cells in the organism after k-generations of
 * cell-divisions. The leaves of such a tree represent the cells of the organism
 * after k-generations of cell divisions.
 * 
 * (Note: The notion of a lineage tree as defined in this class, in particular
 * the cell locations, is overly simplistic. )
 * 
 */

public class Organism {

	// REMOVE THIS LINE AND ADD YOUR INSTANCE VARIABLES HERE

	/**
	 * Create a new organism with no specified zygote cell type, no
	 * differentiation rules, and no default label or explicit labels for cell
	 * types.
	 */
	public Organism() {
		// REMOVE THIS LINE AND WRITE THIS METHOD
	}

	/**
	 * PRE: zygote is a string denoting the name of a cell type that matches
	 * regular expression "[a-zA-Z0-9]*".
	 * 
	 * POST: Set the type of the zygote cell to be zygote.
	 */
	public void setZygote(String zygote) {
		// REMOVE THIS LINE AND WRITE THIS METHOD
	}

	/**
	 * PRE: precursor, leftChild and rightChild are strings denoting cell types
	 * that match regular expression "[a-zA-Z0-9]*". Also, branchAngle is either
	 * -90, 0, 90 or 180, and that this.containsDivisionRule(precursor) is
	 * false.
	 * 
	 * POST: Adds division rule for precursor to the set of division rules for
	 * the organism. The division rule states that a cell of type precursor, has
	 * the ability to divide into a left child cell of type leftChild and a
	 * right child cell of type rightChild, so that the orientation of the left
	 * child cell is the same as the precursor cell and the orientation of the
	 * right child cell is the orientation of the precursor cell rotated by
	 * branchAngle degrees. To be explicit, if r is the orientation of a cell of
	 * type precursor (either 0, 90, 180 or 270 degrees), then the orientation
	 * of the right child of precursor, a cell of type rightChild, is defined as
	 * (r+branchAngle+360)%360.
	 */
	public void addDivisionRule(String precursor, String leftChild,
			String rightChild, int branchAngle) {
		// REMOVE THIS LINE AND WRITE THIS METHOD
	}

	/**
	 * PRE: cell is a string, denoting the type of a cell, that matches regular
	 * expression "[a-zA-Z0-9]*".
	 * 
	 * POST: Returns true if the organism contains a division rule for the cell
	 * named precursor; false otherwise
	 */
	public boolean containsDivisionRule(String precursor) {
		return false; // REMOVE THIS LINE AND WRITE THIS METHOD
	}

	/**
	 * PRE: cell is a string, denoting the type of a cell, that matches regular
	 * expression "[a-zA-Z0-9]*". label is an ascii character, and
	 * this.containsExplicitCellLabel(cell) is false.
	 * 
	 * POST: Sets the label of cell to be the ascii character label.
	 */
	public void addCellLabel(String cell, char label) {
		// REMOVE THIS LINE AND WRITE THIS METHOD
	}

	/**
	 * PRE: label is an ascii character.
	 * 
	 * POST: Sets the default label of any cell type without an explicit label
	 * to be the ascii character label.
	 */
	public void addDefaultCellLabel(char label) {
		// REMOVE THIS LINE AND WRITE THIS METHOD
	}

	/**
	 * PRE: cell is string, denoting the name of a cell type, matching regular
	 * expression "[a-zA-Z0-9]*".
	 * 
	 * POST: returns true if a label has already been added for cell; false
	 * otherwise.
	 */
	public boolean containsExplicitCellLabel(String cell) {
		return false; // REMOVE THIS LINE AND WRITE THIS METHOD
	}

	/**
	 * PRE: The organism has a zygote cell type, and a default cell label.
	 * Integer k is non-negative.
	 * 
	 * POST: Returns a string s (defined below) describing a bracketed preorder
	 * traversal, including non-zero relative orientations, of the organism's
	 * lineage tree after k generations.
	 * 
	 * Let LTk stand for the lineage tree of the organism restricted to a depth
	 * of k. String s is defined as PRETraversal(root), where root is used to
	 * abstractly represent the root node of LTk.
	 * 
	 * Let n be an arbitrary node of LTk with cell type n.type (of type String);
	 * left and right children nodes n.left and n.right, if they exist; and
	 * orientation n.orientation. We define PRETraversal(n) recursively as
	 * follows
	 * 
	 * PRETraversal(n) = n.type , if n is a leaf node of LTk, or
	 * 
	 * PRETraversal(n) = n.type + " ( " + PRETraversal(n.left) + " ) " +
	 * BRANCHANGLESTRING + "( " + PRETraversal(n.right) + " )" ,
	 * 
	 * if n is not a leaf node of LTk, and BRANCHANGLESTRING is either the empty
	 * string "" if (n.right).orientation = n.orientation, or the branch angle
	 * of the right child with respect to its parent followed by one additional
	 * space character (i.e. either "90 ", "-90 " or "180 "), otherwise.
	 */
	public String lineageTreePreorderTraversal(int k) {
		return null; // REMOVE THIS LINE AND WRITE THIS METHOD
	}

	/**
	 * PRE: The organism has a zygote cell type, and a default cell label.
	 * Integer k is non-negative.
	 * 
	 * POST: Returns a String s (defined below), describing the final set of
	 * cells, including non-zero relative orientations and only brackets
	 * relating to non-zero orientations, of the organism after k generations of
	 * cell divisions.
	 * 
	 * Let LTk stand for the lineage tree of the organism restricted to a depth
	 * of k. String s is defined as finalCells(root), where root is used to
	 * abstractly represent the root node of LTk.
	 * 
	 * Let n be an arbitrary node of LTk with cell type n.type (of type String);
	 * left and right children nodes n.left and n.right, if they exist; and
	 * orientation n.orientation. We define finalCells(n) recursively as follows
	 * 
	 * finalCells(n) = n.type , if n is a leaf node of LTk
	 * 
	 * finalCells(n) = finalCells(n.left) + " " + finalCells(n.right) ,
	 * 
	 * if n is not a leaf node of LTk and (n.right).orientation = n.orientation,
	 * or
	 * 
	 * finalCells(n) = finalCells(n.left) + " " + BRANCHANGLE + " ( " +
	 * finalCells(n.right) + " )" ,
	 * 
	 * if n is not a leaf node of LTk, (n.right).orientation != n.orientation,
	 * and BRANCHANGLE is the branch angle of the right child with respect to
	 * its parent (i.e. either "90", "-90" or "180").
	 */
	public String lineageTreeFinalCellTraversal(int k) {
		return null; // REMOVE THIS LINE AND WRITE THIS METHOD
	}

	/**
	 * PRE: The organism has a zygote cell type, and a default cell label.
	 * Integer k is non-negative.
	 * 
	 * POST: Let LTk stand for the lineage tree of the organism restricted to a
	 * depth of k.
	 * 
	 * An OutOfBoundsException is thrown if there exists leaf node in LTk that
	 * has a location (x,y) such that either x<-10, x>10, y<-10 or y>10.
	 * 
	 * Otherwise, this method returns a 21*21 array of characters, that we will
	 * refer to as grid, depicting the organism after k-generations of cell
	 * divisions (as described below).
	 * 
	 * For each i and j such that 0<=i<21, 0<=j<21, we define
	 * 
	 * grid[i][j] = ' ',
	 * 
	 * if there is no leaf node in LTk that has a location (j-10,i-10), or
	 * 
	 * grid[i][j] = c,
	 * 
	 * where c is the ascii character label of the last leaf node found during a
	 * preorder traversal of LTk that has location (j-10, i-10). The ascii
	 * character label c of a node of cell-type "type", is either the explicit
	 * character label for "type", if one exists, or the default cell label
	 * otherwise.
	 * 
	 */
	public char[][] getFinalOrganism(int k) throws OutOfBoundsException {
		return new char[21][21]; // REMOVE THIS LINE AND WRITE THIS METHOD
	}

}
