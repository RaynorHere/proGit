import java.util.Scanner;

/*
 * This program presents a Library object, which will act as a collection for Book objects. It will
 * present a menu to the user, allowing them to add or remove books, print lists of them currently 
 * loaded, and read books by printing out their .txt files to the console.
 * 
 * @author: Jim "JCIII" Crowell
 * 			Debug assist Dr. Amit "Prof" Jain
 * 
 * @version: 1.0
 * 
 * @established: 11/04/2020
 */
public class LibraryOfBooks {

	public static void main(String[] args) {

		Library libraryBooks = new Library();

		// Infinite Loop Lynchpin
		boolean itsAlive = true;

		// Formatting pieces
		String border = "=========================================================";
		String title = "==Welcome to the Libropticon! Please select an option: ==";

		// Main menu
		String menu = "(P)rint Library \n" + "(A)dd to Library \n" + "(D)elete From Library \n"
				+ "(R)ead Book in Library \n" + "(M)enu Redisplay \n" + "(E)xit \n";

		// User input
		Scanner unScanny = new Scanner(System.in);

		// Error messages we'll be needing a fair bit
		String noDice = "There aren't any books loaded, yet! You'll have to load some, first.";
		String outtaBounds = "That number is outside the library's current scope!";

		// Display menu
		System.out.println(border);
		System.out.println(title);
		System.out.println(border);
		System.out.println(menu);

		// Initialize
		while (itsAlive) {
			String userChooser = unScanny.nextLine().toLowerCase();

			switch (userChooser) {

			case "p":
				if (libraryBooks.isEmpty()) {
					System.out.println(noDice);
				} else {
					System.out.println(libraryBooks.toString());
				}
				break;

			case "a":

				String inTitle;
				String inAuthor;
				String inGenre;
				String inFilename;

				System.out.println("Please enter the title of the book: ");
				inTitle = unScanny.nextLine();

				System.out.println("\nPlease enter the author of that book: ");
				inAuthor = unScanny.nextLine();

				System.out.println("\nPlease enter the genre of that book: ");
				inGenre = unScanny.nextLine();

				System.out.println("\nFinally, what is the directory and filename of that book?");
				inFilename = unScanny.nextLine();

				System.out.println("\nAdding book to the shelves!" + "\n");

				Book newAdd = new Book(inTitle, inAuthor);
				newAdd.setGenre(inGenre);
				newAdd.setFilename(inFilename);

				libraryBooks.addBook(newAdd);

				break;

			case "d":
				if (libraryBooks.isEmpty())
					System.out.println(noDice);
				else {
					int killDex;
					System.out.println(
							"Enter the index number, first being \"0\", of the book you " + "would like to delete.");
					killDex = unScanny.nextInt();
					libraryBooks.removeBook(killDex);
				}
				break;

			case "r":
				if (libraryBooks.isEmpty())
					System.out.println(noDice);
				else {

					int readDex;
					Book readBook = new Book("", "");
					System.out.println(
							"Enter the index number, first being \"0\", of the book you" + " would like to read.");
					readDex = unScanny.nextInt();
					readBook = libraryBooks.getBook(readDex);
					try {
						if (readBook.isValid())
							System.out.println(readBook.getText());
						else
							System.out.println("That book is currently unavailable. Please choose another.");
					} catch (Exception e) {
						//This catch functions just to make sure the program doesn't crash; it was crashing when 
						//an index number surpassing the library's index was passed, which Library.java handles,
						//But couldn't prevent crashing, and this is my fix.
					}
				}
				break;

			case "m":
				System.out.println(menu);
				break;

			case "e":
				itsAlive = false;
				break;

			default:
				System.out.println("Invalid selection! Press \"M\" to redisplay valid choices.\n");

			}

		}

	}

}
