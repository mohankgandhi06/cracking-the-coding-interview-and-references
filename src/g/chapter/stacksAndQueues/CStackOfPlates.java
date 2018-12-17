package g.chapter.stacksAndQueues;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class CStackOfPlates {
    /* Question:
     Stack of Plates: Imagine a Stack of plates (literal). If the stack gets too high, it may topple
     Therefore in real life we would likely start a new stack when the previous state exceeds threshold
     Implement a data structure SetOfStacks that mimics this. SetOfStacks should be composed of several
     Stacks and should create a new stack once the previous one exceeds capacity. SetOfStacks.push(),
     SetOfStacks.pop() should behave exactly identical to a single stack (i.e. pop() should return
     the same value as it would if there were a single stack)
     FOLLOW UP
     Implement a function popAt(index) which performs pop operation on a specific sub-stack*/
    public static void main(String[] args) {
        /*SetOfStacks setOfStacks = new SetOfStacks();
        //Change the stack size as you want. Hard coded value '2' below
        StackSet set = new StackSet(2);
        setOfStacks.setHead(set);
        setOfStacks.push(4);
        setOfStacks.push(7);
        setOfStacks.push(8);
        setOfStacks.push(10);
        setOfStacks.push(11);
        setOfStacks.push(12);
        setOfStacks.push(20);
        setOfStacks.push(21);
        setOfStacks.push(30);
        setOfStacks.push(31);

        System.out.println("Entire Stack");
        setOfStacks.show();
        System.out.println();
        System.out.println("Peeking Value: " + setOfStacks.peek());
        System.out.println();

        setOfStacks.pop();

        System.out.println("Stack not empty");
        setOfStacks.show();
        System.out.println();

        System.out.println("Peeking Value: " + setOfStacks.peek());
        System.out.println();

        setOfStacks.pop();
        System.out.println("Stack Empty");
        setOfStacks.show();
        System.out.println();
        System.out.println("Peeking Value: " + setOfStacks.peek());
        System.out.println();

        System.out.println("Popped Value: at 1: " + setOfStacks.popAt(3));
        System.out.println("Popped Value: at 1: " + setOfStacks.popAt(3));
        System.out.println("PopAt function");
        setOfStacks.show();
        System.out.println();*/

        int capacity = 4;
        SetOfStacksOptimal setOfStacks = new SetOfStacksOptimal(capacity);
        setOfStacks.push(4);
        setOfStacks.push(7);
        setOfStacks.push(8);
        setOfStacks.push(10);
        setOfStacks.push(11);
        setOfStacks.push(12);
        setOfStacks.push(20);
        setOfStacks.push(21);
        setOfStacks.push(30);
        setOfStacks.push(31);

        System.out.println("Entire Stack");
        setOfStacks.show();
        System.out.println();

        System.out.println("Popped: " + setOfStacks.pop());

        System.out.println("Stack not empty");
        setOfStacks.show();
        System.out.println();

        //setOfStacks.pop();
        System.out.println("Stack Empty");
        setOfStacks.show();
        System.out.println();

        System.out.println("Popped Value: " + setOfStacks.popAt(1));
        System.out.println("Popped Value: " + setOfStacks.popAt(0));
        setOfStacks.show();
        setOfStacks.push(67);
        System.out.println("After Insertion");
        setOfStacks.show();
        System.out.println();
    }
}

/* Optimal Implementations */
class SetOfStacksOptimal {
    ArrayList<StackOptimal> stackOptimals = new ArrayList<>();
    public int capacity;

    public SetOfStacksOptimal(int capacity) {
        this.capacity = capacity;
    }

    public void push(int value) {
        StackOptimal last = getLastStack();
        if (last != null && !last.isFull()) {//Adding the value to the existing stack
            last.push(value);
        } else {//Creating a new stack and adding the value
            StackOptimal stackOptimal = new StackOptimal(capacity);
            stackOptimal.push(value);
            stackOptimals.add(stackOptimal);
        }
    }

    public int pop() {
        StackOptimal last = getLastStack();
        if (last == null) throw new EmptyStackException();
        int poppedValue = last.pop();
        if (last.size == 0) stackOptimals.remove(stackOptimals.size() - 1);
        return poppedValue;
    }

    public boolean isEmpty() {
        StackOptimal last = getLastStack();
        return last == null || last.isEmpty();
    }

    public StackOptimal getLastStack() {
        if (stackOptimals.size() == 0) return null;
        return stackOptimals.get(stackOptimals.size() - 1);
    }

    public int popAt(int index) {
        return leftShift(index, true);
    }

    public int leftShift(int index, boolean removeTop) {
        StackOptimal stack = stackOptimals.get(index);
        int removedItem;

        if (removeTop) removedItem = stack.pop();
        else removedItem = stack.removeBottom();

        if (stack.isEmpty()) {
            stackOptimals.remove(index);
        } else if (stackOptimals.size() > index + 1) {
            int v = leftShift(index + 1, false);
            stack.push(v);
        }
        return removedItem;
    }

    public void show() {
        int stackNo = 1;
        for (StackOptimal stackOptimal : stackOptimals) {
            Node node = stackOptimal.bottom;
            while (node != null) {
                System.out.println("Stack No: " + stackNo + " Element: " + node.value);
                node = node.above;
            }
            stackNo++;
        }
    }
}

