public class BinarySearchTree extends BinaryTree
{
	int width;
	
	public BinarySearchTree()
	{
		super();
		width = 0;
	}
	
	public TreeNode find (Comparable key)
	{
		TreeNode p = getRoot();
		while (p != null && key.compareTo(p.getValue()) != 0)
		{
			if (key.compareTo(p.getValue()) < 0 )
				p = p.getLeft();
			else
				p = p.getRight();
		}
		return p;
	}	
	
	public TreeNode RecurFind (Comparable Elder, TreeNode node)
	{
		if (node != null)
		{
			if(Elder.compareTo(node.getValue()) != 0)
				return node;
			else if(Elder.compareTo(node.getValue()) <= 0)
				return RecurFind(Elder, node.getLeft());
			else
				return RecurFind(Elder, node.getRight());
		}
		else
		{
			return null;
		}
	}
	
	
	public void insert (Comparable Elder)
	{
		if( this.getRoot() == null )
			setRoot(new TreeNode(Elder));
		else
			insert2(Elder, this.getRoot());
	}
	
	public void insert2 (Comparable Elder, TreeNode node)
	{
		if(Elder.compareTo(node.getValue()) <= 0)
		{
			System.out.println( Elder + " move left" );
			if(node.getLeft() != null)
				insert2(Elder, node.getLeft());
			else
				node.setLeft(new TreeNode(Elder));
		}
		else
		{
			System.out.println( Elder + " move right" );
			if(node.getRight() != null)
				insert2(Elder, node.getRight());
			else
				node.setRight(new TreeNode(Elder));		
		}
	}
	
	public int width()
	{
		width = 0;
		widthCheck(getRoot());
		return width;
	}
	
	public int widthCheck(TreeNode node)
	{
		if(node == null)
			return 0;
		if(node.getRight() == null && node.getLeft() == null)
			return 1;
		else
		{
			int left = depthCheck(node.getLeft());
			int right = depthCheck(node.getRight());

			if(left + right + 1 > width)
				width = left + right + 1;
			return left + right + 1;
		}
	}
	
	public int height()
	{
		return depthCheck(getRoot());	
	}
	
	public int depthCheck(TreeNode node)
	{
		if(node.getRight() == null && node.getLeft() == null)
			return 1;
		else
		{
			int left = node.getLeft() != null ? depthCheck(node.getLeft()) : 0;
			int right = node.getRight() != null ? depthCheck(node.getRight()) : 0;
			return 1 + (left > right ? left : right);
		}
	}
	
	public int depth()
	{
		return height() - 1;
	}
	
	public int leaves()
	{
		return leafCheck(getRoot());
	}
	
	public int leafCheck(TreeNode node)
	{
		if(node.getRight() == null && node.getLeft() == null)
		{
			return 1;
		}
		else
		{
			int left = node.getLeft() != null ? leafCheck(node.getLeft()) : 0;
			int right = node.getRight() != null ? leafCheck(node.getRight()) : 0;
			return left + right;
		}
		
	}
	
}