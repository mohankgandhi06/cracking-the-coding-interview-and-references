package g.chapter.stacksAndQueues;

import java.util.EmptyStackException;

public class AThreeInOne {
    /* Question:
     Three in One: Describe how you could use a single array to implement 3 stacks
    * */

    public static void main(String[] args) {
        /*Stack stack = initializeStacks(4, 8, 7);
        stack.push(1, 5);
        stack.push(1, 6);
        stack.push(1, 4);
        stack.push(1, 1);
        stack.push(1, 7);

        stack.push(2, 12);
        stack.push(2, 16);
        stack.push(2, 18);
        stack.push(2, 87);
        stack.push(2, 88);
        stack.push(2, 89);
        stack.push(2, 90);
        stack.push(2, 91);
        stack.push(2, 92);
        System.out.println("Value " + stack.pop(2) + " has been popped");
        System.out.println("Value " + stack.pop(2) + " has been popped");
        System.out.println("Value " + stack.pop(2) + " has been popped");
        stack.push(2, 1);

        stack.push(3, 21);
        stack.push(3, 61);
        stack.push(3, 81);
        stack.push(3, 41);
        stack.push(3, 65);
        stack.push(2, 4);
        stack.push(3, -1);
        stack.push(3, 12);
        System.out.println("Value " + stack.pop(3) + " has been popped");
        System.out.println("Peeking 3rd Stack: " + stack.peek(3));
        System.out.println("Peeking 2nd Stack: " + stack.peek(2));
        System.out.println("Peeking 1st Stack: " + stack.peek(1));
        stack.showStacks();*/

        try {
            FlexibleMultiStack flexibleMultiStack = new FlexibleMultiStack(3, 4);
            flexibleMultiStack.push(0, 6);
            flexibleMultiStack.push(0, 2);
            flexibleMultiStack.push(0, 4);
            flexibleMultiStack.push(0, 5);

            flexibleMultiStack.push(1, 1);
            /*flexibleMultiStack.push(1, 3);
            flexibleMultiStack.push(1, 7);*/
            flexibleMultiStack.push(1, 9);

            flexibleMultiStack.push(2, 10);
            flexibleMultiStack.push(2, 11);
            flexibleMultiStack.push(2, 8);
            flexibleMultiStack.push(2, 12);

            flexibleMultiStack.show();

            flexibleMultiStack.push(0, 3);
            flexibleMultiStack.push(2, 7);

            System.out.println("After Push: ");
            flexibleMultiStack.show();
        } catch (FullStackException e) {
            System.out.println("Exception Occured: " + e.getMessage());
        }
    }

    public static Stack initializeStacks(int stackOneSize, int stackTwoSize, int stackThreeSize) {
        return new Stack(stackOneSize, stackTwoSize, stackThreeSize);
    }
}

/* Optimal Implementations */
/* Fixed Division */
class FixedMultiStack {
    private int numberOfStacks = 3;
    private int stackCapacity;
    private int[] values;
    private int[] sizes;

    public FixedMultiStack(int stackCapacity) {
        this.stackCapacity = stackCapacity;//Each Stacks individual capacity
        this.values = new int[ stackCapacity * numberOfStacks ];//Single stack covering whole
        this.sizes = new int[ numberOfStacks ];//Each Stacks number of elements
    }

    public void push(int stackNumber, int data) throws FullStackException {
        if (isFull(stackNumber)) throw new FullStackException("Stack is Full");
        sizes[ stackNumber ]++;
        values[ indexOfTop(stackNumber) ] = data;
    }

    public int pop(int stackNumber) {
        if (stackNumber > numberOfStacks - 1) return -1;
        if (isEmpty(stackNumber)) throw new EmptyStackException();
        int index = indexOfTop(stackNumber);
        int popped = values[ index ];
        values[ index ] = 0;
        sizes[ stackNumber ]--;
        return popped;
    }

    public int peek(int stackNumber) {
        if (stackNumber > numberOfStacks - 1) return -1;
        if (isEmpty(stackNumber)) throw new EmptyStackException();
        return values[ indexOfTop(stackNumber) ];
    }

    public boolean isEmpty(int stackNumber) {
        return sizes[ stackNumber ] == 0;
    }

    public boolean isFull(int stackNumber) {
        return sizes[ stackNumber ] == stackCapacity;
    }

    private int indexOfTop(int stackNumber) {
        //stackNumber will be in 0(stack 1), 1(stack 2), 2 (stack 3). offset will return the
        // number of stack elements before the current stack.
        //size will be equal to the current stack's number of elements. index will be one less
        // as we start at 0.
        int offset = stackNumber * stackCapacity;
        int size = sizes[ stackNumber ];
        return size + offset - 1;
    }
}

