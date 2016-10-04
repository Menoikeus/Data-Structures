import chn.util.*;

public class SqueezeDN {
	public static final int TAB_LENGTH = 8;
	
	public static void main(String[] args)
	{
		FileInput inFile = new FileInput("testing.txt");
		FileOutput outFile = new FileOutput("bingosqueezed.txt");
		
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
			
			outFile.println(spaceCount + "\t" + s.substring(count-1));
		}
		inFile.close();
		outFile.close();
	}
}
