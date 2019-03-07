package z.reference.grokking.ELongestCommonSubstring;

public class FShortestCommonSupersequence {
    public static void main(String[] args) {
        FShortestCommonSupersequence game = new FShortestCommonSupersequence();
        String inputOne = "dynamic";
        String inputTwo = "programming";
        System.out.println("Input One: " + inputOne + " Input Two: " + inputTwo);
        game.solveWithBruteForce(inputOne, inputTwo);
        game.solveWithMemoization(inputOne, inputTwo);
        game.solveWithTabulation(inputOne, inputTwo);

        inputOne = "abcf";
        inputTwo = "bdcf";
        System.out.println("\nInput One: " + inputOne + " Input Two: " + inputTwo);
        game.solveWithBruteForce(inputOne, inputTwo);
        game.solveWithMemoization(inputOne, inputTwo);
        game.solveWithTabulation(inputOne, inputTwo);

        inputOne = "ffff";
        inputTwo = "asdfaff";
        System.out.println("\nInput One: " + inputOne + " Input Two: " + inputTwo);
        game.solveWithBruteForce(inputOne, inputTwo);
        game.solveWithMemoization(inputOne, inputTwo);
        game.solveWithTabulation(inputOne, inputTwo);
    }

    private void solveWithBruteForce(String inputOne, String inputTwo) {
        int result = solve(inputOne, inputTwo, 0, 0);
        System.out.println("(Brute Force) shortest common super sequence: " + result);
    }

    private int solve(String inputOne, String inputTwo, int currentIndexOne, int currentIndexTwo) {
        /* when we have reached the end of one string length then the other strings remaining characters has to be taken as such */
        if (currentIndexOne == inputOne.length()) return inputTwo.length() - currentIndexTwo;
        if (currentIndexTwo == inputTwo.length()) return inputOne.length() - currentIndexOne;
        if (inputOne.charAt(currentIndexOne) == inputTwo.charAt(currentIndexTwo)) {
            return 1 + solve(inputOne, inputTwo, currentIndexOne + 1, currentIndexTwo + 1);
        }
        int excludeLeft = 1 + solve(inputOne, inputTwo, currentIndexOne + 1, currentIndexTwo);
        int excludeRight = 1 + solve(inputOne, inputTwo, currentIndexOne, currentIndexTwo + 1);
        return Math.min(excludeLeft, excludeRight);
    }

    private void solveWithMemoization(String inputOne, String inputTwo) {
        Integer[][] memo = new Integer[ inputOne.length() ][ inputTwo.length() ];
        int result = solve(inputOne, inputTwo, 0, 0, memo);
        System.out.println("(Memoization) shortest common super sequence: " + result);
    }

    private int solve(String inputOne, String inputTwo, int currentIndexOne, int currentIndexTwo, Integer[][] memo) {
        if (inputOne.length() == currentIndexOne) return inputTwo.length() - currentIndexTwo;
        if (inputTwo.length() == currentIndexTwo) return inputOne.length() - currentIndexOne;
        if (memo[ currentIndexOne ][ currentIndexTwo ] == null) {
            if (inputOne.charAt(currentIndexOne) == inputTwo.charAt(currentIndexTwo)) {
                return 1 + solve(inputOne, inputTwo, currentIndexOne + 1, currentIndexTwo + 1, memo);
            }
            int excludeLeft = 1 + solve(inputOne, inputTwo, currentIndexOne + 1, currentIndexTwo, memo);
            int excludeRight = 1 + solve(inputOne, inputTwo, currentIndexOne, currentIndexTwo + 1, memo);
            memo[ currentIndexOne ][ currentIndexTwo ] = Math.min(excludeLeft, excludeRight);
        }
        return memo[ currentIndexOne ][ currentIndexTwo ];
    }

    private void solveWithTabulation(String inputOne, String inputTwo) {
        int[][] matrix = new int[ inputOne.length() + 1 ][ inputTwo.length() + 1 ];
        for (int row = 1; row < matrix.length; row++) {
            matrix[ row ][ 0 ] = row;
        }
        for (int column = 1; column < matrix[ 0 ].length; column++) {
            matrix[ 0 ][ column ] = column;
        }
        int result = solve(inputOne, inputTwo, matrix);
        System.out.println("(Tabulation) shortest common super sequence: " + result);
    }

    private int solve(String inputOne, String inputTwo, int[][] matrix) {
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[ 0 ].length; column++) {
                if (inputOne.charAt(row - 1) == inputTwo.charAt(column - 1)) {
                    matrix[ row ][ column ] = 1 + matrix[ row - 1 ][ column - 1 ];
                } else {
                    matrix[ row ][ column ] = 1 + Math.min(matrix[ row ][ column - 1 ], matrix[ row - 1 ][ column ]);
                }
            }
        }
        return matrix[ matrix.length - 1 ][ matrix[ 0 ].length - 1 ];
    }
}