import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * This code sets up a library object to track books loaded into it, as well as a set of panels for
 * loading and manipulating those books.
 * 
 * @author: Jim "JCIII" Crowell
 * 			debug assist Dr. Amit "Prof" Jain
 * 
 * @version: 1.0
 * 
 * @established: 11/23/2020
 */

@SuppressWarnings("serial")
public class LibraryPanel extends JPanel {

	// Library resource
	private Library panelLibrary = new Library();

	// Book list panel
	private JPanel bookListPanel = new JPanel();

	// Book list scrollpane
	private JScrollPane bookListScroller = new JScrollPane(bookListPanel);

	BoxLayout layout = new BoxLayout(bookListPanel, BoxLayout.Y_AXIS);

	// Library loading panel
	private JPanel bookLoaderPanel = new JPanel();
	private JTextField filenameField = new JTextField(15);
	private JButton loadLauncher = new JButton("LOAD");
	private ActionListener bookListener;

	public LibraryPanel(ActionListener loadListener, ActionListener bookListener) {
		this.setBorder(BorderFactory.createTitledBorder("Library"));
		this.setLayout(new BorderLayout());
		this.bookListener = bookListener;

		// This Matryoshka-doll-of-panels is honestly a bit much
		this.add(bookListScroller, BorderLayout.CENTER);
		bookListPanel.setBorder(BorderFactory.createTitledBorder("Book List"));
		bookListPanel.setLayout(layout);
		bookListScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		bookListScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Panel which controls importing of a new library
		this.add(bookLoaderPanel, BorderLayout.SOUTH);
		bookLoaderPanel.setBorder(BorderFactory.createTitledBorder("Load New List"));
		bookLoaderPanel.add(filenameField, JTextArea.LEFT_ALIGNMENT);
		loadLauncher.addActionListener(loadListener);
		bookLoaderPanel.add(loadLauncher, JButton.RIGHT_ALIGNMENT);

	}

	// Functionality for the LOAD button, which takes in the user-entered .csv file
	public void LoadFunction() {
		// Thought removing all buttons before adding a new file would be easier than
		// writing a way to check if any buttons of a second (or third, or fourth)
		// loading are already there
		bookListPanel.removeAll();
		bookListPanel.repaint();

		// Makes a String variable, gives it the data inside the loading textfield, and
		// passes it off to Library.java
		String filename = "";
		filename = filenameField.getText();
		panelLibrary.loadLibraryFromCSV(filename);

		// And here populate the panel with buttons for every book that Library.java has
		// processed
		// This will also set the formatting; centering all buttons and spacing them out
		// a bit
		for (Book loadedBook : panelLibrary.getBooks()) {
			BookButton newGuy = new BookButton(loadedBook);
			bookListPanel.add(newGuy);
			newGuy.addActionListener(bookListener);
			newGuy.setAlignmentX(Component.CENTER_ALIGNMENT);
			bookListPanel.add(Box.createRigidArea(new Dimension(0, 25)));
			revalidate();
		}
	}

}
