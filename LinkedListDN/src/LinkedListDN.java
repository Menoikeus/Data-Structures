import javax.swing.JOptionPane;

public class LinkedListDN {
	public static void main(String[] args)
	{
		// new list
		LinkedListKMC list = new LinkedListKMC();
		
		//keep running until the user stops it
		boolean done = false;
		while(!done)
		{
			//type in a number to choose your command
			int choice = getIntInput("Type in the number the corresponds "
							+ "to the operation you wish "
							+ "to perform and press OK\n\n"
							+ "1 : Add at back\n"
							+ "2 : Add at front\n"
							+ "3 : Print\n"
							+ "4 : Delete\n"
							+ "5 : Get size\n"
							+ "6 : Insert at position\n"
							+ "7 : Insert before last occurrence of object\n"
							+ "8 : Get last value\n"
							+ "9 : Clear list\n"
							+ "10 : Find value\n"
							+ "11 : Remove duplicates\n"
							+ "12 : Print backwards\n"
							+ "13 : Exit\n\n"
							+ list.print());
			
			//do action based on input
			switch(choice)
			{
				case 1:	
					list.addAtBack(getIntInput(
						"What value do you wish to add at the back?"));
					break;
				case 2:
					list.addAtFront(getIntInput(
							"What value do you wish to add at the front?"));
					break;
				case 3:
					break;
				case 4:
					list.delete(getIntInput(
							"What value do you wish to delete?"));
					break;
				case 5:
					JOptionPane.showMessageDialog(null, "Size: " + list.size());
					break;
				case 6:
					list.insertPos(
						getIntInput("What value do you wish to add?"),
						getIntInput("At what location do you wish to add it?"));
					break;
				case 7:
					list.insert(
						getIntInput("What value do you wish to add?"),
						getIntInput("Before what value do you wish to add it?"));
					break;
				case 8:
					JOptionPane.showMessageDialog(null, "Last: " 
							+ list.getLast());
					break;
				case 9:
					list.clear();
					break;
				case 10:
					JOptionPane.showMessageDialog(null, 
							list.find(getIntInput(
								"What value do you wish to find?")) 
								? "Value Found" : "Value not found");
					break;
				case 11:
					list.removeDuplicates();
					break;
				case 12: 
					String o = list.printBackwards(list.getFirstNode());
					if(!o.isEmpty())
						JOptionPane.showMessageDialog(null, o);
					break;
				case 13: 
					done = true;
					break;
				default:
					JOptionPane.showMessageDialog(null, 
							"Doesn't seem to be a valid choice...");
			}
			JOptionPane.showMessageDialog(null, list.print());
		}
	}
	
	//method to validate input
	public static int getIntInput(String message)
	{
		int number = 0;
		boolean valid = false;
		
		//keep asking for an answer until we don't get an error,
		//ie the answer is an integer
		while(!valid)
		{
			String input = JOptionPane.showInputDialog(null, message);
			try
			{
				number = Integer.parseInt(input);
				valid = true; 
				//we won't get to 'valid = true' if it's invalid input
				//because an error will redirect directly to the catch
			}
			catch(NumberFormatException e) //catch errors
			{
				JOptionPane.showMessageDialog(null, 
						"Oi mate. I need a number.");
			}		
		}
		
		//return the validated value
		return number;
	}
}
