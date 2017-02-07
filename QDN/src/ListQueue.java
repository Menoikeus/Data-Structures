import java.util.LinkedList;


public class ListQueue implements Queue 
{ 
  public ListQueue() { list = new LinkedList(); } 

  public void enqueue(Object x) { list.addLast(x); } 
  public Object dequeue() { return list.removeFirst(); } 
  public Object peekFront() { return list.getFirst(); } 
  public boolean isEmpty() { return list.size() == 0; } 
  public int size() { return list.size(); } 

  private LinkedList list; 
} 
