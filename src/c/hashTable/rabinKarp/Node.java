package c.hashTable.rabinKarp;

public class Node {
    public int key;//Index value of the character in the string.
    public char value;//The character is saved while creating the node. This is done to eliminate using the initial character while comparing again the linked list
    public Node next;

    public Node() {

    }

    public Node(int key, char value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
