package z.reference.grokking.CFibonacciNumbers;

public class BStaircase {
    public static void main(String[] args) {
        int totalStairs = 3;
        BStaircase game = new BStaircase();
        System.out.println("Number of ways to reach the top:");
        System.out.println("(Brute Force) " + game.solveWithBruteForce(totalStairs));
        System.out.println();
        System.out.println("(Memoization) " + game.solveWithMemoization(totalStairs));
        System.out.println();
        System.out.println("(Tabulation) " + game.solveWithTabulation(totalStairs));
    }

    private int solveWithBruteForce(int stairsRemaining) {
        if (stairsRemaining == 0)
            return 1;//If the number of steps remaining is 0 it means we have reached the top so 1;
        if (stairsRemaining == 1)
            return 1;//If the number of steps remaining is 1 it means we can reach the top in only one way i.e. to take one step;
        if (stairsRemaining == 2)
            return 2;//If the number of steps remaining is 2 it means we can reach the top in only one way i.e. to take one step twice or take two step once;
        return solveWithBruteForce(stairsRemaining - 1) + solveWithBruteForce(stairsRemaining - 2) + solveWithBruteForce(stairsRemaining - 3);
    }

    private int solveWithMemoization(int totalStairs) {
        Integer[] memo = new Integer[ totalStairs + 1 ];
        memo[ 0 ] = 1;
        memo[ 1 ] = 1;
        memo[ 2 ] = 2;
        return solve(totalStairs, memo);
    }

    private int solve(int stairsRemaining, Integer[] memo) {
        if (memo[ stairsRemaining ] == null) {
            memo[ stairsRemaining ] = solve(stairsRemaining - 1, memo) + solve(stairsRemaining - 2, memo) + solve(stairsRemaining - 3, memo);
        }
        return memo[ stairsRemaining ];
    }

    private int solveWithTabulation(int totalStairs) {
        /* We can even optimize the matrix by  using temporary int and the three previous integer in the process of swapping */
        int[] matrix = new int[ totalStairs + 1 ];
        matrix[ 0 ] = 1;
        matrix[ 1 ] = 1;
        matrix[ 2 ] = 2;
        return solve(totalStairs, matrix);
    }

    private int solve(int totalStairs, int[] matrix) {
        for (int i = 3; i < matrix.length; i++) {
            matrix[ i ] = matrix[ i - 1 ] + matrix[ i - 2 ] + matrix[ i - 3 ];
        }
        return matrix[ totalStairs ];
    }
}