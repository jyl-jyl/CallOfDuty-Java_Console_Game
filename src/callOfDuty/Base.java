

package callOfDuty;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents the base that the game will be played on.
 * All the targets and shooting will be placed on base
 * @author Jingyi Li
 *
 */
public class Base {

	// instance variables 
	
	/**
	 * Keeps a reference to the location of every Target in the game. 
	 * Every location in this array points to a Target, specifically, 
	 *     an instance of a subclass of Target.
	 */
	private Target[][] targets;
	
	/**
	 * The total number of shots fired by the user.
	 */
	private int shotsCount;
	
	/**
	 * The number of targets destroyed.
	 */
	private int destroyedTargetCount;
	

	// constructor 
	/**
	 * Creates an 10x10 ”empty” Base 
	 * (and fills the Targets array with Ground objects). 
	 * Also initializes any game variables, such as how many shots have been fired.
	 */
	public Base() {
		// create an empty 10 by 10 2d Array of Targets 
		this.targets = new Target[10][10];
		// fill in each cell with Ground Object using private method fillWithGround()
		this.fillWithGround(this.targets);
		// initialize shotsCount as 0
		this.shotsCount = 0;
		// initialize the number of targets destroyed as 0
		this.destroyedTargetCount = 0;

	}
	
	
	/**
	 * Fill the given 2d Array of Targets with a Ground Objects
	 * @param targets: the given empty 2d Array
	 */
	public void fillWithGround (Target[][] targets) {
		
		// iterate over the base
		for (int i = 0; i < targets.length; i ++) {
			for (int j = 0; j < targets[i].length; j ++) {
				// initialize a Ground instance
				Ground ground = new Ground (this);
				// assign the ground object to the current position
				targets[i][j] = ground;
				// initiate Ground's Coordinate
				int[] groundCoor = {i, j};
				ground.setCoordinate(groundCoor);
				// initiate Ground's hitarray
			}
		}
	}
	
	/**
	 * Create and place all Targets randomly on the Base
	 * Place larger Target before smaller ones, and place buildings before tanks and oil drums.
	 * There may be no legal place to put a Target in the end
	 */
	public void placeAllTargetRandomly() {
		
	
		// create instances of all targets
		// there are 1 HeadQuarter, 2 Armory, 3 Barracks, 4 SentryTowers, 4 Tanks, 4 OilDrums.
		HeadQuarter headQuarter = new HeadQuarter(this);
		Armory armory1 = new Armory(this);
		Armory armory2 = new Armory(this);
		Barrack barrack1 = new Barrack(this);
		Barrack barrack2 = new Barrack(this);
		Barrack barrack3 = new Barrack(this);
		SentryTower sentryTower1 = new SentryTower(this);
		SentryTower sentryTower2 = new SentryTower(this);
		SentryTower sentryTower3 = new SentryTower(this);
		SentryTower sentryTower4 = new SentryTower(this);
		Tank tank1 = new Tank(this);
		Tank tank2 = new Tank(this);
		Tank tank3 = new Tank(this);
		Tank tank4 = new Tank(this);
		OilDrum oilDrum1 = new OilDrum(this);
		OilDrum oilDrum2 = new OilDrum(this);
		OilDrum oilDrum3 = new OilDrum(this);
		OilDrum oilDrum4 = new OilDrum(this);
		
		// create a new Random Class
		Random rd = new Random();
		

		
		// create an ArrayList of Target to store all the target that have been initialized
		ArrayList <Target> arrayOfTarget = new ArrayList <Target> ();
		// add all the Target instance to the list
		arrayOfTarget.add (headQuarter);
		arrayOfTarget.add (armory1);
		arrayOfTarget.add (armory2);
		arrayOfTarget.add (barrack1);
		arrayOfTarget.add (barrack2);
		arrayOfTarget.add (barrack3);
		arrayOfTarget.add (sentryTower1);
		arrayOfTarget.add (sentryTower2);
		arrayOfTarget.add (sentryTower3);
		arrayOfTarget.add (sentryTower4);
		arrayOfTarget.add (tank1);
		arrayOfTarget.add (tank2);
		arrayOfTarget.add (tank3);
		arrayOfTarget.add (tank4);
		arrayOfTarget.add (oilDrum1);
		arrayOfTarget.add (oilDrum2);
		arrayOfTarget.add (oilDrum3);
		arrayOfTarget.add (oilDrum4);
		
		// iterate over each Target in the ArrayList and try to place them in the Base grid
		for (Target t : arrayOfTarget) {
			// first judge if it is possible to place a certain target in the base
			// using ifCanPlaceTarget
			if (this.ifCanPlaceTarget(t)) {
				// if can legally place the target in base, activate a while loop
				while (true) {
					// generate a random number for row and a random number for column
					int row = rd.nextInt(10);
					int column = rd.nextInt(10);
					// if can place the target at this random location with horizontal
					if (this.okToPlaceTargetAt(t, row, column, true)) {
						// place the target
						this.placeTargetAt(t, row, column, true);
						// end while loop
						break;
					}
					// if can place the target at this random location with vertical
					if (this.okToPlaceTargetAt(t, row, column, false)) {
						// place the target
						this.placeTargetAt(t, row, column, false);
						// end while loop
						break;
					}
				}
				
			}
		}


	}
	
