package n.chapter.sortingAndSearching;

public class HFindDuplicates {
    /* Find Duplicates
     * Question: You have an array with all the numbers from 1 to N, where N is at most 32000.
     * The array may have duplicates entries and you do not know what N is. WIth only 4 kilobytes of
     * memory available, how would you print all duplicate elements in the array? */

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 6, 8, 9, 3, 5, 7, 9, 10, 11, 15, 16, 13, 11, 1, 5, 14, 19, 28, 28, 22, 24, 29, 5, 30, 35, 22, 43, 45, 26, 73, 35};
        /*int[] array = new int[]{1, 2, 3, 6, 8, 9};*/
        System.out.println("Duplicates are output below. If method is complete it means there are no duplicates");
        System.out.println();
        findDuplicates(array);
        System.out.println();
        System.out.println("Completed Processing. Did we find any duplicates?");
    }

    private static void findDuplicates(int[] array) {
        byte[] bitwiseLocation = new byte[ 32000 / Byte.SIZE ];
        for (int i = 0; i < array.length; i++) {
            int mask = 1 << (array[ i ] % Byte.SIZE);
            /*System.out.println(mask);*/
            if ((bitwiseLocation[ array[ i ] / Byte.SIZE ] & mask) == 0) {
                bitwiseLocation[ array[ i ] / Byte.SIZE ] = (byte) (bitwiseLocation[ array[ i ] / Byte.SIZE ] | (1 << (array[ i ] % Byte.SIZE)));
            } else {
                System.out.println("Duplicate value: " + array[ i ]);
            }
        }
    }
}