import java.util.ArrayList;
import java.util.Collections;


public class ToDoList implements ToDoListInterface {

	// Core variables
	private String title;
	private ArrayList<Task> workList = new ArrayList<Task>();
	
	

	// Formatting
	String border = "*****************************************************";
	String header = "\t \t My ToDo List";

	//Variable for pulling most-pressing leftover work
	int pointVal = 0;
	
	public ToDoList(String title) {
		this.title = title;
	}

	public String getName() {

		return this.title;
	}

	// Basic task addition
	public void addTask(Task task) {
		workList.add(task);

	}

	// Description-only task addition
	public void addTask(String description) {
		Task task = new Task(description);
		workList.add(task);

	}
	
	public void addTask(String description, int priority) {
		Task task = new Task(description, priority);
		workList.add(task);
	}

	// Fully-loaded (lol) task addition
	public void addTask(String description, int priority, Task.Category category) {
		Task task = new Task(description, priority, category);
		workList.add(task);
	}

	public String toString() {

		// Headline
		String fullList = border + "\n" + header + "\n" + border + "\n";

		for (Task x : workList) {
			x.toString();
			fullList = fullList + x + "\n";
		}

		return fullList;
	}

	public Task getWork() {
		//ArrayList for tracking the priority of unfinished tasks
		ArrayList<Integer> urgency = new ArrayList<Integer>();
		
		//ArrayList for tracking tasks which are already completed
		ArrayList<Task> doneTasks = new ArrayList<Task>();

		// Can't get something from nothing, so:
		if (workList.isEmpty())
			return null;

		// Run through array of tasks
		for (Task x : workList) {

			//If a task is finished, it's added to the "done" list. Stay with me here.
			if (x.isComplete()) 
				doneTasks.add(x);
			else
				//Unfinished tasks go to the other list.
				urgency.add(x.getPriority());
			}

		//If everything's done, the "work" Array will be empty, so:
		if (urgency.isEmpty())
			return null;
		
		else
			//Because tasks went to either one Array or the other, the indices are mismatched.
			//So, now we edit the original ToDo that is passed, to remove any task that is
			//Done. Now, when we run Collections.min on the "urgency" Array, the index number
			//It passes back will match up with the index number of the lowest-priority task
			//Left over on the original ToDo.
			workList.removeAll(doneTasks);
			return workList.get(urgency.indexOf(Collections.min(urgency)));

	}

	public ArrayList<Task> getTaskList() {
		return workList;
	}

}
