package part2;

/*
 * PART 2 (QUESTION 3a): Implement this class according to its specification.
 * 
 * You must implement this class yourself, "from scratch", without using any of the Java
 * libraries, or any other libraries. (That precludes, for instance, the use of
 * any Java Collections.) This means that NO IMPORT CLAUSES ARE ALLOWED.
 * 
 * Any extra classes that you write should be included in this file as nested
 * classes.
 * 
 * You should aim to make your implementation efficient.
 * 
 * To help you get your solution right, you should write you own JUnit tests in
 * part2.test.TractTest (some basic tests are included to get you started). Tests
 * that you write will not be marked.
 */

/**
 * A representation of the state of the tract of land that the Scrub Turkey in
 * the ScrubTurkeySimulator runs about and wreaks havoc on.
 * 
 * The tract is of land is bounded on the Western and Northern edge, and extends
 * effectively without limit to the East and South. It is divided into
 * equally-sized square segments of land. Each of these segments of land is
 * uniquely identified by its distance in segments from the Northern and Western
 * boundaries, with the segment in the North-Western-most corner defined as
 * being 0 segments South and 0 segments East. (The other segments are then
 * relative to this.)
 * 
 * Each segment of land may be, from the scrub turkey's perspective, in one of
 * two states: either it may have a Scrub Turkey mound build on it, or it may
 * not. (Such mounds of earth are used to incubate Scrub Turkey eggs.)
 * 
 * The Scrub Turkey itself occupies only one segment of the tract of land at any
 * one time, and is facing either North (Tract.NORTH), East (Tract.EAST), South
 * (Tract.SOUTH) or West (Tract.WEST).
 * 
 * The state of the tract of land as a whole is defined by the state of its
 * constituent segments as well as the segment location and direction of the
 * Scrub Turkey itself.
 * 
 */
public class Tract {

	// BEGIN DO NOT MODIFY THE FOLLOWING DECLARATIONS
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	// Line separator for use in toString() method
	final String LS = System.getProperty("line.separator");

	// END DO NOT MODIFY DECLARATIONS
	
	// Global variables
	// the area the turkey can wander around
	public int grid[][];
	// Direction of the turkey
	public int direction;
	// Next two are current position
	public int south;
	public int east;
	// Next 4 are used for initialising and resizing the tract
	public int basesouth = 0;
	public int baseeast = 0;
	public int maxSouth = 0;
	public int maxEast = 0;
	
	
	
	/**
	 * Creates an empty tract of land for the Scrub Turkey to wreak havoc on.
	 * Initially, turkey mounds are absent from all segments of land in the
	 * tract and the turkey is located on the segment of land that is "south"
	 * squares to South of the Northern boundary and "east" segments to the East
	 * of the Western boundary. The initial direction of the turkey is defined
	 * by the integer d, which is assumed to take a value either Tract.NORTH,
	 * Tract.EAST, Tract.SOUTH or Tract.WEST, denoting North, South, East and
	 * West, respectively.
	 */
	public Tract(int south, int east, int d) {
		this.south = south;
		this.east = east;
		this.maxSouth = south;
		this.maxEast = east;
		gridSetup(0);
		// Only 4 possibilities for direction
		if (d == 0 || d == 1 || d == 2 || d==3) {
			this.direction = d;
		} else {
			// Then an incorrect direction was passed in. Default to east
			this.direction = 1;
		}
	}

	/**
	 * Build a mound at the current tract segment of the Scrub Turkey if one
	 * does not already exist, else changes nothing about the state of tract.
	 */
	public void buildMound() {
		if (!moundPresent()) {
			this.grid[this.south][this.east] = 1;
		}
	}

	/**
	 * If there exists a mound at the current tract segment of the Scrub Turkey,
	 * then it is destroyed, else nothing is changed about the state of the
	 * tract.
	 */
	public void destroyMound() {
		if (moundPresent()) {
			// No mound if grid[south][east] is 0
			this.grid[this.south][this.east] = 0;
		}
	}

