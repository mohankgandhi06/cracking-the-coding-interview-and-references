package f.chapter.linkedList.utils;

import java.util.ArrayList;
import java.util.List;

public class LinkedListUtils {
    //Singly Linked List
    //In the Singly Linked List the head cannot be removed since it is pointing to the first value
    //alone and it does not have any value by itself
    public static NodeSingle prepareSinglyLinkedList(String[] list) {
        NodeSingle head = new NodeSingle(-9999);
        int count = 0;
        for (String listItem : list) {
            insertSingle(listItem, head);
            count++;
        }
        System.out.println("Inserted " + count + " values in the list");
        return head;
    }

    public static void insertSingle(String listItem, NodeSingle head) {
        NodeSingle node = new NodeSingle(Integer.valueOf(listItem));
        NodeSingle newNodeFinder = head;
        while (newNodeFinder.next != null) {
            newNodeFinder = newNodeFinder.next;
        }
        newNodeFinder.next = node;
    }

    public boolean removeSingle(String listItem, NodeSingle head) {
        NodeSingle newNodeFinder = head;
        NodeSingle previousNode = head;
        while (newNodeFinder.next != null && newNodeFinder.value != Integer.valueOf(listItem)) {
            previousNode = newNodeFinder;
            newNodeFinder = newNodeFinder.next;
        }
        if (newNodeFinder.next == null) {
            return false;
        } else if (newNodeFinder.value == Integer.valueOf(listItem)) {
            previousNode.next = newNodeFinder.next;
            return true;
        }
        return false;
    }

    public NodeSingle searchSingle(String listItem, NodeSingle head) {
        NodeSingle newNodeFinder = head;
        while (newNodeFinder.next != null && newNodeFinder.value != Integer.valueOf(listItem)) {
            newNodeFinder = newNodeFinder.next;
        }
        if (newNodeFinder.value == Integer.valueOf(listItem)) {
            return newNodeFinder;
        } else if (newNodeFinder.next == null) {
            return null;
        }
        return null;
    }

    //Doubly Linked List
    public List<NodeDouble> prepareDoublyLinkedList(String[] list) {
        List<NodeDouble> headAndTail = new ArrayList<NodeDouble>();
        NodeDouble head = new NodeDouble(-9999);
        NodeDouble tail = new NodeDouble(-9999);
        int count = 0;
        for (String listItem : list) {
            insertDouble(listItem, head, tail);
            count++;
        }
        System.out.println("Inserted " + count + " values in the list");
        headAndTail.add(head);
        headAndTail.add(tail);
        return headAndTail;
    }

    //Insert at the last of the list
    public void insertDouble(String listItem, NodeDouble head, NodeDouble tail) {
        NodeDouble node = new NodeDouble(Integer.valueOf(listItem));
        NodeDouble newNodeFinder = head;
        while (newNodeFinder.next != null) {
            newNodeFinder = newNodeFinder.next;
        }
        newNodeFinder.next = node;
        tail = node;
    }

    //Find the node and point the previous and next to each other
    public boolean removeDouble(String listItem, NodeDouble head, NodeDouble tail) {
        NodeDouble newNodeFinderHead = head;
        NodeDouble newNodeFinderTail = tail;
        while ((newNodeFinderHead.next != newNodeFinderTail && newNodeFinderHead.value != Integer.valueOf(listItem))
                || (newNodeFinderTail.previous != newNodeFinderHead && newNodeFinderTail.value != Integer.valueOf(listItem))) {
            newNodeFinderTail = newNodeFinderTail.previous;
            newNodeFinderHead = newNodeFinderHead.next;
        }
        if (newNodeFinderHead.next == newNodeFinderTail || newNodeFinderTail.previous == newNodeFinderHead.next) {
            return false;
        } else if (newNodeFinderHead.value == Integer.valueOf(listItem)) {
            newNodeFinderHead.previous.next = newNodeFinderHead.next;
            newNodeFinderHead.next.previous = newNodeFinderHead.previous;
            return true;
        } else if (newNodeFinderTail.value == Integer.valueOf(listItem)) {
            newNodeFinderTail.previous.next = newNodeFinderTail.next;
            newNodeFinderTail.next.previous = newNodeFinderTail.previous;
            return true;
        }
        return false;
    }

    public NodeDouble searchDouble(String listItem, NodeDouble head, NodeDouble tail) {
        NodeDouble newNodeFinderHead = head;
        NodeDouble newNodeFinderTail = tail;
        while ((newNodeFinderHead.next != newNodeFinderTail && newNodeFinderHead.value != Integer.valueOf(listItem))
                || (newNodeFinderTail.previous != newNodeFinderHead && newNodeFinderTail.value != Integer.valueOf(listItem))) {
            newNodeFinderTail = newNodeFinderTail.previous;
            newNodeFinderHead = newNodeFinderHead.next;
        }
        if (newNodeFinderHead.next == newNodeFinderTail || newNodeFinderTail.previous == newNodeFinderHead.next) {
            return null;
        } else if (newNodeFinderHead.value == Integer.valueOf(listItem)) {
            return newNodeFinderHead;
        } else if (newNodeFinderTail.value == Integer.valueOf(listItem)) {
            return newNodeFinderTail;
        }
        return null;
    }
}