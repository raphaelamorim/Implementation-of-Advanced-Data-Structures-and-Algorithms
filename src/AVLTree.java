import java.util.LinkedList;
import java.util.Queue;
 
public class AVLTree {
	Node root;
 
	public AVLTree() {
		root = null;
	}
 
	public Node Maximum() {
		Node local = root;
		if (local == null)
			return null;
		while (local.getRight() != null)
			local = local.getRight();
		return local;
	}
 
	public Node Minimum() {
		Node local = root;
		if (local == null)
			return null;
		while (local.getLeft() != null) {
			local = local.getLeft();
		}
		return local;
	}
 
	private int depth(Node node) {
		if (node == null)
			return 0;
		return node.getDepth();
		// 1 + Math.max(depth(node.getLeft()), depth(node.getRight()));
	}
 
	public Node insert(int data) {
		root = insert(root, data);
		switch (balanceNumber(root)) {
		case 1:
			root = rotateLeft(root);
			break;
		case -1:
			root = rotateRight(root);
			break;
		default:
			break;
		}
		return root;
	}
 
	public Node insert(Node node, int data) {
		if (node == null)
			return new Node(data);
		if (node.getData()> data) {
			node = new Node(node.getData(), insert(node.getLeft(), data),
					node.getRight());
			// node.setLeft(insert(node.getLeft(), data));
		} else if (node.getData() < data) {
			// node.setRight(insert(node.getRight(), data));
			node = new Node(node.getData(), node.getLeft(), insert(
					node.getRight(), data));
		}
		// After insert the new node, check and rebalance the current node if
		// necessary.
		switch (balanceNumber(node)) {
		case 1:
			node = rotateLeft(node);
			break;
		case -1:
			node = rotateRight(node);
			break;
		default:
			return node;
		}
		return node;
	}
 
	private int balanceNumber(Node node) {
		int L = depth(node.getLeft());
		int R = depth(node.getRight());
		if (L - R >= 2)
			return -1;
		else if (L - R <= -2)
			return 1;
		return 0;
	}
 
	private Node rotateLeft(Node node) {
		Node q = node;
		Node p = q.getRight();
		Node c = q.getLeft();
		Node a = p.getLeft();
		Node b = p.getRight();
		q = new Node(q.getData(), c, a);
		p = new Node(p.getData(), q, b);
		return p;
	}
 
	private Node rotateRight(Node node) {
		Node q = node;
		Node p = q.getLeft();
		Node c = q.getRight();
		Node a = p.getLeft();
		Node b = p.getRight();
		q = new Node(q.getData(), b, c);
		p = new Node(p.getData(), a, q);
		return p;
	}
 
	public boolean search(int data) {
		Node local = root;
		while (local != null) {
			if (local.getData()== data)
				return true;
			else if (local.getData() > data)
				local = local.getLeft();
			else
				local = local.getRight();
		}
		return false;
	}
 
	public String toString() {
		return root.toString();
	}
 
	public void PrintTree() {
		root.level = 0;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			System.out.println(node);
			int level = node.level;
			Node left = node.getLeft();
			Node right = node.getRight();
			if (left != null) {
				left.level = level + 1;
				queue.add(left);
			}
			if (right != null) {
				right.level = level + 1;
				queue.add(right);
			}
		}
	}
}