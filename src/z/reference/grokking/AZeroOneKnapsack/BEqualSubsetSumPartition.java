package z.reference.grokking.AZeroOneKnapsack;

public class BEqualSubsetSumPartition {

    public int[] set;
    public int sum;

    public BEqualSubsetSumPartition(int[] set) {
        this.set = set;
        int sum = 0;
        for (int i : set) {
            sum = sum + i;
        }
        this.sum = sum;
    }

    public static void main(String[] args) {
        int[] set = {1, 2, 3, 4};
        /*int[] set = {2, 3, 4, 6};*/
        BEqualSubsetSumPartition game = new BEqualSubsetSumPartition(set);
        System.out.println("Brute Force Approach: ");
        System.out.println("Can the partition: " + (game.solveWithBruteForce() ? "T" : "F"));
        System.out.println();

        System.out.println("Memoization Approach: ");
        System.out.println("Can the partition: " + (game.solveWithMemoization() ? "T" : "F"));
        System.out.println();

        System.out.println("Dynamic Programming Approach: ");
        System.out.println("Can the partition: " + (game.solveWithDynamicProgramming() ? "T" : "F"));
        System.out.println();
    }

    private boolean solveWithBruteForce() {
        if (!initialSetup()) return false;
        return solve(0, this.sum / 2);
    }

    private boolean solve(int index, int currentSum) {
        if (currentSum == 0) return true;

        if (index >= this.set.length) return false;

        /* INCLUDE the current element if the sum is not exceeding the sum */
        if (this.set[ index ] <= currentSum) {
            if (solve(index + 1, currentSum - this.set[ index ]))
                // We are returning true only if the recursive method passes because
                // there may be a scenario where including the number might return
                // false but upon proceeding with the next value we will be able to
                // arrive at the desired sum.
                return true;
        }

        /* EXCLUDE the current element and proceed with the rest of the array */
        return solve(index + 1, currentSum);
    }

    private boolean solveWithMemoization() {
        if (!initialSetup()) return false;
        Boolean[][] matrix = new Boolean[ this.set.length ][ (this.sum / 2) + 1 ];
        return solve(matrix, this.sum / 2, 0);
    }

    private boolean solve(Boolean[][] matrix, int currentSum, int currentIndex) {
        /* Base Case */
        if (currentSum == 0) return true;

        if (currentIndex >= this.set.length) return false;

        if (matrix[ currentIndex ][ currentSum ] == null) {
            /* INCLUDE the current element if the current element is not exceeding the sum */
            if (this.set[ currentIndex ] <= currentSum) {
                if (solve(matrix, currentSum - this.set[ currentIndex ], currentIndex + 1)) {
                    matrix[ currentIndex ][ currentSum ] = true;
                    return true;
                }
            }
            /* EXCLUDE the current element and proceed with the next index */
            matrix[ currentIndex ][ currentSum ] = solve(matrix, currentSum, currentIndex + 1);
        }
        return matrix[ currentIndex ][ currentSum ];
    }

    private boolean solveWithDynamicProgramming() {
        if (!initialSetup()) return false;
        Boolean[][] matrix = new Boolean[ this.set.length + 1 ][ (this.sum / 2) + 1 ];
        for (int column = 0; column < matrix[ 0 ].length; column++) {
            matrix[ 0 ][ column ] = false;
        }
        for (int row = 0; row < matrix.length; row++) {
            matrix[ row ][ 0 ] = true;
        }
        boolean result = solve(matrix);
        if (result) showMatrix(matrix);
        return result;
    }

    private boolean solve(Boolean[][] matrix) {
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[ 0 ].length; column++) {
                boolean exclude = matrix[ row - 1 ][ column ];
                boolean include = (column - this.set[ row - 1 ] >= 0) ? matrix[ row - 1 ][ column - this.set[ row - 1 ] ] : false;
                matrix[ row ][ column ] = (exclude || include);
            }
        }
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
            System.out.print(row + "   ");
            for (int column = 0; column < matrix[ 0 ].length; column++) {
                System.out.print((matrix[ row ][ column ] ? "T" : "F") + "   ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean initialSetup() {
        if (this.set.length <= 0) return false;
        /* IMPORTANT: cannot process a odd sum array. If we don't return here inside the solve method there will be a scenario where the condition of
        sum = 0 will be satisfied and true will be returned even though the actual result has to be false */
        if (this.sum % 2 != 0) {
            System.out.println("Cannot process the array having odd sum. Cannot partition odd sum of digits into two equal int");
            return false;
        }
        return true;
    }
}