import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToDoListPanel extends JPanel {
	public ToDoList list;
	
	
	public ToDoListPanel(String name) {
		list = new ToDoList(name);
		JLabel labelOne = new JLabel(name);
		add(labelOne);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public void addTask(Task t) {
		list.addTask(t);
		TaskButton buttonOne = new TaskButton(t);
		add(buttonOne);
		this.revalidate();		
	}
	
	public void showWorkDialog() {
		Task taskOut;
		taskOut = list.getWork();
		if (taskOut == null) 
			JOptionPane.showMessageDialog(null, "Looks like you're done. Put the kettle on!", "Sweet ", JOptionPane.PLAIN_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, taskOut.getTask(), "Next Up", JOptionPane.PLAIN_MESSAGE);
		}
	

}
