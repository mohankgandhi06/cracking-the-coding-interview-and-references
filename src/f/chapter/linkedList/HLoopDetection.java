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
        if (node != null) {
            System.out.println("Corrupted Node Found: " + node.getData());
        } else {
            System.out.println("List is not corrupted");
        }
    }

    /* Optimal Implementation */
    private static CharacterNode detectTheLoopStartingNodeOptimal(CharacterNode node) {
        /* Like a car in a race track going in laps. even though they may be of different speed
         * they will meet at a point in the circle. We don't know the start of the loop and whether
         * the list is a loop in the first place. So we are launching two list using runner technique
         * to find out if they both collide. One is going one step while other goes two step at a time
         * 1) When the slow node enters the loop the fast loop will be k nodes ahead of it.
         * 2) When they meet they will be k nodes away from the start of the loop */
        CharacterNode slow = node;
        CharacterNode fast = node;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        if (fast == null || fast.getNext() == null) {//No Loop found
            return null;
        }
        slow = node;
        while (slow != fast) {
            slow = slow.getNext();
            fast = fast.getNext();
        }
        return fast;
    }

    /* Earlier Implementations */
    public static CharacterNode detectTheLoopsStartingNode(CorruptLinkedList list) {
        /* In this implementation we are actually checking the data to find out the loop
         * But actually we should have compared the reference. Other than that this algorithm
         * requires a hashtable to be maintained and what if the character are not A, B, C alone */
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