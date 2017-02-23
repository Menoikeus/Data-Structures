public abstract class BinaryTree 
{
	
	private TreeNode root;
	
	public BinaryTree()   { root = null;}
	
	public TreeNode getRoot()  	{ return root;}
	
	public void setRoot (TreeNode theNewNode)  { root = theNewNode;}

	public boolean isEmpty()   { return root == null; }
	
	public abstract void insert (Comparable item);
	
	public abstract TreeNode find (Comparable key);
}

