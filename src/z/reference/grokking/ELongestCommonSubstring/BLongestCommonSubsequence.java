package z.reference.grokking.ELongestCommonSubstring;

public class BLongestCommonSubsequence {
    public static void main(String[] args) {
        BLongestCommonSubsequence game = new BLongestCommonSubsequence();
        String inputOne = "abdca";
        String inputTwo = "cbda";
        System.out.println("Input One: " + inputOne + "\nInput Two: " + inputTwo);
        game.solveWithBruteForce(inputOne, inputTwo);
        game.solveWithMemoization(inputOne, inputTwo);
        game.solveWithTabulation(inputOne, inputTwo);

        inputOne = "passport";
        inputTwo = "ppsspt";
        System.out.println("\nInput One: " + inputOne + "\nInput Two: " + inputTwo);
        game.solveWithBruteForce(inputOne, inputTwo);
        game.solveWithMemoization(inputOne, inputTwo);
        game.solveWithTabulation(inputOne, inputTwo);

        inputOne = "pasosptoo";
        inputTwo = "ppsspt";
        System.out.println("\nInput One: " + inputOne + "\nInput Two: " + inputTwo);
        game.solveWithBruteForce(inputOne, inputTwo);
        game.solveWithMemoization(inputOne, inputTwo);
        game.solveWithTabulation(inputOne, inputTwo);
    }

    private void solveWithBruteForce(String inputOne, String inputTwo) {
        int result = solve(inputOne, inputTwo, 0, 0, 0);
        System.out.println("(Brute Force) longest common subsequence: " + result);
    }

    private int solve(String inputOne, String inputTwo, int currentIndexOne, int currentIndexTwo, int count) {
        if (currentIndexOne == inputOne.length() || currentIndexTwo == inputTwo.length()) return count;
        int one = count;
        if (inputOne.charAt(currentIndexOne) == inputTwo.charAt(currentIndexTwo)) {
            one = solve(inputOne, inputTwo, currentIndexOne + 1, currentIndexTwo + 1, count + 1);
        }
        int two = solve(inputOne, inputTwo, currentIndexOne + 1, currentIndexTwo, count);
        int three = solve(inputOne, inputTwo, currentIndexOne, currentIndexTwo + 1, count);
        return Math.max(Math.max(one, two), three);
    }

    private void solveWithMemoization(String inputOne, String inputTwo) {
        Integer[][][] memo = new Integer[ inputOne.length() ][ inputTwo.length() ][ Math.max(inputOne.length(), inputTwo.length()) ];
        int result = solve(inputOne, inputTwo, 0, 0, 0, memo);
        System.out.println("(Memoization) longest common subsequence: " + result);
    }

    private int solve(String inputOne, String inputTwo, int currentIndexOne, int currentIndexTwo, int count, Integer[][][] memo) {
        if (currentIndexOne == inputOne.length() || currentIndexTwo == inputTwo.length()) return count;
        if (memo[ currentIndexOne ][ currentIndexTwo ][ count ] == null) {
            int one = count;
            if (inputOne.charAt(currentIndexOne) == inputTwo.charAt(currentIndexTwo)) {
                one = solve(inputOne, inputTwo, currentIndexOne + 1, currentIndexTwo + 1, count + 1, memo);
            }
            int two = solve(inputOne, inputTwo, currentIndexOne + 1, currentIndexTwo, count, memo);
            int three = solve(inputOne, inputTwo, currentIndexOne, currentIndexTwo + 1, count, memo);
            memo[ currentIndexOne ][ currentIndexTwo ][ count ] = Math.max(Math.max(one, two), three);
        }
        return memo[ currentIndexOne ][ currentIndexTwo ][ count ];
    }

    private void solveWithTabulation(String inputOne, String inputTwo) {
        int[][] matrix = new int[ inputOne.length() + 1 ][ inputTwo.length() + 1 ];
        int result = solve(inputOne, inputTwo, matrix);
        System.out.println("(Tabulation) longest common subsequence: " + result);
    }

    private int solve(String inputOne, String inputTwo, int[][] matrix) {
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[ 0 ].length; column++) {
                if (inputOne.charAt(row - 1) == inputTwo.charAt(column - 1)) {
                    matrix[ row ][ column ] = 1 + matrix[ row - 1 ][ column - 1 ];
                } else {
                    matrix[ row ][ column ] = Math.max(matrix[ row ][ column - 1 ], matrix[ row - 1 ][ column ]);
                }
            }
        }
        /*for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[ 0 ].length; column++) {
                System.out.print(matrix[ row ][ column ] + " ");
            }
            System.out.println();
        }*/
        return matrix[ matrix.length - 1 ][ matrix[ 0 ].length - 1 ];
    }
}