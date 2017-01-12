import java.util.ArrayList;
import java.util.Arrays;

public class PostfixEvaluator {
	public static void Evaluate(String s)
	{
		Stack stack = new ArrayStack(); // makes a stack
		String[] values = s.split(" "); // splits input into array
		String operations = "+-*/";
		
		int numNumbers = 0, numOperations = 0;
		for(String aString : values)
		{
			if(operations.indexOf(aString) != -1)
				numOperations++;
			else
				numNumbers++;
		}
		
		boolean error = false, divideByZero = false; // two types of errors: input and divide by zero
		if(numNumbers == numOperations + 1)
		{
			ArrayList<String> outputValues = new ArrayList<String>();
			for(int i = 0; i < values.length && !error; i++)
			{
				String ch = values[i];
				try
				{
					stack.push(Double.parseDouble(ch));
					outputValues.add(ch);
				}
				catch(Exception e)
				{
					if(!stack.isEmpty())
					{
						double latest = ((Double)stack.pop()).doubleValue();
						if(!stack.isEmpty())
						{
							double secondLatest = ((Double)stack.pop()).doubleValue();
							if(ch.equals("+"))
								stack.push(secondLatest + latest);
							if(ch.equals("-"))
								stack.push(secondLatest - latest);
							if(ch.equals("*"))
								stack.push(secondLatest * latest);
							if(ch.equals("/"))
							{
								if(Math.abs(latest) >= 0.0000001)
									stack.push(secondLatest / latest);
								else
									divideByZero = true;
							}
							
							if(!error && !divideByZero)
							{
								String secondValue = outputValues.remove(outputValues.size() - 1);
								String firstValue = outputValues.remove(outputValues.size() - 1);
								outputValues.add("(" + firstValue + " " + ch + " " + secondValue + ")");
							}
						}
						else
							error = true;
					}
					else
						error = true;
				}
			}
			if(!error && !divideByZero)
				System.out.format("%-36s%-36s%12.3f%n", s, outputValues.get(0).replace("(", "").replace(")", ""), ((Double)stack.pop()).doubleValue());
			else if(divideByZero)
				System.out.format("%-36s%-36s%12s%n", s, "UNDEFINED", "UNDEFINED");
			else if(error)
				System.out.format("%-36s%-36s%12s%n", s, "INVALID", "INVALID");
		}
		else
			System.out.format("%-36s%-36s%12s%n", s, "INVALID", "INVALID");
		
		
		
		/*
		String whole = "";
		ArrayList<String> alValues = new ArrayList<String>(Arrays.asList(values));
		for(String s2 : alValues)
		{
			System.out.println("a:" + s2);
		}
		//for(String z : alValues)
			//System.out.println(z);
		
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
					System.out.println(whole);
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
			//System.out.println(whole);
		}
		
		String output = "";
		for(String os : outputValues)
		{
			output += os;
		}
		
		System.out.println(whole);*/
	}
}

/*
 * if(first)
				{
					/*outputValues.add(secondLatest + ch + latest);
					/*
					outputValues.add(secondLatest + "");
					outputValues.add(ch + "");
					outputValues.add(latest + "");
					
					outputString = secondLatest + " " + ch + " " + latest;
					first = false;
				}
				else
				{
					//if(operations.indexOf(outputValues.get(outputValues.size()-2)) != -1)
					{
						outputString = AddParentheses(outputString) + " " + ch + " " + secondLatest; 
						//outputValues.add(ch + " " + latest);
					}
				}
				*/
