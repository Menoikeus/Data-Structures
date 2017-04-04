import javax.swing.JOptionPane;


public class ACSL4DNguyen {
	public static void main(String[] args)
	{
		int[][] city = new int[6][6];
		
		/*
		int[] topRowOfClues = new int[4];
		int[] bottomRowOfClues = new int[4];
		int[] leftRowOfClues = new int[4];
		int[] rightRowOfClues = new int[4];
		*/
		
		String[] setupInputs = JOptionPane.showInputDialog("INPUT: ").split(", ");
		
		for(int i = 0; i < setupInputs[0].length(); i++)
		{
			city[0][i+1] = Integer.parseInt(setupInputs[0].substring(i, i+1));
		}
		for(int i = 0; i < setupInputs[5].length(); i++)
		{
			city[5][i+1] = Integer.parseInt(setupInputs[5].substring(i, i+1));
		}
		
		for(int i = 1; i <= 4; i++)
		{
			
			city[i][0] = Integer.parseInt(setupInputs[i].substring(0,1));
			city[i][5] = Integer.parseInt(setupInputs[i].substring(
					setupInputs[i].length()-1,
					setupInputs[i].length()));
			
			if(setupInputs[i].length() == 6)
				for(int z = 1; z <= 4; z++)
					city[i][z] = Integer.parseInt(setupInputs[i].substring(z, z+1));
		}
		
		for(int i = 0; i < city.length; i++)
		{
			for(int j = 0; j < city[i].length; j++)
			{
				System.out.print(city[i][j]);
			}
			System.out.println();
		}
	}
}
