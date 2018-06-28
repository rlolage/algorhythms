package com.algorhythms.stack.api;

/**
 * Basic Stack interface.
 * 
 * @author Raj Lolage
 *
 * @param <Item>
 */
public interface Stack<Item> extends Iterable<Item>{

	/**
	 * Push an item into the stack.
	 * 
	 * @param item
	 */
	public void push(Item item);
	
	/**
	 * Pop an item from top of the stack.
	 * This will remove the item from the stack.
	 * @return Item
	 */
	public Item pop();
	
	/**
	 * Peek an item on top of the stack.
	 * This will not remove the item from the stack.
	 * @return Item
	 */
	public Item peek();
	
	/**
	 * Returns a boolean telling whether the stack is empty or not. 
	 * @return true if empty
	 */
	public boolean isEmpty();
}
