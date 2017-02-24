import javax.swing.JOptionPane;


public class TreeDN {
	public static void main(String[] args)
	{
		String s = JOptionPane.showInputDialog("WORD: ");
		
		BinarySearchTree tree = new BinarySearchTree();
		
		for(int i = 0; i < s.length(); i++)
			tree.insert(s.substring(i, i+1));
		
		System.out.println("Height = " + tree.height());
		System.out.println("Depth = " + tree.depth());
		System.out.println("Width = " + tree.width());
		System.out.println("Leaves = " + tree.leaves());
	}

}
