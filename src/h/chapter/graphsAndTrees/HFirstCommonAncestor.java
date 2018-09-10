package h.chapter.graphsAndTrees;

import java.util.*;

public class HFirstCommonAncestor {
    /* First Common Ancestor
     Question: Design an algorithm and write code to find the first common ancestor of two nodes in a binary
     tree. Avoid storing additional nodes in a data strucure. NOTE: This is not necessarily a Binary Search Tree */

    private static ObjectBinaryTree binaryTree = new ObjectBinaryTree();
    private static String[] inputArray;
    private static List<LinkedList> linkedListList = new ArrayList<LinkedList>();
    private static ObjectNode nodeOne;
    private static ObjectNode nodeTwo;
    private static Hashtable hashtable = new Hashtable();

    public static void main(String[] args) {
        //Input Here
        inputArray = new String[]{"a", "B", "d", "r", "j", "m", "n", "p", "X", "z", "q", "3", "6", "1", "G", "O", "K", "U", "W"};
        prepareBinarySearchTree();
        fetchNode(binaryTree.getRootNode(), "p", "3");

        //Logic Here
        showBinaryTree();
        buildLinkedListForEachDepthOfABinaryTree();
        showDepthList();
        if (nodeOne != null && nodeTwo != null) {
            System.out.println("Common Ancestor of the " + nodeOne.getData() + " and " + nodeTwo.getData() + " is: " + findCommonAncestor(nodeOne, nodeTwo));
        } else {
            System.out.println("Common Ancestor not possible as one node is not found...");
        }
    }

    private static String findCommonAncestor(ObjectNode nodeOne, ObjectNode nodeTwo) {
        ObjectNode firstNode = nodeOne;
        while (firstNode.getParent() != null) {
            String key = (String) firstNode.getParent().getData();
            hashtable.put(key, firstNode.getParent());
            firstNode = firstNode.getParent();
        }
        ObjectNode secondNode = nodeTwo;
        while (secondNode.getParent() != null) {
            String key = (String) secondNode.getParent().getData();
            ObjectNode hashtableObjectNode = (ObjectNode) hashtable.get(key);
            if (hashtableObjectNode != null) {
                String hashtableObjectData = (String) hashtableObjectNode.getData();
                if (key.equals(hashtableObjectData)) {
                    return hashtableObjectData;
                }
            }
            secondNode = secondNode.getParent();
        }
        return null;
    }

    private static void fetchNode(ObjectNode objectNode, String inputNodeOne, String inputNodeTwo) {
        if (objectNode == null) {
            return;
        }
        if (nodeOne == null || nodeTwo == null) {
            fetchNode(objectNode.getLeftChild(), inputNodeOne, inputNodeTwo);
            visitNode(objectNode, inputNodeOne, inputNodeTwo);
            fetchNode(objectNode.getRightChild(), inputNodeOne, inputNodeTwo);
            return;
        }
        return;
    }

    private static void visitNode(ObjectNode objectNode, String inputNodeOne, String inputNodeTwo) {
        String object = (String) objectNode.getData();
        if (object.equals(inputNodeOne)) {
            nodeOne = new ObjectNode(objectNode);
        } else if (object.equals(inputNodeTwo)) {
            nodeTwo = new ObjectNode(objectNode);
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
            int k = 2;//Just inserted to skip intelliJ validation for same code
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
            int k = 3;//Just inserted to skip intelliJ validation for same code
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
            int k = 4;//Just inserted to skip intelliJ validation for same code
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
            int k = 5;//Just inserted to skip intelliJ validation for same code
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
}