package z.reference.dynamicProgramming;

public class ZDynamicLargestContiguousSubarray {

    public int[] input;
    public int[][] matrix;

    public ZDynamicLargestContiguousSubarray(int[] input) {
        this.input = input;
        this.matrix = new int[ 2 ][ input.length ];//[0] Current Max, [1] Global Max
    }

    public static void main(String[] args) {
        int input[] = {7, 2, 3, -4, 5, -8};

        ZDynamicLargestContiguousSubarray game = new ZDynamicLargestContiguousSubarray(input);
        System.out.println("The largest sum of contiguous subarray is: " + game.solve());
        game.showMatrix();
        game.showChoices();
    }

    private int solve() {
        /* The logic behind solving the following problem is that it should be a
        contiguous sum, so we either take the current number as it is or consider
        taking the previous maximum along with it */
        this.matrix[ 0 ][ 0 ] = this.input[ 0 ];
        this.matrix[ 1 ][ 0 ] = this.input[ 0 ];
        for (int i = 1; i < this.input.length; i++) {
            this.matrix[ 0 ][ i ] = Math.max(this.input[ i ], this.input[ i ] + this.matrix[ 0 ][ i - 1 ]);
            this.matrix[ 1 ][ i ] = Math.max(this.matrix[ 1 ][ i - 1 ], this.matrix[ 0 ][ i ]);
        }
        return this.matrix[ this.matrix.length - 1 ][ this.matrix[ 0 ].length - 1 ];
    }

    private void showMatrix() {
        System.out.println();
        System.out.print("Input Array  ");
        for (int i = 0; i < this.input.length; i++) {
            System.out.print(this.input[ i ] + (this.input[ i ] < 0 || this.input[ i ] / 10 > 0 ? "  " : "   "));
        }
        System.out.println();
        for (int i = 0; i < this.matrix.length; i++) {
            if (i == 0) System.out.print("Current Max  ");
            else System.out.print("Global Max   ");
            for (int j = 0; j < this.matrix[ 0 ].length; j++) {
                System.out.print(this.matrix[ i ][ j ] + (this.matrix[ i ][ j ] < 0 || this.matrix[ i ][ j ] / 10 > 0 ? "  " : "   "));
            }
            System.out.println();
        }
    }

    private void showChoices() {
        System.out.println();
        System.out.println("Choices Made are as follows: ");
        int i = this.matrix[ this.matrix.length - 1 ][ this.matrix[ 0 ].length - 1 ];
        int position = this.matrix[ 0 ].length - 1;
        while (i > 0 && position >= 1) {
            if ((i - this.input[ position ] == this.matrix[ 0 ][ position - 1 ]) || (i - this.input[ position ] == 0)) {
                System.out.print(this.input[ position ] + " ");
                i = i - this.input[ position ];
            }
            position--;
        }
        if (i > 0 && position == 0) System.out.print(this.input[ position ]);
    }
}