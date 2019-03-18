package q.chapter.mediumDifficulty;

import java.util.Arrays;

public class FSmallestDifference {
    public static void main(String[] args) {
        FSmallestDifference game = new FSmallestDifference();
        int[] arrayOne = new int[]{1, 3, 15, 11, 2, 130};
        int[] arrayTwo = new int[]{3, 23, 127, 235, 19, 8};
        game.solve(arrayOne, arrayTwo);
    }

    private int solve(int[] arrayOne, int[] arrayTwo) {
        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);
        for (int i : arrayOne) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : arrayTwo) {
            System.out.print(i + " ");
        }
        System.out.println();
        int minDifference = Integer.MAX_VALUE;
        int firstArrayIndex = arrayOne.length - 1;
        int secondArrayIndex = 0;
        while (firstArrayIndex >= 0 && secondArrayIndex <= arrayTwo.length - 1) {
            if (arrayOne[ firstArrayIndex ] >= arrayTwo[ secondArrayIndex ]) {
                minDifference = Math.min(minDifference, arrayOne[ firstArrayIndex ] - arrayTwo[ secondArrayIndex ]);
                secondArrayIndex++;
            } else {
                firstArrayIndex--;
                secondArrayIndex = 0;
                continue;
            }
        }
        System.out.println("Min Difference: " + minDifference);
        return minDifference;
    }
}