public class Node {
 
	private int data;
	private Node left;
	private Node right;
	public int level;
	private int depth;
 
	public Node(int data) {
		this(data, null, null);
	}
 
	public Node(int data, Node left, Node right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
		if (left == null && right == null)
			setDepth(1);
		else if (left == null)
			setDepth(right.getDepth() + 1);
		else if (right == null)
			setDepth(left.getDepth() + 1);
		else
			setDepth(Math.max(left.getDepth(), right.getDepth()) + 1);
	}
 
	public int getData() {
		return data;
	}
 
	public void setData(int data) {
		this.data = data;
	}
 
	public Node getLeft() {
		return left;
	}
 
	public void setLeft(Node left) {
		this.left = left;
	}
 
	public Node getRight() {
		return right;
	}
 
	public void setRight(Node right) {
		this.right = right;
	}
 
	/**
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}
 
	/**
	 * @param depth
	 *            the depth to set
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}
 
	
 
	@Override
	public String toString() {
		return "Level " + level + ": " + data;
	}
 
}