import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Road extends JPanel
{
	
	final int LANE_HEIGHT = 150;
	final int ROAD_WIDTH = 900;
	ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
	int carCount = 0;
	
	public Road()
	{
		super();
	}
	
	public void addCar(Vehicle v)
	{
		cars.add(v);
	}
	
	//draws the cars
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		for (int a = LANE_HEIGHT; a < LANE_HEIGHT * 4; a += LANE_HEIGHT) // which lane
		{
			for (int b = 0; b < getWidth(); b+= 40) // which line
			{
				g.fillRect(b, a, 30, 5);
			}
		}
		
		//draws each car in array
		for (int a = 0; a < cars.size(); a++) 
		{
			cars.get(a).paintMe(g);
		}
	}
	
	//called every 10 milliseconds while game is running
	public void step()
	{
		for (int a = 0; a < cars.size(); a++)
		{
			Vehicle v = cars.get(a);
			if(collision(v.getX() + v.getSpeed(), v.getY(), v) == false) //there will be no collision 
			{
				v.setX(v.getX() + v.getSpeed()); //move
				if(v.getX() > ROAD_WIDTH) //if car off road
				{
					if(collision(-50, v.getY(), v) == false) //if wrapping wont cause collision
					{
						v.setX(-50);
						carCount++;
					}
				}
			}
			else //car ahead
			{
				if(v.getY() > 55 && collision(v.getX(), v.getY() - LANE_HEIGHT, v) == false) //won't collide left
				{
					v.setY(v.getY() - LANE_HEIGHT);
				}
				else if(v.getY() < 55 + (3 * LANE_HEIGHT) &&
						collision(v.getX(), v.getY() + LANE_HEIGHT, v) == false) //won't collide right
				{
					v.setY(v.getY() + LANE_HEIGHT);
				}
			}
		}
	}
	
	//returns whether or not the car will collide with another car
	public boolean collision(int x, int y, Vehicle v)
	{
		for (int a = 0; a < cars.size(); a++) 
		{
			Vehicle u = cars.get(a);
			if(y == u.getY()) //if in same lane
			{
				if(u.equals(v) == false) //if not self
				{
					if(x < u.getX() + u.getWidth() && //left side is left of his right side
							x + v.getWidth() > u.getX()) //right side is right of his left side
					{
						return true;
					}
				}
			}
		}
		return false;
		
	}
	
	//car count getter
	public int getCarCount()
	{
		return carCount;
	}
	
	//resets carcount
	public void resetCarCount()
	{
		carCount = 0;
	}

}
