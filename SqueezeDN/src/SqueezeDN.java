import apcslib.Format;
import chn.util.*;

public class SqueezeDN {
	public static final int TAB_LENGTH = 4;
	
	public static void main(String[] args)
	{
		FileInput inFile = new FileInput("Before.txt");
		FileOutput outFile = new FileOutput("AfterDN.txt");
		
		while(inFile.hasMoreLines())
		{
			String s = inFile.readLine();
			
			int spaceCount = 0, count = 0;
			boolean empty = true;
			while(empty && count < s.length())
			{
				if(s.substring(count, count+1).equals(" "))
					spaceCount++;
				else if(s.substring(count, count+1).equals("\t"))
					spaceCount += TAB_LENGTH - spaceCount % TAB_LENGTH;
				else
					empty = false;
				count++;
			}		
			if(s.trim().length() == 0)
				spaceCount = 0;
			
			outFile.println(Format.right(Integer.toString(spaceCount), 2) 
							+ "\t" 
							+ (s.length() == 0 ? "" : s.substring(count-1)));
		}
		inFile.close();
		outFile.close();
	}
}