package c.hashTable.usingLinkedList;

import java.util.Scanner;

public class HashTable {
    private static Table table;
    private static Scanner scanner = new Scanner(System.in);

    public HashTable() {

    }

    public static void main(String[] args) {
        System.out.print("Please enter the size of the table: ");

        // Read a line of text from the user.
        String input = scanner.nextLine();

        // Display the input back to the user.
        //System.out.println( "input = " + input );

        //Set the table size from user input
        table = new Table(input);

        //Insertion Operation (Roll Number, Marks)
        insert("100", 50);
        insert("105", 70);
        insert("180", 60);
        insert("140", 80);
        insert("100", 90);
        insert("325", 90);
        insert("200", 100);
        insert("631", 100);
        insert("130", 100);
        insert("250", 100);

        insert("141", 80);
        insert("142", 90);
        insert("322", 90);
        insert("201", 100);
        insert("632", 100);
        insert("133", 100);
        insert("253", 100);

        insert("145", 80);
        insert("101", 90);
        insert("327", 90);
        insert("207", 100);
        insert("637", 100);
        insert("137", 100);
        insert("257", 100);

        //Search Operation (Roll Number)
        System.out.println("Found or Not(-1 means not found): " + search("145"));
        for (Node node : table.entries) {
            if (node != null) {
                System.out.println("Key: " + node.getKey() + " Value: " + node.getValue());
                while (node.getNext() != null) {
                    System.out.println("*Key: " + node.getNext().getKey() + " Value: " + node.getNext().getValue());
                    node = node.getNext();
                }
            } else {
                System.out.println("Node is empty");
            }
        }
    }

    public static void insert(String key, int value) {
        //Find the correct index position for the Key in the array
        int index = hashFunction(key) % table.TABLE_SIZE;
        if (table.entries[ index ] != null) {
            Node node = table.entries[ index ];
            while (node.getNext() != null && !node.getKey().equalsIgnoreCase(key)) {
                node = node.getNext();
            }
            if (node.getKey().equalsIgnoreCase(key)) {
                node.setValue(value);
            } else {
                node.setNext(new Node(key, value));
            }
        } else {
            table.entries[ index ] = new Node(key, value);
        }
    }

    public static int search(String key) {
        //Find the correct index position for the Key in the array
        int index = hashFunction(key) % table.TABLE_SIZE;
        if (table.entries[ index ] != null) {
            Node node = table.entries[ index ];
            while (node.getNext() != null && !node.getKey().equalsIgnoreCase(key)) {//Iterate the Linked List to find the Node
                node = node.getNext();
            }
            if (node.getKey().equalsIgnoreCase(key)) {
                return node.getValue();
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public static void remove(String key) {
        //Find the correct index position for the Key in the array
        int index = hashFunction(key) % table.TABLE_SIZE;
    }

    /*private static int hashFunction(String value) {
        int index = value.hashCode();
        index = index % table.HASH_DIVISOR;
        if (index < 0) {
            index = index + table.HASH_DIVISOR;
        }
        return index;
    }*/

    private static int hashFunction(String value) {
        int index = asciiSum(value);
        index = index % 255;
        if (index < 0) {
            index = index + 255;
        }
        return index;
    }

    private static int asciiSum(String value) {
        int asciiSum = 0;
        for (int i = 0; i < value.length(); ++i) {
            char c = value.charAt(i);
            int j = (int) c;
            asciiSum = asciiSum + j;
        }
        return asciiSum;
    }
}