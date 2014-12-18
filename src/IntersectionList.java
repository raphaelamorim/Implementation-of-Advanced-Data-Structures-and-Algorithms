import java.util.ArrayList;
import java.util.List;


public class IntersectionList {
	
	private static Snc120030Node head1;
	private static Snc120030Node head2;
	
	
	public IntersectionList() {
		// TODO Auto-generated constructor stub
	}
	
	public static Snc120030Node createList(List<Integer> list) {
		Snc120030Node head = null;
		Snc120030Node next = null;
		 for (int i = list.size() - 1; i >= 0; i--) {
			Snc120030Node n = new Snc120030Node(list.get(i));
			n.setNext(next);
			next = n;
		}
		 head = next;
		
		return head;
	}
	
	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		list1.add(5);
		list1.add(8);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(6);
		list2.add(7);
		list2.add(10);
		list2.add(11);
		list2.add(12);
		head1 = createList(list1);
		head2 = createList(list2);
		Snc120030Node temp1 = head1;
		while (temp1.getData() != 5) {
			temp1 = temp1.getNext();
		}
		Snc120030Node temp2 = head2;
		while (temp2.getNext() != null) {
			temp2 = temp2.getNext();
		}
		temp2.setNext(temp1);
		System.out.print("list 1 :: ");
		printList(head1);
		System.out.print("list 2 :: ");
		printList(head2);
//		reverseList();
		System.out.println(findIntersect());
	}

	private static boolean findIntersect() {
		// TODO Auto-generated method stub
		if (head1.getData() == head2.getData()) {
			return true;
		}
		
		Snc120030Node temp1 = head1;
		Snc120030Node temp2 = head2;
		
		Snc120030Node current1 = head1;
		Snc120030Node prev1 = null;
		Snc120030Node next1 = null;
		Snc120030Node current2 = head2;
		Snc120030Node prev2 = null;
		Snc120030Node next2 = null;
		while (current1 != null || current2 != null) {
			
			if (current1 != null) {
				next1 = current1.getNext();
				current1.setNext(prev1);
				prev1 = current1;
				current1 = next1;
			}
			
			if (prev1.getData() == head2.getData()) {
				return true;
			}
			
			if (current2 != null) {
				next2 = current2.getNext();
				current2.setNext(prev2);
				prev2 = current2;
				current2 = next2;
			}
			
			if (prev2.getData() == head1.getData()) {
				return true;
			}
		}
		return false;
	}

	private static void reverseList() {
		// TODO Auto-generated method stub
		Snc120030Node current = head1;
		Snc120030Node prev = null;
		Snc120030Node next = null;
		while (current != null) {
			next = current.getNext();
			current.setNext(prev);
			prev = current;
			current = next;
			
		}
		head1 = prev;
		printList(head1);
	}
	
	private static void printList(Snc120030Node node) {
		Snc120030Node temp1 = node;
		while (temp1 != null) {
			System.out.print(temp1.getData() + "->");
			temp1 = temp1.getNext();
		}
		System.out.println("NULL");
		
	}

}
