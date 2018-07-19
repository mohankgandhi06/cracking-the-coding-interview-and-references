package f.chapter.linkedList;

public class BReturnKthToLast {
    public static void main(String[] args) {
        LinkedList list = new LinkedList(new int[]{9, 5, 4, 5, 8, 10, 16, 13, 66, 4});
        //list.insert(6, list.head);
        Node element = findKthElementFromLastUsingCount(3, list);
        System.out.println(element.getValue());
        element = findKthElementFromLastWithoutUsingCount(6, list);
        System.out.println(element.getValue());
    }

    public static Node findKthElementFromLastUsingCount(int distanceAwayFromLast, LinkedList list) {
        int noOfNodesToMove = list.count - distanceAwayFromLast;
        Node node = list.head;
        while (noOfNodesToMove > 0) {
            node = node.getNext();
            noOfNodesToMove--;
        }
        return node;
    }

    public static Node findKthElementFromLastWithoutUsingCount(int distanceAwayFromLast, LinkedList list) {
        Node nodeForCount = list.head;
        int count = 0;
        while (nodeForCount.getNext() != null) {
            nodeForCount = nodeForCount.getNext();
            count++;
        }
        int noOfNodesToMove = count - distanceAwayFromLast;
        Node node = list.head;
        while (noOfNodesToMove > 0) {
            node = node.getNext();
            noOfNodesToMove--;
        }
        return node;
    }
}

class LinkedList {
    public Node head;
    public int count;

    public LinkedList() {
        head = new Node(-9999);
        count = 0;
    }

    public LinkedList(int[] listData) {
        head = new Node(-9999);
        count = 0;
        for (int i = 0; i < listData.length; i++) {
            insert(listData[ i ], head);
            count++;
        }
    }

    public void insert(int data, Node head) {
        Node newNode = new Node(data);
        while (head.getNext() != null) {
            head = head.getNext();
        }
        if (head.getNext() == null) {
            head.setNext(newNode);
        }
    }
}