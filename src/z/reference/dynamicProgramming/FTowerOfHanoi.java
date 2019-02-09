package z.reference.dynamicProgramming;

import java.util.Stack;

public class FTowerOfHanoi {
    public static HanoiStack first = new HanoiStack(new Stack());
    public static HanoiStack second = new HanoiStack(new Stack());
    public static HanoiStack third = new HanoiStack(new Stack());
    public static boolean exit = false;
    public static int weightCount = 11;

    public static void main(String[] args) {
        /*first.next = third;
        second.next = first;
        third.next = second;
        for (int i = weightCount; i > 0; i--) {
            first.stack.push(i);
        }

        System.out.println("Initial Position: ");
        HanoiStack showStack = new HanoiStack(first);
        while (!showStack.stack.isEmpty()) {
            System.out.println(showStack.stack.pop());
        }

        solveHanoi(first);

        System.out.println("After Solving: ");
        System.out.println("First Stack");
        while (!first.stack.isEmpty()) {
            System.out.println(first.stack.pop());
        }

        System.out.println("Second Stack");
        while (!second.stack.isEmpty()) {
            System.out.println(second.stack.pop());
        }

        System.out.println("Third Stack");
        while (!third.stack.isEmpty()) {
            System.out.println(third.stack.pop());
        }*/


        /* Optimized Solution */
        /* This is way simpler than the above but it is tricky */
        solveHanoiOptimized(4, 'A', 'B', 'C');
    }

    private static void solveHanoi(HanoiStack hanoiStack) {
        if (!exit) {
            if (third.stack.size() == weightCount) {
                exit = true;
                return;
            }
            HanoiStack tempStack = hanoiStack.next;
            if (!hanoiStack.stack.isEmpty()) {
                int weight = hanoiStack.stack.pop();
                while (tempStack != hanoiStack && tempStack != null) {
                    if (canPush(tempStack, weight)) {
                        tempStack.stack.push(weight);
                        break;
                    }
                    tempStack = tempStack.next;
                }
                if (tempStack.stack.isEmpty() || (tempStack != null && tempStack.stack.peek() != weight)) {
                    //Since the weight cannot be places anywhere else we are again
                    // pushing it to the original stack and moving one to the next
                    hanoiStack.stack.push(weight);
                }
            } else {
                tempStack = hanoiStack;
            }
            solveHanoi(tempStack.next);
        }
    }

    private static boolean canPush(HanoiStack hanoiStack, int weight) {
        return (hanoiStack.stack.isEmpty() || hanoiStack.stack.peek() > weight);
    }

    private static void solveHanoiOptimized(int weight, char rodFrom, char midRod, char rodTo) {
        if (weight == 1) {
            System.out.println("Plate " + weight + " from " + rodFrom + " to " + rodTo);
            return;
        }
        solveHanoiOptimized(weight - 1, rodFrom, rodTo, midRod);
        System.out.println(" - Plate " + weight + " from " + rodFrom + " to " + rodTo);
        solveHanoiOptimized(weight - 1, midRod, rodFrom, rodTo);
    }
}

class HanoiStack {
    protected Stack<Integer> stack;
    protected HanoiStack next;

    public HanoiStack(Stack stack) {
        this.stack = stack;
        this.next = null;
    }

    public HanoiStack(HanoiStack hanoiStack) {
        this.stack = new Stack<>();
        Integer[] tempArray = new Integer[ hanoiStack.stack.size() ];
        hanoiStack.stack.copyInto(tempArray);
        for (int i = 0; i < tempArray.length; i++) {
            this.stack.push(tempArray[ i ]);
        }
    }
}