package g.chapter.stacksAndQueues;

public class AThreeInOne {
    /* Question:
     Three in One: Describe how you could use a single array to implement 3 stacks
    * */

    public static void main(String[] args) {
        Stack stack = initializeStacks(4, 8, 7);
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
        stack.showStacks();
    }

    public static Stack initializeStacks(int stackOneSize, int stackTwoSize, int stackThreeSize) {
        return new Stack(stackOneSize, stackTwoSize, stackThreeSize);
    }
}

class Stack {
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