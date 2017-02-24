import javax.swing.JOptionPane;


public class TreeDN {
	public static void main(String[] args)
	{
		// repeater
		boolean done = false;
		while(!done)
		{
			// input
			String s = JOptionPane.showInputDialog(
					"WORD (write 0 if you're done): ");
			if(!s.equals("0"))
			{		
				BinarySearchTree tree = new BinarySearchTree();
				
				// insert all the values
				for(int i = 0; i < s.length(); i++)
					tree.insert(s.substring(i, i+1));
				
				// run the calculations
				System.out.println("Word: " + s);
				System.out.println("    Height = " + tree.height());
				System.out.println("    Depth = " + tree.depth());
				System.out.println("    Width = " + tree.width());
				System.out.println("    Leaves = " + tree.leaves());
				System.out.println("\n");
			}
			else
				done = true;
		}
	}

}
