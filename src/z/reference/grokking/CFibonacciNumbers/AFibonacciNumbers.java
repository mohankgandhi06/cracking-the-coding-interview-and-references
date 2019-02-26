package z.reference.grokking.CFibonacciNumbers;

public class AFibonacciNumbers {

    public int nth;

    public AFibonacciNumbers(int nth) {
        this.nth = nth;
    }

    public static void main(String[] args) {
        int nth = 4;
        AFibonacciNumbers game = new AFibonacciNumbers(nth);
        System.out.println("Fibonacci with Brute Force: " + game.nth + " (st/nd/rd/th) Fibonacci Number is: " + game.solveWithBruteForce());
        System.out.println("Fibonacci with Memoization: " + game.nth + " (st/nd/rd/th) Fibonacci Number is: " + game.solveWithMemoization());
        System.out.println("Fibonacci with Tabulation: " + game.nth + " (st/nd/rd/th) Fibonacci Number is: " + game.solveWithTabulation());
    }

    private int solveWithBruteForce() {
        if (this.nth < 0) return -1;
        if (this.nth < 2 && this.nth >= 0) return this.nth;
        return solve(this.nth);
    }

    private int solve(int nth) {
        if (nth == 0) return 0;
        if (nth == 1) return 1;
        return solve(nth - 1) + solve(nth - 2);
    }

    private int solveWithMemoization() {
        System.out.println();
        if (this.nth < 0) return -1;
        if (this.nth < 2 && this.nth >= 0) return this.nth;
        Integer[] memo = new Integer[ this.nth + 1 ];
        memo[ 0 ] = 0;
        memo[ 1 ] = 1;

        int result = solve(memo, nth);
        System.out.println("Memo Table is as Follows: ");
        for (int i : memo) {
            System.out.print(i + " ");
        }
        System.out.println();
        return result;
    }

    private int solve(Integer[] memo, int nth) {
        if (memo[ nth ] == null) {
            memo[ nth ] = solve(memo, nth - 1) + solve(memo, nth - 2);
        }
        return memo[ nth ];
    }

    private int solveWithTabulation() {
        System.out.println();
        /*int[] matrix = new int[ this.nth + 1 ];
        matrix[ 0 ] = 0;
        matrix[ 1 ] = 1;

        int result = solve(matrix);
        System.out.println("Matrix Table is as Follows: ");
        for (int i : matrix) {
            System.out.print(i + " ");
        }
        System.out.println();*/
        if (this.nth < 0) return -1;
        if (this.nth < 2 && this.nth >= 0) return this.nth;
        int result = solveOptimized(0, 1, this.nth);
        return result;
    }

    private int solve(int[] matrix) {
        for (int i = 2; i < matrix.length; i++) {
            matrix[ i ] = matrix[ i - 1 ] + matrix[ i - 2 ];
        }
        return matrix[ matrix.length - 1 ];
    }

    private int solveOptimized(int nMinusOne, int nMinusTwo, int nth) {
        int temp = 0;
        for (int i = 2; i <= nth; i++) {
            temp = nMinusOne + nMinusTwo;
            nMinusOne = nMinusTwo;
            nMinusTwo = temp;
        }
        return temp;
    }
}