class StackOptimal {
    private int capacity;//Total Capacity
    public Node top, bottom;
    public int size = 0;//Current no. of elements in the stack

    public StackOptimal(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return capacity == size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean push(int data) {
        if (size >= capacity) return false;
        size++;
        Node node = new Node(data);
        if (size == 1) bottom = node;
        join(node, top);
        top = node;
        return true;
    }

    public int pop() {
        Node t = top;
        if (size > 1) top.below.above = null;
        top = top.below;
        size--;
        return t.value;
    }

    public void join(Node above, Node below) {
        if (below != null) below.above = above;
        if (above != null) above.below = below;
    }

    public int removeBottom() {
        Node b = bottom;
        bottom = bottom.above;
        if (bottom != null) bottom.below = null;
        size--;
        return b.value;
    }
}

class Node {
    public int value;
    public Node above;
    public Node below;

    public Node(int data) {
        this.value = data;
    }
}

/* Earlier Implementations */
class SetOfStacks {
    /* Using the Linked List we are maintaining a growing stack
     * When the stacks get full we are creating a new one and pushing the value */
    private StackSet head;
    private StackSet tail;
    private int totalStacks = 1;

    public SetOfStacks() {

    }

    public void push(int data) {
        StackSet stack = this.getHead();
        while (stack.isFull() && stack.getNext() != null) {
            stack = stack.getNext();
        }
        if (!stack.isFull()) {
            stack.top++;
            stack.getStackArray()[ stack.top ] = data;
            return;
        } else if (stack.getNext() == null) {
            StackSet newStack = new StackSet(stack.size);
            stack.setNext(newStack);
            stack.getNext().setPrevious(stack);
            this.setTail(stack.getNext());
            stack.getNext().top++;
            stack.getNext().getStackArray()[ stack.getNext().top ] = data;
            totalStacks++;
            return;
        }
    }

    public int pop() {
        int oldValue = -9999;
        StackSet tail = this.getTail();
        if (!tail.isEmpty()) {
            int oldTop = tail.getTop();
            oldValue = tail.getStackArray()[ oldTop ];
            tail.getStackArray()[ oldTop ] = 0;
            tail.setTop(tail.getTop() - 1);
            if (tail.isEmpty()) {
                //Delete the stack
                tail.getPrevious().setNext(null);
                this.setTail(tail.getPrevious());
                totalStacks--;
            }
        }
        return oldValue;
    }

    public int peek() {
        return this.tail.getStackArray()[ this.getTail().getTop() ];
    }

    public int popAt(int index) {
        /* Since it has been mentioned as "perform the pop operation on a specific sub-stack "
         * it was assumed that the rolling over of the rest of the stacks was not needed here.
         * But in the optimal implementations that feature (roll over) is present */
        int oldValue = -9999;
        StackSet stack = null;
        //Identify if the head is near or the tail is near to the stack from which value has to be popped
        if (index <= totalStacks) {
            if ((totalStacks - index) >= (totalStacks / 2)) {//It means the head is only near
                int stackCount = 1;
                stack = this.getHead();
                while (stackCount != index) {
                    stackCount++;
                    stack = stack.getNext();
                }
            } else {//It means the tail is near
                int stackCount = totalStacks;
                stack = this.getTail();
                while (stackCount != index) {
                    stackCount--;
                    stack = stack.getPrevious();
                }
            }
            oldValue = stack.getStackArray()[ stack.getTop() ];
            stack.getStackArray()[ stack.getTop() ] = 0;
            stack.setTop(stack.getTop() - 1);
            if (stack.isEmpty()) {
                //Delete the stack
                if (stack.getPrevious() == null) {//Head Stack
                    stack.getNext().setPrevious(null);
                    this.setHead(stack.getNext());
                } else if (stack.getNext() == null) {//Tail Stack
                    stack.getPrevious().setNext(null);
                    this.setTail(stack.getPrevious());
                } else {//Somewhere in the middle
                    stack.getNext().setPrevious(stack.getPrevious());
                    stack.getPrevious().setNext(stack.getNext());
                }
                totalStacks--;
            }
        } else {
            return -1;
        }
        return oldValue;
    }

    public void show() {
        StackSet stack = this.getHead();
        int stackNumber = 1;
        while (stack != null) {
            for (int i = 0; i < stack.getStackArray().length; i++) {
                System.out.println("Stack Number: " + stackNumber + " Element: " + stack.getStackArray()[ i ]);
            }
            stack = stack.getNext();
            stackNumber++;
        }
    }

    public StackSet getHead() {
        return head;
    }

    public void setHead(StackSet head) {
        this.head = head;
    }

    public StackSet getTail() {
        return tail;
    }

    public void setTail(StackSet tail) {
        this.tail = tail;
    }
}

class StackSet {
    private int[] stackArray;
    public int top;
    public int size;
    private StackSet next;
    private StackSet previous;

    public StackSet(int size) {
        this.stackArray = new int[ size ];
        this.next = null;
        this.top = -1;
        this.size = size;
    }

    public boolean isFull() {
        return this.top == this.size - 1;
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public int[] getStackArray() {
        return stackArray;
    }

    public StackSet getNext() {
        return next;
    }

    public void setNext(StackSet next) {
        this.next = next;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public StackSet getPrevious() {
        return previous;
    }

    public void setPrevious(StackSet previous) {
        this.previous = previous;
    }
}