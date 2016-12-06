
import java.util.ArrayList;


public class ArrayStack implements Stack 
{ 
  public ArrayStack() { array = new ArrayList(); } 
  
  public void push(Object x) { array.add(x); } 
  
  public Object pop() { return array.remove(array.size() - 1); } 
  
  public Object peekTop() { return array.get(array.size() - 1); } 
  
  public boolean isEmpty() { return array.size() == 0; } 

  private ArrayList array; 
} 
