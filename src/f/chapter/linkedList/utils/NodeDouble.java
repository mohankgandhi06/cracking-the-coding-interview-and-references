package f.chapter.linkedList.utils;

public class NodeDouble {
    public int value;
    public NodeDouble next;
    public NodeDouble previous;

    public NodeDouble(int value) {

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public NodeDouble getNext() {
        return next;
    }

    public void setNext(NodeDouble next) {
        this.next = next;
    }

    public NodeDouble getPrevious() {
        return previous;
    }

    public void setPrevious(NodeDouble previous) {
        this.previous = previous;
    }
}
