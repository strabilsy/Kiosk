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
/**
 * The TreeNode class represents a single node in a multi-way tree
 */
public class TreeNode {
	private String name, selection, message;
	private TreeNode[] children;
	public static final int NUMOFCHILDREN = 10;
	
	/**
	 * Creates a new TreeNode with default (null) values
	 */
	public TreeNode() {
		name = "";
		selection = "";
		message = "";
		children = new TreeNode[NUMOFCHILDREN];
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
		children = new TreeNode[NUMOFCHILDREN];
	}
	
	/**
	 * Indicates whether this node is a leaf or not
	 * @return True, if the node is a leaf, false otherwise.
	 */
	public boolean isLeaf() {
		boolean isNull = true;
		for (TreeNode child: children) {
			if (child != null)
				isNull = false;
		}
		return isNull;
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

	public TreeNode getChild(int i) {
		return children[i];
	}
	
	public void setChild(int i, TreeNode child) {
		children[i] = child;
	}

}
