import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

public class Window extends JFrame implements KeyListener{
	//Graphics stuff
	private DrawArea drawArea;
	private JPanel canvas;
	private Timer timer;
		
	private static final int FRAMERATE = 15;
	
	ArrayList<int[]> trialInfo;
	State currentState;
	
	public Window(ArrayList<int[]> a)
	{
		trialInfo = a;
		currentState = State.PROCESSED;
		
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
		
		JOptionPane.showMessageDialog(null, "WHAT NUMBER KEY DO I PRESS TO ACCESS A GRAPH?\n\n" +
											"1: TOTAL TASKS PROCESSED\n" +
											"2: NUMBER OF TIME STEPS THE PROCESSOR WAS FULL\n" +
											"3: NUMBER OF TIME STEPS THE QUEUE WAS EMPTY\n" +
											"4: PROCESSES STILL IN QUEUE\n");
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_1)
		{
			currentState = State.PROCESSED;
		}
		if(e.getKeyCode() == KeyEvent.VK_2)
		{
			currentState = State.FULL;
		}
		if(e.getKeyCode() == KeyEvent.VK_3)
		{
			currentState = State.EMPTY;
		}
		if(e.getKeyCode() == KeyEvent.VK_4)
		{
			currentState = State.QSIZE;
		}
		if(e.getKeyCode() == KeyEvent.VK_F1 || e.getKeyCode() == KeyEvent.VK_H)
		{
			JOptionPane.showMessageDialog(null, "WHAT NUMBER KEY DO I PRESS TO ACCESS A GRAPH?\n\n" +
					"1: TOTAL TASKS PROCESSED\n" +
					"2: NUMBER OF TIME STEPS THE PROCESSOR WAS FULL\n" +
					"3: NUMBER OF TIME STEPS THE QUEUE WAS EMPTY\n" +
					"4: PROCESSES STILL IN QUEUE\n");
		}
	}
	
	public void keyReleased(KeyEvent e)
	{}
	
	public void keyTyped(KeyEvent e)
	{}
	
	public class TimerAction implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			drawArea.repaint();
		}
	}
	
	public class DrawArea extends JPanel
	{
		private final int HEIGHT = 1024, WIDTH = 1366;
		
		public DrawArea()
		{
			this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
			this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		}
		
		public void paintComponent(Graphics g)
		{
			//initialize stuff for drawing
			super.paintComponent(g);
			
			final int HEIGHT = 650, WIDTH = 50 * trialInfo.size(), VERTOFFSET = 10, HORIZOFFSET = 100;
			
			g.drawString("Press F1 or H for help", this.getWidth() - 150, 10);
			
			g.drawRect(HORIZOFFSET, HEIGHT + VERTOFFSET, WIDTH, 1);
			g.drawRect(HORIZOFFSET, VERTOFFSET, 1, HEIGHT);
			
			switch(currentState)
			{
				case PROCESSED:
				{
					g.drawString("NUMBER OF TOTAL TASKS PROCESSED", 560, 10);
					for(int i = 0; i < 14; i++) //y axis
					{
						g.drawString(Integer.toString((i) * 50), HORIZOFFSET-37, 5 + HEIGHT + VERTOFFSET - i * 50);
						g.fillRect(95, HEIGHT + VERTOFFSET - i * 50, 10, 1);
					}
					for(int i = 0; i < trialInfo.size(); i++)
						g.drawString(Integer.toString(i+1), 120 + i * 50, HEIGHT + VERTOFFSET + 25);
					
					for(int i = 0; i < trialInfo.size(); i++)
					{
						g.fillRect(HORIZOFFSET + 15 + i * 50, 
								VERTOFFSET + (HEIGHT - trialInfo.get(i)[0]), 
								25, 
								trialInfo.get(i)[0]);
						g.drawString(Integer.toString(trialInfo.get(i)[0]), HORIZOFFSET + 15 + i * 50, VERTOFFSET + (HEIGHT - trialInfo.get(i)[0]) - 10);
					}
					break;
				}
				case FULL:
				{
					g.drawString("NUMBER OF TIME STEPS THE PROCESSOR WAS FULL", 525, 10);
					for(int i = 0; i < 14; i++) //y axis
					{
						g.drawString(Integer.toString((i) * 50), HORIZOFFSET-37, 5 + HEIGHT + VERTOFFSET - i * 50);
						g.fillRect(95, HEIGHT + VERTOFFSET - i * 50, 10, 1);
					}
					for(int i = 0; i < trialInfo.size(); i++)
						g.drawString(Integer.toString(i+1), 120 + i * 50, HEIGHT + VERTOFFSET + 25);
					
					for(int i = 0; i < trialInfo.size(); i++)
					{
						g.fillRect(HORIZOFFSET + 15 + i * 50, 
								VERTOFFSET + (HEIGHT - trialInfo.get(i)[1]), 
								25, 
								trialInfo.get(i)[1]);
						g.drawString(Integer.toString(trialInfo.get(i)[1]), HORIZOFFSET + 15 + i * 50, VERTOFFSET + (HEIGHT - trialInfo.get(i)[1]) - 10);
					}
					break;
				}
				case EMPTY:
				{
					g.drawString("NUMBER OF TIME STEPS THE QUEUE WAS EMPTY", 525, 10);
					for(int i = 0; i < 14; i++) //y axis
					{
						g.drawString(Integer.toString((i) * 30), HORIZOFFSET-37, 5 + HEIGHT + VERTOFFSET - i * 50);
						g.fillRect(95, HEIGHT + VERTOFFSET - i * 50, 10, 1);
					}
					for(int i = 0; i < trialInfo.size(); i++)
						g.drawString(Integer.toString(i+1), 120 + i * 50, HEIGHT + VERTOFFSET + 25);
					
					for(int i = 0; i < trialInfo.size(); i++)
					{
						g.fillRect(HORIZOFFSET + 15 + i * 50, 
								VERTOFFSET + (HEIGHT - 5 * trialInfo.get(i)[2]/3), 
								25, 
								5 * trialInfo.get(i)[2]/3);
						g.drawString(Integer.toString(trialInfo.get(i)[2]), HORIZOFFSET + 15 + i * 50, VERTOFFSET + (HEIGHT - 5 * trialInfo.get(i)[2]/3) - 10);
					}
					break;
				}
				case QSIZE:
				{
					g.drawString("PROCESSES STILL IN QUEUE", 590, 10);
					for(int i = 0; i < 14; i++) //y axis
					{
						g.drawString(Integer.toString((i) * 20), HORIZOFFSET-37, 5 + HEIGHT + VERTOFFSET - i * 50);
						g.fillRect(95, HEIGHT + VERTOFFSET - i * 50, 10, 1);
					}
					for(int i = 0; i < trialInfo.size(); i++)
						g.drawString(Integer.toString(i+1), 120 + i * 50, HEIGHT + VERTOFFSET + 25);
					
					for(int i = 0; i < trialInfo.size(); i++)
					{
						g.fillRect(HORIZOFFSET + 15 + i * 50, 
								VERTOFFSET + (HEIGHT - 5 * trialInfo.get(i)[3]/2), 
								25, 
								5 * trialInfo.get(i)[3]/2);
						g.drawString(Integer.toString(trialInfo.get(i)[3]), HORIZOFFSET + 15 + i * 50, VERTOFFSET + (HEIGHT - 5 * trialInfo.get(i)[3]/2) - 10);
					}
					break;
				}
			}
		}
	}
}
