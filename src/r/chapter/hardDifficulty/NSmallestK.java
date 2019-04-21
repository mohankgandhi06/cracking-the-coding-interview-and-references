package r.chapter.hardDifficulty;

public class NSmallestK {
    public static void main(String[] args) {
        NSmallestK game = new NSmallestK();
        int[] array = new int[]{
                70, 53, 37, 26, 21, 32, 5, 10, 2, 76, 56, 48, 90
        };
        int k = 5;
        int endIndex = game.smallest(array, k);
        System.out.println("Total Array: ");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println("\nSmallest " + k + " Elements ");
        for (int i = 0; i < endIndex; i++) {
            System.out.print(array[ i ] + " ");
        }
        System.out.println();

        array = new int[]{
                53, 37, 26, 21, 32, 5, 90, 10, 28, 76, 56, 48
        };
        k = 5;
        endIndex = game.smallest(array, k);
        System.out.println("Total Array: ");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println("\nSmallest " + k + " Elements ");
        for (int i = 0; i < endIndex; i++) {
            System.out.print(array[ i ] + " ");
        }
        System.out.println();
    }

    private int smallest(int[] array, int k) {
        int pivotIndex = array.length - 1;
        int leftIndex = 0;
        int rightIndex = pivotIndex - 1;
        int result = pivotIndex;
        while (result - k != 0) {
            result = performQuickSort(array, pivotIndex, leftIndex, rightIndex);
            if (result - k >= 1) {/* Look to the left of the pivot */
                rightIndex = result - 2;
                pivotIndex = result - 1;
            } else if (pivotIndex - k < 0) {
                leftIndex = result + 1;
                pivotIndex = result + 1;
            }
        }
        return pivotIndex;
    }

    private int performQuickSort(int[] array, int pivotIndex, int leftIndex, int rightIndex) {
        while (leftIndex < pivotIndex) {
            if (array[ leftIndex ] < array[ pivotIndex ]) {
                leftIndex++;
                continue;
            }
            while (rightIndex > leftIndex) {
                if (array[ rightIndex ] >= array[ pivotIndex ]) {
                    rightIndex--;
                    continue;
                }
                /* Exchange the values and increment the leftIndex and continue */
                int temp = array[ rightIndex ];
                array[ rightIndex ] = array[ leftIndex ];
                array[ leftIndex ] = temp;
                leftIndex++;
                break;
            }
            /* Left Index is less than pivot index and rightIndex > leftIndex
             * i.e. the correct position of the pivot element is found */
            /* exchange the pivot with the rightIndex == leftIndex */
            if (rightIndex == leftIndex) {
                int temp = array[ pivotIndex ];
                array[ pivotIndex ] = array[ leftIndex ];
                array[ leftIndex ] = temp;
                pivotIndex = leftIndex;
                break;
            }
        }
        return pivotIndex;
    }
}