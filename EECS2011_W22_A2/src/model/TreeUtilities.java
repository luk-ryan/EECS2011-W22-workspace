package model;

import java.lang.reflect.Array;

import tests.SLLNode;
import tests.TreeNode;

public class TreeUtilities {
	
//	/* Method 1: getElementsOfRanks()*/
//	public SLLNode<Integer> getElementsOfRanks(TreeNode<Integer> n, int a, int b) {
//		/*
//		 * Step 1: Get a singly Linked List of all descendants of input n 
//		 *         then transfer it to a singly linked list of integers 
//		 *         from the list of descendants
//		 */
//		SLLNode<Integer> head = new SLLNode<Integer>(null, null);
//		SLLNode<TreeNode<Integer>> descendantList = descendants(n);
//		SLLNode<Integer> current = head;
//		
//		while (descendantList != null) {
//			current.setNext(new SLLNode<Integer>(descendantList.getElement().getElement(), null));
//			
//			current = current.getNext();
//			descendantList = descendantList.getNext();
//		}
//		head = head.getNext();
//		
//		/*
//		 * Step 2: Sort the new list of integers in non-descending order
//		 * 		   (Can call helper method below)
//		 */
//		sort(head);
//		
//		/*
//		 * Step 3: use inputs a and b return required indexes
//		 */
//		current = head;
//		int count = 0;
//		while (current != null) {
//			
//			if (count == a-1) {
//				head = current;
//			}
//			if (count == b-1) {
//				current.setNext(null);
//			}
//			count++;
//			current = current.getNext();
//		}
//		
//		return head;
//	}
//	
//	
//	
//	
//	/* Method 2: getStats() */
//	public TreeNode<String> getStats(TreeNode<Integer> n){
//		
//		TreeNode<String> root;
//		
//		root = statHelper(n, null, 1, n.getElement());
//
//		while (root.getParent() != null) {
//			root = root.getParent();
//		}
//		
//		return root;
//	}
//	
//	/*
//	 * Helper methods
//	 */
//	
//	private TreeNode<String> statHelper(TreeNode<Integer> n, TreeNode<String> root, int descendantCount, int sumCount) {
//		if (n.getChildren() == null) {
//			return new TreeNode<String>("Number of descendants: " + 1 + "; Sum of descendants: " + n.getElement());
//		}
//		else {
//			SLLNode<TreeNode<Integer>> current = n.getChildren();
//			TreeNode<String> currentTreeNode = new TreeNode<String>("");
//			root = new TreeNode<String>("");
//			while (current != null) {
//				currentTreeNode = statHelper(current.getElement(), currentTreeNode, descendantCount++, sumCount += current.getElement().getElement());
//				currentTreeNode.setParent(root);
//				root.addChild(currentTreeNode);
//				current = current.getNext();
//			}
//			root.setElement("Number of descendants: " + descendantCount + "; Sum of descendants: " + sumCount);
//			return currentTreeNode;
//		}
//	}
//	
//	private SLLNode<TreeNode<Integer>> descendants(TreeNode<Integer> n) {
//
//		SLLNode<TreeNode<Integer>> head = new SLLNode<>(null, null);
//		this.descendantsHelper(n, head);
//		head = head.getNext();
//		return head;
//	} 
//	
//	private void descendantsHelper(TreeNode<Integer> n, SLLNode<TreeNode<Integer>> current) {
//		if (n != null) {
//			current.setNext(new SLLNode<TreeNode<Integer>>(n, current.getNext()));
//			SLLNode<TreeNode<Integer>> temp = n.getChildren(); // head of list of children
//			while (temp != null) {
//				descendantsHelper(temp.getElement(), current.getNext());
//				temp = temp.getNext();
//			}
//		}
//	}
//	
//	private void sort(SLLNode<Integer> n) { // will be using bubble sort for SLL
//		SLLNode<Integer> current = n; // initialize 2 pointers to help with saving position of nodes
//		SLLNode<Integer> index; 
//		int temp; // place holder to swap elements
//		
//		while (current != null) {
//			index = current.getNext();
//			
//			while (index != null) {
//				if (current.getElement() > index.getElement()) {
//					temp = current.getElement();
//					current.setElement(index.getElement());
//					index.setElement(temp);
//				}
//				index = index.getNext();
//			}
//			current = current.getNext();
//		}
//	}
	
