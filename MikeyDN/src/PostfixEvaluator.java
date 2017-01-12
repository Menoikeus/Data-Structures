import java.util.ArrayList;
import java.util.Arrays;

public class PostfixEvaluator {
	public static void Evaluate(String s)
	{
		Stack stack = new ArrayStack(); // makes a stack
		String[] values = s.split(" "); // splits input into array
		String operations = "+-*/";
		
		int numNumbers = 0, numOperations = 0; //counts the number of operations
												// and numbers
		for(String aString : values)
		{
			if(operations.indexOf(aString) != -1)
				numOperations++;
			else
				numNumbers++;
		}
		
		boolean error = false, divideByZero = false; // two types of errors: input and divide by zero
		if(numNumbers == numOperations + 1) // if there's one more number than operations
		{
			//ArrayList<String> outputValues = new ArrayList<String>(); // arraylist for output
			Stack outputValues = new ArrayStack();
			
			int i = -1;
			while(++i < values.length && !error && !divideByZero) // start at 0 and go through the 
																// input array, until there's an error
			{
				String ch = values[i]; // get the ith value
				
				
				try
				{
					stack.push(Double.parseDouble(ch)); // cast value and push to stack
					outputValues.push(ch); // add the letter to output string arraylist
				}
				catch(Exception e)
				{
					if(!stack.isEmpty()) // if there's something in the stack
					{
						// get the top of the stack
						double latest = ((Double)stack.pop()).doubleValue();
						if(!stack.isEmpty()) // if after one push there's still something, do this
											// if not, we've got an error
						{
							double secondLatest = ((Double)stack.pop()).doubleValue();
							
							// do operation
							if(ch.equals("+"))
								stack.push(secondLatest + latest);
							if(ch.equals("-"))
								stack.push(secondLatest - latest);
							if(ch.equals("*"))
								stack.push(secondLatest * latest);
							if(ch.equals("/"))
							{
								//if you're dividing by zero
								if(Math.abs(latest) >= 0.0000001)
									stack.push(secondLatest / latest);
								else
									divideByZero = true;
							}
							
							if(!error && !divideByZero) // if we haven't errored, then add to output
							{
								// take last two
								String secondValue = (String)outputValues.pop();
								String firstValue = (String)outputValues.pop();
								
								// parentheses and then add back
								outputValues.push("(" + firstValue + " " + ch + " " + secondValue + ")");
							}
						}
						else
							error = true;
					}
					else
						error = true;
				}
			}
			// if we haven't errored, print result
			if(!error && !divideByZero)
			{
				String out = ((String)outputValues.peekTop());
				System.out.format("%-36s%-63s%12.3f%n", s, 
						out.indexOf("(") != -1 ? out.substring(1, out.length() - 1) : out, 
						((Double)stack.pop()).doubleValue());
				// if there are parentheses, then remove the end ones. If not, just print
			}
			else if(divideByZero) // else if / by 0, say so
				System.out.format("%-36s%-63s%12s%n", s, "UNDEFINED", "UNDEFINED");
			else if(error) // if error, error
				System.out.format("%-36s%-63s%12s%n", s, "INVALID", "INVALID");
		}
		else // say error if the beginning input was bad (not enough operators)
			System.out.format("%-36s%-63s%12s%n", s, "INVALID", "INVALID");
	}
}
