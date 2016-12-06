import javax.swing.JOptionPane;


public class ACSL1DNguyen {
	public static void main(String[] args)
	{
		while(true)
		{
			try
			{
				String stringOfCards = JOptionPane.showInputDialog(null, "CARDS (IN FORMAT \"AX BY CZ...\"), WHERE FIRST VALUE IS LEAD CARD: ");
				String[] cards = stringOfCards.split(" ");
				final String cardValues = "A23456789TJQK";
				
				String cardSuit = cards[0].substring(1, 2);
				String cardValue = cards[0].substring(0, 1);
				
				if((cards[1] + cards[2] + cards[3] + cards[4] + cards[5]).indexOf(cardSuit) != -1)
				{
					int lowest = cardValues.length(); //lowest value greater than card's value
					int trueLowest = cardValues.length(); //the actual lowest value
													  //technically, these are just the locations of the lowest values
													  //as they appear in the variable cardValues
					for(int i = 1; i < cards.length; i++)
					{
						if(cards[i].indexOf(cardSuit) != -1)
						{
							String value = cards[i].substring(0, 1);
							if(cardValues.indexOf(value) < lowest && cardValues.indexOf(value) > cardValues.indexOf(cardValue))
							{
								lowest = cardValues.indexOf(value);
							}
							if(cardValues.indexOf(value) < trueLowest)
							{
								trueLowest = cardValues.indexOf(value);
							}
						}
					}
					
					if(lowest >= cardValues.length())
					{
						System.out.println(cardValues.substring(trueLowest, trueLowest+1) + cardSuit);
					}
					else
					{
						System.out.println(cardValues.substring(lowest, lowest+1) + cardSuit);
					}
				}
				else
				{
					int index = -1;
					int trueLowest = cardValues.length();
					for(int i = 1; i < cards.length; i++)
					{
						String value = cards[i].substring(0, 1);
						if(cardValues.indexOf(value) < trueLowest)
						{
							trueLowest = cardValues.indexOf(value);
							index = i;
						}
					}
					System.out.println(cards[index]);
				}
			}catch(Exception e)
			{
			}
		}
	}
}
