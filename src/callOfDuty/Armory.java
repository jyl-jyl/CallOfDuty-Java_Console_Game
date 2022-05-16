package callOfDuty;

/**
 * Armory is a type of Target 
 * Armory occupies  3 cells in length and 2 cells in width in base
 * @author Jingyi, Li
 *
 */
public class Armory extends Target {
	
	// instance variables
	/**
	 * Set the static private variable of Armory length is 3
	 */
	final private static int LENGTH = 3;
	/**
	 * Set the static private variable of Armory width is 2
	 */
	final private static int WIDTH = 2;
	
	/**
	 * Set the static private variable of Armory name is "Armory"
	 */
	final static private String NAME = "armory";
	
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
	public Armory(Base base) {
		// set the length, width and base using the constructor in superclass Target
		super (Armory.LENGTH, Armory.WIDTH, base);
	}
	
	// methods
	/**
	 * Get the name of Armory
	 * It is case insensitive 
	 */
	@Override
	public  String getTargetName() {
		return Armory.NAME;
	}
	
	/**
	 * Overriding gotShot method in the superclass
	 * Call getShot in superclass, then if the target is destroyed and has not exploded yet
	 * Call explode in this class
	 */
	@Override 
	public void getShot (int row, int column) {
		
		// if targets has been destroyed, don't implement this method
		if (super.isDestroyed()) {
			return;
		}
		super.getShot(row, column);
		
		// call explode only when target has never exploded before
		if (this.explosionCounter == 0) {
			// if the target is destroyed, call the explode() method
			// print out the message
			if (super.isDestroyed()) {
				this.setExplosionCounter(1);
				this.explode();
			}
		}
	}
	
	/**
	 * Implement explode() method in Target
	 * When an armory is destroyed, it will explode and hit a 6x7 (or 7x6) area around it
	 * And the explosion can spread, i.e., an explosion can trigger another explosion.
	 */
	@Override
	public void explode() {
		
		// if horizontal, hit the 6 * 7 area
		if (this.getHorizontal()) {
			// get the boundary 
			int topBound = Math.max(super.getCoordinate()[0] - 2, 0);
			int bottomBound = Math.min(super.getCoordinate()[0] + 3, 9);
			
			int leftBound = Math.max (super.getCoordinate()[1] - 2, 0);
			int rightBound = Math.min (super.getCoordinate()[1] + 4, 9);
			
			// if the tank is destroyed, it will hit the surrounding 5 * 5 area
			// iterate over positions within bounds
			for (int r = topBound; r <= bottomBound; r ++) {
				for (int c = leftBound; c <= rightBound; c ++) {
					// exclude the destroyed tank itself
					if (r >= super.getCoordinate()[0] && r <= super.getCoordinate()[0] + 1 &&
							c >= super.getCoordinate()[1] && c <= super.getCoordinate()[1] + 2 ) {
						continue;
					}
					// call getShot method from superclass
					this.getBase().getTargetsArray()[r][c].getShot(r, c);
					
				}
			}
			
		} else {
			// if target is verticle
			// get the boundary 
			int topBound = Math.max(super.getCoordinate()[0] - 2, 0);
			int bottomBound = Math.min(super.getCoordinate()[0] + 4, 9);
			
			int leftBound = Math.max (super.getCoordinate()[1] - 2, 0);
			int rightBound = Math.min (super.getCoordinate()[1] + 3, 9);

			// if the tank is destroyed, it will hit the surrounding 5 * 5 area
			// iterate over positions within bounds
			for (int r = topBound; r <= bottomBound; r ++) {
				for (int c = leftBound; c <= rightBound; c ++) {
					// exclude the destroyed tank itself
					if (r >= super.getCoordinate()[0] && r <= super.getCoordinate()[0] + 2 &&
							c >= super.getCoordinate()[1] && c <= super.getCoordinate()[1] + 1 ) {
						continue;
					}
					// call getShot method from superclass
					super.getShot(r, c);
					
				}
			}
			
		}

	}
		
	/**
	 * Set the explosionCounter using given integer
	 * @param counter: given number of explosion counter
	 */
	public void setExplosionCounter(int counter) {
		this.explosionCounter = counter;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
