
public class BSTNode {

	private BSTNode parent; // Parent of Node
	private BSTNode leftChild; // Left Child of Node
	private BSTNode rightChild; // Right Child of Node
	private NodeData data;  // Data stored in Node
	
	public BSTNode() {
		/*
		 * Creates a new BSTNode object in which all its instance variables have value null.
		 * This is used for leaf node.
		 */
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
		this.data = null;
	}
	
	public BSTNode(BSTNode newParent, BSTNode newLeftChild, BSTNode newRightChild, NodeData newData) {
		/*
		 * Creates a new BSTNode in which the instance variables take the values specified in the corresponding parameters.
		 */
		this.parent = newParent;
		this.leftChild = newLeftChild;
		this.rightChild = newRightChild;
		this.data = newData;
	}
	
	//Getter Methods
	
	public BSTNode getParent() {
		return this.parent;
	}
	
	public BSTNode getLeftChild() {
		return this.leftChild;
	}
	
	public BSTNode getRightChild() {
		return this.rightChild;
	}
	
	public NodeData getData() {
		return this.data;
	}
	
	//Setter Methods
	
	public void setParent(BSTNode newParent) {
		this.parent = newParent;
	}
	
	public void setLeftChild(BSTNode newLeftChild) {
		this.leftChild = newLeftChild;
	}
	
	public void setRightChild(BSTNode newRightChild) {
		this.rightChild = newRightChild;
	}
	
	public void setData(NodeData newData) {
		this.data = newData;
	}
	
	public boolean isLeaf() {
		/*
		 * returns true if this node is a leaf; returns false otherwise.
		 */
		if(this.leftChild == null && this.rightChild == null) {
			return true;
		}
		return false;
	}
	
}
