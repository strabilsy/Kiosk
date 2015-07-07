/**
 * Samier Trabilsy
 * Student ID: 109839226
 * Homework #5
 * Thursday: R04
 * Gustavo Poscidonio
 * Mahsa Torkaman
 * @author Samier
 */
package homework5withExtraCred;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Tree class represents a multi-way tree
 */
public class Tree {
	private TreeNode root;
	
	/**
	 * Creates a new, empty Tree
	 */
	public Tree() {
		root = null;
	}
	
	/**
	 * Creates a new Tree with a specified root
	 * 
	 * @param root
	 */
	public Tree(TreeNode root) {
		this.root = root;
	}
	
	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	/**
	 * Searches the tree for a node with the specified name
	 * 
	 * @param name The name of the node to search for
	 * @return A reference to node if it exists in the tree, null otherwise
	 */
	public TreeNode findNode(String name) {
		TreeNode target = null;
		if (root.getName().equalsIgnoreCase(name))
			target = root;
		for (int i = 0; i < TreeNode.NUMOFCHILDREN; i++) {
			if (target == null && root.getChild(i) != null)
				target = new Tree(root.getChild(i)).findNode(name);
		}
		return target;
	}
	/**
	 * Adds a new node under the node with the given parent label name
	 * 
	 * @param name
	 * @param selection
	 * @param message
	 * @param parentName
	 * @return True, if a node is added, false otherwise
	 */
	public boolean addNode(String name, String selection, String message, String parentName) {
		TreeNode parent = findNode(parentName);
		if (parent == null) {
			System.out.println("\nThat parent doesn't exist.");
			return false;
		}
		
		for (int i = 0; i < TreeNode.NUMOFCHILDREN; i++) {
			if (parent.getChild(i) == null) {
				parent.setChild(i, new TreeNode(name, selection, message));
				return true;
			}
		}
		System.out.println("There are already 10 sub-options for this selection.");
		return false;
	}
	
	/**
	 * Prints the combination of the whole selection menu
	 * 
	 * @param parentInfo
	 */
	public void printMenu(String parentInfo) {
		//Check if node is a leaf
		boolean isLeaf = true;
		for (int i = 0; i < TreeNode.NUMOFCHILDREN; i++) {
			if (root.getChild(i) != null)
				isLeaf = false;
		}
		if (isLeaf) {
			String[] info = parentInfo.split("@", 2);
			System.out.printf("%-16s%-60s%-7s\n", info[0], info[1].replaceAll("@", ",") + root.getSelection(), root.getMessage());
			return;
		}
		if (!root.getName().equals("root"))
			parentInfo += root.getSelection() + "@ "; //used @ to split to pieces of info
		for (int i = 0; i < TreeNode.NUMOFCHILDREN; i++) {
			if (root.getChild(i) != null)
				new Tree(root.getChild(i)).printMenu(parentInfo);
		}
	}
	
	/**
	 * Initiates ordering service. Starting from the root of the tree, it prompts users for inputs; 
	 * chooses the branch of the tree that the user is interested in. 
	 * Prints the order and the price when a leaf is reached.
	 * 
	 * @param parentInfo
	 */
	public void beginSession(String parentInfo) {
		//Check if node is a leaf
		boolean isLeaf = true;
		for (int i = 0; i < TreeNode.NUMOFCHILDREN; i++) {
			if (root.getChild(i) != null)
				isLeaf = false;
		}
		if (isLeaf) {
			String[] info = parentInfo.split("@", 2);
			System.out.println("The order at " + info[0] + ":" + info[1].replaceAll("@", ",")
					+ root.getSelection() + " has been sent to the kitchen. "
					+ "Total amount would be " + root.getMessage());
			return;
		}
		
		System.out.println(root.getMessage());
		for (int i = 0; i < TreeNode.NUMOFCHILDREN; i++) {
			if (root.getChild(i) != null)
				System.out.println(i+1 + " " + root.getChild(i).getSelection());
		}
		System.out.println("0 Exit Session");
		System.out.print("Choice: ");
		
		Scanner input = new Scanner(System.in);
		int choice;
		try {
			choice = input.nextInt();
			System.out.println();
			if (!root.getName().equals("root"))
				parentInfo += root.getSelection() + "@ "; //used @ to split to pieces of info
			if (choice - 1 >= 0 && choice - 1 < TreeNode.NUMOFCHILDREN)
				new Tree(root.getChild(choice-1)).beginSession(parentInfo);
			else
				return;
		}catch(InputMismatchException e) {
			System.out.println("That isn't an option! Exiting session...");
		}
	}
}
