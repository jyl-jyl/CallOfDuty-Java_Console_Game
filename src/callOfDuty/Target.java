package callOfDuty;

/**
 * This abstract class describes the characteristics common to all targets.
 * @author Jingyi Li
 *
 */
public abstract class Target {
	
	// instance variables
	/**
	 * An array of length 2 that specifies the coordinate of the head of a target. 
	 * Head means the upper left part of a Target.
	 */
	private int[] coordinate;
	
	/**
	 * The length of the target.
	 */
	private int length;
	
	/**
	 * The width of the target.
	 * width <= length.
	 */
	private int width;
	
	/**
	 * Indicates whether the Target is horizontal or not.
	 */
	private boolean horizontal;
	
	/**
	 * An array of the same size as the target, 
	 *     indicating the number of times a part of the target has been hit.
	 * For example: 
	 *     A horizontal Armory has a hit of int[2][3].
	 *     A vertical Armory has a hit of int[3][2].
	 */
	private int[][] hit;
	
	/**
	 * An instance of Base that the target is placed in.
	 */
	private Base base;
	
	// Constructor
	/**
	 * This constructor sets the length, the width and the base of the Target.
	 * @param length of the Target
	 * @param width of the Target
	 * @param base the Target is placed in
	 */
	public Target (int length, int width, Base base) {
		this.length = length;
		this.width = width;
		this.base = base;
	}
	
	// Methods
	// Getters
	/**
	 * Get the coordinate array.
	 * @return the coordinate array
	 */
	public int[] getCoordinate() { 
		return this.coordinate;
	}
	
	/**
	 * Get if the Target is horizontal.
	 * @return if the Target is horizontal
	 */
	public boolean getHorizontal() {
		return this.horizontal;
	}
	
	/**
	 * Get the hit array.
	 * @return the hit array
	 */
	public int[][] getHit(){
		return this.hit;
	}
	
	/**
	 * Get the length of the Target.
	 * @return the length of Target
	 */
	public int getLength() {
		return this.length;
	}
	
	/**
	 * Get the width of the Target.
	 * @return the width of Target
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Get the base of target
	 * @return the base of target
	 */
	public Base getBase () {
		return this.base;
	}
	
	// Setters
	/**
	 * Set the coordinate array.
	 * @param coordinate to set
	 */
	void setCoordinate(int[]coordinate) {
		this.coordinate = coordinate;
	}
	
	/**
	 * Set the value of horizontal
	 * @param horizontal: boolean value of if the target is placed horizontal to set
	 */
	void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	/**
	 * Set the hit array.
	 * @param hit: hit array of the target size to set
	 */
	void setHit(int[][] hit) {
		this.hit = hit;
	}
	
	
	// Methods
	// Abstract
	/**
	 * Defines the behavior when a target is destroyed.
	 * Some may explode, while some do nothing.
	 */
	abstract void explode();
	
	/**
	 * Returns the type of Target as a String. 
	 * Every specific type of Target (e.g. Armory, Tank, etc.) 
	 *     has to override and implement this method and return the corresponding Target type.
	 * This method is not case sensitive, 
	 *     i.e., “Armory”, “ARMORY” and “armory” are all acceptable.
	 * @return the types of the target as a string.
	 */
	public abstract String getTargetName();
	
	// Other Methods
	/**
	 * If a part of the Target occupies the given row and column and it is not destroyed, 
	 * mark that part of the Target as “hit” 
	 * (in the hit array, index (0,0) indicates the head).
	 * @param row row number the target gots shot at
	 * @param column column number the target gots shot at
	 */
	public void getShot(int row, int column) {
		// if the target has been destroyed,
		// then don't implement this method
		if (this.isDestroyed()) {
			return;
		}
		// if the target is horizontal
		if (this.horizontal) {
			// get the boundary of the target
			int bottomBound = this.coordinate[0] + this.width - 1;
			int rightBound = this.coordinate[1] + this.length - 1;
			// if the target occupies the given position and not destroyed
			if (row >= this.coordinate[0] && row <= bottomBound && 
					column >= this.coordinate[1] && column <= rightBound &&
					this.isDestroyed() == false) {
				// increment the corresponding position in the hit Array by 1
				this.hit[row - this.coordinate[0]][column - this.coordinate[1]] ++;
			}

		} else {
			// if target is vertical 
			int bottomBound = this.coordinate[0] + this.length - 1;
			int rightBound = this.coordinate[1] + this.width - 1;
			// if the target occupies the given position and not destroyed
			if (row >= this.coordinate[0] && row <= bottomBound && 
					column >= this.coordinate[1] && column <= rightBound &&
					this.isDestroyed() == false) {
				// increment the corresponding position in the hit Array by 1
				this.hit[row - this.coordinate[0]][column - this.coordinate[1]] ++;
			}

		}
		
		// if the target is destroyed after this shot
		// print the message
		// increment the destroyedTargetCount in Base Class by 1
		if (this.isDestroyed()) {
			System.out.println ("You have destroyed a " + this.getTargetName() + "!");
			this.getBase().incrementDestroyedTargetCount();

		}
		
	
	}

	/**
	 * Check if every part of the target has been hit
	 * @return true if every part of the Target has been hit, false otherwise.
	 * For Tank, every part of it should be hit twice. 
	 * This method will be overridden in the Tank Class.
	 */
	public boolean isDestroyed() {
		// iterate over the hitArray 
		for (int i = 0; i < this.hit.length; i ++) {
			for (int j = 0; j < this.hit[i].length; j ++) {
				// as long as there is one position that's false or 0, return false
				if (this.hit[i][j] == 0) {
					return false;
				}
			}
			
		}
		// if none of the position is unhit(0), return true
		return true;
	}
	
	/**
	 * Check if the target has been hit at a given coordinate
	 * @param row: number of row of the given coordinate
	 * @param column: number of column of the given coordinate
	 * @return Returns true if the target has been hit at the given coordinate. 
	 * This method is used to print the Base.
	 */
	public boolean isHitAt(int row, int column) {
		// if the hit array at the corresponding position is marked by number > 0
		if (this.hit[row - this.coordinate[0]][column - this.coordinate[1]] > 0) {
			// return true
			return true;
		}
		return false;
	}
	
	/**
	 * @return a single-character String to use in the Base’s print method.
	 * This method should return ”X” if the Target has been destroyed 
	 *     and ”O” (capital letter O) if it has not been destroyed. 
	 *     But for an un-destroyed Tank, it returns “T”.
	 * If the Target is Ground, it returns “-”.
	 * This method can be used to print out locations in the Base that have been shot at (including those hit by explosion); 
	 *     it should not be used to print locations that have not been shot at.
	 *     Since toString behaves exactly the same for all Target types but for Tank and Ground, it is placed here in the Base class. 
	 *     This method in Tank and Ground class should be overridden.
	 */
	public String toString() {
		// return X if is destroyed
		if (this.isDestroyed()) {
			return "X";
		} else {
			// or else return O
			return "O";
		}
		
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
