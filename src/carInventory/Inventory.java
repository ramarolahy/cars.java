package carInventory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>* * * AWESOME CARSHOP INVENTORY SYSTEM * * *</h1>
 * <p>Program to populate, manage, and print an inventory list for a car dealership.
 * The user uses the program by entering commands on the console. The inventory will
 * reprint on the console after each table modification to show the result.
 * The user can select print option to print the current inventory on a text file
 * called <b>CurrentInventory.txt</b> at any time.<br>
 * <br>
 * The <b>CurrentInventory.txt</b> file is locate in the project folder.</p>
 * <p>Command options are: [ 1:Add Car  2:Delete Car  3:Modify Car  4:Print Inventory  5:Quit Program ]</p>
 * <li>1: Add Car to inventory</li>
 * <li>2: Delete Car from inventory</li>
 * <li>3: Modify Car attribute</li>
 * <li>4: Print current inventory on text file</li>
 * <li>5: Quit the Program</li>
 * <br>
 *
 * @author Mbinintsoa "Ram" Ramarolahy
 * @version 1.0
 */

public class Inventory {
	public final static Scanner scan = new Scanner(System.in);   // Public Scanner object to fix NoSuchElementException.
	public static boolean errorFixed = false;   //  try-catch loop condition. Verifies if exception if fixed

	//Start ArrayList methods ~~~~~~~~~~~~~~~~~~~~

	/**
	 * Adds a new car on the inventory
	 *
	 * @param cars     the inventory ArrayList
	 * @param newEntry new Object of type Car
	 * @return void The method does not return a value
	 */
	public static void addCar(ArrayList<Car> cars, Car newEntry) {
		boolean rightInput = false;
		int carsToAdd = 0;   // Number of cars the user would like to add
		int i = 0;   //iteration counter

		do {
			try {
				System.out.println("How many cars would like to add?");
				System.out.print("--> ");
				carsToAdd = scan.nextInt();
				scan.nextLine();
				rightInput = true;
			} catch (InputMismatchException e) {
				System.out.println("You entered an invalid input");
				scan.nextLine();
			}
		} while (!rightInput);

		for (i = 0; i < carsToAdd; i++) {   // Iterates through creating each car and assigning attributes
			newEntry = new Car();  // Creates newEntry of type Car
			cars.add(newEntry);  // Add newEntry to ArrayList

			if (i >= 1) {   //Changes the prompt message if user wants to add more than 1 car
				System.out.println("Enter info for next car...");
			}
			newEntry.setCarMake();  // Assign newEntry's attributes
			newEntry.setCarModel();
			newEntry.setCarYear();
			newEntry.setCarColor();
			newEntry.setCarMileage();

			System.out.println("");

		}

	}

	/**
	 * Removes car from the inventory list
	 *
	 * @param cars the inventory ArrayList
	 * @return void The method does not return a value
	 */
	public static void deleteCar(ArrayList<Car> cars) {   //Method to delete car from the inventory
		int invNumber;   // Inventory number displayed on the table
		int entryIndex;   // ArrayList index

		System.out.println("Which inventory number would you like to delete?");
		System.out.print("--> ");
		invNumber = scan.nextInt();
		scan.nextLine();
		entryIndex = invNumber - 1;  // Computes the index from the inventory number

		cars.remove(entryIndex);  // remove car entry

	}

	/**
	 * Modifies car attributes from the inventory
	 *
	 * @param cars the inventory ArrayList
	 * @return void The method does not return a value
	 */

	public static void modifyInv(ArrayList<Car> cars) {   //Method to modify car attributes
		boolean checkInput;  // Verifies if the user has made a valid attribute selection
		int carAttribute;     // Car attribute options
		int invNumber;     // Inventory number displayed on the table
		int entryIndex;     // ArrayList index

		System.out.println("Which inventory number would you like to modify?");   // Prompts user to enter the inventory number
		System.out.print("--> ");
		invNumber = scan.nextInt();
		scan.nextLine();   // To prevent skip after integer scan
		entryIndex = invNumber - 1;   // Computes the index from the inventory number

		/*
		 * Iterates through the command prompt until user enters
		 * a valid attribute to modify
		 */
		do {   // Start do-while loop
			System.out.println("Which attribute would you like to modify?");
			System.out.println("1: Make   2: Model   3: Color   4: Year   5: Mileage");
			System.out.print("--> ");
			carAttribute = scan.nextInt();
			scan.nextLine();   // To prevent skip after integer scan

			switch (carAttribute) {
				case 1:
					cars.get(entryIndex).setCarMake();  // Modify car Make
					checkInput = false;
					break;
				case 2:
					cars.get(entryIndex).setCarModel();   // Modify car Model
					checkInput = false;
					break;
				case 3:
					cars.get(entryIndex).setCarColor();   // Modify car Color
					checkInput = false;
					break;
				case 4:
					cars.get(entryIndex).setCarYear();   // Modify car Year
					checkInput = false;
					break;
				case 5:
					cars.get(entryIndex).setCarMileage();   // Modify car Mileage
					checkInput = false;
					break;
				default:
					System.out.println("Please verify your input.");
					checkInput = true;

			}
		} while (checkInput);   // End do-while loop

	}

	/**
	 * Prints the inventory table on the console.
	 *
	 * @param cars the inventory ArrayList
	 * @return void The method does not return a value
	 */

