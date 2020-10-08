package games;

import java.util.Scanner;

public class Sudoku_Light {

	/**
	 * @author Bob Breckenridge
	 * UCSC Extension
	 * Summer Semester, 2012
	 * Course Number 6634.(057) 
	 * Course Title:  Java Programming- Comprehensive  
	 * Homework #1 - Soduku Light
	 * Due:  Jul 8, 2012 11:55 pm 
	 */
	
	public static void main(String[] args) {
		 
		Integer integerBoardSize = getBoardSize ();										
		
		Character columnOrRowChar = getOrientation ();									
				
		int [][] integer2DarrayBoard = getData (integerBoardSize);						
		
		Integer arrayLength = DisplayBoard (integer2DarrayBoard, columnOrRowChar, integerBoardSize);
		
		boolean validBoard = ValidateSudokuData (integer2DarrayBoard, arrayLength);
		
		printResults (validBoard);
	}

	private static void printResults(boolean validBoard) {
		// 10. Print out “SODUKU!!” if there was not duplicates, else print out “NO SODUKU”
		if (validBoard) {
			System.out.println ("SUDOKU!!");
		} else {
			System.out.println ("NO SUDOKU");
		}	
	}

	private static boolean ValidateSudokuData(int [][] integer2DarrayBoard, Integer arrayLength) {
		
		// 9. After all data is entered, validate the board to ensure there is no duplicate in each of the rows and columns 

		// Validate rows
		Integer position;												// this value will be scrutinized
		boolean validBoard = true;										// assume the board is valid
		for (int o = 0; o < arrayLength; o++) {							// for each row
			for (int p = 0; p < arrayLength; p++) {							// for each column
				position = integer2DarrayBoard[o][p];							// pick a value from row to scrutinize against other values in row
				for (int q = 0; q < arrayLength; q++) {							// for each value in the row
					if (p != q) {													// if it's not itself
						if (position == integer2DarrayBoard[o][q]) {					// if it's the same as n
							validBoard = false;												// fail
						}
					}
				}
			}
		}		
		
		// Validate columnns
		for (int o = 0; o < arrayLength; o++) {							// for each row
			for (int p = 0; p < arrayLength; p++) {							// for each column
				position = integer2DarrayBoard[p][o];							// pick a value from row to scrutinize against other values in row
				for (int q = 0; q < arrayLength; q++) {							// for each value in the row
					if (o != q) {													// if it's not itself
						if (position == integer2DarrayBoard[q][p]) {					// if it's the same as n
							validBoard = false;												// fail
						}
					}
				}
			}
		}		

		return validBoard;
	}

	private static Integer DisplayBoard(int [][] integer2DarrayBoard, Character columnOrRowChar, Integer integerBoardSize) {
		
		// 8. Add data to board and display board 
		
		Integer arrayLength = integer2DarrayBoard.length;
		Integer integerBoardDisplayWidth;
		System.out.println();
		integerBoardDisplayWidth = integerBoardSize * 4 + 1;				

		switch (columnOrRowChar) {
		case 'r':
			for (int l = 0; l < arrayLength; l++) {							// print the top of square
				for (int r = 0; r < integerBoardDisplayWidth; r++) {
					System.out.print ("-");									
				}
				System.out.println();										

				System.out.print("| ");										// print left of square
				for (int m = 0; m < arrayLength; m++) {
					System.out.print(integer2DarrayBoard[l][m] + " | ");	// print value and right border of square
				}
				System.out.println();	
			}
			for (int r = 0; r < integerBoardDisplayWidth; r++) {			// print the bottom of square
				System.out.print ("-");
			}
			System.out.println();
			System.out.println();
			break;
		case 'c':
			for (int l = 0; l < arrayLength; l++) {							// print the top of square
				for (int r = 0; r < integerBoardDisplayWidth; r++) {
					System.out.print ("-");									
				}
				System.out.println();										

				System.out.print("| ");										// print left of square
				for (int m = 0; m < arrayLength; m++) {
					System.out.print(integer2DarrayBoard[m][l] + " | ");	// print value and right border of square
				}
				System.out.println();	
			}
			for (int r = 0; r < integerBoardDisplayWidth; r++) {			// print the bottom of square
				System.out.print ("-");
			}
			System.out.println();
			System.out.println();
			break;
		default:
			System.out.println ("Invalid value for columnOrRowChar:  " + columnOrRowChar);
		}		
		
		return arrayLength;
	}

