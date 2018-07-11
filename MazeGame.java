/*
Adam Cummings - Adventure Game
Last Modified: 08 January 2018
*/

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;

public class MazeGame {

	private static char command;


	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		Warehouse warehouse1 = new Warehouse(2);
		
		warehouse1.closeExternal();
		warehouse1.syncRooms();
		warehouse1.heroEntersWarehouse();
		warehouse1.addAllItems(20);
		warehouse1.assignItemsRooms();
		warehouse1.assignItemBooleans();
		warehouse1.assignMonsters(5);
		warehouse1.assignHatch();

		warehouse1.setWarehouseString();
		warehouse1.printWarehouse();
		warehouse1.clearWarehouseString();
		System.out.println("__________'h': information__________");
		warehouse1.seeRoomItems();
		System.out.println("What would you like to do?");
	

		do {
			warehouse1.moveRoom(command);

			if (command == 'w' || 
				command == 'a' ||
				command == 's' ||
				command == 'd' ||
				command == 'p' ||
				command == 'l') {

				warehouse1.setWarehouseString();
				warehouse1.printWarehouse();
				warehouse1.clearWarehouseString();
				System.out.println("__________'h': information__________");
				warehouse1.seeRoomItems();
				warehouse1.checkHealth();
				warehouse1.attackMonster();
				warehouse1.showMonsterHP();
				warehouse1.checkHatch();
				warehouse1.escape();

				if (warehouse1.escape() == true) {
					break;
				}

				if (warehouse1.hero1.getHealth() < 1) {
					System.out.println("\n            You died! \n        Thanks for playing.");
					break;
				}
				

				System.out.println("Health: " + warehouse1.hero1.getHealth());
				if (command == 'p') {
					if (warehouse1.getSuccessTake() == 1) {
						System.out.println("Ok, Item taken.");
					}
					else {System.out.println("Not enough space in BackPack!");}
				}
				if (command == 'l') {
					if (warehouse1.hero1.spaceLeft() == 50) {
						System.out.println("Your BackPack is empty!");
					}
					else if (warehouse1.getSuccessDrop() == true) {
						System.out.println("Ok, Item dropped.");
					}
					else {System.out.println("Sorry, this room is full!");}
				}
				System.out.println("What would you like to do?");
			} 


		}
		while ((command = in.next().charAt(0)) != 'x');
	}
}