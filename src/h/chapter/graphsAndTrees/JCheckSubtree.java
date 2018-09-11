package h.chapter.graphsAndTrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class JCheckSubtree {
    /* Check Subtree
    Question: T1 and T2 are two very large binary trees, with T1 much bigger than T2. Create an algorithm to determine if T2
    is a subtree of T1.
    A tree T2 is a subtree of T1 if there exists a node in T1 such that the subtree of n is identical to T2.
    That is, if you cut off the tree at node n, the two trees would be identical.*/

    private static ObjectBinaryTree mainBinaryTree = new ObjectBinaryTree();
    private static ObjectBinaryTree subBinaryTree = new ObjectBinaryTree();
    private static String[] maintreeInput;
    private static String[] subtreeInput;
    private static List<LinkedList> mainTreeLinkedListList = new ArrayList<LinkedList>();
    private static List<LinkedList> subTreeLinkedListList = new ArrayList<LinkedList>();

    private static List<ObjectNode> mainTreeForCheckingMatch = new ArrayList<ObjectNode>();
    private static List<ObjectNode> subTreeForCheckingMatch = new ArrayList<ObjectNode>();

    public static void main(String[] args) {
        maintreeInput = new String[]{"a", "B", "d", "r", "j", "m", "n", "p", "X", "z", "q", "3", "6", "1", "G", "O", "K", "U", "O"};
        /*subtreeInput = new String[]{"a", "B", "d", "r", "j", "m", "n", "p", "X", "z", "q", "1", "2", "3", "4", "O", "K", "U", "O"};*/
        subtreeInput = new String[]{"r", "p", "X", "O", "K", "U", "O"};
        /*subtreeInput = new String[]{"r", "p", "X", "O", "K", "M", "O"};*/
        prepareBinarySearchTree(maintreeInput, mainBinaryTree);
        showBinaryTree(mainBinaryTree);
        buildLinkedListForEachDepthOfABinaryTree(mainBinaryTree, mainTreeLinkedListList);
        showDepthList(mainTreeLinkedListList);

        prepareBinarySearchTree(subtreeInput, subBinaryTree);
        showBinaryTree(subBinaryTree);
        buildLinkedListForEachDepthOfABinaryTree(subBinaryTree, subTreeLinkedListList);
        showDepthList(subTreeLinkedListList);

        System.out.println("Is there a subtree match!!!: " + checkSubtree());
    }

    private static boolean checkSubtree() {
        ObjectNode mainTreeObjectNode = findThisNode(subBinaryTree.getRootNode());
        Queue mainTreeQueue = new LinkedList();
        //Preparing Main Tree's List
        if (mainTreeObjectNode != null) {
            mainTreeQueue.add(mainTreeObjectNode);
            mainTreeForCheckingMatch.add(mainTreeObjectNode);
            while (!mainTreeQueue.isEmpty()) {
                ObjectNode node = (ObjectNode) mainTreeQueue.poll();
                if (node.getLeftChild() != null) {
                    mainTreeQueue.add(node.getLeftChild());
                    mainTreeForCheckingMatch.add(node.getLeftChild());
                }
                if (node.getRightChild() != null) {
                    mainTreeQueue.add(node.getRightChild());
                    mainTreeForCheckingMatch.add(node.getRightChild());
                }
            }
        }

        //Preparing Sub Tree's List
        ObjectNode subTreeObjectNode = subBinaryTree.getRootNode();
        Queue subTreeQueue = new LinkedList();
        if (subTreeObjectNode != null) {
            subTreeQueue.add(subTreeObjectNode);
            subTreeForCheckingMatch.add(subTreeObjectNode);
            while (!subTreeQueue.isEmpty()) {
                int z = 1;//Just inserted to skip intelliJ validation for same code
                ObjectNode node = (ObjectNode) subTreeQueue.poll();
                if (node.getLeftChild() != null) {
                    subTreeQueue.add(node.getLeftChild());
                    subTreeForCheckingMatch.add(node.getLeftChild());
                }
                if (node.getRightChild() != null) {
                    subTreeQueue.add(node.getRightChild());
                    subTreeForCheckingMatch.add(node.getRightChild());
                }
            }
        }
        if (mainTreeForCheckingMatch.size() != subTreeForCheckingMatch.size()) {
            return false;
        }
        if (mainTreeForCheckingMatch.size() == subTreeForCheckingMatch.size()) {
            for (int i = 0; i < mainTreeForCheckingMatch.size(); i++) {
                if (mainTreeForCheckingMatch.get(i).getData().equals(subTreeForCheckingMatch.get(i).getData())) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private static ObjectNode findThisNode(ObjectNode subBinaryTreeRootNode) {
        Queue queue = new LinkedList();
        queue.add(mainBinaryTree.getRootNode());
        while (!queue.isEmpty()) {
            ObjectNode objectNode = (ObjectNode) queue.poll();
            String objectData = (String) objectNode.getData();
            if (objectData.equals((String) subBinaryTreeRootNode.getData())) {
                return objectNode;
            } else {
                if (objectNode.getLeftChild() != null) {
                    queue.add(objectNode.getLeftChild());
                }
                if (objectNode.getRightChild() != null) {
                    queue.add(objectNode.getRightChild());
                }
            }
        }
        return null;
    }

    private static void prepareBinarySearchTree(String[] inputArray, ObjectBinaryTree binaryTree) {
        int i = 0;
        ObjectNode parentNode = null;
        if (binaryTree.getRootNode() == null) {
            binaryTree.setRootNode(new ObjectNode(inputArray[ i ], parentNode));
        }
        Queue queue = new LinkedList();
        queue.add(binaryTree.getRootNode());
        while (i < inputArray.length) {
            int z = 1;//Just inserted to skip intelliJ validation for same code
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

    private static void showBinaryTree(ObjectBinaryTree binaryTree) {
        ObjectNode node = binaryTree.getRootNode();
        Queue queue = new LinkedList();
        while (node != null) {
            int z = 1;//Just inserted to skip intelliJ validation for same code
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

    private static void showDepthList(List<LinkedList> linkedListList) {
        int depthCount = 1;
        for (LinkedList listItem : linkedListList) {
            int z = 1;//Just inserted to skip intelliJ validation for same code
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

    private static void buildLinkedListForEachDepthOfABinaryTree(ObjectBinaryTree binaryTree, List<LinkedList> linkedListList) {
        ObjectNode node = binaryTree.getRootNode();
        LinkedList queue = new LinkedList();
        LinkedList nextDepthQueue = new LinkedList();
        while (node != null) {
            int z = 1;//Just inserted to skip intelliJ validation for same code
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
}