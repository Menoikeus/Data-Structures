
public class TableEntry {
	//key and data
	private String key;
	private DataType data;
	
	//constructor
	public TableEntry(String theKey, DataType theData)
	{
		key = theKey;
		data = theData;
	}
	
	public String getKey()
	{
		return key;
	}
	
	//to string, which calls data to string
	public String toString()
	{
		return key + ", " + data;
	}
}
