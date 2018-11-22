package n.chapter.sortingAndSearching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FSortBigFile {
    /* Sort Big File
     * Question: Imagine you have 20 GB file with one string per line. Explain how you
     * would sort the file */

    public static AlphabeticalMap alphabeticalMap = new AlphabeticalMap();
    public static String[] output;

    public static void main(String[] args) {
        //Assume that we have converted the single file with one string each line into
        // a String array as below
        String[] input = new String[]{"crony", "tough", "company", "big", "carousal",
                "coat", "cool", "cat", "cream", "boat", "bag", "carrot", "roam",
                "cabbage", "soap", "pretty"};
        output = new String[ input.length ];
        output = sortBigFile(input);
        System.out.println();
        System.out.println("Sorted Output is: ");
        for (String s : output) {
            System.out.println(s);
        }
    }

    private static String[] sortBigFile(String[] input) {
        //STEP 1: Mapping the string to the alphabetical map
        for (String s : input) {
            if (alphabeticalMap.getMap().get(String.valueOf(s.charAt(0))) == null) {
                alphabeticalMap.getMap().put(String.valueOf(s.charAt(0)), new ArrayList());
                alphabeticalMap.getMap().get(String.valueOf(s.charAt(0))).add(s);
            } else {
                alphabeticalMap.getMap().get(String.valueOf(s.charAt(0))).add(s);
            }
        }
        System.out.println("Map is prepared: ");
        for (Map.Entry<String, ArrayList<String>> entry : alphabeticalMap.getMap().entrySet()) {
            int i = 0;//Added to skip the validation of intelliJ
            System.out.println("Key:    " + entry.getKey());
            if (entry.getValue() != null) {
                entry.getValue().forEach(s -> {
                    System.out.println("        " + s.toString());
                });
            }
        }

        //STEP 2: Perform Merge Sorting on each alphabetical character
        for (Map.Entry<String, ArrayList<String>> entry : alphabeticalMap.getMap().entrySet()) {
            if (entry.getValue() != null && entry.getValue().size() > 1) {
                System.out.println("Key:    " + entry.getKey());
                ArrayList<String> li = entry.getValue();
                String[] array = new String[ li.size() ];
                for (int i = 0; i < li.size(); i++) {
                    array[ i ] = li.get(i);
                }
                String[] sortedList = performMergeSort(array, 0, entry.getValue().size() - 1);
                ArrayList<String> sortedArrayList = new ArrayList<>();
                for (String s : sortedList) {
                    sortedArrayList.add(s);
                }
                entry.setValue(sortedArrayList);
            }
        }

        /*System.out.println();
        System.out.println("Sorted Map: ");*/
        String[] tempOutput = new String[ output.length ];
        int j = 0;
        for (Map.Entry<String, ArrayList<String>> entry : alphabeticalMap.getMap().entrySet()) {
            /*System.out.println("Key:    " + entry.getKey());*/
            if (entry.getValue() != null) {
                /*entry.getValue().forEach(s -> {
                    System.out.println("        " + s);
                });*/
                Object[] a = entry.getValue().toArray();
                for (int k = 0; k < a.length; k++) {
                    tempOutput[ j ] = (String) a[ k ];
                    j++;
                }
            }
        }
        return tempOutput;
    }

    private static String[] performMergeSort(String[] arrayList, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int index = (startIndex + endIndex) / 2;
            performMergeSort(arrayList, startIndex, index);//Left Split
            performMergeSort(arrayList, index + 1, endIndex);//Right Split
            merge(arrayList, startIndex, index, endIndex);
        }
        return arrayList;
    }

    private static void merge(String[] arrayList, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        String[] tempArrayList = new String[ arrayList.length ];
        for (int k = 0; k <= end; k++) {
            tempArrayList[ k ] = arrayList[ k ];
        }
        for (int k = start; k <= end; k++) {
            if (i > mid) { // If the left side has been completed checking it means the right side is sorted already properly
                arrayList[ k ] = tempArrayList[ j ];
                j = j + 1;
            } else if (j > end) { // If the right side has been completed checking ...
                arrayList[ k ] = tempArrayList[ i ];
                i = i + 1;
            } else if (tempArrayList[ j ].compareTo(tempArrayList[ i ]) < 0) { // If both the above If condition fails, then sorting is not yet complete. So we are going to check in here if the left is lesser. then replace it and increment the index
                arrayList[ k ] = tempArrayList[ j ];
                j = j + 1;
            } else {
                arrayList[ k ] = tempArrayList[ i ];
                i = i + 1;
            }
        }
        return;
    }
}

class AlphabeticalMap {
    private HashMap<String, ArrayList<String>> map;

    public AlphabeticalMap() {
        this.map = createHashMap(new HashMap<String, ArrayList>());
    }

    public HashMap<String, ArrayList<String>> getMap() {
        return map;
    }

    private HashMap createHashMap(HashMap<String, ArrayList> arrayListHashMap) {
        arrayListHashMap.put("a", null);
        arrayListHashMap.put("b", null);
        arrayListHashMap.put("c", null);
        arrayListHashMap.put("d", null);
        arrayListHashMap.put("e", null);
        arrayListHashMap.put("f", null);
        arrayListHashMap.put("g", null);
        arrayListHashMap.put("h", null);
        arrayListHashMap.put("i", null);
        arrayListHashMap.put("j", null);
        arrayListHashMap.put("k", null);
        arrayListHashMap.put("l", null);
        arrayListHashMap.put("m", null);
        arrayListHashMap.put("n", null);
        arrayListHashMap.put("o", null);
        arrayListHashMap.put("p", null);
        arrayListHashMap.put("q", null);
        arrayListHashMap.put("r", null);
        arrayListHashMap.put("s", null);
        arrayListHashMap.put("t", null);
        arrayListHashMap.put("u", null);
        arrayListHashMap.put("v", null);
        arrayListHashMap.put("w", null);
        arrayListHashMap.put("x", null);
        arrayListHashMap.put("y", null);
        arrayListHashMap.put("z", null);
        return arrayListHashMap;
    }
}