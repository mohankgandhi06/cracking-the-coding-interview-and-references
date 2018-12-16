package f.chapter.linkedList;

public class GIntersection {

    /* Question:
    Intersection: Given two singly linked lists determine if the two lists intersect. Return the intersecting node.
    Note that the intersection is defined based on the reference not value. (i.e.) If the Kth node of the first linked list
    is the exact same node (by reference) as the Jth node of the second linked list, they are considered intersecting.
    * */

    public static void main(String[] args) {
        SingleNode node = new SingleNode(7);
        SinglyLinkedList listOne = new SinglyLinkedList();
        listOne.insert(3);
        listOne.insert(1);
        listOne.insert(5);
        listOne.insert(9);
        listOne.insert(node);
        listOne.insert(2);

        SinglyLinkedList listTwo = new SinglyLinkedList();
        listTwo.insert(4);
        listTwo.insert(6);
        listTwo.insert(node);
        listTwo.insert(1);

        /*//Two separate lists
        SinglyLinkedList listOne = new SinglyLinkedList();
        listOne.insert(3);
        listOne.insert(1);
        listOne.insert(5);
        listOne.insert(9);
        listOne.insert(7);
        listOne.insert(2);

        SinglyLinkedList listTwo = new SinglyLinkedList();
        listTwo.insert(4);
        listTwo.insert(6);
        listTwo.insert(7);
        listTwo.insert(1);*/

        System.out.println("List One: ");
        showList(listOne);
        System.out.println("List Two: ");
        showList(listTwo);
        //If you check the node with the value 99 here it is considered "intersecting" since even though
        // we inserted two additional in two separate lists (listOne and listTwo). When to try to show the list it is
        // fetching combined four values which has been inserted after it.
        SingleNode intersection = intersectionOptimal(listOne, listTwo);
        if (intersection == null) {
            System.out.println("No Match");
        } else {
            System.out.println("Match Found: " + intersection.getData());
        }
    }

    /* Optimal Implementations */
    private static SingleNode intersectionOptimal(SinglyLinkedList listOne, SinglyLinkedList listTwo) {
        if (listOne == null || listTwo == null) {
            return null;
        }
        TailAndSize one = getTailAndSize(listOne.getHead());
        TailAndSize two = getTailAndSize(listTwo.getHead());

        /* If the tail of two list is not same then absolutely there is no chance of intersection
         * So we can return by checking for this */
        if (one.node != two.node) {
            return null;
        }

        SingleNode longer = one.size > two.size ? listOne.getHead() : listTwo.getHead();
        SingleNode shorter = one.size > two.size ? listTwo.getHead() : listOne.getHead();
        int chopOffCount = Math.abs(one.size - two.size);
        longer = chopOff(longer, chopOffCount);
        while (shorter != longer) {
            shorter = shorter.getNext();
            longer = longer.getNext();
        }
        return longer;
    }

    private static TailAndSize getTailAndSize(SingleNode node) {
        if (node == null) return null;
        int count = 1;
        SingleNode head = node;
        while (head.getNext() != null) {
            count++;
            head = head.getNext();
        }
        return new TailAndSize(head, count);
    }

    private static SingleNode chopOff(SingleNode longer, int chopOffCount) {
        while (chopOffCount > 0 && longer != null) {
            longer = longer.getNext();
            chopOffCount--;
        }
        return longer;
    }

    /* Earlier Implementations */
    public static SingleNode intersection(SinglyLinkedList listOne, SinglyLinkedList listTwo) {
        SingleNode nodeOne = listOne.getHead();
        while (nodeOne.getNext() != null) {
            SingleNode nodeTwo = listTwo.getHead();
            while (nodeTwo.getNext() != null) {
                if (nodeOne.getNext() == nodeTwo.getNext()) {
                    //We are checking if they are pointing to same address location
                    return nodeOne.getNext();
                }
                nodeTwo = nodeTwo.getNext();
            }
            nodeOne = nodeOne.getNext();
        }
        return null;
    }

    public static void showList(SinglyLinkedList list) {
        SingleNode node = list.getHead();
        while (node.getNext() != null) {
            System.out.println("-> " + node.getNext().data);
            node = node.getNext();
        }
    }
}

class SinglyLinkedList {
    private SingleNode head;

    public SinglyLinkedList() {
        this.head = new SingleNode();
    }

    /*public SinglyLinkedList(int[] dataArray) {

    }*/

    public void insert(int data) {
        SingleNode node = this.head;
        while (node.next != null) {
            node = node.getNext();
        }
        node.setNext(new SingleNode(data));
    }

    public void insert(SingleNode nodeToBeInserted) {
        SingleNode node = this.head;
        while (node.next != null) {
            node = node.getNext();
        }
        node.setNext(nodeToBeInserted);
    }

    public SingleNode getHead() {
        return head;
    }

    public void setHead(SingleNode head) {
        this.head = head;
    }
}

class SingleNode {
    public int data;
    public SingleNode next;

    public SingleNode() {
        this.data = -9999;
        this.next = null;
    }

    public SingleNode(int data) {
        this.data = data;
        this.next = null;
    }

    public int getData() {
        return data;
    }

    public SingleNode getNext() {
        return next;
    }

    public void setNext(SingleNode next) {
        this.next = next;
    }
}

class TailAndSize {
    public SingleNode node;
    public int size;

    public TailAndSize() {

    }

    public TailAndSize(SingleNode node, int size) {
        this.node = node;
        this.size = size;
    }
}