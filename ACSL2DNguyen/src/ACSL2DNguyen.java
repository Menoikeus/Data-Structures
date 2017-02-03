import java.util.ArrayList;

import javax.swing.JOptionPane;


public class ACSL2DNguyen {
	public static void main(String[] args)
	{
		while(true)
		{
			try
			{
				String d = JOptionPane.showInputDialog(null, "Number?").trim();
				ArrayList<String> output = new ArrayList<String>();
				
				int numDigits = Integer.parseInt(d.substring(0, 1));
				output.add(Integer.toString(Integer.parseInt(d.substring(1, numDigits+1))));
				
				String currentNum = "";
				for(int i = d.length()-1; i >= numDigits+1; i--)
				{
					currentNum += d.substring(i, i+1);
					if(output.get(output.size()-1).equals("") || Integer.parseInt(currentNum) > Integer.parseInt(output.get(output.size()-1)))
					{
						output.add(Integer.toString(Integer.parseInt(currentNum)));
						currentNum = "";
					}
				}
				
				for(String z : output)
					System.out.print(z + " ");
				System.out.println();
			}
			catch(Exception e)
			{
				
			}
		}
	}
}
