package model;

import tests.TreeNode;
import tests.SLLNode;

public class TreeUtilities<E> {

	public TreeNode<E> task2(TreeNode<E> n) {
		TreeNode<E> mirror = new TreeNode<E> (n.getElement());
		TreeNode<E> result = task2Helper(n, mirror);
		return result;
	}
	
	private TreeNode<E> task2Helper(TreeNode<E> n, TreeNode<E> mirror) {
		if (n.getChildren() != null) {
			SLLNode<TreeNode<E>> currentChild = n.getChildren();
			SLLNode<TreeNode<E>> temp = new SLLNode<TreeNode<E>>(new TreeNode<E>(currentChild.getElement().getElement()), null);
			currentChild = currentChild.getNext();
			
			while (currentChild != null) {
				SLLNode<TreeNode<E>> prev = currentChild;
				currentChild = currentChild.getNext();
				SLLNode<TreeNode<E>> temp2 = new SLLNode<TreeNode<E>>(new TreeNode<E>(prev.getElement().getElement()), null);
				temp2.setNext(temp);
				temp = temp2;
			}
			
			currentChild = n.getChildren();
			
			while (temp != null) {
				mirror.addChild(temp.getElement());
				temp.getElement().setParent(mirror);
				
				task2Helper(n.getChildren().getElement(), temp.getElement());
				
				currentChild = currentChild.getNext();
				temp = temp.getNext();
			}

			

			
		}
		return mirror;
	}
}
