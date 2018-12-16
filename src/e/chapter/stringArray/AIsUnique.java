package e.chapter.stringArray;

import java.util.Arrays;

public class AIsUnique {
    /* Question: Implement an algorithm to determine if a string has all unique characters.
    What if you cannot use additional data structures? */
    public static void main(String[] args) {
        //usingArray("Mohan Krishna Gandhi S");
        //normalForLoopComparison("Mohan S");
        //usingHashTable("Mohan S");
        //usingBooleanArray("Mohan S.A");
        usingBitVectorConcept("mohan sa");
    }

    /* Optimal Solution */
    /* Important Assumption: Considering the characters to be anything from a
         ASCII supported characters */
    private static void usingBooleanArray(String input) {
        if (input.length() > 128) {
            System.out.println("Since the character's length is " +
                    "greater than the unique count of characters " +
                    "in ASCII i.e., 128, It is concluded that it " +
                    "having duplicate characters");
        }
        boolean[] bucketForEachCharacter = new boolean[ 128 ];
        for (int i = 0; i < input.length(); i++) {
            int value = input.charAt(i);
            if (bucketForEachCharacter[ value ]) {
                System.out.println("Duplicate Characters found");
                return;
            }
            bucketForEachCharacter[ value ] = true;
        }
        System.out.println("The Character's are unique");
    }

    private static void usingBitVectorConcept(String input) {
        /* Assuming that the character are from a to z only
        not special character or space. even if we type those characters
        it will not be able to find duplicate occurrence of those characters
        Important: Here the boolean array as used in the above methods are not
        needed. since a integer is 32 bit and the character count from a - z is 26
        it is more than sufficient. */
        int bitVector = 0;
        for (int i = 0; i < input.length(); i++) {
            int value = input.charAt(i) - 'a';
            if ((bitVector & (1 << value)) == 1) {
                System.out.println("Duplicate Found");
                return;
            }
            bitVector = bitVector | (1 << value);
        }
        System.out.println("The Character's are unique");
    }

    /* Earlier Implemented Solution */
    public static void usingArray(String input) {
        /* We are sorting the input string and then comparing the adjacent values so that
         * no duplicates are found - BigO (n log n)*/
        char[] charArray = input.toLowerCase().toCharArray();
        Arrays.sort(charArray);
        for (int i = 0; i < charArray.length - 1; i++) {
            if (charArray[ i ] == charArray[ i + 1 ]) {
                System.out.println("Duplicate found");
                return;
            }
        }
        System.out.println("Characters are unique");
    }

    public static void normalForLoopComparison(String inputString) {
        /* Comparing using traditional for loop method of
         * comparing a character with the rest of the character in the string and
         * proceeding - BigO (n^2)*/
        String input = inputString.toLowerCase();
        for (int i = 0; i < input.length() - 1; i++) {
            for (int j = i + 1; j < input.length(); j++) {
                if (input.charAt(i) == input.charAt(j)) {
                    System.out.println("Duplicate found");
                    return;
                }
            }
        }
        System.out.println("Characters are unique");
    }

    public static void usingHashTable(String inputString) {
        String input = inputString.toLowerCase();
        Table table = new Table("10");
        for (int i = 0; i < input.length(); i++) {
            int index = hashFunction(input.charAt(i));
            index = index % table.TABLE_SIZE;
            if (table.nodes[ index ] != null && table.nodes[ index ].getValue() != input.charAt(i)) {
                Node node = table.nodes[ index ];
                while (node.next != null) {
                    if (node.getValue() != input.charAt(i)) {
                        node = node.next;
                    } else {
                        System.out.println("Duplicate found");
                        return;
                    }
                }
                node.setNext(new Node(input.charAt(i)));
            } else if (table.nodes[ index ] != null && table.nodes[ index ].getValue() == input.charAt(i)) {
                System.out.println("Duplicate found");
                return;
            } else {
                table.nodes[ index ] = new Node(input.charAt(i));
            }
        }
        System.out.println("Characters are unique");
    }

    private static int hashFunction(char character) {
        return character % 26;
    }
}

class Table {
    public int TABLE_SIZE;
    public Node[] nodes;

    public Table(String size) {
        this.TABLE_SIZE = Integer.parseInt(size);
        this.nodes = new Node[ TABLE_SIZE ];
        for (int i = 0; i < TABLE_SIZE; i++) {
            nodes[ i ] = null;
        }
    }
}

class Node {
    public char value;
    public Node next;

    public Node() {

    }

    public Node(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}