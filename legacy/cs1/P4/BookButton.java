import javax.swing.JButton;

/*
 * This sets up our most basic component, the Book Button, which is the key that lets us manipulate 
 * our book objects in the GUI to begin with.
 * 
 * @author: Jim "JCIII" Crowell
 * 			debug assist Dr. Amit "Prof" Jain
 * 
 * @version: 1.0
 * 
 * @established: 11/23/2020
 */

@SuppressWarnings("serial")
public class BookButton extends JButton {

	// The "State" of the button
	private Book sBook;

	// Constructor
	public BookButton(Book bookIn) {
		this.sBook = bookIn;

		// Small process to trim button labels to 20 characters
		String buttonText = sBook.getTitle();
		if (sBook.getTitle().length() > 20)
			buttonText = buttonText.substring(0, 16) + "...";
		this.setText(buttonText);

		// Mouse hover popup
		this.setToolTipText(sBook.toString());

	}

	// Book accessor
	public Book getBook() {
		return sBook;
	}

}
