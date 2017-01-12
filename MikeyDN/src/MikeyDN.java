import javax.swing.JOptionPane;

public class MikeyDN {
	public static void main(String[] args)
	{
		// table heading
		System.out.format("%-36s%-63s%12s%n", "Postfix", "Infix", "Result");
		
		// create string s to take in input, and check if it's 'quit'. if so, stop, if not, evaluate s
		String s;
		while(!(s = JOptionPane.showInputDialog(null, "Postfix Expression (or 'quit' to quit): ")).toLowerCase().equals("quit"))
		{
			PostfixEvaluator.Evaluate(s);
		}
	}
}