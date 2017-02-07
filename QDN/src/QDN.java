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
						"2) two trials with graphs? (TYPE '1' OR '2')"))
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
			
			int x = 0;
			int counter = 1;
			// run a thousand times
			while(cycles++ < 1000)
			{
				//try{Thread.sleep(1);}catch(Exception e){}
				if(Math.random() < .63)
				//if(true)
				{
					x++;
					processor.addTask(new Task(numTasks, 1+(int)(Math.random() * 30)));
					//processor.addTask(new Task(numTasks, counter));
					counter++;
					
					numTasks++;
				}
				
				processor.moveToThread();
				processor.process();
				
				outFile.print(String.format("TIME STEP = %-5d QUEUE SIZE = %-3d TASKS IN PROCESS = %-5d%n", cycles, processor.getQueueSize(), processor.getNumTasks()));
				processor.print();
			}
			System.out.println((double)x/1000);
			
			sumFull += processor.getTimesFull();
			sumProcessed += processor.getTasksProcessed();
			
			outFile.println("\n\n=============");
			outFile.println("FINAL REPORT");
			outFile.println("=============\n");
			outFile.println("Total number of tasks processed = " + processor.getTasksProcessed());
			outFile.println("Number of time steps processor was full = " + processor.getTimesFull());
			outFile.println("Number of time steps the queue was empty = " + processor.getTimesQueueEmpty());
			outFile.println("Processes still in queue = " + processor.getQueueSize());
			outFile.println("Total number of tasks created = " + processor.getTotalTasksEncountered());
			outFile.println();
			
			trialInfo.add(new int[]{processor.getTasksProcessed(), processor.getTimesFull(), processor.getTimesQueueEmpty(), processor.getQueueSize()});
		}
		
		System.out.println("Average processes completed = " + (double)sumProcessed/NUM_TRIALS);
		System.out.println("Average timesteps full = " + (double)sumFull/NUM_TRIALS);
		
		outFile.close();
		
		Window tool = new Window(trialInfo);
		tool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tool.setTitle("Graphs");
		tool.pack();
		tool.show();
	}
}
