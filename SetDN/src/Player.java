import java.util.ArrayList;


public abstract class Player {
	//player stuff, name, score, selector location, selected cards queue, whether it is selecting or not, time since you started selecting
	private String name;
	private int score;
	private int selectorX, selectorY;
	private int[][] selected;
	private boolean selecting;
	private long selectTime;
	
	public Player(String n)
	{
		name = n;
		selectorX = 0;
		selectorY = 0;
		selected = new int[3][2];
		selecting = false;
		selectTime = 0;
		for(int i = 0; i < selected.length; i++)
			for(int h = 0; h < selected[i].length; h++)
				selected[i][h] = -1;
	}
	
	//abstract getinput
	public abstract int GetInput(int keycode, Card[][] board, ArrayList<ArrayList<Integer>> dQueue, boolean otherSelecting);
	
	public int getX()
	{
		return selectorX;
	}
	public int getY()
	{
		return selectorY;
	}
	
	//mutators for selector
	public void changeXBy(int x)
	{
		selectorX += x;
		if(selectorX > 3)
			selectorX = 3;
		else if(selectorX < 0)
			selectorX = 0;
	}
	public void changeYBy(int y)
	{
		selectorY += y;
		if(selectorY > 2)
			selectorY = 2;
		else if(selectorY < 0)
			selectorY = 0;
	}
	
	//add card to set, check if it's valid
	public int addSet()
	{
		int startIndex = -1;
		int removeIndex = -1;
		boolean isIn = false;
		//make sure its not already in the queue
		for(int i = 0; i < selected.length; i++)
		{
			if(selected[i][0] == selectorX && selected[i][1] == selectorY)
			{
				isIn = true;
				removeIndex = i;
			}
			if(selected[i][0] == -1 && selected[i][1] == -1 && startIndex == -1)
				startIndex = i;
		}
		
		System.out.println(startIndex);
		//if it is, remove that card and shift everything down
		if(isIn)
		{
			System.out.print("REMOVING (SUPPOSEDLY)");
			int tempSelected[][] = new int[3][2];
			for(int a = 0; a < tempSelected.length; a++)
			{
				tempSelected[a][0] = selected[a][0];
				tempSelected[a][1] = selected[a][1];
			}
			int count = 0;
			selected = new int[3][2];
			for(int j = 0; j < selected.length; j++)
			{
				if(j != removeIndex)
				{
					selected[count][0] = tempSelected[j][0];
					selected[count][1] = tempSelected[j][1];
					count++;
				}
			}
			selected[count][0] = -1;
			selected[count][1] = -1;
			return -1;
		} 
		else if(startIndex != -1)
		{
			//else if its full then return that there is a full queue
			//else say that it was successfully added
				selected[startIndex][0] = selectorX;
				selected[startIndex][1] = selectorY;
				if(startIndex == 2)
				{
					return -2;
				}
				else
					return 1;
		}
		System.out.println("ERROR");
		return -5;
		
		// 1 = successfully added
		// -1 = already exists
		// -2 = full queue (enter)
	}
	
	public int[][] GetSelected()
	{
		return selected;
	}
	
	public void ClearQueue()
	{
		for(int i = 0; i < selected.length; i++)
			for(int h = 0; h < selected[i].length; h++)
				selected[i][h] = -1;
		toggleSelecting();
	}

	public void toggleSelecting()
	{
		selecting = selecting == false? true : false;
		if(selecting == true)
			selectTime = System.nanoTime();
	}
	
	public boolean isSelecting()
	{
		return selecting;
	}
	
	public long timeSinceSelectStart()
	{
		return System.nanoTime() - selectTime;
	}
	
	public void changeScore(int n)
	{
		score += n;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public String getName()
	{
		return name;
	}

}
