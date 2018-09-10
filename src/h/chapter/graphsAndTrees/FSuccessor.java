package h.chapter.graphsAndTrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FSuccessor {
    /* Successor
     Question: write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a binary search tree.
     You may assume that each node has a link to it's parent*/

    /* In order successor means that visitLeftNode(); visitNode(); visitRightNode();*/

    private static ObjectBinaryTree binaryTree = new ObjectBinaryTree();
    private static String[] inputArray;
    private static List<LinkedList> linkedListList = new ArrayList<LinkedList>();
    private static ObjectNode result;
    private static boolean exitFlag;

    public static void main(String[] args) {
        inputArray = new String[]{"a", "B", "d", "r", "j", "m", "n", "p", "X", "z", "q", "3", "6", "1", "G", "O", "K", "U", "O"};
        String input = "X";
        /*inputArray = new String[]{"a", "B", "d", "r", "X"};*/
        prepareBinarySearchTree();
        showBinaryTree();
        buildLinkedListForEachDepthOfABinaryTree();
        showDepthList();
        ObjectNode resultObjectNode = inOrderTraversal(binaryTree.getRootNode(), input);
        if (resultObjectNode.getData().equals(input)) {
            System.out.println("No Successor !!!");
        } else {
            System.out.println("Result (null if the value entered "+input+" is not present in the tree): " + (resultObjectNode != null ? resultObjectNode.getData() : null));
        }
    }

    private static ObjectNode inOrderTraversal(ObjectNode objectNode, String input) {
        if (objectNode != null) {
            if (!exitFlag) {
                inOrderTraversal(objectNode.getLeftChild(), input);
            }
            if (!exitFlag) {
                result = visit(objectNode, input);
            }
            if (!exitFlag) {
                inOrderTraversal(objectNode.getRightChild(), input);
            }
        }
        return result;
    }

    private static ObjectNode visit(ObjectNode objectNode, String input) {
        if (result != null && result.isFoundFlag() && objectNode != null) {
            result = objectNode;
            setExitFlag(true);
            return result;
        }
        String data = (String) objectNode.getData();
        if (data.equals(input)) {
            objectNode.setFoundFlag(true);
            return objectNode;
        } else {
            return null;
        }
    }

    private static void prepareBinarySearchTree() {
        int i = 0;
        ObjectNode parentNode = null;
        if (binaryTree.getRootNode() == null) {
            binaryTree.setRootNode(new ObjectNode(inputArray[ i ], parentNode));
        }
        Queue queue = new LinkedList();
        queue.add(binaryTree.getRootNode());
        while (i < inputArray.length) {
            ObjectNode node = (ObjectNode) queue.poll();
            i++;
            if (i < inputArray.length) {
                node.setLeftChild(new ObjectNode(inputArray[ i ], node));
                queue.add(node.getLeftChild());
            }
            i++;
            if (i < inputArray.length) {
                node.setRightChild(new ObjectNode(inputArray[ i ], node));
                queue.add(node.getRightChild());
            }
        }
    }

    private static void showBinaryTree() {
        ObjectNode node = binaryTree.getRootNode();
        Queue queue = new LinkedList();
        while (node != null) {
            System.out.println("Node: " + (String) node.getData());
            if (node.getLeftChild() != null) {
                queue.add(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                queue.add(node.getRightChild());
            }
            node = (ObjectNode) queue.poll();
        }
    }

    private static void showDepthList() {
        int depthCount = 1;
        for (LinkedList listItem : linkedListList) {
            int i = 1;//Just inserted to skip intelliJ validation for same code
            System.out.print("Depth Level " + depthCount + ": ");
            while (listItem.size() != 0) {
                ObjectNode node = (ObjectNode) listItem.getFirst();
                System.out.print((String) node.getData() + " ");
                listItem.removeFirst();
            }
            depthCount++;
            System.out.println();
        }
    }

    private static void buildLinkedListForEachDepthOfABinaryTree() {
        ObjectNode node = binaryTree.getRootNode();
        LinkedList queue = new LinkedList();
        LinkedList nextDepthQueue = new LinkedList();
        while (node != null) {
            int j = 1;//Just inserted to skip intelliJ validation for same code
            if (linkedListList.size() == 0) {
                queue.add(node);
                linkedListList.add(new LinkedList(queue));
                node = (ObjectNode) queue.poll();
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
            node = (ObjectNode) queue.poll();
        }
    }

    public static boolean isExitFlag() {
        return exitFlag;
    }

    public static void setExitFlag(boolean exitFlag) {
        FSuccessor.exitFlag = exitFlag;
    }
}

class ObjectBinaryTree {
    private ObjectNode rootNode;

    public ObjectBinaryTree() {
        this.rootNode = null;
    }

    public ObjectNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(ObjectNode rootNode) {
        this.rootNode = rootNode;
    }
}

class ObjectNode {
    private Object data;
    private ObjectNode parent;
    private ObjectNode leftChild;
    private ObjectNode rightChild;
    private boolean exitFlag;
    private boolean foundFlag;

    public ObjectNode(Object data, ObjectNode parent) {
        this.data = data;
        this.parent = parent;
        this.leftChild = null;
        this.rightChild = null;
    }

    public ObjectNode(ObjectNode objectNode) {
        this.data = objectNode.getData();
        this.parent = objectNode.getParent();
        this.leftChild = objectNode.getLeftChild();
        this.rightChild = objectNode.getRightChild();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ObjectNode getParent() {
        return parent;
    }

    public void setParent(ObjectNode parent) {
        this.parent = parent;
    }

    public ObjectNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(ObjectNode leftChild) {
        this.leftChild = leftChild;
    }

    public ObjectNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(ObjectNode rightChild) {
        this.rightChild = rightChild;
    }

    public boolean isExitFlag() {
        return exitFlag;
    }

    public void setExitFlag(boolean exitFlag) {
        this.exitFlag = exitFlag;
    }

    public boolean isFoundFlag() {
        return foundFlag;
    }

    public void setFoundFlag(boolean foundFlag) {
        this.foundFlag = foundFlag;
    }
}