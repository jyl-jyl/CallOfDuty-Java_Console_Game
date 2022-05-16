package callOfDuty;

/**
 * Subclass of Weapon, representing a missile
 * Missile hits 3 * 3 area and has an initial count of 3.
 * @author Jingyi Li
 *
 */
public class Missile extends Weapon {
	
	// instance variables
	
	/**
	 * Set the initial missile count to 3
	 */
	final private static int SHOTCOUNT = 3;
	
	/**
	 * Set the weapon type to "Missile"
	 */
	final private static String TYPE = "missile";
	
	// constructor
	/**
	 * Call constructor in superclass with missile's initial count
	 */
	public Missile() {
		super (Missile.SHOTCOUNT);
		
	}
	
	// methods
	
	/**
	 * Get the type of missile
	 * @return the type of missile as "Missile"
	 * It is case insensitive
	 */
	@Override
	public String getWeaponType() {
		return Missile.TYPE;
	}
	
	/**
	 *  Missile will attack a 3x3 area. 
	 *  It will call shootAt(int row, int column) and incrementshotsCount () method in Base class.
	 */
	@Override
	public void shootAt(int row, int column, Base base) {
		// if row and column to shoot at is within the base grid
		if (row >= 0 && row <= 9 && column >= 0 && column <= 9) {
			// fire the missile
			// get the boundary
			int topBound = Math.max(row - 1, 0);
			int bottomBound = Math.min(row + 1, 9);
			int leftBound = Math.max(column - 1, 0);
			int rightBound = Math.min(column + 1, 9);
			// iterate over the 3 * 3 area missile will shoot at, using boundaries
			for (int r = topBound; r <= bottomBound; r ++) {
				for (int c = leftBound; c <= rightBound; c ++) {
					// call ShootAt method for each position
					base.shootAt(r, c);
				}
			}
			
		}
		// call incrementShotsCount in Base Class
		base.incrementShotsCount();
		// decrement shotLeft in superclass
		super.decrementShotLeft();
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
