import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/* This program's intent is to receive a .txt input file, read the dimensions and values 
 * provided, arranging them into two-dimensional arrays. From there, it offers functions to:
 * 1) derive an array of the sums of the cells surrounding each cell
 * 2) create an array made up of the average values of those base sums
 * 3) create an array showing the therapeutic index for each cell, based on its neighboring 
 * 		values
 * 4) utilize those indices to determine whether or not the cells in the provided file are
 * 		at dangerous load-levels
 * 
 * @author: Jim "JCIII" Crowell
 * 
 * @version: 1.0
 * 
 * @established: 1/19/21 
 */

public class GridMonitor implements GridMonitorInterface {

	private int arrayX = 0;
	private int arrayY = 0;
	private double[][] baseArray;
	private double[][] surroundArray;
	private double[][] avgArray;
	private double[][] deltaArray;
	private boolean[][] dangerArray;

	public static void main(String[] args) throws FileNotFoundException {

//		GridMonitor testFunction = new GridMonitor("negatives.txt");
//		
//		System.out.println(testFunction.toString());

	}

	public GridMonitor(String fileName) throws FileNotFoundException {

		// Make file from input directory. Make scanner to pull entire lines from the
		// file.
		File fileIn = new File(fileName);
		Scanner linePuller = new Scanner(fileIn);

		// First scanner pulls first line, the dimensions line.
		String rawLine = linePuller.nextLine();

		// New scanner with a specific delimiter: whitespace. We'll use something
		// similar in the
		// loop to make the array, here in a sec.
		final String SPAZER = " ";
		Scanner dimChop = new Scanner(rawLine).useDelimiter(SPAZER);

		// First two numbers are dimensions of arrays, so grab and store those.
		arrayX = Integer.parseInt(dimChop.next());
		arrayY = Integer.parseInt(dimChop.next());
		dimChop.close();

		// Initialize all arrays, now that we have the dims.
		baseArray = new double[arrayX][arrayY];
		surroundArray = new double[arrayX][arrayY];
		avgArray = new double[arrayX][arrayY];
		deltaArray = new double[arrayX][arrayY];
		dangerArray = new boolean[arrayX][arrayY];

		// Population loop. Pull a fresh line, representing each row, then chop its
		// individual
		// elements into the corresponding [j] coordinates to form columns.
		for (int i = 0; i < arrayX; i++) {
			rawLine = linePuller.nextLine();
			Scanner popChop = new Scanner(rawLine).useDelimiter(SPAZER);
			for (int j = 0; j < arrayY; j++) {
				baseArray[i][j] = Double.parseDouble(popChop.next());
			}
			popChop.close();
		}
		linePuller.close();

	}

	@Override
	public double[][] getBaseGrid() {
		double[][] freeBaseArray = new double[arrayX][arrayY];

		for (int i = 0; i < arrayX; i++) {
			for (int j = 0; j < arrayY; j++) {
				freeBaseArray[i][j] = baseArray[i][j];
			}
		}

		return freeBaseArray;
	}

	@Override
	public double[][] getSurroundingSumGrid() {

		// Nested For's to manipulate baseArray to create surroundArray
		for (int i = 0; i < arrayX; i++) {
			for (int j = 0; j < arrayY; j++) {

				double increment = 0.0;

				// Cell above
				if (i == 0)
					increment += baseArray[i][j];
				else
					increment += baseArray[i - 1][j];

				// Cell to right
				if (j == arrayY - 1)
					increment += baseArray[i][j];
				else
					increment += baseArray[i][j + 1];

				// Cell below
				if (i == arrayX - 1)
					increment += baseArray[i][j];
				else
					increment += baseArray[i + 1][j];

				// Cell to left
				if (j == 0)
					increment += baseArray[i][j];
				else
					increment += baseArray[i][j - 1];

				surroundArray[i][j] = increment;

			}
		}
		return surroundArray;
	}

	@Override
	public double[][] getSurroundingAvgGrid() {

		getSurroundingSumGrid();

		// Iterate through surroundArray, divide each cell by 4.
		for (int i = 0; i < arrayX; i++) {
			for (int j = 0; j < arrayY; j++) {
				avgArray[i][j] = (surroundArray[i][j] / 4);
			}
		}
		return avgArray;
	}

	@Override
	public double[][] getDeltaGrid() {

		getSurroundingAvgGrid();

		// Same as above, but with a different array, and by 2 instead of 4
		for (int i = 0; i < arrayX; i++) {
			for (int j = 0; j < arrayY; j++) {
				deltaArray[i][j] = Math.abs(avgArray[i][j] / 2.0);
			}
		}
		return deltaArray;
	}

	@Override
	public boolean[][] getDangerGrid() {

		getDeltaGrid();

		for (int i = 0; i < arrayX; i++) {
			for (int j = 0; j < arrayY; j++) {
				if (baseArray[i][j] < (avgArray[i][j] + deltaArray[i][j])
						&& baseArray[i][j] > (avgArray[i][j] - deltaArray[i][j]))
					dangerArray[i][j] = false;
				else
					dangerArray[i][j] = true;
			}
		}

		return dangerArray;
	}

	/*
	 * toString method prints out an AESTHETICALLY-ENHANCED version of the
	 * dangerGrid, showing cells that are over- or under-loaded.
	 */
	public String toString() {
		getDangerGrid();

		StringBuffer output = new StringBuffer();

		String border = "---------------------------------- \n";
		String heading = "----------CELL STATUSES---------- \n";

		output.append(border);
		output.append(heading);
		output.append(border);

		for (int i = 0; i < arrayX; i++) {
			if (i > 0)
				output.append("\n");
			for (int j = 0; j < arrayY; j++) {
				if (dangerArray[i][j] == true)
					output.append(" | " + "DANGER" + " | ");
				else
					output.append(" | " + "SAFE" + " | ");
			}
		}
		output.append("\n");
		output.append(border);
		return output.toString();

	}

}
