package n.chapter.sortingAndSearching;

public class ASortedMerge {
    /* Sorted Merge
    Question: You are given two sorted arrays, A and B, where A has large enough buffer at the end to hold B.
    Write a method to merge B into A in sorted order. */

    public static void main(String[] args) {
        //Input 1:
        /*int[] arrayA = new int[]{1, 4, 6, 8, 10, 12, 0, 0, 0, 0, 0, 0};
        int[] arrayB = new int[]{2, 4, 7, 9, 10, 11};*/
        //Input 2:
        /*int[] arrayA = new int[]{1, 2, 4, 6, 8, 12, 0, 0, 0, 0, 0, 0};
        int[] arrayB = new int[]{14, 16, 17, 19, 20, 21};*/
        //Input 3:
        int[] arrayA = new int[]{1, 2, 4, 6, 8, 12, 0, 0, 0, 0, 0, 0};
        int[] arrayB = new int[]{7, 11, 17, 19, 20, 21};
        sortedMerge(arrayA, arrayB);
        System.out.println("Sorted Array is: ");
        for (int a : arrayA) {
            System.out.print(a + " ");
        }
    }

    public static int[] sortedMerge(int[] arrayA, int[] arrayB) {
        int i = 0;
        int j = 0;
        int temp = 0;
        int position = 0;
        while (j < arrayB.length) {
            while (arrayA[ i ] < arrayB[ j ] && arrayA[ i ] != 0) {
                i++;
            }
            if (arrayA[ i ] != 0) {
                temp = arrayA[ i ];
                arrayA[ i ] = arrayB[ j ];
                position = j;
                j++;
                while (j < arrayB.length && arrayB[ j ] < temp) {
                    arrayB[ j - 1 ] = arrayB[ j ];
                    j++;
                }
                arrayB[ j - 1 ] = temp;
                j = position;
                i++;
            } else {
                while (j < arrayB.length) {
                    arrayA[ i ] = arrayB[ j ];
                    j++;
                    i++;
                }
            }
        }
        return arrayA;
    }
}