	/**
	 * Based on the given row, column, and orientation to decide whether it is legal to place the target
	 * @param target to place
	 * @param row: row number of the position
	 * @param column: column number of the position
	 * @param horizontal, if the target is placed horizontal or vertical
	 * @return true if it is okay to put the Target with its head at this location; 
	 *         false otherwise.
	 * The buildings must not overlap another Target, 
	 *     or touch another building (vertically, horizontally, or diagonally)
	 * targets must not ”stick out” beyond the base.
	 * Does not actually change either the Target or the Base
	 */
	public boolean okToPlaceTargetAt(Target target, int row, int column, boolean horizontal) {
		
		// for building and non-building type of Target
		// the diagonal corner cell of it should not stick out of the Base grid
		// get the diagonal corner coordinate of the target
		int cornerRow, cornerCol;
		if (horizontal) {
			// calculate the corner coordinate when the target is horizontal
			cornerRow = row + target.getWidth() - 1;
			cornerCol = column + target.getLength() - 1;
		} else {
			// calculate the corner coordinate when the target is vertical
			cornerRow = row + target.getLength() - 1;
			cornerCol = column + target.getWidth() - 1;
		}
		
		// if corner coordinate falls off the grid
		// it is illegal to place the target, then returns false
		if (row < 0 || column < 0) {
			return false;
		}
		if (cornerRow > 9 || cornerCol > 9) {
			return false;
		}
		
		// if target is of building type: headquarter, armory, barrack and sentrytower 
		// (using ifTargetBuilding() method in Helper)
		// the outer layer of the target should not contain cells 
		// that has been included within another building target
		if (Helper.ifTargetBuilding(target)) {
			if (horizontal) {
				// if horizontal
				
				// get the bigger value from (row - 1) and 0
				int topBound = Math.max(row - 1, 0);
				// get the bigger value from (col - 1) and 0
				int leftBound = Math.max(column - 1, 0);
				// get the smaller value from (row + width) and 9
				int bottomBound = Math.min(row + target.getWidth(), 9);
				// get the smaller value from (col + length) and 9
				int rightBound = Math.min(column + target.getLength(), 9);
				
				// iterate over the positions were to occupied by the target
				for (int r1 = row; r1 < row + target.getWidth(); r1 ++) {
					for (int c1 = column; c1 < column + target.getLength(); c1 ++) {
						// if any of the position is not ground, return false
						if (Helper.ifTargetNotGround(this.targets[r1][c1])) {
							return false;
						}
					}
				}
				
				// iterate over its outer-layer of positions
				// from row - 1 to bottomBound and from column - 1 to rightBound
				for (int r2 = topBound; r2 <= bottomBound; r2 ++) {
					for (int c2 = leftBound; c2 <= rightBound; c2 ++) {
						// first exclude the positions actually occupied by the target
						if (r2 >= row && r2 <= (row + target.getWidth() - 1)
								&& c2 >= column && c2 <= (column + target.getLength() - 1)) {
							continue;
						}
						
						// if any position at the outlayer is building, then return false
						if (Helper.ifTargetBuilding(this.targets[r2][c2])) {
							return false;
						}
					}
				}
				

			} else {
				// if vertical
				// get the bigger value from (row - 1) and 0
				int topBound = Math.max(row +- 1, 0);
				// get the bigger value from (col - 1) and 0
				int leftBound = Math.max(column - 1, 0);
				// get the smaller value from (row + length) and 9
				int bottomBound = Math.min(row + target.getLength(), 9);
				// get the smaller value from (col + width) and 9
				int rightBound = Math.min(column + target.getWidth(), 9);
				
				// iterate over the positions were to occupied by the target
				for (int r1 = row; r1 < row + target.getLength(); r1 ++) {
					for (int c1 = column; c1 < column + target.getWidth(); c1 ++) {
						// if any of the position is not ground, return false
						if (Helper.ifTargetNotGround(this.targets[r1][c1])) {
							return false;
						}
					}
				}
				
				// iterate over its outer-layer of positions
				// from row - 1 to bottomBound and from column - 1 to rightBound
				for (int r2 = topBound; r2 <= bottomBound; r2 ++) {
					for (int c2 = leftBound; c2 <= rightBound; c2 ++) {
						// first exclude the positions actually occupied by the target
						if (r2 >= row && r2 <= (row + target.getLength() - 1)
								&& c2 >= column && c2 <= (column + target.getWidth() - 1)) {
							continue;
						}
						
						// if any position at the outer-layer is building, then return false
						if (Helper.ifTargetBuilding(this.targets[r2][c2])) {
							return false;
						}
					}
				}
				
			}
		} else {
			// if the target is tank or oil-Drum
			// if the position is not Ground, then return false
			if (Helper.ifTargetNotGround(this.targets[row][column])) {
				return false;
			}
		}
		
		
		// if none of the above conditions is satisfied, then return true
		return true;
		
	}
	