class FullStackException extends Exception {
    public FullStackException(String message) {
        super(message);
    }
}

/* Flexible Divisions */
class FlexibleMultiStack {
    /* In the Flexible Stack what we are doing is that we are fixing the capacity initially and then
     * while one of the stack is full we are taking over the next stack's index position and then
     * adding the element. NOTE: this is done only when there is space for a new element.
     * ie., total capacity is not increased, only that the position is shifted and the capacity
     * of the current stack will be increased and the subsequent is decreased, until the position
     * is obtained where there is value 0 i.e., stack.size < stack.capacity */

    //Each Stacks Info like startIndex, it's total capacity and current number of elements (size)
    private StackInfo[] info;
    private int[] values;

    public FlexibleMultiStack(int numberOfStacks, int defaultSize) {
        this.info = new StackInfo[ numberOfStacks ];
        for (int i = 0; i < numberOfStacks; i++) {
            //defaultSize * i starts from 0... so it will hold the number of elements before the
            // current stack and so it will be the start index of the current stack
            this.info[ i ] = new StackInfo(defaultSize * i, defaultSize);
        }
        this.values = new int[ numberOfStacks * defaultSize ];
    }

    private class StackInfo {
        public int start, size, capacity;

        public StackInfo(int start, int capacity) {
            this.start = start;
            this.capacity = capacity;
        }

        /* Adjust the stack capacity */
        public boolean isWithinStackCapacity(int index) {
            if (index < 0 || index >= values.length) {
                return false;
            }

            int contiguousIndex = index < start ? index + values.length : index;
            int end = start + capacity;
            return start < contiguousIndex && contiguousIndex < end;
        }

        /* Get the Stack's last position's index. i.e., current capacity */
        public int lastCapacityIndex() {
            return adjustIndex(start + capacity - 1);
        }

        /* Get the Stack's last element's index. i.e., current size */
        public int lastElementIndex() {
            return adjustIndex(start + size - 1);
        }

        /* Check if the current stack is empty */
        public boolean isEmpty() {
            return size == 0;
        }

        /* Check if the current stack is full */
        public boolean isFull() {
            return size == capacity;
        }
    }

    public void push(int stackNumber, int data) throws FullStackException {
        if (allStacksAreFull()) {
            throw new FullStackException("Stack is Full");
        }
        StackInfo stack = info[ stackNumber ];
        if (stack.isFull()) {
            expand(stackNumber);
        }
        stack.size++;
        values[ stack.lastElementIndex() ] = data;
    }

    public int pop(int stackNumber) {
        StackInfo stackInfo = info[ stackNumber ];
        if (stackInfo.isEmpty()) throw new EmptyStackException();
        int popped = values[ stackInfo.lastElementIndex() ];
        values[ stackInfo.lastElementIndex() ] = 0;
        stackInfo.size--;
        return popped;
    }

    public int peek(int stackNumber) {
        StackInfo stackInfo = info[ stackNumber ];
        return values[ stackInfo.lastElementIndex() ];
    }

    /* Recursive Algorithm to claim the next stack's space and shift all the elements over by one */
    private void shift(int stackNumber) {
        System.out.println("Shifting " + stackNumber);
        StackInfo stack = info[ stackNumber ];

        //If there is a available space i.e., 0 then the size < capacity and base condition is met
        if (stack.size >= stack.capacity) {
            int nextStack = (stackNumber + 1) % info.length;
            shift(nextStack);//Recursive Algorithm
            stack.capacity++;
        }

        //Shifting Process
        int index = stack.lastCapacityIndex();
        while (stack.isWithinStackCapacity(index)) {
            values[ index ] = values[ previousIndex(index) ];
            index = previousIndex(index);
        }

        //Free up the initial location of the stack to be taken up by the previous stack
        values[ stack.start ] = 0;
        stack.start = nextIndex(stack.start);
        stack.capacity--;
    }

    /* Expand the capapcity of the stack to accomodate the new value. NOTE the capacity should not
     * be exceeded in the process */
    private void expand(int stackNumber) {
        shift((stackNumber + 1) % info.length);
        info[ stackNumber ].capacity++;
    }

    /* Calculate the total number of filled spaces across the stack */
    public int numberOfElements() {
        int size = 0;
        for (StackInfo infoItem : info) {
            size = size + infoItem.size;
        }
        return size;
    }

    /* Check for full stack */
    public boolean allStacksAreFull() {
        return numberOfElements() == values.length;
    }

    /* Adjust the index to be between the range 0 -> length - 1 */
    private int adjustIndex(int index) {
        int max = values.length;
        return ((index % max) + max) % max;
    }

