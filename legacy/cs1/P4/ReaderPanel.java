import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/*
 * This code creates the main output pieces of the program; a small panel with information on the 
 * currently-displayed book, and a text area for displaying that book's text. To facilitate, it 
 * includes a function to be "given" a book by the Library Panel.
 * 
 * @author: Jim "JCIII" Crowell
 * 			debug assist Dr. Amit "Prof" Jain
 * 
 * @version: 1.0
 * 
 * @established: 11/23/2020
 */

@SuppressWarnings("serial")
public class ReaderPanel extends JPanel {

	// Book text area
	private JTextArea bookTextDisplay = new JTextArea();

	// Title label
	private JLabel bookNameLabel = new JLabel("Title: ");

	// Author label
	private JLabel writerNameLabel = new JLabel("By: ");

	public ReaderPanel() {
		this.setBorder(BorderFactory.createTitledBorder("Reader"));
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(450, 600));

		// Info Panel
		JPanel infoPanel = new JPanel();
		TitledBorder infoHeader = BorderFactory.createTitledBorder("Information");
		infoPanel.setBorder(infoHeader);
		infoPanel.setLayout(new BorderLayout());
		infoPanel.add(bookNameLabel, BorderLayout.WEST);
		infoPanel.add(writerNameLabel, BorderLayout.EAST);

		this.add(infoPanel, BorderLayout.NORTH);

		// Content Panel
		JScrollPane bookScroll = new JScrollPane(bookTextDisplay);
		bookScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		bookTextDisplay.setEditable(false);

		this.add(bookScroll, BorderLayout.CENTER);

	}

	// Function to update labels on infopanel when a book is chosen to be loaded, as
	// well as text display
	public void receiveBook(Book chosenBook) {
		bookNameLabel.setText("Title: " + chosenBook.getTitle());
		writerNameLabel.setText("By: " + chosenBook.getAuthor());
		bookTextDisplay.setText(chosenBook.getText());
	}

}
