import java.util.ArrayList;


/*
 * This program provides/handles the functions that allow LibraryOfBooks to add, remove, and reference
 * books contained therein.
 * 
 * @author: Jim "JCIII" Crowell
 * 			Debug assist Dr. Amit "Prof" Jain
 * 
 * @version: 1.0
 * 
 * @established: 11/04/2020
 */
public class Library implements LibraryInterface {

	private ArrayList<Book> libraryShelf = new ArrayList<Book>();
	
	
	public Library() {
	
	}
		
	
	//Generate a copy of the list of books in the library
	public ArrayList<Book> getBooks() {
		ArrayList<Book> copyOfLibrary = new ArrayList<Book>();
		for (Book inShelf : libraryShelf) {
			copyOfLibrary.add(inShelf);
		}
		return copyOfLibrary;
	}

	
	//Add a book to the library
	public void addBook(Book newBook) {
		libraryShelf.add(newBook);		
	}

	
	//Delete a book from the library's list based on an index number provided
	public void removeBook(int index) {
		try {
		libraryShelf.remove(index);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("The referenced number is outside the bounds of the library's list!");
		}		
	}


	//Pull a book from the library's list based on an index number provided
	public Book getBook(int index) {
		try {
		Book outBook = new Book("", "");
		outBook = libraryShelf.get(index);
		return outBook;
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Error: The referenced number is outside the bounds of the library's list!");
			return null;
		}
	}

	
	//Print a list of all books currently contained in the library; indices and book attributes included
	public String toString() {
		String libOut = "";
		int dexCount = 0;
		for (Book inShelf : libraryShelf) {
			libOut += "Index Number: " + dexCount + " - " + inShelf.toString() + "\n";
			dexCount++;
		}
		return libOut;
	}
	
	
	//I added an isEmpty() function to my Library because I like being able to head off potential
	//unpleasantness with performing operations on nonexistent items
	public boolean isEmpty() {
		if (libraryShelf.isEmpty()) 
			return true;
		else
			return false;
		}
	
	
}
