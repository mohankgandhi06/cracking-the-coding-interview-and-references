package z.reference.grokking.CFibonacciNumbers;

public class DMinimumJumpsToEnd {

    public int[] jumpArray;

    public DMinimumJumpsToEnd(int[] jumpArray) {
        this.jumpArray = jumpArray;
    }

    public static void main(String[] args) {
        int[] jumpArray = {2, 3, 1, 1, 4};
        jumpArray = new int[]{1, 1, 3, 6, 9, 3, 0, 1, 3};
        DMinimumJumpsToEnd game = new DMinimumJumpsToEnd(jumpArray);
        game.solveWithBruteForce();
        game.solveWithMemoization();
        game.solveWithTabulation();
    }

    private void solveWithBruteForce() {
        System.out.print("Brute Force: ");
        System.out.print("Minimum Jumps required to reach the end: " + solve(0));
        return;
    }

    private int solve(int currentIndex) {
        if (currentIndex == this.jumpArray.length - 1) return 0;
        if (this.jumpArray[ currentIndex ] == 0) return Integer.MAX_VALUE;
        int totalJumps = Integer.MAX_VALUE;
        int start = currentIndex + 1;
        int end = currentIndex + this.jumpArray[ currentIndex ];
        while (start < this.jumpArray.length && start <= end) {
            int minimumJumps = solve(start++);
            if (minimumJumps != Integer.MAX_VALUE) {//Because adding 1 to MAX_VALUE will flip it to negative number and it will become the most minimum value
                totalJumps = Math.min(totalJumps, minimumJumps + 1);
            }
        }
        return totalJumps;
    }

    private void solveWithMemoization() {
        System.out.println("\n");
        System.out.print("Memoization: ");
        Integer[] memo = new Integer[ this.jumpArray.length ];
        System.out.print("Minimum Jumps required to reach the end: " + solve(0, memo));
        System.out.println("\nMemo Table is as follows: ");
        for (int i = 0; i < memo.length; i++) {
            System.out.print(memo[ i ] + " ");
        }
        return;
    }

    private int solve(int currentIndex, Integer[] memo) {
        if (currentIndex == this.jumpArray.length - 1) return 0;
        if (this.jumpArray[ currentIndex ] == 0) return Integer.MAX_VALUE;
        /* The memo is filled in backward manner. at each currentIndex we are calculating which is the best possible route to that index
         * int[] jumpArray = {2, 3, 1, 1, 4};
         * the memo table is saved in this way in backwards
         * [4] null - from the end we need not make any jump
         * [3] 1 - from the third index of the array with the max jump value i.e. 1 we need to make one jump
         * [2] 2 - from the 2 index of the array with the max jump value i.e. 1 we need to make 2 jump
         *         here the value is obtained by the minimumJumps is 1 the value of [3] index and then
         *         totalJumps who's current value is MAX_VALUE is compared with minimumJumps+1 i.e. 2
         * [1] 1 - from the 1 index with the max jump value of 3 we are able to reach the end so we are returned 0
         *         for the minimumJumps and then upon comparing with the MAX_VALUE and minimumJumps+1 we get the 1
         * [0] 2 - same as 2 index.. here we get 2 jumps
         * */
        if (memo[ currentIndex ] != null) {
            return memo[ currentIndex ];
        }
        int totalJumps = Integer.MAX_VALUE;
        int start = currentIndex + 1;
        int end = currentIndex + this.jumpArray[ currentIndex ];
        while (start < this.jumpArray.length && start <= end) {
            int minimumJumps = solve(start++, memo);
            if (minimumJumps != Integer.MAX_VALUE) {
                totalJumps = Math.min(minimumJumps + 1, totalJumps);
            }
        }
        memo[ currentIndex ] = totalJumps;
        return memo[ currentIndex ];
    }

    private void solveWithTabulation() {
        System.out.println("\n");
        System.out.print("Tabulation: \n");
        System.out.print("Minimum Jumps required to reach the end: " + solve());
    }

    private int solve() {
        int[] matrix = new int[ this.jumpArray.length ];
        for (int i = 1; i < matrix.length; i++) {
            matrix[ i ] = Integer.MAX_VALUE;
        }
        for (int start = 0; start < this.jumpArray.length; start++) {
            for (int end = start + 1; end <= start + this.jumpArray[ start ] && end < this.jumpArray.length; end++) {
                matrix[ end ] = Math.min(matrix[ end ], matrix[ start ] + 1);
            }
        }
        System.out.println("Matrix table is as follows: ");
        for (int i : matrix) {
            System.out.print(i + " ");
        }
        System.out.println();
        return matrix[ matrix.length - 1 ];
    }
}