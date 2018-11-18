package n.chapter.sortingAndSearching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BGroupAnagrams {
    /* Group Anagrams
    Question: Write a method to sort an array of strings so that all the anagrams are next to each other. */

    public static HashMap<String, Integer> letterHash;
    public static HashMap<Integer, LinkedList> resultMap = new HashMap();

    public static void main(String[] args) {
        String[] input = new String[]{"cat", "dam", "hat", "rat", "tac", "mad", "lol"};
        System.out.println("Input Array");
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[ i ] + " ");
        }
        System.out.println();
        System.out.println();

        createLetterHash();
        anagramSort(input);
        String[] resultArray = prepareResultArray();
        System.out.println("Output Sorted (by Anagrams)");
        for (int i = 0; i < resultArray.length; i++) {
            System.out.print(resultArray[ i ] + " ");
        }
    }

    private static void anagramSort(String[] input) {
        int hashIndex = 1;
        for (String s : input) {
            hashIndex = 1;
            for (int i = 0; i < s.length(); i++) {
                /*Object obj = letterHash.get(String.valueOf(s.charAt(i)));
                System.out.println("Object:" + (Integer) obj);*/
                hashIndex = hashIndex * (Integer) letterHash.get(String.valueOf(s.charAt(i)));
            }
            /*System.out.println("String s: " + s + " value: " + hashIndex);*/
            if (resultMap.get(hashIndex) != null) {
                LinkedList li = resultMap.get(hashIndex);
                li.addLast(s);
                resultMap.put(hashIndex, li);
            } else {
                LinkedList li = new LinkedList();
                li.add(s);
                resultMap.put(hashIndex, li);
            }
        }
    }

    private static String[] prepareResultArray() {
        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<Integer, LinkedList> entry : resultMap.entrySet()) {
            /*System.out.println("Key:    " + entry.getKey());*/
            entry.getValue().forEach(s -> {
                /*System.out.println("        " + s.toString());*/
                result.add(s.toString());
            });
            /*System.out.println();*/
        }
        return convertToStringArray(result);
    }

    private static String[] convertToStringArray(ArrayList<String> result) {
        /* This conversion is done since the square brackets will be included
         * to the first element and the last element otherwise */
        String[] r = result.toString().split(",");
        r[ 0 ] = r[ 0 ].substring(1);
        r[ r.length - 1 ] = r[ r.length - 1 ].substring(0, r[ r.length - 1 ].length() - 1);
        return r;
    }

    // Letter Hash Map <Character, Unique Prime Number>
    // Each Alphabetical Character is mapped to a unique prime number
    private static void createLetterHash() {
        letterHash = new HashMap<String, Integer>();
        letterHash.put("a", 2);
        letterHash.put("b", 3);
        letterHash.put("c", 5);
        letterHash.put("d", 7);
        letterHash.put("e", 11);
        letterHash.put("f", 13);
        letterHash.put("g", 17);
        letterHash.put("h", 19);
        letterHash.put("i", 23);
        letterHash.put("j", 29);
        letterHash.put("k", 31);
        letterHash.put("l", 37);
        letterHash.put("m", 41);
        letterHash.put("n", 43);
        letterHash.put("o", 47);
        letterHash.put("p", 53);
        letterHash.put("q", 59);
        letterHash.put("r", 61);
        letterHash.put("s", 67);
        letterHash.put("t", 71);
        letterHash.put("u", 73);
        letterHash.put("v", 79);
        letterHash.put("w", 83);
        letterHash.put("x", 89);
        letterHash.put("y", 97);
        letterHash.put("z", 101);
    }
}