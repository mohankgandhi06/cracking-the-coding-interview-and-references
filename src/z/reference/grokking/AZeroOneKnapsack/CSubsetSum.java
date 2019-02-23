package z.reference.grokking.AZeroOneKnapsack;

public class CSubsetSum {

    public int[] input;
    public int sum;

    public CSubsetSum(int[] input, int sum) {
        this.input = input;
        this.sum = sum;
    }

    public static void main(String[] args) {
        int[] input = {3, 7, 8, 6, 2, 3};
        int sum = 6;
        CSubsetSum game = new CSubsetSum(input, sum);

        System.out.println("Brute Force Approach: ");
        System.out.println("Is a subset possible: " + (game.solveWithBruteForce(sum, 0) ? "T" : "F"));
        System.out.println();

        System.out.println("Memoization Approach: ");
        System.out.println("Is a subset possible: " + (game.solveWithMemoization() ? "T" : "F"));
        System.out.println();

        System.out.println("Tabulation Approach: ");
        System.out.println("Is a subset possible: " + (game.solveWithTabulation() ? "T" : "F"));
    }

    private boolean solveWithBruteForce(int sum, int currentIndex) {
        if (sum == 0) return true;
        if (currentIndex >= this.input.length) return false;
        /* INCLUDE the current number only if the sum - current number is greater than or equal to zero */
        if (this.input[ currentIndex ] <= sum) {
            if (solveWithBruteForce(sum - this.input[ currentIndex ], currentIndex + 1)) {
                return true;
            }
        }
        /* EXCLUDE the current number and procees with the rest of the process */
        return solveWithBruteForce(sum, currentIndex + 1);
    }

    private boolean solveWithMemoization() {
        Boolean[][] memo = new Boolean[ this.input.length + 1 ][ this.sum + 1 ];
        return solve(memo, this.sum, 0);
    }

    private boolean solve(Boolean[][] memo, int sum, int index) {
        if (sum == 0) return true;
        if (index >= this.input.length) return false;
        if (memo[ index ][ sum ] == null) {
            /* INCLUDE */
            if (this.input[ index ] <= sum) {
                if (solve(memo, sum - this.input[ index ], index + 1)) {
                    memo[ index ][ sum ] = true;
                    return true;
                }
            }
            /* EXCLUDE */
            memo[ index ][ sum ] = solve(memo, sum, index + 1);
        }
        return memo[ index ][ sum ];
    }

    private boolean solveWithTabulation() {
        Boolean[][] matrix = new Boolean[ this.input.length + 1 ][ this.sum + 1 ];

        for (int column = 0; column < matrix[ 0 ].length; column++) {
            matrix[ 0 ][ column ] = false;
        }

        for (int row = 0; row < matrix.length; row++) {
            matrix[ row ][ 0 ] = true;
        }

        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[ 0 ].length; column++) {
                boolean include = (column - this.input[ row - 1 ]) >= 0 ? matrix[ row - 1 ][ column - this.input[ row - 1 ] ] : false;
                boolean exclude = matrix[ row - 1 ][ column ];
                matrix[ row ][ column ] = (include || exclude);
            }
        }

        showMatrix(matrix);

        return matrix[ matrix.length - 1 ][ matrix[ 0 ].length - 1 ];
    }

    private void showMatrix(Boolean[][] matrix) {
        System.out.println("Matrix is as follows: ");
        System.out.print("    ");
        for (int i = 0; i < matrix[ 0 ].length; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();
        for (int row = 0; row < matrix.length; row++) {
            System.out.print((row - 1 >= 0 ? this.input[ row - 1 ] : "0") + "   ");
            for (int column = 0; column < matrix[ 0 ].length; column++) {
                System.out.print((matrix[ row ][ column ] ? "T" : "F") + "   ");
            }
            System.out.println();
        }
        System.out.println();
    }
}