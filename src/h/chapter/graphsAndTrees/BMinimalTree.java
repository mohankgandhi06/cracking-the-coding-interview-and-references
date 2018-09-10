package h.chapter.graphsAndTrees;

import java.util.LinkedList;
import java.util.Queue;

public class BMinimalTree {
    /* Minimal Tree
     Question: Given a sorted (increasing order) array with unique integer elements, write an algorithm
     to create a binary search tree with minimal height*/
    private static BinaryTree binaryTree = new BinaryTree();
    private static int[] inputArray;

    public static void main(String[] args) {
        /*inputArray = new int[]{1, 3, 4, 6, 8, 10, 12, 15, 17, 18, 20, 21, 25, 28, 31};*/
        inputArray = new int[]{5, 10, 15, 20, 21, 34, 36, 45, 48, 50, 61, 65, 72, 80, 83, 91, 96, 100};
        /*inputArray = new int[]{5, 10, 15, 20, 21, 34, 36, 45};*/
        prepareBinarySearchTree(0, inputArray.length - 1, null);
        showBinaryTree();
    }

    private static void prepareBinarySearchTree(int startIndex, int endIndex, Node node) {
        int midIndex = (int) (startIndex + endIndex) / 2;
        Node tempNode = node;
        while (startIndex <= endIndex) {
            if (binaryTree.getRootNode() == null) {//Root Node
                tempNode = new Node(inputArray[ midIndex ]);
                binaryTree.setRootNode(tempNode);
            } else {
                if (node.getData() >= inputArray[ midIndex ]) {//Left Node
                    node.setLeftChild(new Node(inputArray[ midIndex ]));
                    tempNode = node.getLeftChild();
                } else {//Right Node
                    node.setRightChild(new Node(inputArray[ midIndex ]));
                    tempNode = node.getRightChild();
                }
            }
            if (startIndex != endIndex) {
                prepareBinarySearchTree(startIndex, midIndex - 1, tempNode);//Left Side
                prepareBinarySearchTree(midIndex + 1, endIndex, tempNode);//Right Side
            }
            break;
        }
    }

    private static void showBinaryTree() {
        Node node = binaryTree.getRootNode();
        Queue queue = new LinkedList();
        while (node != null) {
            System.out.println("Node: " + node.getData());
            if (node.getLeftChild() != null) {
                queue.add(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                queue.add(node.getRightChild());
            }
            node = (Node) queue.poll();
        }
    }
}

class BinaryTree {
    private Node rootNode;

    public BinaryTree() {
        this.rootNode = null;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }
}

class Node {
    private int data;
    private Node leftChild;
    private Node rightChild;

    public Node(int data) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
}