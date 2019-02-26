package z.reference.grokking.CFibonacciNumbers;

public class EMinimumJumpsWithFee {

    public int[] fees;

    public EMinimumJumpsWithFee(int[] fees) {
        this.fees = fees;
    }

    public static void main(String[] args) {
        int[] fees = {1, 2, 5, 2, 1, 2};

        /*fees = new int[]{2, 3, 4, 5};*/
        EMinimumJumpsWithFee game = new EMinimumJumpsWithFee(fees);
        game.solveWithBruteForce();
        game.solveWithMemoization();
        game.solveWithTabulation();
    }

    private void solveWithBruteForce() {
        System.out.println("Brute Force: ");
        System.out.println("Minimum Fees to be paid: " + solve(0));
    }

    private int solve(int currentIndex) {
        if (currentIndex >= this.fees.length) return 0;
        int feeForOneStep = solve(currentIndex + 1);
        int feeForTwoSteps = solve(currentIndex + 2);
        int feeForThreeSteps = solve(currentIndex + 3);
        /* Uncomment and learn more about how the recursion occurs */
        /*System.out.println("currentIndex: " + currentIndex + ", feeForOneStep: " + feeForOneStep + ", feeForTwoSteps: " + feeForTwoSteps + ", feeForThreeSteps: " + feeForThreeSteps);*/
        int minFee = Math.min(Math.min(feeForOneStep, feeForTwoSteps), feeForThreeSteps);
        return minFee + fees[ currentIndex ];
    }

    private void solveWithMemoization() {
        System.out.println("Memoization: ");
        Integer[] memo = new Integer[ this.fees.length ];
        System.out.println("Minimum Fees to be paid: " + solve(0, memo));
    }

    private int solve(int currentIndex, Integer[] memo) {
        if (currentIndex >= this.fees.length) return 0;
        if (memo[ currentIndex ] != null) return memo[ currentIndex ];
        int feeForOneStep = solve(currentIndex + 1, memo);
        int feeForTwoSteps = solve(currentIndex + 2, memo);
        int feeForThreeSteps = solve(currentIndex + 3, memo);
        int minFee = Math.min(Math.min(feeForOneStep, feeForTwoSteps), feeForThreeSteps);
        /*System.out.println("currentIndex: " + currentIndex + ", feeForOneStep: " + feeForOneStep + ", feeForTwoSteps: " + feeForTwoSteps + ", feeForThreeSteps: " + feeForThreeSteps);*/
        memo[ currentIndex ] = minFee + fees[ currentIndex ];
        return memo[ currentIndex ];
    }

    private void solveWithTabulation() {
        System.out.println("Tabulation: ");
        int[] matrix = new int[ this.fees.length + 1 ];
        matrix[ 0 ] = 0;
        matrix[ 1 ] = this.fees[ 0 ];
        matrix[ 2 ] = this.fees[ 0 ];
        matrix[ 3 ] = this.fees[ 0 ];
        System.out.println("Minimum Fees to be paid: " + solve(matrix));
    }

    private int solve(int[] matrix) {
        for (int i = 3; i < this.fees.length; i++) {
            int feeForOneStep = matrix[ i - 2 ] + this.fees[ i - 2 ];
            int feeForTwoSteps = matrix[ i - 1 ] + this.fees[ i - 1 ];
            int feeForThreeSteps = matrix[ i ] + this.fees[ i ];
            matrix[ i + 1 ] = Math.min(Math.min(feeForOneStep, feeForTwoSteps), feeForThreeSteps);
        }
        return matrix[ this.fees.length ];
    }
}