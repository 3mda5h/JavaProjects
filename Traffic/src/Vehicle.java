import java.awt.Graphics;

public class Vehicle 
{
	int x;
	int y;
	int width = 0;
	int height = 0;
	int speed = 0;
	
	public Vehicle(int newX, int newY)
	{
		x = newX;
		y = newY;
	}
	
	public void paintMe(Graphics g)
	{
		
	}
	
	//x getter
	public int getX()
	{
		return x;
	}
	
	//x setter
	public void setX(int newX)
	{
		x = newX;
	}
	
	//speed getter
	public int getSpeed()
	{
		return speed;
	}
	
	//y getter
	public int getY()
	{
		return y;
	}
	
	//y setter
	public void setY(int newY)
	{
		y = newY;
	}
	
	//width getter;
	public int getWidth()
	{
		return width;
	}
}
