import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/*
 * This code serves to make a top-level panel, joining the Library and Reader panels, allowing
 * them to talk to one another during program execution.
 * 
 * @author: Jim "JCIII" Crowell
 * 			debug assist Dr. Amit "Prof" Jain
 * 
 * @version: 1.0
 * 
 * @established: 11/23/2020
 */

@SuppressWarnings("serial")
public class ReaderOfBooksPanel extends JPanel {

	private LibraryPanel leftPanel;
	private ReaderPanel rightPanel = new ReaderPanel();

	public ReaderOfBooksPanel() {

		leftPanel = new LibraryPanel(new LoadButtonFunction(), new BookButtonLMSService());

		this.setLayout(new BorderLayout());
		this.add(leftPanel, BorderLayout.WEST);
		this.add(rightPanel, BorderLayout.CENTER);

	}

	// Here's our listener for the button that starts the process of loading the CSV
	private class LoadButtonFunction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			leftPanel.LoadFunction();
		}
	}

	// Here, we'll make our listener for the buttons themselves, which will pass the
	// relevant text to the reader area.
	private class BookButtonLMSService implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			BookButton flashPoint = (BookButton) e.getSource();
			Book chosenOne = flashPoint.getBook();
			rightPanel.receiveBook(chosenOne);
		}

	}

}
