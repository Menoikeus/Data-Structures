// KMC
// Data Structures in Java

// Node class - this class is used on the AB exam


public class ListNode
{

 	private Object value;
  	private ListNode next;

  
  	// Constructor to make a new node with both data and pointer
  	
  	public ListNode(Object initValue, ListNode initNext)
  	{
    	value = initValue;
    	next = initNext;
  	}

  
  	// Constructor to make a new node with a null pointer  
  
  	public ListNode(Object initValue)
  	{
    	this(initValue, null);
  	}

  
  	public void setValue(Object theNewValue)
  	{
    	value = theNewValue;
  	}

  
  	public void setNext(ListNode theNewNext)
  	{
   	 	next = theNewNext;
  	}
  	
  	public Object getValue()
  	{
    	return value;
  	}

  
  	public ListNode getNext()
  	{
    	return next;
  	}
}