package e.chapter.stringArray;

import java.util.Arrays;

public class AIsUnique {
    public static void main(String[] args) {
        //usingArray("Mohan Krishna Gandhi S");
        //normalForLoopComparison("Mohan S");
        usingHashTable("Mohan S");
    }

    public static void usingArray(String input) {
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