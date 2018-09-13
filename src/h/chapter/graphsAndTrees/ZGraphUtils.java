package h.chapter.graphsAndTrees;

public class ZGraphUtils {
    private HashTable hashTable;
    /*private int hashTableSize;*/

    public ZGraphUtils(int size) {
        this.hashTable = new HashTable(size);
        /*this.hashTableSize = size;*/
    }

    public void addVertex(int data) {
        Vertex vertex = new Vertex(data);
        //Find Hash for the data and go to the location in the hash and find the location where we can place it
        int index = this.hashTable.getHashIndex(vertex.getData());
        Vertex node = this.hashTable.getVertices()[ index ];
        while (node.getNext() != null) {
            node = node.getNext();
        }
        node.setNext(vertex);
    }

    public void addEdgeBetween(int fromData, int toData, String direction) {
        int fromDataIndex = this.hashTable.getHashIndex(fromData);
        int toDataIndex = this.hashTable.getHashIndex(toData);
        Vertex fromDataNode = this.hashTable.getVertex(fromDataIndex, fromData);
        Vertex toDataNode = this.hashTable.getVertex(toDataIndex, toData);

        if (direction.equalsIgnoreCase("U")) {
            EdgeNode node = fromDataNode.getEdgesEnd();
            node.setNext(new EdgeNode(toData));
        } else if (direction.equalsIgnoreCase("B")) {

        } else {
            System.out.println("Direction variable is not correct please use either U (Uni) or B (Bi)");
        }
    }

    public void showGraph() {
        for (int i = 0; i < this.hashTable.getVertices().length; i++) {
            System.out.println("::::  " + i + "  ::::");
            Vertex node = this.hashTable.getVertices()[ i ].getNext();
            while (node != null) {
                System.out.print(node.getData() + " ");
                EdgeNode edgeNode = node.getEdgesList().getHead();
                while (edgeNode.getNext() != null) {
                    System.out.print(edgeNode.getNext().getData() + "-> ");
                    edgeNode = edgeNode.getNext();
                }
                node = node.getNext();
            }
            System.out.println("");
            System.out.println("");
        }
    }

    public HashTable getHashTable() {
        return hashTable;
    }

    public void setHashTable(HashTable HashTable) {
        this.hashTable = HashTable;
    }
}

class HashTable {
    private Vertex[] vertices;
    private int HASH_DIVISOR;

    public HashTable(int size) {
        this.vertices = new Vertex[ size ];
        // Setting the head node for all the hashtable indexes
        for (int i = 0; i < this.vertices.length; i++) {
            this.vertices[ i ] = new Vertex();
        }
        this.HASH_DIVISOR = primeNumber(size);
        if (this.HASH_DIVISOR == -1) {
            this.HASH_DIVISOR = size;
        }
    }

    public int getHashIndex(int data) {
        return data % this.HASH_DIVISOR;
    }

    /* Parameter 1 - Retrieve from this Index, Parameter 2 - data in integer */
    public Vertex getVertex(int fromDataIndex, int data) {
        Vertex vertex = this.getVertices()[ fromDataIndex ];
        while (vertex != null && vertex.getData() != data) {
            vertex = vertex.getNext();
        }
        if (vertex == null) {
            return null;
        } else {
            return vertex;
        }
    }

    private static int primeNumber(int size) {
        while (size < 99999999) {
            if (isPrime(size)) {
                return size;
            } else {
                size++;
            }
        }
        return -1;
    }

    private static boolean isPrime(int size) {
        for (int i = 2; i * i <= size; i++) {
            if (size % i == 0) {
                return false;
            }
        }
        return true;
    }

    public Vertex[] getVertices() {
        return vertices;
    }
}

class Vertex {
    private int data;
    private Vertex next;
    private EdgesList edgesList;
    private boolean visited;

    public Vertex(int data) {
        this.data = data;
        this.next = null;
        this.edgesList = new EdgesList();
        this.visited = false;
    }

    //Head Vertex
    public Vertex() {
        this.data = 9999;
        this.next = null;
        this.edgesList = new EdgesList();
        this.visited = false;
    }

    public EdgeNode getEdgesEnd() {
        EdgeNode node = this.getEdgesList().getHead();
        while (node.getNext() != null) {
            node = node.getNext();
        }
        return node;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Vertex getNext() {
        return next;
    }

    public void setNext(Vertex next) {
        this.next = next;
    }

    public EdgesList getEdgesList() {
        return edgesList;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}

class EdgesList {
    private EdgeNode head;

    public EdgesList() {
        this.head = new EdgeNode();
    }

    public EdgeNode getHead() {
        return head;
    }

    public void setHead(EdgeNode head) {
        this.head = head;
    }
}

class EdgeNode {
    private int data;
    private EdgeNode next;

    // Head Node
    public EdgeNode() {
        this.data = -9999;
        this.next = null;
    }

    public EdgeNode(int data) {
        this.data = data;
        this.next = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public EdgeNode getNext() {
        return next;
    }

    public void setNext(EdgeNode next) {
        this.next = next;
    }
}