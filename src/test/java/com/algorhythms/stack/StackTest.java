package com.algorhythms.stack;

import com.algorhythms.stack.Stack;
import com.algorhythms.stack.LinkedStack;

import java.util.Iterator;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests for Stack.
 */
public class StackTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public StackTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(StackTest.class);
    }

    /**
     * Test single push.
     */
    public void testSinglePush() {
        Stack<String> linkedStack = new LinkedStack<>();
        linkedStack.push("1");
        Iterator<String> iter = linkedStack.iterator();

        String actualStackState = "";
        while(iter.hasNext()) {
            actualStackState += iter.next();
        }

        assertEquals("State of Stack is incorrect", "1", actualStackState);
    }

    /**
     * Test single pop.
     */
    public void testSinglePop() {
        LinkedStack<String> linkedStack = new LinkedStack<>();
        linkedStack.push("1");
        String poppedItem = linkedStack.pop();
        assertEquals("State of Stack is incorrect", "1", poppedItem);
    }

    /**
     * Test push and pop.
     */
    public void testPushPop() {
        LinkedStack<String> linkedStack = new LinkedStack<>();
        linkedStack.push("1");
        linkedStack.pop();
        assertTrue("Stack is not empty", linkedStack.isEmpty());
    }

    /**
     * Test happy path for LinkedStack of strings.
     */
    public void testLinkedStackOfStrings() {
        LinkedStack<String> linkedStack = new LinkedStack<>();
        linkedStack.push("1");
        linkedStack.push("2");
        linkedStack.push("3");
        linkedStack.pop();
        linkedStack.push("4");
        Iterator<String> iter = linkedStack.iterator();

        String actualStackState = "";
        while(iter.hasNext()) {
            actualStackState += iter.next();
        }

        assertEquals("State of Stack is incorrect", "421", actualStackState);
    }
}
