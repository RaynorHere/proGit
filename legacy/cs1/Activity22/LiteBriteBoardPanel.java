import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LiteBriteBoardPanel extends JPanel {
	
	private LitePegButton[][] pegs;
	private JPanel playingField = new JPanel();
	
	public LiteBriteBoardPanel(ActionListener listener, int width, int height) {
		
		playingField.setLayout(new GridLayout(width, height));
		
		pegs = new LitePegButton[height][width];
		
		for (int i = 0; i < pegs.length; i++) {
			
			for (int j = 0; j < pegs[i].length; j++) {
												
				pegs[i][j] = new LitePegButton();
				pegs[i][j].addActionListener(listener);
				
			}
		}
		
		for (int i = 0; i < pegs.length; i++) {
			
			for (int j = 0; j < pegs[i].length; j++) {
				playingField.add(pegs[i][j]);
				playingField.revalidate();
			}
		}	
		
		this.add(playingField);
	}
	
	public void reset() {
		for (int i = 0; i < pegs.length; i++) {
			
			for (int j = 0; j < pegs[i].length; j++) {
				pegs[i][j].resetColor();
			}
		}
	}

}
