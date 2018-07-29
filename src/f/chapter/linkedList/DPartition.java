package f.chapter.linkedList;

public class DPartition {
    /* Question:
     Partition: Write a code to partition a linked list around a value x, such that all nodes less than x
     come before all nods greater than or equal to x. If x is contained within thelist, the value of x only need to
     be after the elements less than x. The partition element x can appear anywhere in the "right partition",
     it does not need to appear between the left and right partition */

    /* Example  input:  3->5->8->5->10->2->1
                output: 3->1->2->10->5->5->8 */

    public static void main(String[] args) {
        PartitionLinkedList partitionLinkedList = new PartitionLinkedList();
        int partitionNumber = 5;
        partitionLinkedList.prepareLinkedList(new int[]{3, 15, 8, 5, 10, 2, 1});
        System.out.println("LinkedList before partition: ");
        showList(partitionLinkedList);
        partitionLinkedList = partition(partitionLinkedList, partitionNumber);
        System.out.println("LinkedList after partition: ");
        showList(partitionLinkedList);
    }

    public static void showList(PartitionLinkedList partitionLinkedList) {
        PartitionNode node = partitionLinkedList.getHead();
        while (node.getNext() != null) {
            System.out.println("-> " + node.getNext().getData());
            node = node.getNext();
        }
    }

    public static PartitionLinkedList partition(PartitionLinkedList partitionLinkedList, int partitionNumber) {
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