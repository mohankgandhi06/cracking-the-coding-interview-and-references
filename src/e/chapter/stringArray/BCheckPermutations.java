package e.chapter.stringArray;

import java.util.Arrays;

public class BCheckPermutations {
    /* Question: Given two strings, write a method
    to decide if one is a permutation of the other. */

    public static void main(String[] args) {
        //usingString("bsc","cbs");
        //usingArray("s", "s");
        usingHashTable("omid", "m odi");
    }

    /* Optimal Solution */
    private static void usingBucket(String inputString, String checkThisWithInputString) {
        if (inputString.length() != checkThisWithInputString.length()) {
            System.out.println("Not a Permutation");
            return;
        }
        int[] characterBucket = new int[ 128 ];
        char[] array = inputString.toCharArray();
        for (char c : array) {
            characterBucket[ c ]++;
        }
        for (int i = 0; i < checkThisWithInputString.length(); i++) {
            int character = checkThisWithInputString.charAt(i);
            characterBucket[ character ]--;
            if (characterBucket[ character ] < 0) {
                System.out.println("Not a Permutation");
                return;
            }
        }
        System.out.println("Permutation");
    }

    /* Earlier Implementations */
    public static void usingHashTable(String inputString, String checkThisWithInputString) {
        String input = inputString.toLowerCase();
        HashTable table = new HashTable("26");
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            int index = hashFunction(input.charAt(i));
            if (table.nodes[ index ] != null) {
                HashNode node = table.nodes[ index ];
                while (node.getNext() != null) {
                    node = node.next;
                }
                node.setNext(new HashNode(input.charAt(i)));
            } else {
                table.nodes[ index ] = new HashNode(input.charAt(i));
            }
            count++;
        }
        System.out.println(count);
        for (int j = 0; j < checkThisWithInputString.length(); j++) {
            int index = hashFunction(checkThisWithInputString.charAt(j));
            if (table.nodes[ index ] != null && table.nodes[ index ].getValue() != checkThisWithInputString.charAt(j)) {
                HashNode node = table.nodes[ index ];
                while (node.getNext() != null && node.getNext().getValue() != checkThisWithInputString.charAt(j)) {
                    node = node.next;
                }
                if (node.getNext() != null && node.getNext().getValue() == checkThisWithInputString.charAt(j)) {
                    node.getNext().setValue('.');
                    count--;
                } else {
                    System.out.println("Not a Permutation");
                    return;
                }
            } else if (table.nodes[ index ] != null && table.nodes[ index ].getValue() == checkThisWithInputString.charAt(j)) {
                table.nodes[ index ].setValue('.');
                count--;
            } else {
                System.out.println("Not a Permutation");
                return;
            }
        }
        if (count == 0) {
            System.out.println("Permutation");
        } else {
            System.out.println("Not a Permutation");
        }
    }

    private static int hashFunction(char character) {
        return character % 26;
    }

    public static void usingArray(String inputString, String checkThisWithInputString) {
        /* Directly we are sorting the arrays and then comparing the characters linearly
         * Sorting BigO (n log n)
         * Comparing BigO (n)
         * Overall BigO (n log n) */
        if (inputString.length() == checkThisWithInputString.length()) {
            char[] inputOneArray = inputString.toLowerCase().toCharArray();
            char[] inputTwoArray = checkThisWithInputString.toLowerCase().toCharArray();
            Arrays.sort(inputOneArray);
            Arrays.sort(inputTwoArray);
            for (int i = 0; i < inputString.length(); i++) {
                if (inputOneArray[ i ] != inputTwoArray[ i ]) {
                    System.out.println("Array: Not a Permutation");
                    return;
                }
            }
            System.out.println("Array: Permutation");
        } else {
            System.out.println("Array: Not a Permutation");
        }
    }

    public static void usingString(String inputString, String checkThisWithInputString) {
        /* In this we are comparing the string with another string and if found we are removing the
         * character from both the strings and then continuing until one of the string becomes empty
         * and check if both are empty to determine there are a permutation. */
        int i = 0;
        int j = 0;
        while (!inputString.equalsIgnoreCase("") && !checkThisWithInputString.equalsIgnoreCase("")
                && j < checkThisWithInputString.length()) {
            if (inputString.charAt(i) == checkThisWithInputString.charAt(j)) {
                inputString = inputString.substring(1, inputString.length());
                checkThisWithInputString = checkThisWithInputString.substring(0, j) + checkThisWithInputString.substring(j + 1, checkThisWithInputString.length());
                j = 0;
                continue;
            }
            j++;
        }
        if (inputString.isEmpty() && checkThisWithInputString.isEmpty()) {
            System.out.println("Permutation");
        } else {
            System.out.println("Not a Permutation");
        }
    }
}

class HashTable {
    public static int TABLE_SIZE;
    public HashNode[] nodes;

    public HashTable(String size) {
        this.TABLE_SIZE = Integer.parseInt(size);
        this.nodes = new HashNode[ TABLE_SIZE ];
        for (int i = 0; i < TABLE_SIZE; i++) {
            nodes[ i ] = null;
        }
    }
}

class HashNode {
    public char value;
    public HashNode next;

    public HashNode() {

    }

    public HashNode(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public HashNode getNext() {
        return next;
    }

    public void setNext(HashNode next) {
        this.next = next;
    }
}