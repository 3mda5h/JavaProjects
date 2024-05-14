
public class Edge 
{
	Node first;
	Node second;
	String label;
	
	public Edge(Node newFirst, Node newSecond, String newLabel)
	{
		//add this edge to the array list of connected edges for the first and second node
		newFirst.setConnectedEdge(this);
		newSecond.setConnectedEdge(this);
		first = newFirst;
		second = newSecond;
		label = newLabel;
	}
	
	//returns the node on the other end of this edge
	public Node getOtherEnd(Node n)
	{
		if(first.equals(n))
		{
			return second;
		}
		else if(second.equals(n))
		{
			return first;
		}
		else
		{
			return null; 
		}
	}
	
	//getters and setters
	public Node getFirst() 
	{
		return first;
	}
	public void setFirst(Node first) 
	{
		this.first = first; 
	}
	public Node getSecond() 
	{
		return second;
	}
	public void setSecond(Node second) 
	{
		this.second = second;
	}
	public String getLabel() 
	{
		return label;
	}
	public void setLabel(String label) 
	{
		this.label = label;
	}
}
