
public class CountYay {

	public static void main(String[] args) {
		for (int i = 100; i > 1; i--) {
			if (!(i % 2 == 0) && (i % 3 == 0) && (i % 11 == 0))
				System.out.print("Yay! ");
			else if (!(i % 2 == 0) && (i % 3 == 0))
				System.out.print(i + " ");
		}

	}

}
