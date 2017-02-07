
public class Task {
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
		if(newlyCreated)
			return newlyCreated = false;
		else
		{
			numSteps--;
			return true;
		}
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public int getStepsRemaining()
	{
		return numSteps;
	}
}
