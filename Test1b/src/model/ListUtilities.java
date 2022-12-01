package model;

import tests.Node;

public class ListUtilities<E> {

	public Node<E> task(Node<E> head, E e) {
		
		if (head == null) { // the case that input list is of size 0
			return null;
		}
		else { // case for list of all other sizes
			Node<E> current = head.getNext();
			Node<E> prev = head;

			while (current != null) { // traverse through list 

				if (current.getElement().equals(e)) { // if the elements are equal, remove current node, then set current to previous' new next
					prev.setNext(current.getNext());
					current.setNext(null);
					current = prev.getNext();
				}
				else { // if elements are not equal, both previous and current will move to next nodes
					prev = prev.getNext();
					current = current.getNext();
				}
			}

			if (head.getElement().equals(e)) { // since we did not address first element, check if head contains element we want to remove
				Node<E> temp = head;
				head = head.getNext();
				temp.setNext(null);
			}

			return head;
		}
	}
}
