package model;

import java.lang.reflect.Array;

@SuppressWarnings("unchecked")

public class TreeNode<E> {
	private E element; /* data object */
	private TreeNode<E> parent; /* unique parent node */
	private TreeNode<E>[] children; /* list of child nodes */
	
	private final int MAX_NUM_CHILDREN = 10; /* fixed max */
	private int noc; /* number of child nodes */
	
	public TreeNode(E element) {
		this.element = element;
		this.parent = null;
		this.children = 
			(TreeNode<E>[]) Array.newInstance(this.getClass(), MAX_NUM_CHILDREN); // ClassCastException: this.children = (TreeNode<E>[]) new Object[MAX_NUM_CHILDREN];
		this.noc = 0;
	}
	
	public E getElement() {
		return this.element;
	}
	
	public void setElement(E element) {
		this.element = element;
	}
	
	public TreeNode<E> getParent() {
		return this.parent;
	}
	
	public void setParent(TreeNode<E> parent) {
		this.parent = parent;
	}
	
	public TreeNode<E>[] getChildren() {
		TreeNode<E>[] result = 
			(TreeNode<E>[]) Array.newInstance(this.getClass(), this.noc); // ClassCastException: this.children = (TreeNode<E>[]) new Object[MAX_NUM_CHILDREN];// ClassCastException: (TreeNode<E>[]) new Object[this.noc];
		for(int i = 0; i < this.noc; i ++) {
			result[i] = this.children[i];
		}
		/* length of the result matches the number of child nodes */
		return result;
	} 
	
	public void addChild(TreeNode<E> child) {
		this.children[this.noc] = child;
		this.noc ++;
	}
	
	public void removeParent() {
		this.parent = null;
	}
	/* Remove the child node stored at index i */
	public void removeChildAt(int i) {
		/*
		 * Exercise.
		 * Assumption: i is a valid index for array `this.children`.
		 */
		this.children[i].removeParent(); // remove reference to parent class before removing from array
		
		for (; i < noc - 1; i++) {
			this.children[i] = this.children[i+1];
		}
		this.children[noc-1] = null;
		noc--;
	}
}
