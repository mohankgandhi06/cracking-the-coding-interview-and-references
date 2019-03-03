package z.reference.grokking.DPalindromicSubsequence;

public class EPalindromicPartition {
    public static void main(String[] args) {
        EPalindromicPartition game = new EPalindromicPartition();
        String input = "abdbca";
        System.out.println(input);
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = "abdca";
        System.out.println("\n" + input);
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = "aabd";
        System.out.println("\n" + input);
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = "dfaaa";
        System.out.println("\n" + input);
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = "cddpd";
        System.out.println("\n" + input);
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = "pqr";
        System.out.println("\n" + input);
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = "pp";
        System.out.println("\n" + input);
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = "madam";
        System.out.println("\n" + input);
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);
    }

    private void solveWithBruteForce(String input) {
        int result = solve(input, 0, input.length() - 1);
        System.out.println("(Brute Force) minimum cuts to make all the substrings palindromic: " + result);
    }

    private int solve(String input, int startingIndex, int endingIndex) {
        if (startingIndex >= endingIndex || isPalindromic(input, startingIndex, endingIndex)) return 0;
        int minimum = endingIndex - startingIndex;
        for (int i = startingIndex; i <= endingIndex; i++) {
            if (isPalindromic(input, startingIndex, i)) {
                minimum = Math.min(minimum, 1 + solve(input, i + 1, endingIndex));
            }
        }
        return minimum;
    }

    private boolean isPalindromic(String input, int startingIndex, int endingIndex) {
        while (startingIndex < endingIndex) {
            if (input.charAt(startingIndex) != input.charAt(endingIndex)) {
                return false;
            }
            startingIndex++;
            endingIndex--;
        }
        return true;
    }

    private void solveWithMemoization(String input) {
        Integer[][] memo = new Integer[ input.length() ][ input.length() ];
        int result = solve(input, 0, input.length() - 1, memo);
        System.out.println("(Memoization) minimum cuts to make all the substrings palindromic: " + result);
    }

    private int solve(String input, int startingIndex, int endingIndex, Integer[][] memo) {
        if (startingIndex >= endingIndex || isPalindromic(input, startingIndex, endingIndex)) return 0;
        if (memo[ startingIndex ][ endingIndex ] == null) {
            int minimum = endingIndex - startingIndex;
            for (int i = startingIndex; i <= endingIndex; i++) {
                if (isPalindromic(input, startingIndex, i)) {
                    minimum = Math.min(minimum, 1 + solve(input, i + 1, endingIndex));
                }
            }
            memo[ startingIndex ][ endingIndex ] = minimum;
        }
        return memo[ startingIndex ][ endingIndex ];
    }

    private void solveWithTabulation(String input) {
        boolean[][] matrix = new boolean[ input.length() ][ input.length() ];
        for (int i = 0; i < matrix.length; i++) {
            matrix[ i ][ i ] = true;//Comparing same element will be a palindrome
        }
        int result = solve(input, matrix);
        System.out.println("(Tabulation) minimum cuts to make all the substrings palindromic: " + result);
    }

    private int solve(String input, boolean[][] matrix) {
        /* Populate the boolean matrix which will tell if the substring is a palindrome or not
         * for a substring to be a palindrome its inner substring [row+1][column-1] needs to be
         * palindrome as well or there should not be any element in-between */
        for (int startIndex = matrix.length - 1; startIndex >= 0; startIndex--) {
            for (int endIndex = startIndex + 1; endIndex < matrix.length; endIndex++) {
                if (input.charAt(startIndex) == input.charAt(endIndex)) {
                    if ((endIndex - startIndex == 1) || matrix[ startIndex + 1 ][ endIndex - 1 ]) {
                        matrix[ startIndex ][ endIndex ] = true;
                    }
                }
            }
        }
        int[] noOfCuts = new int[ input.length() ];
        for (int startIndex = matrix.length - 1; startIndex >= 0; startIndex--) {
            int minimumCuts = input.length();
            for (int endIndex = matrix.length - 1; endIndex >= 0; endIndex--) {
                if (matrix[ startIndex ][ endIndex ]) {
                    /* If the matrix[ startIndex ][ endIndex ] then it means that it is a palindrome and so we can
                     * make a cut after it. i.e since we don't know at this point of time the future character to be
                     * compared we are only thinking if we can cut after a palindrome. If we are comparing the
                     * numOfCuts{previous character location} with the current to find out the minimum cuts possible */
                    minimumCuts = (endIndex == input.length() - 1) ? 0 : Math.min(minimumCuts, 1 + noOfCuts[ endIndex + 1 ]);
                }
            }
            noOfCuts[ startIndex ] = minimumCuts;
        }
        return noOfCuts[ 0 ];
    }
}