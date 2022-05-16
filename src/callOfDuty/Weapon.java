package callOfDuty;

/**
 * Abstract class with subclasses of two types of weapons: missile and rocketLauncher
 * @author Jingyi Li
 *
 */
public abstract class Weapon {

	// instance variables
	/**
	 * The number of shots left. 
	 * Initially, it’s 20 for RocketLauncher and 3 for Missile.
	 */
	private int shotLeft;
	
	// constructor
	/**
	 * Initiate the weapon with shotCount 
	 * @param initialCount: given count of shot
	 */
	public Weapon (int initialCount) {
		this.shotLeft = initialCount;
	}
	
	// methods
	/**
	 * Get shot left
	 * @return the shot left
	 */
	public int getShotLeft() {
		return this.shotLeft;
	}
	
	// abstract methods
	/**
	 * Get the type of weapon
	 * @return RocketLauncher will return “rocketLauncher”,
	 *     Missile will return “missile”
	 *     It is case insensitive.
	 */
	public abstract String getWeaponType();
	
	/**
	 * RocketLauncher will only shoot at one square, 
	 *     while Missile will attack a 3x3 area. 
	 * @param row: row number to shoot at
	 * @param column: column number to shoot at
	 * @param base: the base of enemy
	 * It will call shootAt(int row, int column) and incrementshotsCount () method in Base class.
	 */
	public abstract void shootAt(int row, int column, Base base);
	
	// other methods
	/**
	 * Decrement shot by 1.
	 */
	public void decrementShotLeft() {
		// if shotLeft >= 1 0 then decrement by 1, or don't do anything
		if (this.shotLeft >= 1) {
			this.shotLeft -= 1;
		}

	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
