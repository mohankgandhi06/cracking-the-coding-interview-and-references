package g.chapter.stacksAndQueues;

public class ESortStack {
    /*Question:
     Sort Stack: Write a program to sort a stack such that the smallest items are othe top. You can use
     additional temporary stack, but you may not copy the elements into any other data structure
     (such as an array).The stack supports push, pop, peek, isEmpty*/
    public static void main(String[] args) {
        SortThisStack sortThisStack = new SortThisStack(10);
        //sortThisStack.getInputStack().setArray(new int[]{9, 2, 8, 5, 6, 7, 3, 1});
        sortThisStack.getInputStack().push(7);
        sortThisStack.getInputStack().push(2);
        sortThisStack.getInputStack().push(5);
        sortThisStack.getInputStack().push(8);
        //sortThisStack.getInputStack().pop();
        sortThisStack.getInputStack().push(6);
        sortThisStack.getInputStack().push(3);
        sortThisStack.getInputStack().push(1);
        sortThisStack.getInputStack().push(9);
        //sortThisStack.getInputStack().pop();
        System.out.println("Peek value: " + sortThisStack.getInputStack().peek());
        sortThisStack.sortThisStack();
        //The sorted Stack is the HeavierBelowStack second column in the output
        sortThisStack.show();
    }
}

class SortThisStack {
    private StackForSorting inputStack;
    private StackForSorting heavierBelowStack;
    private StackForSorting lighterBelowStack;

    public SortThisStack(int size) {
        this.inputStack = new StackForSorting(size);
        this.heavierBelowStack = new StackForSorting(size);
        this.lighterBelowStack = new StackForSorting(size);
    }

    public StackForSorting sortThisStack() {
        while (!this.getInputStack().isEmpty()) {
            if (this.getHeavierBelowStack().isEmpty()) {
                //When the heavierBelowStack is Empty
                int popValue = this.getInputStack().pop();
                this.getHeavierBelowStack().push(popValue);
            } else if (this.getInputStack().peek() >
                    this.getHeavierBelowStack().getArray()[ this.getHeavierBelowStack().getTop() ]) {
                //Empty out the heavierBelowStack (into the lighterBelowStack) until the top of inputStack is heavier than the
                // heavierBelowStack. Once the position is reached then pop the value from the inputStack
                // and push it in the heavierBelowStack. Then pop the lighterBelowStack and push them back into the
                // heavierBelowStack. Repeat these steps untill the inputStack is emptied
                while (!this.getHeavierBelowStack().isEmpty() &&
                        this.getHeavierBelowStack().peek() < this.getInputStack().peek()) {
                    int popValue = this.getHeavierBelowStack().pop();
                    this.getLighterBelowStack().push(popValue);
                }
                int popValue = this.getInputStack().pop();
                this.getHeavierBelowStack().push(popValue);
                while (!this.getLighterBelowStack().isEmpty()) {
                    int poppedValue = this.getLighterBelowStack().pop();
                    this.getHeavierBelowStack().push(poppedValue);
                }
            } else {
                int popValue = this.getInputStack().pop();
                this.getHeavierBelowStack().push(popValue);
            }
        }
        return this.getHeavierBelowStack();
    }

    public void show() {
        for (int i = this.inputStack.getArray().length - 1; i >= 0; i--) {
            System.out.println(" | " + this.inputStack.getArray()[ i ] + " | " + this.getHeavierBelowStack().getArray()[ i ] + " | " + this.getLighterBelowStack().getArray()[ i ] + " | ");
        }
    }

    public StackForSorting getInputStack() {
        return inputStack;
    }

    public StackForSorting getHeavierBelowStack() {
        return heavierBelowStack;
    }

    public StackForSorting getLighterBelowStack() {
        return lighterBelowStack;
    }
}

class StackForSorting {
    private int[] array;
    private int top;
    private int max;

    public StackForSorting(int size) {
        this.array = new int[ 10 ];
        this.top = -1;
        this.max = size;
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public boolean isFull() {
        return this.top == this.max;
    }

    public int peek() {
        return this.getArray()[ this.top ];
    }

    public void push(int data) {
        if (!this.isFull()) {
            this.top++;
            this.getArray()[ this.top ] = data;
        }
    }

    public int pop() {
        int oldValue = -9999;
        if (!isEmpty()) {
            oldValue = this.getArray()[ this.top ];
            this.getArray()[ this.top ] = 0;
            top--;
        }
        return oldValue;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}