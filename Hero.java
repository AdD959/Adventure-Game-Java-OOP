/*
Adam Cummings - Adventure Game
Last Modified: 08 February 2018
*/

import java.util.LinkedList;
import java.util.Collections;
import java.util.Iterator;

public class Hero {

	private LinkedList<Item> backPack = new LinkedList<Item>();
	private int health = 10;


	//Displays Items in Backpack
	public void seeBackPack() {
		int index = 0;

		if (backPack.size() > 0) {
			Iterator here = backPack.iterator();
			while (here.hasNext() == true) {
				System.out.print("(" + (index + 1) + ")" + " ");
				index++;
				System.out.println(here.next());
				System.out.println("");
			}
			System.out.println("Total Damage of Items: " + totalValue());
			System.out.println("Space Left in BackPack: " + spaceLeft() + "\n");
			System.out.println("____________________________________");
			System.out.println("What would you like to do?");
		}
		else if (backPack.isEmpty()) {
			System.out.println("Your BackPack is Empty.");
			System.out.println("____________________________________");
			System.out.println("What would you like to do?");
		}
	}

	public int getHealth() {
		return health;
	}

	public int takeDamage(int Mdamage) {
		health -= Mdamage;
		return health;
	}

	//Finds how much space is left in the BackPack (Total Capacity 50)
	public int spaceLeft() {
		int u = backPack.size();
		int total = 0;
		if (backPack.isEmpty() || backPack == null) {
			total = 0;
		}
		else {
			for (int i = 0; i < u; i++) {
				Item s = backPack.get(i);
				total = total + s.getSize();
			}
		}
		total = 50 - total;
		return total;
	}

	//Transfers Items to backpack
	public void pickUp(Item add2pack) {
		backPack.addFirst(add2pack);
	}

	//Drops Item from BackPack
	public void drop(Item dropItem) {
		backPack.remove(dropItem);
	}

	public LinkedList getBackPack() {
		return backPack;
	}

	public Item findItem(int whichOne) {
		return backPack.get(whichOne);
	}

	public int totalValue() {
		int und = backPack.size();
		int total = 0;

		if (backPack.isEmpty() || backPack == null) {
			total = 0;
		}

		else {
			for (int i = 0; i < und; i++) {
				Item s = backPack.get(i);
				total = total + s.getValue();
			}
		}
		return total;
	}
}