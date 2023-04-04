

public class TaskMaster 
{

	
	public static void main(String[] args) 
	{
		//Task 1 Set
		Task task1 = new Task("Finish Activity 14", 1, Task.Category.JOB);
		
		//Task 2 Set
		Task task2 = new Task("Give Tigger a bath", 10, Task.Category.EXTRA);
		
		//Task 3 Set
		Task task3 = new Task("Finish Activity 14", 15, Task.Category.JOB);
		
		
		//Print task 1 with default "complete" value; "false" (open square brackets).
		System.out.println(task1.toString());
				
		//Print task 1 with method-changed "complete" value; "true" ("X" in square brackets).
		task1.setComplete(true);
		System.out.println(task1.toString());
		
		//Print task 2 with original set priority; "10".
		System.out.println(task2.toString());
		
		//Print task 2 with method-changed priority; "20".
		task2.setPriority(20);
		System.out.println(task2.toString());
		
		//Print task 3, meant for comparing task names
		System.out.println(task3.toString());
		
		
		//Compare task 1 and task 3's descriptions
		System.out.println("\n Let's see if tasks 1 and 3 are the same task.");
		if (task1.equals(task3))
			System.out.println("Tasks 1 and 3 are identical. \n");
		else
			System.out.println("Tasks 1 and 3 are unidentical. \n");
			
		//Test the compareTo function on tasks 1 and 2
		System.out.println("Let's compare tasks 1 and 2.");
		if (task1.compareTo(task2) < 0)
			System.out.println("Task 1 is less important than task 2.");
		else if (task1.compareTo(task2) == 0)
			System.out.println("Task 1 is equally as important as task 2.");
		else
			System.out.println("Task 1 is more important than task 2.");
		
		//ToDo List
		ToDoList myToDo = new ToDoList("My ToDo List");
		
		//Populate
		myToDo.addTask(task1);
		myToDo.addTask(task2);
		myToDo.addTask(task3);
		myToDo.addTask("Sift at Least 2 Albums Into Library", 5, Task.Category.HOME);

		
		//String 'em out
		System.out.println(myToDo.toString());
		
		
		//Get Work Function test
		System.out.println("Got work?");
		System.out.println(myToDo.getWork() + "\n");
		
		//Null "Hypothesis" (lol)
		System.out.println("Empty List?");
		ToDoList listLess = new ToDoList("List-Less");
		System.out.println(listLess.getWork()+ "\n");
		
		//Full "Hypothesis" (does that scan?)
		System.out.println("Finished List?");
		Task finTask1 = new Task("Finished 1", 10);
		Task finTask2 = new Task("Finished 2", 20);
		finTask1.setComplete(true);
		finTask2.setComplete(true);
		//Name is strictly for my fellow Completionist fans
		ToDoList finishIst = new ToDoList("Finishist");
		finishIst.addTask(finTask1);
		finishIst.addTask(finTask2);
		System.out.println(finishIst.getWork());
		
		
		
		
		System.out.println("\n \nEnumerations Experimentation:\n");
				
		//Up above, tasks 1 and 3 come out identical, because their descriptions
		//and categories are the same. Let's change a category, string them out
		//again, and compare them.
		
		System.out.println(task1.toString());
		System.out.println(task3.toString());
		
		System.out.println("\nChanging task 1's category. \n");
		task1.setCategory(Task.Category.EXTRA);
		
		System.out.println(task1.toString());
		System.out.println(task3.toString() +"\n");
				
		System.out.println("Comparing task 1 and 3, now with changed category.");
		if (task1.equals(task3))
			System.out.println("Something is clearly wrong; they're still identical");
		else
			System.out.println("The system works; the two tasks are now unidentical.");
		
		System.out.println("\n \nNow let's check out adding a task without a category, to make sure it applies the \"Extra\" category.");
		System.out.println("For fun, we'll set it to print out complete if it successfully applies the proper category.");
		
		Task task4 = new Task("Test Enumeration Preparedness", 1);
		if (task4.taskCategory == Task.Category.EXTRA)
			task4.setComplete(true);
		System.out.println(task4.toString());
		
		System.out.println("\n\n So, if that worked, let's set up a false case, to make sure we're right for the right reasons.");
		Task task5 = new Task("This Task Should Print Incomplete", 1);
		if (task5.taskCategory == Task.Category.HOME)
			task5.setComplete(true);
		System.out.println(task5.toString());
		
		System.out.println("\n\n Now, to make that task print out complete:");
		if (task5.taskCategory == Task.Category.EXTRA)
			task5.setComplete(true);
		System.out.println("Ignore the name of the task; it should be complete now.");
		System.out.println(task5.toString());
		
		
		
				
	}

}
