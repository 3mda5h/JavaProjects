import java.util.ArrayList;

public class Node
{
	int x;
	int y;
	String label;
	boolean highlighted;
	ArrayList<Edge> connectedEdges = new ArrayList<Edge>();
	
	public Node(int newX, int newY, String newLabel)
	{
		x = newX;
		y = newY;
		label = newLabel;
		highlighted = false;
	}
	
	//getters and setters
	public int getX() 
	{
		return x;
	}
	public void setX(int x) 
	{
		this.x = x;
	}
	public int getY() 
	{
		return y;
	}
	public void setY(int y) 
	{
		this.y = y;
	}
	public String getLabel() 
	{
		return label;
	}
	public void setLabel(String label) 
	{
		this.label = label;
	}
	public boolean getHighlighted()
	{
		return highlighted;
	}
	public void setHighlighted(boolean highlighted)
	{
		this.highlighted = highlighted;
	}
	public void setConnectedEdge(Edge edge)
	{
		this.connectedEdges.add(edge);
	}
	public ArrayList<Edge> getConnectedEdges()
	{
		return connectedEdges;
	}
	
}