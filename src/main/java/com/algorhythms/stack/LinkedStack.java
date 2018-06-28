package com.algorhythms.stack;

import java.util.Iterator;

/**
 * Linked List implementation of Generic stack. 
 * Does not require any resizing. 
 * Push and pop operations are constant time independent of the number of links.
 * 
 * @author Raj Lolage
 *
 * @param <Item>
 */
public class LinkedStack<Item> implements Stack<Item> {
	
	private Node first = null;
	private int size = 0;
	
	public LinkedStack() {}
	
	public void push(Item item) {
		Node oldFirst = this.first;
		this.first = new Node();
		this.first.item = item;
		this.first.next = oldFirst;
		this.size++;
	}
	
	public Item pop() {
		if(isEmpty()) {
			throw new UnsupportedOperationException("Stack is empty! You shall not pop");
		}
		
		Item item = this.first.item;
		this.first = this.first.next;
		this.size--;
		return item;
	}
	
	public Item peek() {
		if(isEmpty()) {
			throw new UnsupportedOperationException("Stack is empty! You shall not peek");
		}
		
		return this.first.item;
	}
	
	public boolean isEmpty() {
		return (this.size == 0);
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new LinkedStackIterator();
	}

	private class Node {
		Item item;
		Node next;
	}
	
	/*
	 * Stack iterator for traversing the linked list.
	 */
	private class LinkedStackIterator implements Iterator<Item> {
		
		private int i = size;
		private Node n = first;
		
		@Override
		public boolean hasNext() {
			return (i != 0);
		}

		@Override
		public Item next() {
			Item item = n.item;
			n = n.next;
			i--;
			return item;
		}
	}
}
