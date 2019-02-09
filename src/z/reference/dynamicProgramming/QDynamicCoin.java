package z.reference.dynamicProgramming;

public class QDynamicCoin {

    public int[] coin;
    public int capacity;
    public int[][] matrix;

    public QDynamicCoin(int[] coin, int capacity) {
        this.coin = coin;
        this.capacity = capacity;
        this.matrix = new int[ coin.length + 1 ][ capacity + 1 ];
        for (int column = 0; column <= coin.length; column++) {
            this.matrix[ column ][ 0 ] = 1;
        }
    }

    public static void main(String[] args) {
        int[] coin = {1, 2, 3};
        int capacity = 4;
        QDynamicCoin game = new QDynamicCoin(coin, capacity);
        System.out.println("Maximum combinations possible: " + game.solve());
        game.showMatrix();
    }

    public int solve() {
        for (int c = 1; c <= coin.length; c++) {
            for (int a = 1; a <= capacity; a++) {
                int t = 0;
                if (a - coin[ c - 1 ] >= 0) {
                    t = this.matrix[ c ][ a - coin[ c - 1 ] ];
                }
                this.matrix[ c ][ a ] = Math.max(this.matrix[ c - 1 ][ a ] + t, this.matrix[ c - 1 ][ a ]);
            }
        }
        return this.matrix[ coin.length ][ capacity ];
    }

    public void showMatrix() {
        System.out.println();
        System.out.println("Matrix which make up the table");
        for (int i = 0; i <= coin.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                System.out.print(this.matrix[ i ][ j ] + " ");
            }
            System.out.println();
        }
    }
}