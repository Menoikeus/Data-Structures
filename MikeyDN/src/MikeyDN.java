
public class MikeyDN {
	public static void main(String[] args)
	{
		PostfixEvaluator evaluator = new PostfixEvaluator();
		
		System.out.println(evaluator.Evaluate("4 5 6 * 3 / +"));
	}
}