	/**
	 * Sets the value of the “hit” array, “coordinate” array, and “horizontal” boolean value of the target.
	 * “Put” the Target in the Base. This involves giving values to the coordinate and horizontal instance variables in the Target, 
	 *     and it also involves putting a reference to the Target 
	 *     in each of 1 or more locations in the targets array in the Base object.
	 * @param target: the given target to place in the base
	 * @param row: row number of the header of the target
	 * @param column: column number of the header of the target
	 * @param horizontal: if the target is placed horizontally or vertically 
	 */
	public void placeTargetAt(Target target, int row, int column, boolean horizontal) {
		
		// set the coordinate (header) of the target
		// create a int[2] array
		int[] coordinate = {row, column};
		target.setCoordinate(coordinate);
		
		// set the horizontal of the target
		target.setHorizontal(horizontal);
		
		// set the hit array
		// get the width and length of the target
		int width = target.getWidth();
		int length = target.getLength();
		// if horizontal
		if (horizontal) {
			// set hit array using int[width][length]
			target.setHit(new int[width][length]);
		} else {
			// if vertical, set the hit array using int[length][width]
			target.setHit(new int[length][width]);
		}
		
		// putting reference to all cells that are occupied by the given target in the Target[][] of Base
		if (horizontal) {
			// iterate from row to row + width - 1, and from column to column + length - 1
			for (int r = row; r <= row + target.getWidth() - 1; r ++) {
				for (int c = column; c <= column + target.getLength() - 1; c ++) {
					// assign the target's reference to every cell of the target object
					this.targets[r][c] = target;
					
				}
			}
		}
		
		// if the target is vertical
		if (! horizontal) {
			// iterate from row to row + length - 1, and from column to column + width - 1
			for (int r1 = row; r1 <= row + target.getLength() - 1; r1 ++) {
				for (int c1 = column; c1 <= column + target.getWidth() - 1; c1 ++) {
					// assign the target's reference to every cell of the target object
					this.targets[r1][c1] = target;
				}
			}
		}
		
		
	}
	
	/**
	 * Check if a given location contains a Target (not a Ground)
	 * @param row: row number of the given location
	 * @param column: column number of the given location
	 * @return Returns true if the given location contains a Target(not a Ground), false if it does not.
	 */
	public boolean isOccupied(int row,int column) {
		// get the target reference at the given location
		Target targetAtLocation = this.targets[row][column];
		// decide if this Target is Ground using ifTargetNotGround method in Helper Class
		if (Helper.ifTargetNotGround(targetAtLocation)) {
			// if not ground, then return true
			return true;
		} else {
			// if is ground, then return false
			return false;
		}
	}
	
	
	/**
	 * Get the 2d array containing all references of locations
	 * @return the targets array
	 */
	public Target[][] getTargetsArray() {
		return this.targets;
	}
	
