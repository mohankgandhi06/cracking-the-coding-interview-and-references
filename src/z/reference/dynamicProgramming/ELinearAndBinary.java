package z.reference.dynamicProgramming;

public class ELinearAndBinary {
    public static void main(String[] args) {
        /*int[] array = new int[]{4, 19, 1, 6, 7, 3, 8, 10, 15};*/
        /*System.out.println("Number 10 is found at: " + linearSearch(array, 10, 0));*/
        int[] array = new int[]{1, 3, 4, 6, 7, 8, 10, 15, 19};
        System.out.println("Number 8 is found at: " + binarySearch(array, 8, 0, array.length - 1));
    }

    public static int linearSearch(int[] array, int number, int index) {
        if (index == array.length) return -1;
        if (array[ index ] == number) return index;
        return linearSearch(array, number, index + 1);
    }

    public static int binarySearch(int[] array, int number, int startIndex, int endIndex) {
        if (startIndex > endIndex) return -1;
        int midIndex = (startIndex + endIndex) / 2;
        if (array[ midIndex ] == number) return midIndex;
        if (array[ midIndex ] > number)
            return binarySearch(array, number, startIndex, midIndex - 1);
        else
            return binarySearch(array, number, midIndex + 1, endIndex);
    }
}
