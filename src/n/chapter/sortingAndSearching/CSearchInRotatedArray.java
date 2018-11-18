package n.chapter.sortingAndSearching;

public class CSearchInRotatedArray {
    /* Search in Rotated Array
    Question: Given a sorted array of n integers that has been rotated an unknown number of times,
    write code to find an element in the array. You may assume that the array was originally sorted
    in increasing order. */

    /* Algorithm Followed:
     * We proceed in the linear way until the place is reached where the current element and it's next element's
     * difference is positive number. Then we use binary search algorithm for then onwards. */

    public static void main(String[] args) {
        int[] inputArray = new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        int elementToFind = 5;
        /*int[] inputArray = new int[]{15, 16, 19, 20, 25, 1, 3, 4, 6, 7, 10, 14};
        int elementToFind = 5;*/
        /*int[] inputArray = new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        int elementToFind = 14;*/
        System.out.println("Index Position is: " + searchRotatedArray(inputArray, elementToFind));
    }

    private static int searchRotatedArray(int[] inputArray, int elementToFind) {
        int startLocation = 0;
        int endLocation = 0;
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[ i ] == elementToFind) {
                return i;
            } else {
                if (i < inputArray.length - 1) {
                    if ((inputArray[ i ] - inputArray[ i + 1 ]) > 0) {
                        startLocation = i + 1;
                        endLocation = inputArray.length - 1;
                        return performBinarySearch(inputArray, elementToFind, startLocation, endLocation);
                    }
                } else {
                    return -1;
                }
            }
        }
        return -1;
    }

    private static int performBinarySearch(int[] inputArray, int elementToFind, int startLocation, int endLocation) {
        int result = -1;
        if (startLocation <= endLocation) {
            int mid = (startLocation + endLocation) / 2;
            if (inputArray[ mid ] == elementToFind) {
                return mid;
            } else if (elementToFind < inputArray[ mid ]) {
                result = performBinarySearch(inputArray, elementToFind, startLocation, mid - 1);
            } else if (elementToFind > inputArray[ mid ]) {
                result = performBinarySearch(inputArray, elementToFind, mid + 1, endLocation);
            }
        }
        return result;
    }
}