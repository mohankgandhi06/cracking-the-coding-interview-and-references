package f.chapter.linkedList;

import f.chapter.linkedList.utils.LinkedListUtils;
import f.chapter.linkedList.utils.NodeSingle;

public class ARemoveDups {

    /* Question: Write a code to remove duplicates from an unsorted linked list
     * FOLLOW UP
     * How would you solve this problem if a temporary buffer is not allowed? */

    public static void main(String[] args) {

        NodeSingle head = LinkedListUtils.prepareSinglyLinkedList(new String[]{"6007", "5008", "3468", "6007", "1987", "1986", "3468", "5102"});
        removeDuplicatesUsingNormalComparison(head);

        while (head != null) {
            System.out.println("Result List: " + head.value);
            head = head.next;
        }

        head = LinkedListUtils.prepareSinglyLinkedList(new String[]{"6007", "5008", "3468", "3468", "1987", "1986", "1987"});
        removeDuplicatesUsingHashTable(head);

        while (head != null) {
            System.out.println("Result List: " + head.value);
            head = head.next;
        }
    }

    /* Optimal Implementation */
    public static void removeDuplicatesOptimal(NodeSingle head) {
        /* This is just same as the removeDuplicatesUsingNormalComparison(). Earlier I had thought that since the
         * singly linked list cannot be moved backwards there was a need to maintain previous node temporarily
         * each time and so that implementation was a little complex */
        NodeSingle node = head;
        while (node != null) {
            NodeSingle runner = node;
            while (runner.next != null) {
                if (node.getValue() == runner.next.getValue()) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            node = node.next;
        }
    }

    /* Earlier Implementations */
    public static void removeDuplicatesUsingNormalComparison(NodeSingle head) {
        NodeSingle node = head.next;
        NodeSingle nodeToBeCompared = node.next;
        NodeSingle nodeWhosNextIsToBeReplaced = node;
        while (node.next != null) {
            while (nodeToBeCompared.next != null && nodeToBeCompared.value != node.value) {
                nodeWhosNextIsToBeReplaced = nodeToBeCompared;
                nodeToBeCompared = nodeToBeCompared.next;
            }
            if (nodeToBeCompared.value == node.value) {
                if (nodeToBeCompared.next != null) {
                    nodeWhosNextIsToBeReplaced.next = nodeToBeCompared.next;
                    nodeToBeCompared = nodeWhosNextIsToBeReplaced.next;
                } else {
                    nodeWhosNextIsToBeReplaced.next = null;
                    node = node.next;
                    nodeToBeCompared = node.next;
                }
            } else {
                node = node.next;
                nodeToBeCompared = node.next;
                nodeWhosNextIsToBeReplaced = node;
            }
        }
    }

    public static void removeDuplicatesUsingHashTable(NodeSingle head) {
        /* In the solution they have implemented using the Java in-built hashtable and since i tried
         * with the hashtable implementation myself it looks long. also as stated earlier I had thought the previous
         * node needs to be maintained every check. */
        NodeSingle node = head.next;
        NodeSingle nodeWhosNextIsToBeReplaced = head;
        Table table = new Table("30");
        int index = 0;
        while (node != null) {
            index = (node.getValue() % table.HASH_DIVISOR) % table.TABLE_SIZE;
            if (index < 0) {
                index = node.getValue() % table.TABLE_SIZE;
            }
            // Compare with all the nodes to find out if it exists.
            // If not then insert the value and move on to the next value
            // If it exists then remove the value
            Node hashNode = table.nodes[ index ];
            while (hashNode != null && hashNode.getValue() != node.getValue()) {
                hashNode = hashNode.getNext();
            }
            if (hashNode != null && hashNode.getValue() == node.getValue()) {
                // Remove the node. i.e. point the previous node to the next of the current node
                // and proceed to the next node which is the replaced node
                nodeWhosNextIsToBeReplaced.next = node.next;
                node = nodeWhosNextIsToBeReplaced.next;
            } else {
                // Insert it to the hash table and move on to the next node
                table.nodes[ index ] = new Node(node.getValue());
                nodeWhosNextIsToBeReplaced = node;
                node = node.next;
            }
        }
    }
}

class Table {
    public int TABLE_SIZE;
    public Node[] nodes;
    public int HASH_DIVISOR;

    public Table(String size) {
        this.TABLE_SIZE = Integer.parseInt(size);
        this.nodes = new Node[ TABLE_SIZE ];
        //This below lines of code is not needed. Just for the clarity
        for (int i = 0; i < TABLE_SIZE; i++) {
            nodes[ i ] = null;
        }
        this.HASH_DIVISOR = primeNumber(TABLE_SIZE);
        if (this.HASH_DIVISOR == -1) {
            this.HASH_DIVISOR = this.TABLE_SIZE;
        }
    }

    private static int primeNumber(int size) {
        while (size < 99999999) {
            if (isPrime(size)) {
                return size;
            } else {
                size++;
            }
        }
        return -1;
    }

    private static boolean isPrime(int size) {
        for (int i = 2; i * i <= size; i++) {
            if (size % i == 0) {
                return false;
            }
        }
        return true;
    }
}

class Node {
    private int value;
    private Node next;

    public Node() {

    }

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}