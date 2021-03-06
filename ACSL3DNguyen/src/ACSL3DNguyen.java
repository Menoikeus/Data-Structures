import javax.swing.JOptionPane;

public class ACSL3DNguyen {
	public static void main(String[] args)
	{
		while(true)
		{
			try
			{
				boolean[][] board = new boolean[8][8];
				String input = JOptionPane.showInputDialog(null, "INPUT: ");
				String[] vals = input.split(" ");
				
				int numSets = Integer.parseInt(vals[0]);
				for(int i = 1; i <= numSets; i++)
				{
					int row = Integer.parseInt(vals[i].substring(0, 1)) - 1; //-1
					for(int j = 1; j < vals[i].length(); j++)
						board[row][Integer.parseInt(vals[i].substring(j, j+1)) - 1] = true; //-1
				}
				
				for(int i = numSets + 2; 
						i <= numSets + 1 + Integer.parseInt(vals[numSets + 1]); i++)
					press(board, 
						Integer.parseInt(vals[i].substring(0, 1))-1, 
						Integer.parseInt(vals[i].substring(1, 2))-1);
				
				int counter = 0;
				for(int i = board.length - 1; i >= 0; i--)
					for(int j = 0; j < board[i].length; j++)
						if(board[i][j])
							counter++;
				
				System.out.println(counter);
			}
			catch(Exception e)
			{
				
			}
		}
	}
	
	public static void press(boolean[][] b, int row, int col)
	{
		for(int i = 0; i < b.length; i++)
			for(int j = 0; j < b[i].length; j++)
				if(Math.abs(i - row) + Math.abs(j - col) <= 2)
					b[i][j] = b[i][j] ? false : true;
	}
}
