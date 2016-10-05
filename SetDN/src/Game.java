import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JFrame implements KeyListener{
	private BufferedImage set;
	private BufferedImage[] selectors;
	private BufferedImage[] selectedMarks;
	private BufferedImage background;
	private DrawArea drawArea;
	private JPanel canvas;
	private Timer timer;
	
	private int currentEvent;
	
	private static final int FRAMERATE = 15;
	
	private long timeLastRemoval;
	private long timeLastAddition;
	private long timeSetmode;
	//private int x, y;
	
	public ArrayList<Card> deck = new ArrayList<Card>();
	public Card[][] board = new Card[3][4];
	
	public Player[] players = new Player[2];
	
	public ArrayList<ArrayList<Integer>> deleteQueue;
	public ArrayList<Card> addQueue;
	
	public Game()
	{	
		players[0] = new HumanPlayer("Bob", 0);
		players[1] = new HumanPlayer("John", 1);
		//x=0;
		//y=0;
		timeLastRemoval = System.nanoTime();
		timeLastAddition = System.nanoTime();
		timeSetmode = System.nanoTime();
		
		currentEvent = -1;
		
		deleteQueue = new ArrayList<ArrayList<Integer>>();
		addQueue = new ArrayList<Card>();
		
		selectors = new BufferedImage[2];
		selectedMarks = new BufferedImage[2];
		//get image
		try {
			set = ImageIO.read(new File("./src/set.jpg"));
			selectors[0] = ImageIO.read(new File("./src/button.jpg"));
			selectors[1] = ImageIO.read(new File("./src/button_blue.jpg"));
			selectedMarks[0] = ImageIO.read(new File("./src/red_selected.jpg"));
			selectedMarks[1] = ImageIO.read(new File("./src/blue_selected.jpg"));
			background = ImageIO.read(new File("./src/background.jpg"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addKeyListener(this);
		
		/*not sure*/
		final TimerAction timeAction = new TimerAction();
		
		//starting some timer, from start of game?
		timer = new Timer(1000/FRAMERATE, timeAction);
		timer.start();
		/* */
		
		drawArea = new DrawArea();
		canvas = new JPanel();
		canvas.add(drawArea);
		drawArea.requestFocus();
		this.setContentPane(canvas);
		
		BuildDeck();
		
		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board[i].length; j++)
			{
				int index = (int)(Math.random() * deck.size());
				board[i][j] = deck.get(index);
				deck.remove(index);
			}
	}
	
	public void BuildDeck()
	{
		for(int i = 1; i <= 3; i++)
			for(int j = 0; j <= 2; j++)
				for(int k = 0; k <= 2; k++)
					for(int l = 0; l <= 2; l++)
						deck.add(new Card(i,j,k,l));
	}
	
	public void RepopulateBoard()
	{
		int nullCount = 0;
		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board[i].length; j++)
			{
				if(board[i][j] == null)
				{
					nullCount++;
				}
					
			}
		for(int i = 0; i < nullCount && addQueue.size() < nullCount && deck.size() > 0; i++)
			addQueue.add(deck.remove((int)(Math.random() * deck.size())));
	}
	
	/*refresh*/
	
	public class TimerAction implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			//System.out.println(ae.getActionCommand());
			//drawArea.addLogX(speed);
			//if(!done)
			drawArea.repaint();
		}
	}
	
	/* input */
	public void keyPressed(KeyEvent e)
	{
		// 1 = successfully added
		// 0 = moved
		// -1 = already exists
		// -2 = full queue (enter)
		currentEvent = e.getKeyCode();
		
		
		/*
			if(timer.isRunning())
			{
				if(e.getKeyCode() == KeyEvent.VK_LEFT)
				{
					x += x == 0 ? 0 : -1;
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					x += x == 8 ? 0 : 1;
				}
				if(e.getKeyCode() == KeyEvent.VK_UP)
				{
					y += y == 0 ? 0 : -1;
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN)
				{
					y += y == 8 ? 0 : 1;
				}
			}*/
	}
	
	public void keyReleased(KeyEvent e)
	{}
	
	public void keyTyped(KeyEvent e)
	{}
	
	public class DrawArea extends JPanel
	{
		private final int HEIGHT = 1024;
		private final int WIDTH = 1280;
		
		
		public DrawArea()
		{
			this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
			this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			g.drawImage(background, 
					0,
					0, null);
			
			//***** INPUT
			for(int w = 0; w < players.length; w++)
			{
				int key = currentEvent;
				
				//System.out.println("IN");
				int keycode = players[w].GetInput(key, board, deleteQueue, players[w == 0 ? 1 : 0].isSelecting());
					//take in input key, the board, the delete queue, and the other player's selecting mode
				if(keycode == -1)
					System.out.println("Already selected");
				else if(keycode == -2)
				{
					int[][] selectedArray = players[w].GetSelected();
					System.out.println("CHECKING");
					if(CheckValidSets(selectedArray))
					{
						players[w].changeScore(1);
						System.out.println("SCORE!");
						for(int a = 0; a < selectedArray.length; a++)
						{
							ArrayList<Integer> coords = new ArrayList<Integer>();
							coords.add(new Integer(selectedArray[a][0]));
							coords.add(new Integer(selectedArray[a][1]));
							deleteQueue.add(coords);
						}
					}
					else
					{
						players[w].changeScore(-1);
						System.out.println("LOSER!");
					}
					
					players[w].ClearQueue();
				}
				else if(keycode == 1)
					System.out.println("ADDED");
			}
			currentEvent = -1;
			
			//wait some time before deleting
			if(System.nanoTime() - timeLastRemoval > 1000 * (long)Math.pow(10,6) && deleteQueue.size() > 0 && !players[0].isSelecting() && !players[1].isSelecting())
			{
				board[deleteQueue.get(0).get(1)][deleteQueue.get(0).get(0)] = null;
				deleteQueue.remove(0);
				timeLastRemoval = System.nanoTime();
			}
			//wait some time before addingn
			if(System.nanoTime() - timeLastAddition > 1000 * (long)Math.pow(10,6) && System.nanoTime() - timeLastRemoval > 1001 * (long)Math.pow(10,6) && addQueue.size() > 0 && !players[0].isSelecting() && !players[1].isSelecting())
			{
				System.out.println("ADDING " + addQueue.size());
				boolean done = false;
				for(int i = 0; i < board.length && !done; i++)
					for(int j = 0; j < board[i].length && !done; j++)
						if(board[i][j] == null)
						{
							board[i][j] = addQueue.get(0);
							addQueue.remove(0);
							
							done = true;
						}
				timeLastAddition = System.nanoTime();
			}
			RepopulateBoard();
			
			//if you run out of time, you lose points!
			if(players[0].isSelecting() && players[0].timeSinceSelectStart() > 9000 * (long)Math.pow(10,6))
			{
				players[0].ClearQueue();
				players[0].changeScore(-1);
			}
			if(players[1].isSelecting() && players[1].timeSinceSelectStart() > 9000 * (long)Math.pow(10,6))
			{
				players[1].ClearQueue();
				players[1].changeScore(-1);
			}
			
			/*if(System.nanoTime() - timeSetmode > 10000 * (long)Math.pow(10,6))
			{
				if(players[0].isSelecting())
					players[0].ClearQueue();
				if(players[1].isSelecting())
					players[1].ClearQueue();
				timeSetmode = System.nanoTime();
			}*/
			
			for(int y = 0; y < board.length; y++)
				for(int z = 0; z < board[y].length; z++)
					if(board[y][z] != null)
					{
					g.drawImage(set.getSubimage( 
							board[y][z].getShape() * set.getWidth()/3 + (board[y][z].getNumber()-1) * set.getWidth()/9 + 2, 
							board[y][z].getColor() * set.getHeight()/3 + board[y][z].getShade() * set.getHeight()/9 + 2, 
							set.getWidth()/9 -2, 
							set.getHeight()/9-2), 
							z * set.getWidth()/9 + z * 10 + WIDTH/2-set.getWidth()/9 * 2, y * set.getHeight()/9 + y * 10 + HEIGHT/40, null);
					}
			
			for(int h = 0; h < players.length; h++)
			{
				g.drawImage(selectors[h].getSubimage(0, 0, set.getWidth()/18, selectors[h].getHeight()), 
						players[h].getX() * set.getWidth()/9 + set.getWidth()/18 * h       + players[h].getX() * 10 + WIDTH/2-set.getWidth()/9 * 2,
						players[h].getY() * set.getHeight()/9       + players[h].getY() * 10 + HEIGHT/40, null);
			}
			
			for(int h = 0; h < players.length; h++)
			{
				int[][] selectedCards = players[h].GetSelected();
				for(int i = 0; i < selectedCards.length; i++)
				{
					if(selectedCards[i][0] != -1 && selectedCards[i][1] != -1)
						g.drawImage(selectedMarks[h].getSubimage(0, 0, set.getWidth()/18, selectedMarks[h].getHeight()), 
								selectedCards[i][0] * set.getWidth()/9 + set.getWidth()/18 * h           + selectedCards[i][0] * 10 + WIDTH/2-set.getWidth()/9 * 2,
								selectedCards[i][1] * set.getHeight()/9 + 10            + selectedCards[i][1] * 10 + HEIGHT/40, null);
				}
			}
			for(int h = 0; h < players.length; h++)
			{
				if(players[h].isSelecting())
				{
					g.drawImage(selectedMarks[h].getSubimage(0, 0, set.getWidth()/18, selectedMarks[h].getHeight()), 
							WIDTH-200,
							HEIGHT/40, null);
				}
			}
				
			
			//subimage function for buffered image above
			
			for(int i = 0; i < players.length; i++)
			{
				g.setColor(Color.WHITE);
				g.setFont(new Font("Serif", Font.BOLD, 50));
		        g.drawString("Player: " + (i+1) + ": " + Integer.toString(10 * players[i].getScore()),20,50 + 50 * i);
			}

		}
		
	}
	
	public boolean CheckValidSets(int[][] sets)
	{
		String[] cardIDs = new String[3];
		for(int i = 0; i < sets.length; i++)
		{
			if(board[sets[i][1]][sets[i][0]] == null)
			{
				System.out.println("OI       OI" + sets[i][1] + " " + sets[i][0]);
			
			try
			{
			Thread.sleep(1000000);
			}
			catch(Exception e)
			{}}
			cardIDs[i] = board[sets[i][1]][sets[i][0]].getID();
		}
		
		for(int i = 0; i < cardIDs.length; i++)
		{
			System.out.println(cardIDs[i]);
		}
			
		boolean allValid = true;	
		for(int i = 0; i < cardIDs[0].length(); i++)
		{
			boolean allEqual = true;
			boolean allDif = true;
			for(int z = 0; z < cardIDs.length-1; z++)
			{
				if(!(Integer.parseInt(cardIDs[z].substring(i, i+1)) == Integer.parseInt(cardIDs[z+1].substring(i, i+1))))
					allEqual = false;
				if(Integer.parseInt(cardIDs[0].substring(i, i+1)) == Integer.parseInt(cardIDs[z+1].substring(i, i+1)))
					allDif = false;
			}
			System.out.println(allEqual + " " + allDif);
			if(allEqual == false && allDif == false)
				allValid = false;
		}
		
		return allValid;	
	}
	
	/*
	private static long deltaTime()
	{
		long delta = System.nanoTime() - currentTime;
		currentTime = System.nanoTime();
		
		return delta;
	}*/
}
