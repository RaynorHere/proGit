import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.text.html.ImageView;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Point;



/**
 * Lesson 4: Activity - Using Classes and Objects
 * 
 * Uses the MiniFig class to draw a custom avatar.
 * 
 * @author CS121 instructors
 * @author <you>
 */
@SuppressWarnings("serial")
public class MyAvatar extends JPanel
{
	public final int INITIAL_WIDTH = 800;
	public final int INITIAL_HEIGHT = 600;
	
	/**
	 * Draws the picture in the panel. This is where all of your
	 * code will go.
	 * @param canvas The drawing area of the window.
	 */
	public void paintComponent (Graphics g)
	{
		/* Store the height and width of the panel at the time
		 * the paintComponent() method is called.
		 */
		int currentHeight = getHeight();
		int currentWidth = getWidth();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, currentWidth, currentHeight);
		
		/* This is the anchor point for the MiniFig (x,y) -> (mid,top) */
		int mid = currentWidth / 2;
		int top = currentHeight / 4;
		

		
		
		/* This is the scaler that is used to calculate the dimensions (height / width) 
		 * of each of the MiniFig components. It uses the Math.min() function to select
		 * the smaller of currentWidth/INITIAL_WIDTH and currentHeight/INITIAL_HEIGHT.
		 * This way all the components are scaled to fit within the smaller of the two 
		 * panel dimensions.
		 */
		double scaleFactor = Math.min(currentWidth/(double)INITIAL_WIDTH,currentHeight/(double)INITIAL_HEIGHT );
		
		//Moon in the background
		int moonBoxX1 = (int)(currentWidth - 50 * scaleFactor);
		int moonBoxY1 = (int)(-100 * scaleFactor);
		int moonBoxX2 = (int)(currentWidth + 100 * scaleFactor);
		int moonBoxY2 = (int)(300 * scaleFactor);			
		g.setColor(Color.lightGray);
		g.fillOval(moonBoxX1, moonBoxY1, moonBoxX2, moonBoxY2);
		
		

		// TODO: 1. Instantiate a new Point object called "anchor". Use "mid" as your x value and
		//       "top" as your y value.
		
		Point anchor = new Point(mid, top);
		
		// TODO: 2. Instantiate a new MiniFig object and give the reference variable a name of a person, 
		//       such as "bob". Use the MiniFig constructor with the following
		//       parameters: MiniFig(g, scaleFactor, anchor)
		
		MiniFig Raynor = new MiniFig(g, scaleFactor, anchor);
		
		// TODO: 3. Create a new custom Color object. An example is shown below.
		
		Color royalPurple = new Color (138, 42, 226);
		
		// TODO: 4. Invoke the setTorsoColor(Color color) method on your MiniFig instance.
		//       Use your color object as a parameter to change the shirt color.
		//       This lets you change the color of "bob's" shirt. :)
		
		Raynor.setTorsoColor(royalPurple);
		
				
		// TODO: 5. Invoke the draw() method on your MiniFig instance. This is where "bob" is displayed on the screen.
		
		//Raynor.draw();
		
		// TODO: 6. Adjust the size of your Avatar's window. Notice how the avatar does not stay grounded
		//       on the grass. To fix this, use the getBaseMidPoint() method to find the the base mid point of your
		//       MiniFig. This method returns a Point object that represents the x,y coordinates at the
		//       base of the MiniFig, right between its feet. 
		//       Replace the hard-coded value of grassYOffset with the y value 
		//       of the returned point.
		
		Raynor.getBaseMidPoint();
		
		double grassY = Raynor.getBaseMidPoint().getY();
		int grassYOffset = (int) grassY;
				
		
		Color marsRed = new Color (255,51,51);
		g.setColor(marsRed);
		g.fillRect(0, grassYOffset, currentWidth, currentHeight - grassYOffset);

		// TODO: 7. Create an Alias of for your MiniFig object and change the torso color of the alias.
		//       If in step 2 you used the variable name "bob", you can create an alias named "robert"
		//       using the following:
		//       MiniFig robert = bob;
		//       robert.setTorsoColor(Color.RED);

		MiniFig Kerrigan = new MiniFig(g, scaleFactor, anchor);
		Color Silver = new Color(50, 50, 50);
		
		Color darkGrey = new Color(153, 153, 153);
		Color deepPurple = new Color(75, 0, 130);
		Kerrigan.setTorsoColor(deepPurple);
		Kerrigan.setLegColor(darkGrey);
		Kerrigan.setArmColor(Color.red);
		
		Raynor = Kerrigan;
		//Raynor.setTorsoColor(royalPurple);
	
		
		
	    // TODO: 8. Comment out the draw statement under TODO item 5 and then draw the original MiniFig 
		//       below. If you used the variable name "bob" is would simply be the following:
		//       bob.draw();
		//       What color is Bob's Shirt?  Why?
				
		//Take slashes off one, place on other to display.
		
		Raynor.draw();
		//Kerrigan.draw();
		
		//Code for setting up helmet/mask frame
		int helmXCorner = (int)Raynor.getRightShoulderPoint().getX();
		int helmYCorner = (int)Raynor.getTopMidPoint().getY();
		int helmWidth = (int)2 * Raynor.getWidth() / 5;
		int helmHeight = (int)2 * Raynor.getHeight() / 7;
		g.setColor(Silver);
		g.fillRoundRect(helmXCorner, helmYCorner, helmWidth, helmHeight, 10, 10);
		
		//Code for setting up helmet/mask optics. Each eye is one circle inside another.
		
