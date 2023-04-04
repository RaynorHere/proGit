import java.awt.Color;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class LitePegButton extends JButton {

	// Total list of colors available
	private final Color[] COLORS = { Color.black, Color.blue, Color.cyan, Color.green, Color.magenta, Color.orange,
			Color.pink, Color.red, Color.white, Color.yellow };

	// Reference number for background color
	private int colorIndex;

	public LitePegButton() {
		this.colorIndex = 0;
		this.setBackground(COLORS[colorIndex]);
	}

	// Get current color state
	public Color getColor() {
		return this.COLORS[colorIndex];
	}

	// Reset to black
	public void resetColor() {
		this.colorIndex = 0;
		this.setBackground(COLORS[colorIndex]);
		this.repaint();
	}

	// Increment one color forward
	public void colorChange() {
		this.colorIndex++;
		if (this.colorIndex > 9)
			this.colorIndex = 0;
		this.setBackground(COLORS[colorIndex]);
		this.repaint();
	}

}
