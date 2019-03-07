package z.reference.grokking.ELongestCommonSubstring;

public class ISubsequencePatternMatching {
    public static void main(String[] args) {
        ISubsequencePatternMatching game = new ISubsequencePatternMatching();
        String input = "baxmx";
        String pattern = "ax";
        System.out.println("Input: " + input + " Pattern: " + pattern);
        game.solveWithBruteForce(input, pattern);
        game.solveWithMemoization(input, pattern);
        game.solveWithTabulation(input, pattern);

        input = "tomorrow";
        pattern = "tor";
        System.out.println("\nInput: " + input + " Pattern: " + pattern);
        game.solveWithBruteForce(input, pattern);
        game.solveWithMemoization(input, pattern);
        game.solveWithTabulation(input, pattern);

        input = "madmadmax";
        pattern = "max";
        System.out.println("\nInput: " + input + " Pattern: " + pattern);
        game.solveWithBruteForce(input, pattern);
        game.solveWithMemoization(input, pattern);
        game.solveWithTabulation(input, pattern);
    }

    private void solveWithBruteForce(String input, String pattern) {
        int count = solve(input, pattern, 0, 0);
        System.out.println("(Brute Force) subsequence pattern matching: " + count);
    }

    private int solve(String input, String pattern, int inputIndex, int patternIndex) {
        if (inputIndex == input.length() && patternIndex != pattern.length()) return 0;
        if (patternIndex == pattern.length()) return 1;
        int match = 0;
        if (input.charAt(inputIndex) == pattern.charAt(patternIndex)) {
            match = solve(input, pattern, inputIndex + 1, patternIndex + 1);
        }
        int skipped = solve(input, pattern, inputIndex + 1, patternIndex);
        return match + skipped;
    }

    private void solveWithMemoization(String input, String pattern) {
        Integer[][] memo = new Integer[ input.length() ][ input.length() ];
        int count = solve(input, pattern, 0, 0, memo);
        System.out.println("(Memoization) subsequence pattern matching: " + count);
    }

    private int solve(String input, String pattern, int inputIndex, int patternIndex, Integer[][] memo) {
        if (input.length() == inputIndex && pattern.length() != patternIndex) return 0;
        if (pattern.length() == patternIndex) return 1;
        if (memo[ inputIndex ][ patternIndex ] == null) {
            int match = 0;
            if (input.charAt(inputIndex) == pattern.charAt(patternIndex)) {
                match = solve(input, pattern, inputIndex + 1, patternIndex + 1, memo);
            }
            int skipped = solve(input, pattern, inputIndex + 1, patternIndex, memo);
            memo[ inputIndex ][ patternIndex ] = match + skipped;
        }
        return memo[ inputIndex ][ patternIndex ];
    }

    private void solveWithTabulation(String input, String pattern) {
        int[][] matrix = new int[ input.length() + 1 ][ pattern.length() + 1 ];
        for (int row = 0; row < matrix.length; row++) {
            matrix[ row ][ 0 ] = 1;
        }
        int count = solve(input, pattern, matrix);
        System.out.println("(Tabulation) subsequence pattern matching: " + count);
    }

    private int solve(String input, String pattern, int[][] matrix) {
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[ 0 ].length; column++) {
                if (input.charAt(row - 1) == pattern.charAt(column - 1)) {
                    matrix[ row ][ column ] = matrix[ row - 1 ][ column - 1 ];
                }
                matrix[ row ][ column ] = matrix[ row ][ column ] + matrix[ row - 1 ][ column ];
            }
        }
        return matrix[ matrix.length - 1 ][ matrix[ 0 ].length - 1 ];
    }
}