	private static int[][] getData(Integer integerBoardSize) {

		// 4. Prompt for data
		
		Scanner scannerUserInput = new Scanner (System.in);
		
		// 2. Create two dimensional array based on selected option
		int integer2DarrayBoard[][] = new int[integerBoardSize][integerBoardSize];
		System.out.println();
		int j;
		for (int i = 0; i < integerBoardSize; i++) {								// for each set of numbers
			j = 0;
			System.out.println( "Enter " + integerBoardSize + " numbers, from 1 to " + integerBoardSize +  " separated by commas:" );	
			// 5. enforce entry of numbers separated by commas
			boolean booleanValidDataEntry = false;										// set trigger to invalid in order to enter loop
			while (! booleanValidDataEntry) {												// while invalid
				booleanValidDataEntry = true;													// assume valid, until found otherwise
				String stringSudokuNumbers = scannerUserInput.next();							// get the numbers from user input string
					
				// 5. Data should be enter using comma separate i.e 1,2,3,4 
				stringSudokuNumbers.trim();														// remove white space
				String [] stringarrayParsedStrings = stringSudokuNumbers.split(",");			// parse the numbers into separate strings
		        if (stringarrayParsedStrings.length == integerBoardSize) {						// if there are the correct number of parsed items
		        	Integer integerDataEntryValue;
		        	// if each parsed value is a number
		        	for (String stringSubstring : stringarrayParsedStrings) {						// for each string in string array
		        		integerDataEntryValue = Integer.parseInt(stringSubstring);				// convert the string into an integer for array
		        		
		        		// 6. Parse and validate to make sure values are valid based on board size i.e - 1 to 4 for board size of 4 
		        		if ( integerDataEntryValue > 0 && integerDataEntryValue <= integerBoardSize ) {		// if numbers are in the expected range
			        		integer2DarrayBoard[i][j] = integerDataEntryValue;									// put the number into array
			        		j++;																				// increment second array counter
			        		char [] characterarrayDataEntryValue = stringSubstring.toCharArray(); 				// convert to char array for evaluation by isDigit
			        		for (char charValue : characterarrayDataEntryValue) {								// for each character in string
			        			if (! Character.isDigit (charValue)) {												// if it's not a digit
			        				booleanValidDataEntry = false;														// flag error
			        				
			        				// 7. If invalid data is provided, prompt user to re-enter the data for that row or column
			        				System.out.println ("Non-digit characters detected.  Please try again...");			// notify user
			        			}
			        		}
		        		}
		        		else {																				// else
		        			booleanValidDataEntry = false;														// flag error
		        			
		        			// 7. If invalid data is provided, prompt user to re-enter the data for that row or column  
		        			System.out.println ("Valid numbers are from 1 to " + integerBoardSize);				// notify user
		        			break;
		        		}
		        	}
		        } 
		        else {																			// else flag error and notify user
					booleanValidDataEntry = false;
					
					// 7. If invalid data is provided, prompt user to re-enter the data for that row or column
					System.out.println("The expected format is 4 numbers, separated by commas.  Please try again...");
		        }
			}
		}

		return integer2DarrayBoard;
	}

	private static Character getOrientation() {
		
		// 3. Ask user for which way to enter data – row or column
		
		Scanner scannerUserInput = new Scanner (System.in);
		String columnOrRowString = "z";
		Character columnOrRowChar = 'x';
		
		System.out.println();
		System.out.println("Would you like to enter data across rows or down columns?  Enter 'r' for rows, 'c' for columns:");
		
		while (! ((columnOrRowString.equals("r")) || (columnOrRowString.equals("c")))) {
			columnOrRowString = scannerUserInput.next();
			if (columnOrRowString.equals("r")) {
				columnOrRowChar = 'r';
				break;
			}
			else if (columnOrRowString.equals("c")) {
					columnOrRowChar = 'c';
					break;
				}
			else {
				System.out.println("Invalid input.  Please try again...");
			}
		}

		return columnOrRowChar;
	}

	private static Integer getBoardSize() {

		// 1. Prompt user for board size option: 4 or 9
		
		Scanner scannerInput = new Scanner (System.in);
		Integer integerGameBoardSize = 0;
		
		System.out.println("Enter the board size of your Sudoku game:  a board with 4 squares across and 4 squares down [4] or 9 by 9 [9]:  ");
		
		while (! ((integerGameBoardSize.equals(9)) || (integerGameBoardSize.equals(4)))) {
			integerGameBoardSize = scannerInput.nextInt();
			switch (integerGameBoardSize){
			case 4:
				integerGameBoardSize = 4;
				break;
			case 9:
				integerGameBoardSize = 9;
				break;
			default:
				System.out.println("Invalid input.  Please try again...");
			}
		}

		return integerGameBoardSize;
	}

}
