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
/**
 * The TreeNode class represents a single node in a multi-way tree
 */
public class TreeNode {
	private String name, selection, message;
	private TreeNode left, middle, right;
	
	/**
	 * Creates a new TreeNode with default (null) values
	 */
	public TreeNode() {
		name = "";
		selection = "";
		message = "";
		left = middle = right = null;
	}
	
	/**
	 * Creates a new TreeNode with specified values
	 * 
	 * @param name 
	 * @param selection 
	 * @param message 
	 */
	public TreeNode(String name, String selection, String message) {
		this.name = name;
		this.selection = selection;
		this.message = message;
	}
	
	/**
	 * Indicates whether this node is a leaf or not
	 * @return True, if the node is a leaf, false otherwise.
	 */
	public boolean isLeaf() {
		return (left == null && middle == null && right == null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getMiddle() {
		return middle;
	}

	public void setMiddle(TreeNode middle) {
		this.middle = middle;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}
}
