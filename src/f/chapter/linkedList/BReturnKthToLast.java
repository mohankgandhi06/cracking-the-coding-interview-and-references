package f.chapter.linkedList;

public class BReturnKthToLast {
    /* Question: Implement an algorithm to find the kth to last element of a singly linked list */

    public static void main(String[] args) {
        LinkedList list = new LinkedList(new int[]{9, 5, 4, 5, 8, 10, 16, 13, 66, 4});
        //list.insert(6, list.head);
        /*Node element = findKthElementFromLastUsingCount(3, list);
        System.out.println(element.getValue());
        element = findKthElementFromLastWithoutUsingCount(0, list);
        System.out.println(element.getValue());*/
        Node element = findKthElementFromLastCheeky(list.head, -2);
        System.out.println((element != null) ? element.getValue() : "null");
    }

    /* Optimal Implementation */
    private static Node findKthElementFromLastOptimal(Node node, int kth) {
        Index index = new Index();
        return findKthElementFromLastOptimal(node.getNext(), kth, index);
    }

    private static Node findKthElementFromLastOptimal(Node node, int kth, Index index) {
        if (node == null) return null;
        Node result = findKthElementFromLastOptimal(node.getNext(), kth, index);
        if (result != null) return result;
        index.value = index.value + 1;
        if (index.value == kth) {
            return node;
        }
        return result;
    }

    /* More Indirect Approach - but a cheeky one */
    private static Node findKthElementFromLastCheeky(Node node, int kth) {
        if (kth < 0) return null;
        Node pointOne = node;
        Node pointTwo = node;
        //Move the pointTwo to be k distance apart from pointOne
        for (int i = 0; i < kth; i++) {
            if (pointTwo.getNext() == null) return null;
            pointTwo = pointTwo.getNext();
        }
        //Move each one until pointTwo reaches the end
        while (pointTwo.getNext() != null) {
            pointOne = pointOne.getNext();
            pointTwo = pointTwo.getNext();
        }
        return pointOne;
    }

    /* Earlier Implementations */
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

class Index {
    public int value = -1;
}