package callOfDuty;

/**
 * SentryTower is a type of Target 
 * Armory occupies  1 cells in base
 * @author Jingyi Li
 *
 */

public class SentryTower extends Target {
	
	// instance variables
	/**
	 * Set the static private variable of SentryTower length is 1
	 */
	final private static int LENGTH = 1;
	/**
	 * Set the static private variable of SentryTower width is 1
	 */
	final private static int WIDTH = 1;
	
	/**
	 * Set the static private variable of SentryTower name is "SentryTower"
	 */
	final static private String NAME = "sentryTower";

	/**
	 * Set the static private variable of sentrytower's hit flag
	 */
	private int hit = 0;
	
	// constructor
	/**
	 * Set the width and length variables to 1 and 1 and the Base variable 
	 *     by calling the constructor in the super class.
	 * @param base: the base of the enemy 
	 */
	public SentryTower(Base base) {
		// set the length, width and base using the constructor in superclass Target
		super (SentryTower.LENGTH, SentryTower.WIDTH, base);
	}
	
	// methods
	/**
	 * Get the name of SentryTower
	 * The name is case insensitive 
	 */
	@Override
	public  String getTargetName() {
		return SentryTower.NAME;
	}
	
	/**
	 * Implement explode() method in Target
	 * Sentry tower does not explode
	 */
	@Override
	public void explode() {
		return;
	}
	
	/**
	 * Overriding the getShot method in superclass Target
	 * If the sentry tower is hit, increment its only element in the hitArray by 1
	 */
	@Override
	public void getShot(int row, int column) {
		// if the target has been destroyed, don't implement this method
		if (this.isDestroyed()) {
			return;
		}
		
		// if the Ground Object's hit Array is 0, then increment it 1
		if (this.hit == 0) {
			this.hit += 1;
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
	 * Overriding the isHitAt method in superclass
	 * @return true if the hit Array is > 0, otherwise false
	 */
	@Override
	public boolean isHitAt(int row, int column) {
		// if the hit array is > 0, then return true
		if (this.hit >0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Check if a tank has been destroyed
	 * @return true if a sentry tower was hit once
	 */
	@Override
	public boolean isDestroyed() {
		if (this.hit >= 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Get the hit number of single target 
	 * @return hit of sentry tower
	 */
	public int getHitSingleTarget() {
		return this.hit;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
