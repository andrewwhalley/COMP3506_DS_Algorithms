package part2;

/*
 * PART 2 (QUESTION 3a): Implement Tract.java so that this class works!
 */

/**
 * A class to simulate the strange behaviour of a Scrub Turkey witnessed at
 * the University of Queensland.
 */
public class ScrubTurkeySimulator {

	private Tract tract;

	/**
	 * Creates a new tract of land, initially devoid of all mounds of earth, for
	 * the Scrub Turkey to wreak havoc on, and places the turkey "south"
	 * segments of land to the South of the Northern border, and "east" segments
	 * of land to the East of the Western border. Assuming that d takes a value
	 * Tract.NORTH, Tract.EAST, Tract.SOUTH or Tract.WEST, the Scrub Turkey is
	 * set to initially be facing in that direction.
	 */
	public ScrubTurkeySimulator(int south, int east, int d) {
		tract = new Tract(south, east, d);
	}

	/**
	 * Moves the Scrub Turkey once on the tract of land according to its
	 * observed behaviour: if there is a mound present at the Scrub Turkey's
	 * current location on the tract of land, then it is destroyed by the Turkey
	 * in search of food, the Scrub Turkey turns 90 degrees to the right, and
	 * runs forward one tract segment. If there is no mound present, the Scrub
	 * Turkey builds one, turns 90 degrees to the left of its current location,
	 * and runs forward one tract segment.
	 */
	public void move() {
		if (tract.moundPresent()) {
			tract.destroyMound();
			tract.turnTurkeyRight();
			tract.dashForward();
		} else {
			tract.buildMound();
			tract.turnTurkeyLeft();
			tract.dashForward();
		}
	}

	/**
	 * Prints the state of the tract of land that the Turkey has wrought havoc on.
	 */
	public void printTract() {
		System.out.print(tract.toString());
	}

}
