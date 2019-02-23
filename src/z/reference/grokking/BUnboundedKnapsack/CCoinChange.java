package z.reference.grokking.BUnboundedKnapsack;

public class CCoinChange {

    public int[] denominations;
    public int sum;

    public CCoinChange(int[] denominations, int sum) {
        this.denominations = denominations;
        this.sum = sum;
    }

    public static void main(String[] args) {
        int[] denominations = {1, 2, 3};
        int sum = 5;

        /*int[] denominations = {1, 2, 3};
        int sum = 4;*/

        /*int[] denominations = {8, 3, 1, 2};
        int sum = 3;*/

        /*int[] denominations = {2, 5, 3, 6};
        int sum = 10;*/

        /*int[] denominations = {9, 6, 5, 1};
        int sum = 11;*/

        CCoinChange game = new CCoinChange(denominations, sum);
        System.out.print("Number of ways the sum: " + game.sum + "\n can be achieved using the denominations: " + game.printArray() + "\n is as follows: \n");
        game.solveWithBruteForce();
        game.solveWithMemoization();
        game.solveWithTabulation();
    }

    private void solveWithBruteForce() {
        System.out.print("Brute Force: ");
        System.out.println(solve(0, this.sum));
    }

    private int solve(int currentIndex, int currentSum) {
        if (currentIndex >= this.denominations.length) return 0;
        if (currentSum < 0) return 0;
        if (currentSum == 0) return 1;
        int include = 0;
        if (this.denominations[ currentIndex ] <= currentSum) {
            include = solve(currentIndex, currentSum - this.denominations[ currentIndex ]);
        }
        int exclude = solve(currentIndex + 1, currentSum);
        return include + exclude;
    }

    private void solveWithMemoization() {
        System.out.print("Memoization: ");
        Integer[][] memo = new Integer[ this.denominations.length ][ this.sum + 1 ];
        System.out.println(solve(0, this.sum, memo));
        System.out.println();
        System.out.println("Memo is as follows: ");
        for (int row = 0; row < memo.length; row++) {
            for (int column = 0; column < memo[ 0 ].length; column++) {
                System.out.print((memo[ row ][ column ] == null ? 0 : memo[ row ][ column ]) + " ");
            }
            System.out.println();
        }
    }

    private int solve(int currentIndex, int currentSum, Integer[][] memo) {
        if (currentIndex >= this.denominations.length) return 0;
        if (currentSum < 0) return 0;
        if (currentSum == 0) return 1;
        if (memo[ currentIndex ][ currentSum ] == null) {
            int include = solve(currentIndex, currentSum - this.denominations[ currentIndex ], memo);
            int exclude = solve(currentIndex + 1, currentSum, memo);
            memo[ currentIndex ][ currentSum ] = include + exclude;
        }
        return memo[ currentIndex ][ currentSum ];
    }

    private void solveWithTabulation() {
        System.out.print("Tabulation: ");
        int[][] matrix = new int[ this.denominations.length + 1 ][ this.sum + 1 ];
        for (int row = 0; row < matrix.length; row++) {
            matrix[ row ][ 0 ] = 1;
        }
        System.out.println(solve(matrix));
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
                int include = (column - this.denominations[ row - 1 ] >= 0 ? matrix[ row ][ column - this.denominations[ row - 1 ] ] : 0);
                int exclude = matrix[ row - 1 ][ column ];
                matrix[ row ][ column ] = include + exclude;
            }
        }
        return matrix[ matrix.length - 1 ][ matrix[ 0 ].length - 1 ];
    }

    private String printArray() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : this.denominations) {
            stringBuilder.append(i + " ");
        }
        return stringBuilder.toString();
    }
}