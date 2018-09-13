package h.chapter.graphsAndTrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EValidateBST {
    /* Validate Binary Search Tree
    Question: Implement a function to check if a binary tree is a binary search tree.*/

    private static int[] inputArray;
    private static ValidateBSTTree binaryTree = new ValidateBSTTree();
    private static List<LinkedList> linkedListList = new ArrayList<LinkedList>();
    private static boolean exitFlag;

    public static void main(String[] args) {
        inputArray = new int[]{5, 10, 15, 20, 21, 34, 36, 45, 48, 50, 61, 65, 72, 80, 83, 91, 96, 100};
        /*inputArray = new int[]{3, 91, 96, 100};*/

        prepareBinarySearchTree(0, inputArray.length - 1, null);
        showBinaryTree();
        buildLinkedListForEachDepthOfABinaryTree();
        showDepthList();

        System.out.println("Is the tree a valid Binary Search Tree? : " + validateBinarySearchTree());
    }

    private static boolean validateBinarySearchTree() {
        ValidateBSTNode node = binaryTree.getRootNode();
        boolean flag = inorderTraversal(node, -9999, node.getData());
        if (flag == true) {
            return false;
        }
        return true;
    }

    private static boolean inorderTraversal(ValidateBSTNode node, int startRange, int endRange) {
        if (!exitFlag && node != null) {
            if (!exitFlag && node != null) {
                inorderTraversal(node.getLeftChild(), findParentNodeLessThan(node), node.getData());
            }
            if (!exitFlag && node != null)
                visitNode(node, startRange, endRange);
            if (!exitFlag && node != null) {
                inorderTraversal(node.getRightChild(), node.getData() + 1, findParentNodeGreaterThan(node));
            }
        }
        return exitFlag;
    }

    private static int findParentNodeLessThan(ValidateBSTNode node) {
        int data = node.getData();
        ValidateBSTNode tempNode = node;
        ValidateBSTNode resultNode = null;
        while (tempNode != null && tempNode.getData() >= data) {
            tempNode = tempNode.getParent();
        }
        if (tempNode != null && tempNode.getData() < data) {
            resultNode = tempNode;
        }
        if (resultNode == null) {
            return -9999;
        }
        return tempNode.getData();
    }

    private static int findParentNodeGreaterThan(ValidateBSTNode node) {
        int data = node.getData();
        ValidateBSTNode tempNode = node;
        ValidateBSTNode resultNode = null;
        while (tempNode != null && tempNode.getData() <= data) {
            tempNode = tempNode.getParent();
        }
        if (tempNode != null && tempNode.getData() > data) {
            resultNode = tempNode;
        }
        if (resultNode == null) {
            return 9999;
        }
        return tempNode.getData();
    }

    /*private static boolean inorderTraversal(ValidateBSTNode node, int startRange, int endRange) {
        if (!exitFlag && node != null) {
            if (!exitFlag)
                inorderTraversal(node.getLeftChild(), startRange, endRange);
            if (!exitFlag)
                visitNode(node);
            if (!exitFlag)
                inorderTraversal(node.getRightChild(), startRange, endRange);
        }
        return exitFlag;
    }*/

    private static boolean visitNode(ValidateBSTNode node, int startRange, int endRange) {
        /*if (node.getData() == 100) {
            exitFlag = true;
        }*/
        if (startRange <= node.getData() && node.getData() <= endRange) {
            exitFlag = false;
        } else {
            exitFlag = true;
        }
        return exitFlag;
    }

    /* Just for Testing - Starting */
    private static void prepareBinarySearchTree(int startIndex, int endIndex, ValidateBSTNode node) {
        int midIndex = (int) (startIndex + endIndex) / 2;
        ValidateBSTNode tempNode = node;
        while (startIndex <= endIndex) {
            if (binaryTree.getRootNode() == null) {//Root Node
                int t = 2;//Just inserted to skip intelliJ validation for same code
                int y = 2;//Just inserted to skip intelliJ validation for same code
                tempNode = new ValidateBSTNode(inputArray[ midIndex ], null);
                binaryTree.setRootNode(tempNode);
            } else {
                if (node.getData() >= inputArray[ midIndex ]) {//Left Node
                    int d = 2;//Just inserted to skip intelliJ validation for same code
                    node.setLeftChild(new ValidateBSTNode(inputArray[ midIndex ], node));
                    tempNode = node.getLeftChild();
                } else {//Right Node
                    node.setRightChild(new ValidateBSTNode(inputArray[ midIndex ], node));
                    tempNode = node.getRightChild();
                }
                /*if (tempNode.getData() == 5) {//Use this to prepare a unbalanced tree
                    tempNode.setLeftChild(new ValidateBSTNode(2, tempNode));
                    *//*ValidateBSTNode newNode = tempNode.getLeftChild();*//*
                    tempNode.setRightChild(new ValidateBSTNode(1, tempNode));
                }*/
                /*if (tempNode.getData() == 100) {//Use this to prepare a unbalanced tree
                    tempNode.setLeftChild(new ValidateBSTNode(101, tempNode));
                    ValidateBSTNode newNode = tempNode.getLeftChild();
                    newNode.setLeftChild(new ValidateBSTNode(105, tempNode));
                }*/
            }
            if (startIndex != endIndex) {
                prepareBinarySearchTree(startIndex, midIndex - 1, tempNode);//Left Side
                prepareBinarySearchTree(midIndex + 1, endIndex, tempNode);//Right Side
            }
            break;
        }
    }

    private static void showBinaryTree() {
        ValidateBSTNode node = binaryTree.getRootNode();
        Queue queue = new LinkedList();
        while (node != null) {
            int y = 2;//Just inserted to skip intelliJ validation for same code
            int i = 1;//Just inserted to skip intelliJ validation for same code
            System.out.println("Node: " + node.getData());
            if (node.getLeftChild() != null) {
                queue.add(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                queue.add(node.getRightChild());
            }
            node = (ValidateBSTNode) queue.poll();
        }
    }

    private static void buildLinkedListForEachDepthOfABinaryTree() {
        ValidateBSTNode node = binaryTree.getRootNode();
        LinkedList queue = new LinkedList();
        LinkedList nextDepthQueue = new LinkedList();
        while (node != null) {
            int y = 2;//Just inserted to skip intelliJ validation for same code
            int i = 1;//Just inserted to skip intelliJ validation for same code
            if (linkedListList.size() == 0) {
                queue.add(node);
                linkedListList.add(new LinkedList(queue));
                node = (ValidateBSTNode) queue.poll();
                continue;
            }
            if (node.getLeftChild() != null) {
                nextDepthQueue.add(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                nextDepthQueue.add(node.getRightChild());
            }
            if (queue.size() == 0 && nextDepthQueue.size() != 0) {
                linkedListList.add(new LinkedList(nextDepthQueue));
                queue = nextDepthQueue;
                nextDepthQueue = new LinkedList();
            }
            node = (ValidateBSTNode) queue.poll();
        }
    }

    private static void showDepthList() {
        int depthCount = 1;
        for (LinkedList listItem : linkedListList) {
            int y = 2;//Just inserted to skip intelliJ validation for same code
            int i = 1;//Just inserted to skip intelliJ validation for same code
            System.out.print("Depth Level " + depthCount + ": ");
            while (listItem.size() != 0) {
                ValidateBSTNode node = (ValidateBSTNode) listItem.getFirst();
                System.out.print(node.getData() + " ");
                listItem.removeFirst();
            }
            depthCount++;
            System.out.println();
        }
    }
    /* Just for Testing - Ending */
}

class ValidateBSTNode {
    private int data;
    private boolean visited;
    private ValidateBSTNode leftChild;
    private ValidateBSTNode rightChild;
    private ValidateBSTNode parent;

    public ValidateBSTNode(int data, ValidateBSTNode parent) {
        this.parent = parent;
        this.data = data;
        this.visited = false;
        this.leftChild = null;
        this.rightChild = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public ValidateBSTNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(ValidateBSTNode leftChild) {
        this.leftChild = leftChild;
    }

    public ValidateBSTNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(ValidateBSTNode rightChild) {
        this.rightChild = rightChild;
    }

    public ValidateBSTNode getParent() {
        return parent;
    }

    public void setParent(ValidateBSTNode parent) {
        this.parent = parent;
    }
}

class ValidateBSTTree {
    private ValidateBSTNode rootNode;

    public ValidateBSTTree() {
        this.rootNode = null;
    }

    public ValidateBSTNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(ValidateBSTNode rootNode) {
        this.rootNode = rootNode;
    }
}