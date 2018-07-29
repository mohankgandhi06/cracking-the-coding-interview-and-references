package f.chapter.linkedList;

public class HLoopDetection {
    /* Question:
     Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop
     Definition:
     Circular Linked List: A (Corrupt) linked list in which a node's next pointer points to an earlier node, so as to
     make a loop in the linked list */

    public static void main(String[] args) {
        CorruptLinkedList list = new CorruptLinkedList();
        list.corruptIt('C', 'L');
        showList(list);
        CharacterNode node = detectTheLoopsStartingNode(list);
        if (node!=null){
            System.out.println("Corrupted Node Found:" +node.getData());
        } else {
            System.out.println("List is not corrupted");
        }
    }

    public static CharacterNode detectTheLoopsStartingNode(CorruptLinkedList list) {
        char[] table = new char[ 26 ];
        CharacterNode node = list.getHead();
        while (node.getNext() != null) {
            int index = hashFunction(node.getNext().getData());
            if (table[ index ] != node.getNext().getData()) {
                table[ index ] = node.getNext().getData();
                node = node.getNext();
            } else {
                return node.getNext();
            }
        }
        return null;
    }

    public static void showList(CorruptLinkedList list) {
        int count = 0;
        CharacterNode node = list.getHead();
        while (node.getNext() != null) {
            if (node.getNext().getData() == 'C') {
                count++;
            }
            if (count < 2) {
                System.out.println("-> " + node.getNext().getData());
                node = node.getNext();
            } else if (count == 2) {
                System.out.println("-> " + node.getNext().getData());
                return;
            }
        }
    }

    public static int hashFunction(char data) {
        return data % 26;
    }
}

class CorruptLinkedList {
    //It is considered corrupt it is like a hybrid of normal linked list, but it becomes circular after a certain node
    private CharacterNode head;

    private String value = "A";

    public CorruptLinkedList() {
        this.head = new CharacterNode();
    }

    public void insert(CharacterNode nodeToBeInserted) {
        CharacterNode node = this.getHead();
        while (node.getNext() != null) {
            node = node.getNext();
        }
        node.setNext(nodeToBeInserted);
    }

    public void corruptIt(char corruptStartingNodeCharacter, char corruptItBeforeThisCharacter) {
        CharacterNode node = new CharacterNode(corruptStartingNodeCharacter);
        while (!value.equalsIgnoreCase(Character.toString(corruptItBeforeThisCharacter))) {
            if (value.equalsIgnoreCase(Character.toString(corruptStartingNodeCharacter))) {
                insert(node);
                int charValue = value.charAt(0);
                this.setValue(String.valueOf((char) (charValue + 1)));
                continue;
            }
            insert(new CharacterNode(value.charAt(0)));
            int charValue = value.charAt(0);
            this.setValue(String.valueOf((char) (charValue + 1)));
        }
        insert(node);
    }

    public CharacterNode getHead() {
        return head;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

class CharacterNode {
    private char data;
    private CharacterNode next;

    public CharacterNode() {

    }

    public CharacterNode(char data) {
        this.data = data;
        this.next = null;
    }

    public char getData() {
        return data;
    }

    public CharacterNode getNext() {
        return next;
    }

    public void setNext(CharacterNode next) {
        this.next = next;
    }
}