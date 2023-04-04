
public class CardDealer {

	public static void main(String[] args) {
		
		//Constructor test (pass)
		DeckOfCards testrun = new DeckOfCards();
		System.out.println(testrun.toString());
		
		
		//Shuffle test (pass)
		testrun.shuffle();
		System.out.println(testrun.toString());
		
		
		
		//Draw function test (seems to pass)
		System.out.println(testrun.draw());
		System.out.println(testrun.draw());
		System.out.println(testrun.draw());
		
		
		//Just for fun, let's call the draw function another 50 times (there should be 48 left in the
		//deck, and the fiftieth should give us a null (also pass)
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());
//		System.out.println(testrun.draw());

	}

}
