package z.reference.grokking.AZeroOneKnapsack;

public class FTargetSum {

    public int[] input;
    public int sum;
    public int totalSum;
    public int[][] matrix;

    public FTargetSum(int[] input, int sum) {
        this.input = input;
        this.sum = sum;
        int total = 0;
        for (int i : input) {
            total += i;
        }
        this.totalSum = total;
    }

    public static void main(String[] args) {
        /*int[] input = {1, 1, 2, 3};
        int sum = 1;*/

        int[] input = {1, 2, 7, 1, 1, 1};
        int sum = 9;
        FTargetSum game = new FTargetSum(input, sum);
        System.out.println("Count of subset with sum " + game.sum + " are: " + game.solveWithTabulation());
    }

    private int solveWithTabulation() {
        /* If sum that we need to find and totalSum is odd then we cannot find
         * the count. Also if the sum we need to find is greater than the total sum */
        if (sum > totalSum || ((sum + totalSum) % 2 == 1)) return 0;
        this.matrix = new int[ this.input.length + 1 ][ ((sum + totalSum) / 2) + 1 ];
        for (int row = 0; row < this.matrix.length; row++) {
            this.matrix[ row ][ 0 ] = 1;
        }
        return solve(((sum + totalSum) / 2));
    }

    private int solve(int targetSum) {
        for (int row = 1; row < this.matrix.length; row++) {
            for (int column = 1; column < this.matrix[ 0 ].length; column++) {
                int include = column - this.input[ row - 1 ] >= 0 ? this.matrix[ row - 1 ][ column - this.input[ row - 1 ] ] : 0;
                int exclude = this.matrix[ row - 1 ][ column ];
                this.matrix[ row ][ column ] = include + exclude;
            }
        }
        return this.matrix[ this.matrix.length - 1 ][ this.matrix[ 0 ].length - 1 ];
    }
}