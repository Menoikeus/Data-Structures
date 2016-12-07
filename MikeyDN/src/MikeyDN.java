import javax.swing.JOptionPane;

public class MikeyDN {
	public static void main(String[] args)
	{
		PostfixEvaluator evaluator = new PostfixEvaluator();
		
		System.out.println(evaluator.Evaluate(JOptionPane.showInputDialog(null, "Postfix Expression: ")));
	}
}
