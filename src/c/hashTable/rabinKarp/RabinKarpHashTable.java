package c.hashTable.rabinKarp;

import java.util.Scanner;

public class RabinKarpHashTable {
    private static Table table;
    private static Scanner scanner = new Scanner(System.in);
    private static String inputString;
    private static String stringToBeFound;
    private static char previousCharacter;
    private static int previousHashcodeTotal;

    public RabinKarpHashTable() {

    }

    public static void main(String[] args) {
        System.out.print("Please enter the Input String: ");
        inputString = scanner.nextLine();
        System.out.println("Input String is : " + inputString);

        System.out.print("Please enter the String you wish to be found: ");
        stringToBeFound = scanner.nextLine();
        System.out.println("String To Be Found is : " + stringToBeFound);

        table = new Table("26");
        prepareTable();
        //printTable();
        System.out.println("Locations where we have found it (-1 means we have not found it) : " + findTheLocations());
    }

    public static void prepareTable() {
        for (int i = 0; i < inputString.length() - (stringToBeFound.length() - 1); i++) {
            int index = hashFunctionForInputString(i);
            index = index % 26;
            if (table.nodes[ index ] != null) {
                Node node = table.nodes[ index ];
                while (node.getNext() != null) {
                    node = node.getNext();
                }
                node.setNext(new Node(i, inputString.charAt(i)));
            } else {
                table.nodes[ index ] = new Node(i, inputString.charAt(i));
            }
        }
    }

    public static void printTable() {
        for (Node node : table.nodes) {
            if (node != null) {
                System.out.println("Key (): " + node.getKey() + " Value: " + node.getValue());
                while (node.getNext() != null) {
                    System.out.println("*Key: " + node.getNext().getKey() + " Value: " + node.getNext().getValue());
                    node = node.getNext();
                }
            } else {
                System.out.println("Node is empty");
            }
        }
    }

    public static String findTheLocations() {
        StringBuilder locations = new StringBuilder();
        int index = hashFunctionForStringToBeFound(stringToBeFound);
        index = index % 26;
        Node node = table.nodes[ index ];
        while (node != null) {
            if (node.getValue() == stringToBeFound.charAt(0) && inputString.substring(node.getKey(), (node.getKey()+stringToBeFound.length())).equalsIgnoreCase(stringToBeFound)) {
                locations = locations.append(node.getKey() + ": ");
            }
            node = node.next;
        }
        if (locations.length()==0){
            locations.append("-1");
        }
        return locations.toString();
    }

    public static int hashFunctionForInputString(int characterIndex) {
        int codeTotal = 0;
        if (characterIndex == 0) {
            String findHashForThisString = inputString.substring(characterIndex, characterIndex + stringToBeFound.length());
            for (int j = 0; j < findHashForThisString.length(); j++) {
                codeTotal = codeTotal + findHashForThisString.charAt(j);
            }
            previousCharacter = findHashForThisString.charAt(0);
            previousHashcodeTotal = codeTotal;
        } else {
            codeTotal = (previousHashcodeTotal - previousCharacter) + inputString.charAt(characterIndex + (stringToBeFound.length() - 1));
            previousCharacter = inputString.charAt(characterIndex);
            previousHashcodeTotal = codeTotal;
        }
        return codeTotal;
    }

    public static int hashFunctionForStringToBeFound(String str) {
        int codeTotal = 0;
        for (int j = 0; j < str.length(); j++) {
            codeTotal = codeTotal + str.charAt(j);
        }
        return codeTotal;
    }
}