	/**
	 * Returns true if there is a mound at the current tract segment of the
	 * Scrub Turkey and false otherwise.
	 */
	public boolean moundPresent() {
		// Mound present if grid[south][east] is set to 1
		if (this.grid[this.south][this.east] == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Changes the direction of the Scrub Turkey to face 90 degrees to the left
	 * of its current direction.
	 */
	public void turnTurkeyLeft() {
		// Only 4 options for direction, so it circles around 0,1,2,3
		if (this.direction == 0) {
			this.direction = 3;
		} else {
			this.direction--;
		}
	}

	/**
	 * Changes the direction of the Scrub Turkey to face 90 degrees to the right
	 * of its current direction.
	 */
	public void turnTurkeyRight() {
		// Only 4 options for direction, so it circles around 0,1,2,3
		if (this.direction == 3) {
			this.direction = 0;
		} else {
			this.direction++;
		}
	}

	/**
	 * Moves the Scrub Turkey forward one tract segment in the direction it is
	 * facing, unless this would mean the turkey moving beyond the Northern or
	 * Western boundary. In the latter two boundary cases the direction of the
	 * turkey is first rotated 180 degrees and then the turkey is moved forward
	 * one segment of the tract in the new direction it is facing. There are no
	 * limits to how far South or East the bird might go.
	 */
	public void dashForward() {
		// On Northern boundary trying to move north
		if (this.direction == 0 && this.south == 0) {
			// rotate 180 degrees
			this.direction = 2;
			this.south++;
			return;
		}
		// On Western boundary trying to move west
		if (this.direction == 3 && this.east == 0) {
			// rotate 180 degrees
			this.direction = 1;
			this.east++;
			return;
		}
		// Move in given direction
		switch (this.direction) {
			case 0:
				this.south--;
				break;
			case 1:
				this.east++;
				if (this.east > this.maxEast) {
					// If the turkey is moving into 'unexplored territory'
					// we need to increase area bounds
					this.maxEast = this.east;
					gridSetup(1);
				}
				break;
			case 2:
				this.south++;
				if (this.south > this.maxSouth) {
					// If the turkey is moving into 'unexplored territory'
					// we need to increase area bounds
					this.maxSouth = this.south;
					gridSetup(1);
				}
				break;
			case 3:
				this.east--;
				break;
		}
		
	}

	/**
	 * Let x be the Southern most distance South the Scrub Turkey has been on
	 * the tract of land since its initialization, and y be the Eastern most
	 * distance the Scrub Turkey has been East on the tract of land, since its
	 * initialization. The string representation of the tract then is of the
	 * form:
	 * 
	 * s0 + LS + s1 + LS + ... + s(x-1) + LS + sx + LS + "Direction: " + D + LS
	 * 
	 * where each si, for 0<= i <= x, is a string representing the state of the
	 * first y+1 segments of land to the East of the Western boundary, that are
	 * a distance of i segments from the Northern boundary. This string si is of
	 * the form:
	 * 
	 * c0 + c2 + ... + c(y-1) + cy
	 * 
	 * where cj, for 0<= j <= y, is a string representation of the segment of
	 * land that is "i" South of the Northern boundary and "j" East of the
	 * Western boundary. That string representation of cj is (note the spaces):
	 * 
	 * "|M|" if there is a mound present and the Scrub Turkey is currently at
	 * that segment; " M " if there is a mound present, but the Scrub Turkey is
	 * not currently located there; "|_|" if there is no mound, but the Scrub
	 * Turkey is currently at that segment; " _ " if there is no mound, or Scrub
	 * Turkey present
	 * 
	 * Finally, "D" is a string representing the current direction of the Scrub
	 * Turkey. It is "north" for Tract.NORTH, "east" for Tract.EAST, "south" for
	 * Tract.SOUTH and "west" for Tract.WEST.
	 * 
	 * For example, after running the following code:
	 * 
	 * Tract t = new Tract(1,1,Tract.EAST); // turkey at (1S,1E) facing east
	 * 
	 * t.buildMound(); // mound built at (1S,1E)
	 * 
	 * t.dashForward(); // run to (1S,2E)
	 * 
	 * t.turnTurkeyLeft(); t.turnTurkeyLeft(); // face west
	 * 
	 * t.dashForward(); // run to (1S,1E)
	 * 
	 * t.turnTurkeyLeft(); // face south
	 * 
	 * t.dashForward(); // run to (2S,1E)
	 * 
	 * t.buildMound(); // mound built at (2,1)
	 * 
	 * String s = tract.toString();
	 * 
	 * String s would be:
	 * 
	 * " _  _  _ " + LS +
	 * 
	 * " _  M  _ " + LS +
	 * 
	 * " _ |M| _ " + LS +
	 * 
	 * "DIRECTION: " + "south" + LS
	 */
	public String toString() {
		String returnVal = "";
		// 4 different possibilities for each space in the tract
		for (int i = 0; i < this.maxSouth + 1; i++) {
			for (int j = 0; j < this.maxEast + 1; j++) {
				// There is a mound and the turkey is on it
				if (this.grid[i][j] == 1 && i == this.south 
						&& j == this.east) {
					returnVal += "|M|";
				}
				// No mound but turkey is present
				else if (this.grid[i][j] == 0 && i == this.south
						&& j == this.east) {
					returnVal += "|_|";
				}
				// Mound but no turkey present
				else if (this.grid[i][j] == 1) {
					returnVal += " M ";
				}
				// Completely empty.
				else {
					returnVal += " _ ";
				}
			}
			returnVal += LS;
		}
		//returnVal += LS;
		switch (this.direction) {
			case 0:
				returnVal += "DIRECTION: north" + LS;
				break;
			case 1:
				returnVal += "DIRECTION: east" + LS;
				break;
			case 2:
				returnVal += "DIRECTION: south" + LS;
				break;
			case 3:
				returnVal += "DIRECTION: west" + LS;
				break;
		}
		return returnVal;
	}
	
	
	/**
	 * Initialises and updates the size of the tract, keeping all previous
	 * values intact.
	 * 
	 * @param update - integer denoting whether or not we are updating or
	 * initialising
	 */
	private void gridSetup(int update) {
		if (update == 0) {
			// Run the setup for the first time
			this.grid = new int[this.maxSouth + 1][this.maxEast + 1];
		} else {
			// Now we need to update instead of setup
			// Create a new grid and set the values up to the size of the
			// old grid to the values that were in the old grid
			final int newgrid[][];
			newgrid = new int[this.maxSouth + 1][this.maxEast + 1];
			for (int i = 0; i < this.basesouth + 1; i++) {
				for (int j = 0; j < this.baseeast + 1; j++) {
					newgrid[i][j] = this.grid[i][j];
				}
			}
			this.grid = newgrid;
		}
		for (int i = this.basesouth + 1; i < this.maxSouth + 1; i++) {
			for (int j = this.baseeast + 1; j < this.maxEast + 1; j++) {
				this.grid[i][j] = 0;
			}
		}
		this.basesouth = this.maxSouth;
		this.baseeast = this.maxEast;
	}

}
