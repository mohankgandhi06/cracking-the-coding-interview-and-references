package z.reference.grokking.ELongestCommonSubstring;

public class CMinimumDeletionsAndInsertions {
    public static void main(String[] args) {
        CMinimumDeletionsAndInsertions game = new CMinimumDeletionsAndInsertions();
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
        inputTwo = "passpt";
        System.out.println("\nInput One: " + inputOne + "\nInput Two: " + inputTwo);
        game.solveWithBruteForce(inputOne, inputTwo);
        game.solveWithMemoization(inputOne, inputTwo);
        game.solveWithTabulation(inputOne, inputTwo);

        inputOne = "pasosptoo";
        inputTwo = "psspt";
        System.out.println("\nInput One: " + inputOne + "\nInput Two: " + inputTwo);
        game.solveWithBruteForce(inputOne, inputTwo);
        game.solveWithMemoization(inputOne, inputTwo);
        game.solveWithTabulation(inputOne, inputTwo);
    }

    private void solveWithBruteForce(String inputOne, String inputTwo) {
        int longestCommonSequence = solve(inputOne, inputTwo, 0, 0, 0);
        /* longest common sequence returns the number of matching character in inputOne and inputTwo
         * if we need to find out the deletions required then we need to remove all the unmatched characters
         * from the inputOne */
        int minimumDeletions = inputOne.length() - longestCommonSequence;
        /* we will need to insert the characters which are present in inputTwo but not in the inputOne i.e. uncommon
         * no of characters from the inputTwo will need to be inserted into the inputOne so as to make inputOne equal
         * as the inputTwo */
        int minimumInsertions = inputTwo.length() - longestCommonSequence;
        System.out.println("(Brute Force) minimum deletions: " + minimumDeletions + " minimum insertions: " + minimumInsertions + " are required to make the " + inputOne + " equal to " + inputTwo);
    }

    private int solve(String inputOne, String inputTwo, int currentIndexOne, int currentIndexTwo, int count) {
        /* First we find the subsequence and then calculate the deletions and insertions needed to achieve same characters */
        if (currentIndexOne == inputOne.length() || currentIndexTwo == inputTwo.length()) return count;
        int one = count;
        if (inputOne.charAt(currentIndexOne) == inputTwo.charAt(currentIndexTwo)) {
            int k = 0;//Just added to skip the duplicate code validation in IntelliJ
            /* Since the characters are equal we are trying to check the next set of characters with the increased count */
            one = solve(inputOne, inputTwo, currentIndexOne + 1, currentIndexTwo + 1, count + 1);
        }
        /* Since the characters are not equal therefore we are trying to check both the possibilities increasing one element in the
         * first string and then by increasing the element index in the second string we are proceeding with two recursive calls
         * to decide the longest common sequence */
        int two = solve(inputOne, inputTwo, currentIndexOne + 1, currentIndexTwo, count);
        int three = solve(inputOne, inputTwo, currentIndexOne, currentIndexTwo + 1, count);
        return Math.max(Math.max(one, two), three);
    }

    private void solveWithMemoization(String inputOne, String inputTwo) {
        Integer[][][] memo = new Integer[ inputOne.length() ][ inputTwo.length() ][ Math.max(inputOne.length(), inputTwo.length()) ];
        int longestCommonSequence = solve(inputOne, inputTwo, 0, 0, 0, memo);
        int minimumDeletions = inputOne.length() - longestCommonSequence;
        int minimumInsertions = inputTwo.length() - longestCommonSequence;
        System.out.println("(Memoization) minimum deletions: " + minimumDeletions + " minimum insertions: " + minimumInsertions + " are required to make the " + inputOne + " equal to " + inputTwo);
    }

    private int solve(String inputOne, String inputTwo, int currentIndexOne, int currentIndexTwo, int count, Integer[][][] memo) {
        if (currentIndexOne == inputOne.length() || currentIndexTwo == inputTwo.length()) return count;
        if (memo[ currentIndexOne ][ currentIndexTwo ][ count ] == null) {
            int k = 0;//Just added to skip the duplicate code validation in IntelliJ
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
        int longestCommonSequence = solve(inputOne, inputTwo, matrix);
        int minimumDeletions = inputOne.length() - longestCommonSequence;
        int minimumInsertions = inputTwo.length() - longestCommonSequence;
        System.out.println("(Tabulation) minimum deletions: " + minimumDeletions + " minimum insertions: " + minimumInsertions + " are required to make the " + inputOne + " equal to " + inputTwo);
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