package q.chapter.mediumDifficulty;

import java.util.ArrayList;
import java.util.List;

public class XPairsWithSum {
    public static void main(String[] args) {
        XPairsWithSum game = new XPairsWithSum();
        int[] array = new int[]{1, 2, 1, 4, 5, 1, 3, 3, 2, 4, 5, 4, 2, 1, 0};
        int targetSum = 4;
        game.pairsWithSum(array, targetSum);

        targetSum = 5;
        game.pairsWithSum(array, targetSum);

        targetSum = 7;
        game.pairsWithSum(array, targetSum);

        targetSum = 8;
        game.pairsWithSum(array, targetSum);

        targetSum = 10;
        game.pairsWithSum(array, targetSum);
    }

    private void pairsWithSum(int[] array, int targetSum) {
        List<int[]> resultPairs = solve(array, 0, 0, 0, new ArrayList<>(), targetSum, new int[ 2 ]);
        System.out.println("Target Sum is " + targetSum);
        for (int[] pair : resultPairs) {
            System.out.println("Pair is: " + pair[ 0 ] + " and " + pair[ 1 ]);
        }
        System.out.println();
    }

    private List<int[]> solve(int[] array, int currentSum, int currentIndex, int currentCount, List<int[]> pairs, int targetSum, int[] pair) {
        if (currentIndex == array.length && currentSum != targetSum) return pairs;
        if (currentIndex == array.length && currentSum == targetSum) {
            if (currentCount == 2) {
                pairs.add(pair);
            }
            return pairs;
        }
        if (currentCount > 2) return pairs;
        if (currentCount == 2 && currentSum != targetSum) return pairs;
        if (currentCount == 2 && currentSum == targetSum) {
            pairs.add(pair);
            return pairs;
        }
        /* INCLUDE */
        int sum = currentSum;
        int[] tempPair = pair.clone();
        if (array[ currentIndex ] <= targetSum) {
            sum += array[ currentIndex ];
            tempPair[ currentCount ] = array[ currentIndex ];
            solve(array, sum, currentIndex + 1, currentCount + 1, pairs, targetSum, tempPair);
        }
        /* EXCLUDE */
        solve(array, currentSum, currentIndex + 1, currentCount, pairs, targetSum, pair);
        return pairs;
    }
}