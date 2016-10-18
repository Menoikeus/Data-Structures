import java.util.ArrayList;
import apcslib.Format;
import chn.util.*;

public class AminoDN {
	public static void main(String[] args)
	{
		FileInput translationFile = new FileInput("H:\\DNA\\codon_to_amino.txt");
		FileInput inFile = new FileInput("H:\\DNA\\test.txt");
		FileOutput outFile = new FileOutput("H:\\DNA\\AfterDN.txt");
		
		ArrayList<String> translations = new ArrayList<String>();
		
		int count = 0;
		while(translationFile.hasMoreLines())
		{
			String s = translationFile.readLine();
			String formattedS = s.replace("\t", "*");
			
			translations.add(formattedS);
				
			count++;
		}
		
		for(int i = 0; i < translations.size(); i++)
		{
			System.out.println(translations.get(i));
		}
		
		/*
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
		}*/
	}
}
