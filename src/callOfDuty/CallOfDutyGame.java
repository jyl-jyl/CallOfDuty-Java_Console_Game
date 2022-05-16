package callOfDuty;

import java.util.Scanner;

/**
 * Contains the main method to control the logistics of the game.
 * Contains all the inputs and outputs.
 * Contains only static private methods and variables to aid the implement of main method.
 * @author Jingyi Li
 * PennID: 60370578
 * Statement: I worked alone on this project. I refered to these posts on Piazza:
 *            https://piazza.com/class/ksesb4ullr51zo?cid=685
 *            https://piazza.com/class/ksesb4ullr51zo?cid=648
 *
 */
public class CallOfDutyGame {
	
	// static variable
	/**
	 * set the weapon used by user as static variable
	 */
	private static Weapon WEAPON;
	
	// static private methods
	/**
	 * set the weapon used by player
	 * @param weaponToSet: weapon to be used by player
	 */
	private static void setWeapon(Weapon weaponToSet) {
		CallOfDutyGame.WEAPON = weaponToSet;
	}

	
	/**
	 * print all welcome messages and rules before the game
	 */
	private static void printWelcomeAndRules() {
		// print welcome messages
		System.out.println("Welcome to the CallOfCuty game!");
		System.out.println();

		// inform the user of the rules of game
		System.out.println("Your mission is to destroy all targets in the 10 * 10 enemy base!\n"
				+ "\n"
				+ "There are 6 types of targets in the base \n"
				+ "(◼︎ indicates the size of the targets and each target type can be positioned horizontally or vertically) \n"
				+ "\n"
				+ "One HeadQuarter:   ◼ ◼ ◼ ◼ ◼ ◼ \n"
				+ "\n"
				+ "Two Armory:        ◼ ◼ ◼   ◼ ◼ ◼\n"
				+ "                   ◼ ◼ ◼   ◼ ◼ ◼\n"
				+ "\n"
				+ "Three Barracks:    ◼ ◼ ◼   ◼ ◼ ◼   ◼ ◼ ◼\n"
				+ "\n"
				+ "Four Sentrytowers: ◼   ◼   ◼   ◼\n"
				+ "\n"
				+ "Four Tanks:        ◼   ◼   ◼   ◼\n"
				+ "\n"
				+ "Four Oildrums:     ◼   ◼   ◼   ◼︎");
		System.out.println();
		System.out.println("A target is destroyed only when all of the positions occupied are hit at.\n"
				+ "(For tank, the target is only destroyed when it is hit twice.)");
		System.out.println();
		System.out.println("When Armory is destroyed, it will explode and hit a 6 * 7 (horizontal) or a 7 * 6 (vertical) area aournd it.\n"
				+ "(When a tank or an oildrum is destroyed, it will explode and hit a 5 * 5 area around it.");
		System.out.println();
		System.out.println("The object is to destroy all targets with as few shots as possible.\n"
				+ "But you can only shoot a rocket launcher 20 times and a missile 3 times.\n"
				+ "a rocket launcher will hit a 1 * 1 area and a missile 3 * 3 area.");
		System.out.println();
		System.out.println("If all targets are destroyed before you run out of ammunition, you win. Otherwise, you lose.\n"
				+ "You can switch weapons. Each time before you shoot, you will be informed how many shots are left.");
		System.out.println();
		System.out.println("At first the base is covered with mist, but once you started shooting, you will see your hits and misses: \n"
				+ "O : A location you have fired upon and hit a target\n"
				+ "- : A location you have fired upon and found nothing there\n"
				+ "X : A location containing a destroyed Target\n"
				+ ". : A location that you have never fired upon\n"
				+ "T : an undestroyed but hit Tank");
		System.out.println();
		

	}
			

	/**
	 * ask yes or no
	 * @param input: the question for user
	 * @param sc: Scanner
	 * @return true if input Y or y (whitespace included), false if N or n (whitespace included)
	 * ask for input again when encountering invalid input
	 */
	private static boolean askYesOrNo(String input, Scanner sc) {
		System.out.println (input + " (\"Y\" or \"y\" for yes and \"N\" or \"n\" for no)");
		while (true) {
			String ifYesOrNo = sc.next();
			// if user input Y or y with any white spaces, then return true
			if (ifYesOrNo.strip().toLowerCase().equals ("y")) {
				return true;
			} else if (ifYesOrNo.strip().toLowerCase().equals ("n")) {
				// if user input N or n with any white spaces, then return false
				return false;
			} else {
				// otherwise ask for user input again
				System.out.println("Invalid input, please re-enter:  ");
			}

		}

		
	}
	
