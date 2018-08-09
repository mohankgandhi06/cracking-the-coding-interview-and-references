package g.chapter.stacksAndQueues;

public class DQueueViaStacks {
    /* Question:
     Queue via Stacks: Implement a MyQueue class which implements a queue using two Stacks */
    public static void main(String[] args) {
        StackQueue stackQueue = new StackQueue(5);
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
        System.out.println("Peek value is: " + stackQueue.peek());
    }
}

class StackQueue {
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