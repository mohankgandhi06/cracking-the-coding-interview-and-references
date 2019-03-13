package z.reference.grokking.ELongestCommonSubstring;

import java.util.Arrays;

public class KLongestAlternatingSubsequence {

    public static void main(String[] args) {
        KLongestAlternatingSubsequence game = new KLongestAlternatingSubsequence();
        int[] input = new int[]{1, 2, 3, 4};
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = new int[]{3, 2, 1, 4};
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = new int[]{1, 3, 2, 4};
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);
    }

    private void solveWithBruteForce(int[] input) {
        int increasing = solveIncreasing(input, 0, -1);
        int decreasing = solveDecreasing(input, 0, -1);
        int result = Math.max(increasing, decreasing);
        System.out.println("(Brute Force) longest alternating subsequence: " + result);
    }

    private int solveIncreasing(int[] input, int currentIndex, int previousIndex) {
        if (currentIndex == input.length) return 0;
        int include = 0;
        if (previousIndex == -1 || input[ currentIndex ] > input[ previousIndex ]) {
            include = 1 + solveDecreasing(input, currentIndex + 1, currentIndex);
        }
        int exclude = solveIncreasing(input, currentIndex + 1, previousIndex);
        return Math.max(include, exclude);
    }

    private int solveDecreasing(int[] input, int currentIndex, int previousIndex) {
        if (currentIndex == input.length) return 0;
        int include = 0;
        if (previousIndex == -1 || input[ currentIndex ] < input[ previousIndex ]) {
            include = 1 + solveIncreasing(input, currentIndex + 1, currentIndex);
        }
        int exclude = solveDecreasing(input, currentIndex + 1, previousIndex);
        return Math.max(include, exclude);
    }

    private void solveWithMemoization(int[] input) {
        Integer[][] memoIncreasing = new Integer[ input.length ][ input.length ];
        Integer[][] memoDecreasing = new Integer[ input.length ][ input.length ];
        int increasing = solveIncreasing(input, 0, -1, memoIncreasing);
        int decreasing = solveDecreasing(input, 0, -1, memoDecreasing);
        int result = Math.max(increasing, decreasing);
        System.out.println("(Memoization) longest alternating subsequence: " + result);
    }


    private int solveIncreasing(int[] input, int currentIndex, int previousIndex, Integer[][] memo) {
        if (currentIndex == input.length) return 0;
        if (memo[ currentIndex ][ previousIndex + 1 ] == null) {
            int include = 0;
            if (previousIndex == -1 || input[ currentIndex ] > input[ previousIndex ]) {
                include = 1 + solveDecreasing(input, currentIndex + 1, currentIndex);
            }
            int exclude = solveIncreasing(input, currentIndex + 1, previousIndex);
            memo[ currentIndex ][ previousIndex + 1 ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ previousIndex + 1 ];
    }

    private int solveDecreasing(int[] input, int currentIndex, int previousIndex, Integer[][] memo) {
        if (currentIndex == input.length) return 0;
        if (memo[ currentIndex ][ previousIndex + 1 ] == null) {
            int include = 0;
            if (previousIndex == -1 || input[ currentIndex ] < input[ previousIndex ]) {
                include = 1 + solveIncreasing(input, currentIndex + 1, currentIndex);
            }
            int exclude = solveDecreasing(input, currentIndex + 1, previousIndex);
            memo[ currentIndex ][ previousIndex + 1 ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ previousIndex + 1 ];
    }

    private void solveWithTabulation(int[] input) {
        int[][] matrix = new int[ input.length ][ 2 ];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[ 0 ].length; column++) {
                matrix[ row ][ column ] = 1;
            }
        }
        /* we are using the first row 0 for increasing and second row 1 for decreasing.
        If at an index it is increasing then we save in the increasing index 0 the sum
        of the current one and the decreasing index of the other index that it is being compared with */
        int maxAlternate = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < i; j++) {
                if (input[ 1 ] > input[ j ]) {//Increasing
                    matrix[ i ][ 0 ] = Math.max(matrix[ i ][ 0 ], 1 + matrix[ j ][ 1 ]);
                    maxAlternate = Math.max(maxAlternate, matrix[ i ][ 0 ]);
                } else {
                    matrix[ i ][ 1 ] = Math.max(matrix[ i ][ 1 ], 1 + matrix[ j ][ 0 ]);
                    maxAlternate = Math.max(maxAlternate, matrix[ i ][ 1 ]);
                }
            }
        }
        System.out.println("(Tabulation) longest alternating subsequence: " + maxAlternate);
    }
}