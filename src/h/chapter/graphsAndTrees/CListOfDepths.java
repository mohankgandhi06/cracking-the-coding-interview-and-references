package h.chapter.graphsAndTrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CListOfDepths {
    /* List of Depths
    Question: Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth
    (e.g., if you have a tree with Depth D, you'll have D Linked Lists)*/

    private static BinaryTree binaryTree = new BinaryTree();
    private static int[] inputArray;
    private static List<LinkedList> linkedListList = new ArrayList<LinkedList>();

    public static void main(String[] args) {
        inputArray = new int[]{5, 10, 15, 20, 21, 34, 36, 45, 48, 50, 61, 65, 72, 80, 83, 91, 96, 100};
        prepareBinarySearchTree(0, inputArray.length - 1, null);
        buildLinkedListForEachDepthOfABinaryTree();
        showDepthList();
    }

    private static void prepareBinarySearchTree(int startIndex, int endIndex, Node node) {
        int midIndex = (int) (startIndex + endIndex) / 2;
        Node tempNode = node;
        while (startIndex <= endIndex) {
            int i = 1;//Just inserted to skip intelliJ validation for same code
            if (binaryTree.getRootNode() == null) {//Root Node
                int j = 1;//Just inserted to skip intelliJ validation for same code
                tempNode = new Node(inputArray[ midIndex ]);
                binaryTree.setRootNode(tempNode);
            } else {
                if (node.getData() >= inputArray[ midIndex ]) {//Left Node
                    int k = 1;//Just inserted to skip intelliJ validation for same code
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

    private static void showDepthList() {
        int depthCount = 1;
        for (LinkedList listItem : linkedListList) {
            System.out.print("Depth Level " + depthCount + ": ");
            while (listItem.size() != 0) {
                Node node = (Node) listItem.getFirst();
                System.out.print(node.getData() + " ");
                listItem.removeFirst();
            }
            depthCount++;
            System.out.println();
        }
    }

    private static void buildLinkedListForEachDepthOfABinaryTree() {
        Node node = binaryTree.getRootNode();
        LinkedList queue = new LinkedList();
        LinkedList nextDepthQueue = new LinkedList();
        while (node != null) {
            if (linkedListList.size() == 0) {
                queue.add(node);
                linkedListList.add(new LinkedList(queue));
                node = (Node) queue.poll();
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
            node = (Node) queue.poll();
        }
    }
}
