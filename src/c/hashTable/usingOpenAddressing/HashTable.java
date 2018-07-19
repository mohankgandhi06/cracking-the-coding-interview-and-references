package c.hashTable.usingOpenAddressing;

import java.util.Scanner;

public class HashTable {

    public static Table table;

    public static void main(String[] args) throws Exception {
        /*
         * First line contains number of test cases t.First line of each test case
         * contains number of elements n in array A. Second line contains n elements
         * of array of A.Third line contains k(number of queries). Following k lines
         * contain a number x.
         * */
        System.out.print("Please enter the no of Test cases: ");
        //Scanner
        Scanner s = new Scanner(System.in);
        String noOfTestCases = s.nextLine();

        for (int i = 0; i < Integer.parseInt(noOfTestCases); i++) {
            System.out.print("Please enter the no of inputs for CASE " + i+1 + ": ");
            String arraySize = s.nextLine();
            System.out.print("Please enter the inputs for CASE " + i+1 + " in csv format (e.x: 1,4,3,2,-1): ");
            String arrayInputsAsCSV = s.nextLine();
            prepareTable(arraySize, arrayInputsAsCSV);
            System.out.print("Please enter the no of queries to be answered: ");
            String noOfQueries = s.nextLine();
            for (int j = 0; j < Integer.parseInt(noOfQueries); j++) {
                System.out.print("Please enter the query no. " + j + " : ");
                String query = s.nextLine();
                System.out.println("Is the value " + query + " present in the array: " + search(query));
            }
        }
    }

    public static void insert(String key, String value) {
        int index = hashFunction(key);
        if (table.entries[ index ] == null) {
            table.entries[ index ] = new KeyValue(key, Integer.parseInt(value));
        } else {
            int inc = 1;
            while (table.entries[ index ] != null && !table.entries[ index ].getKey().equalsIgnoreCase(key)) {
                index = (hashFunction(key) + inc) % table.TABLE_SIZE;
                inc++;
            }
            if (table.entries[ index ] == null) {//Found the next location where there is no entry
                table.entries[ index ] = new KeyValue(key, Integer.parseInt(value));
            } else {//Already the Key exists so only the value is updated
                table.entries[ index ].setValue(Integer.parseInt(value));
            }
        }
    }

    public static boolean search(String key) {
        int index = hashFunction(key);
        if (table.entries[ index ] != null && table.entries[ index ].getKey().equalsIgnoreCase(key)) {
            return true;
        } else {
            int inc = 1;
            while (table.entries[ index ] != null && !table.entries[ index ].getKey().equalsIgnoreCase(key)&&inc<=table.TABLE_SIZE) {
                index = (hashFunction(key) + inc) % table.TABLE_SIZE;
                inc++;
            }
            if (table.entries[ index ].getKey().equalsIgnoreCase(key)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static int hashFunction(String key) {
        int index = key.hashCode();
        index = index % table.TABLE_SIZE;
        if (index < 0) {
            index = index + table.TABLE_SIZE;
        }
        return index;
    }

    public static void prepareTable(String arraySize, String arrayInputsAsCSV) {
        table = new Table(arraySize);
        String[] array = arrayInputsAsCSV.split(",");
        for (int i = 0; i < array.length; i++) {
            insert(array[ i ], array[ i ]);
        }
    }
}
