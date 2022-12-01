package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import generic_dll.DoublyLinkedList; 

public class TestGenericDLL_String {
	
	@Test
	public void test_String_DLL_Empty_List() {
		DoublyLinkedList<String> list = new DoublyLinkedList<>();
		assertTrue(list.getSize() == 0);
		assertTrue(list.getFirst() == null);
		assertTrue(list.getLast() == null);
	}

	@Test
	public void test_String_DLL_Insert_Front_End() {
		DoublyLinkedList<String> list = new DoublyLinkedList<>(); 
		list.addFirst("Mark");
		list.addFirst("Alan");
		
		assertTrue(list.getSize() == 2);
		assertEquals("Alan", list.getFirst().getElement());
		assertEquals("Mark", list.getFirst().getNext().getElement()); 
		
		list = new DoublyLinkedList<>(); 
		list.addLast("Mark");
		list.addLast("Alan");
		
		assertTrue(list.getSize() == 2);
		assertEquals("Alan", list.getLast().getElement());
		assertEquals("Mark", list.getLast().getPrev().getElement()); 
	}
	
	@Test
	public void test_String_DLL_addAt() {
		DoublyLinkedList<String> list = new DoublyLinkedList<>(); 
		list.addAt(0, "Alan");
		list.addAt(1, "Tom");
		list.addAt(1, "Mark");
		
		assertTrue(list.getSize() == 3);
		assertEquals("Alan", list.getFirst().getElement());
		assertEquals("Mark", list.getFirst().getNext().getElement()); 
		assertEquals("Tom", list.getFirst().getNext().getNext().getElement()); 
	}
	
	@Test
	public void test_String_DLL_Remove_Front_End() {
		DoublyLinkedList<String> list = new DoublyLinkedList<>(); 
		list.addFirst("Mark");
		list.addFirst("Alan"); 
		list.removeFirst();
		list.removeFirst(); 
		assertTrue(list.getSize() == 0);
		
		list = new DoublyLinkedList<>(); 
		list.addFirst("Mark");
		list.addFirst("Alan");
		list.removeLast();
		list.removeLast(); 
		assertTrue(list.getSize() == 0);
	}
	
	@Test
	public void test_String_DLL_removeAt() {
		DoublyLinkedList<String> list = new DoublyLinkedList<>(); 
		list.addFirst("Mark");
		list.addFirst("Alan"); 
		list.addFirst("Tom"); 
		assertTrue(list.getSize() == 3);
		list.removeAt(1);
		assertTrue(list.getSize() == 2);
		list.removeAt(0); 
		assertTrue(list.getSize() == 1);
		list.removeAt(0); 
		assertTrue(list.getSize() == 0);
	}
}
