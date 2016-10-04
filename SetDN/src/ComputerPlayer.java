import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class ComputerPlayer extends Player{
	private int playerNumber;
	private ArrayList<ComputerMoves> moveQueue;
	private ArrayList<Integer> designatedSet;
	
	private long timeSinceLastSet;
	private long timeSinceLastMove;
	
	public ComputerPlayer(String n, int pN)
	{
		super(n);
		playerNumber = pN;
		
		moveQueue = new ArrayList<ComputerMoves>();
		designatedSet = null;
		
		timeSinceLastSet = System.nanoTime();
		timeSinceLastMove = System.nanoTime();
	}
	
	public int GetInput(int keycode, Card[][] board, ArrayList<ArrayList<Integer>> dQueue, boolean otherSelecting)
	{	
		if(designatedSet == null && System.nanoTime() - timeSinceLastSet > 5000 * Math.pow(10, 6) && !otherSelecting)
		{
			ArrayList<ArrayList<Integer>> possibleSets = new ArrayList<ArrayList<Integer>>();
			
			for(int a = 0; a < board.length; a++)
				for(int b = 0; b < board[a].length; b++)
					for(int c = 0; c < board.length; c++)
						for(int d = 0; d < board[c].length; d++)
							for(int e = 0; e < board.length; e++)
								for(int f = 0; f < board[e].length; f++)
								{
									boolean notDeleting = true, notSameOrEmpty = false;;
									//if(board[a][b] != board[c][d] && board[c][d] != board[e][f] && board[a][b] != null && board[c][d] != null && board[e][f] != null)
									if(!(a + " " + b).equals(c + " " + d) 
											&& !(c + " " + d).equals(e + " " + f) 
											&& !(a + " " + b).equals(e + " " + f) 
											&& board[a][b] != null 
											&& board[c][d] != null 
											&& board[e][f] != null)
									{
										notSameOrEmpty = true;
										for(int k = 0; k < dQueue.size(); k++)
										{
											if(dQueue.get(k).get(0).intValue() == b
													&& dQueue.get(k).get(1).intValue() == a
													|| dQueue.get(k).get(0).intValue() == d 
													&& dQueue.get(k).get(1).intValue() == c
													|| dQueue.get(k).get(0).intValue() == f 
													&& dQueue.get(k).get(1).intValue() == e)
												notDeleting = false;
										}
									}
									
									if(notDeleting && notSameOrEmpty)
									{
										boolean allValid = true;	
										for(int i = 0; i < board[a][b].getID().length(); i++)
										{
											boolean allEqual = true;
											boolean allDif = true;
											
											if(!(Integer.parseInt(board[a][b].getID().substring(i, i+1)) == Integer.parseInt(board[c][d].getID().substring(i, i+1)) 
													&& Integer.parseInt(board[c][d].getID().substring(i, i+1)) == Integer.parseInt(board[e][f].getID().substring(i, i+1))))
												allEqual = false;
											if(Integer.parseInt(board[a][b].getID().substring(i, i+1)) == Integer.parseInt(board[c][d].getID().substring(i, i+1)) 
													|| Integer.parseInt(board[a][b].getID().substring(i, i+1)) == Integer.parseInt(board[e][f].getID().substring(i, i+1))  
													|| Integer.parseInt(board[c][d].getID().substring(i, i+1)) == Integer.parseInt(board[e][f].getID().substring(i, i+1)))
												allDif = false;
											
											if(allEqual == false && allDif == false)
												allValid = false;
											//System.out.println(allEqual + " " + allDif);
										}
										
										if(allValid)
										{
											System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + f);
											System.out.println(board[a][b] == null ? "FAILED" : "" + board[c][d] == null ? "FAILED" : "" + board[e][f] == null ? "FAILED" : "");
											ArrayList<Integer> coords = new ArrayList<Integer>();
											coords.add(new Integer(a));
											coords.add(new Integer(b));
											coords.add(new Integer(c));
											coords.add(new Integer(d));
											coords.add(new Integer(e));
											coords.add(new Integer(f));
											possibleSets.add(coords);
										}
									}
								}
			if(possibleSets.size() > 0 && !(moveQueue.size() > 0))
			{
				/*for(int i = 0; i < possibleSets.get(0).size(); i++)
					System.out.print(possibleSets.get(0).get(i).intValue() + " ");*/
				
				designatedSet = possibleSets.get((int)(Math.random() * possibleSets.size()));
				System.out.println("DESIGNATED SET: " + designatedSet.get(0).intValue() + designatedSet.get(1).intValue() + designatedSet.get(2).intValue() + designatedSet.get(3).intValue() + designatedSet.get(4).intValue() + designatedSet.get(5).intValue());
				GenerateMoveSet();
			}
			//System.out.println();
		}
		
		//slow down the computer
		//at least 500 milliseconds between moves
		//if they've set in the last 5 seconds, don't let them move
		if(System.nanoTime() - timeSinceLastMove > 500 * Math.pow(10, 6))
		{
			ComputerMoves move = null;
			if(moveQueue.size() == 1) //upon doing last move, clear designated set to generate new list
			{
				designatedSet = null;
				System.out.println("NEW SET");
			}
			if(moveQueue.size() > 0)
			{
				move = moveQueue.remove(0);
				switch(move)
				{
				case LEFT: 	super.changeXBy(-1);
							System.out.println("LEFT");
					break;
				case RIGHT:	super.changeXBy(1);
							System.out.println("RIGHT");
					break; 
				case UP:	super.changeYBy(-1);
							System.out.println("UP");
					break;
				case DOWN:	super.changeYBy(1);
							System.out.println("DOWN");
					break;
				case ENTER:	System.out.println(super.getX() + " " + super.getY());
							int returnVal = super.addSet();
							if(returnVal == -2)
								timeSinceLastSet = System.nanoTime(); //reset time once you set
							return returnVal;
				case SELECT: if(!super.isSelecting())
								super.toggleSelecting();
					break;
				}
			}
			timeSinceLastMove = System.nanoTime(); //reset time once you move
		}
		return 0;			
		//******
		
		
		// 1 = successfully added
		// 0 = moved
		// -1 = already exists
		// -2 = full queue (enter)
	}
	
	public boolean isSelecting()
	{
		return super.isSelecting();
	}
	
	public enum ComputerMoves
	{
		LEFT, UP, RIGHT, DOWN, ENTER, SELECT;
	}
	
	public void GenerateMoveSet()
	{
		moveQueue.add(ComputerMoves.SELECT); //we want to start selecting, so that we block out
											//the human player and prevent them from messing things up
		for(int i = 0; i < designatedSet.size()/2; i++)
		{
			System.out.println(designatedSet.get(0 + i * 2) + " " + designatedSet.get(1 + i * 2) + " \t");
			int xOffset, yOffset;
			if(i == 0)
			{
				yOffset = designatedSet.get(0 + i * 2) - super.getY();
				xOffset = designatedSet.get(1 + i * 2) - super.getX();
			}
			else
			{
				yOffset = designatedSet.get(0 + i * 2) - designatedSet.get(0 + (i - 1) * 2);
				xOffset = designatedSet.get(1 + i * 2) - designatedSet.get(1 + (i - 1) * 2);
			}
			for(int a = 0; a < Math.abs(xOffset); a++)
			{
				if(xOffset < 0)
					moveQueue.add(ComputerMoves.LEFT);
				if(xOffset > 0)
					moveQueue.add(ComputerMoves.RIGHT);
			}
			for(int a = 0; a < Math.abs(yOffset); a++)
			{
				if(yOffset < 0)
					moveQueue.add(ComputerMoves.UP);
				if(yOffset > 0)
					moveQueue.add(ComputerMoves.DOWN);
			}
			moveQueue.add(ComputerMoves.ENTER);
		}
		
		for(int i = 0; i < moveQueue.size(); i++)
			System.out.println(moveQueue.get(i));
		
	}
}
