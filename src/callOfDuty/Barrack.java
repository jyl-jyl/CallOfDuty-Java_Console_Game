package callOfDuty;

/**
 * Barrack is a type of Target 
 * Barrack occupies  3 cells in length and 1 cells in width in base
 * @author Jingyi Li
 *
 */

public class Barrack extends Target {
	

	// instance variables
	/**
	 * Set the static private variable of Barrack length is 3
	 */
	final private static int LENGTH = 3;
	/**
	 * Set the static private variable of Barrack width is 1
	 */
	final private static int WIDTH = 1;
	
	/**
	 * Set the static private variable of Barrack name is "Barrack"
	 */
	final static private String NAME = "barrack";

	// constructor
	/**
	 * Set the width and length variables to 2 and 3 and the Base variable 
	 *     by calling the constructor in the super class.
	 * @param base: the base of the enemy 
	 */
	public Barrack(Base base) {
		// set the length, width and base using the constructor in superclass Target
		super (Barrack.LENGTH, Barrack.WIDTH, base);
	}
	
	// methods
	/**
	 * Get the name of Barrack
	 * The name is case insensitive 
	 */
	@Override
	public  String getTargetName() {
		return Barrack.NAME;
	}
	
	/**
	 * Implement explode() method in Target
	 * Barrack does not explode
	 */
	@Override
	public void explode() {
		return;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
