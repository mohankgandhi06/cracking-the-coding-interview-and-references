package z.reference.dynamicProgramming;

public class SDynamicSubsetSum {

    public int[] values;
    public boolean[][] matrix;
    public int sum;

    public SDynamicSubsetSum(int[] values, int sum) {
        this.values = values;
        this.matrix = new boolean[ values.length + 1 ][ sum + 1 ];
        this.sum = sum;
    }

    public static void main(String[] args) {
        int[] values = {5, 2, 1, 3};
        int sum = 9;
        SDynamicSubsetSum game = new SDynamicSubsetSum(values, sum);
        System.out.println("Is subset sum possible with the input: " + (game.solve() ? "T" : "F"));
        game.showMatrix();
        game.showChoices();
    }

    private boolean solve() {
        for (int column = 0; column < this.matrix[ 0 ].length; column++) {
            this.matrix[ 0 ][ column ] = false;
        }
        for (int row = 0; row < this.matrix.length; row++) {
            this.matrix[ row ][ 0 ] = true;
        }
        for (int subset = 1; subset < this.matrix.length; subset++) {
            for (int sum = 1; sum < this.matrix[ 0 ].length; sum++) {
                if (this.matrix[ subset - 1 ][ sum ]) {
                    this.matrix[ subset ][ sum ] = this.matrix[ subset - 1 ][ sum ];
                } else {
                    this.matrix[ subset ][ sum ] = sum - this.values[ subset - 1 ] >= 0 ? this.matrix[ subset - 1 ][ sum - this.values[ subset - 1 ] ] : false;
                }
            }
        }
        return this.matrix[ this.matrix.length - 1 ][ this.matrix[ 0 ].length - 1 ];
    }

    private void showMatrix() {
        System.out.println();
        System.out.println("The Matrix is as follows: ");
        System.out.print("   ");
        for (int i = 0; i <= this.sum; i++) {
            System.out.print(i + "  ");
        }
        System.out.print("\n");
        for (int i = 0; i < this.matrix.length; i++) {
            System.out.print((i > 0 ? this.values[ i - 1 ] : "0") + "  ");
            for (int j = 0; j < this.matrix[ 0 ].length; j++) {
                System.out.print((this.matrix[ i ][ j ] ? "T" : "F") + "  ");
            }
            System.out.println();
        }
    }

    private void showChoices() {
        System.out.println();
        System.out.println("Subset which makes up the sum " + this.sum + " is as follows: ");
        int sum = this.sum;
        int row = this.matrix.length - 1;
        int column = this.matrix[ 0 ].length - 1;
        while (sum > 0) {
            if (this.matrix[ row ][ column ] != this.matrix[ row - 1 ][ column ]) {
                System.out.println(this.values[ row - 1 ] + " ");
                row--;
                column = sum - this.values[ row ];
                sum = sum - this.values[ row ];
            } else {
                row--;
            }
        }
    }
}