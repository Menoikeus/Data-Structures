import javax.swing.JOptionPane;

public class ACSL3DNguyen {
	public static void main(String[] args)
	{
		while(true)
		{
			boolean[][] board = new boolean[8][8];
			String input = JOptionPane.showInputDialog(null, "INPUT: ");
			String[] vals = input.split(" ");
			
			int numSets = Integer.parseInt(vals[0]);
			for(int i = 1; i <= numSets; i++)
			{
				int row = Integer.parseInt(vals[i].substring(0, 1)) - 1; //-1
				for(int j = 1; j < vals[i].length(); j++)
				{
					board[row][Integer.parseInt(vals[i].substring(j, j+1)) - 1] = true; //-1
				}
			}
			
			for(int i = numSets + 2; 
					i <= numSets + 1 + Integer.parseInt(vals[numSets + 1]); i++)
			{
				press(board, 
					Integer.parseInt(vals[i].substring(0, 1))-1, 
					Integer.parseInt(vals[i].substring(1, 2))-1);
			}
			
			int counter = 0;
			for(int i = board.length - 1; i >= 0; i--)
			{
				//System.out.print((i+1) + " ");
				for(int j = 0; j < board[i].length; j++)
				{
					//System.out.print(board[i][j] ? 1 : 0);
					if(board[i][j])
						counter++;
				}
				//System.out.println();
			}
			//System.out.println("  12345678");
			
			System.out.println(counter);
		}
	}
	
	public static void press(boolean[][] b, int row, int col)
	{
		for(int i = 0; i < b.length; i++)
			for(int j = 0; j < b[i].length; j++)
			{
				if(Math.pow(i - row, 2) + Math.pow(j - col, 2) <= 4)
					b[i][j] = b[i][j] ? false : true;
			}
		
	}
}
