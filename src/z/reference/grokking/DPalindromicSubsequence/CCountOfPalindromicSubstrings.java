package z.reference.grokking.DPalindromicSubsequence;

public class CCountOfPalindromicSubstrings {

    public static void main(String[] args) {
        CCountOfPalindromicSubstrings game = new CCountOfPalindromicSubstrings();
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
    }

    private void solveWithBruteForce(String input) {
        int result = solve(input, 0, input.length() - 1, new Count(input.length()));
        System.out.println("(Brute Force) Count: " + result);
    }

    private int solve(String input, int startIndex, int endIndex, Count count) {
        /* This method will only count the list of palindromic substring and not the individual characters.
         * so the final count will be the sum of count value returned and the individual count of the characters */
        if (startIndex > endIndex) return 0;
        if (startIndex == endIndex) return 1;
        if (input.charAt(startIndex) == input.charAt(endIndex)) {
            int remainingLength = endIndex - startIndex - 1;
            if (remainingLength == solve(input, startIndex + 1, endIndex - 1, count)) {
                return count.count++;
            }
        } else {
            solve(input, startIndex + 1, endIndex, count);
            solve(input, startIndex, endIndex - 1, count);
        }
        return count.count;
    }

    private void solveWithMemoization(String input) {
        Integer[][] memo = new Integer[ input.length() ][ input.length() ];
        int result = solve(input, 0, input.length() - 1, new Count(input.length()), memo);
        System.out.println("(Memoization) Count: " + result);
    }

    private int solve(String input, int startIndex, int endIndex, Count count, Integer[][] memo) {
        if (startIndex > endIndex) return 0;
        if (startIndex == endIndex) return 1;
        if (memo[ startIndex ][ endIndex ] == null) {
            if (input.charAt(startIndex) == input.charAt(endIndex)) {
                int x = 0;//Just to skip the IntelliJ duplicate code warning. this is not a part of the program
                int remainingLength = endIndex - startIndex - 1;
                if (remainingLength == solve(input, startIndex + 1, endIndex - 1, count)) {
                    return count.count++;
                }
            } else {
                solve(input, startIndex + 1, endIndex, count);
                solve(input, startIndex, endIndex - 1, count);
            }
            memo[ startIndex ][ endIndex ] = count.count;
        }
        return memo[ startIndex ][ endIndex ];
    }

    private void solveWithTabulation(String input) {
        boolean[][] matrix = new boolean[ input.length() ][ input.length() ];
        int result = solve(input, matrix);
        System.out.println("(Tabulation) Count: " + result);
    }

    private int solve(String input, boolean[][] matrix) {
        for (int index = 0; index < matrix.length; index++) {
            matrix[ index ][ index ] = true;
        }
        int count = input.length();
        for (int startIndex = matrix.length - 1; startIndex >= 0; startIndex--) {
            for (int endIndex = startIndex + 1; endIndex < matrix.length; endIndex++) {
                if (input.charAt(startIndex) == input.charAt(endIndex)) {
                    if (endIndex - startIndex == 1 || matrix[ startIndex + 1 ][ endIndex - 1 ]) {
                        matrix[ startIndex ][ endIndex ] = true;
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

class Count {
    protected int count;

    public Count(int count) {
        this.count = count;
    }
}