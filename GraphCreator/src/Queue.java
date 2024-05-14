import java.util.ArrayList;

public class Queue 
{
	ArrayList<String> queue = new ArrayList<String>();
	
	//adds string to queue 
	public void enqueue(String s)
	{
		queue.add(s);
	}
	
	//removes string from queue and returns it
	public String dequeue()
	{
		String s = queue.get(0);
		queue.remove(0);
		return s;
	}
	
	//returns if the queue is empty or not
	public boolean isEmpty()
	{
		return queue.isEmpty(); 
	}
}
