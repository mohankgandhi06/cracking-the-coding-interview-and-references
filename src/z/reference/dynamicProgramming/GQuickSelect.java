package z.reference.dynamicProgramming;

import java.util.Random;

public class GQuickSelect {
    public static void main(String[] args) {
        int kth = 2;
        int result = quickSelect(new int[]{4, 12, 10, 5, 6, 8, 3, 15, 7}, kth);
        System.out.println(kth + " element if it were a sorted array is: " + result);
    }

    private static int quickSelect(int[] array, int kth) {
        System.out.println("Input Array: ");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        int result = select(array, 0, array.length - 1, kth - 1);
        return result;
    }

    private static int select(int[] array, int startIndex, int endIndex, int kMinusOne) {
        int pivot = partition(array, startIndex, endIndex);
        if (pivot > kMinusOne) {
            return select(array, startIndex, pivot - 1, kMinusOne);
        } else if (pivot < kMinusOne) {
            return select(array, pivot + 1, endIndex, kMinusOne);
        }
        return array[ pivot ];
    }

    private static int partition(int[] array, int startIndex, int endIndex) {
        int pivotIndex = new Random().nextInt(endIndex - startIndex + 1) + startIndex;
        /*int pivotIndex = (startIndex + endIndex) / 2;*/
        /*System.out.println("Pivot Index: " + pivotIndex);*/
        swap(array, pivotIndex, endIndex);
        for (int i = startIndex; i < endIndex; i++) {
            if (array[ i ] < array[ endIndex ]) {
                swap(array, i, startIndex);
                startIndex++;
            }
        }
        swap(array, startIndex, endIndex);
        return startIndex;
    }

    private static void swap(int[] array, int indexOne, int indexTwo) {
        int temp = array[ indexOne ];
        array[ indexOne ] = array[ indexTwo ];
        array[ indexTwo ] = temp;
    }
}