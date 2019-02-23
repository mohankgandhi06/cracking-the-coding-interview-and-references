package z.reference.grokking.AZeroOneKnapsack;

public class DMinimumSubsetSumDifference {

    public int[] input;
    public int total;

    public DMinimumSubsetSumDifference(int[] input) {
        this.input = input;
        int total = 0;
        for (int i : this.input) {
            total = total + i;
        }
        this.total = total;
    }

    public static void main(String[] args) {
        int[] input = {1, 2, 3, 9};
        DMinimumSubsetSumDifference game = new DMinimumSubsetSumDifference(input);
        System.out.println(game.solveWithTabulation());
    }

    private int solveWithBruteForce() {
        return solve(0, 0, new Result());
    }

    private int solve(int sum, int index, Result result) {
        /* Here we are following the brute force and include the element at a specific index or move on without adding
         * If we are reaching the end of the index then we are taking the total value of the array minus the current sum
         * which will leave us with the remaining subset's sum. so now we can take the difference of both and then return
         * the final value. Once all these values are obtained we are going to take the minimum between the current minimum
         * and the overall minimum and replace if a new minimum is found */
        if (index == this.input.length) {
            return Math.abs(sum - Math.abs(sum - this.total));
        }
        int include = solve(sum + this.input[ index ], index + 1, result);
        int exclude = solve(sum, index + 1, result);
        result.minimum = Math.min(Math.min(include, exclude), result.minimum);
        return result.minimum;
    }

    private int solveWithMemoization() {
        int[][] memo = new int[ this.input.length ][ this.total + 1 ];
        return solve(memo, 0, 0, new Result());
    }

    private int solve(int[][] memo, int sum, int index, Result result) {
        if (index == this.input.length) {
            System.out.println(Math.abs(sum - Math.abs(sum - this.total)));
            return Math.abs(sum - Math.abs(sum - this.total));
        }
        if (memo[ index ][ sum ] == 0) {
            int include = solve(memo, sum + this.input[ index ], index + 1, result);
            int exclude = solve(memo, sum, index + 1, result);
            result.minimum = Math.min(Math.min(include, exclude), result.minimum);
            memo[ index ][ sum ] = result.minimum;
        }
        return memo[ index ][ sum ];
    }

    private int solveWithTabulation() {
        Boolean[][] matrix = new Boolean[ this.input.length + 1 ][ ((this.total) / 2) + 1 ];
        for (int column = 0; column < matrix[ 0 ].length; column++) {
            matrix[ 0 ][ column ] = false;
        }
        for (int row = 0; row < matrix.length; row++) {
            matrix[ row ][ 0 ] = true;
        }
        return solve(matrix);
    }

    private int solve(Boolean[][] matrix) {
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[ 0 ].length; column++) {
                boolean include = (column - this.input[ row - 1 ] >= 0) ? matrix[ row - 1 ][ column - this.input[ row - 1 ] ] : false;
                boolean exclude = matrix[ row - 1 ][ column ];
                matrix[ row ][ column ] = (include || exclude);
            }
        }
        return findMinimumDifference(matrix);
    }

    private int findMinimumDifference(Boolean[][] matrix) {
        int row = matrix.length - 1;
        int column = matrix[ 0 ].length - 1;
        while (!matrix[ row ][ column ]) {
            column--;
        }
        int subsetSumOne = column;
        int subsetSumTwo = this.total - column;
        return Math.abs(subsetSumOne - subsetSumTwo);
    }
}

class Result {
    public int minimum;

    public Result() {
        this.minimum = Integer.MAX_VALUE;
    }
}