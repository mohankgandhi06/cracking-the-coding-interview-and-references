package z.reference.grokking.ELongestCommonSubstring;

public class ALongestCommonSubstring {
    public static void main(String[] args) {
        ALongestCommonSubstring game = new ALongestCommonSubstring();
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

        inputOne = "passpt";
        inputTwo = "ppsspt";
        System.out.println("\nInput One: " + inputOne + "\nInput Two: " + inputTwo);
        game.solveWithBruteForce(inputOne, inputTwo);
        game.solveWithMemoization(inputOne, inputTwo);
        game.solveWithTabulation(inputOne, inputTwo);

    }

    private void solveWithBruteForce(String stringOne, String stringTwo) {
        int result = solve(stringOne, stringTwo, 0, 0, 0);
        System.out.println("(Brute Force) longest common substring: " + result);
    }

    private int solve(String stringOne, String stringTwo, int currentIndexOne, int currentIndexTwo, int count) {
        if (currentIndexOne == stringOne.length() || currentIndexTwo == stringTwo.length()) return count;
        int one = count;
        if (stringOne.charAt(currentIndexOne) == stringTwo.charAt(currentIndexTwo)) {
             one = solve(stringOne, stringTwo, currentIndexOne + 1, currentIndexTwo + 1, count + 1);
        }
        int two = solve(stringOne, stringTwo, currentIndexOne + 1, currentIndexTwo, 0);
        int three = solve(stringOne, stringTwo, currentIndexOne, currentIndexTwo + 1, 0);
        return Math.max(Math.max(one, two), three);
    }

    private void solveWithMemoization(String inputOne, String inputTwo) {
        Integer[][][] memo = new Integer[ inputOne.length() ][ inputTwo.length() ][ Math.max(inputOne.length(), inputTwo.length()) ];
        int result = solve(inputOne, inputTwo, 0, 0, 0, memo);
        System.out.println("(Memoization) longest common substring: " + result);
    }

    private int solve(String stringOne, String stringTwo, int currentIndexOne, int currentIndexTwo, int count, Integer[][][] memo) {
        if (currentIndexOne == stringOne.length() || currentIndexTwo == stringTwo.length()) return count;
        if (memo[ currentIndexOne ][ currentIndexTwo ][ count ] == null) {
            int one = count;
            if (stringOne.charAt(currentIndexOne) == stringTwo.charAt(currentIndexTwo)) {
                one = solve(stringOne, stringTwo, currentIndexOne + 1, currentIndexTwo + 1, count + 1, memo);
            }
            int two = solve(stringOne, stringTwo, currentIndexOne + 1, currentIndexTwo, 0, memo);
            int three = solve(stringOne, stringTwo, currentIndexOne, currentIndexTwo + 1, 0, memo);
            memo[ currentIndexOne ][ currentIndexTwo ][ count ] = Math.max(Math.max(one, two), three);
        }
        return memo[ currentIndexOne ][ currentIndexTwo ][ count ];
    }

    private void solveWithTabulation(String inputOne, String inputTwo) {
        int[][] matrix = new int[ inputOne.length() + 1 ][ inputTwo.length() + 1 ];
        int result = solve(inputOne, inputTwo, matrix);
        System.out.println("(Tabulation) longest common substring: " + result);
    }

    private int solve(String inputOne, String inputTwo, int[][] matrix) {
        int count = 0;
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[ 0 ].length; column++) {
                if (inputOne.charAt(row - 1) == inputTwo.charAt(column - 1)) {
                    matrix[ row ][ column ] = 1 + matrix[ row - 1 ][ column - 1 ];
                    count = Math.max(count, matrix[ row ][ column ]);
                }
            }
        }
        return count;
    }
}