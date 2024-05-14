import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Semi extends Vehicle
{
	Image myImage;
	
	//sets dimensions, speed, and image
	public Semi(int newX, int newY)
	{
		super(newX, newY);
		width = 110;
		height = 34;
		speed = 1;
		try 
		{
			myImage = ImageIO.read(new File("semi.png"));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
	
	//draws semi
	public void paintMe(Graphics g)
	{
		g.drawImage(myImage, x, y, null);
	}
}
