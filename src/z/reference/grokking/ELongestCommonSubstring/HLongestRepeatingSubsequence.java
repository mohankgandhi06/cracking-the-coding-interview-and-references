package z.reference.grokking.ELongestCommonSubstring;

public class HLongestRepeatingSubsequence {
    public static void main(String[] args) {
        HLongestRepeatingSubsequence game = new HLongestRepeatingSubsequence();
        String input = "tomorrow";
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = "abadbbcec";
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);

        input = "fmff";
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
        game.solveWithTabulation(input);
    }

    private void solveWithBruteForce(String input) {
        int result = solve(input, 0, 0);
        System.out.println("(Brute Force) longest repeating subsequence: " + result);
    }

    private int solve(String input, int currentIndexOne, int currentIndexTwo) {
        if (currentIndexOne == input.length() || currentIndexTwo == input.length()) return 0;
        if (currentIndexOne != currentIndexTwo && input.charAt(currentIndexOne) == input.charAt(currentIndexTwo)) {
            return 1 + solve(input, currentIndexOne + 1, currentIndexTwo + 1);
        }
        int excludeLeft = solve(input, currentIndexOne + 1, currentIndexTwo);
        int excludeRight = solve(input, currentIndexOne, currentIndexTwo + 1);
        return Math.max(excludeLeft, excludeRight);
    }

    private void solveWithMemoization(String input) {
        Integer[][] memo = new Integer[ input.length() ][ input.length() ];
        int result = solve(input, 0, 0, memo);
        System.out.println("(Memoization) longest repeating subsequence: " + result);
    }

    private int solve(String input, int currentIndexOne, int currentIndexTwo, Integer[][] memo) {
        if (currentIndexOne == input.length() || currentIndexTwo == input.length()) return 0;
        if (memo[ currentIndexOne ][ currentIndexTwo ] == null) {
            if (currentIndexOne != currentIndexTwo && input.charAt(currentIndexOne) == input.charAt(currentIndexTwo)) {
                return 1 + solve(input, currentIndexOne + 1, currentIndexTwo + 1, memo);
            }
            int excludeLeft = solve(input, currentIndexOne + 1, currentIndexTwo, memo);
            int excludeRight = solve(input, currentIndexOne, currentIndexTwo + 1, memo);
            memo[ currentIndexOne ][ currentIndexTwo ] = Math.max(excludeLeft, excludeRight);
        }
        return memo[ currentIndexOne ][ currentIndexTwo ];
    }

    private void solveWithTabulation(String input) {
        int[][] matrix = new int[ input.length() + 1 ][ input.length() + 1 ];
        int result = solve(input, matrix);
        System.out.println("(Tabulation) longest repeating subsequence: " + result);
    }

    private int solve(String input, int[][] matrix) {
        for (int currentIndexOne = 1; currentIndexOne < matrix.length; currentIndexOne++) {
            for (int currentIndexTwo = 1; currentIndexTwo < matrix[ 0 ].length; currentIndexTwo++) {
                if (currentIndexOne != currentIndexTwo && input.charAt(currentIndexOne - 1) == input.charAt(currentIndexTwo - 1)) {
                    matrix[ currentIndexOne ][ currentIndexTwo ] = 1 + matrix[ currentIndexOne - 1 ][ currentIndexTwo - 1 ];
                } else {
                    matrix[ currentIndexOne ][ currentIndexTwo ] = Math.max(matrix[ currentIndexOne ][ currentIndexTwo - 1 ], matrix[ currentIndexOne - 1 ][ currentIndexTwo ]);
                }
            }
        }
        return matrix[ input.length() ][ input.length() ];
    }
}