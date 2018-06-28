package com.algorhythms.stack.impl;

import com.algorhythms.stack.api.Stack;
import java.util.Iterator;

/**
 * Resizing Array Implementation of a Generic Stack. Dynamically doubles the size of the stack when 
 * the stack reaches at full capacity.
 * 
 * @author Raj Lolage
 *
 * @param <Item>
 */
public class ResizingArrayStack<Item> implements Stack<Item> {
	
	private Item[] data;
	private int topPointer = 0;
	
	@SuppressWarnings("unchecked")
	public ResizingArrayStack() {
		this.data = (Item[])new Object[1]; //Initialize with a capacity of 1, resizing when full.
	}
	
	@Override
	public void push(Item item) {
		
		if(topPointer == this.data.length - 1) {
			resizeArray(2 * this.data.length);
		}
		this.data[topPointer++] = item;
	}
	
	@Override
	public Item pop() {
		
		if(isEmpty()) {
			throw new UnsupportedOperationException("Stack is empty. Cannot Pop.");
		}
		
		Item popItem = this.data[--topPointer];
		this.data[topPointer] = null; //Avoid Loitering.
		
		if(topPointer > 0 && topPointer == data.length/4) {
			resizeArray(this.data.length/2);
		}
		
		return popItem;
	}
	
	@Override
	public Item peek() {
		if(isEmpty()) {
			throw new UnsupportedOperationException("Stack is empty. Cannot peek.");
		}
		int peekPointer = topPointer - 1;
		return this.data[peekPointer];
	}
	
	@Override
	public boolean isEmpty() {
		return (topPointer == 0);
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new StackIterator();
	}
	
	/*
	 * Resize the array with the given length.
	 */
	private void resizeArray(int resizeLength) {
		@SuppressWarnings("unchecked")
		Item[] tempData = (Item[])new Object[resizeLength];
		for(int j = 0; j < this.data.length; j++) {
			tempData[j] = this.data[j];
		}
		
		this.data = tempData;

	}
	
	/*
	 * StackIterator which allows iteration over Stack Items.
	 */
	private class StackIterator implements Iterator<Item> {
		private int i = topPointer - 1;
		
		@Override
		public boolean hasNext() {
			return (i >= 0);
		}

		@Override
		public Item next() {
			return data[i--];
		}
	}
}
