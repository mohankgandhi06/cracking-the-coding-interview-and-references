package q.chapter.mediumDifficulty;

import java.util.Arrays;
import java.util.HashMap;

public class USumSwap {
    public static void main(String[] args) {
        USumSwap sumSwap = new USumSwap();
        int[] array1 = new int[]{4, 1, 2, 1, 1, 2};
        int[] array2 = new int[]{2, 8, 3, 2};
        int[] result = sumSwap.swapOnlyOne(array1, array2);
        System.out.println("Array1: ");
        Arrays.stream(array1).forEach(x -> System.out.print(x + " "));
        System.out.println();

        System.out.println("Array2: ");
        Arrays.stream(array2).forEach(x -> System.out.print(x + " "));
        System.out.println();

        if (result != null) {
            System.out.println("Exchange values '" + result[ 0 ] + "' from array1 with '" + result[ 1 ] + "' from array2 to make their sum equal");
        } else {
            System.out.println("No values to swap");
        }
    }

    private int[] swapOnlyOne(int[] arrayA, int[] arrayB) {
        int sumA = 0;
        int sumB = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arrayA.length; i++) {
            sumA += arrayA[ i ];
            hashMap.putIfAbsent(arrayA[ i ], arrayA[ i ]);
        }
        for (int i = 0; i < arrayB.length; i++) {
            sumB += arrayB[ i ];
        }
        /*System.out.println("Sum of Array1: " + sumA + " Sum of Array2: " + sumB);*/
        if ((sumA + sumB) % 2 != 0) {
            return null;
        }
        /*for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }*/
        int numberToFind = (Math.abs(sumA - sumB)) / 2;
        if (sumB < sumA) {
            for (int i = 0; i < arrayB.length; i++) {
                if (hashMap.containsKey(arrayB[ i ] + numberToFind)) {
                    int[] result = new int[ 2 ];
                    result[ 0 ] = hashMap.get(arrayB[ i ] + numberToFind);
                    result[ 1 ] = arrayB[ i ];
                    return result;
                }
            }
        } else {
            for (int i = 0; i < arrayB.length; i++) {
                if (hashMap.containsKey(arrayB[ i ] - numberToFind)) {
                    int[] result = new int[ 2 ];
                    result[ 0 ] = hashMap.get(arrayB[ i ] - numberToFind);
                    result[ 1 ] = arrayB[ i ];
                    return result;
                }
            }
        }
        return null;
    }
}