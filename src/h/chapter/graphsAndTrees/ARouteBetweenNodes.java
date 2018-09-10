package h.chapter.graphsAndTrees;

import java.util.PriorityQueue;
import java.util.Queue;

public class ARouteBetweenNodes {
    /* Route Between Nodes
     Question: Given a directed graph, design an algorithm to find out whether there is a route between the
     two nodes*/

    public static void main(String[] args) {
        ZGraphUtils routeGraph = new ZGraphUtils(30);

        routeGraph.addVertex(7);
        routeGraph.addVertex(5);
        routeGraph.addVertex(4);
        routeGraph.addVertex(9);
        routeGraph.addVertex(1);
        routeGraph.addVertex(10);
        routeGraph.addVertex(12);
        routeGraph.addVertex(6);

        //U means uni-directional, B means bi-directional
        routeGraph.addEdgeBetween(7, 5, "U");
        routeGraph.addEdgeBetween(7, 9, "U");
        routeGraph.addEdgeBetween(7, 1, "U");
        routeGraph.addEdgeBetween(1, 10, "U");
        routeGraph.addEdgeBetween(1, 12, "U");
        routeGraph.addEdgeBetween(4, 7, "U");
        routeGraph.addEdgeBetween(10, 5, "U");
        routeGraph.addEdgeBetween(10, 7, "U");
        routeGraph.addEdgeBetween(10, 12, "U");
        routeGraph.addEdgeBetween(5, 9, "U");
        routeGraph.addEdgeBetween(9, 6, "U");

        routeGraph.showGraph();

        boolean result = doesARouteExistBetween(7, 9, routeGraph);
        System.out.println("Route Exists between " + 7 + " & " + 9 + "? " + result);
    }

    private static boolean doesARouteExistBetween(int vertexA, int vertexB, ZGraphUtils routeGraph) {
        Queue queue = new PriorityQueue();
        int indexOfVertexA = routeGraph.getHashTable().getHashIndex(vertexA);
        Vertex fromDataNode = routeGraph.getHashTable().getVertex(indexOfVertexA, vertexA);
        EdgeNode edgeNode = fromDataNode.getEdgesList().getHead();
        while (edgeNode.getNext() != null) {
            queue.add(edgeNode.getNext().getData());
            edgeNode = edgeNode.getNext();
        }
        fromDataNode.setVisited(true);

        while (!queue.isEmpty()) {
            int data = (int) queue.poll();
            int indexOfVertex = routeGraph.getHashTable().getHashIndex(data);
            Vertex vertex = routeGraph.getHashTable().getVertex(indexOfVertex, data);
            if (vertex.getData() == vertexB) {
                return true;
            }
            vertex.setVisited(true);
            EdgeNode edge = vertex.getEdgesList().getHead();
            while (edge.getNext() != null) {
                int indexOfEdgeVertex = routeGraph.getHashTable().getHashIndex(edge.getNext().getData());
                Vertex edgeVertex = routeGraph.getHashTable().getVertex(indexOfEdgeVertex, edge.getNext().getData());
                if (!edgeVertex.isVisited()) {
                    queue.add(edgeVertex.getData());
                }
                edge = edge.getNext();
            }
        }
        return false;
    }
}
