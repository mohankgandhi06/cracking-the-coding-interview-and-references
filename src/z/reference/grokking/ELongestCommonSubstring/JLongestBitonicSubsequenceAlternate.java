package z.reference.grokking.ELongestCommonSubstring;

import java.util.Arrays;

public class JLongestBitonicSubsequenceAlternate {
    public static void main(String[] args) {
        JLongestBitonicSubsequenceAlternate game = new JLongestBitonicSubsequenceAlternate();
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
        int longestBitonicSubsequence = 0;
        for (int i = 0; i < input.length; i++) {
            int c1 = longestDecreasingSubsequence(input, i, -1);
            int c2 = longestDecreasingSubsequenceReverse(input, i, -1);
            longestBitonicSubsequence = Math.max(longestBitonicSubsequence, c1 + c2 - 1);
        }
        /* Since we are initially checking for the increasing we are making the turnTaken as false */
        System.out.println("(Brute Force) longest bitonic subsequence: " + longestBitonicSubsequence);
    }

    private int longestDecreasingSubsequence(int[] input, int currentIndex, int previousIndex) {
        if (currentIndex == input.length) return 0;
        int include = 0;
        if (previousIndex == -1 || input[ currentIndex ] < input[ previousIndex ]) {
            include = 1 + longestDecreasingSubsequence(input, currentIndex + 1, currentIndex);
        }
        int exclude = longestDecreasingSubsequence(input, currentIndex + 1, previousIndex);
        return Math.max(include, exclude);
    }

    private int longestDecreasingSubsequenceReverse(int[] input, int currentIndex, int previousIndex) {
        if (currentIndex < 0) return 0;
        int include = 0;
        if (previousIndex == -1 || input[ currentIndex ] < input[ previousIndex ]) {
            include = 1 + longestDecreasingSubsequenceReverse(input, currentIndex - 1, currentIndex);
        }
        int exclude = longestDecreasingSubsequenceReverse(input, currentIndex - 1, previousIndex);
        return Math.max(include, exclude);
    }

    private void solveWithMemoization(int[] input) {
        /* We are using two separate memo table for the left and right so that they will be initialized to null
         * separately and don't overlap each other */
        Integer[][] memoLeft = new Integer[ input.length ][ input.length + 1 ];
        Integer[][] memoRight = new Integer[ input.length ][ input.length + 1 ];
        int longest = 0;
        for (int i = 0; i < input.length; i++) {
            int c1 = longestDecreasingSubsequence(input, i, -1, memoLeft);
            int c2 = longestDecreasingSubsequenceReverse(input, i, -1, memoRight);
            longest = Math.max(longest, c1 + c2 - 1);
        }
        System.out.println("(Memoization) longest bitonic subsequence: " + longest);
    }

    private int longestDecreasingSubsequence(int[] input, int currentIndex, int previousIndex, Integer[][] memo) {
        if (currentIndex == input.length) return 0;
        if (memo[ currentIndex ][ previousIndex + 1 ] == null) {
            int include = 0;
            if (previousIndex == -1 || input[ currentIndex ] < input[ previousIndex ]) {
                include = 1 + longestDecreasingSubsequence(input, currentIndex + 1, currentIndex, memo);
            }
            int exclude = longestDecreasingSubsequence(input, currentIndex + 1, previousIndex, memo);
            memo[ currentIndex ][ previousIndex + 1 ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ previousIndex + 1 ];
    }

    private int longestDecreasingSubsequenceReverse(int[] input, int currentIndex, int previousIndex, Integer[][] memo) {
        if (currentIndex < 0) return 0;
        if (memo[ currentIndex ][ previousIndex + 1 ] == null) {
            int include = 0;
            if (previousIndex == -1 || input[ currentIndex ] < input[ previousIndex ]) {
                include = 1 + longestDecreasingSubsequenceReverse(input, currentIndex - 1, currentIndex, memo);
            }
            int exclude = longestDecreasingSubsequenceReverse(input, currentIndex - 1, previousIndex, memo);
            memo[ currentIndex ][ previousIndex + 1 ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ previousIndex + 1 ];
    }

    private void solveWithTabulation(int[] input) {
        int[] left = new int[ input.length ];
        int[] right = new int[ input.length ];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < i; j++) {
                if (input[ i ] > input[ j ]) {
                    left[ i ] = Math.max(left[ i ], left[ j ] + 1);
                }
            }
        }
        for (int i = input.length - 1; i >= 0; i--) {
            for (int j = input.length - 1; j > i; j--) {
                if (input[ i ] > input[ j ]) {
                    right[ i ] = Math.max(right[ i ], right[ j ] + 1);
                }
            }
        }

        System.out.println("Left: ");
        for (int i: left){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("Right: ");
        for (int i: right){
            System.out.print(i+" ");
        }
        System.out.println();

        int longest = 0;
        for (int i = 0; i < left.length; i++) {
            longest = Math.max(longest, left[ i ] + right[ i ] - 1);
        }
        System.out.println("(Tabulation) longest bitonic subsequence: " + longest);
    }
}