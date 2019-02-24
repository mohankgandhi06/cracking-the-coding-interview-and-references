package z.reference.grokking.BUnboundedKnapsack;

public class DMinimumCoinChange {

    public int[] denominations;
    public int sum;

    public DMinimumCoinChange(int[] denominations, int sum) {
        this.denominations = denominations;
        this.sum = sum;
    }

    public static void main(String[] args) {
        int[] denominations = {1, 2, 3, 8, 5};
        int sum = 5;

        /*int[] denominations = {3, 2, 1};
        int sum = 11;*/

        /*int[] denominations = {1, 2, 3};
        int sum = 11;*/

        DMinimumCoinChange game = new DMinimumCoinChange(denominations, sum);
        game.solveWithBruteForce();
        game.solveWithMemoization();
        game.solveWithTabulation();
    }

    private void solveWithBruteForce() {
        System.out.println("Brute Force Method: ");
        System.out.println("Minimum count of changes required to make a sum of " + this.sum + " is: " + solve(0, this.sum, 0, Integer.MAX_VALUE));
    }

    private int solve(int currentIndex, int currentSum, int currentCount, int minimumChange) {
        if (currentIndex >= this.denominations.length) return minimumChange;
        if (currentSum < 0) return minimumChange;
        if (currentSum == 0) return Math.min(currentCount, minimumChange);
        int include = Integer.MAX_VALUE;
        if (this.denominations[ currentIndex ] <= currentSum) {
            include = solve(currentIndex, currentSum - this.denominations[ currentIndex ], currentCount + 1, minimumChange);
        }
        int exclude = solve(currentIndex + 1, currentSum, currentCount, minimumChange);
        return Math.min(include, exclude);
    }

    private void solveWithMemoization() {
        Integer[][] memo = new Integer[ this.denominations.length ][ this.sum + 1 ];
        System.out.println("Memoization Method: ");
        System.out.println("Minimum count of changes required to make a sum of " + this.sum + " is: " + solve(0, this.sum, 0, Integer.MAX_VALUE, memo));

        for (int row = 0; row < memo.length; row++) {
            for (int column = 0; column < memo[ 0 ].length; column++) {
                System.out.print(memo[ row ][ column ] + " ");
            }
            System.out.println();
        }
    }

    private int solve(int currentIndex, int currentSum, int currentCount, int minimumChange, Integer[][] memo) {
        if (currentIndex >= this.denominations.length) return minimumChange;
        if (currentSum < 0) return minimumChange;
        if (currentSum == 0) return Math.min(currentCount, minimumChange);
        if (memo[ currentIndex ][ currentSum ] == null || currentCount < minimumChange) {
            //IMPORTANT STEP: Here the currentCount < minimumChange is checked because there can be a minimum than the currently established minimum in the array
            int include = Integer.MAX_VALUE;
            if (this.denominations[ currentIndex ] <= currentSum) {
                include = solve(currentIndex, currentSum - this.denominations[ currentIndex ], currentCount + 1, minimumChange, memo);
            }
            int exclude = solve(currentIndex + 1, currentSum, currentCount, minimumChange, memo);
            memo[ currentIndex ][ currentSum ] = Math.min(include, exclude);
        }
        return memo[ currentIndex ][ currentSum ];
    }

    private void solveWithTabulation() {
        int[][] matrix = new int[ this.denominations.length + 1 ][ this.sum + 1 ];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[ 0 ].length; column++) {
                matrix[ row ][ column ] = Integer.MAX_VALUE;
            }
        }
        /* IMPORTANT STEP: If we don't make the first row as 0 then when are including a number and move that many columns we will initially
         * end up in the first column and if this value being 0, we will correctly get the count as 1+0 and then from there onwards we will be
         * considering only the other integers until we reach the next row and the same scenario repeats */
        for (int row = 0; row < matrix.length; row++) {
            matrix[ row ][ 0 ] = 0;
        }
        solve(matrix);
        System.out.println();
        System.out.println("Matrix is as follows: ");
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[ 0 ].length; column++) {
                System.out.print(matrix[ row ][ column ] + " ");
            }
            System.out.println();
        }
    }

    private int solve(int[][] matrix) {
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[ 0 ].length; column++) {
                /* IMPORTANT STEP: since we are populating all the array as Integer.MAX_VALUE when we are including the value we need
                 * to first validate if the index is not out of bounds and then the value is not equal to Integer.MAX_VALUE. otherwise
                 * we will make the integer in -MAX_VALUE which will become the most minimum value ever in the matrix
                 * IMPORTANT: Here also we are checking the previous will lead to a valid count of the variable and not Integer.MAX_VALUE
                 * which means there is no possible combination of the previous value and so there will not be a combination possible now */
                int include = (column - this.denominations[ row - 1 ] >= 0
                        && matrix[ row ][ column - this.denominations[ row - 1 ] ] != Integer.MAX_VALUE)
                        ? (matrix[ row ][ column - this.denominations[ row - 1 ] ] + 1)
                        : Integer.MAX_VALUE;
                int exclude = matrix[ row - 1 ][ column ];
                matrix[ row ][ column ] = Math.min(include, exclude);
            }
        }
        return matrix[ matrix.length - 1 ][ matrix[ 0 ].length - 1 ];
    }
}