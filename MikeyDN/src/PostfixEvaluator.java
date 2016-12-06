import java.util.ArrayList;

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
					outputValues.add(latest + "");*/
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
		
		String output = "";
		for(String os : outputValues)
		{
			output += os;
		}
		
		System.out.println(output);
		return (int)stack.pop();
	}
}
