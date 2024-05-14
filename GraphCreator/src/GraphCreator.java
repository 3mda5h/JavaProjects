/* Graph creator - user can create a graph of nodes and edges; find whether or not one node is connected to another; 
 * and find the shortest/cheapest path to connect all nodes, without backtracking
 * Author: Emily MacaPherson
 * Date: 1/10/21
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GraphCreator implements ActionListener, MouseListener
{
	JFrame frame = new JFrame();
	GraphPanel panel = new GraphPanel();
	JButton nodeB = new JButton("Create Node");
	JButton edgeB = new JButton("Create Edge");
	JTextField firstNode = new JTextField("First");
	JTextField secondNode = new JTextField("Second");
	JLabel JOptionText = new JLabel("hi");
	JButton connectedB = new JButton("Test Connected");
	Container north = new Container();
	Container nodesAndEdges = new Container();
	Container testConnected = new Container();
	Container shortestPath = new Container();
	Container nodeTFs = new Container();
	JTextField salesmanStartTF = new JTextField("Starting Node");
	JButton salesmanB = new JButton("Shortest Path");
	final int NODE_CREATE = 0;
	final int EDGE_FIRST = 1;
	final int EDGE_SECOND = 2;
	int state = NODE_CREATE;
	Node first = null; 
	ArrayList<ArrayList<Node>> completed = new ArrayList<ArrayList<Node>>(); 
	ArrayList<Integer> totals = new ArrayList<Integer>();
	
	
	//constructor for graph creator 
	public GraphCreator()
	{
		frame.setSize(1000, 800);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.white);
		north.setLayout(new FlowLayout(1, 25, 5)); 
		nodesAndEdges.setLayout(new FlowLayout());
		nodesAndEdges.add(nodeB);
		nodeB.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 19));
		nodeB.setPreferredSize(new Dimension(150, 100));
		nodeB.setBackground(new Color(0, 255, 128));
		nodeB.addActionListener(this);
		nodesAndEdges.add(edgeB);
		edgeB.setPreferredSize(new Dimension(150, 100));
		edgeB.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		edgeB.setBackground(Color.LIGHT_GRAY);
		edgeB.addActionListener(this);
		north.add(nodesAndEdges);
		nodeTFs.setLayout(new BorderLayout());
		nodeTFs.add(firstNode, BorderLayout.NORTH);
		firstNode.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		firstNode.setPreferredSize(new Dimension(120, 40));
		firstNode.setHorizontalAlignment(SwingConstants.CENTER);
		secondNode.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		secondNode.setPreferredSize(new Dimension(120, 40));
		secondNode.setHorizontalAlignment(SwingConstants.CENTER);
		nodeTFs.add(secondNode, BorderLayout.SOUTH);
		testConnected.setLayout(new FlowLayout());
		testConnected.add(nodeTFs);
		testConnected.add(connectedB);
		north.add(testConnected);
		connectedB.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		connectedB.setPreferredSize(new Dimension(150, 70));
		connectedB.addActionListener(this);
		shortestPath.setLayout(new FlowLayout());
		shortestPath.add(salesmanStartTF);
		salesmanStartTF.setPreferredSize(new Dimension(120, 40));
		salesmanStartTF.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		salesmanStartTF.setHorizontalAlignment(SwingConstants.CENTER);
		shortestPath.add(salesmanB);
		salesmanB.setPreferredSize(new Dimension(150, 70));
		salesmanB.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		salesmanB.addActionListener(this);
		north.add(shortestPath);		
		
		JOptionText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		
		frame.add(north, BorderLayout.NORTH);
		panel.addMouseListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		new GraphCreator();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}
	
	//called whenever the mouse is released
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		if(state == NODE_CREATE)
		{
			JOptionText.setText("Enter node label");
			String nodeLabel = JOptionPane.showInputDialog(frame, JOptionText);
			//if ok button is pressed
			if(nodeLabel != null)
			{
				//if theres no other node with the same name
				if(panel.nodeExists(nodeLabel) == false)
				{
					panel.addNode(e.getX(), e.getY(), nodeLabel);
				}
				else
				{
					JOptionText.setText("There is already a node called " + nodeLabel);
					JOptionPane.showMessageDialog(frame, JOptionText);
				}
			}	
		}
		else if(state == EDGE_FIRST)
		{
			Node n = panel.getNode(e.getX(), e.getY());
			if(n != null)
			{
				first = n;
				state = EDGE_SECOND;
				n.setHighlighted(true);
			}
		}
		else if(state == EDGE_SECOND)
		{
			Node n = panel.getNode(e.getX(), e.getY());
			//allows you to un highlight a node by clicking on it again
			if(n.getHighlighted() == true)
			{
				first = null;
				state = EDGE_FIRST;
				n.setHighlighted(false);
			}
			else
			{
				JOptionText.setText("Enter edge label");
				String edgeLabel = JOptionPane.showInputDialog(frame, JOptionText);
				//if ok button is pressed
				if(edgeLabel != null)
				{
					String s = edgeLabel;
					boolean valid = true;
					//check if label is a number
					for (int i = 0; i < s.length(); i++) 
					{
						if(Character.isDigit(s.charAt(i)) == false)
						{
							valid = false;
						}
					}
					if(valid == true)
					{
						n = panel.getNode(e.getX(), e.getY());
						if(n != null && !first.equals(n))
						{
							first.setHighlighted(false);
							panel.addEdge(first, n, edgeLabel);
							first = null;
							state = EDGE_FIRST;
						}
					}
					else
					{
						JOptionText.setText("Your edge label must be a number");
						JOptionPane.showMessageDialog(frame, JOptionText);
					}
				}
			}	
		}
		frame.repaint();
	}
	
	//called when a button is clicked
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource().equals((nodeB)))
		{
			nodeB.setBackground(new Color(0, 255, 128));
			edgeB.setBackground(Color.LIGHT_GRAY);
			state = NODE_CREATE;
		}
		if(e.getSource().equals(edgeB))
		{
			edgeB.setBackground(new Color(0, 255, 128));
			nodeB.setBackground(Color.LIGHT_GRAY);
			state = EDGE_FIRST;
			panel.stopHighlighting();
			frame.repaint();
		}
		if(e.getSource().equals(connectedB))
		{
			if(panel.nodeExists(firstNode.getText()) == false)
			{
				JOptionText.setText("Node " + firstNode.getText() + " does not exist");
				JOptionPane.showMessageDialog(frame, JOptionText);
			}
			else if(panel.nodeExists(secondNode.getText()) == false)
			{
				JOptionText.setText("Node " + secondNode.getText() + " does not exist");
				JOptionPane.showMessageDialog(frame, JOptionText);
			}
			else
			{
				ArrayList<String> connectedList = totalConnectedNodes(panel.getNode(firstNode.getText()));
				if(connectedList.contains(secondNode.getText()))
				{
					JOptionText.setText("Node " + firstNode.getText() + " and node "
							+ secondNode.getText() + " are connected.");
					JOptionPane.showMessageDialog(frame, JOptionText);
				}
				else
				{
					JOptionText.setText("Node " + firstNode.getText() + " and node "
							+ secondNode.getText() + " are not connected.");
					JOptionPane.showMessageDialog(frame, JOptionText);
				}
			}
		}
		if(e.getSource().equals(salesmanB))
		{
			if(panel.getNode(salesmanStartTF.getText()) != null)
			{
				//clear the completed and total arrays in case graph was modified
				completed.clear();
				totals.clear();
				if(panel.getNode(salesmanStartTF.getText()).getConnectedEdges().size() > 0)
				{
					traveling(panel.getNode(salesmanStartTF.getText()), new ArrayList<Node>(), 0);
					calculateShortest();
				}
				else
				{
					JOptionText.setText("Node " + salesmanStartTF.getText() + " has no connected edges");
					JOptionPane.showMessageDialog(frame, JOptionText);
				}
			}
			else
			{
				JOptionText.setText("Not a vaid starting node");
				JOptionPane.showMessageDialog(frame, JOptionText);
			}
		}
	}
	
	//returns an array list of the names of all the nodes connected to the given node
	public ArrayList<String> totalConnectedNodes(Node n)
	{
		Queue queue = new Queue();
		ArrayList<String> connectedList = new ArrayList<String>();
		connectedList.add(n.getLabel());
		ArrayList<String> edges = panel.getConnectedLabels(n.getLabel());
		for (int i = 0; i < edges.size(); i++) 
		{
			queue.enqueue(edges.get(i));
		}
		while(queue.isEmpty() == false)
		{
			String currentNode = queue.dequeue();
			if(connectedList.contains(currentNode) == false)
			{
				connectedList.add(currentNode);
			}
			edges = panel.getConnectedLabels(currentNode);
			for (int i = 0; i < edges.size(); i++) 
			{
				if(connectedList.contains(edges.get(i)) == false)
				queue.enqueue(edges.get(i));
			}
		}
		return connectedList;
	}
	
	//finds all the possible paths between a series of connected nodes, without backtracking 
	public void traveling(Node n, ArrayList<Node> path, int total)
	{
		//make an array list of the path specific to this instance of the function
		ArrayList<Node> thisPath = new ArrayList<Node>();
		for (int i = 0; i < path.size(); i++) 
		{
			thisPath.add(path.get(i));
		}
		thisPath.add(n);
		//edgeList is set to the edges connected to the current node
		ArrayList<Edge> edgeList = n.getConnectedEdges();
		//if the number of nodes in the path is equal to the total number of nodes connected to the starting node
		if(thisPath.size() >= totalConnectedNodes(panel.getNode(salesmanStartTF.getText())).size()) 
		{
			completed.add(thisPath); //add this path to the completed list
			totals.add(total); //add this paths total to the list of totals 
			return;
		}
		else
		{
			for (int i = 0; i < edgeList.size(); i++) 
			{
				Edge e = edgeList.get(i);
				//see if they are connected to the current node & if they are not already in the path
				if(e.getOtherEnd(n) != null && !thisPath.contains(e.getOtherEnd(n)))
				{
					//call the function again with the new node, path, and total
					traveling(e.getOtherEnd(n), thisPath, total + Integer.parseInt(e.getLabel()));
				}
			}
		}
	}
	
	//using the array of paths and the array of totals, calculates the "shortest" (smallest total) path
	public void calculateShortest()
	{
		
		int lowest = totals.get(0);
		int lowestIndex = 0;
		//find the lowest value and its index in the totals array
		for (int i = 0; i < totals.size(); i++) 
		{
			if(totals.get(i) < lowest)
			{
				lowest = totals.get(i);
				lowestIndex = i;
			}
		}
		String writtenPath = "";
		for (int i = 0; i < completed.get(lowestIndex).size(); i++) //the index of the lowest number will match up with the path it belongs to
		{
			writtenPath += completed.get(lowestIndex).get(i).getLabel() + ", ";
		}
		JOptionText.setText("the shortest path is: " + writtenPath + "with a total of: " + lowest);
		JOptionPane.showMessageDialog(frame, JOptionText);
	}
}
