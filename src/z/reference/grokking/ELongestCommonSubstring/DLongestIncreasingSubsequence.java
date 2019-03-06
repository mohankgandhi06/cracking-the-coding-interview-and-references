package z.reference.grokking.ELongestCommonSubstring;

import java.util.Arrays;

public class DLongestIncreasingSubsequence {

    public static void main(String[] args) {
        DLongestIncreasingSubsequence game = new DLongestIncreasingSubsequence();

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

        input = new int[]{1,3,8,4,14,6,14,1,9,4,13,3,11,17,29};
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);
    }

    private void solveWithBruteForce(int[] input) {
        int result = solve(input, 0, -1);
        System.out.println("(Brute Force) longest increasing subsequence: " + result);
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
        int result = solve(input, 0, -1, memo);
        System.out.println("(Memoization) longest increasing subsequence: " + result);
    }

    private int solve(int[] input, int currentIndex, int previousIndex, Integer[][] memo) {
        if (currentIndex == input.length) return 0;
        //previousIndex is +1 because we have -1 index location which will cause the out of bounds exception if used
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
        int result = solve(input, matrix);
        System.out.println("(Tabulation) longest increasing subsequence: " + result);
    }

    private int solve(int[] input, int[] matrix) {
        int longestIncreasingSubsequence = 0;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                /*System.out.println("i: " + i + " j: " + j +" Condition: "+(input[ i ] > input[ j ] && matrix[ i ] <= matrix[ j ]));*/
                /* In the below line we are taking the matrix[j] and adding one to it since the value denotes the current longest sequence possible until that end */
                matrix[ i ] = (input[ i ] > input[ j ] && matrix[ i ] <= matrix[ j ]) ? matrix[ j ] + 1 : matrix[ i ];
                longestIncreasingSubsequence = Math.max(longestIncreasingSubsequence, matrix[ i ]);
            }
        }
        return longestIncreasingSubsequence;
    }
}