package h.chapter.graphsAndTrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DCheckBalanced {
    /* Check Balanced
     Question: Implement a function to check if a binary tree is balanced. For the purpose of this question
     a balanced tree is defined to be a tree such that heights of the two subtrees of any node never differ by
     more than one */

    private static int height = 0;
    private static int individualHeight = 0;
    private static boolean breakFlag = true;
    private static BinaryVisitTree binaryTree = new BinaryVisitTree();
    private static int[] inputArray;
    private static List<LinkedList> linkedListList = new ArrayList<LinkedList>();

    public static void main(String[] args) {
        inputArray = new int[]{5, 10, 15, 20, 21, 34, 36, 45, 48, 50, 61, 65, 72, 80, 83, 91, 96, 100};
        /*inputArray = new int[]{5, 10, 91, 96, 100};*/
        prepareBinarySearchTree(0, inputArray.length - 1, null);
        showBinaryTree();
        buildLinkedListForEachDepthOfABinaryTree();
        showDepthList();
        System.out.println("Is Balanced: " + isBalanced());
    }

    private static boolean isBalanced() {
        return recursiveCalls(binaryTree.getRootNode());
    }

    private static boolean recursiveCalls(VisitNode node) {
        if (node == null) return true;
        if (breakFlag) {
            visit(node);
            if (node.getLeftChild() != null && !node.getLeftChild().isVisited()) {
                recursiveCalls(node.getLeftChild());
            }
            if (node.getRightChild() != null && !node.getRightChild().isVisited()) {
                recursiveCalls(node.getRightChild());
            }

            if (isLeafNode(node)) {
                if (height != 0 && Math.abs(height - individualHeight) > 1) {
                    breakFlag = false;
                    return false;
                }
                //The following is to keep the minimum height since when comparing a leaf node with another node of size
                // difference 1 and then again comparing the next of again difference 1 will lead to mistake if we save the
                // value. If we keep the minimum height and then start comparing it will be correct
                if (height == 0 || individualHeight < height) {
                    height = individualHeight;
                } else {
                    height = individualHeight;
                }
                individualHeight--;
            } else if (isChildVisited(node)) {
                individualHeight--;
            }
            if (breakFlag) {
                return true;
            }
        }
        //Since it is a recursive function need to break only when breakFlag is false
        // If false then subsequent has to be returned false,and true if otherwise
        if (breakFlag) {
            return true;
        } else {
            return false;
        }
    }

    private static void visit(VisitNode node) {
        node.setVisited(true);
        individualHeight++;
    }

    private static boolean isLeafNode(VisitNode node) {
        return (node.getLeftChild() == null && node.getRightChild() == null);
    }

    private static boolean isChildVisited(VisitNode node) {
        if ((node.getLeftChild() != null && node.getLeftChild().isVisited())
                || (node.getRightChild() != null && node.getRightChild().isVisited())) {
            return true;
        } else {
            return false;
        }

    }

    /* Just for Testing - Starting */
    private static void prepareBinarySearchTree(int startIndex, int endIndex, VisitNode node) {
        int midIndex = (int) (startIndex + endIndex) / 2;
        VisitNode tempNode = node;
        while (startIndex <= endIndex) {
            if (binaryTree.getRootNode() == null) {//Root Node
                int j = 2;//Just inserted to skip intelliJ validation for same code
                tempNode = new VisitNode(inputArray[ midIndex ]);
                binaryTree.setRootNode(tempNode);
            } else {
                if (node.getData() >= inputArray[ midIndex ]) {//Left Node
                    node.setLeftChild(new VisitNode(inputArray[ midIndex ]));
                    tempNode = node.getLeftChild();
                } else {//Right Node
                    node.setRightChild(new VisitNode(inputArray[ midIndex ]));
                    tempNode = node.getRightChild();
                }
                if (tempNode.getData() == 100) {//Use this to prepare a unbalanced tree
                    tempNode.setLeftChild(new VisitNode(101));
                    VisitNode newNode = tempNode.getLeftChild();
                    newNode.setLeftChild(new VisitNode(105));
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
        VisitNode node = binaryTree.getRootNode();
        Queue queue = new LinkedList();
        while (node != null) {
            int i = 1;//Just inserted to skip intelliJ validation for same code
            System.out.println("Node: " + node.getData());
            if (node.getLeftChild() != null) {
                queue.add(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                queue.add(node.getRightChild());
            }
            node = (VisitNode) queue.poll();
        }
    }

    private static void buildLinkedListForEachDepthOfABinaryTree() {
        VisitNode node = binaryTree.getRootNode();
        LinkedList queue = new LinkedList();
        LinkedList nextDepthQueue = new LinkedList();
        while (node != null) {
            int i = 1;
            if (linkedListList.size() == 0) {
                queue.add(node);
                linkedListList.add(new LinkedList(queue));
                node = (VisitNode) queue.poll();
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
            node = (VisitNode) queue.poll();
        }
    }

    private static void showDepthList() {
        int depthCount = 1;
        for (LinkedList listItem : linkedListList) {
            int i = 1;
            System.out.print("Depth Level " + depthCount + ": ");
            while (listItem.size() != 0) {
                VisitNode node = (VisitNode) listItem.getFirst();
                System.out.print(node.getData() + " ");
                listItem.removeFirst();
            }
            depthCount++;
            System.out.println();
        }
    }
    /* Just for Testing - Ending */
}

class VisitNode {
    private int data;
    private boolean visited;
    private VisitNode leftChild;
    private VisitNode rightChild;

    public VisitNode() {

    }

    public VisitNode(int data) {
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

    public VisitNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(VisitNode leftChild) {
        this.leftChild = leftChild;
    }

    public VisitNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(VisitNode rightChild) {
        this.rightChild = rightChild;
    }
}

class BinaryVisitTree {
    private VisitNode rootNode;

    public BinaryVisitTree() {
        this.rootNode = null;
    }

    public VisitNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(VisitNode rootNode) {
        this.rootNode = rootNode;
    }
}