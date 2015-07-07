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
		if (target == null && root.getLeft() != null) {
			target = new Tree(root.getLeft()).findNode(name);
		}
		if (target == null && root.getMiddle() != null) {
			target = new Tree(root.getMiddle()).findNode(name);
		}
		if (target == null && root.getRight() != null) {
			target = new Tree(root.getRight()).findNode(name);
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
			System.out.println("That parent doesn't exist.");
			return false;
		}
		if (parent.getLeft() == null)
			parent.setLeft(new TreeNode(name, selection, message));
		else if (parent.getMiddle() == null)
			parent.setMiddle(new TreeNode(name, selection, message));
		else if (parent.getRight() == null)
			parent.setRight(new TreeNode(name, selection, message));
		else {
			System.out.println("There are already 3 sub-options for this selection.");
			return false;
		}
		return true;
	}
	
	/**
	 * Prints the combination of the whole selection menu
	 * 
	 * @param parentInfo
	 */
	public void printMenu(String parentInfo) {
		//Check if node is a leaf
		if (root.getLeft() == null && root.getMiddle() == null && root.getRight() == null) {
			String[] info = parentInfo.split("@", 2);
			System.out.printf("%-16s%-60s%-7s\n", info[0], info[1].replaceAll("@", ",") + root.getSelection(), root.getMessage());
			return;
		}
		if (!root.getName().equals("root"))
			parentInfo += root.getSelection() + "@ "; //used @ to split to pieces of info
		if (root.getLeft() != null)
			new Tree(root.getLeft()).printMenu(parentInfo);
		if (root.getMiddle() != null)
			new Tree(root.getMiddle()).printMenu(parentInfo);
		if (root.getRight() != null)
			new Tree(root.getRight()).printMenu(parentInfo);
		
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
		if (root.getLeft() == null && root.getMiddle() == null && root.getRight() == null) {
			String[] info = parentInfo.split("@", 2);
			System.out.println("The order at " + info[0] + ":" + info[1].replaceAll("@", ",")
					+ root.getSelection() + " has been sent to the kitchen. "
					+ "Total amount would be " + root.getMessage());
			return;
		}
		
		System.out.println(root.getMessage());
		if (root.getLeft() != null)
			System.out.println("1 " + root.getLeft().getSelection());
		if (root.getMiddle() != null)
			System.out.println("2 " + root.getMiddle().getSelection());
		if (root.getRight() != null)
			System.out.println("3 " + root.getRight().getSelection());
		System.out.println("0 Exit Session");
		System.out.print("Choice: ");
		
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		System.out.println();
		if (!root.getName().equals("root"))
			parentInfo += root.getSelection() + "@ "; //used @ to split to pieces of info
		switch(choice) {
		case(1):
			new Tree(root.getLeft()).beginSession(parentInfo);
			break;
		case(2):
			new Tree(root.getMiddle()).beginSession(parentInfo);
			break;
		case(3):
			new Tree(root.getRight()).beginSession(parentInfo);
			break;
		default:
			return;
		}
	}
}