		//Outer Optics
		int leftEyeOuterX = (int) ((int)Raynor.getCapPoint().getX() - 35 * scaleFactor);
		int leftEyeOuterY = (int) ((int)Raynor.getCapPoint().getY() + 15 * scaleFactor);
		int eyeOuterWidth = (int) Raynor.getFaceWidth() / 3;
		int eyeOuterHeight = (int) Raynor.getFaceWidth() / 3;
		int rightEyeOuterX = (int) ((int)Raynor.getCapPoint().getX() + 5 * scaleFactor);
		int rightEyeOuterY = (int) ((int)Raynor.getCapPoint().getY() + 15 * scaleFactor);
		g.setColor(Color.blue);
		g.fillOval(leftEyeOuterX, leftEyeOuterY, eyeOuterWidth, eyeOuterHeight);
		g.fillOval(rightEyeOuterX, rightEyeOuterY, eyeOuterWidth, eyeOuterHeight);

		//Inner Optics
		int leftEyeInnerX = (int) ((int)Raynor.getCapPoint().getX() - 30 * scaleFactor);
		int leftEyeInnerY = (int) ((int)Raynor.getCapPoint().getY() + 20 * scaleFactor);
		int eyeInnerWidth = (int) Raynor.getFaceWidth() / 5;
		int eyeInnerHeight = (int) Raynor.getFaceWidth() / 5;
		int rightEyeInnerX = (int) ((int)Raynor.getCapPoint().getX() + 10 * scaleFactor);
		int rightEyeInnerY = (int) ((int)Raynor.getCapPoint().getY() + 20 * scaleFactor);
		
		Color lightBlue = new Color(51, 204, 255);
		g.setColor(lightBlue);
		g.fillOval(leftEyeInnerX, leftEyeInnerY, eyeInnerWidth, eyeInnerHeight);
		g.fillOval(rightEyeInnerX, rightEyeInnerY, eyeInnerWidth, eyeInnerHeight);
		
		
		//Light decal between eyes, along forehead. Decal is meant to be same color as outer optics
		
		int decalX = (int) ((int)Raynor.getTopMidPoint().getX() - 3 * scaleFactor);
		int decalY = (int) ((int)Raynor.getTopMidPoint().getY() + 10 * scaleFactor);
		int decalWidth = (int) Raynor.getFaceWidth() / 15;
		int decalHeight = (int) Raynor.getFaceHeight() / 3;
		g.setColor(Color.blue);
		g.fillRect(decalX, decalY, decalWidth, decalHeight);
		
		//Line decal to the sides of eyes; should be same color as light decal
		
		int frameLeftStartX = (int) ((int)Raynor.getRightShoulderPoint().getX() + 2 * scaleFactor);
		int frameLeftEndX = (int)(frameLeftStartX + 6 * scaleFactor);
		int frameY = (int) ((int)Raynor.getCapPoint().getY() + 28 * scaleFactor);
		int frameRightStartX = (int)((int)Raynor.getLeftShoulderPoint().getX() - 10 * scaleFactor);
		int frameRightEndX = (int)((int)Raynor.getLeftShoulderPoint().getX() - 4 * scaleFactor);
		g.setColor(Color.blue);
		g.drawLine(frameLeftStartX, frameY, frameLeftEndX, frameY);
		g.drawLine(frameRightStartX, frameY, frameRightEndX, frameY);
		
		// Arc striation decals above eyes
		
		int leftArcStriX = leftEyeOuterX;
		int arcStriY1 = (int) (leftEyeOuterY -10 * scaleFactor);
		int arcStriY2 = (int) (leftEyeOuterY -20 * scaleFactor);
		int arcWidth = eyeOuterWidth;
		int arcHeight = eyeOuterHeight;
		
		int rightArcStriX = rightEyeOuterX;
		
		g.setColor(Color.red);
		g.drawArc(leftArcStriX, arcStriY1, arcWidth, arcHeight, 20, 140);
		g.drawArc(leftArcStriX, arcStriY2, arcWidth, arcHeight, 20, 140);
		g.drawArc(rightArcStriX, arcStriY1, arcWidth, arcHeight, 20, 140);
		g.drawArc(rightArcStriX, arcStriY2, arcWidth, arcHeight, 20, 140);
		
		//Accessory to hold (Could not figure out how to import image, so drew one instead)
		
		int saberHiltX = (int)(Raynor.getRightHandCenterPoint().getX());
		int saberHiltY = (int)(Raynor.getRightHandCenterPoint().getY());
		int saberPointX = (int)(Raynor.getRightHandCenterPoint().getX() - 2 * Raynor.getWidth() / 5);
		int saberPointY = (int)(Raynor.getRightHandCenterPoint().getY() - Raynor.getHeight() / 3);
		
		g.setColor(Color.green);
		g.drawLine(saberHiltX, saberHiltY, saberPointX, saberPointY);
		
		
		
		
		/* The torso color of the minifig is the color originally assigned to the alias (unless you take out
		 * my re-assignment of the torso to purple, above. This is because the minifig is an object, and that 
		 * statement, "Kerrigan = Raynor" causes those two names to point to the same object/address, meaning
		 * as you change one, you change the other. It also means no matter which one you draw, you'll get the
		 * exact same figure.
		*/	 
		
	}


	/**
	 * Constructor (panel initialization)
	 */
	public MyAvatar()
	{
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(INITIAL_WIDTH, INITIAL_HEIGHT));
	}

	/**
	 * Sets up a JFrame and the MiniFigDriver panel.
	 * @param args unused
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("MyAvatar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MyAvatar());
		frame.pack();
		frame.setVisible(true);
	}
}
