package z.reference.dynamicProgramming;

public class RDynamicRod {

    public int capacity;
    public int[] piece;
    public int[] price;
    public int[][] matrix;

    public RDynamicRod(int capacity, int[] piece, int[] price) {
        this.capacity = capacity;
        this.piece = piece;
        this.price = price;
        this.matrix = new int[ piece.length + 1 ][ capacity + 1 ];
    }

    public static void main(String[] args) {
        int capacity = 5;
        int[] piece = {1, 2, 3, 4};
        int[] price = {2, 5, 7, 3};
        RDynamicRod game = new RDynamicRod(capacity, piece, price);
        System.out.println("Maximum Benefit: " + game.solve());
        game.showMatrix();
        game.showChoices();
    }

    public int solve() {
        for (int p = 1; p <= piece.length; p++) {
            for (int s = 1; s <= capacity; s++) {
                int taking = 0;
                int notTaking = 0;
                int t = 0;

                notTaking = this.matrix[ p - 1 ][ s ];

                if (s - p >= 0) {//Avoid out of bounds
                    t = this.matrix[ p ][ s - p ];
                }

                if (s >= p) {//Not considering the cutting option if the size is less than the cut size
                    taking = price[ p - 1 ] + t;
                }
                this.matrix[ p ][ s ] = Math.max(notTaking, taking);
            }
        }
        return this.matrix[ piece.length ][ capacity ];
    }

    public void showMatrix() {
        System.out.println();
        System.out.println("Matrix which make up the table");
        for (int i = 0; i <= piece.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                System.out.print(this.matrix[ i ][ j ] + " ");
            }
            System.out.println();
        }
    }

    public void showChoices() {
        System.out.println();
        System.out.println("Optimal choices are as follows: ");
        for (int row = piece.length, column = capacity; row > 0 && column >= 0; ) {
            if (this.matrix[ row ][ column ] != this.matrix[ row - 1 ][ column ]) {
                System.out.println("Select " + piece[ row - 1 ] + " sized (rod) piece of value " + price[ row - 1 ]);
                column = column - row;
            } else {
                row--;
            }
        }
    }
}