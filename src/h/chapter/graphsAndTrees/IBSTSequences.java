package h.chapter.graphsAndTrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IBSTSequences {
    /* BST Sequences
    Question: A binary search tree was created by traversing through an array from left to right and inserting each element.
    Given a binary search tree with distinct elements, print all possible arrays that could have led to this tree.
    Example
    Input:               |2|
                         / \
                       |1| |3|

    Output: {2,1,3}, {2,3,1} */

    private static int[] inputArray;
    private static SequenceBSTTree binaryTree = new SequenceBSTTree();
    private static List<LinkedList> linkedListList = new ArrayList<LinkedList>();
    private static ArrayList<ArrayList<Integer>> permutations = new ArrayList<ArrayList<Integer>>();

    public static void main(String[] args) {
        //The below input is too large. try it at your own risk.... :)
        /*inputArray = new int[]{5, 10, 15, 20, 21, 34, 36, 45, 48, 50, 61, 65, 72, 80, 83, 91, 96, 100};*/

        inputArray = new int[]{5, 10, 15, 20, 21, 34, 36, 45};
        /*inputArray = new int[]{1, 2, 3, 4};*/
        /*inputArray = new int[]{1, 2, 3};*/
        prepareBinarySearchTree(0, inputArray.length - 1, null);
        showBinaryTree();
        buildLinkedListForEachDepthOfABinaryTree();
        showDepthList();
        prepareSequences();
        showPermutations();
    }

    private static void prepareSequences() {
        Queue queue = fetchTheList();
        int[] inputArray = convertToIntArray(queue);
        permute(inputArray, 0, permutations);
        return;
    }

    private static Queue fetchTheList() {
        Queue queue = new LinkedList();
        Queue tempQueue = new LinkedList();
        tempQueue.add(binaryTree.getRootNode());
        while (!tempQueue.isEmpty()) {
            SequenceBSTNode node = (SequenceBSTNode) tempQueue.poll();
            if (node.getLeftChild() != null) {
                tempQueue.add(node.getLeftChild());
                queue.add(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                tempQueue.add(node.getRightChild());
                queue.add(node.getRightChild());
            }
        }
        return queue;
    }

    private static void permute(int[] inputArray, int start, ArrayList<ArrayList<Integer>> permutations) {
        if (start >= inputArray.length) {
            ArrayList<Integer> permutationItem = convertAndAddToList(inputArray);
            permutations.add(permutationItem);
        }
        for (int j = start; j < inputArray.length; j++) {
            swap(inputArray, start, j);
            permute(inputArray, start + 1, permutations);
            swap(inputArray, start, j);
        }
    }

    private static void swap(int[] inputArray, int i, int j) {
        int temp = inputArray[ i ];
        inputArray[ i ] = inputArray[ j ];
        inputArray[ j ] = temp;
    }

    private static ArrayList<Integer> convertAndAddToList(int[] inputArray) {
        ArrayList<Integer> item = new ArrayList<Integer>();
        item.add(binaryTree.getRootNode().getData());
        for (int z = 0; z < inputArray.length; z++) {
            item.add(inputArray[ z ]);
        }
        return item;
    }

    private static void showPermutations() {
        for (ArrayList<Integer> item : permutations) {
            System.out.println(item);
        }
    }

    private static int[] convertToIntArray(Queue queue) {
        int[] inputArray = new int[ queue.size() ];
        int i = 0;
        while (!queue.isEmpty()) {
            SequenceBSTNode tempNode = (SequenceBSTNode) queue.poll();
            inputArray[ i ] = tempNode.getData();
            i++;
        }
        return inputArray;
    }

    /* Just for Testing - Starting */
    private static void prepareBinarySearchTree(int startIndex, int endIndex, SequenceBSTNode node) {
        int midIndex = (int) (startIndex + endIndex) / 2;
        SequenceBSTNode tempNode = node;
        while (startIndex <= endIndex) {
            if (binaryTree.getRootNode() == null) {//Root Node
                tempNode = new SequenceBSTNode(inputArray[ midIndex ], null);
                binaryTree.setRootNode(tempNode);
            } else {
                if (node.getData() >= inputArray[ midIndex ]) {//Left Node
                    node.setLeftChild(new SequenceBSTNode(inputArray[ midIndex ], node));
                    tempNode = node.getLeftChild();
                } else {//Right Node
                    node.setRightChild(new SequenceBSTNode(inputArray[ midIndex ], node));
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
        SequenceBSTNode node = binaryTree.getRootNode();
        Queue queue = new LinkedList();
        while (node != null) {
            System.out.println("Node: " + node.getData());
            if (node.getLeftChild() != null) {
                queue.add(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                queue.add(node.getRightChild());
            }
            node = (SequenceBSTNode) queue.poll();
        }
    }

    private static void buildLinkedListForEachDepthOfABinaryTree() {
        SequenceBSTNode node = binaryTree.getRootNode();
        LinkedList queue = new LinkedList();
        LinkedList nextDepthQueue = new LinkedList();
        while (node != null) {
            if (linkedListList.size() == 0) {
                queue.add(node);
                linkedListList.add(new LinkedList(queue));
                node = (SequenceBSTNode) queue.poll();
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
            node = (SequenceBSTNode) queue.poll();
        }
    }

    private static void showDepthList() {
        int depthCount = 1;
        for (LinkedList listItem : linkedListList) {
            System.out.print("Depth Level " + depthCount + ": ");
            while (listItem.size() != 0) {
                SequenceBSTNode node = (SequenceBSTNode) listItem.getFirst();
                System.out.print(node.getData() + " ");
                listItem.removeFirst();
            }
            depthCount++;
            System.out.println();
        }
    }
    /* Just for Testing - Ending */
}

class SequenceBSTNode {
    private int data;
    private SequenceBSTNode leftChild;
    private SequenceBSTNode rightChild;

    public SequenceBSTNode(int data, SequenceBSTNode parent) {
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

    public SequenceBSTNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(SequenceBSTNode leftChild) {
        this.leftChild = leftChild;
    }

    public SequenceBSTNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(SequenceBSTNode rightChild) {
        this.rightChild = rightChild;
    }
}

class SequenceBSTTree {
    private SequenceBSTNode rootNode;

    public SequenceBSTTree() {
        this.rootNode = null;
    }

    public SequenceBSTNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(SequenceBSTNode rootNode) {
        this.rootNode = rootNode;
    }
}