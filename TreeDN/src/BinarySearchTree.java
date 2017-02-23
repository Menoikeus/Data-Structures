//BinarySearchTree class to extend abstract BinaryTree class

//Only need to provide implementation for the two abstract methods from
//   the BinaryTree class: "insert" and "find"
//All other methods from BinaryTree are automatically inherited


public class BinarySearchTree extends BinaryTree
{
	
	// No constructor provided so the compiler will call the default 
	// constructor from the BinaryTree superclass 
	
			
	// ------------------------------------------------------------
	// Returns TreeNode, p, which has the given "key"  -- OR --
	//     If "key" is not in tree, then returns null
	//
	// NOTE: THIS IS THE ITERATIVE VERSION OF THE "FIND" METHOD
	//
	// ------------------------------------------------------------
	
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
	
	// --------------------------------------------------------------------------------------
	// Insert an item into a binary search tree - Let's write the recursive "insert" method
	// --------------------------------------------------------------------------------------
	
	
	
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
			{
				
				insert2(Elder, node.getLeft());
				
			}
			else
			{
				node.setLeft(new TreeNode(Elder));
				
			}
		}
		else
		{
			System.out.println( Elder + " move right" );
			if(node.getRight() != null)
			{
				insert2(Elder, node.getRight());
				
			}
			else
			{
				node.setRight(new TreeNode(Elder));		
				
			}
		}
		
	}
	
	
		
	}












