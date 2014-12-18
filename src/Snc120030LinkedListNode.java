

public class Snc120030LinkedListNode {
	
	private Snc120030Node head;
	private Snc120030Node prev;
	
	/**
	 * this method inserts data at the head of the linked list
	 * @param data
	 */
	public void addElement(int data) {
		Snc120030Node node = createNode(data);
		node.setNext(head);
		head = node;
	}
	
	/**
	 * this method inserts data at the tail of the linked list
	 * @param data
	 */
	public void addElementToTail(int data) {
		Snc120030Node node = createNode(data);
		if (head == null) {
			head = node;
		} else {
			prev.setNext(node);
		}
		prev = node;
	}
	
	/**
	 * this method creates a node with the data
	 * @param data
	 * @return
	 */
	private Snc120030Node createNode(int data) {
		Snc120030Node node = new Snc120030Node(data);
		return node;
	}
	
	public Snc120030Node getHead() {
		return head;
	}
	
	/**
	 * this method converts the number to linked list
	 * @param no
	 * @return
	 */
	public Snc120030Node convertToLinkedList(String no) {
		int length = no.length();
		for (int i = 0; i < length; i+=100) {
			String val = (String) no.subSequence(i, i+100); 
			addElement(Integer.parseInt(val));
		}
		return head;
	}

}
