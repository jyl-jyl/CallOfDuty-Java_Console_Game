package callOfDuty;

/**
 * HeadQuarter is a type of Target 
 * HeadQuarter occupies 6 consecutive cells in base
 * @author Jingyi Li
 *
 */
public class HeadQuarter extends Target {

	// instance variables
	/**
	 * Set the static private variable of HeadQuarter length is 1
	 */
	final private static int LENGTH = 6;
	/**
	 * Set the static private variable of HeadQuarter width is 1
	 */
	final private static int WIDTH = 1;
	
	/**
	 * Set the static private variable of HeadQuarter name is "Ground"
	 */
	final static private String NAME = "headQuarter";

	// constructor
	/**
	 * Set the width and length variables to 1 and 6 and the Base variable 
	 *     by calling the constructor in the super class.
	 * @param base: the base of the enemy 
	 */
	public HeadQuarter(Base base) {
		// set the length, width and base using the constructor in superclass Target
		super (HeadQuarter.LENGTH, HeadQuarter.WIDTH, base);
	}
	
	// methods
	/**
	 * Get the name of HeadQuarter
	 * It is case insensitive 
	 */
	@Override
	public  String getTargetName() {
		return HeadQuarter.NAME;
	}
	
	/**
	 * Implement explode() method in Target
	 * HeadQuarter Object does not explode
	 */
	@Override
	public void explode() {
		return;
	}
		

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
