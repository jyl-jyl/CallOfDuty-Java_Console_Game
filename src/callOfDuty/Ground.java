package callOfDuty;

/**
 * Ground is a type of Target 
 * Ground denotes the absence of Target within the base
 * @author Jingyi Li
 *
 */
public class Ground extends Target {

	// instance variable 
	/**
	 * Set the static private variable of ground length is 1
	 */
	final static private int LENGTH = 1;
	
	/**
	 * Set the static private variable of ground width is 1
	 */
	final static private int WIDTH = 1;
	
	/**
	 * Set the static private variable of ground name is "Ground"
	 */
	final static private String NAME = "ground";
	
	/**
	 * Set the static private variable of ground's hit flag
	 */
	private int hit = 0;

	// constructor 
	/**
	 * Set the width and length variables to 1 and the Base variable 
	 *     by calling the constructor in the super class.
	 * @param base: the base of the enemy 
	 */
	public Ground (Base base) {
		// set the length, width and base using the constructor in superclass Target
		super (Ground.LENGTH, Ground.WIDTH, base);
	}
	
	// methods
	/**
	 * Get the name of Ground
	 * The name is case insensitive 
	 */
	@Override
	public  String getTargetName() {
		// make the NAME case insensitive 
		return NAME;
	}
	
	/**
	 * @return Always returns false 
	 * Ground cannot be destroyed.
	 */
	@Override
	public boolean isDestroyed() {
		return false;
	}
	
	/**
	 * @return a single-character String to use in the Base’s print method.
	 * If the Target is Ground, it returns “-”.
	 */
	public String toString() {
		return "-";
		
	}
	
	
	
	/**
	 * Implement explode() method in Target
	 * Ground Object does not explode
	 */
	@Override
	public void explode() {
		return;
	 
	}
	

	/**
	 * Overriding the getShot method in superclass Target
	 * If the Ground Object is hit, increment its only element in the hitArray by 1
	 */
	@Override
	public void getShot(int row, int column) {
		// if the row and column equals the target's coordinate
		if (row == super.getCoordinate()[0] && column == super.getCoordinate()[1]) {
			// if the Ground Object's hit Array is 0, then increment it 1
			if (this.hit == 0) {
				this.hit += 1;
			}
		}

	}
	
	/**
	 * Overriding the isHitAt method in superclass
	 * @return true if the hit Array is > 0, otherwise false
	 */
	@Override
	public boolean isHitAt(int row, int column) {
		// if the hit array is > 0 && row and column equals the target's coordinate , then return true
		if (this.hit >0 && row == super.getCoordinate()[0] && column == super.getCoordinate()[1]) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Get the hit number of single target 
	 * @return hit
	 */
	public int getHitSingleTarget() {
		return this.hit;
	}	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
