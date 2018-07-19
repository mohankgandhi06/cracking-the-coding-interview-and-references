package f.chapter.linkedList.utils;

public class NodeSingle {
    public int value;
    public NodeSingle next;

    public NodeSingle(int value) {
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public NodeSingle getNext() {
        return next;
    }

    public void setNext(NodeSingle next) {
        this.next = next;
    }
}
