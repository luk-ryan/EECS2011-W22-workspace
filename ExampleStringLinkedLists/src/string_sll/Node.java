package string_sll;

public class Node {
	private String element;
	private Node next;
	
	public Node(String e, Node n) {
		element = e;
		next = n;
	}
	
	public String getElement() {
		return element;
	} 
	
	public void setElement(String e) {
		element = e;
	}
	
	public void setNext(Node n) {
		next = n;
	}
	
	public Node getNext() {
		return next;
	}
}
