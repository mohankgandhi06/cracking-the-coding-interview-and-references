package p.chapter.dynamicProgramming;

public class AThreeSteps {

    private static int counter = 0;

    public static void main(String[] args) {
        int step = 6;
        hop(0, step);
        System.out.println("Steps: " + step);
        System.out.println("Recursion: Total Possible Ways: " + counter);
        counter = 0;
        hopBruteForce(step);
        System.out.println("Brute Force: Total Possible Ways: " + counter);
        counter = 0;
        int[] memo = new int[ step + 1 ];
        memo[ 0 ] = 1;
        counter = hopMemoization(step, memo);
        System.out.println("Memoization: Total Possible Ways: " + counter);
        counter = 0;
        counter = hopTabulation(step);
        System.out.println("Tabulation: Total Possible Ways: " + counter);
    }

    private static void hop(int n, int target) {
        if (n > target) return;
        if (n == target) {
            counter++;
            return;
        }
        if (n != target) {
            hop(n + 1, target);
            hop(n + 2, target);
            hop(n + 3, target);
        }
    }

    private static int hopBruteForce(int stepsRemaining) {
        if (stepsRemaining == 0) return 1;
        if (stepsRemaining == 1) return 1;
        if (stepsRemaining == 2) return 2;
        counter = hopBruteForce(stepsRemaining - 1) + hopBruteForce(stepsRemaining - 2) + hopBruteForce(stepsRemaining - 3);
        return counter;
    }

    private static int hopMemoization(int stepsRemaining, int[] memo) {
        if (stepsRemaining == 0) return 1;
        if (stepsRemaining == 1) return 1;
        if (stepsRemaining == 2) return 2;
        if (memo[ stepsRemaining ] == 0) {
            memo[ stepsRemaining ] = hopBruteForce(stepsRemaining - 1) + hopBruteForce(stepsRemaining - 2) + hopBruteForce(stepsRemaining - 3);
        }
        return memo[ stepsRemaining ];
    }

    private static int hopTabulation(int step) {
        int[] matrix = new int[ step + 1 ];
        matrix[ 0 ] = 1;
        matrix[ 1 ] = 1;
        matrix[ 2 ] = 2;
        for (int i = 3; i < matrix.length; i++) {
            matrix[ i ] = matrix[ i - 1 ] + matrix[ i - 2 ] + matrix[ i - 3 ];
        }
        return matrix[ matrix.length - 1 ];
    }
}