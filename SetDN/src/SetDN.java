import javax.swing.JFrame;

//main, blah blah, starts the actual program
public class SetDN{
	public static void main(String[] args)
	{
		Game tool = new Game();
		tool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tool.setTitle("SET 2K16");
		tool.pack();
		tool.show();
	}
}
