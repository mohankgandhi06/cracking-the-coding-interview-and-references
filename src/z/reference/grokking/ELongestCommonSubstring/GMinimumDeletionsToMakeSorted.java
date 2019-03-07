package z.reference.grokking.ELongestCommonSubstring;

import java.util.Arrays;

public class GMinimumDeletionsToMakeSorted {
    public static void main(String[] args) {
        GMinimumDeletionsToMakeSorted game = new GMinimumDeletionsToMakeSorted();
        int[] input = {4, 2, 3, 6, 10, 1, 12};
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = new int[]{-4, 10, 3, 7, 15};
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = new int[]{3, 2, 1, 0};
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);
    }

    private void solveWithBruteForce(int[] input) {
        int maxInxreasingSubsequence = solve(input, 0, -1);
        int deletionsRequired = input.length - maxInxreasingSubsequence;
        System.out.println("(Brute Force) minimum deletion to make sorted: " + deletionsRequired);
    }

    private int solve(int[] input, int currentIndex, int previousIndex) {
        if (currentIndex == input.length) return 0;
        int include = 0;
        if (previousIndex == -1 || input[ currentIndex ] > input[ previousIndex ]) {
            include = 1 + solve(input, currentIndex + 1, currentIndex);
        }
        int exclude = solve(input, currentIndex + 1, previousIndex);
        return Math.max(include, exclude);
    }

    private void solveWithMemoization(int[] input) {
        Integer[][] memo = new Integer[ input.length ][ input.length + 1 ];
        int maxInxreasingSubsequence = solve(input, 0, -1, memo);
        int deletionsRequired = input.length - maxInxreasingSubsequence;
        System.out.println("(Memoization) minimum deletion to make sorted: " + deletionsRequired);
    }

    private int solve(int[] input, int currentIndex, int previousIndex, Integer[][] memo) {
        if (currentIndex == input.length) return 0;
        if (memo[ currentIndex ][ previousIndex + 1 ] == null) {
            int include = 0;
            if (previousIndex == -1 || input[ currentIndex ] > input[ previousIndex ]) {
                include = 1 + solve(input, currentIndex + 1, currentIndex, memo);
            }
            int exclude = solve(input, currentIndex + 1, previousIndex, memo);
            memo[ currentIndex ][ previousIndex + 1 ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ previousIndex + 1 ];
    }

    private void solveWithTabulation(int[] input) {
        int[] matrix = new int[ input.length ];
        Arrays.fill(matrix, 1);
        int maxInxreasingSubsequence = solve(input, matrix);
        int deletionsRequired = input.length - maxInxreasingSubsequence;
        System.out.println("(Tabulation) minimum deletion to make sorted: " + deletionsRequired);
    }

    private int solve(int[] input, int[] matrix) {
        int max = 0;
        for (int currentIndex = 1; currentIndex < matrix.length; currentIndex++) {
            for (int previousIndex = 0; previousIndex < currentIndex; previousIndex++) {
                if (input[ currentIndex ] > input[ previousIndex ] && matrix[ currentIndex ] <= matrix[ previousIndex ]) {
                    matrix[ currentIndex ] = matrix[ previousIndex ] + 1;
                }
                max = Math.max(matrix[ previousIndex ], matrix[ currentIndex ]);
            }
        }
        /*for (int i : matrix) {
            System.out.print(i + " ");
        }
        System.out.println();*/
        return max;
    }
}