	/*
	 * Assumptions:
	 * 	+ lowerRank >= 1
	 * 	+ upperRank <= size of tree
	 * 	+ root != null
	 */
	public SLLNode<Integer> getElementsOfRanks(TreeNode<Integer> root, int lowerRank, int upperRank) {
		SLLNode<Integer> sortedList = getSortedListFrom(root);
		SLLNode<Integer> result = null;
		SLLNode<Integer> currentResult = null;
		
		int i = 1;
		SLLNode<Integer> current = sortedList;
		while(current != null) {
			
			if(lowerRank <= i && i <= upperRank) {
				SLLNode<Integer> n = new SLLNode<>(current.getElement(), null);
				if(result == null) {
					result = n;
					currentResult = result;
				}
				else {
					currentResult.setNext(n);
					currentResult = currentResult.getNext();
				}
			} 
			current = current.getNext();
			i ++;
		}
		
		return result;
	}
	
	private SLLNode<Integer> getSortedListFrom(TreeNode<Integer> root) {
		SLLNode<Integer> start = new SLLNode<>(null, null);
		SLLNode<Integer> sorrtedList = getSortedListFromHelper(root, start);
		return sorrtedList;
	}
	
	private SLLNode<Integer> getSortedListFromHelper(TreeNode<Integer> root, SLLNode<Integer> current) {
		SLLNode<Integer> result = null;
		if(current.getElement() == null) {
			current.setElement(root.getElement());
			result = current;
		}
		else {
			result = current;
			boolean inserted = false;
			Integer rootElement = root.getElement();
			SLLNode<Integer> n = new SLLNode<>(rootElement, null);
			SLLNode<Integer> prev = null;
			while(current != null && !inserted) {
				Integer currentElement = current.getElement();
				if(rootElement > currentElement) {
					prev = current;
					current = current.getNext();
				}
				else {
					if(prev == null) { /* root element becomes the min in the list */
						n.setNext(current);
						result = n;
					}
					else {
						n.setNext(prev.getNext());
						prev.setNext(n);
					}
					inserted = true;
				}
			}
			if(!inserted) { /* root element becomes the max in the list */
				prev.setNext(n);
			}
		} 
		SLLNode<TreeNode<Integer>> children = root.getChildren();
		SLLNode<TreeNode<Integer>> currentChild = children;
		while(currentChild != null) {
			result = getSortedListFromHelper(currentChild.getElement(), result);
			currentChild = currentChild.getNext();
		}
		return result;
	}

// Allow students to use charAt
//	result = new TreeNode<String>(String.format("Number of nodes: %d; Sum: %d", 1, n.getElement()));
	
	public TreeNode<String> getStats(TreeNode<Integer> n) {
		TreeNode<Integer> rootOfSizes = getSizes(n);
		TreeNode<Integer> rootOfSums = getSums(n);
		return getStatsHelper(rootOfSizes, rootOfSums);
	}
	
	private TreeNode<String> getStatsHelper(TreeNode<Integer> currentSize, TreeNode<Integer> currentSum) {
		String stat = String.format("Number of descendants: %d; Sum of descendants: %d", currentSize.getElement(), currentSum.getElement());
		TreeNode<String> result = new TreeNode<>(stat);
		
		if(currentSize.getChildren() != null) {
			SLLNode<TreeNode<Integer>> currentSizeChild = currentSize.getChildren();
			SLLNode<TreeNode<Integer>> currentSumChild = currentSum.getChildren();
			
			while(currentSizeChild != null) {
				TreeNode<String> statOfChild = getStatsHelper(currentSizeChild.getElement(), currentSumChild.getElement());
				 
				result.addChild(statOfChild);
				statOfChild.setParent(result);
						
				currentSizeChild = currentSizeChild.getNext();
				currentSumChild = currentSumChild.getNext();
			} 
		}
		
		return result;
	}
	
	private TreeNode<Integer> getSizes(TreeNode<Integer> n) {
		TreeNode<Integer> result = new TreeNode<>(1);
		if(n.getChildren() != null) { 
			SLLNode<TreeNode<Integer>> currentChild = n.getChildren();
			while(currentChild != null) {
				TreeNode<Integer> sizeOfChild = getSizes(currentChild.getElement());
				result.setElement(result.getElement() + sizeOfChild.getElement());
				result.addChild(sizeOfChild);
				sizeOfChild.setParent(result);
				currentChild = currentChild.getNext();
			}
		}
		return result;
	}
	
	private TreeNode<Integer> getSums(TreeNode<Integer> n) {
		TreeNode<Integer> result = new TreeNode<>(n.getElement());
		if(n.getChildren() != null) { 
			SLLNode<TreeNode<Integer>> currentChild = n.getChildren();
			while(currentChild != null) {
				TreeNode<Integer> sumOfChild = getSums(currentChild.getElement());
				result.setElement(result.getElement() + sumOfChild.getElement());
				result.addChild(sumOfChild);
				sumOfChild.setParent(result);
				currentChild = currentChild.getNext();
			}
		}
		return result;
	}
}
