import javax.swing.JOptionPane;


public class HashTableDN {
	public static void main(String[] args)
	{
		//gets number of buckets and creates a hash table
		int h;
		while((h = getIntInput("How many buckets?")) <= 0);
		HashTable hT = new HashTable(h);
		
		//while not done yet
		boolean done = false;
		while(!done)
		{
			//get choice
			int choice = getIntInput("What would you like to do?\n\n"
									+ "1. Add a student\n"
									+ "2. Delete all entries with "
										+ "a certain last name\n"
									+ "3. Show the current table\n"
									+ "4. Exit");
			switch(choice)
			{
			case 1: //insert a new list
				hT.insert(getStringInput("Last name?"), 
					new DataType(getStringInput("First name?"), 
						Double.parseDouble(
								JOptionPane.showInputDialog("GPA?"))));
				break;
			case 2: //delete table entry with key
				hT.delete(getStringInput(
						"What last name would you like to remove?"));
				break;
			case 3: //print table
				JOptionPane.showMessageDialog(null, hT.printTable());
				break;
			case 4:
				done = true;
				break;
			default:
				JOptionPane.showMessageDialog(null, 
						"Give me a real choice, boyo.");
				break;
			}
		}
	}
	
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
		return number;
	}
	
	//gets string input with message
	public static String getStringInput(String message)
	{
		return JOptionPane.showInputDialog(null, message);
	}
}
