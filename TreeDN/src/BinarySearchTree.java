public class BinarySearchTree extends BinaryTree
{
	private int width;
	
	// constructor
	public BinarySearchTree()
	{
		super();
		width = 0;
	}
	
	//iterative find written by class
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
	
	// recursion find written by class
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
	
	// helper method we wrote
	public void insert (Comparable Elder)
	{
		if( this.getRoot() == null )
			setRoot(new TreeNode(Elder));
		else
			insert2(Elder, this.getRoot());
	}
	
	// insert method we wrote together
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
	
	// get width helper method
	public int width()
	{
		width = 0;
		widthCheck(getRoot());
		return width;
	}
	
	public int widthCheck(TreeNode node)
	{
		if(node.getRight() == null && node.getLeft() == null) 
			// if it's a leaf, then it has depth 1
			return 1;
		else
		{
			// if left/right are not null, run a widthcheck, 
			// which will return the longest path
			// with aforementioned left/right node as root
			int left = node.getLeft() == null ? 
					0 : widthCheck(node.getLeft());
			int right = node.getRight() == null ? 
					0 : widthCheck(node.getRight());

			// if this path through root (the longest path left 
			// and right, + the root itself) is longer
			// than current highest width, increment
			if(left + right + 1 > width)
				width = left + right + 1;
			
			// return one of the longest paths 
			// (left or right) and add root
			// We only add one side because, if a path
			// has to choose between two routes,
			// it will obviously choose the longer one
			return 1 + (left > right ? left : right);
		}
	}
	
	// helper method
	public int height()
	{
		return depthCheck(getRoot());	
	}
	
	public int depthCheck(TreeNode node)
	{
		// if it's a leaf, add one to the count
		if(node.getRight() == null && node.getLeft() == null)
			return 1;
		else
		{
			// if left/right aren't null, then 
			// run depth check to find depth
			// of left and right
			int left = node.getLeft() != null ? 
					depthCheck(node.getLeft()) : 0;
			int right = node.getRight() != null ? 
					depthCheck(node.getRight()) : 0;
			
			// return the larger value 
			// (since height/depth depends on 
			// longest route from root to leaf)
			return 1 + (left > right ? left : right);
		}
	}
	
	public int depth()
	{
		// but why?
		return height() - 1;
	}
	
	// helper method
	public int leaves()
	{
		return leafCheck(getRoot());
	}
	
	public int leafCheck(TreeNode node)
	{
		// if it's a leaf, then count it
		if(node.getRight() == null && node.getLeft() == null)
			return 1;
		else
		{
			// check left/right null, then leafcheck the non nulls
			int left = node.getLeft() != null ? 
					leafCheck(node.getLeft()) : 0;
			int right = node.getRight() != null ? 
					leafCheck(node.getRight()) : 0;
			
			// add num leaves from left and right
			return left + right;
		}	
	}
}