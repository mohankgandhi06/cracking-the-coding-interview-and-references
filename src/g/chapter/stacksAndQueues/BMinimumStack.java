package g.chapter.stacksAndQueues;

public class BMinimumStack {

    /* Question:
     Stack Min: How would you design a stack which in addition to push and pop has a function min which returns
     the minimum? push, pop and min should all operate in O(1) time*/

    /*LLT means Linked List Technique and PST Previous State Technique*/
    public static void main(String[] args) {
        MinStack minStack = new MinStack(10);
        minStack.pushLLT(9);
        minStack.pushLLT(7);
        minStack.pushLLT(1);
        minStack.pushLLT(8);
        minStack.pushLLT(5);
        //System.out.println("Popped Value: " + minStack.popLLT());
        System.out.println("Minimum Value of the stack is: " + minStack.minLLT());
        //System.out.println("Popped Value: " + minStack.popLLT());
        System.out.println("Popped Value: " + minStack.popLLT());
        System.out.println("Minimum Value of the stack is: " + minStack.minLLT());
        showStackLLT(minStack);
        showMinimumListLLT(minStack);

        /* Previous State Technique */
        minStack.pushPST(9);
        minStack.pushPST(8);
        minStack.pushPST(7);
        minStack.pushPST(1);
        minStack.pushPST(10);
        minStack.pushPST(6);
        System.out.println("");
        showStackPST(minStack);
        System.out.println("__________________");
        System.out.println("Minimum Value is: " + minStack.minPST());
        System.out.println("Popped Value: " + minStack.popPST());
        System.out.println("");
        showStackPST(minStack);
        System.out.println("__________________");
        System.out.println("Minimum Value is: " + minStack.minPST());
    }

    public static void showStackLLT(MinStack minStack) {
        for (int i = 0; i < minStack.getArray().length; i++) {
            System.out.println("-> " + minStack.getArray()[ i ]);
        }
    }

    public static void showMinimumListLLT(MinStack minStack) {
        MinNode node = minStack.getMinimumLinkedList().getHead();
        while (node.getNext() != null) {
            System.out.println("--- " + node.getNext().getData());
            node = node.getNext();
        }
    }

    public static void showStackPST(MinStack minStack) {
        for (int i = 0; i < minStack.getPstArray().length; i++) {
            if (minStack.getPstArray()[ i ] != null) {
                System.out.println("Data: " + minStack.getPstArray()[ i ].getData() + " Minimum if this value is removed: " + minStack.getPstArray()[ i ].getMinimumIfDataGetsRemoved());
            } else {
                System.out.println("Data: null");
            }
        }
    }
}

class MinStack {
    //For the Linked List Technique (LLT)
    private int[] array;
    private MinimumLinkedList minimumLinkedList;
    private int topLLT;

    //For Previous State Technique (PST)
    private StackPST[] pstArray;
    private int minValue;
    private int topPST;

    public MinStack(int size) {
        this.topLLT = -1;
        this.array = new int[ size ];
        this.minimumLinkedList = new MinimumLinkedList();
        this.pstArray = new StackPST[ size ];
        this.minValue = 9999999;
        this.topPST = -1;
    }

    public void pushLLT(int data) {
        this.topLLT++;
        this.array[ topLLT ] = data;
        insertDataInMinimumQueueLLT(data);
    }

    public int popLLT() {
        int oldTop = this.topLLT;
        int poppedValue = this.array[ oldTop ];
        removeDataFromMinimumQueueLLT(this.array[ oldTop ]);
        this.array[ oldTop ] = 0;
        this.topLLT--;
        return poppedValue;
    }

    private void insertDataInMinimumQueueLLT(int data) {
        MinNode node = this.minimumLinkedList.getHead();
        while (node.getNext() != null) {
            if (node.getNext().getData() > data) {
                MinNode newNode = new MinNode(data);
                newNode.setNext(node.getNext());
                newNode.setPrevious(node);
                node.getNext().setPrevious(newNode);
                node.setNext(newNode);
                break;
            }
            node = node.getNext();
        }
        if (node.getNext() == null) {
            MinNode newNode = new MinNode(data);
            newNode.setNext(node.getNext());
            newNode.setPrevious(node);
            node.setPrevious(node);
            node.setNext(newNode);
        }
    }

    private void removeDataFromMinimumQueueLLT(int data) {
        MinNode node = this.minimumLinkedList.getHead();
        while (node.getNext() != null) {
            if (node.getNext().getData() == data) {
                if (node.getNext().getNext() != null) {
                    node.getNext().getNext().setPrevious(node.getPrevious());
                }
                node.setNext(node.getNext().getNext());
                break;
            }
            node = node.getNext();
        }
    }

    public int minLLT() {
        return this.getMinimumLinkedList().getHead().getNext().getData();
    }

    public void pushPST(int data) {
        this.topPST++;
        StackPST node = new StackPST();
        node.setData(data);
        node.setMinimumIfDataGetsRemoved(this.minValue);
        this.pstArray[ topPST ] = node;
        if (data < this.minValue) {
            this.minValue = data;
        }
    }

    public int popPST() {
        StackPST oldNode = this.pstArray[ topPST ];
        int oldValue = oldNode.getData();
        this.minValue = oldNode.getMinimumIfDataGetsRemoved();
        this.pstArray[ topPST ] = null;
        this.topPST--;
        return oldValue;
    }

    public int minPST() {
        return this.minValue;
    }

    public int[] getArray() {
        return array;
    }

    public MinimumLinkedList getMinimumLinkedList() {
        return minimumLinkedList;
    }

    public StackPST[] getPstArray() {
        return pstArray;
    }
}

class MinimumLinkedList {
    private MinNode head;

    public MinimumLinkedList() {
        this.head = new MinNode(-9999);
    }

    public MinNode getHead() {
        return head;
    }
}

class MinNode {
    private int data;
    private MinNode next;
    private MinNode previous;

    public MinNode(int data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public int getData() {
        return data;
    }

    public MinNode getNext() {
        return next;
    }

    public void setNext(MinNode next) {
        this.next = next;
    }

    public MinNode getPrevious() {
        return previous;
    }

    public void setPrevious(MinNode previous) {
        this.previous = previous;
    }
}

class StackPST {
    private int data;
    private int minimumIfDataGetsRemoved;

    public StackPST() {

    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getMinimumIfDataGetsRemoved() {
        return minimumIfDataGetsRemoved;
    }

    public void setMinimumIfDataGetsRemoved(int minimumIfDataGetsRemoved) {
        this.minimumIfDataGetsRemoved = minimumIfDataGetsRemoved;
    }
}