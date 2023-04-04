import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Lesson 18-19: Activity - Task Master GUI
 * 
 * This class represents the main TaskMaster JPanel.
 * 
 * It contains a ToDoListPanel and the control sub-panel.
 * 
 * @author CS121 Instructors
 * @version [semester]
 * @author [your name]
 */
@SuppressWarnings("serial")
public class TaskMasterPanel extends JPanel {
	/**
	 * Creates a new TaskMasterPanel.
	 */

	ToDoListPanel listPanel = new ToDoListPanel("My ToDo List");

	public TaskMasterPanel() {
		this.setPreferredSize(new Dimension(500, 400));
		this.setLayout(new BorderLayout());

		JTextField descriptionField = new JTextField(15);
		JPanel controlPanel = new JPanel();
		JButton getWorkButton = new JButton("Get Work");
		JScrollPane toDoListScrollPane = new JScrollPane(listPanel);
		JButton addTaskButton = new JButton("Add Task");

		// A buncha temp buttons to test function
		listPanel.addTask(new Task("Task 1"));
		listPanel.addTask(new Task("Task 2"));
		listPanel.addTask(new Task("Task 3"));

		//Control panel, with its south layout
		this.add(controlPanel, BorderLayout.SOUTH);

		//Scroll Panel
		this.add(toDoListScrollPane, BorderLayout.CENTER);
		
		toDoListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		toDoListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		//Populate control panel
		controlPanel.add(getWorkButton);
		controlPanel.add(descriptionField);
		controlPanel.add(addTaskButton);

		//Create 'get work' button functionality
		class GetWorkButtonListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				listPanel.showWorkDialog();
			}

		}
		//Apply functionality
		getWorkButton.addActionListener(new GetWorkButtonListener());
		
		
		//Add Task button functionality
		class AddTaskButtonListener implements ActionListener{
			
			public void actionPerformed(ActionEvent e) {
				String entText = descriptionField.getText();
				Task taskIn = new Task(entText);
				listPanel.addTask(taskIn);
			}
		}
		//Apply functionality
		addTaskButton.addActionListener(new AddTaskButtonListener());
		descriptionField.addActionListener(new AddTaskButtonListener());
		
		
		
	}
}
