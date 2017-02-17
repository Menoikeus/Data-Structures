import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import chn.util.FileOutput;

public class QDN {
	public static void main(String args[])
	{
		// create file to output to
		// create trial info to pass to grapher
		FileOutput outFile = new FileOutput("report.txt");
		ArrayList<int[]> trialInfo = new ArrayList<int[]>();
		
		// input
		final int NUM_TRIALS = 
				Integer.parseInt(JOptionPane.showInputDialog(null, 
						"1) One trial with no graphs or " +
						"2) two trials with graphs? " +
						"(TYPE '1' OR '2')"))
						== 1 ? 1 : 25;
		
		//
		int sumFull = 0, sumProcessed = 0;
		//
		
		// do it x trials
		int trials = 0;
		while(trials++ < NUM_TRIALS)
		{
			// create processor and variables
			int cycles = 0, numTasks = 1;
			Processor processor = new Processor(outFile);
			
			// run a thousand times
			while(cycles++ < 1000)
			{
				// randomly create task
				if(Math.random() < .63)
				{
					// add the task with random timestep
					processor.addTask(new Task(numTasks, 
							1 + (int)(Math.random() * 30)));
					numTasks++;
				}
				
				// processor logic, moving to thread and processing
				processor.moveToThread();
				processor.process();
				
				// print the info
				outFile.print(String.format("TIME STEP = %-5d"
						+ " QUEUE SIZE = %-3d"
						+ " TASKS IN PROCESS = %-5d%n", 
						cycles, processor.getQueueSize(), 
						processor.getNumTasks()));
				processor.print();
			}
			
			// get sums to check average
			sumFull += processor.getTimesFull();
			sumProcessed += processor.getTasksProcessed();
			
			// Final report
			outFile.println("\n\n=============");
			outFile.println("FINAL REPORT");
			outFile.println("=============\n");
			outFile.println("Total time steps elapsed = "
					+ 1000);
			outFile.println("Total number of tasks processed = "
					+ processor.getTasksProcessed());
			outFile.println("Number of time steps processor was full = "
					+ processor.getTimesFull());
			outFile.println("Number of time steps the queue was empty = "
					+ processor.getTimesQueueEmpty());
			outFile.println("Processes still in queue = "
					+ processor.getQueueSize());
			outFile.println("Total number of tasks created = "
					+ processor.getTotalTasksEncountered());
			outFile.println();
			
			trialInfo.add(new int[]{processor.getTasksProcessed(), 
					processor.getTimesFull(), 
					processor.getTimesQueueEmpty(), 
					processor.getQueueSize()});
		}
		
		// Some average info
		System.out.println("Average processes completed = "
				+ (double)sumProcessed/NUM_TRIALS);
		System.out.println("Average timesteps full = "
				+ (double)sumFull/NUM_TRIALS);
		
		// closes output file
		outFile.close();
		
		// graphics
		Window tool = new Window(trialInfo);
		tool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tool.setTitle("Graphs");
		tool.pack();
		tool.show();
	}
}
