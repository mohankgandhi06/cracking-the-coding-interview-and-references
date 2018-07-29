package f.chapter.linkedList;

public class GIntersection {

    /* Question:
    Intersection: Given two singly linked lists determine if the two lists intersect. Return the intersecting node.
    Note that the intersection is defined based on the reference not value. (i.e.) If the Kth node of the first linked list
    is the exact same node (by reference) as the Jth node of the second linked list, they are considered intersecting.
    * */

    public static void main(String[] args) {
        SingleNode node = new SingleNode(99);
        SinglyLinkedList listOne = new SinglyLinkedList();
        listOne.insert(6);
        listOne.insert(5);
        listOne.insert(22);
        listOne.insert(14);
        listOne.insert(4);
        listOne.insert(node);
        listOne.insert(56);
        listOne.insert(23);

        SinglyLinkedList listTwo = new SinglyLinkedList();
        listTwo.insert(6);
        listTwo.insert(5);
        listTwo.insert(22);
        listTwo.insert(node);
        listOne.insert(14);
        listOne.insert(56);
        System.out.println("List One: ");
        showList(listOne);
        System.out.println("List Two: ");
        showList(listTwo);
        //If you check the node with the value 99 here it is considered "intersecting" since even though
        // we inserted two additional in two separate lists (listOne and listTwo). When to try to show the list it is
        // fetching combined four values which has been inserted after it.
        SingleNode intersection = intersection(listOne, listTwo);
        if (intersection == null) {
            System.out.println("No Match");
        } else {
            System.out.println("Match Found: " + intersection.getData());
        }
    }

    public static SingleNode intersection(SinglyLinkedList listOne, SinglyLinkedList listTwo) {
        SingleNode nodeOne = listOne.getHead();
        while (nodeOne.getNext() != null) {
            SingleNode nodeTwo = listTwo.getHead();
            while (nodeTwo.getNext() != null) {
                if (nodeOne.getNext() == nodeTwo.getNext()) {
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