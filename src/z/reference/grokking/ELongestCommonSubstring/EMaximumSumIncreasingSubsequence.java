package z.reference.grokking.ELongestCommonSubstring;

import java.util.Arrays;

public class EMaximumSumIncreasingSubsequence {
    public static void main(String[] args) {
        EMaximumSumIncreasingSubsequence game = new EMaximumSumIncreasingSubsequence();
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

        input = new int[]{1, 2, 3, 5, 10, 1, 4, 14};
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = new int[]{4, 12, 15, 6, 10, 1, 12};
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = new int[]{1, 3, 8, 4, 14, 6, 14, 1, 9, 4, 13, 3, 11, 17, 29};
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);
    }

    private void solveWithBruteForce(int[] input) {
        int result = solve(input, 0, -1);
        System.out.println("(Brute Force) maximum sum increasing subsequence: " + result);
    }

    private int solve(int[] input, int currentIndex, int previousIndex) {
        if (currentIndex == input.length) return 0;
        int include = 0;
        if (previousIndex == -1 || input[ currentIndex ] > input[ previousIndex ]) {
            include = input[ currentIndex ] + solve(input, currentIndex + 1, currentIndex);
        }
        int exclude = solve(input, currentIndex + 1, previousIndex);
        return Math.max(include, exclude);
    }

    private void solveWithMemoization(int[] input) {
        Integer[][] memo = new Integer[ input.length ][ input.length + 1 ];
        int result = solve(input, 0, -1, memo);
        System.out.println("(Memoization) maximum sum increasing subsequence: " + result);
    }

    private int solve(int[] input, int currentIndex, int previousIndex, Integer[][] memo) {
        if (currentIndex == input.length) return 0;
        if (memo[ currentIndex ][ previousIndex + 1 ] == null) {
            int include = 0;
            if (previousIndex == -1 || input[ currentIndex ] > input[ previousIndex ]) {
                include = input[ currentIndex ] + solve(input, currentIndex + 1, currentIndex, memo);
            }
            int exclude = solve(input, currentIndex + 1, previousIndex, memo);
            memo[ currentIndex ][ previousIndex + 1 ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ previousIndex + 1 ];
    }

    private void solveWithTabulation(int[] input) {
        int[] matrix = input.clone();
        int result = solve(input, matrix);
        System.out.println("(Tabulation) maximum sum increasing subsequence: " + result);
    }

    private int solve(int[] input, int[] matrix) {
        int maxSum = 0;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                if (input[ i ] > input[ j ] && matrix[ i ] < matrix[ j ] + input[ i ]) {
                    matrix[ i ] = matrix[ j ] + input[ i ];
                }
                maxSum = Math.max(matrix[ i ], maxSum);
            }
        }
        /*System.out.println("Matrix: ");
        Arrays.stream(matrix).forEach(x -> System.out.print(x + " "));
        System.out.println();*/
        return maxSum;
    }
}