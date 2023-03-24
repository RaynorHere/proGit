
public class Driver {

	public static void main(String[] args) {
		
		BubbleSorter testDrive = new BubbleSorter(12);
		
		System.out.println(testDrive.toString() + "\n");
		
		testDrive.sort();
		
		System.out.println(testDrive.toString());

	}

}