	/**
	 * Check if a target can be possibly placed legally in the base grid
	 * @param target: the given target
	 * @return true if can be placed legally, false otherwise
	 */
	public boolean ifCanPlaceTarget (Target target) {
		
		// iterate over each location in the base
		for (int i  = 0; i < this.targets.length; i ++) {
			for (int j = 0; j < this.targets[i].length; j++) {
				// using okToPlaceTargetAt to decide if it is legal to place the target in the current location
				if (this.okToPlaceTargetAt(target, i, j, true) == true || 
						this.okToPlaceTargetAt(target, i, j, false) == true) {
					return true;
				}
			}
		}
		// if after going through all locations in the base, the target still cannot be placed, return false
		return false;
	}
	
	
	/**
	 * Attack the position specified by the row and the column.
	 * @param row: row number of the position being attacked
	 * @param column: column number of the position being attacked
	 */
	public void shootAt(int row, int column) {
		// get the Target at the location
		Target targetShot = this.targets[row][column];
	
		// call getShot method from corresponding Class
		targetShot.getShot(row, column);	
	}
	
	/**
	 * Check if all targets have been destroyed or have run out of ammunition: game over
	 * @param weapon1 weapon 1 to check left shots
	 * @param weapon2 weapon 2 to check ldft shots
	 * @return true if game is over, false otherwise
	 */
	public boolean isGameOver(Weapon weapon1, Weapon weapon2) {
		// if all targets have been destroyed or all weapons have been launched
		if (this.win() || (weapon1.getShotLeft() == 0 && weapon2.getShotLeft() == 0)) {
			// then game over
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Check if all targets on base have been destroyed 
	 * @return true if all targets have been destroyed
	 */
	public Boolean win() {
		// iterate over all positions of the targets Array
		for (int i = 0; i < this.targets.length; i ++) {
			for (int j = 0; j < this.targets[i].length; j ++) {
				// if there is any position whose target is not ground and  has not been destroyed
				// then return false
				if ((! this.targets[i][j].isDestroyed()) && (Helper.ifTargetNotGround(this.targets[i][j]))) {
					return false;
				
				}
			}
		}
		
		// if all positions are destroyed, return true
		return true;
	}
	
	/**
	 * Print the base.
	 * The top left corner square should be 0,0.
	 * “O” (capital letter O): Used to indicate a location that player has fired upon and hit a target.
	 * “-”: Use ‘-’ to indicate a location that player has fired upon and found nothing there.
	 * “X” (capital letter X): Use ‘X’ to indicate a location containing a destroyed Target.
	 * ‘.’: Use ‘.’ (a period) to indicate a location that you have never fired upon.
	 * “T”: Used to indicate an un-destroyed but hit Tank.
	 */
	public void print() {
		System.out.println();
		// print the column number
		System.out.print ("  ");
		for (int j = 0; j < 10; j ++) {
			System.out.print(j + " ");
		}
		// print each row headed with row number
		for (int i = 0; i < 10; i ++) {
			// start a new line
			System.out.println ("");
			System.out.print(i + " ");
			// iterate over each position at the current row
			for (int k = 0; k < 10; k ++) {
				// if occupied by targets
				if (this.isOccupied(i, k)) {
					// if has not been fired
					if (! this.targets[i][k].isHitAt(i, k)) {
						// print "."
						System.out.print("." + " ");
					} else {
						// if has been hit at, call the toString method
						System.out.print(this.targets[i][k].toString() + " ");
					}
				} else {
					// if not occupied, then it is Ground Object
					// if has been hit, then call the toString method
					if (this.targets[i][k].isHitAt(i, k)) {
						System.out.print(this.targets[i][k].toString() + " ");

					} else {
						// if Ground has not been fired, then print "."
						System.out.print(". ");
					}
				}
			}
		}
		
	}
	
	/**
	 * Returns the number of shots fired
	 * @return the number of shots fired
	 */
	public int getShotsCount() {
		return this.shotsCount;
	}
	
	/**
	 * Increment the shotCount by 1
	 * This method will be called from shootAt(int row, int column) from Weapon class.
	 */
	public void incrementShotsCount() {
		this.shotsCount ++;
	}
	
	/**
	 * Returns the count of destroyed targets
	 * @return the number of destroyed targets
	 */
	public int getDestroyedTargetCount() {
		return this.destroyedTargetCount;
	}
	
	/**
	 * Increment the destroyedTargetCount by 1
	 */
	public void incrementDestroyedTargetCount() {
		this.destroyedTargetCount ++;
	}
		
	/**
	 * Set the destroyedTargetCount by given integer
	 * @param DestroyedTargetCount: the integer to be set as destroyedTargetCount
	 */
	public void setDestroyedTargetCount(int DestroyedTargetCount) {
		this.destroyedTargetCount = DestroyedTargetCount;
	}
			
	
	public static void main(String[] args) {
	

	}


}
