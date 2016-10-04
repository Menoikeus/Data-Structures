import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class HumanPlayer extends Player{
	private int playerNumber;
	private int[] controls;
	
	public HumanPlayer(String n, int pN)
	{
		super(n);
		playerNumber = pN;
		
		controls = new int[5];
		
		System.out.println(playerNumber);
		switch(playerNumber)
		{
		case 0:
			controls[0] = KeyEvent.VK_LEFT;
			controls[1] = KeyEvent.VK_RIGHT;
			controls[2] = KeyEvent.VK_UP;
			controls[3] = KeyEvent.VK_DOWN;
			controls[4] = KeyEvent.VK_ENTER;
			break;
		case 1:
			controls[0] = KeyEvent.VK_A;
			controls[1] = KeyEvent.VK_D;
			controls[2] = KeyEvent.VK_W;
			controls[3] = KeyEvent.VK_S;
			controls[4] = KeyEvent.VK_SHIFT;
			break;
		}
	}
	
	public int GetInput(int keycode, Card[][] board, ArrayList<ArrayList<Integer>> dQueue, boolean otherSelecting)
	{
		if(keycode == controls[0])
			super.changeXBy(-1);
		if(keycode == controls[1])
			super.changeXBy(1);
		if(keycode == controls[2])
			super.changeYBy(-1);
		if(keycode == controls[3])
			super.changeYBy(1);
		if(keycode == controls[4] && !otherSelecting)
		{
			
			if(!super.isSelecting())
				super.toggleSelecting();
			boolean notDeleting = true;
			for(int a = 0; a < dQueue.size(); a++)
			{
				if(dQueue.get(a).get(0).intValue() == super.getX() && dQueue.get(a).get(1).intValue() == super.getY())
					notDeleting = false;
			}
			if(board[super.getY()][super.getX()] != null && notDeleting)
				return super.addSet();
			else
				return 0;
			
		}
		return 0;
		
		
		// 1 = successfully added
		// 0 = moved
		// -1 = already exists
		// -2 = full queue (enter)
	}
	
	public boolean isSelecting()
	{
		return super.isSelecting();
	}
	
}
