import javax.swing.JOptionPane;

//DISCLAIMER: After extensive testing, this program seems to work
//			  However, I cannot comprehend why it works all the time
//			  Proceed at your own risk

public class ACSL4DNguyen {
	public static void main(String[] args)
	{
		int[][] city = new int[6][6];
		
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
		
		//starting fill
		for(int i = 1; i <= 4; i++)
		{
			if(city[0][i] == 4)
			{
				for(int z = 1; z <= 4; z++)
				{
					city[z][i] = z;
				}
			}
		}
		for(int i = 1; i <= 4; i++)
		{
			if(city[5][i] == 4)
			{
				for(int z = 1; z <= 4; z++)
				{
					city[4-z+1][i] = z;
				}
			}
		}
		
		//columns
		for(int i = 1; i <= 4; i++)
		{
			if(city[i][0] == 4)
			{
				for(int z = 1; z <= 4; z++)
				{
					city[i][z] = z;
				}
			}
		}
		for(int i = 1; i <= 4; i++)
		{
			if(city[i][5] == 4)
			{
				for(int z = 1; z <= 4; z++)
				{
					city[i][4-z+1] = z;
				}
			}
		}
		
		// fillup stuff, forgot what it was really for
		for(int i = 1; i <= 4; i++)
		{
			boolean[] used = new boolean[]{false, false, false, false, false};
			int currentEmpty = -1;
			int numEmpty = 0;
			for(int j = 1; j <= 4; j++)
			{
				if(city[i][j] != 0)
					used[city[i][j]] = true;
				else
				{
					numEmpty++;
					currentEmpty = j;
				}
			}
			if(numEmpty == 1)
			{
				int unusedValue = -1;
				for(int h = 1; h <= 4 && unusedValue == -1; h++)
				{
					if(!used[h])
					{
						unusedValue = h;
					}
				}
				city[i][currentEmpty] = unusedValue;
			}
		}
		for(int j = 1; j <= 4; j++)
		{
			boolean[] used = new boolean[]{false, false, false, false, false};
			int currentEmpty = -1;
			int numEmpty = 0;
			for(int i = 1; i <= 4; i++)
			{
				if(city[i][j] != 0)
					used[city[i][j]] = true;
				else
				{
					numEmpty++;
					currentEmpty = i;
				}
			}
			if(numEmpty == 1)
			{
				int unusedValue = -1;
				for(int h = 1; h <= 4 && unusedValue == -1; h++)
				{
					if(!used[h])
					{
						unusedValue = h;
					}
				}
				city[currentEmpty][j] = unusedValue;
			}
		}
		
		// 1's
		for(int i = 1; i <= 4; i++)
		{
			if(city[0][i] == 1)
				city[1][i] = 4;
			if(city[5][i] == 1)
				city[4][i] = 4;
			if(city[i][0] == 1)
				city[i][1] = 4;
			if(city[i][5] == 1)
				city[i][4] = 4;
		}
		
		int[][] cloned = city.clone();

		boolean utterFailure = false;
		for(int i = 1; i <= 4 && !utterFailure; i++)
		{
				for(int j = 1; j <= 4 && !utterFailure; j++)
				{
					if(city[i][j] == 0)
					{
						int num = 1;
						boolean failed = false;
						do
						{
							failed = false;
							for(int x = 1; x <= 4 && !failed; x++)
							{
								if(city[i][x] == num)
									failed = true;
								if(city[x][j] == num)
									failed = true;
							}
							if(failed)
							{
								if(num == 4)
									utterFailure = true;
								else
									num++;
							}
						}while(failed && !utterFailure);
						if(!utterFailure)
							city[i][j] = num;
					}
				}
		}
		
		if(utterFailure)
		{
			city = cloned.clone();
			boolean done = false;
			
			int offset = 1;
			while(!done)
			{
				utterFailure = false;
				for(int i = 1; i <= 4 && !utterFailure; i++)
				{
						for(int j = 1; j <= 4 && !utterFailure; j++)
						{
							if(city[i][j] == 0)
							{
								int num = 1;
								boolean failed = false;
								do
								{
									failed = false;
									for(int x = 1; x <= 4 && !failed; x++)
									{
										if(city[i][x] == num)
											failed = true;
										if(city[x][j] == num)
											failed = true;
									}
									if(failed)
									{
										if(num == 4)
											utterFailure = true;
										else
											num++;
									}
									else if(offset > 0)
									{
										failed = true;
										offset--;
									}
								}while(failed && !utterFailure);
								if(!utterFailure)
									city[i][j] = num;
							}
						}
				}
				
				if(!utterFailure)
					done = true;
				else
					offset++;
			}
		}
		
		/*
		System.out.println("\n\n");
		for(int i = 0; i < city.length; i++)
		{
			for(int j = 0; j < city[i].length; j++)
				System.out.print(city[i][j]);
			System.out.println();
		}*/
		
		for(;;)
		{
			String s = JOptionPane.showInputDialog(null, "INPUT: ");
			System.out.println(city[Integer.parseInt(s.substring(0,1))][Integer.parseInt(s.substring(1,2))]);
		}
	}
}

//	
//2241, 232141, 22, 13, 32, 2214
//3321, 412341, 22, 13, 23, 2124
//2123, 234213, 22, 41, 13, 1232
//2213, 22, 22, 143214, 41, 2321
//3221, 41, 22, 14, 231422, 2313
