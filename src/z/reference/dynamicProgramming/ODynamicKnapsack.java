package z.reference.dynamicProgramming;

public class ODynamicKnapsack {

    public int items;
    public int capacity;
    public int[][] matrix;
    public int[] weight;
    public int[] value;

    public ODynamicKnapsack(int capacity, int[] value, int[] weight) {
        this.items = value.length;
        this.capacity = capacity;
        this.weight = weight;
        this.value = value;
        this.matrix = new int[ this.items + 1 ][ this.capacity + 1 ];
        System.out.println("Items: " + this.items);
        System.out.println("Capacity: " + this.capacity);
        System.out.println("Number: " + this.items);
        for (int i = 0; i < matrix.length; i++) {//Row - Items
            for (int j = 0; j < matrix[ 0 ].length; j++) {//Column - Weights
                System.out.print(matrix[ i ][ j ] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] value = {10, 4, 7};
        int[] weight = {4, 2, 3};
        int capacity = 5;
        ODynamicKnapsack game = new ODynamicKnapsack(capacity, value, weight);
        /*System.out.println(game.knapsack());
        for (int i = 0; i < game.matrix.length; i++) {//Row - Items
            for (int j = 0; j < game.matrix[ 0 ].length; j++) {//Column - Weights
                System.out.print(game.matrix[ i ][ j ] + " ");
            }
            System.out.println();
        }*/

        int maxBenefit = game.knapsackDynamicProgramming(1, 1);
        System.out.println("Max Benefit: " + maxBenefit);
        System.out.println("Choices which will need to be taken are: ");
        game.showChoices(maxBenefit);
        System.out.println();

        for (int i = 0; i < game.matrix.length; i++) {//Row - Items
            for (int j = 0; j < game.matrix[ 0 ].length; j++) {//Column - Weights
                System.out.print(game.matrix[ i ][ j ] + " ");
            }
            System.out.println();
        }
    }

    private int knapsack() {
        /* S[i][w] = not taking + taking
         * S[i][w] = Math.max(S[i-1][w] ; value[i] + S[i-1][w-weight[i]] ) */

        for (int i = 1; i < this.matrix.length; i++) {
            for (int w = 1; w < this.matrix[ 0 ].length; w++) {
                if (w - this.weight[ i - 1 ] >= 0) {
                    this.matrix[ i ][ w ] = Math.max(this.matrix[ i - 1 ][ w ], (this.value[ i - 1 ] + this.matrix[ i - 1 ][ w - this.weight[ i - 1 ] ]));
                }
            }
        }
        return this.matrix[ this.items ][ this.capacity ];
    }

    private int knapsackDynamicProgramming(int i, int w) {
        if (w - this.weight[ i - 1 ] >= 0) {
            this.matrix[ i ][ w ] = Math.max(this.matrix[ i - 1 ][ w ], (this.value[ i - 1 ] + this.matrix[ i - 1 ][ w - this.weight[ i - 1 ] ]));
        }
        if (i == this.items && w + 1 > this.capacity) return matrix[ i ][ w ];
        if (w >= this.capacity) {
            i++;
            w = 0;
        }
        return knapsackDynamicProgramming(i, w + 1);
    }

    private void showChoices(int totalBenefit) {
        int i = this.matrix.length - 1;
        int w = this.matrix[ 0 ].length - 1;
        while (i >= 0 && w >= 0) {
            if (this.matrix[ i ][ w ] == 0) return;
            if (this.matrix[ i ][ w ] != this.matrix[ i - 1 ][ w ]) {
                System.out.println("Choice is made: " + i);
                w = w - weight[ i - 1 ];
                i--;
            }
        }
    }

}