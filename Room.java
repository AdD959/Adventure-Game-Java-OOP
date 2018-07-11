/*
Adam Cummings - Adventure Game
Last Modified: 08 January 2018
*/

import java.util.concurrent.ThreadLocalRandom;
import java.util.LinkedList;
import java.util.Iterator;

public class Room {

	private boolean north;
	private boolean south;
	private boolean east;
	private boolean west;
	private boolean itemTopLeft;
	private boolean itemTopRight;
	private boolean itemBottomLeft;
	private boolean itemBottomRight;
	private boolean itemTopLeftRight;
	private boolean itemBottomLeftRight;

	private boolean itemNW;
	private boolean itemNE;
	private boolean itemSW;
	private boolean itemSE;

	private int noOfItems;

	private String string1 = "---------";
	private String string2 = "|       |";
	private String string3 = "|       |";
	private String string4 = "|       ";
	private String string4b = "|";
	private String string5 = "|       |";
	private String string6 = "|       |";
	private String string7 = "---------";

	private String itemL = "|i      |";
	private String itemR = "|      i|";
	private String itemLR = "|i     i|";

	private String heroPresent = "|   H   ";
	private String heroPresentW = "    H   ";
	private boolean isHeroThere;

	private String monsterPresent = "|   M   |";
	private String deadMonster = "|   X   |";
	private boolean monsterdead;
	private boolean monster;
	private int monsterHealth;
	private boolean prevent;

	private String hatchPresent = "|  [/]  |";
	private String hatchUnlocked = "|  [ ]  |";
	private boolean hatch;

	private LinkedList<Item> RoomItems = new LinkedList<Item>();

	public Room() {
		randomDoors();
	}

	public boolean preventBug() {
		return prevent;
	}

	public void setPrevent(boolean set) {
		prevent = set;
	}

	public void openHatch() {
		hatchPresent = hatchUnlocked;
	}

	public boolean isMonsterDead() {
		return monsterdead;
	}

	public void killMonster() {
		monsterPresent = deadMonster;
		monsterdead = true;
	}

	public void damageMonster(int damage) {
		monsterHealth -= damage;
	}

	public int getMonsterHP() {
		return monsterHealth;
	}

	public void setMonsterHP(int health) {
		monsterHealth = health;
	}

	//Allows Items to visually be removed from Room
	public void removeItemString() {
		if (RoomItems.size() >= 0) {
			if (itemTopLeft == true) {
				itemTopLeft = false;
				string2 = "|       |";
				return;
			}
			else if (itemTopRight == true) {
				itemTopRight = false;
				string2 = "|       |";
				return;
			}
			else if (itemBottomLeft == true) {
				itemBottomLeft = false;
				string6 = "|       |";
				return;
			}
			else if (itemBottomRight == true) {
				itemBottomRight = false;
				string6 = "|       |";
				return;
			}
			else if (itemTopLeftRight == true) {
				itemTopLeftRight = itemTopLeft;
				itemTopLeftRight = false;
				itemTopLeft = true;
				string2 = "|i      |";
				return;
			}
			else if (itemBottomLeftRight == true) {
				itemBottomLeftRight = itemBottomRight;
				itemBottomLeftRight = false;
				itemBottomRight = true;
				string6 = "|      i|";
				return;
			}
		}
	}

	//Allows Items to be visually added to Room
	public void addItemString() {
		if (RoomItems.size() <= 4) {
			if (itemTopLeft == false && itemTopRight == true) {
				itemTopLeft = true;
				string2 = "|i     i|";
			}
			else if (itemTopLeft == false && itemTopRight == false && itemTopLeftRight == false) {
				itemTopLeft = true;
				string2 = "|i      |";
			}
			else if (itemTopRight == false && itemTopLeft == true) {
				itemTopRight = true;
				string2 = "|i     i|";
			}
			else if (itemBottomLeft == false && itemBottomRight == true) {
				itemBottomLeft = true;
				string2 = "|i     i|";
			}
			else if (itemBottomLeft == false && itemBottomRight == false && itemBottomLeftRight == false) {
				itemBottomLeft = true;
				string2 = "|i      |";
			}
			else if (itemBottomRight == false && itemBottomLeft == false) {
				itemBottomRight = true;
				string2 = "|      i|";
			}
			else if (itemBottomRight == false && itemBottomLeft == true) {
				itemBottomRight = true;
				string2 = "|i     i|";
			}
		}
	}


