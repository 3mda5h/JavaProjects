import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;


public class GraphPanel extends JPanel
{
	ArrayList<Node> nodeList = new ArrayList<Node>();
	ArrayList<Edge> edgeList = new ArrayList<Edge>();
	int circleRadius = 30;
	
	ArrayList<ArrayList<Boolean>> adjacency = new ArrayList<ArrayList<Boolean>>();
	
	public GraphPanel()
	{
		super();
	}
	
	//returns the names of the nodes connected to the given node
	public ArrayList<String> getConnectedLabels(String label)
	{
		ArrayList<String> toReturn = new ArrayList<String>();
		int b = getIndex(label);
		for (int i = 0; i < adjacency.size(); i++) 
		{
			if(adjacency.get(b).get(i) == true && !nodeList.get(i).getLabel().equals(label))
			{
				toReturn.add(nodeList.get(i).getLabel());
			}
		}
		return toReturn;
	}
	
	//prints a matrix in the console showing the connections between all the nodes in the graph
	public void printAdjacency()
	{
		System.out.println();
		for (int i = 0; i < adjacency.size(); i++) 
		{
			for (int j = 0; j < adjacency.get(0).size(); j++) 
			{
				System.out.print(adjacency.get(i).get(j) + "\t");
			}
			System.out.println();
		}
	}
	
	//adds a node
	public void addNode(int newX, int newY, String newLabel)
	{
		nodeList.add(new Node(newX, newY, newLabel));
		adjacency.add(new ArrayList<Boolean>());
		for (int i = 0; i < adjacency.size() - 1; i++) 
		{
			adjacency.get(i).add(false);
		}
		for (int i = 0; i < adjacency.size(); i++) 
		{
			adjacency.get(adjacency.size() - 1).add(false);
		}
		printAdjacency();
	}
	
	//gets node given an X and Y position
	public Node getNode(int x, int y)
	{
		for (int i = 0; i < nodeList.size(); i++) 
		{
			Node node = nodeList.get(i);
			double radius = Math.sqrt(Math.pow(x - node.getX(), 2) + Math.pow(y - node.getY(), 2));
			if(radius < circleRadius)
			{
				return node;
			}
		}
		return null;
	}
	
	//given a label, this returns the node it belongs to
	public Node getNode(String s)
	{
		for (int i = 0; i < nodeList.size(); i++)
		{
			Node node = nodeList.get(i);
			if(s.equals(node.getLabel()))
			{
				return node;
			}
		}
		return null;
	}
	
	//returns the index of a node in the nodeList
	public int getIndex(String s)
	{
		for (int i = 0; i < nodeList.size(); i++)
		{
			Node node = nodeList.get(i);
			if(s.equals(node.getLabel()))
			{
				return i;
			}
		}
		return -1;
	}
	
	//returns where or not a node exists
	public boolean nodeExists(String s)
	{
		for (int i = 0; i < nodeList.size(); i++) 
		{
			if(s.equals(nodeList.get(i).getLabel()))
			{
				return true;
			}
		}
		return false;
	}
	
	//creates an edge
	public void addEdge(Node first, Node second, String newLabel)
	{
		edgeList.add(new Edge(first, second, newLabel));
		int firstIndex = 0;
		int secondIndex = 0;
		for (int i = 0; i < nodeList.size(); i++) 
		{
			if(first.equals(nodeList.get(i)))
			{
				firstIndex = i;
			}
			if(second.equals(nodeList.get(i)))
			{
				secondIndex = i;
			}
		}
		adjacency.get(firstIndex).set(secondIndex, true);	
		adjacency.get(secondIndex).set(firstIndex, true);	
		printAdjacency();
	}
	 
	//draws components on the the panel
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int i = 0; i < nodeList.size(); i++) 
		{
			if(nodeList.get(i).getHighlighted() == true)
			{
				g.setColor(Color.red);
			}
			else
			{
				g.setColor(Color.black);
			}
			g.drawOval(nodeList.get(i).getX() - circleRadius, nodeList.get(i).getY() - circleRadius, 
					circleRadius * 2, circleRadius * 2);
			g.setColor(Color.LIGHT_GRAY);
			g.setColor(Color.black);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
			g.drawString(nodeList.get(i).getLabel(), nodeList.get(i).getX() - 5, nodeList.get(i).getY() + 6);
		}
		for (int i = 0; i < edgeList.size(); i++) 
		{
			g.setColor(Color.black);
			g.drawLine(edgeList.get(i).getFirst().getX(), edgeList.get(i).getFirst().getY(), 
					edgeList.get(i).getSecond().getX(), edgeList.get(i).getSecond().getY());
			int fx = edgeList.get(i).getFirst().getX();
			int fy = edgeList.get(i).getFirst().getY();
			int sx = edgeList.get(i).getSecond().getX();
			int sy = edgeList.get(i).getSecond().getY();

			g.drawString(edgeList.get(i).getLabel(), Math.min(fx, sx) + (Math.abs(sx - fx) / 2),
					Math.min(fy, sy) + (Math.abs(sy - fy) / 2));
		}
	}
	
	//removes red highlight from a node
	public void stopHighlighting() 
	{
		for (int i = 0; i < nodeList.size(); i++) 
		{
			nodeList.get(i).setHighlighted(false);
		}
	}
}
