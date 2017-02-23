public class TreeNode 
{
	// Basic TreeNode class 

	// Each node has a data item and a left and right pointer

	  private Object value;
	  private TreeNode left;
	  private TreeNode right;
	  
	  public TreeNode(Object initValue)
	    { 
	    	value = initValue; 
	    	left = null; 
	    	right = null; 
	    }

	  public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
	    { 
	    	value = initValue; 
	    	left = initLeft; 
	    	right = initRight; 
	    }

	  
	  // GET METHODS
	  
	  public Object getValue() { return value; }
	  
	  public TreeNode getLeft() { return left; }
	  
	  public TreeNode getRight() { return right; }

	  
	  // SET METHODS
	  
	  public void setValue(Object theNewValue) { value = theNewValue; }
	  
	  public void setLeft(TreeNode theNewLeft) { left = theNewLeft; }
	  
	  public void setRight(TreeNode theNewRight) { right = theNewRight; }

	
}
