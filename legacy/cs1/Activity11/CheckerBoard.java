import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Lesson 11: Activity - for Loops
 * 
 * @author CS121 Instructors
 * @version [semester] 
 * @author [your name]  
 */
@SuppressWarnings("serial")
public class CheckerBoard extends JPanel
{
	private static final int NUM_ROWS = 8;
	private static final int NUM_COLS = 8;

	/**
	 * Sets the initial dimensions of the panel. 
	 */
	public CheckerBoard()
	{
		setPreferredSize(new Dimension(500, 500));
	}

	/**
	 *  Draws the checker board.
	 *  @param page Graphics context
	 */
	public void paintComponent(Graphics page)
	{
		int width = getWidth();
		int height = getHeight();

		int boxWidth = (int) Math.ceil((double) width/NUM_COLS);
		int boxHeight = (int) Math.ceil((double) height/NUM_ROWS);
		page.setColor(Color.RED);
		int boxXStart = 0;
		int boxYStart = 0;
		
		//This loop paints a box, top to bottom, alternating color
		for (int i = 0; i <= NUM_COLS; i++)
		{
			page.fillRect(boxXStart, boxYStart, boxWidth, boxHeight);
		
			//This counter starts at 1, because the initial loop paints the first column, and starting
			//j at 0 (or any even number) would cause this function to sync with the other loop, 
			//which is not only redundant, it causes you to have bars instead of boxes (i.e., each 
			//column is a single color). 
			for (int j = 1; j <= NUM_ROWS; j++)
			{
				
				page.fillRect(boxXStart, boxYStart, boxWidth, boxHeight);
				boxXStart += Math.ceil((double) width/NUM_COLS);
				if (page.getColor() == Color.red)
					page.setColor(Color.BLACK);
				else
					page.setColor(Color.red);
			}
			if (page.getColor() == Color.red)
				page.setColor(Color.black);
			else
				page.setColor(Color.red);
			boxXStart = 0;
			boxYStart += Math.ceil((double) height/NUM_ROWS); 
		}
		
		
	}

	/**
	 * Creates the main frame of the program.
	 * @param args
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Checker Board");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CheckerBoard panel = new CheckerBoard();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
