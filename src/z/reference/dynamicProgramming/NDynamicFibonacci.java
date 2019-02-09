package z.reference.dynamicProgramming;

public class NDynamicFibonacci {

    public int[] memoizedFibonacci;

    public NDynamicFibonacci(int number) {
        this.memoizedFibonacci = new int[ number + 1 ];
    }

    public static void main(String[] args) {
        /* Top Down Approach - Recursive */
        /*System.out.println(fibonacciWithRecursion(4));*/

        NDynamicFibonacci game = new NDynamicFibonacci(10);

        /* Top Down Approach - Recursive with Memoization */
        initializeMemoization(game);

        /* Showing Initialized Array */
        for (int n : game.memoizedFibonacci) {
            System.out.print(" " + n);
        }
        System.out.println();

        /* Fibonacci of the Nth number */
        System.out.println(game.fibonacciWithTopDownRecursive(10));

        /* Showing Memoized Table */
        for (int n : game.memoizedFibonacci) {
            System.out.print(" " + n);
        }

        /* Bottom Up Approach - Iterative */
        /*System.out.println(game.fibonacciWithBottomUpIterative(10));

        for (int n : game.memoizedFibonacci) {
            System.out.print(" " + n);
        }*/

    }

    private static int fibonacciWithRecursion(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciWithRecursion(n - 1) + fibonacciWithRecursion(n - 2);
    }

    private int fibonacciWithTopDownRecursive(int n) {
        if (this.memoizedFibonacci[ n ] >= 0) return this.memoizedFibonacci[ n ];
        return this.memoizedFibonacci[ n ] = fibonacciWithTopDownRecursive(n - 1) + fibonacciWithTopDownRecursive(n - 2);
    }

    private int fibonacciWithBottomUpIterative(int n) {
        memoizedFibonacci[ 0 ] = 0;
        memoizedFibonacci[ 1 ] = 1;
        for (int b = 2; b <= n; b++) {
            memoizedFibonacci[ b ] = memoizedFibonacci[ b - 1 ] + memoizedFibonacci[ b - 2 ];
        }
        return memoizedFibonacci[ n ];
    }

    private static void initializeMemoization(NDynamicFibonacci obj) {
        //intializing the array with -1 before the starting
        for (int i = 2; i < obj.memoizedFibonacci.length; i++) {
            obj.memoizedFibonacci[ i ] = -1;
        }
        obj.memoizedFibonacci[ 1 ] = 1;
    }
}