	public void seeItems() {
		int index = 0;
		if (RoomItems.size() > 0) {
			Iterator here = RoomItems.iterator();
			while (here.hasNext() == true) {
				System.out.print("(" + (index + 1) + ")" + " ");
				index++;
				System.out.print(here.next());
				System.out.println("");
			}
		}
		switch (RoomItems.size()) {
			case 0: System.out.print("\n\n\n\n");
				break;
			case 1: System.out.print("\n\n\n");
				break;
			case 2: System.out.print("\n\n");
				break;
			case 3: System.out.print("\n");
				break;
		}
		System.out.println("____________________________________");
	}

	//Finds an Item at a particular index
	public Item findItem(int number) {
		return RoomItems.get(number);
	}

	//Removes an Item at a particular index
	public void removeItem(int removeI) {
		RoomItems.remove(removeI);
	}


	//Checks if hatch is in room
	public boolean isHatchPresent() {
		return hatch;
	}

	//Allows warehouse class to set hatch
	public void setHatch(boolean setHatch) {
		hatch = setHatch;
	}

	//Allows display of hatch as string
	public void hatchToString() {
		if (hatch == true) {
			string5 = hatchPresent;
		}
	}



	//Checks if monster is in room
	public boolean isMonsterPresent() {
		return monster;
	}

	//Allows warehouse class to set monsters
	public void setMonster(boolean setit) {
		monster = setit;
	}

	//Allows the display of monster as string
	public void monsterToString() {
		if (monster == true) {
			string3 = monsterPresent;
		}
	}

	//Changes boolean values to indicate if an item is located in a particular area of a room
	public void changeItemStatus() {
		noOfItems = RoomItems.size();
		if (noOfItems > 0) {
			switch (noOfItems) {
				case 1: 
					int randomNum = ThreadLocalRandom.current().nextInt(0, 4);
					switch (randomNum) {
						case 0: itemTopLeft = true;
							break;
						case 1: itemTopRight = true;
							break;
						case 2: itemBottomLeft = true;
							break;
						case 3: itemBottomRight = true;
							break;
					}
					break;
				case 2: 
					int randomNum2 = ThreadLocalRandom.current().nextInt(0, 7);
					switch (randomNum2) {
						case 0: itemTopLeftRight = true;
							break;
						case 1: itemBottomLeftRight = true;
							break;
						case 3: itemTopLeft = true;
								itemBottomLeft = true;
							break;
						case 4: itemTopLeft = true;
								itemBottomRight = true;
							break;
						case 5: itemTopRight = true;
								itemBottomLeft = true;
							break;
						case 6: itemTopRight = true;
								itemBottomRight = true;
							break; 
					}
					break;
				case 3:
					int randomNum3 = ThreadLocalRandom.current().nextInt(0, 4);
					switch (randomNum3) {
						case 0: itemTopLeftRight = true;
								itemBottomLeft = true;
							break;
						case 1: itemTopLeftRight = true;
								itemBottomRight = true;
							break;
						case 2: itemBottomLeftRight = true;
								itemTopLeft = true;
							break;
						case 3: itemBottomLeftRight = true;
								itemTopRight = true;
							break;
					}
					break;
				case 4: itemBottomLeftRight = true;
						itemTopLeftRight = true;
					break;
			}
		}
	}

	//Converts boolean values of items to string
	public void itemToString() {
		if (isHeroThere == true) {
			if (itemTopLeft == true && itemTopRight == false) {
				string2 = itemL;
			}
			if (itemTopLeft == true && itemTopRight == true) {
				string2 = itemLR;
			}
			if (itemBottomLeft == true && itemBottomRight == true) {
				string6 = itemLR;
			}
			if ((itemTopRight == true && itemTopLeft == false)) {
				string2 = itemR;
			}
			if ((itemBottomLeft == true && itemBottomRight == false)) {
				string6 = itemL;
			}
			if ((itemBottomRight == true && itemBottomLeft == false)) {
				string6 = itemR;
			}
			if ((itemTopLeftRight == true)) {
				string2 = itemLR;
			}
			if ((itemBottomLeftRight == true)) {
				string6 = itemLR;
			}
		}
	}

