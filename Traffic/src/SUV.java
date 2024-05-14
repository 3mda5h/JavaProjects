import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class SUV extends Vehicle
{
	Image myImage;
	
	//sets dimensions, speed, and image
	public SUV(int newX, int newY)
	{
		super(newX, newY);
		width = 80;
		height = 38;
		speed = 2;
		try 
		{
			myImage = ImageIO.read(new File("suv.png"));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
	
	//draws suv
	public void paintMe(Graphics g)
	{
		g.drawImage(myImage, x, y, null);
	}
}
