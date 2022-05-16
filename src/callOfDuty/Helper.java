package callOfDuty;
/**
 * This class stores all static helper methods.
 * @author Jingyi Li
 *
 */
public class Helper {

	/**
	 * Check if the given target is one of the building types
	 * @param target: the given target
	 * @return true if target is head quarter, armory, barrack or sentry tower, false otherwise
	 * The name of the type is case insensitive
	 */
	
	public static boolean ifTargetBuilding (Target target) {
		// get the target name using getTargetName method in each of the subclass of Target
		String targetName = target.getTargetName().trim().toLowerCase();
		// if targetName belongs one of the four building names
		if (targetName.equals ("headquarter") ||
				targetName.equals ("armory") ||
				targetName.equals ("barrack") ||
				targetName.equals ("sentrytower")) {
			return true;
		} else {
			// otherwise return false
			return false;
		}
	}
	
	/**
	 * Check if the given target is a type of Target except Ground
	 * @param target: the given target
	 * @return true if the target is not Ground, false otherwise
	 */
	public static boolean ifTargetNotGround (Target target) {
		// get target's name and make it case insensitive 
		String targetName = target.getTargetName().trim().toLowerCase();
		// if targetName is "ground", then return false, otherwise, return true
		if (targetName.equals ("ground")) {
			return false;
		} else {
			return true;
		}
	}
	

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
