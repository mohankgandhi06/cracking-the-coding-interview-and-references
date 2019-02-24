package z.reference.grokking.BUnboundedKnapsack;

public class EMaximumRibbonCut {

    public int size;
    public int[] pieces;

    public EMaximumRibbonCut(int size, int[] pieces) {
        this.size = size;
        this.pieces = pieces;
    }

    public static void main(String[] args) {
        /*int size = 5;
        int[] pieces = {2, 3, 5};*/

        /*int size = 7;
        int[] pieces = {2, 3};*/

        int size = 13;
        /*int[] pieces = {3, 5, 7};*/
        int[] pieces = {3, 5, 7, 1};

        EMaximumRibbonCut game = new EMaximumRibbonCut(size, pieces);
        game.solveWithBruteForce();
        game.solveWithMemoization();
        game.solveWithTabulation();
    }

    private void solveWithBruteForce() {
        System.out.println("Maximum ribbon pieces which can be made out of the ribbon of total size " + this.size + " is: " + solve(0, this.size, 0, 0));
    }

    private int solve(int currentIndex, int currentSize, int currentMaximum, int overallMaximum) {
        if (currentIndex >= this.pieces.length) return overallMaximum;
        if (currentSize < 0) return overallMaximum;
        if (currentSize == 0) return Math.max(currentMaximum, overallMaximum);
        int include = 0;
        if (this.pieces[ currentIndex ] <= currentSize) {
            include = solve(currentIndex, currentSize - this.pieces[ currentIndex ], currentMaximum + 1, overallMaximum);
        }
        int exclude = solve(currentIndex + 1, currentSize, currentMaximum, overallMaximum);
        return Math.max(include, exclude);
    }

    private void solveWithMemoization() {
        System.out.println();
        Integer[][] memo = new Integer[ this.pieces.length ][ this.size + 1 ];
        System.out.println("Maximum ribbon pieces which can be made out of the ribbon of total size " + this.size + " is: " + solve(0, this.size, 0, 0, memo));
        System.out.println("Memo table is as follows: ");
        for (int row = 0; row < memo.length; row++) {
            for (int column = 0; column < memo[ 0 ].length; column++) {
                System.out.print(memo[ row ][ column ] + " ");
            }
            System.out.println();
        }
    }

    private int solve(int currentIndex, int currentSize, int currentMaximum, int overallMaximum, Integer[][] memo) {
        if (currentIndex >= this.pieces.length) return overallMaximum;
        if (currentSize < 0) return overallMaximum;
        if (currentSize == 0) return Math.max(currentMaximum, overallMaximum);
        if (memo[ currentIndex ][ currentSize ] == null || currentMaximum > overallMaximum) {
            int include = 0;
            if (this.pieces[ currentIndex ] <= currentSize) {
                include = solve(currentIndex, currentSize - this.pieces[ currentIndex ], currentMaximum + 1, overallMaximum, memo);
            }
            int exclude = solve(currentIndex + 1, currentSize, currentMaximum, overallMaximum, memo);
            memo[ currentIndex ][ currentSize ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ currentSize ];
    }

    private void solveWithTabulation() {
        System.out.println();
        int[][] matrix = new int[ this.pieces.length + 1 ][ this.size + 1 ];

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[ 0 ].length; column++) {
                matrix[ row ][ column ] = Integer.MIN_VALUE;
            }
        }

        for (int row=0; row<matrix.length;row++){
            matrix[row][0] = 0;
        }

        System.out.println("Maximum ribbon pieces which can be made out of the ribbon of total size " + this.size + " is: " + solve(matrix));
        System.out.println("Matrix table is as follows: ");
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
                int include = (column - this.pieces[ row - 1 ] >= 0
                        && matrix[ row ][ column - this.pieces[ row - 1 ] ] != Integer.MIN_VALUE)
                        ? (1+ matrix[ row ][ column - this.pieces[ row - 1 ] ])
                        : Integer.MIN_VALUE;
                int exclude = matrix[ row - 1 ][ column ];
                matrix[ row ][ column ] = Math.max(include, exclude);
            }
        }
        return matrix[ matrix.length - 1 ][ matrix[ 0 ].length - 1 ];
    }
}