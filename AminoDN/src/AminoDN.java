import java.util.ArrayList;
import apcslib.Format;
import chn.util.*;

public class AminoDN {
	public static void main(String[] args)
	{
		FileInput translationFile = new FileInput("H:\\DNA\\codon_to_amino.txt");
		FileInput inFile = new FileInput("H:\\DNA\\rna_info.txt");
		FileOutput outFile = new FileOutput("H:\\DNA\\after_result_DN.txt");
		//FileInput translationFile = new FileInput("codon_to_amino.txt");
		//FileInput inFile = new FileInput("rna_info.txt");
		//FileOutput outFile = new FileOutput("after_result_DN.txt");
		
		//array list of the translations in the file
		ArrayList<String> translations = new ArrayList<String>(); 
		
		//line number of where the starts appear in the file
		int indexOfStarts = 0;
		
		while(translationFile.hasMoreLines())
		{
			String formattedS = translationFile.readLine().replace("\t", "*");
			translations.add(formattedS); //add formatted strings to translations
		}
		
		//find where the start and stop translation lines are
		for(int i = 0; i < translations.size(); i++)
		{
			String[] vals = extractCodons(translations.get(i)); 
			if(vals[0].equals("START"))
				indexOfStarts = i;
		}
		
		//result from translations
		ArrayList<String> translatedLines = new ArrayList<String>(); 
		while(inFile.hasMoreLines())
		{
			String s = inFile.readLine(); //read in line
			
			boolean stillInLine = true; //since there can be multiple chains
										//in one line, stay in the line until
										//we've reached the end
			int currentLineStart = 0;   //once a chain is read, we're going to 
										//cut off that part and read what's after
			String aminoAcids = "";		//this is what's getting added to 
										//the translationedlines arraylist
			while(stillInLine)
			{
				s = s.substring(currentLineStart);	//cut the line from where 
													//we left off 
													//(0 if we're just starting)
				int lineStart = -1;		//this'll be the index of where to start
										//reading (when we see the first START)
				for(String codon : extractCodons(translations.get(indexOfStarts)))
				{
					if(s.indexOf(codon) != -1 && lineStart == -1)
						lineStart = s.indexOf(codon);
				}
				
				if(lineStart != -1)	//if we actual start
				{
					boolean stillReading = true;	//keep reading codons until we 
													//get to end of line 
													//or a STOP codon
					int codonCount = 1;				//count of how many codons 
													//have been read
					String acidAddition = "";		//the amino chain we're going 
													//to be adding to the 
													//translated line
					while(stillReading)
					{
						//get the next codon in the chain
						String codon = s.substring(lineStart + 3 * codonCount,
											lineStart + 3 * codonCount + 3); 
						
						String val = "";	//value will store the translated value
											//traverse from the back, so that we 
											//can see if it stops first
						for(int z = translations.size()-1; z >= 0; z--)		
							if(translations.get(z).indexOf(codon) != -1 
								&& val == "" && z != indexOfStarts)		
								val = extractCodons(translations.get(z))[0];	
						//if the codon exists in one of the 
						//translation lines, set 'val' to the value before the *. 
						//Also make sure not to read anything as a 'START'
						
						if(val.equals("STOP"))
							stillReading = false; //if it's a stop, 
												  //get out of reading
						else
							acidAddition += val;  //else, add the codon 
												  //to the current chain
						
						//increment the number of codons we've counted, 
						//so we can count the next one
						codonCount++;			
						if(!(lineStart + 3 * codonCount + 3 <= s.length()))
							stillReading = false; //if we're out of bounds, stop
					}
					
					//if the acid addition was empty, we've got a 
					//START-STOP or START-END
					if(acidAddition.equals(""))		
						aminoAcids += "NULL";
					else							//else add the current chain 
													//to the current line
						aminoAcids += acidAddition;
					aminoAcids += " ";				//space after
					
					//we're going to cut out the chain we just 
					//read and everything before it
					//this is the index of the next codon
					currentLineStart = lineStart + 3 * codonCount;	
				}
				else
					stillInLine = false;	//if we didn't find any START, 
											//end the line read
				if(currentLineStart + 2 > s.length())
					stillInLine = false;
			}
			translatedLines.add(aminoAcids); //add lines 
		}
		
		//write translated acids to file
		for(int j = 0; j < translatedLines.size(); j++)
		{
			outFile.println(Format.right(Integer.toString(j+1), 2) 
					+ ": " + translatedLines.get(j));
			System.out.println(Format.right(Integer.toString(j+1), 2)  
					+ ": " + translatedLines.get(j));
		}
		
		outFile.close();
	}
	
	public static String[] extractCodons(String translations)
	{
		//splits the lines and finds the values
		//returns values in an array, so that we can later get the translation
		return translations.split("[*,]"); 
	}
}

