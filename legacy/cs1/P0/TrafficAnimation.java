import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * CS 121 Project 1: Traffic Animation
 *
 * Animates a car passing by a crosswalk, with an observer waiting to cross through that crosswalk.
 *
 * @author BSU CS 121 Instructors
 * @author James "JCIII" Crowell
 */
@SuppressWarnings("serial")
public class TrafficAnimation extends JPanel
{
	// This is where you declare constants and variables that need to keep their
	// values between calls	to paintComponent(). Any other variables should be
	// declared locally, in the method where they are used.

	/**
	 * A constant to regulate the frequency of Timer events.
	 * Note: 100ms is 10 frames per second - you should not need
	 * a faster refresh rate than this
	 */
	private final int DELAY = 100; //milliseconds

	/**
	 * The anchor coordinate for drawing / animating. All of your vehicle's
	 * coordinates should be relative to this offset value.
	 */
	private int xOffset = 0;

	/**
	 * The number of pixels added to xOffset each time paintComponent() is called.
	 */
	private int stepSize = 30;

	private final Color BACKGROUND_COLOR = Color.white;
	
	private final ImageIcon DOOMGUY = new ImageIcon("DOOMGUY.jpg");
	

	/* This method draws on the panel's Graphics context.
	 * This is where the majority of your work will be.
	 *
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{
		// Get the current width and height of the window.
		int width = getWidth(); // panel width
		int height = getHeight(); // panel height

		// Fill the graphics page with the background color.
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, width, height);
		
		// Draw in road for vehicle
		g.setColor(Color.gray);
		g.fillRect(0, height / 5, width, height / 3);
		
		// Ocean in distant background
		Color oceanBlue = new Color(50,7,219);
		g.setColor(oceanBlue);
		g.fillRect(0, 0, width, height / 7);
		
		// Sand border; road-ocean
		Color Sand = new Color(245,190,41);
		g.setColor(Sand);
		g.fillRect(0, height / 7, width, height / 6);
		
		// Grass to the bottom.  Except not that insane, neon grass that looks like it's radioactive
		Color nonRadGrass = new Color(18,162,8);
		g.setColor(nonRadGrass);
		g.fillRect(0, height / 13 * 7, width, height);
		
		// Draw in some traffic lane dashes
		Color roadYellow = new Color(242,242,4);
		g.setColor(roadYellow);
		int divWidth = width / 10;
		int divHeight = height / 45;
		int divLineUp = height / 3 + height / 15;
		g.fillRect(0 - divWidth / 2, divLineUp, divWidth, divHeight);
		g.fillRect(4 * divWidth / 3, divLineUp, divWidth, divHeight);
		g.fillRect(13 * divWidth / 4, divLineUp, divWidth, divHeight);
		g.fillRect(16 * divWidth / 3, divLineUp, divWidth, divHeight);
		g.fillRect(15 * divWidth / 2, divLineUp, divWidth, divHeight);
		g.fillRect(19 * divWidth / 2, divLineUp, divWidth, divHeight);
		
		// Put in a crosswalk for our observer to use
		// Frame. Excuse the numbers here; the easiest way for my brain to do this was to do fractions of 100
		g.setColor(Color.white);
		g.drawLine(43 * width / 100, 53 * height / 100, 43 * width / 100, 31 * height / 100);
		g.drawLine(53 * width / 100, 53 * height / 100, 53 * width / 100, 31 * height / 100);
		// Blocks
		int walkWidth = 2 * width / 25;
		int walkHeight = height / 40;
		int walkXLine = 44 * width /100;
		g.fillRect(walkXLine, 49 * height / 100, walkWidth, walkHeight);
		g.fillRect(walkXLine, 45 * height / 100, walkWidth, walkHeight);
		g.fillRect(walkXLine, 41 * height / 100, walkWidth, walkHeight);
		g.fillRect(walkXLine, 37 * height / 100, walkWidth, walkHeight);
		g.fillRect(walkXLine, 33 * height / 100, walkWidth, walkHeight);
		
		/*
		Okay. This is going to be a little weird. My original idea was to step the Y coordinates of the avatar's 
		body parts in tune with the way the vehicle's X coordinates are stepped with xOffset, starting once the 
		vehicle passes a certain point on the screen. I know we haven't done this yet, but I'm sure there's a way,
		even if I couldn't figure it out sufficiently. For and While loops didn't quite do it, and an If statement
		SORT OF does, but it doesn't step the Y coordinate more than once (and it returned bizarrely different
		values for the drawn arms vs the head icon). Because of that, I admitted half-defeat here and just set the 
		Y coordinates to one of two values. Since it didn't have any interstitial frames to begin with, the avatar
		was just teleporting, so that's what I settled for, and then simplified it. Doomguy still crosses the crosswalk 
		after the vehicle passes, and returns to his starting point when the animation loops, but it's a single 
		frame of movement, instead of gradual. Still, it's not nothing, so I left it in. I would like to refine 
		this until it's "true" animation, but I can't justify the time expense.
		All this to explain why there are If statements under every single Y coordinate of every drawn element of 
		avatar. I wanted to add something a little extra. I'll admit, it's slightly dependent on aspect ratio to
		work, though...
		*/
		
