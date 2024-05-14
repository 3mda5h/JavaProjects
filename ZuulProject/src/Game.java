import java.util.ArrayList;
/*Zuul - A text based adventure game. Wander around Cedar Mill, picking up and dropping objects until you win
 * Author: Emily MacPherson but also these dudes: Michael Kolling and David J. Barnes
 * Date: 10/25/20
 */

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initializes all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */
//heyy why are your comments blue no fair i wanna be blue
/**
 * ooh cool now im blue :D
 */

/*
 * HOW TO WIN GAME (if you don't wanna figure it out):
 * - go north right away, get hand sanitizer (you can't pick up anything without it)
 * - get string from tennis store
 * - find your way to dollar tree as quickly as possible, sunset is just a distraction--only bad things 
 * will come from there hehe
 * - get umbrella and facemask at dollar tree. you cannot enter safeway without a mask, and you will
 * need umbrella later to protect your book
 * - get or don't get something at safeway, doesn't matter
 * - don't get anything at mcdonalds, they will sneeze on your food!!
 * - go to library and get the sci fi book. everyone knows sci fi is the best genre (is that how you
 * spell genre?? it looks so wrong) 
 * - get an apple pie at the bakery (apple pie is objectively the best pie, and you need it to win. if you
 * are not aware of this fact, hopefully the clues at safeway will point you towards the correct flavor of 
 * pie
 * - get chicken nuggets at dq instead of mcdonalds. you'll need these to befriend the cat :D
 * - you will find the cat in the empty construction site. don't bring your apple pie there, you will trip and 
 * drop it!! leave pie at library or somewhere else
 * - once at construction site you use the string to lure the cat towards you and you feed it some chicken.
 * you are now best friends forever :D
 * - pick up cat and go back to get the pie. if you also have the sci fi book, you win!
 *
 *LINK TO MAP: 
 *https://www.canva.com/design/DAELo9UxR-A/I3tgJyfn0CVsHM_eZj_gCA/view?utm_content=DAELo9UxR-A&utm_campaign=designshare&utm_medium=link&utm_source=sharebutton
 *(I think it should work??)
 */
class Game 
{
    private Parser parser;
    private Room currentRoom;
    Room sac, sacBathroom, sacTennisStore, sunsetMain, sunsetOneHall, oneTwenty, dollarTree, safeway, wallgreens, mcdonalds, 
    bakery, library, libraryBathroom, constructionSite, dq;
    ArrayList<Item> inventory = new ArrayList<Item>();
        
