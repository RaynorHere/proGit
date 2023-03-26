
/*
 * This program prints out a list of numbers, from 0 to 99, inclusive, with the following conditionals:
 * 1) Numbers divisible by three (3) are replaced with the word "Pop"
 * 2) Numbers divisible by eight (8) are replaced with the word "Sickle"
 * 3) Numbers divisible by both three (3) and eight (8) are replaced with the word "PopSickle"
 * 
 * @Author: James "JCIII" Crowell
 * 
 * @Version: 1.0
 * 
 * @Established: 12/15/2020
 */
public class PopSickle {

	public static void main(String[] args) {
	
		for (int i = 0; i < 100; i++) {
			if (i % 3 == 0 && i % 8 == 0)
				System.out.print("PopSickle ");
			else if (i % 3 != 0 && i % 8 == 0)
				System.out.print("Sickle ");
			else if (i % 3 == 0 && i % 8 != 0)
				System.out.print("Pop ");
			else
				System.out.print(i + " ");
		}
		

	}

}
