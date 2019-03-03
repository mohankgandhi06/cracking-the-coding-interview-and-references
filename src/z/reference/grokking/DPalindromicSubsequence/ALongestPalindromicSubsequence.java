package z.reference.grokking.DPalindromicSubsequence;

public class ALongestPalindromicSubsequence {
    public static void main(String[] args) {
        ALongestPalindromicSubsequence game = new ALongestPalindromicSubsequence();
        String input = "abdbca";
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
        System.out.println("Brute Force: " + solve(input, 0, input.length() - 1));
    }

    private int solve(String input, int startIndex, int endIndex) {
        if (startIndex > endIndex) return 0;
        if (startIndex == endIndex) return 1;
        if (input.charAt(startIndex) == input.charAt(endIndex)) {
            return 2 + solve(input, startIndex + 1, endIndex - 1);
        }
        int leftSideExcluded = solve(input, startIndex + 1, endIndex);
        int rightSideExcluded = solve(input, startIndex, endIndex - 1);
        return Math.max(leftSideExcluded, rightSideExcluded);
    }

    private void solveWithMemoization(String input) {
        Integer[][] memo = new Integer[ input.length() ][ input.length() ];
        System.out.println("Memoization: " + solve(input, 0, input.length() - 1, memo));
    }

    private int solve(String input, int startIndex, int endIndex, Integer[][] memo) {
        if (startIndex > endIndex) return 0;
        if (startIndex == endIndex) return 1;
        if (memo[ startIndex ][ endIndex ] == null) {
            if (input.charAt(startIndex) == input.charAt(endIndex)) {
                memo[ startIndex ][ endIndex ] = 2 + solve(input, startIndex + 1, endIndex - 1, memo);
            } else {
                int leftSideExcluded = solve(input, startIndex + 1, endIndex, memo);
                int rightSideExcluded = solve(input, startIndex, endIndex - 1, memo);
                memo[ startIndex ][ endIndex ] = Math.max(leftSideExcluded, rightSideExcluded);
            }
        }
        return memo[ startIndex ][ endIndex ];
    }

    private void solveWithTabulation(String input) {
        int[][] matrix = new int[ input.length() ][ input.length() ];
        System.out.println("Tabulation: " + solve(input, matrix));
    }

    private int solve(String input, int[][] matrix) {
        for (int location = 0; location < matrix.length; location++) {
            matrix[ location ][ location ] = 1;//When comparing the same character it will be 1
        }
        for (int row = matrix.length - 1; row >= 0; row--) {
            for (int column = row + 1; column < matrix.length; column++) {
                if (input.charAt(row) == input.charAt(column)) {
                    matrix[ row ][ column ] = 2 + matrix[ row + 1 ][ column - 1 ];
                } else {
                    matrix[ row ][ column ] = Math.max(matrix[ row ][ column - 1 ], matrix[ row + 1 ][ column ]);
                }
            }
        }
        return matrix[ 0 ][ matrix[ 0 ].length - 1 ];
    }
}