    /**
     * Create the game and initialize its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }
    
    public static void main(String[] args)
    {
    	Game mygame = new Game();
    	mygame.play();
    }
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
      
        // create the rooms
        sac = new Room("at Sunset Athletic Club.");
        sacBathroom = new Room("in the SAC bathroom.");
        sacTennisStore = new Room("in the SAC tennis store, they re-string raquets here.");
        sunsetMain = new Room("standing in the main hallway at Sunset highschool.");
        sunsetOneHall = new Room("in One Hall at sunset highschool.");
        oneTwenty = new Room("in room 1-20, a sorta cool classroom.");
        dollarTree = new Room("at the Dollar Tree. Why not buy something?");
        safeway = new Room("at Safeway.");
        wallgreens = new Room("at Wallgreens.");
        mcdonalds = new Room("at McDonalds. Maybe get some food.");
        library = new Room("at the Cedar Mill Library.");
        libraryBathroom = new Room("in the bathroom at Cedar Mill library. This definetly does not just exist so I "
        		+ "have 15 rooms.");
        bakery = new Room("at Grand Central Bakery. Get something tasty :P");
        dq = new Room("at Dairy Queen. Maybe get some food.");
        constructionSite = new Room("at the construction site next to the Cedar Mill library. It's empty right now. "
        		+ " But wait... is that movement?");
        
        // Initialize room exits & items
        sac.setExit("south", sacTennisStore);
        sac.setExit("north", sacBathroom);
        
        sacBathroom.setExit("south", sac);
        sacBathroom.setItem(new Item("sanitizer"));
        
        sacTennisStore.setExit("north", sac);
        sacTennisStore.setExit("south", sunsetMain);
        sacTennisStore.setItem(new Item("string"));
        
        sunsetMain.setExit("north", sacTennisStore);
        sunsetMain.setExit("east", dollarTree);
        sunsetMain.setExit("west", sunsetOneHall);
        
        sunsetOneHall.setExit("south", oneTwenty);
        sunsetOneHall.setExit("east", sunsetMain);
        sunsetOneHall.setItem(new Item("balloon"));
        
        oneTwenty.setExit("north", sunsetOneHall);
        
        dollarTree.setExit("west", sunsetMain);
        dollarTree.setExit("east", safeway);
        dollarTree.setItem(new Item("umbrella"));
        dollarTree.setItem(new Item("facemask"));
        dollarTree.setItem(new Item("socks"));
        
        safeway.setExit("west", dollarTree);
        safeway.setExit("south", wallgreens);
        safeway.setExit("east", mcdonalds);
        safeway.setItem(new Item("applejuice"));
        safeway.setItem(new Item("apple"));
        safeway.setItem(new Item("applesauce"));
        
        wallgreens.setExit("north", safeway);
        wallgreens.setExit("east", bakery);
        
        bakery.setExit("west", wallgreens);
        bakery.setExit("east", constructionSite);
        bakery.setExit("south", dq);
        bakery.setItem(new Item("applepie"));
        bakery.setItem(new Item("chocolatepie"));
        bakery.setItem(new Item("pumpkinpie"));
        
        mcdonalds.setExit("west", safeway);
        mcdonalds.setExit("east", library);
        mcdonalds.setItem(new Item("milkshake"));
        mcdonalds.setItem(new Item("coffee"));
        mcdonalds.setItem(new Item("chickennuggets"));
        
        library.setExit("west", mcdonalds);
        library.setExit("south", constructionSite);
        library.setExit("north", libraryBathroom);
        library.setItem(new Item ("textbook"));
        library.setItem(new Item ("sciFiBook"));
        library.setItem(new Item ("magazine"));
        
        libraryBathroom.setExit("south", library);
        
        dq.setExit("north", bakery);
        dq.setItem(new Item("dipcone"));
        dq.setItem(new Item("blizzard"));
        dq.setItem(new Item("chickennuggets"));
        
        constructionSite.setExit("north", library);
        constructionSite.setExit("west", bakery);
        constructionSite.setItem(new Item("cat"));
        
        currentRoom = sac;  // start game at SAC
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    boolean cat = false;
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        while(!finished) 
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
            
            //hehe
            if(currentRoom == oneTwenty)
            {
            	System.out.println("Oh no!! There was some corona virus lingering in the air in room 1-20!! You lose :O");
            	finished = true;
            }
            //winning conditions 
            if(hasBook == true && hasCat == true && hasPie == true)
        	{
        		System.out.println("You have the best type of pie, a good book, and a cat!! You win :D!!!");
        		finished = true;
        	}
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Adventure!");
        System.out.println("Adventure is a new, incredibly boring adventure game.");
        System.out.println("Your goal is: stay healthy, get a tasty pastry, get a good book, and befreind a cat.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) 
        {
            System.out.println("I don't know what you mean. Make sure you are using a valid command word.");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
        {
        	 printHelp();
        }
        else if(commandWord.equals("go"))
        {
        	wantToQuit = goRoom(command);
        }
        else if(commandWord.equals("quit")) 
        {
            wantToQuit = quit(command);
        }
        else if(commandWord.equals("inventory"))
        {
        	printInventory();
        }
        else if(commandWord.equals("get"))
        {
        	 wantToQuit = getItem(command);
        }
        else if(commandWord.equals("drop"))
        {
        	dropItem(command);
        }
        return wantToQuit;
    }
    
    //takes item out of player's inventory and puts into the room's inventory
    private void dropItem(Command command) 
    {
        if(!command.hasSecondWord())
        {
            // if there is no second word, we don't what to drop...
            System.out.println("Drop what?");
            return;
        }

        String item = command.getSecondWord();

        Item newItem = null;
        int index = 0;
        for(int i = 0; i < inventory.size(); i++) 
        {
			if(inventory.get(i).getDescription().equals(item))
			{
				newItem = inventory.get(i);
				index = i;
			}
		}

        if (newItem == null)
        {
            System.out.println("You don't even have that item :O");
        }
        else 
        {
            inventory.remove(index);
            currentRoom.setItem(new Item (item));
            System.out.println("Dropped: " + item);
            
            //sets item variables to false if you no longer have them
            if(item.equals("sanitizer"))
            {
            	hasSanitizer = false;
            }
            if(item.equals("string"))
            {
            	hasString = false;
            }
            if(item.equals("balloon"))
            {
            	hasBalloon = false;
            }
            if(item.equals("facemask"))
            {
            	hasMask = false;
            }
            if(item.equals("umbrella"))
            {
            	hasUmbrella = false;
            }
            if(item.equals("chickennuggets"))
            {
            	hasChicken = false;
            }
            if(item.equals("applepie"))
            {
            	hasPie = false;
            }
            if(item.equals("sciFiBook"))
            {
            	hasBook = false;
            }
            if(item.equals("cat"))
            {
            	hasCat = false;
            }
        }
    }
    
    //variables dealing with whether or not you have a certain item
    boolean hasSanitizer = false;
    boolean hasMask = false;
    boolean hasUmbrella = false;
    boolean hasChicken = false;
    boolean hasString = false;
    boolean hasBalloon = false;
    boolean hasPie = false;
    boolean hasBook = false;
    boolean hasCat = false;
    
    //puts item in player's inventory and removes from room inventory
    private boolean getItem(Command command) 
    {
    	if(!command.hasSecondWord()) 
    	{
            // if there is no second word, we don't know what to get...
            System.out.println("Get what?");
            return false;
        }

        String item = command.getSecondWord();

        // Try to get item
        Item newItem = currentRoom.getItem(item);

        if(newItem == null)
        {
            System.out.println("There is no such item");
            return false;
        }
        if(hasSanitizer == false && !newItem.getDescription().equals("sanitizer"))
        {
        	System.out.println("Dude, you can't just touch random objects without hand sanitizer!! "
        			+ "Don't you know there's a pandemic right now?!! You get corona. You lose.");
        	return true;
        }
        if(currentRoom == mcdonalds)
        {
        	System.out.println("Oh no!! McDonald's employee sneezed on your food D: You get corona, you lose.");
        	return true;
        }
        if(newItem.getDescription().equals("cat") && cat == false)
        {
        	System.out.println("You can't pick up the cat!! He ran away.");
        	return false;
        }
        else 
    	{
    		inventory.add(newItem);
            currentRoom.removeItem(item);
            System.out.println("Picked up: " + item);
            
            //hehe there might be a more efficient way than making a ton of variables but im too lazy to figure that out
            if(newItem.getDescription().equals("sanitizer"))
            {
            	hasSanitizer = true;
            	return false;
            }
            if(newItem.getDescription().equals("string"))
            {
            	hasString = true;
            	return false;
            }
            if(newItem.getDescription().equals("balloon"))
            {
            	hasBalloon = true;
            	return false;
            }
            if(newItem.getDescription().equals("facemask"))
            {
            	hasMask = true;
            	return false;
            }
            if(newItem.getDescription().equals("umbrella"))
            {
            	hasUmbrella = true;
            	return false;
            }
            if(newItem.getDescription().equals("chickennuggets"))
            {
            	hasChicken = true;
            	return false;
            }
            if(newItem.getDescription().equals("applepie"))
            {
            	hasPie = true;
            	return false;
            }
            if(newItem.getDescription().equals("sciFiBook"))
            {
            	hasBook = true;
            	return false;
            }
            if(newItem.getDescription().equals("cat"))
            {
            	hasCat = true;
            	return false;
            }
            return false;
    	}
    }
    
    //lists everything in the player's inventory
    private void printInventory() 
    {
    	if(inventory.size() > 0)
    	{
    		String output = "";
        	for (int i = 0; i < inventory.size(); i++) 
        	{
    			output += inventory.get(i).getDescription() + " ";
    		}
        	System.out.println("You are carrying:");
        	System.out.println(output);
    	}
    	else
    	{
    		System.out.println("You have nothing in your inventory.");
    	}
    	
	}

	// implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around Cedarmill on this overcast day.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private boolean goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return false;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if(nextRoom == null)
        {
        	 System.out.println("You can't go that way, stinky.");
        	 return false;
        }
        if(nextRoom == safeway && hasMask == false)
        {
        	System.out.println("Sorry, you can't go into Safeway without a mask. You're still at Dollar Tree.");
        	return false;
        }
        if(currentRoom == library && hasUmbrella == false && hasBook == true)
        {
        	System.out.println("Noo!! As you left the library, it started raining like crazy!! You had nothing to"
        			+ " protect your book with and it got all wet and ruined ): You lose.");
        	return true;
        }  
        else 
        {
        	currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            //if player goes into the construction site, that is where the cat is. if the player has all the right objects,
            //they can get the cat! 
            if(currentRoom == constructionSite && hasCat == false)
            {
            	if(hasBalloon == false)
            	{
            		if(hasPie == true)
                	{
                		System.out.println("Oh nooo, you tripped over a beam laying on the ground in the construction site!!"
                				+ " You dropped you're apple pie. Next time don't bring such a precious food to such a "
                				+ "dangerous place!! :O");
                		return true;
                	}
            		else if(hasString == true && hasChicken == true)
                	{
                		System.out.println("You see the cat and lure it towards you with the string. When the cat comes closer "
                				+ "you give it some tasty chicken. You have befreinded the cat :D");
                		cat = true;
                		return false;
                	}
                	else if(hasString == true)
                	{
                		System.out.println("You see the cat and lure it towards you with the string. But eventually the cat "
                				+ "gets bored and runs off. He looked pretty hungry, maybe next time give him something to eat?");
                		return false;
                	}
                	else if(hasChicken == true)
                	{
                		System.out.println("You see the cat and offer it some chicken, but it is too far away to notice. Maybe"
                				+ " next time get something to lure him closer :)");
                		return false;
                	}
                	else
                	{
                		System.out.println("You see the cat, but it runs away. You can't expect a cat to approach you without "
                				+ "some bribery :O");
                		return false;
                	}
            	}
            	else
            	{
            		System.out.println("Don't you know cats are TERRIFIED of balloons!!? The cat runs away instantly :(");
            		return false;
            	}
            }
            return false;
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
}