		int crossThresh = 95 * width / 100;
		//Head
		int headY1 = 60 * height / 100;
		if (xOffset >= crossThresh) 
		{
		headY1 = (headY1 - stepSize) % height / 6;
		}
		g.drawImage(DOOMGUY.getImage(), (44 * width) / 100, headY1, width / 13, height / 12, null);
		
		//Body
		Color Purple = new Color(142,25,201);
		g.setColor(Purple);
		int bodyY1 = 68 * height / 100;
		if (xOffset >= crossThresh)
		{
			bodyY1 = 17 * height /100;
		}
		int bodyY2 = 76 * height / 100;
		if (xOffset >= crossThresh)
		{
			bodyY2 = 25 * height / 100;
		}
		int bodyX = 48 * width / 100;
		g.drawLine(bodyX, bodyY1, bodyX, bodyY2);
		
		//Right Arm
		int rArmY = 71 * height / 100;
		if (xOffset >= crossThresh)
		{
			rArmY = 19 * height / 100;
		}
		int rArmX1 = 44 * width / 100;
		int rArmX2 = 48 * width / 100;
		g.drawLine(rArmX1, rArmY, rArmX2, rArmY);
		
		//Left Arm
		int lArmY1 = 71 * height / 100;
		if (xOffset >= crossThresh)
		{
			lArmY1 = 19 * height / 100;
		}
		int lArmX1 = 48 * width / 100;
		int lArmY2 = 67 * height / 100;
		if (xOffset >= crossThresh)
		{
			lArmY2 = 15 * height / 100;
		}
		int lArmX2 = 52 * width / 100;
		g.drawLine(lArmX1, lArmY1, lArmX2, lArmY2);
		
		//Right Leg
		int rLegY1 = 76 * height / 100;
		if (xOffset >= crossThresh)
		{
			rLegY1 = 25 * height /100;
		}
		int rLegX1 = 48 * width / 100;
		int rLegY2 = 83 * height / 100;
		if (xOffset >= crossThresh)
		{
			rLegY2 = 32 * height / 100;
		}
		int rLegX2 = 46 * width / 100;
		g.drawLine(rLegX1, rLegY1, rLegX2, rLegY2);
		
		//Left Leg
		int lLegY1 = 76 * height / 100;
		if (xOffset >= crossThresh)
		{
			lLegY1 = 25 * height / 100;
		}
		int lLegX1 = 48 * width / 100;
		int lLegY2 = 83 * height / 100;
		if (xOffset >= crossThresh)
		{
			lLegY2 = 32 * height / 100;
		}
		int lLegX2 = 50 * width / 100;
		g.drawLine(lLegX1, lLegY1, lLegX2, lLegY2);
		
				

		// Calculate the new xOffset position of the moving object.
		
