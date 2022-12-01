package model;

import tests.Node;

public class ListUtilities {

	public ListUtilities() {}
	
	public Node<String> getAllPrefixes(Node<Integer> input, int m, int n) {
		Node<String> output = new Node<String>("", null); // create new head string node labeled output
		Node<String> outCurrent = output; // tracker for adding new nodes to String list
		
		for(int i = m; i <= n; i++) { // lengths increase from m to i (range also indicates number of string nodes)
			Node<Integer> current = input;
			String temp = "[";
			for (int j = 1; j <= i; j++) { // building string goes from beginning of integer list until i
				if (j == i) {
					temp += current.getElement() + "]";
				}
				else {
					temp += current.getElement() + ", ";
				}
				current = current.getNext();
			}	
			outCurrent.setElement(temp);
			if (i < n) { // does not create a new empty node on the last iteration
				outCurrent.setNext(new Node<String>("", null));
				outCurrent = outCurrent.getNext();
			}
		}
		return output;
	}
	
	
	public Node<Integer> getMergedChain(Node<Integer> left, Node<Integer> right) {
		Node<Integer> output = new Node<Integer>(null, null); // create new head integer node labeled output
		Node<Integer> currentR = right; // keep track of nodes in both right and left
		Node<Integer> currentL = left;
		Node<Integer> curr = output;
		int count = 0; // counter used to set last element to null at the end
		
		while (currentL != null || currentR != null) {
			if (currentL == null) {
				curr.setElement(currentR.getElement());
				currentR = currentR.getNext();
			}
			else if (currentR == null) {
				curr.setElement(currentL.getElement());
				currentL = currentL.getNext();
			}
			else if (currentL.getElement() <= currentR.getElement()) {
				curr.setElement(currentL.getElement());
				currentL = currentL.getNext();
			}
			else if (currentR.getElement() <= currentL.getElement()) {
				curr.setElement(currentR.getElement());
				currentR = currentR.getNext();
			}
			curr.setNext(new Node<Integer>(null, null));
			curr = curr.getNext();
			count ++;
		}
		
		if (output.getElement() == null) { // if no elements were added, the first node is set to null
			output = null;
		}
		else { // set last element to null
			curr = output;
			for (int i = 0; i < count-1; i++) {
				curr = curr.getNext();
			}
			curr.setNext(null);
		}
		return output;
	}
	
	public Node<Integer> getInterleavedArithmeticFibonacciSequences(int aStart, int cd, int aSize, int fSize) {
		Node<Integer> output = new Node<Integer>(1, null); // set output node that will be the head of the output
		Node<Integer> curr = output; // keep track of where we are in the list
		int element = 0;
		if (fSize == 0) { // the case where fibonacci sequence size is 0
			output = null;
		}
		else {
			for (int i = 1; i < fSize; i++) { // build fibonacci sequence first
				curr.setNext(new Node<Integer>(element+curr.getElement(), null));
				element = curr.getElement();
				curr = curr.getNext();
			}
		}
		curr = output; // current now points to the head of newly built fibonacci sequence
		element = aStart; // integer to keep track of arithmetic sequence
		if (aSize == 0) {
			output = curr; // if arithmetic sequence size is 0, output points back to head of the fibonacci sequence (which can also be null)
		}
		else {
			Node<Integer>temp = curr; // placeholder for fibonacci sequence
			output = new Node<Integer>(aStart, null); // since arithmetic sequence size > 0, output points to aStart (will be head of final output)
			curr = output;
			for (int i = 1; i < aSize; i++) { // temp keeps track of fibonacci sequence, curr and element keep track of arithmetic sequence
				element += cd;
				if (temp == null) {
					curr.setNext(new Node<Integer>(element, null));
					curr = curr.getNext();
				}
				else {
					curr.setNext(temp);
					curr = temp;
					temp = temp.getNext();
					curr.setNext(new Node<Integer>(element, temp));
					curr = curr.getNext();
				}
			}
		}
		return output;
	}
	
	public Node<String> getGroupedStrings(Node<String> input, int m, int n) {
		if (input == null) { // empty list is given
			return null;
		}
		else if (input.getNext() == null) { // list of only one node is given
			return input;
		}
		else { // list of size > 1
			Node<String> head1 = null;
			Node<String> head2 = null;
			Node<String> head3 = null;
			Node<String> curr1 = head1;
			Node<String> curr2 = head1;
			Node<String> curr3 = head1;
			
			Node<String> curr = input;
		
			/*
			 * Split the list into 3 separate lists, and have head nodes and current nodes pointing to each
			 * We should only need to loop through the input list once and update their next nodes accordingly
			 */
			while (curr != null) {
				if (curr.getElement().length() < m) {
					if (head1 == null) {
						head1 = new Node<String>(curr.getElement(), null);
						curr1 = head1;
					}
					else {
						curr1.setNext(new Node<String>(curr.getElement(), null));
						curr1 = curr1.getNext();
					}
				}
				else if (curr.getElement().length() < n) {
					if (head2 == null) {
						head2 = new Node<String>(curr.getElement(), null);
						curr2 = head2;
					}
					else {
						curr2.setNext(new Node<String>(curr.getElement(), null));
						curr2 = curr2.getNext();
					}
				}
				else {
					if (head3 == null) {
						head3 = new Node<String>(curr.getElement(), null);
						curr3 = head3;
					}
					else {
						curr3.setNext(new Node<String>(curr.getElement(), null));
						curr3 = curr3.getNext();
					}
				}
				curr = curr.getNext();
			}
			
			/*
			 * Now that we have 3 separate lists, we must link the end of 1 to 2 and the end of 2 to 3
			 * There are possibilities of any of the three lists to be null so we must check accordingly
			 */
			if (curr1 != null) { // the case where list 1 is not empty
				if (curr2 != null) { // the case where list 2 is also not empty, we can return the result (list 3 can be empty or not)
					curr1.setNext(head2);
					curr2.setNext(head3);
					return head1;
				}
				else { // the case where list 2 is empty, but list 1 is not: chain end of list 1 to list 3
					curr1.setNext(head3);
					return head1;
				}
			}
			else { // the case where list 1 is empty: check for list 2
				if (curr2 != null) { // the case where list 1 is empty but list 2 is not: chain end of list 2 to list 3 and return head of list 2
					curr.setNext(head3);
					return head2;
				}
				else { // the case where both list 1 and list 2 are empty: return head of list 3
					return head3;
				}
			}
		}
	}
}
