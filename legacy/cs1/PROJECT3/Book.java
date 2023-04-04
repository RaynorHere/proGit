import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * This program provides/handles the very basic functions of the Book object, specifically the 
 * ability to get/set all four attributes (title, author, genre, filename), as well as to
 * validate the object, print its attributes to a string, and read the associated text file
 * 
 * @author: Jim "JCIII" Crowell
 * 			debug assist Dr. Amit "Prof" Jain
 * 
 * @version: 1.0
 * 
 * @established: 11/04/2020
 */

public class Book implements BookInterface {

	private String title;
	private String author;
	private String genre;
	private String filename;

	
	//Constructor
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.genre = null;
		this.filename = null;
	}


	//Ensure none of the values of the Book are null, then ensure the provided filename is correct
	public boolean isValid() {
		if (this.title != null && this.author != null && this.genre != null && this.filename != null) {
			File pathCheck = new File(this.filename);
			if (pathCheck.exists()) {
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}

	//Pull the associated text file from a Book object, parse it to a string, return that string
	public String getText() {
		String textOut = "";
		if (this.isValid()) {
			File bookText = new File(this.filename);
			try {
				Scanner bookReader = new Scanner(bookText);
				while (bookReader.hasNextLine()) {
					textOut += bookReader.nextLine() + "\n";
				}
				bookReader.close();
			} catch (FileNotFoundException e) {
				System.out.println(
						"Error: File not found; please ensure the file exists and the entered path name is correct.");
				return null;
			}
			
			return textOut;

		}

		else {
			System.out.println("That book is currently unavailable.");
			return null;
		}

	}
	
	
	//String out the object, with every attribute included and headed
	public String toString() {
		String textOut = "";
		textOut += "Book Title: " + this.title + "; Author: " + this.author + "; Genre: " + this.genre + "; File Path: " + this.filename;
		return textOut;
	}
	
	
	
	//Accessors and Mutators (4)
	
	//Title
	public String getTitle() {
		return this.title;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}

	//Author
	public String getAuthor() {
		return this.author;
	}

	
	public void setAuthor(String author) {
		this.author = author;

	}

	//Genre
	public String getGenre() {
		// TODO Auto-generated method stub
		return this.genre;
	}

	
	public void setGenre(String genre) {
		this.genre = genre;

	}

	//Filename
	public String getFilename() {
		return this.filename;
	}

	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}



