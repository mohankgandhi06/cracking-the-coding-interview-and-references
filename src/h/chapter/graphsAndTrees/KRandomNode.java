package h.chapter.graphsAndTrees;

import java.util.*;

public class KRandomNode {
    /* Random Node
    Question: You are implementing a binary tree class from scratch which,in addition to insert, find, and delete, has a method
    getRandomNode() which returns a random node from the tree. All nodes should be equally likely to be chosen. Design and implement an
    algorithm for getRandomNode, and explain how you would implement the rest of the methods. */
    private static List<String> list = new ArrayList<String>();
    private static RandomBinaryTree binaryTree = new RandomBinaryTree();

    public static void main(String[] args) {
        System.out.println("Insert: null returned if no insertion took place...");
        System.out.println("Inserted Data: " + insert("A").getData());
        System.out.println("Inserted Data: " + insert("B").getData());
        System.out.println("Inserted Data: " + insert("C").getData());
        System.out.println("Inserted Data: " + insert("D").getData());
        System.out.println("Inserted Data: " + insert("E").getData());
        System.out.println("Inserted Data: " + insert("F").getData());
        System.out.println("Inserted Data: " + insert("G").getData());
        System.out.println("Inserted Data: " + insert("H").getData());
        System.out.println("Inserted Data: " + insert("I").getData());
        System.out.println("Inserted Data: " + insert("J").getData());
        System.out.println("Inserted Data: " + insert("K").getData());
        System.out.println("Inserted Data: " + insert("L").getData());
        showBinaryTree(binaryTree);
        System.out.println();
        System.out.println("*******************Find*************************");
        System.out.println("Find: null returned if not found...");
        RandomBinaryNode node = find("F");
        if (node != null) {
            System.out.println("Found " + node.getData());
        } else {
            System.out.println("null");
        }

        delete("B");
        System.out.println();
        System.out.println("*******************After Delete*************************");
        showBinaryTree(binaryTree);
        System.out.println();
        System.out.println("*******************Random*************************");
        //The Below six entries will generate a number which might look like it is not random
        // Please try with large set (i.e. copy paste these codes again to generate more random numbers)
        System.out.println("Random Node: " + getRandomNode().getData());
        System.out.println("Random Node: " + getRandomNode().getData());
        System.out.println("Random Node: " + getRandomNode().getData());
        System.out.println("Random Node: " + getRandomNode().getData());
        System.out.println("Random Node: " + getRandomNode().getData());
        System.out.println("Random Node: " + getRandomNode().getData());
    }

    private static RandomBinaryNode insert(String data) {
        Queue queue = new LinkedList();
        if (binaryTree.getRootNode() == null) {//Insert Root Node
            binaryTree.setRootNode(new RandomBinaryNode(data, "Root Node", null));
            list.add(data);
            return binaryTree.getRootNode();
        } else {
            queue.add(binaryTree.getRootNode());
            while (!queue.isEmpty()) {
                RandomBinaryNode node = (RandomBinaryNode) queue.poll();
                if (node.getLeftChild() == null) {//Insert Left Child Node
                    node.setLeftChild(new RandomBinaryNode(data, "Left Node", node));
                    list.add(data);
                    return node.getLeftChild();
                } else if (node.getRightChild() == null) {//Insert Right Child Node
                    node.setRightChild(new RandomBinaryNode(data, "Right Node", node));
                    list.add(data);
                    return node.getRightChild();
                } else {
                    if (node.getLeftChild() != null) {
                        queue.add(node.getLeftChild());
                    }
                    if (node.getRightChild() != null) {
                        queue.add(node.getRightChild());
                    }
                }
            }
        }
        return null;
    }

    private static RandomBinaryNode find(String data) {
        RandomBinaryNode randomRootNode = binaryTree.getRootNode();
        Queue queue = new LinkedList();
        if (randomRootNode != null) {
            queue.add(randomRootNode);
            while (!queue.isEmpty()) {
                RandomBinaryNode node = (RandomBinaryNode) queue.poll();
                if (node.getData().equals(data)) {
                    return node;
                } else {
                    if (node.getLeftChild() != null) {
                        queue.add(node.getLeftChild());
                    }
                    if (node.getRightChild() != null) {
                        queue.add(node.getRightChild());
                    }
                }
            }
        }
        return null;
    }