		// I am editing the modulator for the draw function in order to have my vehicle be able to completely
		// disappear and wrap around without suddenly snapping into view.
		xOffset  = (xOffset + stepSize) % (4 * width / 3);

		// TODO: Use width, height, and xOffset to draw your scalable objects
		// at their new positions on the screen
		
		// This variable will start the car behind the x = 0 line, so it isn't suddenly snapping into view
		int easyIntro = (4 * height / 9);
		
		// This draws the first element of the vehicle.
		int cabLength = height / 3;
		int cabHeight = cabLength / 2;
		g.setColor(Color.red);
		g.fillRoundRect(xOffset - easyIntro, cabLength, cabHeight, cabHeight, 15, 15);
		
		// This draws the second element of the vehicle.
		int hoodLength = cabLength / 3;
		int hoodHeight = cabHeight / 2;		
		g.fillRoundRect((xOffset + cabLength * 2 / 5) - easyIntro, cabLength + (cabLength / 4), hoodLength, hoodHeight, 10, 10);
		
		// This will add the vehicle's tires (of which there will be three visible)
		g.setColor(Color.black);
		g.fillOval(xOffset + (cabLength * 5 / 10) - easyIntro, height / 3 + height / 7, cabLength / 6, cabHeight /4);
		g.fillOval(xOffset + (cabLength * 1 / 15) - easyIntro, height / 3 + height / 7, cabLength / 6, cabHeight /4);
		g.fillOval(xOffset + (cabLength * 3 / 15) - easyIntro, height / 3 + height / 7, cabLength / 6, cabHeight /4);
		
		// Also throw in a windshield to make the car look less plain
		int windshieldWidth = cabLength / 5;
		int windshieldHeight = cabHeight * 2 / 5;
		g.setColor(Color.cyan);
		g.fillRoundRect(xOffset + (4 * cabLength / 14) - easyIntro, cabLength + (cabLength / 180) + 2, windshieldWidth, windshieldHeight, 15, 15);
		
		//Now we just need to display some text in the scene, and we're done! And, of course, it's also a reference
		Color missingColor = new Color(255,162,0);
		g.setColor(missingColor);
		g.setFont(new Font ("Serif", Font.BOLD, 24));
		FontMetrics dementDimens = g.getFontMetrics();
		String inspirational = "Get thee to the beach, but don't";
		String message = "rip and tear into traffic!";
		int insX = (width - dementDimens.stringWidth(inspirational)) / 4;
		int insY = (85 * height / 100 + dementDimens.getHeight());
		int mesX = (width - dementDimens.stringWidth(message)) / 3;
		int mesY = (90 * height / 100 + dementDimens.getHeight());
		g.drawString(inspirational.toUpperCase(), insX, insY);
		g.drawString(message.toUpperCase(), mesX, mesY);
		
		
				
		
		// Put your code above this line. This makes the drawing smoother.
		Toolkit.getDefaultToolkit().sync();
	}


	//==============================================================
	// You don't need to modify anything beyond this point.
	//==============================================================


	/**
	 * Starting point for this program. Your code will not go in the
	 * main method for this program. It will go in the paintComponent
	 * method above.
	 *
	 * DO NOT MODIFY this method!
	 *
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		// DO NOT MODIFY THIS CODE.
		JFrame frame = new JFrame ("Traffic Animation");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TrafficAnimation());
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Constructor for the display panel initializes necessary variables.
	 * Only called once, when the program first begins. This method also
	 * sets up a Timer that will call paint() with frequency specified by
	 * the DELAY constant.
	 */
	public TrafficAnimation()
	{
		// Do not initialize larger than 800x600. I won't be able to
		// grade your project if you do.
		int initWidth = 600;
		int initHeight = 400;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);

		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically.
	 * DO NOT MODIFY this method!
	 */
	private void startAnimation()
	{
		ActionListener timerListener = new TimerListener();
		Timer timer = new Timer(DELAY, timerListener);
		timer.start();
	}

	/**
	 * Repaints the graphics panel every time the timer fires.
	 * DO NOT MODIFY this class!
	 */
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
}
