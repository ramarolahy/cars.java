package carInventory;


import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * <h2>Create a Car object!</h2>
 * Car Attributes: Car Make, car Model, Car Color, Car Year, Car Mileage<br>
 * <br>
 * @author Mbinintsoa "Ram" Ramarolahy
 * @param None All Methods have no parameters
 *
 */
public class Car {
	private String carMake  = "";   // Stores Car Make
	private String carModel = "";   // Stores Car Model
	private String carColor = "";   // Stores Car Color
	private int carYear     = 0;    // Stores Car Year
	private int carMileage  = 0;    // Stores Car Mileage

	public void setCarMake() {   // carMake setter method
		String newMake = "";   // Stores new Car Make

		System.out.print("Enter car make: ");
		newMake = Inventory.scan.nextLine();
		carMake = newMake;   // Assigns new Car Make to new Car Object
		Inventory.errorFixed = true;

		return;
	}

	public void setCarModel() {   // carModel setter method
		String newModel = "";   // Stores new Car Model

		System.out.print("Enter car model: ");  
		newModel = Inventory.scan.nextLine();
		carModel = newModel;   // Assigns new Car Model to new Car Object
		return;
	}

	public void setCarColor() {   // carColor setter method
		String newColor = "";   // Stores new Car Color

		System.out.print("Enter car color: ");  
		newColor = Inventory.scan.nextLine();
		carColor = newColor;   // Assigns new Car Color to new Car Object
		return;
	}

	public void setCarYear(){   // carYear setter method
		boolean rightInput = false;   // Verifies if user input is correct
		int newYear = 0;   // Stores new Car Year

		do {   // loops until user input is correct
			try {
				System.out.print("Enter car Year: ");
				newYear = Inventory.scan.nextInt();
				Inventory.scan.nextLine();   // fixes skip after integer scan.
				carYear = newYear;   // Assigns new Car Year to new Car Object
				rightInput = true;   // User input is correct
			} catch (InputMismatchException e) {   // Catches incorrect input from user
				System.out.println ("You entered an invalid input.");
				Inventory.scan.nextLine();
			}
		} while(!rightInput);

		return;
	}

	public void setCarMileage(){   // carMileage setter method
		boolean rightInput = false;   // Verifies if user input is correct
		int newMileage = 0;   // Stores new Car Mileage


		do {   // loops until user input is correct
			try {
				System.out.print("Enter car mileage: ");
				newMileage = Inventory.scan.nextInt();
				Inventory.scan.nextLine();   // fixes skip after integer scan.
				carMileage = newMileage;   // Assigns new Car Mileage to new Car Object
				rightInput = true;   // User input is correct
			} catch (InputMismatchException e) {   // catches incorrect input from user
				System.out.println ("You entered an invalid input.");
				Inventory.scan.nextLine();
			}
		} while(!rightInput);   // End loop

		return;
	}

	public void print(){   // print on console method
		System.out.printf("%-13s\t%-13s\t%-20s\t%-7s\t%9s\n", carMake, carModel, carColor, carYear, carMileage);
	}

	public void printText(PrintWriter printInv){   // Print inventory on text file
		printInv.printf("%-13s\t%-13s\t%-20s\t%-7s\t%9s\n", carMake, carModel, carColor, carYear, carMileage);
	}

	public Car(){   //Car class constructor
		carMake    = "";
		carModel   = "";
		carColor   = "";
		carYear    = 0;
		carMileage = 0;
	}

}





























