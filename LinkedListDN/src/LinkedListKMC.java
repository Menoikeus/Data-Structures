import javax.swing.JOptionPane;



public class LinkedListKMC
{
 	private ListNode first;
 	
 	public LinkedListKMC()
 	{
 		first = null;	// first is initially the external pointer
 	}
 	
 	public LinkedListKMC(ListNode firstNode)
 	{
 		first = firstNode;			
 	}
 	
 	//get first element in list (first node's value)
 	public Object getFirst()
 	{
  		return first == null ? null : first.getValue();		
 		// call to ListNode method
 		// Be careful - no guard against 
 		// first = null => fix this!
 	}
 	
 	//return first node
 	public ListNode getFirstNode()
 	{
 		return first;
 	}	

 	//add at front of list
 	public void addAtFront (Object value)
 	{
 		first = new ListNode(value, first); 	
 	}
 	
 	//print list
 	public String print()
 	{
 		if (first == null)
 			return "The list is empty.";
 		else
 		{
 			String output = "";
 			ListNode current = first;
 			while (current != null)
 			{
 				output += current.getValue() + " ";
 				current = current.getNext();
 			}
 			return "Current list: " + output;
 		}
 	}
 	
 	//adds at back
 	public void addAtBack(Object value)
 	{
 		if (first == null)
 			first = new ListNode(value);
 		else
 		{
 			ListNode current = first;
 			while (current.getNext() != null)
 			{
 				current = current.getNext();
 			}
 			current.setNext(new ListNode(value));
 		}
 	}
 	
 	//deletes all occurrences of $value
 	public void delete(Object value)
 	{
 		//this clears out the cases where the first node's value is equal
		while(first != null && first.getValue().equals(value))
			first = first.getNext();
		
		//now we go through and check the next value
		//if the next value is equal, then take the current node
		//and connect it to the 
		ListNode current = first;
		while(current != null && current.getNext() != null)
		{
			//if the next one is equal, connect
			if(current.getNext().getValue().equals(value))
				current.setNext(current.getNext().getNext());
			else
				current = current.getNext();
			//if not, go to the next node
		}
 	}
 	
 	//gets size. go through and count up until null
 	public int size()
 	{
 		int count = 0;
 		ListNode current = first;
 		while(current != null)
 		{
 			count++;
 			current = current.getNext();
 		}
 		return count;
 	}
 	
 	//insert a listnode with value at position pos
 	public void insertPos(Object value, int pos)
 	{
 		if(pos >= 1 && pos <= size() + 1)
	 		if(pos == 1) //put at beginning if pos == 1
	 			first = new ListNode(value, first);
	 		else
	 		{
	 			//go through until either we're at the listnode before
	 			//the position or at the end of thelist
		 		int currentPos = 1;
		 		ListNode current = first;
		 		while(current != null && currentPos + 1 < pos )
		 		{
		 			current = current.getNext();
		 			currentPos++;
		 		}
		 		if(currentPos + 1 == pos)
		 			current.setNext(new ListNode(value, current.getNext()));
		 		//connect the current with a new 
		 		//one that points to the rest
	 		}
 	}
 	
 	public void insert(Object value, Object search_value)
 	{
 		//insert before value
 		int lastPos = -1, count = 1;
 		ListNode current = first;
 		while(current != null)
 		{
 			//go through the whole list, and set the last position
 			//whenever you see the value, since we want the last occurrence
 			if(current.getValue().equals(search_value))
 				lastPos = count;
 			count++;
 			current = current.getNext();
 		}
 		if(lastPos != -1)
 		{
 			//if we ever go for
 			insertPos(value, lastPos);
 			JOptionPane.showMessageDialog(null, "Insertion made!");
 		}
 		else
 			JOptionPane.showMessageDialog(null, "Insertion failed, "
 											+ "search value not found!");
 			
 	}
 	
 	public Object getLast()
 	{
 		ListNode current = first;
 		while(current != null && current.getNext() != null)
 			current = current.getNext();
 		return current == null ? null : current.getValue();
 	}
 	
 	//clears list
 	public void clear()
 	{
 		first = null;
 	}
 	
 	//finds whether or not a value exists in the list
 	public boolean find(Object value)
 	{
 		boolean isIn = false;
 		ListNode current = first;
 		while(current != null)
 		{
 			//if we ever get the same value as value, it's in
 			if(current.getValue().equals(value))
 				isIn = true;
 			current = current.getNext();
 		}
		return isIn;
 	}
 	
 	//removes all duplicates
 	public void removeDuplicates()
 	{
 		ListNode current = first;
 		while(current != null)
 		{
 			//so here, we go through each current value.
 			//we make a new linked list whose first points to 
 			//the listnode after current
 			//we then delete current's value from the new linked list
 			//then we append that list to current
 			LinkedListKMC append = new LinkedListKMC(current.getNext());
 			append.delete(current.getValue());
 			current.setNext(append.getFirstNode());
 			current = current.getNext();
 		}
 	}
 	
 	//recursive print backwards
 	public String printBackwards(ListNode current)
 	{
 		if(current != null) //keep adding the next value in front (until null)
 			return printBackwards(current.getNext()) + " " + current.getValue();
 		else
 			return "";
 		
 	}
}