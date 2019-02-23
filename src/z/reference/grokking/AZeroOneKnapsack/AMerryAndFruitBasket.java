package z.reference.grokking.AZeroOneKnapsack;

public class AMerryAndFruitBasket {

    public String[] fruits;
    public int[] weights;
    public int[] values;
    public int capacity;

    public AMerryAndFruitBasket(String[] fruits, int[] weights, int[] values, int capacity) {
        this.fruits = fruits;
        this.weights = weights;
        this.values = values;
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        /*String[] fruits = {"Apple", "Orange", "Banana", "Melon"};
        int[] weights = {2, 3, 1, 4};
        int[] value = {4, 5, 3, 7};
        int capacity = 5;*/
        String[] fruits = {"Apple", "Orange", "Banana", "Melon"};
        int[] weights = {1, 2, 3, 5};
        int[] value = {1, 6, 10, 16};
        int capacity = 7;

        AMerryAndFruitBasket game = new AMerryAndFruitBasket(fruits, weights, value, capacity);
        /*game.solveWithBruteForce();
        System.out.println();
        game.solveWithMemoization();
        System.out.println();
        game.solveWithTabulation();*/
        /*System.out.println();
        game.solveWithDPUsingTwoArray();*/
        game.solveWithDPUsingOneArray();
    }

    private void solveWithBruteForce() {
        int capacity = this.capacity;
        System.out.println("Brute Force");
        int profit = solve(0, capacity);
        System.out.println("Maximum profit which can be reaped: " + profit);
    }

    private int solve(int index, int capacity) {
        if (index >= this.fruits.length || capacity <= 0) return 0;
        /* INCLUDE the fruit if it is not exceeding the size */
        int profitInclude = 0;
        if (capacity - this.weights[ index ] >= 0) {
            profitInclude = this.values[ index ] + (solve(index + 1, capacity - this.weights[ index ]));
        }
        /* EXCLUDE the fruit and proceed with the remaining array */
        int profitExclude = solve(index + 1, capacity);
        return Math.max(profitInclude, profitExclude);
    }

    private void solveWithMemoization() {
        int capacity = this.capacity;
        int[][] memo = new int[ this.fruits.length + 1 ][ this.capacity + 1 ];
        System.out.println("Memoization");
        int profit = solve(memo, 0, capacity);
        System.out.println("Maximum profit which can be reaped: " + profit);
    }

    private int solve(int[][] memo, int index, int capacity) {
        if (index >= this.fruits.length || capacity < 0) return 0;

        if (memo[ index ][ capacity ] > 0) return memo[ index ][ capacity ];

        int profitInclude = 0;
        if (this.weights[ index ] <= capacity) {
            profitInclude = this.values[ index ] + solve(memo, index + 1, capacity - this.weights[ index ]);
        }

        int profitExclude = solve(memo, index + 1, capacity);

        return memo[ index ][ capacity ] = Math.max(profitInclude, profitExclude);
    }

    private void solveWithTabulation() {
        int[][] matrix = new int[ this.weights.length + 1 ][ this.capacity + 1 ];
        System.out.println("Dynamic Programming");
        System.out.println("Maximum profit which can be reaped: " + solve(matrix));
        showMatrix(matrix);
        showChoices(matrix);
    }

    private int solve(int[][] matrix) {
        for (int fruit = 1; fruit <= this.fruits.length; fruit++) {
            for (int weight = 1; weight <= this.capacity; weight++) {
                int profitInclude = 0;
                if (this.weights[ fruit - 1 ] <= weight) {
                    profitInclude = this.values[ fruit - 1 ] + ((weight - this.weights[ fruit - 1 ]) >= 0 ? matrix[ fruit - 1 ][ weight - this.weights[ fruit - 1 ] ] : 0);
                }
                int profitExclude = matrix[ fruit - 1 ][ weight ];

                matrix[ fruit ][ weight ] = Math.max(profitInclude, profitExclude);
            }
        }
        return matrix[ matrix.length - 1 ][ matrix[ 0 ].length - 1 ];
    }

    private void showMatrix(int[][] matrix) {
        System.out.println();
        System.out.println("The Matrix is as follows: ");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[ 0 ].length; j++) {
                System.out.print(matrix[ i ][ j ] + " ");
            }
            System.out.println();
        }
    }

    private void showChoices(int[][] matrix) {
        System.out.println();
        System.out.println("Fruits that must be chosen to maximize profits: ");
        int row = matrix.length - 1;
        int column = matrix[ 0 ].length - 1;
        int capacity = this.capacity;
        while (capacity > 0) {
            if (matrix[ row ][ column ] != matrix[ row - 1 ][ column ]) {
                System.out.print(this.fruits[ row - 1 ] + " ");
                capacity = capacity - this.weights[ row - 1 ];
                column = column - this.weights[ row - 1 ];
                row--;
            } else {
                row--;
            }
        }
    }

    private void solveWithDPUsingTwoArray() {
        System.out.println("Dynamic Programming using Two Array: ");
        System.out.println("Maximum profit which can be reaped: " + solve());
    }

    private int solve() {
        int[] previousRow = new int[ this.capacity + 1 ];
        int[] currentRow = new int[ this.capacity + 1 ];
        for (int fruit = 1; fruit <= this.fruits.length; fruit++) {
            for (int weight = 1; weight <= this.capacity; weight++) {
                int profitInclude = 0;
                if (this.weights[ fruit - 1 ] <= weight) {
                    profitInclude = this.values[ fruit - 1 ] + ((weight - this.weights[ fruit - 1 ]) >= 0 ? previousRow[ weight - this.weights[ fruit - 1 ] ] : 0);
                }
                int profitExclude = previousRow[ weight ];

                currentRow[ weight ] = Math.max(profitInclude, profitExclude);
            }
            previousRow = currentRow;

            /* Since we are using only two array's we are just printing out the result for reference */
            for (int i : currentRow) {
                System.out.print(i + " ");
            }
            System.out.print("\n");

            currentRow = new int[ this.capacity + 1 ];
        }
        return previousRow[ this.capacity ];
    }

    private void solveWithDPUsingOneArray() {
        System.out.println("Dynamic Programming using One Array: ");
        System.out.println("Maximum profit which can be reaped: " + solveOneArray());
    }

    private int solveOneArray() {
        int[] row = new int[ this.capacity + 1 ];
        for (int fruit = 1; fruit <= this.fruits.length; fruit++) {
            for (int weight = this.capacity; weight >= 1; weight--) {
                int profitInclude = 0;
                if (this.weights[ fruit - 1 ] <= weight) {
                    profitInclude = this.values[ fruit - 1 ] + ((weight - this.weights[ fruit - 1 ]) >= 0 ? row[ weight - this.weights[ fruit - 1 ] ] : 0);
                }
                int profitExclude = row[ weight ];

                row[ weight ] = Math.max(profitInclude, profitExclude);
            }

            /* Since we are using only two array's we are just printing out the result for reference */
            /*for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.print("\n");*/
        }
        return row[ this.capacity ];
    }
}