	/**
	 * print the shot left for rocket launcher and missile
	 * print current weapon type
	 * print the number of destroyed target
	 * @param rl: rocket launcher for the game
	 * @param mis: missile for the game
	 * @param base base the targets placed on
	 * @param ifSwitchWeapon: boolean value for if switching weapon 
	 */
	private static void printWeaponInfo (RocketLauncher rl, Missile mis, boolean ifSwitchWeapon, Base base) {
		System.out.println ();

		System.out.println ("Rocket Launcher Left: " + rl.getShotLeft());
		System.out.println ("Missile Left: " + mis.getShotLeft());
		System.out.println ("Targets destroyed: " + base.getDestroyedTargetCount());

		
		// if user did not switch weapon, set the weapon to rocket launcher
		if (! ifSwitchWeapon) {
			System.out.println ("Your current weapon is: " + WEAPON.getWeaponType());
		} else {
			// if user switched weapon, set the weapon to the other one and print it
			if (WEAPON.getWeaponType().toLowerCase().equals("rocketlauncher")) {
				CallOfDutyGame.setWeapon(mis);
			} else {
				CallOfDutyGame.setWeapon(rl);
			}
			System.out.println ("Your current weapon is: " + WEAPON.getWeaponType());
		}

	}
	
	
	public static void main(String[] args) {
		// initialize a scanner
		Scanner sc = new Scanner(System.in);
		// print welcome messages and rules
		CallOfDutyGame.printWelcomeAndRules();
		// ask user if they want start the game
		boolean ifStartGame = CallOfDutyGame.askYesOrNo("Want to start the game?", sc);
		// while user wants to start the game
		while (ifStartGame) {
			// create a base
			Base base = new Base();
			
			// create weapons
			RocketLauncher rl = new RocketLauncher();
			Missile mis = new Missile();
			// set default weapon to rocket launcher
			CallOfDutyGame.setWeapon(rl);
			// set ifSwitchWeapon to false
			boolean ifSwitchWeapon;
			
			// place all the targets
			base.placeAllTargetRandomly();
			
			// initiate row and column to store the coordinate user wants to shoot at
			int row;
			int column;
			
			// set loop for game 
			while (! base.isGameOver(rl, mis)) {
				// display the base
				base.print();
				ifSwitchWeapon = false;
				// print the weapon
				CallOfDutyGame.printWeaponInfo(rl, mis, ifSwitchWeapon, base);
				// ask if the use want to shoot at a particular position or to switch weapon
				while (true) {
					System.out.println ("Enter row, column or q to switch weapon: ");
					// next the next token
					String next = sc.next();
					// if user enters "q" or "Q" with white spaces included
					if (next.strip().toLowerCase().equals("q")) {
						// call printWeaponInfo and switch weapon
						ifSwitchWeapon = true;
						CallOfDutyGame.printWeaponInfo(rl, mis, ifSwitchWeapon, base);
						continue;
					} else {
						// if user enters int for row and column, strip and split the String with ","
						String[] arrayString = next.strip().split(",");
						// parse each String in the array to integer as row and column
						row = Integer.parseInt(arrayString[0].strip());
						column = Integer.parseInt(arrayString[1].strip());
						
						// if current weapon running out of ammunition
						// then print the weapon and ask user again to switch weapon
						if (CallOfDutyGame.WEAPON.getShotLeft() == 0) {
							System.out.println ("No ammunition!");
							CallOfDutyGame.printWeaponInfo(rl, mis, ifSwitchWeapon, base);
							continue;
						} 
						break;

					}
				}
				
				// call shootAt method in Weapon Class's subclass
				CallOfDutyGame.WEAPON.shootAt(row, column, base);
				
				
				// check if the user wins
				if (base.win()) {
					// print out the message
					System.out.println ();
					System.out.println ("Congratulations! You won!");
					System.out.println ("Here is the final result: ");
					System.out.println ("-----------------------------------------------------");
					// print the base again
					base.print();
					// print the ammunition and targets destroyed count
					System.out.println ();
					System.out.println ("Rocket Launcher Left: " + rl.getShotLeft());
					System.out.println ("Missile Left: " + mis.getShotLeft());
					System.out.println ("Targets destroyed: " + base.getDestroyedTargetCount());
					break;
				}
				
				// if user has run out of ammunition, then they lose
				// print the message and exit the loop
				if (rl.getShotLeft() == 0 && mis.getShotLeft() == 0) {
					System.out.println ("You have run out of both weapons, you lose!");
					break;
				}
				
			}
			
			// ask the user if they want to play again
			ifStartGame = CallOfDutyGame.askYesOrNo("Want to play another round?", sc);

		}
		
		// close scanner
		sc.close();

	}

}
