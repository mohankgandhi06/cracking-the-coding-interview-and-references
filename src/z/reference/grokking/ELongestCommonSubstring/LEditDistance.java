package z.reference.grokking.ELongestCommonSubstring;

public class LEditDistance {
    public static void main(String[] args) {
        LEditDistance game = new LEditDistance();
        String inputOne = "bat";
        String inputTwo = "but";
        System.out.println("InputOne: " + inputOne + " InputTwo: " + inputTwo);
        game.solveWithBruteForce(inputOne, inputTwo);
        game.solveWithMemoization(inputOne, inputTwo);
        game.solveWithTabulation(inputOne, inputTwo);

        inputOne = "abdca";
        inputTwo = "cbda";
        System.out.println("\nInputOne: " + inputOne + " InputTwo: " + inputTwo);
        game.solveWithBruteForce(inputOne, inputTwo);
        game.solveWithMemoization(inputOne, inputTwo);
        game.solveWithTabulation(inputOne, inputTwo);

        inputOne = "passpot";
        inputTwo = "ppsspqrt";
        System.out.println("\nInputOne: " + inputOne + " InputTwo: " + inputTwo);
        game.solveWithBruteForce(inputOne, inputTwo);
        game.solveWithMemoization(inputOne, inputTwo);
        game.solveWithTabulation(inputOne, inputTwo);
    }

    private void solveWithBruteForce(String inputOne, String inputTwo) {
        int result = solve(inputOne, inputTwo, 0, 0);
        System.out.println("(Brute Force) no. of edits to make inputOne as same as inputTwo: " + result);
    }

    private int solve(String inputOne, String inputTwo, int currentIndexOne, int currentIndexTwo) {
        if (currentIndexOne == inputOne.length()) {
            return inputTwo.length() - currentIndexTwo;
        }
        if (currentIndexTwo == inputTwo.length()) {
            return inputOne.length() - currentIndexOne;
        }
        if (inputOne.charAt(currentIndexOne) == inputTwo.charAt(currentIndexTwo)) {
            return solve(inputOne, inputTwo, currentIndexOne + 1, currentIndexTwo + 1);
        }
        int insert = 1 + solve(inputOne, inputTwo, currentIndexOne, currentIndexTwo + 1);
        int delete = 1 + solve(inputOne, inputTwo, currentIndexOne + 1, currentIndexTwo);
        int replace = 1 + solve(inputOne, inputTwo, currentIndexOne + 1, currentIndexTwo + 1);
        return Math.min(Math.min(insert, delete), replace);
    }

    private void solveWithMemoization(String inputOne, String inputTwo) {
        int[][] memo = new int[ inputOne.length() ][ inputTwo.length() ];
        int result = solve(inputOne, inputTwo, 0, 0, memo);
        System.out.println("(Memoization) no. of edits to make inputOne as same as inputTwo: " + result);
    }

    private int solve(String inputOne, String inputTwo, int currentIndexOne, int currentIndexTwo, int[][] memo) {
        if (currentIndexOne == inputOne.length()) return inputTwo.length() - currentIndexTwo;
        if (currentIndexTwo == inputTwo.length()) return inputOne.length() - currentIndexOne;
        if (memo[ currentIndexOne ][ currentIndexTwo ] == 0) {
            if (inputOne.charAt(currentIndexOne) == inputTwo.charAt(currentIndexTwo)) {
                return memo[ currentIndexOne ][ currentIndexTwo ] = solve(inputOne, inputTwo, currentIndexOne + 1, currentIndexTwo + 1, memo);
            }
            int insert = 1 + solve(inputOne, inputTwo, currentIndexOne, currentIndexTwo + 1, memo);
            int delete = 1 + solve(inputOne, inputTwo, currentIndexOne + 1, currentIndexTwo, memo);
            int replace = 1 + solve(inputOne, inputTwo, currentIndexOne + 1, currentIndexTwo + 1, memo);
            memo[ currentIndexOne ][ currentIndexTwo ] = Math.min(Math.min(insert, delete), replace);
        }
        return memo[ currentIndexOne ][ currentIndexTwo ];
    }

    private void solveWithTabulation(String inputOne, String inputTwo) {
        int[][] matrix = new int[ inputOne.length() + 1 ][ inputTwo.length() + 1 ];
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[ 0 ].length; column++) {
                if (inputOne.charAt(row-1) == inputTwo.charAt(column-1)) {
                    matrix[ row ][ column ] = matrix[ row - 1 ][ column - 1 ];
                    continue;
                }
                int insert = 1 + matrix[ row ][ column - 1 ];
                int delete = 1 + matrix[ row - 1 ][ column ];
                int replace = 1 + matrix[ row - 1 ][ column - 1 ];
                matrix[ row ][ column ] = Math.min(Math.min(insert, delete), replace);
            }
        }
        System.out.println("(Tabulation) no. of edits to make inputOne as same as inputTwo: " + matrix[ matrix.length - 1 ][ matrix[ 0 ].length - 1 ]);
    }
}