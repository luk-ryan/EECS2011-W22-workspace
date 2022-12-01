package model;

import tests.TreeNode;
import tests.SLLNode;

public class TreeUtilities<E> {

	/* Method 1: copyOf() */
	public TreeNode<E> copyOf(TreeNode<E> n) {
		TreeNode<E> result = new TreeNode<>(n.getElement());
		
		if (n.getChildren() != null) {
			SLLNode<TreeNode<E>> current = n.getChildren();
			
			while (current != null) {
				TreeNode<E> child = copyOf(current.getElement());
				
				result.addChild(child);
				child.setParent(result);
				
				current = current.getNext();
			}
			
		}
		
		return result;
	}
	
	
	/* Method 2: getPreOrderSeq() */

	public SLLNode<TreeNode<E>> getPreOrderSeq(TreeNode<E> n) {

		SLLNode<TreeNode<E>> head = new SLLNode<>(null, null);
		head = getPreOrderSeqHelper(n, head);
		return head;
	} 
	
	private SLLNode<TreeNode<E>> getPreOrderSeqHelper(TreeNode<E> n, SLLNode<TreeNode<E>> current) {
		SLLNode<TreeNode<E>> result = current;

		if (current.getElement() == null) {
			current.setElement(n);
		}
		else {
			SLLNode<TreeNode<E>> temp = new SLLNode<TreeNode<E>>(n, null);
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(temp);
		}
		
		SLLNode<TreeNode<E>> children = n.getChildren(); // head of list of children
		SLLNode<TreeNode<E>> currentChild = children; // head of list of children
		while (currentChild != null) {
			getPreOrderSeqHelper(currentChild.getElement(), result);
			currentChild = currentChild.getNext();
		}
		
		
		return result;
	}
}
