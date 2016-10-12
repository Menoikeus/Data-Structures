import java.util.ArrayList;
import apcslib.Format;
import chn.util.*;

public class AminoDN {
	public static void main(String[] args)
	{
		FileInput translationFile = new FileInput("H:\\DNA\\codon_to_amino.txt");
		FileInput inFile = new FileInput("H:\\DNA\\test.txt");
		FileOutput outFile = new FileOutput("H:\\DNA\\AfterDN.txt");
		
		ArrayList<ArrayList<String>> translations = new ArrayList<ArrayList<String>>();
		
		int count = 0;
		while(translationFile.hasMoreLines())
		{
			String s = translationFile.readLine();
			String[] values = s.split("[\t,]+");
			
			ArrayList<String> valuesInLine = new ArrayList<String>();
			for(int i = 0; i < values.length; i++)
				valuesInLine.add(values[i]);
			translations.add(valuesInLine);	
				
			count++;
		}
		
		for(int i = 0; i < translations.size(); i++)
		{
			for(int j = 0; j < translations.get(i).size(); j++)
				System.out.print(translations.get(i).get(j) + " ");
			System.out.println();
		}
		
		while(inFile.hasMoreLines())
		{
			String s = inFile.readLine();
			
			int start = s.indexOf(translations.get(translations.size()-2).get(0));
			int codonCount = 0;
			String aminoSequence;
			boolean isReading = true;
			while(isReading)
			{
				String codon = s.substring(codonCount * 3 + start, codonCount * 3 + start + 3);
			}
		}
	}
}
