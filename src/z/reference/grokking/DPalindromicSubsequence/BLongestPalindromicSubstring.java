package z.reference.grokking.DPalindromicSubsequence;

public class BLongestPalindromicSubstring {

    public static void main(String[] args) {
        BLongestPalindromicSubstring game = new BLongestPalindromicSubstring();
        String input = "abdbca";
        System.out.println(input);
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);
        System.out.println();
        input = "abdca";
        System.out.println(input);
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);
        System.out.println();
        input = "aabd";
        System.out.println(input);
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);
        System.out.println();
        input = "dfaaa";
        System.out.println(input);
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);
        System.out.println();
        input = "cddpd";
        System.out.println(input);
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);
        System.out.println();
        input = "pqr";
        System.out.println(input);
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);
    }

    private void solveWithBruteForce(String input) {
        int result = solve(input, 0, input.length() - 1);
        System.out.println("(Brute Force) Length: " + result);
    }

    private int solve(String input, int startIndex, int endIndex) {
        if (startIndex > endIndex) return 0;
        if (startIndex == endIndex) return 1;
        /* If equal then process to see if the subsequent is equal as well if so then add the result of the
        recursive call with the current count of 2 */
        int lengthOne = 0;
        if (input.charAt(startIndex) == input.charAt(endIndex)) {
            int remainingLength = endIndex - startIndex - 1;//Calculate how many elements remain after the current
            // if the remainingLength becomes equal to the count of the recursive call it means all the inside characters
            // are a palindrome and so we can go ahead and add the current 2 characters as well since they are equal
            if (remainingLength == solve(input, startIndex + 1, endIndex - 1)) {
                lengthOne = remainingLength + 2;
            }
        }
        int lengthTwo = solve(input, startIndex + 1, endIndex);
        int lengthThree = solve(input, startIndex, endIndex - 1);
        return Math.max(Math.max(lengthOne, lengthTwo), lengthThree);
    }

    private void solveWithMemoization(String input) {
        Integer[][] memo = new Integer[ input.length() ][ input.length() ];
        int result = solve(input, 0, input.length() - 1, memo);
        System.out.println("(Memoization) Length: " + result);
    }

    private int solve(String input, int startIndex, int endIndex, Integer[][] memo) {
        if (startIndex > endIndex) return 0;
        if (startIndex == endIndex) return 1;
        if (memo[ startIndex ][ endIndex ] == null) {
            int lengthOne = 0;
            if (input.charAt(startIndex) == input.charAt(endIndex)) {
                int remainingLength = endIndex - startIndex - 1;
                if (remainingLength == solve(input, startIndex + 1, endIndex - 1, memo)) {
                    lengthOne = remainingLength + 2;
                }
            }
            int lengthTwo = solve(input, startIndex + 1, endIndex, memo);
            int lengthThree = solve(input, startIndex, endIndex - 1, memo);
            memo[ startIndex ][ endIndex ] = Math.max(Math.max(lengthOne, lengthTwo), lengthThree);
        }
        return memo[ startIndex ][ endIndex ];
    }

    private void solveWithTabulation(String input) {
        boolean[][] matrix = new boolean[ input.length() ][ input.length() ];
        int result = solve(input, matrix);
        System.out.println("(Tabulation) Length: " + result);
    }

    private int solve(String input, boolean[][] matrix) {
        for (int index = 0; index < matrix.length; index++) {
            matrix[ index ][ index ] = true;
        }
        int maxLength = 1;
        for (int startIndex = matrix.length - 1; startIndex >= 0; startIndex--) {
            for (int endIndex = startIndex + 1; endIndex < matrix.length; endIndex++) {
                if (input.charAt(startIndex) == input.charAt(endIndex)) {
                    if (endIndex - startIndex == 1 || matrix[ startIndex + 1 ][ endIndex - 1 ]) {
                        matrix[ startIndex ][ endIndex ] = true;
                        maxLength = Math.max(maxLength, endIndex - startIndex + 1);
                    }
                }
            }
        }
        return maxLength;
    }
}