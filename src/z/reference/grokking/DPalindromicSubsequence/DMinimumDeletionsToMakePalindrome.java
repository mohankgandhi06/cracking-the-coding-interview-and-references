package z.reference.grokking.DPalindromicSubsequence;

public class DMinimumDeletionsToMakePalindrome {
    public static void main(String[] args) {
        DMinimumDeletionsToMakePalindrome game = new DMinimumDeletionsToMakePalindrome();
        String input = "abdbca";
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
        int longestSequence = solve(input, 0, input.length() - 1);
        int noOfDeletions = input.length() - longestSequence;
        System.out.println("(Brute Force) No of Deletions: " + noOfDeletions);
    }

    private int solve(String input, int startIndex, int endIndex) {
        if (startIndex > endIndex) return 0;
        if (startIndex == endIndex) return 1;
        if (input.charAt(startIndex) == input.charAt(endIndex)) {
            return 2 + solve(input, startIndex + 1, endIndex - 1);
        }
        int leftIndexExcluded = solve(input, startIndex + 1, endIndex);
        int rightIndexExcluded = solve(input, startIndex, endIndex - 1);
        return Math.max(leftIndexExcluded, rightIndexExcluded);
    }

    private void solveWithMemoization(String input) {
        Integer[][] memo = new Integer[ input.length() ][ input.length() ];
        int longestSequence = solve(input, 0, input.length() - 1, memo);
        int noOfDeletions = input.length() - longestSequence;
        System.out.println("(Memoization) No of Deletions: " + noOfDeletions);
    }

    private int solve(String input, int startIndex, int endIndex, Integer[][] memo) {
        if (startIndex > endIndex) return 0;
        if (startIndex == endIndex) return 1;
        if (memo[ startIndex ][ endIndex ] == null) {
            if (input.charAt(startIndex) == input.charAt(endIndex)) {
                return 2 + solve(input, startIndex + 1, endIndex - 1, memo);
            }
            int leftExcluded = solve(input, startIndex + 1, endIndex, memo);
            int rightExcluded = solve(input, startIndex, endIndex - 1, memo);
            memo[ startIndex ][ endIndex ] = Math.max(leftExcluded, rightExcluded);
        }
        return memo[ startIndex ][ endIndex ];
    }

    private void solveWithTabulation(String input) {
        int[][] matrix = new int[ input.length() ][ input.length() ];
        int longestSequence = solve(input, matrix);
        int noOfDeletions = input.length() - longestSequence;
        System.out.println("(Tabulation) No of Deletions: " + noOfDeletions);
    }

    private int solve(String input, int[][] matrix) {
        for (int index = 0; index < matrix.length; index++) {
            matrix[ index ][ index ] = 1;
        }
        for (int startIndex = matrix.length - 1; startIndex >= 0; startIndex--) {
            for (int endIndex = startIndex + 1; endIndex < matrix.length; endIndex++) {
                if (input.charAt(startIndex) == input.charAt(endIndex)) {
                    matrix[ startIndex ][ endIndex ] = 2 + matrix[ startIndex + 1 ][ endIndex - 1 ];
                } else {
                    matrix[ startIndex ][ endIndex ] = Math.max(matrix[ startIndex + 1 ][ endIndex ], matrix[ startIndex ][ endIndex - 1 ]);
                }
            }
        }
        return matrix[ 0 ][ matrix[ 0 ].length - 1 ];
    }
}