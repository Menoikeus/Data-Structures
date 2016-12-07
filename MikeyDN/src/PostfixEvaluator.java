import java.util.ArrayList;
import java.util.Arrays;

public class PostfixEvaluator {
	public static int Evaluate(String s)
	{
		Stack stack = new ArrayStack();
		String[] values = s.split(" ");
		String operations = "+-*/";
		
		ArrayList<String> outputValues = new ArrayList<String>();
		boolean first = true;
		
		for(int i = 0; i < values.length; i++)
		{
			String ch = values[i];
			try
			{
				stack.push(Integer.parseInt(ch));
			}
			catch(Exception e)
			{
				int latest = (int)stack.pop();
				int secondLatest = (int)stack.pop();
				if(ch.equals("+"))
					stack.push(secondLatest + latest);
				if(ch.equals("-"))
					stack.push(secondLatest - latest);
				if(ch.equals("*"))
					stack.push(secondLatest * latest);
				if(ch.equals("/"))
					stack.push(secondLatest / latest);
				
				if(first)
				{
					outputValues.add(secondLatest + ch + latest);
					/*
					outputValues.add(secondLatest + "");
					outputValues.add(ch + "");
					outputValues.add(latest + "");
					*/
					first = false;
				}
				else
				{
					//if(operations.indexOf(outputValues.get(outputValues.size()-2)) != -1)
					{
						outputValues.add(ch + " " + latest);
					}
				}
			}
		}
		
		String whole = "";
		ArrayList<String> alValues = new ArrayList<String>(Arrays.asList(values));
		for(String z : alValues)
			System.out.println(z);
		
		int i = alValues.size() - 1;
		while(i > 0)
		{
			if(operations.indexOf(alValues.get(i)) != -1)
			{
				if(whole.isEmpty())
				{
					System.out.println(i + " " + alValues.size());
					whole = alValues.get(i-2) + " " + alValues.get(i) + " " + alValues.get(i-1);
					alValues.remove(i-2);
					alValues.remove(i-2);
					alValues.remove(i-2);
					i -= 2;
				}
				else
				{
					whole += AddParentheses(whole) + " " + alValues.get(i) + " " + alValues.get(i-1);
					alValues.remove(i-2);
					alValues.remove(i-2);
					i -= 1;
				}
			}
			i--;
			System.out.println(whole);
		}
		
		String output = "";
		for(String os : outputValues)
		{
			output += os;
		}
		
		System.out.println(whole);
		return (int)stack.pop();
	}
	
	private static String AddParentheses(String s)
	{
		return "(" + s + ")";
	}
}
