package f.chapter.linkedList;

public class CDeleteMiddleNode {
    /* Question: Implement an algorithm to delete a node in the middle (i.e., any node but the first and last node,
    not necessarily the exact middle) of a singly linked list, given only access to that node. */

    public static void main(String[] args) {
        LinkedList list = new LinkedList(new int[]{9, 5, 4, 10, 16, 13, 66, 4});
        int countOfNodeToBePassed = list.count / 2;
        Node node = list.head;
        while (countOfNodeToBePassed > 0) {
            node = node.getNext();
            countOfNodeToBePassed--;
        }
        //deleteTheMiddleNode(node);
        deleteTheMiddleOptimal(node);
        Node head = list.head;
        while (head.getNext() != null) {
            System.out.println(":  " + head.getNext().getValue());
            head = head.getNext();
        }
    }

    /* Optimal Implementation */
    private static void deleteTheMiddleOptimal(Node nodeToDelete) {
        /* When we are deleting the node we just need to replace the current node's value with the next node
         * and replace current node's next node to be current's node's next's next node :) */
        if (nodeToDelete == null || nodeToDelete.getNext() == null) {
            return;
        }
        Node node = nodeToDelete;
        node.setValue(node.getNext().getValue());
        node.setNext(node.getNext().getNext());
    }

    public static void deleteTheMiddleNode(Node nodeToBeDeleted) {
        //Replace the current node with next node until the next is null
        Node node = nodeToBeDeleted;
        while (node.getNext().getNext() != null) {
            node.setValue(node.getNext().getValue());
            node = node.getNext();
        }
        node.setValue(node.getNext().getValue());
        node.setNext(null);
    }
}