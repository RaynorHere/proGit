import java.awt.Dimension;

import javax.swing.JFrame;
/*
 * This code is for our very top-level executor of the program. Just making a frame and adding the 
 * amalgamated panel to it.
 * 
 * @author: Jim "JCIII" Crowell
 * 			debug assist Dr. Amit "Prof" Jain
 * 
 * @version: 1.0
 * 
 * @established: 11/23/2020
 */

public class ReaderOfBooks {

	public static void main(String[] args) {

		JFrame mainFrame = new JFrame("Reader of Books");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setMinimumSize(new Dimension(650, 700));

		ReaderOfBooksPanel theSetAndStage = new ReaderOfBooksPanel();

		mainFrame.getContentPane().add(theSetAndStage);
		mainFrame.pack();
		mainFrame.setVisible(true);

	}

}
