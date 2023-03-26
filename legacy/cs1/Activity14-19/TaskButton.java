import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TaskButton extends JButton {

	private Task task;

	public TaskButton(Task taskIn) {
		this.task = taskIn;
		setText(taskIn.toString());
		addActionListener(new TaskButtonListener());
	}

	private class TaskButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clicked!");
			if (task.isComplete()) {
				task.setComplete(false);
				setText(task.toString());
				setForeground(Color.BLACK);
			} 
			else {
				task.setComplete(true);
				setText(task.toString());
				setForeground(Color.GRAY);
			}

		}

	}
}
