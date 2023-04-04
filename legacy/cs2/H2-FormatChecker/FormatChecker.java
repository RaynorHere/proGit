import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/* The FormatChecker class' purpose is to receive a .dat file from the user, iterate through that .dat file, and ensure it follows
 * all expected rules of formatting. That is, the file is a two-dimensional array whose first row is the row and colum dimensions
 * of said array, the all following elements are Double (or at least "Double-friendly"; i.e., Integers that can be entered and 
 * processed as Doubles) values. Additionally, the number of rows and columns must match the provided values at the top of the file.
 * Finally, FormatChecker serves to handle several of the most common exceptions a program will encounter, as well as a catch-all
 * at the end to prevent program crashes at unforeseen issues.
 * 
 * @author: James "JCIII" Crowell
 * 
 * @version: 1.0
 * 
 * @est: 02/09/21
 * 
 */
public class FormatChecker {

	// There's not going to be much to the Main(), for the purposes of catching and
	// processing exceptions, we'll have to write another function.
	public static void main(String[] args) {
		
		// Right off, we'll check to make sure arguments were passed.
		if (args.length == 0) {
			System.out.println("Error: This program must be run with arguments in the form of data files to be processed.");
			System.out.println("Please run with one or more files in the form of: \"$ java FormatChecker [file1] [file2] [file(n)].\"");
			return;
		}

		for (String thisArg : args) {
			CheckFormat(thisArg);
		}

	}
	/* The CheckFormat function is going to be doing the heavy lifting. This is the part of the program that accepts the file from the
	 * Main method, which will be iterating through the args String array; CheckFormat accepts those Strings as arguments, turns them 
	 * into file names, and creates Scanners to read and process them.  
	 */
	private static void CheckFormat(String fileName) {
		// Must first declare file variable to be used, such that it will be accessible
		// by both the TRY and the CATCH(es).
		File inFile = new File(fileName);

		// Entire program must be encased in a "try" block, in order to be able to
		// "catch" exceptions of varying stripe.
		try {

			// First scanner pulls the .dat apart into lines.
			Scanner linePull = new Scanner(inFile);
			String fullLine = linePull.nextLine();

			// Second scanner pulls the line apart into tokens.
			Scanner dimChop = new Scanner(fullLine);

			// We know the top row is the dims of the .dat array, and MUST be integers, so
			// we'll call nextInt to set up an early catch-able exception.
			int row = dimChop.nextInt();

			// We'll also put in some early checks to ensure declared row/col dims are 1 or
			// more.
			if (row < 1) {
				System.out.println(inFile + "\n INVALID: File declares number of rows less than 1. \n");
				linePull.close();
				dimChop.close();
				return;
			}
			int col = dimChop.nextInt();
			if (col < 1) {
				System.out.println(inFile + "\n INVALID: File declares number of columsn less than 1. \n");
				linePull.close();
				dimChop.close();
				return;
			}

			// The dim row should only be two elements, so if there's more:
			if (dimChop.hasNext()) {
				System.out.println(
						inFile + "\n INVALID: File's initial row contains more than elements declaring dimensions. \n");
				linePull.close();
				dimChop.close();
				return;
			}

			// For further iteration, we'll need a scanner whose contents changes with every
			// line, so lineChop's job is done.
			dimChop.close();

			// Nested FOR loops will iterate through the .dat, counting rows and columns in
			// each row.
			for (int i = 0; i < row; i++) {
				fullLine = linePull.nextLine();
				Scanner lineChopper = new Scanner(fullLine);
				for (int j = 0; j < col; j++) {
					double dummyDouble = lineChopper.nextDouble();
				}

				// This line runs once the "columns" loop has finished. If loopChopper has
				// another value after the j counter reaches the value of col, a row has too many elements, and the format is 
				// invalid.
				if (lineChopper.hasNext()) {
					System.out.println(inFile + "\n INVALID: File declares " + col
							+ " columns, but at least one row has too many. \n");
					lineChopper.close();
					return;
				}
				lineChopper.close();
			}

			// Similar to line 83, this block will check to see if there are rows after
			// we've reached what the dims told us is supposed to be the final row. We'll set it up to disregard blank lines.
			if (linePull.hasNextLine()) {
				fullLine = linePull.nextLine().trim();

				// If this scanner finds anything in the line after it's been trimmed, it's a
				// non-empty line that exists past the limits of the initial dims.
				Scanner blankCheck = new Scanner(fullLine);
				if (blankCheck.hasNext()) {
					System.out.println(inFile + "\n INVALID: File declares " + row + " rows, but contains more. \n");
					blankCheck.close();
					return;
				}
				blankCheck.close();
			}

			linePull.close();

			// Assuming both that the program has gotten this far and that we've accounted
			// for every non-exception-derived error we'll find in these .dats, the reward is a final output:
			System.out.println(inFile + "\n VALID. \n");

		}

		// Onward came the exception handles!

		// Input Mismatch catch, for if dims are given as non-integers, or there are non-double-able values in the array, for 
		// instance.
		catch (InputMismatchException e) {
			System.out
					.println(inFile + "\n INVALID: " + e.toString() + " (File provides non-number element(s) in number"
							+ " array, or provides non-integer values when declaring its dimensions.) \n");
		}

		// File Not Found catch, purpose is right in the name.
		catch (FileNotFoundException e) {
			System.out.println(inFile + "\n INVALID: " + e.toString()
					+ " (Ensure filename is correct and file is extant" + " in directory). \n");
		}

		// No Such Element catch, for when there are fewer rows or columns than
		// expected.
		catch (NoSuchElementException e) {
			System.out.println(inFile + "\n INVALID: " + e.toString()
					+ " (Provided file contains fewer rows or columns than it declares.) \n");
		}
		// For any remaining types of exceptions not yet handled, we'll insert this
		// all-purpose, to prevent program crashes.
		catch (Exception e) {
			System.out.println(inFile + "\n INVALID: This .dat file provided caused an unforeseen error. \n");

		} 
		finally {
		}

	}

}
