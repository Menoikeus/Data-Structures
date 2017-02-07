import java.util.ArrayList;

import chn.util.FileOutput;

public class Processor {
	ArrayList<Task> thread;
	Queue queue = new ListQueue();
	int tasksProcessed, timesFull, timesQueueEmpty, totalTasksEncountered;
	
	ArrayList<String> output;
	FileOutput outFile;
	
	public Processor(FileOutput f)
	{
		thread = new ArrayList<Task>();
		tasksProcessed = timesFull = timesQueueEmpty = totalTasksEncountered = 0;
		
		output = new ArrayList<String>();
		outFile = f;
	}
	
	public void addTask(Task t)
	{
		queue.enqueue(t);
		output.add("TASK #" + t.getNumber() + " (time = " + t.getStepsRemaining() + ") put into queue\n");
		totalTasksEncountered++;
	}
	
	public void moveToThread()
	{
		if(thread.size() < 10 && !queue.isEmpty())
		{
			Task t = (Task)queue.dequeue();
			thread.add(t);
			output.add("TASK #" + t.getNumber() + " (time = " + t.getStepsRemaining() + ") moved to processor\n");
		}
		if(queue.isEmpty())
		{
			timesQueueEmpty++;
		}
	}
	
	public void process()
	{
		for(int i = thread.size() - 1; i >= 0; i--)
		{
			Task t = thread.get(i);
			boolean processed = t.decrementSteps();
			if(t.getStepsRemaining() == 0)
			{
				thread.remove(i);
				output.add("TASK #" + t.getNumber() + " (time = " + t.getStepsRemaining() + ") completed\n");
				tasksProcessed++;
			}
			else if(processed)
				output.add("TASK #" + t.getNumber() + " (time = " + t.getStepsRemaining() + ") processing\n");
		}
		if(thread.size() == 10)
		{
			timesFull++;
		}
	}
	
	//accessors
	public int getQueueSize()
	{
		return queue.size();
	}
	
	public int getNumTasks()
	{
		return thread.size();
	}
	
	public int getTasksProcessed()
	{
		return tasksProcessed;
	}
	
	public int getTimesFull()
	{
		return timesFull;
	}
	
	public int getTimesQueueEmpty()
	{
		return timesQueueEmpty;
	}
	
	public int getTotalTasksEncountered()
	{
		return totalTasksEncountered;
	}
	
	public void print()
	{
		for(String s : output)
			outFile.print(s);
		output.clear();
	} 
}
