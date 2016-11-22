public class HashTable {
	private int size;
	private int count;
	private LinkedListKMC[] T;
	
	//constructor
	public HashTable(int tableSize)
	{
		//creates an array of linked list with size tableSize and initializing
		size = tableSize;
		T = new LinkedListKMC[size];
		for(int i = 0; i < T.length; i++)
			T[i] = new LinkedListKMC();
	}
	
	public int hash(Object key)
	{
		//returns the hash modded by size, which allows us to sort
		return Math.abs(key.hashCode()) % size;
	}
	
	public void delete(String key)
	{
		//goes through each list and deletes entries with a certain key
		for(LinkedListKMC list : T)
			list.deleteTableEntryKey(key);
	}
	
	public void insert(String key, DataType data)
	{
		//deletes, then inserts a new table entry at the correct list
		delete(key);
		T[hash(key)].addAtBack(new TableEntry(key, data));
	}
	
	public String printTable()
	{
		//prints all the lists
		String output = "";
		for(int i = 0; i < T.length; i++)
		{
			if(T[i] != null)
				output += "List " + i + ": " + T[i].print() + "\n";
		}
		return output;
	}
}
