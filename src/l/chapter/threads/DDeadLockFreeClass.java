package l.chapter.threads;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DDeadLockFreeClass {
    /* Deadlock is a condition where one thread is waiting for the resource
     * to be given to it by another thread, which in turn is waiting for a
     * resource being given to it which is being held the other. In this
     * case neither of them will give back the resource. */

    public static Graph graph = new Graph();

    public static void main(String[] args) {
        Lock a = new Lock(new int[]{1, 2, 3, 4});
        Lock b = new Lock(new int[]{1, 3, 5});
        Lock c = new Lock(new int[]{7, 5, 9, 2});

        List<Lock> lockList = new ArrayList<>();
        lockList.add(a);
        lockList.add(b);
        lockList.add(c);

        buildGraph(lockList);
        graph.displayGraph();

        boolean isDeadLockFree = graph.findCycle();
        System.out.println("isDeadLockFree? " + !isDeadLockFree);
    }

    private static void buildGraph(List<Lock> lockList) {
        for (Lock lock : lockList) {
            for (int i = 0; i < lock.getLockOrder().length - 1; i++) {
                graph.addTableEntry(new Edge(new Vertex(lock.getLockOrder()[ i ]), new Vertex(lock.getLockOrder()[ i + 1 ])));
            }
        }
    }

}

class Lock {
    private int[] lockOrder;

    public Lock(int[] lockOrder) {
        this.lockOrder = lockOrder;
    }

    public int[] getLockOrder() {
        return lockOrder;
    }
}

class Graph {
    private ThreadHashTable table;

    public Graph() {
        this.table = new ThreadHashTable();
    }

    public void addTableEntry(Edge edge) {
        table.findLocationAndAdd(edge);
    }

    public void displayGraph() {
        for (TableEntry t : table.getTableEntries()) {
            if (t != null) {
                System.out.print(t.getVertex().getData() + " is Visited: ");
                System.out.print(t.getVertex().isVisited() + " -> ");
                Iterator p = t.getLinkedList().iterator();
                while (p.hasNext()) {
                    System.out.print((Integer) p.next() + " ");
                }
                System.out.println();
            }
        }
    }

    public boolean findCycle() {
        System.out.println("findCycle");
        Queue queue = new ConcurrentLinkedQueue();
        queue.offer(table.getTableEntries()[ 1 ]);
        return cyclicCall(queue);
    }

    private boolean cyclicCall(Queue queue) {
        while (!queue.isEmpty()) {
            TableEntry entry = (TableEntry) queue.poll();
            if (entry.getVertex().isVisited()) {
                return true;
            } else {
                Iterator p = entry.getLinkedList().iterator();
                while (p.hasNext()) {
                    Integer value = (Integer) p.next();
                    TableEntry temp = table.findTableEntry(value);
                    if(!queue.contains(temp)) {
                        queue.offer(temp);
                    }
                }
                entry.getVertex().setVisited(true);
            }
        }
        return false;
    }
}

class ThreadHashTable {
    /* Here we are trying to use the hash to find only unique positions for
     * the vertex. since we don't want two data's edges to be together.
     * It will lead to confusion. */
    private TableEntry[] tableEntries;

    public ThreadHashTable() {
        this.tableEntries = new TableEntry[ 13 ];
    }

    /* Method will find the location for the new vertex data and then get the
     * edges of it and add the new edge along with it. */
    public void findLocationAndAdd(Edge edge) {
        int index = getHashIndex(edge.getFrom().getData());
        int i = 1;
        System.out.println("Edge From: " + edge.getFrom().getData());
        System.out.println("Edge To: " + edge.getTo().getData());
        System.out.println();
        while (i < tableEntries.length && tableEntries[ index ] != null && tableEntries[ index ].getVertex().getData() != edge.getFrom().getData()) {
            index = getHashIndex(edge.getFrom().getData() + i);
            i++;
        }
        // New Edge Alone
        if (tableEntries[ index ] == null) {
            tableEntries[ index ] = new TableEntry(new Vertex(edge.getFrom().getData()), new LinkedList());
            tableEntries[ index ].getLinkedList().add(edge.getTo().getData());
        }
        // New Entry
        else if (tableEntries[ index ].getVertex().getData() == edge.getFrom().getData()) {
            tableEntries[ index ].getLinkedList().add(edge.getTo().getData());
        }
        // Handling Out of Bound
        else if (tableEntries[ index ] != null) {
            System.out.println("Could not add this element since out of space: " + edge.getFrom().getData());
        }

        // Create a new Vertex for the Edge's To Vertex if not present already
        addEdgeIfNotAlreadyPresent(edge.getTo().getData());
    }

    public void addEdgeIfNotAlreadyPresent(int data) {
        int index = getHashIndex(data);
        int i = 1;
        while (i < tableEntries.length && tableEntries[ index ] != null && tableEntries[ index ].getVertex().getData() != data) {
            index = getHashIndex(data + i);
            i++;
        }
        // New Edge Alone
        if (tableEntries[ index ] == null) {
            tableEntries[ index ] = new TableEntry(new Vertex(data), new LinkedList());
        }
        // New Entry
        else if (tableEntries[ index ].getVertex().getData() == data) {
            //Do nothing
        }
        // Handling Out of Bound
        else if (tableEntries[ index ] != null) {
            System.out.println("Could not add this element since out of space: " + data);
        }
    }

    public TableEntry findTableEntry(int value) {
        int index = getHashIndex(value);
        int i = 1;
        while (i < tableEntries.length && tableEntries[ index ].getVertex().getData() != value) {
            index = getHashIndex(value + i);
            i++;
        }
        // Matching Entry
        if (tableEntries[ index ].getVertex().getData() == value) {
            return tableEntries[ index ];
        }
        // Handling Out of Bound
        else if (tableEntries[ index ] != null) {
            System.out.println("Could not find this element" + value);
        }
        return null;
    }

    private int getHashIndex(int data) {
        return data % 13;
    }

    public TableEntry[] getTableEntries() {
        return tableEntries;
    }
}

class TableEntry {
    private Vertex vertex;
    private LinkedList linkedList;

    public TableEntry(Vertex vertex, LinkedList linkedList) {
        this.vertex = vertex;
        this.linkedList = linkedList;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public LinkedList getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(LinkedList linkedList) {
        this.linkedList = linkedList;
    }
}

class Vertex {
    private int data;
    private boolean visited;

    public Vertex(int data) {
        this.data = data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}

class Edge {
    private Vertex from;
    private Vertex to;

    public Edge(Vertex from, Vertex to) {
        this.from = from;
        this.to = to;
    }

    public Vertex getFrom() {
        return from;
    }

    public Vertex getTo() {
        return to;
    }
}