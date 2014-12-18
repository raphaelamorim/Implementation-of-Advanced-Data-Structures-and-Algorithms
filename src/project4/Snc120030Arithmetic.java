package project4;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;


public class Snc120030Arithmetic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//enter the input and enter 0 to stop
		System.out.println("Enter the input(Enter 0 to stop)");
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		List<String> inputList = new ArrayList<String>();
		inputList.add("");
		while(!line.equals("0")) {
			String var = line.substring(2);
			inputList.add(var);
			line = in.nextLine();
		}
		
		
		//map to store input and output
		Map<String, Snc120030Node> nodeMap = new LinkedHashMap<String, Snc120030Node>();
//		Map<String, String> varMap = new LinkedHashMap<String, String>();
//		Map<String, Snc120030Node> outputMap = new LinkedHashMap<String, Snc120030Node>();
		/*for(String inputVar : inputList) {
			String regex = "(.)*(\\d)(.)*";
			if (inputVar.matches(regex)) {
				String no = inputVar.substring(2);
				Snc120030LinkedListNode linkedListNode = new Snc120030LinkedListNode();
				Snc120030Node head = linkedListNode.convertToLinkedList(no);
				nodeMap.put(inputVar.charAt(0) + "", head);
			} else {
				String no = inputVar.substring(2);
				varMap.put(inputVar.charAt(0) + "", no);
			}
			
			
		}*/
		
		
		//process input
		for (int i = 1; i < inputList.size(); i++) {
			String inputVar = inputList.get(i);
			String regex = "(.)*(\\d)(.)*";
			if (inputVar.matches(regex) && !inputVar.contains("?")) {
				//process input if it has numbers
				String no = inputVar.substring(2);
				Snc120030LinkedListNode linkedListNode = new Snc120030LinkedListNode();
				Snc120030Node head = linkedListNode.convertToLinkedList(no);
				nodeMap.put(inputVar.charAt(0) + "", head);
			} else if (inputVar.length() == 1) {
				//print the linked list
				int length = getLength(nodeMap.get(inputVar));
				if (length == 1) {
					System.out.print(nodeMap.get(inputVar).getData());
				} else {
//					printLinkedList(nodeMap.get(inputVar));
					printLinkedList(nodeMap,(inputVar));
				}
				System.out.println();
			} else {
				if (inputVar.contains("+")) {
					//process addition
					String add = inputVar.substring(2);
					String[] varArray = add.split("\\+");
					List<Snc120030Node> nodeList = new ArrayList<Snc120030Node>();
					nodeList.add(nodeMap.get(varArray[0]));
					nodeList.add(nodeMap.get(varArray[1]));
					Snc120030Node result = additon(nodeList);
					nodeMap.put(inputVar.charAt(0) + "", (result));
				} else if (inputVar.contains("-")) {
					//process subtraction
					String add = inputVar.substring(2);
					String[] varArray = add.split("-");
					Snc120030Node result = subtraction(nodeMap.get(varArray[0]), nodeMap.get(varArray[1]));
					nodeMap.put(inputVar.charAt(0) + "", (result));
				} else if (inputVar.contains("*")) {
					//process multiplication
					String add = inputVar.substring(2);
					String[] varArray = add.split("\\*");
					Snc120030Node result = multiplication(nodeMap.get(varArray[0]), nodeMap.get(varArray[1]));
					nodeMap.put(inputVar.charAt(0) + "", (result));
				} else if (inputVar.contains("^")) {
					//process power
					String add = inputVar.substring(2);
					String[] varArray = add.split("\\^");
					Snc120030Node result = power(nodeMap.get(varArray[0]), nodeMap.get(varArray[1]));
					nodeMap.put(inputVar.charAt(0) + "", (result));
				} else if (inputVar.contains("?")) {
					//process for loop
					String[] varArray = inputVar.split("\\?");
					int start = Integer.parseInt(varArray[1]);
					List<String> loopList = new ArrayList<String>();
					for (; start < i; start++) {
						loopList.add(inputList.get(start));
					}
					loopUntilZero(varArray[0], loopList, nodeMap);
//					nodeMap.put(inputVar.charAt(0) + "", result);
				}
			}
		}
		
		
		
		
	}

	/**
	 * This method performs the loop action - ?
	 * @param var
	 * @param loopList
	 * @param nodeMap
	 */
	private static void loopUntilZero(String var, List<String> loopList,
			Map<String, Snc120030Node> nodeMap) {
		// TODO Auto-generated method stub
		
		while (checkForZero(nodeMap.get(var))) {
			for (int i = 0; i < loopList.size(); i++) {
				String inputVar = loopList.get(i);
				String regex = "(.)*(\\d)(.)*";
				if (inputVar.matches(regex)) {
					//process numbers
					String no = inputVar.substring(2);
					Snc120030LinkedListNode linkedListNode = new Snc120030LinkedListNode();
					Snc120030Node head = linkedListNode.convertToLinkedList(no);
					nodeMap.put(inputVar.charAt(0) + "", head);
				} else if (inputVar.length() == 1) {
					//process numbers
					int length = getLength(nodeMap.get(inputVar));
					if (length == 1) {
						System.out.print(nodeMap.get(inputVar).getData());
					} else {
//						printLinkedList(nodeMap.get(inputVar));
						printLinkedList(nodeMap,(inputVar));
					}
					System.out.println();
				} else {
					if (inputVar.contains("+")) {
						//process addition
						String add = inputVar.substring(2);
						String[] varArray = add.split("\\+");
						List<Snc120030Node> nodeList = new ArrayList<Snc120030Node>();
						nodeList.add((nodeMap.get(varArray[0])));
						nodeList.add((nodeMap.get(varArray[1])));
						Snc120030Node result = additon(nodeList);
						nodeMap.put(inputVar.charAt(0) + "", (result));
					} else if (inputVar.contains("-")) {
						//process subtraction
						String add = inputVar.substring(2);
						String[] varArray = add.split("-");
						Snc120030Node result = subtraction((nodeMap.get(varArray[0])), (nodeMap.get(varArray[1])));
						nodeMap.put(inputVar.charAt(0) + "", (result));
					} else if (inputVar.contains("*")) {
						//process multiplication
						String add = inputVar.substring(2);
						String[] varArray = add.split("\\*");
						Snc120030Node result = multiplication((nodeMap.get(varArray[0])), (nodeMap.get(varArray[1])));
						nodeMap.put(inputVar.charAt(0) + "", (result));
					} else if (inputVar.contains("^")) {
						//process power
						String add = inputVar.substring(2);
						String[] varArray = add.split("^");
						Snc120030Node result = power((nodeMap.get(varArray[0])), (nodeMap.get(varArray[1])));
						nodeMap.put(inputVar.charAt(0) + "", (result));
					}
				}
			}
		}
	}
	
	/**
	 * checks whether the integer value of the node is 0.
	 * @param node
	 * @return
	 */
	private static boolean checkForZero(Snc120030Node node) {
		// TODO Auto-generated method stub
		//check if the node value is 0
		Snc120030Node temp = node;
		while(temp != null) {
			if (temp.getData() != 0) {
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}

	/**
	 * this method performs the power operation of a^b
	 * @param a
	 * @param b
	 * @return
	 */
	private static Snc120030Node power(Snc120030Node a, Snc120030Node b) {
		// TODO Auto-generated method stub
		
		if (!checkForZero(b)) {
			return new Snc120030Node(1);
		}
		
		Snc120030Node bTemp = b;
		Snc120030Node product = new Snc120030Node(1);
		Snc120030Node one = new Snc120030Node(1);
		while (checkForZero(bTemp)) {
			//process no * no
			product = multiplication(product, a);
			//subtract one from power value
			bTemp = subtraction(bTemp, one);
		}
		
		return product;
	}

	/**
	 * this method performs multiplication of a*b
	 * @param a
	 * @param b
	 * @return
	 */
	private static Snc120030Node multiplication(Snc120030Node a, Snc120030Node b) {
		// TODO Auto-generated method stub
		
		Snc120030Node aTemp = a;
		Snc120030Node bTemp = b;
		int level = 0;
		List<Snc120030Node> mulList = new ArrayList<Snc120030Node>();
		while (bTemp != null) {
			//process b node
			Snc120030LinkedListNode result = new Snc120030LinkedListNode();
			for (int j = 1; j <= level; j++) {
				result.addElementToTail(0);
			}			
			int bVal = bTemp.getData();
			int carry = 0;
			//process a node
			while (aTemp != null) {
				int aVal = aTemp.getData();
				int product = bVal * aVal;
				product = product + carry;
				int rem = product%10;
				//add rem to the linked list
				result.addElementToTail(rem);
				carry = product/10;
				aTemp = aTemp.getNext();
			}
			if (carry != 0) {
				//add if carry is not zero to linked list
				result.addElementToTail(carry);
			}
			bTemp = bTemp.getNext();
			mulList.add(result.getHead());
			//increment the level to add zeros to the tail
			level++;
			aTemp = a;
		}
		
		if (mulList.size() > 1) {
			//add the list of multiplied values
			return additon(mulList);
		} else {
			return mulList.get(0);
		}
	}
	

	/**
	 * this method performs subtraction of a-b
	 * @param a
	 * @param b
	 * @return
	 */
	private static Snc120030Node subtraction(Snc120030Node a, Snc120030Node b) {
		// TODO Auto-generated method stub
		List<Snc120030Node> nodeList = new ArrayList<Snc120030Node>();
		nodeList.add(a);
		nodeList.add(b);
		//make equal length for all nodes
		Snc120030Node[] subNode = makeEqualLength(nodeList);
		Snc120030Node first = subNode[0];
		Snc120030Node second = subNode[1];
				
		//validate if first is greater
		if(validateSubtract(first, second)){
			//subtract fist - second
			return subtractNumbers(first, second);
		} else {
			//if first node is smaller return 
			Snc120030Node node = new Snc120030Node(0);
			return node;
		}
		
	}

	/**
	 * this method checks for borrow in subtraction
	 * @param first
	 * @param second
	 * @return
	 */
	private static Snc120030Node subtractNumbers(Snc120030Node first, Snc120030Node second) {
		// TODO Auto-generated method stub
		
		Snc120030LinkedListNode result = new Snc120030LinkedListNode();
		Snc120030Node a = first;
		Snc120030Node b = second;
		boolean lesser = false;
		
		while (a != null) {
			int aVal = a.getData();
			//if thr is a borrow
			if (lesser && aVal != 0) {
				//decrement the value by one
				aVal--;
				lesser = false;
			}
			int bVal = b.getData();
			if (aVal >= bVal) {
				if (aVal == 0) {
					if (lesser) {
						//if node value is 0, then it is a borrow make it as 9
						int diff = 9 - bVal;
						result.addElementToTail(diff);
					} else {
						//subtract 0-0
						result.addElementToTail(0);
					}
				} else {
					//subtract a - b
					int diff = aVal - bVal;
					result.addElementToTail(diff);
					lesser = false;
				}
			} else {
				if (aVal == 0) {
					if (!lesser) {
						//if the node value is 0, and thr is no borrow
						int diff = 10 - bVal;
						result.addElementToTail(diff);
					} else {
						//if the node value is 0 and thr is a borrow
						int diff = 9 - bVal;
						result.addElementToTail(diff);
					}
				} else {
					//carry from borrow and sub 
					int diff = 10 + aVal - bVal;
					result.addElementToTail(diff);
				}
				lesser = true;
				
			}
			a = a.getNext();
			b = b.getNext();
		}
		
		return result.getHead();
	}

	/**
	 * this method validates whether a>b
	 * @param a
	 * @param b
	 * @return
	 */
	private static boolean validateSubtract(Snc120030Node a, Snc120030Node b) {
		// TODO Auto-generated method stub
		
		Stack<Integer> aStack = new Stack<Integer>();
		Stack<Integer> bStack = new Stack<Integer>();
		Snc120030Node aTemp = a;
		while(aTemp != null) {
			aStack.push(aTemp.getData());
			aTemp = aTemp.getNext();
		}
		Snc120030Node bTemp = b;
		while(bTemp != null) {
			bStack.push(bTemp.getData());
			bTemp = bTemp.getNext();
		}
		
		while(!aStack.isEmpty()) {
			int aValue = aStack.pop();
			int bValue = bStack.pop();
			if (aValue < bValue) {
				//if value is lesser return false
				return false;
			} else if (aValue > bValue) {
				//if value is lesser return true
				return true;
			}
		}
		
		return true;
	}

	/**
	 * this method prints the list using recursion.
	 * @param node
	 * @return
	 */
	private static boolean printLinkedList(Snc120030Node node) {
		// TODO Auto-generated method stub
		
		if (node != null) {
			boolean print = printLinkedList(node.getNext());
			if (node.getData() != 0 || print) {
				System.out.print(node.getData());
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * this method reverse the list and print it.
	 * @param nodeMap
	 * @param inputVar
	 * @return
	 */
	private static boolean printLinkedList(Map<String, Snc120030Node> nodeMap, String inputVar) {
		// TODO Auto-generated method stub
		
//		if (node != null) {
//			boolean print = printLinkedList(node.getNext());
//			if (node.getData() != 0 || print) {
//				System.out.print(node.getData());
//				return true;
//			} else {
//				return false;
//			}
//		}
//		
		//reverse the linked list
		Snc120030Node head = reverseLinkedList(nodeMap.get(inputVar));
		Snc120030Node temp = head;
		boolean print = false;
		//print the linked list
		while(temp != null) {
			if (temp.getData() != 0 || print) {
				System.out.print(temp.getData());
				print = true;
				temp = temp.getNext();
			} else {
				temp = temp.getNext();
			}
		}
		
		//reverse the linked lists
		Snc120030Node reverse = reverseLinkedList(head);
		nodeMap.put(inputVar, reverse);
		return false;
	}
	
	/**
	 * this method is to reverse the linked list.
	 * @param head
	 * @return
	 */
	private static Snc120030Node reverseLinkedList(Snc120030Node head) {
		Snc120030Node prev = null;
		Snc120030Node current = head;
		Snc120030Node next = null;
		
		while (current != null) {
			next = current.getNext();
			current.setNext(prev);
			prev = current;
			current = next;
			
		}
		
		return prev;
	}

	/**
	 * this method performs addition of a+b
	 * @param nodeList
	 * @return
	 */
	private static Snc120030Node additon(List<Snc120030Node> nodeList) {
		// TODO Auto-generated method stub
		//make equal size
		Snc120030Node[] additionList = makeEqualLength(nodeList);
		Snc120030LinkedListNode addResult = new Snc120030LinkedListNode();
		int carry = 0;
		Snc120030Node temp = additionList[0];
		
		while(temp != null) {
			int sum = carry;
			for (int i = 0; i < additionList.length; i++) {
				Snc120030Node node = additionList[i];
				//add each digit
				sum = sum + node.getData();
			}
			int rem = sum%10;
			addResult.addElementToTail(rem);
			carry = sum/10;
			for (int i = 0; i < additionList.length; i++) {
				Snc120030Node node = additionList[i];
				additionList[i] = node.getNext();
			}
			temp = additionList[0];
		}
		
		if (carry != 0) {
			//add carry to tail
			addResult.addElementToTail(carry);
		}
		return addResult.getHead();
	}
	
	/**
	 * this method makes the list of linked list as same size
	 * @param nodeList
	 * @return
	 */
	private static Snc120030Node[] makeEqualLength(List<Snc120030Node> nodeList) {
		// TODO Auto-generated method stub
		Snc120030Node[] additionList = new Snc120030Node[nodeList.size()];
		int maxLength = 0;
		//check for maximum length
		for (Snc120030Node node : nodeList) {
			int length = getLength(node);
			if (maxLength < length) {
				maxLength = length;
			}
		}
		
		for (int i = 0; i < nodeList.size(); i++) {
			Snc120030Node node = nodeList.get(i);
			int length = getLength(node);
			if (length != maxLength) {
				//pad zeros to front
				Snc120030Node padNodes = padZeros(node, maxLength - length);
				additionList[i] = padNodes;
			} else {
				additionList[i] = node;
			}
		}
		
		return additionList;
	}

	/**
	 * this method pad zeros to the linked list
	 * @param node
	 * @param i
	 * @return
	 */
	private static Snc120030Node padZeros(Snc120030Node node, int i) {
		// TODO Auto-generated method stub
		Snc120030Node head = node;
		Snc120030Node temp = node;
		while (temp.getNext() != null) {
			temp = temp.getNext();
		}
		//append zeros
		for (int j = 1; j <= i; j++) {
			Snc120030Node nodeTemp = new Snc120030Node(0);
			temp.setNext(nodeTemp);
			temp = nodeTemp;
		}
		return head;
	}

	/**
	 * this method returns the length of the linked list
	 * @param node
	 * @return
	 */
	private static int getLength(Snc120030Node node) {
		int count = 0;
		Snc120030Node temp = node;
		//get length
		while (temp != null) {
			temp = temp.getNext();
			count++;
		}
		return count;
	}

}
