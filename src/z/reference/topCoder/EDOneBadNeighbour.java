package z.reference.topCoder;

import java.util.Arrays;

public class EDOneBadNeighbour {

    public int[] donations;
    public int[][] matrix;

    public EDOneBadNeighbour(int[] donations) {
        this.donations = donations;
        this.matrix = new int[ 2 ][ donations.length ];
        Arrays.fill(this.matrix[ 0 ], -1);
        Arrays.fill(this.matrix[ 1 ], -1);
    }

    public static void main(String[] args) {
        int[] donations = {10, 3, 2, 5, 7, 8};
        /*int[] donations = {7, 7, 7, 7, 7, 7, 7};*/
        /*int[] donations = {94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61, 6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397, 52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72};*/
        /*int[] donations = {835, 784, 541, 755, 778, 788, 621, 371, 521, 561, 519, 482, 442, 727, 803, 158, 59, 112, 334, 792, 824, 622, 465, 602, 245, 355, 777, 197, 403, 567, 738, 793, 918, 884, 972, 786, 284, 22, 529, 280};*/
        EDOneBadNeighbour game = new EDOneBadNeighbour(donations);
        System.out.println("Maximum Donations: " + game.solve());
    }

    private int solve() {
        return Math.max(max(2, true) + this.donations[ 0 ], max(1, false));
    }

    private int max(int index, boolean taken) {
        if (index == this.donations.length) return 0;
        //It is to avoid the neighbour (final) been taken when it's previous element is been taken
        if (index == this.donations.length - 1 && taken) return 0;
        //This is to take the final since the taken is false from above step itself
        else if (index == this.donations.length - 1) return this.donations[ index ];
        if (this.matrix[ taken ? 1 : 0 ][ index ] >= 0) return this.matrix[ taken ? 1 : 0 ][ index ];
        return this.matrix[ taken ? 1 : 0 ][ index ] = Math.max(max(index + 2, taken) + this.donations[ index ], max(index + 1, taken));
    }
}