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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JFrame implements KeyListener{
	//Images for the cards, selectors, background
	private BufferedImage set;
	private BufferedImage[] selectors;
	private BufferedImage[] selectedMarks;
	private BufferedImage background;
	
	//Graphics stuff
	private DrawArea drawArea;
	private JPanel canvas;
	private Timer timer;
	
	//Variable to hold the keypress to pass into main
	private int currentEvent;
	
	//Framerate
	private static final int FRAMERATE = 15;
	
	//times to set so that i can find time since last 'x'
	private long timeLastRemoval;
	private long timeLastAddition;
	private long timeSetmode;
	
	//deck and board
	public ArrayList<Card> deck = new ArrayList<Card>();
	public Card[][] board = new Card[3][4];
	
	//players
	public Player[] players = new Player[2];
	
	//queues for deleting stuff from and adding stuff to the board
	public ArrayList<ArrayList<Integer>> deleteQueue;
	public ArrayList<Card> addQueue;
	
	public Game()
	{	
		//pvp or pve
		String choice = JOptionPane.showInputDialog("Would you like to play against a human player or the AI? Enter 1 for human player or 2 for AI");
		if(choice.equals("1") || choice.toLowerCase().equals("human player"))
		{
			//names
			players[0] = new HumanPlayer(JOptionPane.showInputDialog("Player 1's name?"), 0);
			players[1] = new HumanPlayer(JOptionPane.showInputDialog("Player 2's name?"), 1);
		}
		else if(choice.equals("2") || choice.toLowerCase().equals("AI"))
		{
			players[0] = new HumanPlayer(JOptionPane.showInputDialog("Player 1's name?"), 0);
			players[1] = new ComputerPlayer("HAL", 1);
		}
		
		//initialize times
		timeLastRemoval = System.nanoTime();
		timeLastAddition = System.nanoTime();
		timeSetmode = System.nanoTime();
		
		//initialize current keycode
		currentEvent = -1;
		
		//initialize queues
		deleteQueue = new ArrayList<ArrayList<Integer>>();
		addQueue = new ArrayList<Card>();
		
		//create image arrays
		selectors = new BufferedImage[2];
		selectedMarks = new BufferedImage[2];
		//get images from file
		try {
			set = ImageIO.read(new File("./src/set.jpg"));
			selectors[0] = ImageIO.read(new File("./src/button.jpg"));
			selectors[1] = ImageIO.read(new File("./src/button_blue.jpg"));
			selectedMarks[0] = ImageIO.read(new File("./src/red_selected.jpg"));
			selectedMarks[1] = ImageIO.read(new File("./src/blue_selected.jpg"));
			background = ImageIO.read(new File("./src/background.jpg"));	
		} catch (IOException e) {}
		
		//keylistener adding
		addKeyListener(this);
		
		// frame refresh thing... probably
		final TimerAction timeAction = new TimerAction();
		
		//starting some timer, from start of game?
		timer = new Timer(1000/FRAMERATE, timeAction);
		timer.start();
		
		//grapics stuff
		drawArea = new DrawArea();
		canvas = new JPanel();
		canvas.add(drawArea);
		drawArea.requestFocus();
		this.setContentPane(canvas);
		
		//create deck and create board
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
		//make deck of every possible permutation
		for(int i = 1; i <= 3; i++)
			for(int j = 0; j <= 2; j++)
				for(int k = 0; k <= 2; k++)
					for(int l = 0; l <= 2; l++)
						deck.add(new Card(i,j,k,l));
	}
	
	public void RepopulateBoard()
	{
		//add cards to add queue if there are open spaces on the board
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
	
	//refresh each frame, i think
	public class TimerAction implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			drawArea.repaint();
		}
	}
	
	/* input */
	public void keyPressed(KeyEvent e)
	{
		//set current event to the keystroke
		currentEvent = e.getKeyCode(); 
	}
	
	public void keyReleased(KeyEvent e)
	{}
	
	public void keyTyped(KeyEvent e)
	{}
	
	//screen to draw
	public class DrawArea extends JPanel
	{
		//height/width
		private final int HEIGHT = 1024;
		private final int WIDTH = 1280;
		
		public DrawArea()
		{
			this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
			this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		}
		
		public void paintComponent(Graphics g)
		{
			//initialize stuff for drawing
			super.paintComponent(g);
			g.drawImage(background, 
					0,
					0, null);
			
			//***** INPUT
			for(int w = 0; w < players.length; w++)
			{
				//alright, here, we get the current events variable that we were set earlier in key listener
				int key = currentEvent;
				
				//the reason we put getInput here instead of the key listener is that the key listener ONLY TRIGGERS
				//when input is detected. However, the AI requires getinput to be triggered every frame. So, we simply
				//set currentEvent to -1 everyframe, and change it whenever input is detected
				//thus, getinput runs every frame on -1, which does nothing, except when there is input
				int keycode = players[w].GetInput(key, board, deleteQueue, players[w == 0 ? 1 : 0].isSelecting());
				//take in input key, the board, the delete queue, and the other player's selecting mode
				
				//get input returns a value to tell the game what just happened
				if(keycode == -1)
					System.out.println("Already selected");
				else if(keycode == -2) //if the player's queue has been filled
				{
					//check whether the set is valid
					int[][] selectedArray = players[w].GetSelected();
					System.out.println("CHECKING");
					if(CheckValidSets(selectedArray))
					{
						//increase score if you got a set
						players[w].changeScore(1);
						System.out.println("SCORE!");
						for(int a = 0; a < selectedArray.length; a++)
						{
							//add to the delete queue the cards in the set
							ArrayList<Integer> coords = new ArrayList<Integer>();
							coords.add(new Integer(selectedArray[a][0]));
							coords.add(new Integer(selectedArray[a][1]));
							deleteQueue.add(coords);
						}
					}
					else
					{
						//take away score if you failed to get a set
						players[w].changeScore(-1);
						System.out.println("LOSER!");
					}
					
					//clear the selected cards queue for the player
					players[w].ClearQueue();
				}
				else if(keycode == 1)
					System.out.println("ADDED");
			}
			//this is what i was talking about setting current event to -1 every frame
			currentEvent = -1;
			
			//wait some time before deleting (delay between cards being deleted)
			//also don't remove while they're selecting
			if(System.nanoTime() - timeLastRemoval > 1000 * (long)Math.pow(10,6) && deleteQueue.size() > 0 && !players[0].isSelecting() && !players[1].isSelecting())
			{
				board[deleteQueue.get(0).get(1)][deleteQueue.get(0).get(0)] = null;
				deleteQueue.remove(0);
				timeLastRemoval = System.nanoTime();
			}
			
			//wait some time before adding
			//also make sure you're not deleting
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
			//add to the add queue if the board has spaces
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
			
			//draw the cards onto the screen. based on their attributes, get the location of the texture on the
			//spritesheet
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
			
			//draw the player selectors
			for(int h = 0; h < players.length; h++)
			{
				g.drawImage(selectors[h].getSubimage(0, 0, set.getWidth()/18, selectors[h].getHeight()), 
						players[h].getX() * set.getWidth()/9 + set.getWidth()/18 * h       + players[h].getX() * 10 + WIDTH/2-set.getWidth()/9 * 2,
						players[h].getY() * set.getHeight()/9       + players[h].getY() * 10 + HEIGHT/40, null);
			}
			
			//draw symbols on top of the cards that have been selected
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
			
			//draw a symbol to show who is currently selecting
			for(int h = 0; h < players.length; h++)
				if(players[h].isSelecting())
					g.drawImage(selectedMarks[h].getSubimage(0, 0, set.getWidth()/18, selectedMarks[h].getHeight()), 
							WIDTH-200,
							HEIGHT/40, null);
			
			//draw string showing player and their score
			for(int i = 0; i < players.length; i++)
			{
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", Font.BOLD, 50));
		        g.drawString(players[i].getName() + ": " + Integer.toString(10 * players[i].getScore()),20,50 + 50 * i);
			}

		}
		
	}
	
	public boolean CheckValidSets(int[][] sets)
	{
		//put the card ids of the selected cards into a string array
		String[] cardIDs = new String[3];
		for(int i = 0; i < sets.length; i++)
			cardIDs[i] = board[sets[i][1]][sets[i][0]].getID();
		
		//go through each string index and check if they are the same or all different
		//for all the cards
		boolean allValid = true;	
		for(int i = 0; i < cardIDs[0].length(); i++)
		{
			boolean allEqual = true;
			boolean allDif = true;
			
			//check if all equal
			for(int z = 0; z < cardIDs.length-1; z++)
			{
				if(!(Integer.parseInt(cardIDs[z].substring(i, i+1)) == Integer.parseInt(cardIDs[z+1].substring(i, i+1))))
					allEqual = false;
			}
			//check if all dif
			if(Integer.parseInt(cardIDs[0].substring(i, i+1)) == Integer.parseInt(cardIDs[1].substring(i, i+1)) 
					|| Integer.parseInt(cardIDs[1].substring(i, i+1)) == Integer.parseInt(cardIDs[2].substring(i, i+1)) 
					|| Integer.parseInt(cardIDs[0].substring(i, i+1)) == Integer.parseInt(cardIDs[2].substring(i, i+1)) )
				allDif = false;
			
			//based on previous values, is it valid?
			if(allEqual == false && allDif == false)
				allValid = false;
		}
		return allValid;	
	}
}
