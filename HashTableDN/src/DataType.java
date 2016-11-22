
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
