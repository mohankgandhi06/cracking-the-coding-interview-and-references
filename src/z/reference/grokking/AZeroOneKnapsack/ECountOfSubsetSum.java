package z.reference.grokking.AZeroOneKnapsack;

public class ECountOfSubsetSum {

    public int[] input;
    public int sum;

    public ECountOfSubsetSum(int[] input, int sum) {
        this.input = input;
        this.sum = sum;
    }

    public static void main(String[] args) {
        /*int[] input = {1, 3, 2, 1};*/
        /*int[] input = {1, 3, 2, 1, 1};*/
        int[] input = {1, 3, 2, 4, 1, 2, 2};
        int sum = 4;
        ECountOfSubsetSum game = new ECountOfSubsetSum(input, sum);
        /*game.solveWithBruteForce();*/
        /*game.solveWithMemoization();*/
        game.solveWithTabulation();
    }

    private void solveWithBruteForce() {
        System.out.println("Count of subset with the sum " + this.sum + " is: " + solve(0, 0, 0));
    }

    private int solve(int index, int sum, int count) {
        if (sum == this.sum) return count + 1;
        if (sum > this.sum || index >= this.input.length) return count;
        /* INCLUDE */
        count = solve(index + 1, sum + this.input[ index ], count);
        /* EXCLUDE */
        count = solve(index + 1, sum, count);
        return count;
    }

    private void solveWithMemoization() {
        System.out.println("Count of subset with the sum " + this.sum + " is: " + solve(0, 0, 0, new Integer[ this.input.length + 1 ][ this.sum + 1 ]));
    }

    private int solve(int index, int sum, int count, Integer[][] memo) {
        if (sum == this.sum) return count + 1;
        if (sum > this.sum || index >= this.input.length) return count;
        if (memo[ index ][ sum ] == null) {
            /* INCLUDE */
            count = solve(index + 1, sum + this.input[ index ], count);
            /* EXCLUDE */
            count = solve(index + 1, sum, count);

            memo[ index ][ sum ] = count;
        }
        return memo[ index ][ sum ];
    }

    private void solveWithTabulation() {
        int[][] matrix = new int[ this.input.length + 1 ][ this.sum + 1 ];
        solve(matrix);
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[ 0 ].length; column++) {
                System.out.print(matrix[ row ][ column ] + "  ");
            }
            System.out.println();
        }
    }

    private int[][] solve(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            matrix[ row ][ 0 ] = 1;
        }
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[ 0 ].length; column++) {
                int include = column - this.input[ row - 1 ] >= 0 ? matrix[ row - 1 ][ column - this.input[ row - 1 ] ] : 0;
                int exclude = matrix[ row - 1 ][ column ];
                matrix[ row ][ column ] = include + exclude;
            }
        }
        return matrix;
    }
}