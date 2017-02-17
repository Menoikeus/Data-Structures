import java.util.ArrayList;

import chn.util.FileOutput;

public class Processor {
	ArrayList<Task> thread; // processes in thread
	Queue queue = new ListQueue(); // queue of tasks
	int tasksProcessed, timesFull, 
		timesQueueEmpty, totalTasksEncountered;
	// variables to output
	
	// file output stuff
	ArrayList<String> output;
	FileOutput outFile;
	
	// constructor
	public Processor(FileOutput f)
	{
		// create thread
		thread = new ArrayList<Task>();
		tasksProcessed = timesFull = 
				timesQueueEmpty = totalTasksEncountered = 0;
		
		// output is handled by adding output to a list,
		// then printing
		// this is because we need the header first
		output = new ArrayList<String>();
		outFile = f;
	}
	
	// add a task
	public void addTask(Task t)
	{
		// enqueue the said task, and then report it
		queue.enqueue(t);
		output.add("TASK #" + t.getNumber() + 
				" (time = " + t.getStepsRemaining() + 
				") put into queue");
		totalTasksEncountered++;
	}
	
	public void moveToThread()
	{
		// if we have room in the processor, and there's
		// something in the queue, add to the processor
		if(thread.size() < 10 && !queue.isEmpty())
		{
			Task t = (Task)queue.dequeue();
			thread.add(t);
			output.add("TASK #" + t.getNumber() + 
					" (time = " + t.getStepsRemaining() + 
					") moved to processor");
		}
		if(queue.isEmpty())
		{
			// report
			timesQueueEmpty++;
		}
	}
	
	public void process()
	{
		// go through processor and process
		for(int i = thread.size() - 1; i >= 0; i--)
		{
			// process, and if decrementSteps returns true.
			// the task is processing
			Task t = thread.get(i);
			boolean processed = t.decrementSteps();
			if(t.getStepsRemaining() == 0)
			{
				thread.remove(i); // remove when done
				output.add("TASK #" + t.getNumber() + 
						" (time = " + t.getStepsRemaining() + 
						") completed");
				tasksProcessed++;
			}
			else if(processed)
				output.add("TASK #" + t.getNumber() + 
						" (time = " + t.getStepsRemaining() + 
						") processing");
		}
		if(thread.size() == 10)
		{
			// report
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
	
	// we can print (in order) here
	public void print()
	{
		for(String s : output)
			outFile.println(s);
		output.clear();
	} 
}
