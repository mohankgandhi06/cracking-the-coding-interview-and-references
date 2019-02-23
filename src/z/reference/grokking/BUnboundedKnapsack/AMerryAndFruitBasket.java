package z.reference.grokking.BUnboundedKnapsack;

public class AMerryAndFruitBasket {

    public String[] fruits;
    public int[] weights;
    public int[] prices;
    public int capacity;

    public AMerryAndFruitBasket(String[] fruits, int[] weights, int[] prices, int capacity) {
        this.fruits = fruits;
        this.weights = weights;
        this.prices = prices;
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        /*String[] fruits = {"Apple", "Orange", "Melon"};
        int[] weights = {1, 2, 3};
        int[] prices = {15, 20, 50};
        int capacity = 5;*/

        String[] fruits = {"Apple", "Orange", "Melon", "Jack Fruit"};
        int[] prices = {15, 50, 60, 90};
        int[] weights = {1, 3, 4, 5};
        int capacity = 8;
        AMerryAndFruitBasket game = new AMerryAndFruitBasket(fruits, weights, prices, capacity);
        /*game.solveWithBruteForce();
        game.solveWithMemoization();*/
        game.solveWithTabulation();
    }

    private void solveWithBruteForce() {
        System.out.println("Maximum profit which can be earned: " + solve(0, 0, 0));
    }

    private int solve(int index, int weight, int sum) {
        if (index >= this.weights.length) return sum;
        if (weight + this.weights[ index ] > this.capacity) return sum;
        int include = solve(index, weight + this.weights[ index ], sum + this.prices[ index ]);
        int exclude = solve(index + 1, weight, sum);
        return Math.max(include, exclude);
    }

    private void solveWithMemoization() {
        Integer[][] memo = new Integer[ this.weights.length ][ this.capacity + 1 ];
        System.out.println("Maximum profit which can be earned: " + solve(0, this.capacity, 0, memo));
        System.out.println();
        System.out.println("Memo Table is as follows: ");
        for (int row = 0; row < memo.length; row++) {
            for (int column = 0; column < memo[ 0 ].length; column++) {
                System.out.print(memo[ row ][ column ] + " ");
            }
            System.out.println();
        }
    }

    private int solve(int index, int weight, int sum, Integer[][] memo) {
        if (weight <= 0 || this.prices.length == 0 || weights.length != this.prices.length || index >= this.prices.length) {
            return 0;
        }
        if (memo[ index ][ weight ] == null) {
            int include = 0;
            if (weights[ index ] <= weight) {
                include = this.prices[ index ] + solve(index, weight - weights[ index ], sum + this.prices[ index ], memo);
            }
            int exclude = solve(index + 1, weight, sum, memo);
            memo[ index ][ weight ] = Math.max(include, exclude);
        }
        return memo[ index ][ weight ];
    }

    private void solveWithTabulation() {
        int[][] matrix = new int[ this.weights.length + 1 ][ this.capacity + 1 ];
        solve(matrix);
        System.out.println("Maximum profit which can be earned: " + matrix[ matrix.length - 1 ][ matrix[ 0 ].length - 1 ]);
        System.out.println();
        System.out.println("The Matrix is as follows: ");
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[ 0 ].length; column++) {
                System.out.print(matrix[ row ][ column ] + " ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Choices which have been taken to reach this profit is as follows: ");
        showChoices(matrix);
    }

    private int[][] solve(int[][] matrix) {
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[ 0 ].length; column++) {
                int include = 0;
                if (this.weights[ row - 1 ] <= column) {
                    include = this.prices[ row - 1 ] + (column - this.weights[ row - 1 ] >= 0 ? matrix[ row ][ column - this.weights[ row - 1 ] ] : 0);
                }
                int exclude = matrix[ row - 1 ][ column ];
                matrix[ row ][ column ] = Math.max(include, exclude);
            }
        }
        return matrix;
    }

    private void showChoices(int[][] matrix) {
        int row = matrix.length - 1;
        int column = matrix[ 0 ].length - 1;
        while (matrix[ row ][ column ] != 0) {
            if (matrix[ row ][ column ] != matrix[ row - 1 ][ column ]) {
                column = column - this.weights[ row - 1 ];
                System.out.print(this.fruits[ row - 1 ] + " ");
            } else {
                row--;
            }
        }
    }
}