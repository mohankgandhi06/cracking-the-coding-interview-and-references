package q.chapter.mediumDifficulty;

import java.util.Arrays;

public class PSubSort {

    public static void main(String[] args) {
        PSubSort minimumSort = new PSubSort();
        int[] array = new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18};
        System.out.println("Array: ");
        Arrays.stream(array).forEach(x -> System.out.print(x + " "));
        System.out.println("\nSorting Requirement: ");
        minimumSort.sort(array);

        array = new int[]{1, 2, 4, 7, 10, 11, 7, 12, 8, 7, 2, 18, 19};
        System.out.println("\nArray: ");
        Arrays.stream(array).forEach(x -> System.out.print(x + " "));
        System.out.println("\nSorting Requirement: ");
        minimumSort.sort(array);

        array = new int[]{1, 2, 4, 7, 7, 8, 9, 10, 14, 18, 19};
        System.out.println("\nArray: ");
        Arrays.stream(array).forEach(x -> System.out.print(x + " "));
        System.out.println("\nSorting Requirement: ");
        minimumSort.sort(array);

        array = new int[]{1, 2, 4, 7, 7, 9, 8, 10, 14, 18, 19};
        System.out.println("\nArray: ");
        Arrays.stream(array).forEach(x -> System.out.print(x + " "));
        System.out.println("\nSorting Requirement: ");
        minimumSort.sort(array);
    }

    private void sort(int[] array) {
        int midpoint = array.length / 2;
        int leftStartIndex = 0;
        int leftEndIndex = midpoint - 1;
        int midStartIndex = midpoint;
        int midEndIndex = midpoint;
        int rightStartIndex = midpoint + 1;
        int rightEndIndex = array.length - 1;
        sort(array, leftStartIndex, leftEndIndex, midStartIndex, midEndIndex, rightStartIndex, rightEndIndex);
    }

    private void sort(int[] array, int leftStartIndex, int leftEndIndex, int midStartIndex, int midEndIndex, int rightStartIndex, int rightEndIndex) {
        int maxLeftIndex = maxIndex(array, leftStartIndex, leftEndIndex);
        int minMidIndex = minIndex(array, midStartIndex, midEndIndex);
        int minRightIndex = minIndex(array, rightStartIndex, rightEndIndex);
        int maxMidIndex = maxIndex(array, midStartIndex, midEndIndex);
        int middleIndexStart = midStartIndex;
        int middleIndexEnd = midEndIndex;
        while (!((array[ maxLeftIndex ] <= Math.min(array[ minMidIndex ], array[ minRightIndex ]))
                && (array[ minRightIndex ] >= Math.max(array[ maxMidIndex ], array[ maxLeftIndex ])))) {
            if (!(array[ maxLeftIndex ] <= Math.min(array[ minMidIndex ], array[ minRightIndex ]))) {
                middleIndexStart = maxLeftIndex;
            }
            if (!(array[ minRightIndex ] >= Math.max(array[ maxMidIndex ], array[ maxLeftIndex ]))) {
                middleIndexEnd = minRightIndex;
            }
            maxLeftIndex = maxIndex(array, leftStartIndex, maxLeftIndex - 1);
            minMidIndex = minIndex(array, middleIndexStart, middleIndexEnd);
            minRightIndex = minIndex(array, minRightIndex + 1, rightEndIndex);
            maxMidIndex = maxIndex(array, middleIndexStart, middleIndexEnd);
        }
        if (middleIndexStart != middleIndexEnd) {
            System.out.println("Index starting from " + middleIndexStart + " until " + middleIndexEnd + " has to be sorted");
        } else {
            System.out.println("No Sorting Needed");
        }
    }

    private int minIndex(int[] array, int startIndex, int endIndex) {
        int minIndex = startIndex >= array.length ? startIndex - 1 : startIndex;
        if (startIndex >= 0 && startIndex < array.length && endIndex >= 0 && endIndex < array.length) {
            int minValue = array[ startIndex ];
            for (int i = startIndex; i <= endIndex; i++) {
                if (array[ i ] <= minValue) {
                    minIndex = i;
                    minValue = array[ i ];
                }
            }
        }
        return minIndex;
    }

    private int maxIndex(int[] array, int startIndex, int endIndex) {
        int maxIndex = startIndex >= array.length ? startIndex - 1 : startIndex;
        if (startIndex >= 0 && startIndex < array.length && endIndex >= 0 && endIndex < array.length) {
            int maxValue = array[ startIndex ];
            for (int i = startIndex; i <= endIndex; i++) {
                if (array[ i ] >= maxValue) {
                    maxIndex = i;
                    maxValue = array[ i ];
                }
            }
        }
        return maxIndex;
    }
}