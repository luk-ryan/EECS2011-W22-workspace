package model;

import tests.Node;

public class ListUtilities<E> {

	
	/*
	 * Accessors
	 */
	public Node<E> reverseOf(Node<E> input) {
		if (input == null) { // special case that input list is empty
			return null;
		}
		else { // if not empty list
			Node<E> output = new Node<>(input.getElement(), null); // create new node that will be the head of output
			Node<E> current = input.getNext(); // keeps track of input list
			while (current != null) { 
				Node<E> temp = output; // keeps track of next node of the new list every iteration
				output = new Node<>(current.getElement(), temp);
				current = current.getNext();
			}
			return output;
		}
	}
	
	public Node<E> copyOf(Node<E> input) {
		if (input == null) { // special case that input list is empty
			return null;
		}
		else { // if not empty list
			Node<E> output = new Node<>(input.getElement(), null); // create new node that will be the head of output
			Node<E> current = input.getNext(); // keeps track of input list
			Node<E> temp = output; // keeps track of next node of the new list every iteration
			while (current != null) { 
				temp.setNext(new Node<>(current.getElement(), null)); 
				temp = temp.getNext(); 
				current = current.getNext();
			}
			return output;
		}
	}
	
	public int getSize(Node<E> input) { // helper method to get the size of input list
		int size = 0;
		Node<E> current = input;
		while(current != null) { /* loop exists when current == null */
			size ++;
			current = current.getNext();
		}
		return size; 
	}
	
	public Node<E> removeNthFromEnd(Node<E> input, int n) { // assuming n is <= size
		int size = getSize(input); // use getSize helper method created above
		
		if (size - n == 0) {
			Node<E> temp = input;
			input = input.getNext();
			temp.setNext(null);
			return input;
		}
		else {
			Node<E> prev = input; // will be the previous node of the one we want to remove
			for (int i = 1; i < size-n; i++) {
				prev = prev.getNext();
			}
			Node<E> temp = prev.getNext(); // this is the node we want to remove
			prev.setNext(temp.getNext());
			temp.setNext(null);
			return input;
		}
		
	}
	/*
	 * Mutators
	 */
	
}
