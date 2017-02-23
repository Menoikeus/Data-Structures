import javax.swing.JOptionPane;


public class TreeDN {
	static int z;
	
	public static void main(String[] args)
	{
		z = 0;
		
		String s = JOptionPane.showInputDialog("WORD: ");
		
		BinaryTree tree = new BinarySearchTree();
		
		for(int i = 0; i < s.length(); i++)
			tree.insert(s.substring(i, i+1));
		
		System.out.println("Height = " + height(tree));
		System.out.println("Depth = " + (height(tree)-1));
		System.out.println("Width = " + width2(tree));
	}
	
	public static int height(BinaryTree tree)
	{
		TreeNode node = tree.getRoot();
		return depthCheck(node.getLeft()) > depthCheck(node.getRight()) ?
				depthCheck(node.getLeft()) : depthCheck(node.getRight());
	}
	
	public static int depthCheck(TreeNode node)
	{
		if(node == null)
			return 1;
		else
		{
			return 1 + (depthCheck(node.getLeft()) > depthCheck(node.getRight()) ?
					depthCheck(node.getLeft()) : depthCheck(node.getRight()));
		}
	}
	
	/*
	public static int width(BinaryTree tree)
	{
		TreeNode node = tree.getRoot();
		return depthCheck(node.getLeft()) > depthCheck(node.getRight()) ?
				depthCheck(node.getLeft()) : depthCheck(node.getRight());
	}
	
	
	public static int enter(TreeNode root)
	{
		BinaryTree leftTree = new BinarySearchTree();
		leftTree.setRoot(root.getLeft());
		BinaryTree rightTree = new BinarySearchTree();
		rightTree.setRoot(root.getRight());
		
		if(height(leftTree) > height(rightTree))
			return height(leftTree) + height()
		
	}*/
	
	public static int width2(BinaryTree tree)
	{
		TreeNode node = tree.getRoot();
		
		widthCheck(node);
		return z;
		//return (1 + widthCheck(node.getLeft()) + widthCheck(node.getRight())) < z ? 
			//	z : (widthCheck(node.getLeft()) > widthCheck(node.getRight()) ?
				//		widthCheck(node.getLeft()) : widthCheck(node.getRight()));
	}
	
	public static int widthCheck(TreeNode node)
	{
		if(node == null)
			return 0;
		if(node.getRight() == null && node.getLeft() == null)
			return 1;
		else
		{
			int left = widthCheck(node.getLeft());
			int right = widthCheck(node.getRight());
			//int height = 1 + widthCheck(node.getLeft()) + widthCheck(node.getRight());
					
					//(depthCheck(node.getLeft()) > depthCheck(node.getRight()) ?
					//depthCheck(node.getLeft()) : depthCheck(node.getRight()));
			if(left + right + 1 > z)
			{
				z = left + right + 1;
			}
			return 1 + (left > right ? left : right);
		}
		/*
		else
		{
			return 1 + (depthCheck(node.getLeft()) > depthCheck(node.getRight()) ?
					depthCheck(node.getLeft()) : depthCheck(node.getRight()));
		}*/
	}

}
