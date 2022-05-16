
package callOfDuty;

/**
 * Subclass of Weapon, representing a rocketLauncher
 * RocketLauncher hit 1 * 1 area and has initial count of 20.
 * @author Jingyi Li
 *
 */

public class RocketLauncher extends Weapon {

	// instance variables
	
	/**
	 * Set the initial missile count to 1
	 */
	final private static int SHOTCOUNT = 20;
	
	/**
	 * Set the weapon type to "RocketLauncher"
	 */
	final private static String TYPE = "rocketLauncher";
	
	// constructor
	/**
	 * Call constructor in superclass with rocketlauncher's initial count
	 */
	public RocketLauncher() {
		super (RocketLauncher.SHOTCOUNT);
		
	}
	
	// methods
	
	/**
	 * Get the type of RocketLauncher
	 * @return the type of RocketLauncher as "RocketLauncher"
	 * It is case insensitive
	 */
	@Override
	public String getWeaponType() {
		return RocketLauncher.TYPE;
	}
	
	/**
	 *  RocketLauncher will only shoot at one square. 
	 *  It will call shootAt(int row, int column) and incrementshotsCount () method in Base class.
	 */
	@Override
	public void shootAt(int row, int column, Base base) {
		// if row and column is within base grid
		if (row >= 0 && row <= 9 && column >= 0 && column <= 9) {
			// shoot at the one square, call the shootAt method in Base class
			base.shootAt(row, column);
		}

		// call the incrementshotsCount method in Base Class
		base.incrementShotsCount();
		// decrement shotLeft in superclass
		super.decrementShotLeft();
		
	}
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
