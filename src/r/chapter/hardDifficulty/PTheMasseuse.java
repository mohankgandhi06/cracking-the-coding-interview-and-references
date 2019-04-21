package r.chapter.hardDifficulty;

import java.util.Arrays;

public class PTheMasseuse {
    public static void main(String[] args) {
        PTheMasseuse game = new PTheMasseuse();
        int[] input = new int[]{
                30, 15, 90, 75, 15, 30, 75, 90
        };
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);

        input = new int[]{
                30, 15, 60, 75, 45, 15, 15, 45
        };
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);

        input = new int[]{
                30, 15, 90, 75, 45, 15, 15, 45
        };
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);


        input = new int[]{
                30, 15, 60, 75, 45, 15, 60, 45
        };
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);


        input = new int[]{
                30, 15, 60, 75, 90, 90, 15, 45
        };
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);


        input = new int[]{
                30, 75, 60, 75, 45, 15, 15, 45
        };
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.solveWithBruteForce(input);
        game.solveWithMemoization(input);
    }

    private int solveWithMemoization(int[] input) {
        int total = 0;
        for (int i : input) {
            total += i;
        }
        Integer[][] memo = new Integer[ input.length + 1 ][ total ];
        int maxHonor = solve(input, 0, 0, memo);
        System.out.println("Memoization - Time that masseuse can honor: " + maxHonor);
        return -1;
    }

    private int solve(int[] input, int currentIndex, int currentSum, Integer[][] memo) {
        if (currentIndex > input.length - 1) return currentSum;
        if (memo[ currentIndex ][ currentSum ] == null) {
            int include = solve(input, currentIndex + 2, currentSum + input[ currentIndex ], memo);
            int exclude = solve(input, currentIndex + 1, currentSum, memo);
            int result = Math.max(include, exclude);
            memo[ currentIndex ][ currentSum ] = result;
        }
        return memo[ currentIndex ][ currentSum ];
    }

    private int solveWithBruteForce(int[] input) {
        int maxHonor = solve(input, 0, 0);
        System.out.println("Brute Force - Time that masseuse can honor: " + maxHonor);
        return -1;
    }

    private int solve(int[] input, int currentIndex, int currentSum) {
        if (currentIndex > input.length - 1) return currentSum;
        int include = solve(input, currentIndex + 2, currentSum + input[ currentIndex ]);
        int exclude = solve(input, currentIndex + 1, currentSum);
        return Math.max(include, exclude);
    }
}