
public class Task implements Comparable<Task>


{
private String taskDescrip;
private int taskPriority;
private boolean taskComplete;
private String checkMark = "[ ]";
public enum Category {HOME, JOB, EXTRA};
public Category taskCategory;



//Constructors (3)
public Task(String entTask)
	{
		this.taskDescrip = entTask;
		this.taskPriority = 0;
		this.taskComplete = false;
		this.taskCategory = Category.EXTRA;
		
	}

public Task(String entTask, int entPrior)
	{
		this.taskDescrip = entTask;
		this.taskPriority = entPrior;
		this.taskComplete = false;
		this.taskCategory = Category.EXTRA;
	}

public Task(String entTask, int entPrior, Category inCat) 
	{
		this.taskDescrip = entTask;
		this.taskPriority = entPrior;
		this.taskComplete = false;
		this.taskCategory = inCat;
	}


//Setters (4)
public void setTask(String entTask)
	{
		this.taskDescrip = entTask;
	}

public void setPriority (int entPrior)
	{
		this.taskPriority = entPrior;
	}

public void setComplete (boolean taskComplete)
{
	if (taskComplete)
		{
			this.taskComplete = true;
			this.checkMark = "[X]";
		}
	else
	{
			this.taskComplete = false;
			this.checkMark = "[ ]";
	}
}

public void setCategory (Category inCat)
	{
		this.taskCategory = inCat;	
	}


//Getters (4)

public String getTask()
	{
		return this.taskDescrip;
	}

public int getPriority()
	{
		return this.taskPriority;
	}

public boolean isComplete()
	{
		return this.taskComplete;
	}

public Category getCategory()
	{
		return this.taskCategory;
	}



//toString Method
public String toString()
	{
		return checkMark + " " + taskDescrip + "; Priority: " + taskPriority + "; Category: " + taskCategory;
	}

//Equals Method
public boolean equals(Task compTask)
	{
		String testValue = compTask.getTask();
		Category testCat = compTask.getCategory();
				
		if (this.taskDescrip.equalsIgnoreCase(testValue) && this.taskCategory.equals(testCat))
			return true;
		else
			return false;
	}


public int compareTo(Task taskIn) 
	{
		//Comparisons between two tasks that are BOTH COMPLETE or BOTH INCOMPLETE
		if ((this.isComplete() && taskIn.isComplete()) || (!this.isComplete() && !taskIn.isComplete()))
			if (this.taskPriority == taskIn.getPriority())
				return 0;
			else if (this.taskPriority < taskIn.getPriority())
				return -1;
			else 
				return 1;
		
		//Original task is complete, compared task is not
		else if (this.isComplete() && !taskIn.isComplete())
			return -1;
		
		//Only remaining condition is this task is not complete, compared task is
		else 
			return 1;
	}


}
