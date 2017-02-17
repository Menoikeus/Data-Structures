
public class Task {
	// task variables, like id, steps, and if it's newly created
	// (since we don't process on first turn)
	int number, numSteps;
	boolean newlyCreated;
	
	public Task(int n, int nS)
	{
		number = n;
		numSteps = nS;
		newlyCreated = true;
	}
	
	public boolean decrementSteps()
	{
		// if the thing is not newly created, then decrement
		if(newlyCreated)
			return newlyCreated = false;
		else
		{
			numSteps--;
			return true;
		}
	}
	
	// accessors
	public int getNumber()
	{
		return number;
	}
	
	public int getStepsRemaining()
	{
		return numSteps;
	}
}
