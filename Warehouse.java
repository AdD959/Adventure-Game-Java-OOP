/*
Adam Cummings - Adventure Game
Last Modified: 08 February 2018
*/

import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;
import java.util.LinkedList;


public class Warehouse {
	
	private int size = 4;
	private String fullMaze;
	private int warehouseSize = size;
	private String completeWarehouse = "";
	private int randomNum;
	private int randomNum2;
	private int totalItems;
	private int whichItem;
	private int successTake;
	private boolean successDrop;
	private int deadMonsters = 0;
	private boolean gameOver;


	Room warehouse[][] = new Room[size][size];
	private LinkedList<Item> allItems = new LinkedList<Item>();
	Hero hero1 = new Hero();



	public Warehouse(int warehouseSize) {
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				Room roomInstance = new Room();
				warehouse[j][i] = roomInstance;
			}
		}
	}

	public boolean escape() {
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				if (warehouse[j][i].isHatchPresent() && deadMonsters == 5 && warehouse[j][i].isHeroHere()) {
					System.out.println("            Congratulations! \n             You Escaped!");
					gameOver = true;
				}
			}
		}
		return gameOver;
	}

	public void checkHatch() {
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				if (warehouse[j][i].isMonsterDead() == true && warehouse[j][i].isHeroHere() && warehouse[j][i].preventBug() == false) {
					deadMonsters++;
					warehouse[j][i].setPrevent(true);
				}
			}
		}

		System.out.println("Monsters Killed: " + deadMonsters);

		if (deadMonsters == 5) {
			for (int j = 0; j < size; j++) {
				for (int i = 0; i < size; i++) {
					if (warehouse[j][i].isHatchPresent() == true) {
						warehouse[j][i].openHatch();
					}
				}
			}
		}
	}

	public void showMonsterHP() {
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				if (warehouse[j][i].isMonsterPresent() == true && warehouse[j][i].isHeroHere()) {
					System.out.println("Monster Health: " + warehouse[j][i].getMonsterHP());
				}
			}
		}
	}

	public void attackMonster() {
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				if (warehouse[j][i].isMonsterPresent() == true && warehouse[j][i].isHeroHere() && warehouse[j][i].getMonsterHP() > 0) {
					warehouse[j][i].damageMonster(hero1.totalValue());
					if (warehouse[j][i].getMonsterHP() < 1 && warehouse[j][i].isMonsterDead() == false) {
						warehouse[j][i].killMonster();
					}
				}
			}
		}
	}

	public void checkHealth() {
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				if (warehouse[j][i].isMonsterPresent() == true && warehouse[j][i].isHeroHere() && warehouse[j][i].getMonsterHP() > 0) {
					int randomNum = ThreadLocalRandom.current().nextInt(1, 6);
					hero1.takeDamage(randomNum);
				}
			}
		}
	}

	public int getSuccessTake() {
		return successTake;
	}

	public boolean getSuccessDrop() {
		return successDrop;
	}

	public int getWhichItem() {
		return whichItem;
	}

	public void seeRoomItems() {
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				if (warehouse[j][i].isHeroHere() == true) {
					System.out.println("Items in Room:");
					warehouse[j][i].seeItems();
				}
			}
		}
	}

	public int howManyItemsInRoom() {

		int a = 0;
		int b = 0;

		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				if (warehouse[j][i].isHeroHere() == true) {
					a = j;
					b = i;
				}
			}
		}

		return warehouse[a][b].howManyItems();
	}




	public void assignHatch() {
		Double assignCol = Math.floor(Math.random() * (size));
		Double assignRow = Math.floor(Math.random() * (size));
		int j = assignCol.intValue();
		int i = assignRow.intValue();
		warehouse[j][i].setHatch(true);
	}

	//Assigns chosen number of monsters
	public void assignMonsters(int monsternumbers) {
		int monsterHealthInc = 10;
		int numbers = 0;
		while (numbers < monsternumbers) {
			Double assignCol = Math.floor(Math.random() * (size));
			Double assignRow = Math.floor(Math.random() * (size));
			int j = assignCol.intValue();
			int i = assignRow.intValue();
			if (warehouse[j][i].isMonsterPresent() == true) {
				continue;
			}
			else {
				warehouse[j][i].setMonster(true);
				warehouse[j][i].setMonsterHP(monsterHealthInc);
				monsterHealthInc += 10;
				numbers++;
			}
		}
	}

	//Assigns all rooms random items
	public void assignItemBooleans() {
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				warehouse[j][i].changeItemStatus();
			}
		}
	}

	//Assigns items to linkedlist AllItems
	public void addAllItems(int itemNo) {

		totalItems = itemNo;

		for (int j = 0; j < itemNo; j++) {
			Item randomItem = new Item();
			allItems.add(randomItem);
			randomItem.closeFile();
		}
	}

	//Assigns random rooms items
	public void assignItemsRooms() {
		while (allItems.size() > 0) {
			Double assignCol = Math.floor(Math.random() * (size));
			Double assignRow = Math.floor(Math.random() * (size));
			int j = assignCol.intValue();
			int i = assignRow.intValue();

			if (warehouse[j][i].howManyItems() < 4) {
				warehouse[j][i].addItems(allItems.getFirst());
				allItems.removeFirst();
			}
			else {continue;}
		}
	}



	//User prompt produces the follows responses:
	public void moveRoom(char newCommand) {
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {

				if (warehouse[j][i].isHeroHere()) {
					warehouse[j][i].itemToString();
					try {
						switch (newCommand) {
							case '\u0000':
								return;
							case 'w': 
								if (warehouse[j-1][i].getSouth() == true) {
									
									warehouse[j-1][i].enterHero();
									warehouse[j][i].exitHero();
									warehouse[j-1][i].showItems();
									warehouse[j-1][i].itemToString();
									warehouse[j-1][i].monsterToString();
									warehouse[j-1][i].hatchToString();
									warehouse[j][i].hidePrevious();
								}
								else {System.out.println("Can't go that way!");}
								return;
							case 's':
								if (warehouse[j+1][i].getNorth() == true) {
									warehouse[j+1][i].enterHero();
									warehouse[j][i].exitHero();
									warehouse[j+1][i].showItems();
									warehouse[j+1][i].itemToString();
									warehouse[j+1][i].monsterToString();
									warehouse[j+1][i].hatchToString();
									warehouse[j][i].hidePrevious();
								}
								else {System.out.println("Can't go that way!");}
								return;
							case 'd':
								if (warehouse[j][i+1].getWest() == true) {
									warehouse[j][i+1].enterHero();
									warehouse[j][i].exitHero();
									warehouse[j][i+1].showItems();
									warehouse[j][i+1].itemToString();
									warehouse[j][i+1].monsterToString();
									warehouse[j][i+1].hatchToString();
									warehouse[j][i].hidePrevious();
								}
								else {System.out.println("Can't go that way!");}
								return;
							case 'a':
								if (warehouse[j][i-1].getEast() == true) {
									warehouse[j][i-1].enterHero();
									warehouse[j][i].exitHero();
									warehouse[j][i-1].showItems();
									warehouse[j][i-1].itemToString();
									warehouse[j][i-1].monsterToString();
									warehouse[j][i-1].hatchToString();
									warehouse[j][i].hidePrevious();
								}
								else {System.out.println("Can't go that way!");}
								return;
							case 'p':
									if (warehouse[j][i].howManyItems() > 0) {
										System.out.println("Which Item would you like to pick up?");
										Scanner in = new Scanner(System.in);
										whichItem = in.nextInt() - 1;
										if (hero1.spaceLeft() - warehouse[j][i].findItem(whichItem).getSize() > 0) {
											hero1.pickUp(warehouse[j][i].findItem(whichItem));
											warehouse[j][i].removeItem(whichItem);
											warehouse[j][i].removeItemString();
											warehouse[j][i].itemToString();
											successTake = 1;
										}
									}
									else {
										if (warehouse[j][i].howManyItems() == 0) {
											successTake = 2;
										}
									}
								return;
							case 'l': 
								if (warehouse[j][i].howManyItems() < 4 && hero1.spaceLeft() != 50) {
									System.out.println("Ok. Here are the items you can drop:");
									hero1.seeBackPack();
									System.out.print("Which Item would you like to drop? ");
									Scanner in = new Scanner(System.in);
									try {
										whichItem = in.nextInt() - 1;
										warehouse[j][i].addItems(hero1.findItem(whichItem));
										hero1.drop(hero1.findItem(whichItem));
										System.out.print("Ok. Item dropped. \n");
										warehouse[j][i].addItemString();
										warehouse[j][i].itemToString();
										successDrop = true;
									}
									catch(Exception e) {}
								}
								else {
									successDrop = false;
								}
								return;
							case 'h':
								System.out.println("GAME RULES:\n 1. Defeat all 5 monsters hidden in the warehouse \n 2. Collect items that increase your damage and health\n 3. Escape the Warehouse when all monsters are defeated\n\nCOMMANDS: \n 'w,a,s,d': directional movement keys\n 'i': check inventory \n 'p': pick up an item\n 'l': drop an item\n 'e': escape through the hatch\n 'x': exit game\nWhat would you like to do?");
								return;
							case 'i':
								System.out.println("Inventory:");
								hero1.seeBackPack();
								return;
							default: System.out.println("Please enter an appropriate command.");
						}
					}
					catch (IndexOutOfBoundsException e) {
						System.out.println("Can't go that way!catch");
					}
				}
			}
		}
	}

	//for testing purposes
	// public void checkBooleans() {
	// 	for (int j = 0; j < size; j++) {
	// 		for (int i = 0; i < size; i++) {
	// 			System.out.println("Row: " + (j + 1) + " Column: " + (i + 1) +  " " + warehouse[j][i].isHeroHere());
	// 		}
	// 	}
	// }





	//Places hero in random location in warehouse
	public void heroEntersWarehouse() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, 4);
		int randomNum2 = ThreadLocalRandom.current().nextInt(0, 4);
		warehouse[randomNum][randomNum2].enterHero();
	}


		



	//Opens New Door when Neighbouring Room has a Touching Door
	public void syncRooms() { 
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				if (j != 0) {
					if (warehouse[j][i].getNorth() == true) {
						warehouse[j-1][i].setSouth();
					}
				}
				if (j != (size-1)) {
					if (warehouse[j][i].getSouth() == true) {
						warehouse[j+1][i].setNorth();
					}
				}
				if (i != 0) {
					if (warehouse[j][i].getWest() == true) {
						warehouse[j][i-1].setEast();
					}
				}
				if (i != (size-1) || i == 1) {
					if (warehouse[j][i].getEast() == true) {
						warehouse[j][i+1].setWest();
					}
				}
			}
		}
	}

	//Closes Doors Located on External Walls
	public void closeExternal() {
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				if (i == 0) {
					warehouse[j][0].setLeft();
				}
				if (j == 0) {
					warehouse[j][i].setTop();
				}
				if (i == (size-1)) {
					warehouse[j][i].setRight();
				}
				if (j == (size-1)) {
					warehouse[j][i].setBottom();
				}
			}
		}
	}


	//Produces full warehouse string to be displayed on command prompt
	public void setWarehouseString() {
		for (int j = 0; j < size; j++) {

			String final1 = new String();
			String final2 = new String();
			String final3 = new String();
			String final4 = new String();
			String final5 = new String();
			String final6 = new String();
			String final7 = new String();

			for (int i = 0; i < size; i++) {

				warehouse[j][i].setRoomStrings();
				String N = warehouse[j][i].getString("1");
				String two = warehouse[j][i].getString("2");
				String three = warehouse[j][i].getString("3");
				String W = warehouse[j][i].getString("4");
				String E = warehouse[j][i].getString("4b");
				String five = warehouse[j][i].getString("5");
				String six = warehouse[j][i].getString("6");
				String S = warehouse[j][i].getString("7");

				final1 += N;
				final2 += two;
				final3 += three;
				final4 += (W + E);
				final5 += five;
				final6 += six;
				final7 += S;
			}

			completeWarehouse += final1 + "\n" 
			+ final2 + "\n" 
			+ final3 + "\n" 
			+ final4 + "\n" 
			+ final5 + "\n"
			+ final6 + "\n" 
			+ final7 + "\n";
		}
	}

	public void clearWarehouseString() {
		completeWarehouse = "";
	}



	//Prints warehouse
	public void printWarehouse() {
		System.out.println(completeWarehouse);
	}
}