package z.reference.grokking.CFibonacciNumbers;

public class FHouseThief {

    public static void main(String[] args) {
        int[] wealth = {2, 5, 1, 3, 6, 2, 4};
        FHouseThief game = new FHouseThief();
        System.out.println("Brute Force: " + game.solveWithBruteForce(wealth));
        System.out.println("Memoization: " + game.solveWithMemoization(wealth));
        System.out.println("Tabulation: " + game.solveWithTabulation(wealth));
        System.out.println();
        wealth = new int[]{2, 10, 14, 8, 1};
        System.out.println("Brute Force: " + game.solveWithBruteForce(wealth));
        System.out.println("Memoization: " + game.solveWithMemoization(wealth));
        System.out.println("Tabulation: " + game.solveWithTabulation(wealth));
    }

    private int solveWithBruteForce(int[] wealth) {
        return solve(wealth, 0);
    }

    private int solve(int[] wealth, int currentIndex) {
        if (currentIndex >= wealth.length) return 0;
        int include = wealth[ currentIndex ] + solve(wealth, currentIndex + 2);
        int exclude = solve(wealth, currentIndex + 1);
        return Math.max(include, exclude);
    }

    private int solveWithMemoization(int[] wealth) {
        Integer[] memo = new Integer[ wealth.length ];
        return solve(wealth, 0, memo);
    }

    private int solve(int[] wealth, int currentIndex, Integer[] memo) {
        if (currentIndex >= wealth.length) return 0;
        if (memo[ currentIndex ] != null) return memo[ currentIndex ];
        int include = wealth[ currentIndex ] + solve(wealth, currentIndex + 2, memo);
        int exclude = solve(wealth, currentIndex + 1, memo);
        return memo[ currentIndex ] = Math.max(include, exclude);
    }

    private int solveWithTabulation(int[] wealth) {
        int[] matrix = new int[ wealth.length + 1 ]; // '+1' to handle the zero house
        matrix[ 0 ] = 0; // if there are no houses, the thief can't steal anything
        matrix[ 1 ] = wealth[ 0 ]; // only one house, so the thief have to steal from it
        for (int i = 2; i <= wealth.length; i++) {
            int include = wealth[ i - 1 ] + matrix[ i - 2 ];// wealth is taken i-1 since the wealth the index location will be from 0 to n
            int exclude = matrix[ i - 1 ];
            matrix[ i ] = Math.max(include, exclude);
        }
        /*for (int i : matrix) {
            System.out.print(i + " ");
        }*/
        return matrix[ wealth.length ];
    }
}