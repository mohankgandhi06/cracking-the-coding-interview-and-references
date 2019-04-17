package r.chapter.hardDifficulty;

import java.util.Arrays;

public class DMissingNumber {
    public static void main(String[] args) {
        DMissingNumber game = new DMissingNumber();
        int[] array = new int[]{
                3, 4, 5, 8, 9, 7, 6, 2, 1, 10, 12, 11, 14, 13, 15
        };
        Arrays.stream(array).forEach(x -> System.out.print(x + " "));
        System.out.println();
        System.out.println("Missing number is: " + game.findMissingNumber(array));

        array = new int[]{
                3, 4, 5, 8, 9, 7, 16, 2, 1, 10, 12, 11, 14, 13, 15
        };
        System.out.println();
        Arrays.stream(array).forEach(x -> System.out.print(x + " "));
        System.out.println();
        System.out.println("Missing number is: " + game.findMissingNumber(array));

        array = new int[]{
                3, 4, 5, 8, 9, 7, 6, 16, 1, 10, 12, 11, 14, 13, 15
        };
        System.out.println();
        Arrays.stream(array).forEach(x -> System.out.print(x + " "));
        System.out.println();
        System.out.println("Missing number is: " + game.findMissingNumber(array));

        array = new int[]{
                3, 4, 16, 8, 9, 7, 6, 2, 1, 10, 12, 11, 14, 13, 15
        };
        System.out.println();
        Arrays.stream(array).forEach(x -> System.out.print(x + " "));
        System.out.println();
        System.out.println("Missing number is: " + game.findMissingNumber(array));

        array = new int[]{
                16, 4, 5, 8, 9, 7, 6, 2, 1, 10, 12, 11, 14, 13, 15
        };
        System.out.println();
        Arrays.stream(array).forEach(x -> System.out.print(x + " "));
        System.out.println();
        System.out.println("Missing number is: " + game.findMissingNumber(array));
    }

    private int findMissingNumber(int[] array) {
        int[] numbers = new int[ array.length + 1 ];
        for (int i = 1; i <= numbers.length; i++) {
            numbers[ i - 1 ] = i;
        }
        return solve(array, 0, numbers);
    }

    private int solve(int[] array, int jth, int[] numbers) {
        if (length(array) == 1) return find(numbers);
        int[] ones = new int[ array.length ];
        int[] zeros = new int[ array.length ];
        Arrays.fill(ones, -1);
        Arrays.fill(zeros, -1);
        int onesCount = 0;
        int zerosCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[ i ] != -1) {
                if (fetchSpecificBitFromNumber(array, jth, i) == 1) {
                    ones[ onesCount ] = array[ i ];
                    onesCount++;
                } else {
                    zeros[ zerosCount ] = array[ i ];
                    zerosCount++;
                }
            }
        }
        if (zerosCount > onesCount) {
            strikeOut(numbers, zeros);
            return solve(ones, jth + 1, numbers);
        } else {
            strikeOut(numbers, ones);
            return solve(zeros, jth + 1, numbers);
        }
    }

    private int length(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[ i ] == -1) {
                break;
            }
            count++;
        }
        return count + 1;
    }

    private int find(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[ i ] != -1) {
                return numbers[ i ];
            }
        }
        return -1;
    }

    private void strikeOut(int[] numbers, int[] strikeThese) {
        /* If we should not be using method where we are comparing the numbers directly. We could iterate through
         * the numbers list and individually check is those digits are matching the ones to be removed i.e. for example
         * if we want to remove all the digits that are equal to 1 for the jth digit then those that match the criteria
         * can be deleted after the check and it would not exceed the BigO(n) complexity */
        for (int i = 0; i < strikeThese.length; i++) {
            if (strikeThese[ i ] == -1) {
                break;
            }
            numbers[ strikeThese[ i ] - 1 ] = -1;
        }
    }

    private int fetchSpecificBitFromNumber(int[] array, int jthBit, int index) {
        return (array[ index ] & (1 << jthBit)) >>> jthBit;
    }
}