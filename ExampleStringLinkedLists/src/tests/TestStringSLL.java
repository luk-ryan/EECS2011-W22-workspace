package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import string_sll.Node;
import string_sll.SinglyLinkedList;

public class TestStringSLL {

	@Test
	public void test_ChainOfNodes_Approach_01() {
		Node tom = new Node("Tom", null);
		Node mark = new Node("Mark", tom);
		Node alan = new Node("Alan", mark);
		
		assertTrue(alan.getNext() == mark);
		assertTrue(mark.getNext() == tom);
		assertTrue(alan.getNext().getNext() == tom);
		assertTrue(tom.getNext() == null);
		assertTrue(mark.getNext().getNext() == null);
		assertTrue(alan.getNext().getNext().getNext() == null);
		
		assertEquals("Alan", alan.getElement());
		assertEquals("Mark", mark.getElement());
		assertEquals("Mark", alan.getNext().getElement());
		assertEquals("Tom", tom.getElement());
		assertEquals("Tom", mark.getNext().getElement());
		assertEquals("Tom", alan.getNext().getNext().getElement());
	}
	
	@Test
	public void test_ChainOfNodes_Approach_02() {
		Node alan = new Node("Alan", null);
		Node mark = new Node("Mark", null);
		Node tom = new Node("Tom", null);
		alan.setNext(mark);
		mark.setNext(tom);
		
		assertTrue(alan.getNext() == mark);
		assertTrue(mark.getNext() == tom);
		assertTrue(alan.getNext().getNext() == tom);
		assertTrue(tom.getNext() == null);
		assertTrue(mark.getNext().getNext() == null);
		assertTrue(alan.getNext().getNext().getNext() == null);
		
		assertEquals("Alan", alan.getElement());
		assertEquals("Mark", mark.getElement());
		assertEquals("Mark", alan.getNext().getElement());
		assertEquals("Tom", tom.getElement());
		assertEquals("Tom", mark.getNext().getElement());
		assertEquals("Tom", alan.getNext().getNext().getElement());
	}
	
	/*
	 * Setting the head of a SLL to a chain of node.
	 */
	@Test
	public void testSLL_01() {
		Node tom = new Node("Tom", null);
		Node mark = new Node("Mark", tom);
		Node alan = new Node("Alan", mark);
		
		SinglyLinkedList list = new SinglyLinkedList();
		list.setHead(alan);
		
		assertTrue(tom == list.getTail());
		assertSame(tom, list.getTail());
		
		assertTrue(list.getSize() == 3);
		assertEquals("Alan", list.getFirst().getElement());
		assertEquals("Mark", list.getFirst().getNext().getElement());
		assertEquals("Tom", list.getFirst().getNext().getNext().getElement());
	}
	
	/*
	 * Gradually adding elements to a list by invoking SLL methods. 
	 * Exercise: contrast how the same SLL can be constructed using testSLL_01 vs. testSLL_02.
	 */
	@Test
	public void testSLL_02() {
		SinglyLinkedList list = new SinglyLinkedList();
		assertTrue(list.getSize() == 0);
		assertTrue(list.getFirst() == null);
		
		list.addFirst("Tom");
		list.addFirst("Mark");
		list.addFirst("Alan");
		assertTrue(list.getSize() == 3);
		assertEquals("Alan", list.getFirst().getElement());
		assertEquals("Mark", list.getFirst().getNext().getElement());
		assertEquals("Tom", list.getFirst().getNext().getNext().getElement());
	}
	
	@Test
	public void testSLL_addAt() {
		SinglyLinkedList list = new SinglyLinkedList();
		assertTrue(list.getSize() == 0);
		assertTrue(list.getFirst() == null);
		
		list.addFirst("Tom");
		list.addFirst("Mark");
		list.addFirst("Alan");
		assertTrue(list.getSize() == 3);
		
		list.addAt(0, "Suyeon"); 
		list.addAt(2, "Yuna");
		assertTrue(list.getSize() == 5);
		list.addAt(list.getSize(), "Heeyeon");
		assertTrue(list.getSize() == 6);
		assertEquals("Suyeon", list.getNodeAt(0).getElement());
		assertEquals("Alan", list.getNodeAt(1).getElement());
		assertEquals("Yuna", list.getNodeAt(2).getElement());
		assertEquals("Mark", list.getNodeAt(3).getElement());
		assertEquals("Tom", list.getNodeAt(4).getElement());
		assertEquals("Heeyeon", list.getNodeAt(5).getElement());
	}
	
	@Test
	public void testSLL_removeLast() {
		SinglyLinkedList list = new SinglyLinkedList();
		assertTrue(list.getSize() == 0);
		assertTrue(list.getFirst() == null);
		
		list.addFirst("Tom");
		list.addFirst("Mark");
		list.addFirst("Alan");
		assertTrue(list.getSize() == 3);
		
		list.removeLast();
		assertTrue(list.getSize() == 2);
		assertEquals("Alan", list.getNodeAt(0).getElement());
		assertEquals("Mark", list.getNodeAt(1).getElement());
		
		list.removeLast();
		assertTrue(list.getSize() == 1);
		assertEquals("Alan", list.getNodeAt(0).getElement());
		
		list.removeLast();
		assertTrue(list.getSize() == 0);
		assertNull(list.getFirst());
	}
}
