/*
Adam Cummings - Adventure Game
Last Modified: 08 January 2018
*/

import java.util.Scanner;
import java.io.*; 

/*
Class Item currently provides an automatically randomated Item.
It also has various methods for interacting with Warehouse and Room classes.
Currently it imports names.txt to find Item names.
*/
public class Item {

	private String name;
	private int size;
	private int damage;
	private Item next;
	private Scanner x;
	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private String g;
	private String h;
	private String i;
	private String j;




	//Currently randomly assigns an Item name form the names.txt file
	//Item is also assigned a random size and damage
	public Item() {
		double findName = Math.floor(Math.random() * 10);
		int foundName = (int) Math.round(findName);

		openFile();
		readFile();

		switch (foundName) {
			case 0: name = a;
				break;
			case 1: name = b;
				break;
			case 2: name = c;
				break;
			case 3: name = d;
				break;
			case 4: name = e;
				break;
			case 5: name = f;
				break;
			case 6: name = g;
				break;
			case 7: name = h;
				break;
			case 8: name = i;
				break;
			case 9: name = j;
				break;	}

		double findSize = Math.random() * 15;
		int foundSize = (int) Math.round(findSize);
		foundSize += 5;
		size = foundSize;

		double finddamage = Math.random() * 20;
		int founddamage = (int) Math.round(finddamage);
		damage = founddamage;
	}





	//Opens file
	public void openFile() {
		 try {
		 	x = new Scanner(new File("names.txt"));
		 }
		 catch(Exception e) {
		 	System.out.println("Could not find file.");
		 }
	}

	//Closes file
	public void closeFile() {
		x.close();
	}


	//Reads in names from file
	public void readFile() {
		while(x.hasNext()) {

			a = x.next();
			b = x.next();
			c = x.next();
			d = x.next();
			e = x.next();
			f = x.next();
			g = x.next();
			h = x.next();
			i = x.next();
			j = x.next();
		}
	}





	//Returns String containing information on Item 
	public String toString() {
		return name + " (Size:" + size + ", damage:" + damage + ")";
	}


	//Series of getter methods

	//Gets name
	public String getName() {
		return name;
	}

	//Gets size
	public int getSize() {
		return size;
	}

	//Gets damage
	public int getdamage() {
		return damage;
	}

	//Gets next Item
	public Item getNext() {
		return next;
	}

	//Gets value
	public int getValue() {
		return damage;
	}
}




