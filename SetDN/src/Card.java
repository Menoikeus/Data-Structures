
public class Card {
	private int number;
	// 1 , 2 , 3
	private int color;
	// 0 = red, 1 = green, 2 = blue
	private int shape;
	//0 = rectangle, 1 = triangle, 2 = circle
	private int shade;
	//0 = nothing, 1 = shade, 3 
	
	private String id;
	
	public Card(int n, int c, int f, int s)
	{
		number = n;
		color = c;
		shape = f;
		shade = s;
		
		id = "" + number + color + shape + shade;
	}
	
	public String getID()
	{
		return id;
	}
	
	public int getNumber()
	{
		return number;
	}
	public int getColor()
	{
		return color;
	}
	public int getShape()
	{
		return shape;
	}
	public int getShade()
	{
		return shade;
	}
}
