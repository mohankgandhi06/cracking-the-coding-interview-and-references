package z.reference.grokking.BUnboundedKnapsack;

public class BRodCutting {

    public int[] lengths;
    public int[] prices;
    public int size;

    public BRodCutting(int[] lengths, int[] prices, int size) {
        this.lengths = lengths;
        this.prices = prices;
        this.size = size;
    }

    public static void main(String[] args) {
        /*int[] lengths = {1, 3, 2, 5, 4};
        int[] prices = {2, 7, 6, 13, 10};
        int size = 5;*/

        int[] lengths = {1, 2, 3, 4, 5};
        int[] prices = {2, 6, 7, 10, 13};
        int size = 5;
        BRodCutting game = new BRodCutting(lengths, prices, size);
        /*game.solveWithBruteForce();*/
        /*game.solveWithMemoization();*/
        game.solveWithTabulation();
    }

    private void solveWithBruteForce() {
        System.out.println("Maximum Profit which can be earned: " + solve(0, this.size));
    }

    private int solve(int currentIndex, int currentSize) {
        if (currentIndex >= this.prices.length) return 0;
        if (currentSize < 0) return 0;
        int include = 0;
        if (currentSize - this.lengths[ currentIndex ] >= 0) {
            include = this.prices[ currentIndex ] + solve(currentIndex, currentSize - this.lengths[ currentIndex ]);
        }
        int exclude = solve(currentIndex + 1, currentSize);
        return Math.max(include, exclude);
    }

    private void solveWithMemoization() {
        Integer[][] memo = new Integer[ this.lengths.length ][ this.size + 1 ];
        System.out.println("Maximum Profit which can be earned: " + solve(0, this.size, memo));
        System.out.println("Memo Table is as follows: ");
        for (int row = 0; row < memo.length; row++) {
            for (int column = 0; column < memo[ 0 ].length; column++) {
                System.out.print(memo[ row ][ column ] + " ");
            }
            System.out.println();
        }
    }

    private int solve(int currentIndex, int currentSize, Integer[][] memo) {
        if (currentIndex >= this.prices.length) return 0;
        if (currentSize < 0) return 0;
        if (memo[ currentIndex ][ currentSize ] == null) {
            int include = 0;
            if (this.lengths[ currentIndex ] <= currentSize) {
                include = this.prices[ currentIndex ] + solve(currentIndex, currentSize - this.lengths[ currentIndex ], memo);
            }
            int exclude = solve(currentIndex + 1, currentSize, memo);
            memo[ currentIndex ][ currentSize ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ currentSize ];
    }

    private void solveWithTabulation() {
        int[][] matrix = new int[ this.lengths.length + 1 ][ this.size + 1 ];
        System.out.println("Maximum Profit which can be earned: " + solve(matrix));
        System.out.println();
        System.out.println("The Matrix is as follows");
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[ 0 ].length; column++) {
                System.out.print(matrix[ row ][ column ] + " ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Choices which have been taken to reach this profit is as follows: ");
        showChoices(matrix);
    }

    private int solve(int[][] matrix) {
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[ 0 ].length; column++) {
                int include = 0;
                if (this.lengths[ row - 1 ] <= column) {
                    include = this.prices[ row - 1 ] + (column - this.lengths[ row - 1 ] >= 0 ? matrix[ row ][ column - this.lengths[ row - 1 ] ] : 0);
                }
                int exclude = matrix[ row - 1 ][ column ];
                matrix[ row ][ column ] = Math.max(include, exclude);
            }
        }
        return matrix[ matrix.length - 1 ][ matrix[ 0 ].length - 1 ];
    }

    private void showChoices(int[][] matrix) {
        int row = matrix.length - 1;
        int column = matrix[ 0 ].length - 1;
        while (matrix[ row ][ column ] != 0) {
            if (matrix[ row ][ column ] != matrix[ row - 1 ][ column ]) {
                column = column - this.lengths[ row - 1 ];
                System.out.print(this.lengths[ row - 1 ] + " ");
            } else {
                row--;
            }
        }
    }
}