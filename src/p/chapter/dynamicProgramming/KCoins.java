package p.chapter.dynamicProgramming;

import java.util.Arrays;

public class KCoins {
    public static void main(String[] args) {
        KCoins game = new KCoins();
        int[] denominations = new int[]{25, 10, 5, 1};
        int knapsackCapacity = 30;
        System.out.print("Denominations: ");
        Arrays.stream(denominations).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(denominations, knapsackCapacity);
        game.solveWithMemoization(denominations, knapsackCapacity);
        game.solveWithTabulation(denominations, knapsackCapacity);
    }

    private void solveWithBruteForce(int[] denominations, int knapsackCapacity) {
        int count = solve(denominations, 0, knapsackCapacity);
        System.out.println("(Brute Force) number of ways the " + knapsackCapacity + " can be achieved: " + count);
    }

    private int solve(int[] denominations, int currentIndex, int currentCapacity) {
        if (currentIndex == denominations.length || currentCapacity < 0) return 0;
        if (currentCapacity == 0) return 1;
        int include = 0;
        if (denominations[ currentIndex ] <= currentCapacity) {
            /* Since the same index can be used infinite times currentIndex is not */
            include = solve(denominations, currentIndex, currentCapacity - denominations[ currentIndex ]);
        }
        int exclude = solve(denominations, currentIndex + 1, currentCapacity);
        return include + exclude;
    }

    private void solveWithMemoization(int[] denominations, int knapsackCapacity) {
        Integer[][] memo = new Integer[ denominations.length ][ knapsackCapacity + 1 ];
        int count = solve(denominations, 0, knapsackCapacity, memo);
        System.out.println("(Memoization) number of ways the " + knapsackCapacity + " can be achieved: " + count);
    }

    private int solve(int[] denominations, int currentIndex, int currentCapacity, Integer[][] memo) {
        if (currentIndex == denominations.length || currentCapacity < 0) return 0;
        if (currentCapacity == 0) return 1;
        if (memo[ currentIndex ][ currentCapacity ] == null) {
            int include = 0;
            if (denominations[ currentIndex ] <= currentCapacity) {
                /* Since the same index can be used infinite times currentIndex is not */
                include = solve(denominations, currentIndex, currentCapacity - denominations[ currentIndex ]);
            }
            int exclude = solve(denominations, currentIndex + 1, currentCapacity);
            memo[ currentIndex ][ currentCapacity ] = include + exclude;
        }
        return memo[ currentIndex ][ currentCapacity ];
    }

    private void solveWithTabulation(int[] denominations, int knapsackCapacity) {
        int[][] matrix = new int[ denominations.length + 1 ][ knapsackCapacity + 1 ];
        for (int i = 0; i < matrix.length; i++) {
            matrix[ i ][ 0 ] = 1;
        }
        int count = solve(denominations, matrix);
        System.out.println("(Tabulation) number of ways the " + knapsackCapacity + " can be achieved: " + count);
    }

    private int solve(int[] denominations, int[][] matrix) {
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[ 0 ].length; column++) {
                int include = 0;
                if (denominations[ row - 1 ] <= column) {
                    include = matrix[ row ][ column - denominations[ row - 1 ] ];
                }
                int exclude = matrix[ row - 1 ][ column ];
                matrix[ row ][ column ] = include + exclude;
            }
        }
        return matrix[ matrix.length - 1 ][ matrix[ 0 ].length - 1 ];
    }
}