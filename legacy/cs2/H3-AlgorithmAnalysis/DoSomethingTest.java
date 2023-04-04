import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Test DoSomething - count number of statements to doSomething() to various arrays.
 * @author mvail
 */
public class DoSomethingTest {
	/**
	 * Get an array of specified size and pass it to doSomething.doSomething().
	 * Report the results.
	 * @param args either size of a new array, or a file containing ints
	 */
	public static void main(String[] args) throws FileNotFoundException {
		//call modified doSomething() with different array sizes and write results to
		// "doSomething.csv" with a PrintStream
		PrintStream outfile = new PrintStream("doSomething.csv");
		outfile.println("size, statements");
		for (int size = 1; size < 101; size += 1) {
			int[] array = ArrayOfInts.randomizedArray(size);
			DoSomething.doSomething(array); //this is the call we want to measure
			long statements = DoSomething.getStatements();
			outfile.println(size + ", " + statements);

			//summary
			System.out.printf("\nfor n = %d, took %d statements\n",
					size, statements);

			//show "sorted" array
//			for (int i = 0; i < array.length; i++) {
//				System.out.print(array[i] + " ");
//			}
//			System.out.println();
		}
		outfile.close();
	}
}