	public static void printCons(ArrayList<Car> cars) {
		int entryIndex;  // index counter

		//Print table header
		System.out.printf("%5s\t%-13s\t%-13s\t%-20s\t%-7s\t%9s\n", "Inv", "Make", "Model", "Color", "Year", "Mileage");
		System.out.println("------\t-------------\t-------------\t--------------------\t-------\t---------\t");
		//Print table entries
		for (entryIndex = 0; entryIndex < cars.size(); entryIndex++) {   // Iterates through each entry and prints on console
			System.out.printf("%5s\t", entryIndex + 1);
			cars.get(entryIndex).print();
		}
		System.out.println("------\t-------------\t-------------\t--------------------\t-------\t---------\t");
	}

	/**
	 * Prints the inventory table on a text file.
	 *
	 * @param cars the inventory ArrayList
	 * @return void The method does not return a value
	 */

	public static void printText(ArrayList<Car> cars) {
		File invFile = new File("CurrentInventory.txt");  // Creates text file CurrentInventory if file does not exist.
		int entryIndex;  // Index counter

		try {
			PrintWriter printInv = new PrintWriter(invFile);

			//Print table header
			printInv.println("\t\t* * * AWESOME CARSHOP INVENTORY SYSTEM * * *");  // Program title
			printInv.println("");
			printInv.println("Dealership Name: .................    Inventory Date: .. / .. / ..  ");
			printInv.println("");
			printInv.printf("%5s\t%-13s\t%-13s\t%-20s\t%-7s\t%-9s\n", "Inv", "Make", "Model", "Color", "Year", "Mileage");
			printInv.println("------\t-------------\t-------------\t--------------------\t-------\t---------\t");
			//Print table entries
			for (entryIndex = 0; entryIndex < cars.size(); entryIndex++) {
				printInv.printf("%5s\t", entryIndex + 1);
				cars.get(entryIndex).printText(printInv);   // Iterates through the entries and prints on the text file
			}
			printInv.println("------\t-------------\t-------------\t--------------------\t-------\t---------\t");

			printInv.close();
		} catch (IOException ex) {  //
			System.out.printf("Error: %s\n", ex);
		}
	}

	//End ArrayList methods ~~~~~~~~~~~~~~~~~~~~

	public static void main(String[] args) {
		Car newCar = new Car();   // Creates variable newCar of type Car for new entries
		boolean maintainInv = true;   // while condition, resets loop until user Quit Program
		boolean commandCheck = true;   // do-while condition, resets loop after each command completion. Exit loop at Quit Program
		int commandEntry = 0;   // switch case option for user command.

		ArrayList<Car> carList = new ArrayList<>();

		System.out.println("\t\t* * * AWESOME CARSHOP INVENTORY SYSTEM * * *");  // Program title
		System.out.println(" ");
		printCons(carList);   // Prints inventory list
		System.out.println(" ");

		while (maintainInv) {  // Start While loop for Command prompt loop

			System.out.println(" ");
			System.out.println("Please enter command ...");   // Prompt user for initial command
			System.out.println("[ 1:Add Car  2:Delete Car  3:Modify Car  4:Print Inventory  5:Quit Program ]");  // Command options

			// START do-while loop to verify user command input.
			do {
				try {   // Stores user command input. Catches input mismatch exception.
					System.out.print("--> ");
					commandEntry = scan.nextInt();
					scan.nextLine();   // fixes skip after integer scan.
					errorFixed = true;
				} catch (InputMismatchException e) {
					System.out.println("You entered an invalid input.");
					System.out.println("Please select between the given command options.");
					scan.next();   // fixes try-catch infinite loop
				}
			} while (!errorFixed);
			// END do-while loop to verify user command input.

			// START DO-WHILE LOOP TO VERIFY USER ACTIVITY IF USE/QUIT
			do {

				switch (commandEntry) {   // START Switch for user command selection
					case 1:                    // Add car
						addCar(carList, newCar);
						printCons(carList);
						commandCheck = false;
						break;
					case 2:                    // Delete car.
						if (carList.size() >= 1) {   // Verifies if inventory has any entry to delete
							deleteCar(carList);
							printCons(carList);
							commandCheck = false;
						} else {   // Throws message if inventory is empty
							System.out.println("The inventory is currently empty.");
							System.out.println(" ");
							commandCheck = false;
						}
						break;
					case 3:                    // Modify car
						if (carList.size() >= 1) {   // Verifies if inventory has any entry to modify
							modifyInv(carList);
							printCons(carList);
							commandCheck = false;
						} else {   // Throws message if inventory is empty
							System.out.println("The inventory is currently empty.");
							System.out.println(" ");
							commandCheck = false;
						}
						break;
					case 4:                    // Print inventory to text file
						printText(carList);
						System.out.println("CurrentInventory.text has been created in the project folder....");
						System.out.println(" ");
						printCons(carList);
						commandCheck = false;
						break;
					case 5:                    // Exit Inventory
						commandCheck = false;
						maintainInv = false;
						break;
					default:                    // Verifies command

						do {   // START do-while loop to verify user command input
							try {
								System.out.println("Please select between the given command options.");
								System.out.print("--> ");
								commandEntry = scan.nextInt();
								scan.nextLine();   // fixes skip after integer scan.
								commandCheck = true;
								errorFixed = true;
							} catch (InputMismatchException e) {
								System.out.println("You entered an invalid input.");
								scan.next();   // fixes try-catch infinite loop
							}
						} while (!errorFixed);   // END do-while loop to verify user command input

				}   // END Switch for user command selection

			} while (commandCheck);
			// END DO-WHILE LOOP TO VERIFY USER ACTIVITY IF USE/QUIT

		}  // END While loop for Command prompt loop

		System.out.println("Exiting program . . .");
		System.out.println("Good bye!");

		scan.close();

	}  // End main method

}  // End Inventory class

































