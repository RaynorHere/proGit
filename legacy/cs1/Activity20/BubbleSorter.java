import java.util.Random;

public class BubbleSorter {

	private int[] compArray;

	public BubbleSorter(int size) {

		// Spawn array
		compArray = new int[size];

		Random valueSpitter = new Random();

		int counter = 0;
		for (int point = 0; point < size; point++) {
			compArray[point] += valueSpitter.nextInt();
		}

	}

	// toString
	public String toString() {
		String outVal = "";
		for (int member : compArray) {
			outVal += member + " ";
		}
		return outVal;
	}

	private void swap(int val1, int val2) {
		int placeHolder = compArray[val2];
		compArray[val2] = compArray[val1];
		compArray[val1] = placeHolder;
	}

	public void sort() {
		boolean done = false;
		while (!done) {
			done = true;
			for (int i = 1; i < compArray.length; i++) {
				if (compArray[i - 1] > compArray[i]) {
					swap(i - 1, i);
					done = false;
				}
			}
		}

	}

}