	//Hides previously visited room
	public void hidePrevious() {
		string2 = "|       |";
		string6 = "|       |";
		string3 = "|       |";
		string5 = "|       |";
	}




	//adds item to room
	public void addItems(Item givenItem) {
		RoomItems.addFirst(givenItem);
	}

	//retrieves number of items in a particular room
	public int howManyItems() {
		return RoomItems.size();
	}

	//Currently used to display how many items are in a room (testing)
	public void showItems() {
		System.out.println(RoomItems.size());
	}


	//Places Hero in room
	public void enterHero() {
		if (west == true) {
			string4 = heroPresentW;
		}
		if (west == false) {
			string4 = heroPresent;
		}

		isHeroThere = true;
	}

	//Removes Hero from room
	public void exitHero() {
		if (string4 == heroPresentW) {
			string4 = "        ";
		}
		if (string4 == heroPresent) {
			string4 = "|       ";
		}
		isHeroThere = false;
	}

	//Allows warehouse class to check if Hero is in room
	public boolean isHeroHere() {
		return isHeroThere;
	}




	//Assigns Doors Randomly to Room Objects
	public void randomDoors() {

		while (noOfDoors() < 1) {

			double flip1 = Math.floor(Math.random() * 2);
			double flip2 = Math.floor(Math.random() * 2);
			double flip3 = Math.floor(Math.random() * 2);
			double flip4 = Math.floor(Math.random() * 2);
			
			if (flip1 == 1) {
				north = true;
			}

			if (flip2 == 1) {
				south = true;
			}

			if (flip3 == 1) {
				east = true;
			}

			if (flip4 == 1) {
				west = true;
			}
		}
	}


	//Sets Room Strings
	public void setRoomStrings() {
		if (north == true) {
			string1 = "---   ---";
		}
		if (west == true && isHeroThere == false) {
			string4 = "        ";
		}
		if (east == true) {
			string4b = " ";
		}
		if (south == true) {
			string7 = "---   ---";
		}
		if (isHeroThere == true && west == true) {
			string4 = "    H   ";
		}
		if (isHeroThere == true && west == false) {
			string4 = "|   H   ";
		}
	}

	//Allows warehouse class to find Room String
	public String getString(String num) {
		switch (num) {
			case "1": num = string1; break;
			case "2": num = string2; break;
			case "3": num = string3; break;
			case "4": num = string4; break;
			case "4b": num = string4b; break;
			case "5": num = string5; break;
			case "6": num = string6; break;
			case "7": num = string7; break;
		}
		return num;
	}

	//Set of Methods Used to Retrieve Boolean Values From Warehouse Class
	public boolean getNorth() {
		return north;
	}

	public boolean getSouth() {
		return south;
	}

	public boolean getEast() {
		return east;
	}

	public boolean getWest() {
		return west;
	}


	//Set of Methods Used to Change Boolean Values From Warehouse Class
	//For Linking Juxtaposed Doors
	public void setNorth() {
		north = true;
	}
	//For Linking Juxtaposed Doors
	public void setSouth() {
		south = true;
	}
	//For Linking Juxtaposed Doors
	public void setEast() {
		east = true;
	}
	//For Linking Juxtaposed Doors
	public void setWest() {
		west = true;
	}
	//For Closing External Wall Doors
	public void setTop() {
		north = false;
	}
	//For Closing External Wall Doors
	public void setBottom() {
		south = false;
	}
	//For Closing External Wall Doors
	public void setRight() {
		east = false;
	}
	//For Closing External Wall Doors
	public void setLeft() {
		west = false;
	}

	//Used to ensure each Room Object has at least one door
	//Referrenced from StackOverFlow
	public int noOfDoors() {
		int myInt1 = (north) ? 1 : 0;
		int myInt2 = (south) ? 1 : 0;
		int myInt3 = (east) ? 1 : 0;
		int myInt4 = (west) ? 1 : 0;

		myInt1 = myInt1 + myInt2 + myInt3 + myInt4;

		return myInt1;
	}

}
