package f.chapter.linkedList;

public class DPartition {
    /* Question:
     Partition: Write a code to partition a linked list around a value x, such that all nodes less than x
     come before all nods greater than or equal to x. If x is contained within the list, the value of x only need to
     be after the elements less than x. The partition element x can appear anywhere in the "right partition",
     it does not need to appear between the left and right partition */

    /* Example  input:  3->5->8->5->10->2->1
                output: 3->1->2->10->5->5->8 */

    public static void main(String[] args) {
        /*PartitionLinkedList partitionLinkedList = new PartitionLinkedList();*/
        int partitionNumber = 5;
        /*partitionLinkedList.prepareLinkedList(new int[]{3, 15, 8, 5, 10, 2, 1});*/
        /*System.out.println("LinkedList before partition: ");
        showList(partitionLinkedList);
        partitionLinkedList = partition(partitionLinkedList, partitionNumber);
        System.out.println("LinkedList after partition: ");
        showList(partitionLinkedList);*/
        /*OptimalLinkedList optimalLinkedList = new OptimalLinkedList(new int[]{3, 1, 15, 8, 5, 10, 2, 1});*/
        OptimalLinkedList optimalLinkedList = new OptimalLinkedList(new int[]{1, 2, 5, 6, 5, 10, 12, 11});
        System.out.println("Optimal Algorithm");
        showList(optimalLinkedList.head);
        /*System.out.println("In Reverse");
        showListReverse(optimalLinkedList.tail);*/
        OptimalNode node = partitionLinkedList(optimalLinkedList.head, partitionNumber);
        System.out.println("Node data is: " + node.data);
        showList(node);
    }

    public static void showList(PartitionLinkedList partitionLinkedList) {
        PartitionNode node = partitionLinkedList.getHead();
        while (node.getNext() != null) {
            System.out.println("-> " + node.getNext().getData());
            node = node.getNext();
        }
    }

    public static void showList(OptimalNode node) {
        while (node != null) {
            System.out.println("-> " + node.data);
            node = node.next;
        }
    }

    public static void showListReverse(OptimalNode node) {
        while (node != null) {
            System.out.println("-> " + node.data);
            node = node.previous;
        }
    }

    /* Optimal Implementations */
    private static OptimalNode partitionLinkedList(OptimalNode node, int partitionNumber) {
        /* In the Optimal we are using the same list. Here we are first taking the starting node to be both head and tail
         * Ex: 3, 1, 15, 8, 5, 10, 2, 1 here the value will be initially that 3 is both head and tail.
         * What happends during each iteration is that the if the number is less than the partition it becomes
         * resultHead or if it is greater then it becomes the resultTail by pushing other element.
         * For a element to become the head it must push its current to the right and if the element is to
         * become a tail it needs to push the current to the left so that nothing comes after it */
        OptimalNode resultHead = node;
        OptimalNode resultTail = node;
        while (node != null) {
            OptimalNode next = node.next;
            if (node.data < partitionNumber) {//Smaller - attach to the head
                node.next = resultHead;//Pushing the current head to the next
                resultHead = node;
            } else {//Larger - attach to the tail
                resultTail.next = node;//Pushing the current tail to the previous
                resultTail = node;
            }
            node = next;
        }
        resultTail.next = null;
        return resultHead;
    }

    /* Earlier Implementations */
    public static PartitionLinkedList partition(PartitionLinkedList partitionLinkedList, int partitionNumber) {
        /* We are creating two lists with one having the smaller numbers and the other having the number equal and greater
         * Once we complete the loop we can attach the tail of the smaller list to the head of the greater list
         * It will take BigO (n) and also space required is more since we are using two other lists of unknown size */
        PartitionLinkedList smallerNumberLinkedList = new PartitionLinkedList();
        PartitionLinkedList largerNumberLinkedList = new PartitionLinkedList();
        PartitionNode node = partitionLinkedList.getHead();
        while (node.getNext() != null) {
            if (node.getNext().getData() < partitionNumber) {
                smallerNumberLinkedList.insert(node.getNext().getData());
            } else {
                largerNumberLinkedList.insert(node.getNext().getData());
            }
            node = node.getNext();
        }
        //Attach the tail of the smaller to the head's next of the larger
        smallerNumberLinkedList.getTail().setNext(largerNumberLinkedList.getHead().getNext());
        return smallerNumberLinkedList;
    }
}

class PartitionLinkedList {
    private PartitionNode head;
    private PartitionNode tail;

    public PartitionLinkedList() {
        this.head = new PartitionNode();
    }

    public void prepareLinkedList(int[] dataList) {
        for (int i = 0; i < dataList.length; i++) {
            this.insert(dataList[ i ]);
        }
    }

    public void insert(int data) {
        PartitionNode node = this.getHead();
        while (node.getNext() != null) {
            node = node.getNext();
        }
        node.setNext(new PartitionNode(data));
        node.getNext().setPrevious(node);
        this.setTail(node.getNext());
    }

    public PartitionNode getHead() {
        return head;
    }

    public PartitionNode getTail() {
        return tail;
    }

    public void setTail(PartitionNode tail) {
        this.tail = tail;
    }
}

class PartitionNode {
    private int data;
    private PartitionNode next;
    private PartitionNode previous;

    public PartitionNode() {
        this.data = -9999;
        this.next = null;
    }

    public PartitionNode(int data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public int getData() {
        return data;
    }

    public PartitionNode getNext() {
        return next;
    }

    public void setNext(PartitionNode next) {
        this.next = next;
    }

    public void setPrevious(PartitionNode previous) {
        this.previous = previous;
    }
}

class OptimalLinkedList {
    public OptimalNode head;
    public OptimalNode tail;

    public OptimalLinkedList(int[] input) {
        this.head = new OptimalNode();
        this.tail = new OptimalNode();
        boolean isFirstNodeSet = false;
        for (int a : input) {
            if (!isFirstNodeSet) {
                this.head.data = a;
                this.tail.data = a;
                isFirstNodeSet = true;
            } else {
                OptimalNode node = this.head;
                while (node.next != null) {
                    node = node.next;
                }
                OptimalNode newNode = new OptimalNode(a);
                this.tail.next = newNode;
                newNode.previous = this.tail;
                node.next = newNode;
                this.tail = newNode;
            }
        }
    }
}

class OptimalNode {
    public int data;
    public OptimalNode next;
    public OptimalNode previous;

    public OptimalNode() {

    }

    public OptimalNode(int data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }
}