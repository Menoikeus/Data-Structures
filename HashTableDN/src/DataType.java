
public class DataType {
	//data type stores name and chpa
	private String firstName;
	private double GPA;
	
	//constructor
	public DataType(String s, double d)
	{
		firstName = s;
		GPA = d;
	}
	
	//to string, to be called automatically
	public String toString()
	{
		return firstName + " " + GPA;
	}
}
<<<<<<< HEAD


/* FROM LISTNODEKMC
//delete method, specifically to delete table entry keys
	public void deleteTableEntryKey(String key)
	{
		//this clears out the cases where the first node's value is equal
		while(first != null && ((TableEntry)first.getValue()).getKey().equals(key))
			first = first.getNext();
		
		//now we go through and check the next value
		//if the next value is equal, then take the current node
		//and connect it to the 
		ListNode current = first;
		while(current != null && current.getNext() != null)
		{
			//if the next one is equal, connect
			if(((TableEntry)current.getNext().getValue()).getKey().equals(key))
				current.setNext(current.getNext().getNext());
			else
				current = current.getNext();
			//if not, go to the next node
		}
	}
*/
=======
>>>>>>> branch 'master' of https://github.com/Menoikeus/Data-Structures.git
