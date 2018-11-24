package n.chapter.sortingAndSearching;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class JRankFromStream {
    /* Rank from Stream
     * Question: Imagine you are reading in a stream of integers. Periodically, you wish to be able to lookup the rank of the number
     * x(the number of values less than or equal to x). Implement the data structure and algorithms to support these operations
     * That is, implement the method track(int x) which is called when each number is generated, and the method getRankOfNumber(int x),
     * which returns the number of values less than or equal to x (not including itself)
     * EXAMPLE
     * Stream (in order of appearance): 5, 1, 4, 4, 5, 9, 7, 13, 3
     * getRankOfNumber(1) = 0
     * getRankOfNumber(3) = 1
     * getRankOfNumber(4) = 3 */

    public static BinarySearchTree binarySearchTree = new BinarySearchTree();
    public static List<Node> rankingQueueMain = new ArrayList<>();

    public static void main(String[] args) {
        int[] array = new int[]{5, 1, 4, 4, 5, 9, 7, 13, 3};
        rankFromStream(array);
        Node node = binarySearchTree.getRootNode();
        Queue<Node> queue = new LinkedList();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node tempNode = queue.poll();
            if (tempNode != null) {
                System.out.println("Node: " + tempNode.getData() + " Rank: " + tempNode.getRank());
                if (tempNode.getLeftNode() != null) {
                    queue.offer(tempNode.getLeftNode());
                    System.out.println("    Left Node: " + tempNode.getLeftNode().getData());
                }
                if (tempNode.getRightNode() != null) {
                    queue.offer(tempNode.getRightNode());
                    System.out.println("    Right Node: " + tempNode.getRightNode().getData());
                }
            }
        }
    }

    private static void rankFromStream(int[] array) {
        int i = 0;
        while (i < array.length) {
            Node newNode = new Node(array[ i ], i + 1);
            Node node = binarySearchTree.getRootNode();
            if (node == null) {
                binarySearchTree.setRootNode(newNode);
            }
            Queue<Node> queue = new LinkedList();
            while (node != null) {
                if (node.getData() >= newNode.getData()) {
                    //STEP 1: EXCHANGED RANK
                    int temp = node.getRank();
                    node.setRank(newNode.getRank());
                    newNode.setRank(temp);
                    queue.offer(node);
                    if (node.getLeftNode() != null) {
                        node = node.getLeftNode();
                    } else if (node.getLeftNode() == null) {
                        node.setLeftNode(newNode);
                        break;
                    }
                } else if (node.getData() < newNode.getData() && node.getRank() < newNode.getRank()) {
                    //STEP 1: DON'T EXCHANGE RANK, JUST MOVE TO THE RIGHT
                    if (node.getRightNode() != null) {
                        node = node.getRightNode();
                    } else if (node.getRightNode() == null) {
                        node.setRightNode(newNode);
                        break;
                    }
                } else if (node.getData() < newNode.getData() && node.getRank() > newNode.getRank()) {
                    //STEP 1: EXCHANGE RANK, AND JUST MOVE TO THE RIGHT
                    int temp = node.getRank();
                    node.setRank(newNode.getRank());
                    newNode.setRank(temp);
                    queue.offer(node);
                    if (node.getRightNode() != null) {
                        node = node.getRightNode();
                    }
                    if (node.getRightNode() == null) {
                        node.setRightNode(newNode);
                        break;
                    }
                }
            }
            //Algorithm for ranking
            while (!queue.isEmpty()) {
                Node r = queue.poll();
                //r node has the rank which is to be transferred to the children
                int rankToBeTransferred = r.getRank();
                Node nodeOne = r.getRightNode();
                rankingQueueMain = new ArrayList();
                //Take all the right children in the depth first pattern
                inOrderTraversal(nodeOne, rankToBeTransferred - 1);
                /*for (int l = 0; l < rankingQueueMain.size(); l++) {
                    System.out.println("------    Node: " + rankingQueueMain.get(l).getData() + " Rank: " + rankingQueueMain.get(l).getRank());
                }*/
                if (!rankingQueueMain.isEmpty()) {
                    r.setRank(rankingQueueMain.get(0).getRank());
                    for (int k = 0; k < rankingQueueMain.size() - 1; k++) {
                        rankingQueueMain.get(k).setRank(rankingQueueMain.get(k + 1).getRank());
                    }
                    rankingQueueMain.get(rankingQueueMain.size() - 1).setRank(rankToBeTransferred);
                }
            }
            i++;
        }
    }

    private static void inOrderTraversal(Node node, int rankToBeTransferred) {
        if (node != null && node.getRank() <= rankToBeTransferred) {
            inOrderTraversal(node.getLeftNode(), rankToBeTransferred);
            visit(node, rankToBeTransferred);
            inOrderTraversal(node.getRightNode(), rankToBeTransferred);
        }
    }

    private static void visit(Node node, int rankToBeTransferred) {
        if (node.getRank() <= rankToBeTransferred) {
            rankingQueueMain.add(node);
        }
    }
}

class Node {
    private int data;
    private int rank;
    private Node leftNode;
    private Node rightNode;

    public Node(int data, int rank) {
        this.data = data;
        this.rank = rank;
    }

    public Node() {

    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}

class BinarySearchTree {
    private Node rootNode;

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }
}