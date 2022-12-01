package model;

import java.lang.reflect.Array;

public class TreeUtilities<E> {
	
	/*
	 * Return the depth of node `n`.
	 */
	public int depth(TreeNode<E> n) {
		if(n.getParent() == null) { 
			/* depth of root is 0 */
			return 0;
		}
		else {
			return 1 + depth(n.getParent());
		}
	}
	
	/*
	 * Return the height of the subtree rooted at node `n`.
	 */
	public int height(TreeNode<E> n) {
		TreeNode<E>[] children = n.getChildren();
		if(children.length == 0) {
			/* height of the subtree rooted at an external node is 0 */
			return 0;
		}
		else {
			int max = 0;
			for(int i = 0; i < children.length; i ++) {
				int h = 1 + height(children[i]);
				max = h > max ? h : max;
			}
			return max;
		}
	}
	
	/*
	 * Return all ancestors of node `n` as an array.
	 */
	@SuppressWarnings("unchecked")
	public TreeNode<E>[] ancestors(TreeNode<E> n) {
		/*
		 * Exercise
		 */
		int size = this.numberOfAncestors(n);
		TreeNode<E>[] list = (TreeNode<E>[]) Array.newInstance(n.getClass(), size);
		this.ancestorsHelper(n, list, 0);
		return list;
	} 
	
	private void ancestorsHelper(TreeNode<E> n, TreeNode<E>[] list, int i) {
		if (n != null) {
			list[i] = n;
			ancestorsHelper(n.getParent(), list, i+1);
		}
	}
	
	private int numberOfAncestors(TreeNode<E> n) {
		if (n.getParent() == null) {
			return 1;
		}
		else {
			return 1 + numberOfAncestors(n.getParent());
		}
	}
	
	
	
	/*
	 * Return all descendants of node `n` as an array.
	 */
	@SuppressWarnings("unchecked")
	public TreeNode<E>[] descendants(TreeNode<E> n) {
		/*
		 * Exercise
		 */
		int size = this.numberOfDescendants(n);
		TreeNode<E>[] list = (TreeNode<E>[]) Array.newInstance(n.getClass(), size);
		this.descendantsHelper(n, list, 0);
		return list;
	} 
	
	private void descendantsHelper(TreeNode<E> n, TreeNode<E>[] list, int i) {
		/* 
		 * WARNING: i index is incorrect here, as each recursive call saves its own instance of variable i, leading some cases to 
		 * insert an element into the middle of the array !!!
		 * */
		
		if (n != null) {
			list[i] = n;
			for (int j = 0; j < n.getChildren().length; j++) {
				descendantsHelper(n.getChildren()[j], list, ++i);
			}
		}
	}
	
	private int numberOfDescendants(TreeNode<E> n) {
		int number = 1;
		if (n.getChildren().length == 0) {
			return 1;
		}
		else {
			for (int i = 0; i < n.getChildren().length; i++) {
				number += numberOfDescendants(n.getChildren()[i]);
			}
			return number;
		}
	}
}
