package r.chapter.hardDifficulty;

import java.util.Arrays;

public class SMissingTwo {
    public static void main(String[] args) {
        SMissingTwo game = new SMissingTwo();
        int[] array = new int[]{
                4, 7, 1, 6, 3, 5, 8, 9, 10
        };
        int howMany = 2;
        System.out.println("Array: find " + howMany + " missing number(s)");
        Arrays.stream(array).forEach(x -> System.out.print(x + " "));
        System.out.println();
        int[] result = game.findMissing(array, howMany);
        game.showResult(result, howMany);

        System.out.println("\n");
        array = new int[]{
                4, 7, 2, 6, 11, 13, 5, 1, 9, 10, 12, 3
        };
        howMany = 1;
        System.out.println("Array: find " + howMany + " missing number(s)");
        Arrays.stream(array).forEach(x -> System.out.print(x + " "));
        System.out.println();
        result = game.findMissing(array, howMany);
        game.showResult(result, howMany);
    }

    private void showResult(int[] result, int howMany) {
        if (result == null) {
            System.out.println("Illegal Arguments - howMany: " + howMany);
        } else {
            System.out.println("Missing Number(s): ");
            int i = 0;
            while (i < result.length && result[ i ] != -1) {
                System.out.print(result[ i ] + " ");
                i++;
            }
        }
    }

    private int[] findMissing(int[] array, int howMany) {
        if (howMany > 2 || howMany < 1) return null;
        int[] result = new int[ howMany ];
        Arrays.fill(result, -1);

        int totalPresentInArray = 0;
        int totalOfAllElementsTillN = 0;
        for (int element : array) {
            totalPresentInArray += element;
        }
        for (int j = 1; j <= array.length + howMany; j++) {
            totalOfAllElementsTillN += j;
        }

        if (howMany == 1) {
            result[ 0 ] = totalOfAllElementsTillN - totalPresentInArray;
        } else {
            int sumOfSquareOfElementsInArray = 0;
            int sumOfSquareOfAllElementsTillN = 0;
            for (int element : array) {
                sumOfSquareOfElementsInArray += Math.pow(element, 2);
            }
            for (int j = 1; j <= array.length + 2; j++) {
                sumOfSquareOfAllElementsTillN += Math.pow(j, 2);
            }
            int aSquarePlusBSquare = sumOfSquareOfAllElementsTillN - sumOfSquareOfElementsInArray;
            int aPlusB = totalOfAllElementsTillN - totalPresentInArray;
            /* (A+B) (A+B) = A^2 + B^2 + 2AB
             * AB = (((A+B) (A+B)) - (A^2 + B^2))/2 */
            int aTimesB = ((aPlusB * aPlusB) - aSquarePlusBSquare) / 2;
            /* A + B = 11
             * A*B = 18
             * B = 11 - A
             * (11 - A)*A = 18
             * 11A - A^2 = 18
             * A^2 - 11A + 18 = 0
             * A^2 - (aPlusB * A) + aTimesB
             * x = 1; y = -aPlusB; z = aTimesB
             * A = (-y + SQRT(y^2 - 4(x*z)))/(2*x)
             * B = (-y - SQRT(y^2 - 4(x*z)))/(2*x) */
            int x = 1;
            int y = -aPlusB;
            int z = aTimesB;
            int bSquareMinus4AC = (int) Math.sqrt(Math.pow(y, 2) - (4 * x * z));
            result[ 0 ] = (-y + bSquareMinus4AC) / (2 * x);
            result[ 1 ] = (-y - bSquareMinus4AC) / (2 * x);
        }
        return result;
    }
}