    private int nextIndex(int index) {
        return adjustIndex(index + 1);
    }

    private int previousIndex(int index) {
        return adjustIndex(index - 1);
    }

    public void show() {
        /*for (int l = 0; l < info.length; l++) {
            for (int i = 0; i < info[ l ].capacity; i++) {
                System.out.println("Stack Number " + l + " - Value: " + values[ l * info[ l ].capacity + i ]);
            }
        }*/
        for (int i : values) {
            System.out.println("- " + i);
        }
    }
}

/* Earlier Implementations */
class Stack {
    /* Since it has been mentioned as the 3 stacks we are directly using the three stacks
     * but here we are not pushing the value to the next stack if the overflow is there
     * we are also using with each method the stack specific operation and not overall.
     * Since these are not given they have been assumed */
    private int[] array;
    private int firstArrayTop;
    private int secondArrayTop;
    private int thirdArrayTop;
    private int stackOneSize;
    private int stackTwoSize;
    private int stackThreeSize;

    public Stack(int stackOneSize, int stackTwoSize, int stackThreeSize) {
        this.array = new int[ stackOneSize + stackTwoSize + stackThreeSize ];
        this.firstArrayTop = -1;
        this.secondArrayTop = stackOneSize - 1;
        this.thirdArrayTop = stackOneSize + stackTwoSize - 1;
        this.stackOneSize = stackOneSize;
        this.stackTwoSize = stackTwoSize;
        this.stackThreeSize = stackThreeSize;
    }

    public void push(int stackNumber, int data) {
        if (isFull(stackNumber)) {
            System.out.println("Cannot Push: " + data + " Stack " + stackNumber + " is full");
        } else {
            findTopAndIncrement(stackNumber);
            int position = findTop(stackNumber);
            array[ position ] = data;
        }
    }

    public int pop(int stackNumber) {
        if (isEmpty(stackNumber)) {
            System.out.println("Cannot pop anymore: The Stack" + stackNumber + " is already empty");
            return -1;
        } else {
            int oldPosition = findTop(stackNumber);
            int poppedElement = array[ oldPosition ];
            array[ oldPosition ] = 0;
            findTopAndDecrement(stackNumber);
            return poppedElement;
        }
    }

    public int peek(int stackNumber) {
        int position = findTop(stackNumber);
        return array[ position ];
    }

    public boolean isEmpty(int stackNumber) {
        int top = findTop(stackNumber);
        switch (stackNumber) {
            case 1:
                return top == -1;
            case 2:
                return top == this.stackOneSize - 1;
            case 3:
                return top == this.stackOneSize + this.stackTwoSize - 1;
            default:
                System.out.println("Stack Number does not exist");
        }
        return false;
    }

    public boolean isFull(int stackNumber) {
        int top = findTop(stackNumber);
        switch (stackNumber) {
            case 1:
                return top == this.stackOneSize - 1;
            case 2:
                return top == this.stackOneSize + this.stackTwoSize - 1;
            case 3:
                return top == this.stackOneSize + this.stackTwoSize + this.stackThreeSize - 1;
            default:
                System.out.println("Stack Number does not exist");
        }
        return false;
    }

    public int findTop(int stackNumber) {
        switch (stackNumber) {
            case 1:
                return this.firstArrayTop;
            case 2:
                return this.secondArrayTop;
            case 3:
                return this.thirdArrayTop;
            default:
                System.out.println("Stack Number does not exist");
        }
        return -1;
    }

    public void findTopAndIncrement(int stackNumber) {
        switch (stackNumber) {
            case 1:
                this.firstArrayTop++;
                break;
            case 2:
                this.secondArrayTop++;
                break;
            case 3:
                this.thirdArrayTop++;
                break;
            default:
                System.out.println("Stack Number does not exist");
                break;
        }
    }

    public void findTopAndDecrement(int stackNumber) {
        switch (stackNumber) {
            case 1:
                this.firstArrayTop--;
                break;
            case 2:
                this.secondArrayTop--;
                break;
            case 3:
                this.thirdArrayTop--;
                break;
            default:
                System.out.println("Stack Number does not exist");
                break;
        }
    }

    public void showStacks() {
        int tempLocation = 0;
        int tempStackNumber = 1;
        while (tempLocation != this.array.length) {
            if (tempLocation == this.stackOneSize || tempLocation == this.stackOneSize + this.stackTwoSize) {
                tempStackNumber++;
            }
            System.out.println("Stack Number: " + tempStackNumber + " Data: " + this.array[ tempLocation ]);
            tempLocation++;
        }
    }

    /* Getters and Setters */
    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }
}