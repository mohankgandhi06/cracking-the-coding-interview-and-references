package n.chapter.sortingAndSearching;

import java.util.BitSet;

public class GMissingInt {
    /* Missing Int
     * Question: Given an input file with four billion non-negative integers, provide an algorithm
     * to generate an integer that is not contained in the file. Assume you have 1 GB of memory
     * available for this task.
     * FOLLOW UP
     * What if you have only 10 MB of memory? Assume that all the values are distinct and we now have
     * no more than one billion non-negative integers. */

    public static void main(String[] args) {
        int[] intArray = new int[]{
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                41, 42, 43, 44, 45, 46, 47, 48, 49, 50
        };
        /*int[] intArray = new int[]{0, 13, 15, 3, 4, 6, 7, 1, 2, 8, 9, 11, 5, 14, 16, 12, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};*/

        System.out.println("Missing Integer is (using missingIntWith1GB): " + missingIntWith1GB(intArray));

        System.out.println("Missing Integer is (using missingIntWith1GBAndByte): " + missingIntWith1GBAndByte(intArray));

        System.out.println("Missing Integer is (using missingIntWith10MB): " + missingIntWith10MB(intArray));
    }

    private static long missingIntWith1GB(int[] intArray) {
        BitSet bitSet = new BitSet(32);
        int i;
        for (int num = 0; num < intArray.length; num++) {
            bitSet.set(intArray[ num ], true);
        }
        for (i = 0; i < bitSet.length(); i++) {
            if (!bitSet.get(i)) {
                return i;
            }
        }
        if (i > intArray.length - 1) {
            System.out.println("Since the array is continuous we are taking a number after the last element: ");
            return i;
        }
        return -1;
    }

    private static long missingIntWith1GBAndByte(int[] intArray) {
        /*System.out.println(Integer.MAX_VALUE);*/
        //Split the number of bytes based on the maximum long numbers available
        long numberOfInts = ((long) Integer.MAX_VALUE);
        byte[] bytes = new byte[ (int) numberOfInts / 8 ];
        for (int i : intArray) {
            /*System.out.println("BYTE BEFORE " + (i / 8) + " is: " + bytes[ i / 8 ]);*/
            bytes[ i / 8 ] = (byte) (bytes[ i / 8 ] | 1 << (i % Byte.SIZE));
            /*System.out.println("BYTE " + (i / 8) + " is: " + bytes[ i / 8 ]);*/
        }
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 0; j < Byte.SIZE; j++) {
                if ((bytes[ i ] & (1 << j)) == 0) {
                    return (i * 8) + j;
                }
            }
        }
        return 1;
    }

    private static int missingIntWith10MB(int[] intArray) {
        int range = decideRange();
        int[] count = new int[ (intArray.length / range) + 1 ];

        //Save the count of all the range of buckets to find out in which range
        // for simplicity we consider the bucket size as 10 each and we iterate to find out the integer
        firstPass(intArray, count, range);
        /*System.out.println("Count in each bucket");
        for (int i : count) {
            System.out.println(i);
        }*/

        //Detect the index (to get the range) first occurrence where the count is less than
        // expected. i.e., the bucket will be full if the range is complete
        // Now since the range is narrowed down we can take only that range and use the byte[] to save the space.
        int bucketLocation = detectTheFirstOccurrence(count, range);
        /*System.out.println("Bucket Location: " + bucketLocation);*/

        int startLocation = (bucketLocation * range);
        int endLocation = startLocation + range;
        byte[] bitlocation = new byte[ (range / Byte.SIZE) + 1 ];
        for (int a = 0; a < intArray.length; a++) {
            //Save only the ranges values in the bytes array
            if (intArray[ a ] >= startLocation && intArray[ a ] <= endLocation) {
                int temp = intArray[ a ] % startLocation;
                bitlocation[ temp / 8 ] = (byte) (bitlocation[ temp / 8 ] | 1 << (temp % Byte.SIZE));
            }
        }
        /*System.out.println(Byte.);*/
        /*for (byte b : bitlocation) {
            System.out.println("B: " + b);
        }*/

        for (int i = 0; i < bitlocation.length; i++) {
            for (int j = 0; j < Byte.SIZE; j++) {
                byte mask = (byte) (1 << j);
                if ((mask & bitlocation[ i ]) == 0) {
                    return startLocation + ((i * Byte.SIZE) + j);
                }
            }
        }
        return -1;
    }

    private static int decideRange() {
        return 10;//Currently we are fixing the range as 10
    }

    private static void firstPass(int[] array, int[] count, int range) {
        for (int i = 0; i < array.length; i++) {
            count[ array[ i ] / range ]++;
        }
    }

    private static int detectTheFirstOccurrence(int[] count, int range) {
        for (int l = 0; l < count.length; l++) {
            if (count[ l ] < range) {
                return l;
            }
        }
        return -1;
    }
}