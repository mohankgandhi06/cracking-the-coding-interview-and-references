package z.reference.grokking.CFibonacciNumbers;

public class CNumberFactors {

    public int number;

    public CNumberFactors(int number) {
        this.number = number;
    }

    public static void main(String[] args) {
        /*int number = 4;*/
        int number = 5;
        CNumberFactors game = new CNumberFactors(number);
        System.out.println("Number of ways to express the number: " + game.number + " as a factor of 1,3,4 is: ");
        game.solveWithBruteForce();
        game.solveWithMemoization();
        game.solveWithTabulation();
    }

    private void solveWithBruteForce() {
        System.out.print("Brute Force: ");
        System.out.print(solve(this.number));
        System.out.println();
    }

    private int solve(int remaining) {
        if (remaining < 0) return 0;
        if (remaining == 0) return 1;
        if (remaining == 1) return 1;
        if (remaining == 3) return 2;
        return solve(remaining - 1) + solve(remaining - 3) + solve(remaining - 4);
    }

    private void solveWithMemoization() {
        Integer[] memo = new Integer[ this.number + 1 ];
        memo[ 0 ] = 1;
        memo[ 1 ] = 1;
        memo[ 3 ] = 2;
        System.out.print("Memoization Force: ");
        System.out.print(solve(this.number, memo));
        System.out.println();
    }

    private int solve(int remaining, Integer[] memo) {
        if (remaining < 0) return 0;
        if (memo[ remaining ] == null) {
            memo[ remaining ] = solve(remaining - 1, memo) + solve(remaining - 3, memo) + solve(remaining - 4, memo);
        }
        return memo[ remaining ];
    }

    private void solveWithTabulation() {
        int[] matrix = new int[ this.number + 1 ];
        matrix[ 0 ] = 1;
        matrix[ 1 ] = 1;
        matrix[ 3 ] = 2;
        System.out.print("Tabulation Force: ");
        System.out.print(solve(this.number, matrix));
        System.out.println();
    }

    private int solve(int number, int[] matrix) {
        for (int i = 2; i < matrix.length; i++) {
            if (matrix[ i ] != 0) continue;
            matrix[ i ] = (i - 1 >= 0 ? matrix[ i - 1 ] : 0) + (i - 3 >= 0 ? matrix[ i - 3 ] : 0) + (i - 4 >= 0 ? matrix[ i - 4 ] : 0);
        }
        return matrix[ number ];
    }
}