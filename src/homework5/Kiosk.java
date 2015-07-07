/**
 * Samier Trabilsy
 * Student ID: 109839226
 * Homework #5
 * Thursday: R04
 * Gustavo Poscidonio
 * Mahsa Torkaman
 * @author Samier
 */
package homework5;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Kiosk class contains a main method that interacts with the user
 */
public class Kiosk {
	/**
	 * The main method presents a menu for the user to interact with. The menu has the following options:
	 * <br><br>
	 	(L) Ð Load the contents of a tree from a file. You should create a new Tree.
	 	<br><br>
        (P) Ð Prints the menu in a neat tabular format.
        <br><br>
        (S) Ð Starts a service session. Ask for the user's order, and print the order at the end.
        <br><br>
        (Q) Ð Terminates the program.
        <br>
	 * @param args
	 */
	public static void main(String[] args) {
		String menu = "\nMain menu:\n\nL) Load a Tree\nP) Print menu\nS) Start service\nQ) Quit\nChoice: ";
		Scanner input;
		String choice;
		char letter;
		Tree a = new Tree();
		do {
			System.out.print(menu);
			input = new Scanner(System.in);
		    choice = input.next();
		    letter = choice.charAt(0);
		    switch(Character.toUpperCase(letter)) {
		    
		    case('L'):
		    	System.out.print("\nEnter the name of the file: ");
		    	String fileName = input.next();
		    	try {
		    		Scanner reader = new Scanner(new File(fileName));
		    		String parentName, name, selection, message;
		    		int children;
		    		a = new Tree(new TreeNode(reader.nextLine(), reader.nextLine(), reader.nextLine()));
		    		while (reader.hasNext()) {
			    		parentName = reader.next();
			    		children = reader.nextInt();
			    		reader.nextLine();
			    		for(int i = 1; i <= children; i++) {
			    			name = reader.nextLine();
			    			selection = reader.nextLine();
			    			message = reader.nextLine();
			    			if (!a.addNode(name, selection, message, parentName)) {
			    				System.out.println("The tree cannot be built with this input file.");
			    				break;
			    			}
			    		}
		    		}
				} catch (IOException e) {
					System.out.println(e.getMessage());
					break;
				}
		    	
		    	System.out.println("\nMenu has been updated!");
		    	break;
		    
		    case('P'):
		    	if (a.getRoot() == null) {
		    		System.out.println("\nNo tree has been loaded. Cannot print menu.");
		    		break;
		    	}
		    	System.out.println("\nMenu:");
		    	System.out.print(String.format("%-16s%-60s%-7s", "Dining", "Selection", "Price")
				+ "\n-----------------------------------------------------------------------------------\n");
		    	a.printMenu("");
		    	break;
		    	
		    case('S'):
		    	if (a.getRoot() == null) {
		    		System.out.println("\nNo tree has been loaded. Cannot start service.");
		    		break;
		    	}
		    	System.out.println("\nHelp session starting...\n");
		    	a.beginSession("");
		    	break;
		    
		    case('Q'):
		    	System.out.println("\nKiosk shutting down...");
		    	System.exit(0);
		    }
	    	
		}while(letter!='Q');
	}

}