    private static boolean delete(String data) {
        RandomBinaryNode nodeToBeDeleted = find(data);
        Queue queue = new LinkedList();
        Queue retainedQueue = new LinkedList();
        //Preparing Tree's List before deleting
        if (nodeToBeDeleted != null) {
            queue.add(nodeToBeDeleted);
            /*retainedQueue.add(nodeToBeDeleted);*/
            while (!queue.isEmpty()) {
                RandomBinaryNode node = (RandomBinaryNode) queue.poll();
                if (node.getLeftChild() != null) {
                    queue.add(node.getLeftChild());
                    retainedQueue.add(node.getLeftChild());
                }
                if (node.getRightChild() != null) {
                    queue.add(node.getRightChild());
                    retainedQueue.add(node.getRightChild());
                }
            }
        }
        RandomBinaryTree subTree = prepareSubtree(retainedQueue);
        System.out.println("*******************Before Delete*************************");
        System.out.println("Sub Tree: ");
        showBinaryTree(subTree);
        list.remove(data);

        if (nodeToBeDeleted.getNodePosition().equals("Left Node")) {
            nodeToBeDeleted.getParent().setLeftChild(subTree.getRootNode());
            return true;
        } else if (nodeToBeDeleted.getNodePosition().equals("Right Node")) {
            nodeToBeDeleted.getParent().setRightChild(subTree.getRootNode());
            return true;
        } else if (nodeToBeDeleted.getNodePosition().equals("Root Node")) {
            binaryTree.setRootNode(subTree.getRootNode());
            return true;
        }
        return false;
    }

    private static RandomBinaryTree prepareSubtree(Queue queue) {
        RandomBinaryTree subTree = new RandomBinaryTree();
        RandomBinaryNode randomBinaryNode = (RandomBinaryNode) queue.poll();
        if (subTree.getRootNode() == null) {
            subTree.setRootNode(new RandomBinaryNode(randomBinaryNode.getData(), "Root Node", null));
        }
        Queue tempQueue = new LinkedList();
        tempQueue.add(subTree.getRootNode());
        while (!tempQueue.isEmpty()) {
            RandomBinaryNode node = (RandomBinaryNode) tempQueue.poll();
            randomBinaryNode = (RandomBinaryNode) queue.poll();
            if (randomBinaryNode != null) {
                node.setLeftChild(new RandomBinaryNode(randomBinaryNode.getData(), "Left Node", node));
                tempQueue.add(node.getLeftChild());
            }
            randomBinaryNode = (RandomBinaryNode) queue.poll();
            if (randomBinaryNode != null) {
                node.setRightChild(new RandomBinaryNode(randomBinaryNode.getData(), "Right Node", node));
                tempQueue.add(node.getRightChild());
            }
        }
        return subTree;
    }

    private static RandomBinaryNode getRandomNode() {
        int random = getRandomNumberInts(list.size());
        String data = list.get(random);
        return find(data);
    }

    public static int getRandomNumberInts(int max) {
        Random random = new Random();
        /*int i = random.ints(min, (max + 1)).findFirst().getAsInt();*/
        /*((max) * i) % ((max) - (i))*/
        return random.nextInt(max);
    }

    private static void showBinaryTree(RandomBinaryTree binaryTree) {
        RandomBinaryNode node = binaryTree.getRootNode();
        Queue queue = new LinkedList();
        System.out.println();
        while (node != null) {
            System.out.println((String) node.getNodePosition() + ": " + (String) node.getData());
            if (node.getLeftChild() != null) {
                queue.add(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                queue.add(node.getRightChild());
            }
            node = (RandomBinaryNode) queue.poll();
        }
    }
}

class RandomBinaryTree {
    private RandomBinaryNode rootNode;

    public RandomBinaryTree() {
        this.rootNode = null;
    }

    public RandomBinaryNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(RandomBinaryNode rootNode) {
        this.rootNode = rootNode;
    }
}

class RandomBinaryNode {
    private String data;
    private RandomBinaryNode parent;
    private RandomBinaryNode leftChild;
    private RandomBinaryNode rightChild;
    private String nodePosition;

    public RandomBinaryNode(String data, String nodePosition, RandomBinaryNode parent) {
        this.data = data;
        this.parent = parent;
        this.leftChild = null;
        this.rightChild = null;
        this.nodePosition = nodePosition;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public RandomBinaryNode getParent() {
        return parent;
    }

    public void setParent(RandomBinaryNode parent) {
        this.parent = parent;
    }

    public RandomBinaryNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(RandomBinaryNode leftChild) {
        this.leftChild = leftChild;
    }

    public RandomBinaryNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(RandomBinaryNode rightChild) {
        this.rightChild = rightChild;
    }

    public String getNodePosition() {
        return nodePosition;
    }

    public void setNodePosition(String nodePosition) {
        this.nodePosition = nodePosition;
    }
}