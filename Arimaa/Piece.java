import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Piece
{
  ImageIcon animalIcon = new ImageIcon("");
  String color = "";
  int powerLevel = 1;
  ArrayList<Piece> neighbors = new ArrayList<Piece>();
  int startingPositionX = 0;
  int startingPositionY = 0;

  public Piece(ImageIcon newAnimalIcon, int newPowerLevel, String turn)
  {
    animalIcon = newAnimalIcon;
    if(turn.equals("goldSetup") || turn.equals("goldPlay")|| turn.equals ("goldMove")) //also need push and pull states
    {
      color = "gold";
    }
    else
    {
      color = "silver";
    }
    powerLevel = newPowerLevel;
    //for debugging
    //System.out.println("New piece created. Color: " + color + ", power level: " +  Integer.toString(powerLevel));
  }
  
  public void addNeighbor(Piece neighbor)
  {
    neighbors.add(neighbor);
  }
  public void resetNeighbors() 
  {
	  neighbors.removeAll(neighbors);
  }
  public void setStartingPositionX(int x)
  {
    startingPositionX = x;
  }
   public void setStartingPositionY(int y)
  {
    startingPositionY = y;
  }

  public String getColor()
  {
  	return color;
  }
  public ImageIcon getAnimal()
  {
  	return animalIcon;
  }
  public int getPowerLevel()
  {
  	return powerLevel;
  }
  public ArrayList<Piece> getNeighbors()
  {
    return neighbors;
  }
  public  int getStartingPositionX()
  {
    return startingPositionX;
  }
  public int getStartingPositionY()
  {
    return startingPositionY;
  }
	
}
