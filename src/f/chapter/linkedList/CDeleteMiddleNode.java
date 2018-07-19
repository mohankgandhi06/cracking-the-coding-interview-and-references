package f.chapter.linkedList;

public class CDeleteMiddleNode {
    public static void main(String[] args) {
        LinkedList list = new LinkedList(new int[]{9, 5, 4, 5, 8, 10, 16, 13, 66, 4});
        int countOfNodeToBePassed = list.count / 2;
        Node node = list.head;
        while (countOfNodeToBePassed > 0) {
            node = node.getNext();
            countOfNodeToBePassed--;
        }
        deleteTheMiddleNode(node);
        Node head = list.head;
        while (head.getNext() != null){
            System.out.println(":  "+head.getNext().getValue());
            head = head.getNext();
        }
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