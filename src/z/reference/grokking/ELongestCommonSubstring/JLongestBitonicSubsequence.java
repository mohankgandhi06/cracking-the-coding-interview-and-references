package z.reference.grokking.ELongestCommonSubstring;

import java.util.Arrays;

public class JLongestBitonicSubsequence {

    public static void main(String[] args) {
        JLongestBitonicSubsequence game = new JLongestBitonicSubsequence();
        int[] input = {4, 2, 3, 6, 10, 1, 12};
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = new int[]{4, 2, 5, 9, 7, 6, 10, 3, 1};
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = new int[]{1, 2, 12, 15, 9, 11, 6, 10, 7, 1};
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

    }

    private void solveWithBruteForce(int[] input) {
        int longest = solve(input, 0, -1, false);
        /* Since we are initially checking for the increasing we are making the turnTaken as false */
        System.out.println("(Brute Force) longest bitonic subsequence: " + longest);
    }

    private int solve(int[] input, int currentIndex, int previousIndex, boolean turnTaken) {
        if (currentIndex == input.length) return 0;
        if (!turnTaken) {
            int increasing = 0;
            if (previousIndex == -1 || input[ currentIndex ] > input[ previousIndex ]) {//Increasing Check
                increasing = 1 + solve(input, currentIndex + 1, currentIndex, turnTaken);
            }
            /* If it is not increasing then we can take two kinds of decisions "skip the current element" and check the rest
             * or take a turn (change the flag value from false to true) with the "current element" and check if it returning
             * a max value */
            int skippingCurrent = solve(input, currentIndex + 1, previousIndex, turnTaken);
            int takingTurn = solve(input, currentIndex, previousIndex, true);
            return Math.max(Math.max(increasing, skippingCurrent), takingTurn);
        } else {
            int decreasing = 0;
            if (previousIndex == -1 || input[ currentIndex ] < input[ previousIndex ]) {//Decreasing Check
                decreasing = 1 + solve(input, currentIndex + 1, currentIndex, turnTaken);
            }
            /* Here we are not taking a turn like we did before in the Increasing Check's takingTurn scenario since we are
             * checking only for a bitonic and so only one turn is allowed */
            int skippingCurrent = solve(input, currentIndex + 1, previousIndex, turnTaken);
            return Math.max(decreasing, skippingCurrent);
        }
    }

    private void solveWithMemoization(int[] input) {
        Integer[][][] memo = new Integer[ input.length ][ input.length ][ 2 ];
        int longest = solve(input, 0, -1, false, memo);
        System.out.println("(Memoization) longest bitonic subsequence: " + longest);
    }

    private int solve(int[] input, int currentIndex, int previousIndex, boolean turnTaken, Integer[][][] memo) {
        if (currentIndex == input.length) return 0;
        int turnIndex = turnTaken ? 1 : 0; /* turnTaken if false we save in 0th index of the third dimension of the memo matrix */
        if (memo[ currentIndex ][ previousIndex + 1 ][ turnIndex ] == null) {
            int increase = 0;
            int decrease = 0;
            if (!turnTaken) {
                if (previousIndex == -1 || input[ currentIndex ] > input[ previousIndex ]) {//Increasing Check
                    increase = 1 + solve(input, currentIndex + 1, currentIndex, turnTaken, memo);
                }
                int skipping = solve(input, currentIndex + 1, previousIndex, turnTaken, memo);
                int takingTurn = solve(input, currentIndex, previousIndex, true, memo);
                increase = Math.max(Math.max(increase, takingTurn), skipping);
            } else {
                if (previousIndex == -1 || input[ currentIndex ] < input[ previousIndex ]) {
                    decrease = 1 + solve(input, currentIndex + 1, currentIndex, turnTaken, memo);
                }
                int skipping = solve(input, currentIndex + 1, previousIndex, turnTaken, memo);
                decrease = Math.max(decrease, skipping);
            }
            memo[ currentIndex ][ previousIndex + 1 ][ turnIndex ] = Math.max(increase, decrease);
        }
        return memo[ currentIndex ][ previousIndex + 1 ][ turnIndex ];
    }

    private void solveWithTabulation(int[] input) {
        int[][] matrix = new int[ input.length + 1 ][ input.length + 1 ];
        solve(input, matrix);
    }

    private int solve(int[] input, int[][] matrix) {
        /* This approach is not complete */
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[ 0 ].length; column++) {
                matrix[ row ][ column ] = 0;
            }
        }
        return -1;
    }
}