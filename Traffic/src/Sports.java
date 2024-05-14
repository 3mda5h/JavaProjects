import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sports extends Vehicle
{
	Image myImage;
	
	//sets dimensions, speed, and image
	public Sports(int newX, int newY)
	{
		super(newX, newY);
		width = 70;
		height = 70;
		speed = 3;
		try 
		{
			myImage = ImageIO.read(new File("sports.png"));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//draws sports
	public void paintMe(Graphics g)
	{
		g.drawImage(myImage, x, y, null);
	}
}
