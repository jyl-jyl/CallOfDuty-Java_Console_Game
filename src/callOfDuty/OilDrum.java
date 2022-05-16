package callOfDuty;
/**
 * OilDrum is a type of Target 
 * OilDrum occupies 1 cell in base
 * @author Jingyi Li
 *
 */
public class OilDrum extends Target {

	// instance variables
	/**
	 * Set the static private variable of OilDrum length is 1
	 */
	final private static int LENGTH = 1;
	/**
	 * Set the static private variable of OilDrum width is 1
	 */
	final private static int WIDTH = 1;
	
	/**
	 * Set the static private variable of OilDrum name is "OilDrum"
	 */
	final static private String NAME = "oilDrum";
	
	/**
	 * Set the static private variable of oilDrum's hit flag
	 */
	private int hit = 0;
	
	/**
	 * Set the counter for explosion time as 0
	 */
	private int explosionCounter;

	// constructor
	/**
	 * Set the width and length variables to 2 and 3 and the Base variable 
	 *     by calling the constructor in the super class.
	 * @param base: the base of the enemy 
	 */
	public OilDrum(Base base) {
		// set the length, width and base using the constructor in superclass Target
		super (OilDrum.LENGTH, OilDrum.WIDTH, base);
	}
	
	// methods
	/**
	 * Get the name of OilDrum
	 * The name is case insensitive 
	 */
	@Override
	public  String getTargetName() {
		return OilDrum.NAME;
	}
	
	/**
	 * Implement explode() method in Target
	 * When an OilDrum is destroyed, it will explode and hit a 5x5 area around it.
	 */
	@Override
	public void explode() {
		// get the boundary 
		int topBound = Math.max(super.getCoordinate()[0] - 2, 0);
		int bottomBound = Math.min(super.getCoordinate()[0] + 2, 9);
		
		int leftBound = Math.max (super.getCoordinate()[1] - 2, 0);
		int rightBound = Math.min (super.getCoordinate()[1] + 2, 9);
		
		// iterate over row - 2 to row + 2, from col - 2 to col + 2
		for (int r = topBound; r <= bottomBound; r ++) {
			for (int c = leftBound; c <= rightBound; c ++) {
				// exclude the destroyed tank itself
				if (r == super.getCoordinate()[0] && c == super.getCoordinate()[1]) {
					continue;
				}
				// call getShot method from superclass
				this.getBase().getTargetsArray()[r][c].getShot(r, c);
				
			}
		}

	
	}
	
	/**
	 * Overriding the getShot method in superclass Target
	 * If the oil drum Object is hit, increment its only element in the hitArray by 1
	 */
	@Override
	public void getShot(int row, int column) {
		// if the target has been destroyed, then don't implement this method
		if (this.isDestroyed()) {
			return;
		}
		//if the row and column equal the oildrum's coordinate
		if (row == super.getCoordinate()[0] && column == super.getCoordinate()[1]) {
			// if the Ground Object's hit Array is 0, then increment it 1
			if (this.hit == 0) {
				this.hit += 1;
			}
		}

		// if the target is destroyed after this shot
		// increment the destroyedTargetCount in Base Class by 1
		if (this.isDestroyed()) {
			System.out.println ("You have destroyed a " + this.getTargetName() + "!");
			this.getBase().incrementDestroyedTargetCount();

		}
		// call explode only when target has never exploded before
		if (this.explosionCounter == 0) {
			// if the target is destroyed, call the explode() method
			if (this.isDestroyed()) {
				this.setExplosionCounter(1);
				this.explode();
			}
		}
	
	}
	
	/**
	 * Overriding the isHitAt method in superclass
	 * @return true if the hit Array is > 0, otherwise false
	 */
	@Override
	public boolean isHitAt(int row, int column) {
		// if the hit array is > 0 and row and column equal oildrum's coordinate, then return true
		if (this.hit >0 && row == super.getCoordinate()[0] && column == super.getCoordinate()[1]) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Check if a tank has been destroyed
	 * @return true if a oil drum tower was hit once
	 */
	@Override
	public boolean isDestroyed() {
		if (this.hit >= 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Set the explosionCounter using given integer
	 * @param counter: given number of explosion counter
	 */
	public void setExplosionCounter(int counter) {
		this.explosionCounter = counter;
	}
	
	/**
	 * Get the hit number of single target 
	 * @return hit of oil drum
	 */
	public int getHitSingleTarget() {
		return this.hit;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
