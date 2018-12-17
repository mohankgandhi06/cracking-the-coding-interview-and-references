package g.chapter.stacksAndQueues;

import java.util.Stack;

public class DQueueViaStacks {
    /* Question:
     Queue via Stacks: Implement a MyQueue class which implements a queue using two Stacks */
    public static void main(String[] args) {
        /*StackQueue stackQueue = new StackQueue(5);
        stackQueue.add(8);
        stackQueue.add(3);
        stackQueue.add(1);
        stackQueue.add(7);
        stackQueue.add(10);
        System.out.println("After Adding to Queue: ");
        stackQueue.show();
        System.out.println("After 1st Remove: ");
        stackQueue.remove();
        stackQueue.show();

        System.out.println("After 2nd and 3rd Remove: ");
        stackQueue.remove();
        stackQueue.remove();
        stackQueue.show();
        System.out.println("Peek value is: " + stackQueue.peek());*/
        MyQueue stackQueue = new MyQueue();
        stackQueue.offer(8);
        stackQueue.offer(3);
        stackQueue.offer(1);
        stackQueue.offer(7);
        stackQueue.offer(10);
        System.out.println("After Adding to Queue: ");
        stackQueue.show();
        System.out.println("After 1st Remove: ");
        stackQueue.poll();
        stackQueue.show();

        System.out.println("After 2nd and 3rd Remove: ");
        stackQueue.poll();
        stackQueue.poll();
        stackQueue.show();
        System.out.println("Peek value is: " + stackQueue.peek());
    }
}

/* Optimal Implementations */
class MyQueue<Integer> {
    /* Implementing as discussed in the Earlier Implementations below */
    Stack<Integer> newestFirstStack, oldestFirstStack;

    public MyQueue() {
        this.newestFirstStack = new Stack<>();
        this.oldestFirstStack = new Stack<>();
    }

    public int size() {
        return newestFirstStack.size();//Only the newest stack counts for size
        //In the solutions it has been given that size is a total of both old
        // and new which will be wrong since if we are going to add the new elements
        // in the second stack as well we cannot manage that pushing and popping
        // mechanism. So it should be just the newestFirstStack
    }

    public boolean offer(Integer data) {
        pushToNewestFirstStack();
        return newestFirstStack.push(data) != null;//To know if the push worked
    }

    public Integer poll() {
        pushToOldestFirstStack();
        return oldestFirstStack.pop();
    }

    public Integer peek() {
        pushToOldestFirstStack();
        return oldestFirstStack.peek();
    }

    /* The show method will appear wrong since the first element inserted
     * in the queue will appear initial and not at the end like a queue. But
     * it is actually working properly. We can test by peek method before
     * the for loop */
    public void show() {
        pushToNewestFirstStack();
        for (Integer i : newestFirstStack) {
            System.out.println("- " + i);
        }
        System.out.println("");
    }

    /* The below two methods can be made to work as one but it will be a
     * bit confusing to explain. So keeping it separate */
    private void pushToOldestFirstStack() {
        if (!newestFirstStack.isEmpty()) {
            while (!newestFirstStack.isEmpty()) {
                oldestFirstStack.push(newestFirstStack.pop());
            }
        }
    }

    private void pushToNewestFirstStack() {
        if (!oldestFirstStack.isEmpty()) {
            while (!oldestFirstStack.isEmpty()) {
                newestFirstStack.push(oldestFirstStack.pop());
            }
        }
    }
}

/* Earlier Implementations */
class StackQueue {
    /* Here we are using the newly implemented stack and then for each remove
     * and peek we need to push everything to the new stack and then take the
     * above element. But this operation could be taken (pushing to new stack)
     * only when necessary i.e., when a new element is added we can shift back */
    private ViaStack stackOne;
    private ViaStack stackTwo;

    public StackQueue(int size) {
        this.stackOne = new ViaStack(size);
        this.stackTwo = new ViaStack(size);
    }

    public void add(int data) {
        if (!this.stackOne.isFull()) {
            this.stackOne.top++;
            this.stackOne.getArray()[ this.stackOne.top ] = data;
        } else {
            System.out.println("Queue is Full. Please remove the element before adding new");
        }
    }

    public int remove() {
        while (!this.stackOne.isEmpty()) {
            this.stackTwo.top++;
            this.stackTwo.getArray()[ this.stackTwo.top ] = this.stackOne.getArray()[ this.stackOne.top ];
            this.stackOne.getArray()[ this.stackOne.top ] = 0;
            this.stackOne.top--;
        }
        int oldValue = this.stackTwo.getArray()[ this.stackTwo.top ];
        this.stackTwo.getArray()[ this.stackTwo.top ] = 0;
        this.stackTwo.top--;
        while (!this.stackTwo.isEmpty()) {
            this.stackOne.top++;
            this.stackOne.getArray()[ this.stackOne.top ] = this.stackTwo.getArray()[ this.stackTwo.top ];
            this.stackTwo.getArray()[ this.stackTwo.top ] = 0;
            this.stackTwo.top--;
        }
        return oldValue;
    }

    public int peek() {
        return this.stackOne.getArray()[ this.stackOne.getTop() ];
    }

    public void show() {
        for (int i = this.stackOne.getArray().length - 1; i >= 0; i--) {
            System.out.println("- " + stackOne.getArray()[ i ]);
        }
    }
}

class ViaStack {
    private int[] array;
    public int top;
    private int max;

    public ViaStack(int size) {
        this.array = new int[ size ];
        this.top = -1;
        this.max = size;
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public boolean isFull() {
        return this.top == this.max;
    }

    public int[] getArray() {
        return array;
    }

    public int getTop() {
        return top;
    }
}