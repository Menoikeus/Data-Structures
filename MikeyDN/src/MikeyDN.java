import javax.swing.JOptionPane;

public class MikeyDN {
	public static void main(String[] args)
	{
		System.out.format("%-36s%-36s%12s%n", "Postfix", "Infix", "Result");
		
		String s;
		while(!(s = JOptionPane.showInputDialog(null, "Postfix Expression: ")).toLowerCase().equals("quit"))
		{
			PostfixEvaluator.Evaluate(s);
		}
	}
}