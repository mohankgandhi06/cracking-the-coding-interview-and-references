package z.reference.topCoder;

public class DOneDZigZag {

    public int[] numbers;
    public int[][] matrix;//[][0] number [][1] length [][2] Check the next value for increase(+1) or decrease(-1) [][3] previous index

    public DOneDZigZag(int[] numbers) {
        this.numbers = numbers;
        this.matrix = new int[ numbers.length ][ 4 ];
        for (int i = 0; i < matrix.length; i++) {
            this.matrix[ i ][ 0 ] = this.numbers[ i ];
        }
        this.matrix[ 0 ][ 1 ] = 1;
        this.matrix[ 0 ][ 2 ] = 0;
        this.matrix[ 0 ][ 3 ] = -1;
    }

    public static void main(String[] args) {
        /*int[] numbers = { 1, 7, 4, 9, 2, 5 };*/
        /*int[] numbers = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};*/
        /*int[] numbers = { 44 };*/
        /*int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };*/
        int[] numbers = { 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 };
        /*int[] numbers = {374, 40, 854, 203, 203, 156, 362, 279, 812, 955,
                600, 947, 978, 46, 100, 953, 670, 862, 568, 188,
                67, 669, 810, 704, 52, 861, 49, 640, 370, 908,
                477, 245, 413, 109, 659, 401, 483, 308, 609, 120,
                249, 22, 176, 279, 23, 22, 617, 462, 459, 244};*/


        DOneDZigZag game = new DOneDZigZag(numbers);
        System.out.println("Length of the zig-zag sequence: " + game.solve());
        game.showChoices();
        game.showMatrix();
    }

    private int solve() {
        int previousIndex = 0;
        for (int i = 1; i < this.matrix.length; i++) {
            if (i == 1) {
                if ((this.matrix[ i - 1 ][ 0 ] - this.matrix[ i ][ 0 ]) > 0) {//+1
                    this.matrix[ i ][ 2 ] = +1;
                } else {//0
                    this.matrix[ i ][ 2 ] = 0;
                }
                this.matrix[ i ][ 1 ] = this.matrix[ i - 1 ][ 1 ] + 1;
                this.matrix[ i ][ 3 ] = previousIndex;
                previousIndex = i;
            } else {
                if (this.matrix[ i - 1 ][ 2 ] == 0) {//Expecting Decrease
                    if ((this.matrix[ i - 1 ][ 0 ] - this.matrix[ i ][ 0 ]) > 0) {
                        this.matrix[ i ][ 1 ] = this.matrix[ i - 1 ][ 1 ] + 1;
                        this.matrix[ i ][ 2 ] = +1;
                        this.matrix[ i ][ 3 ] = previousIndex;
                    } else {//Copy previous state to this index
                        this.matrix[ i ][ 1 ] = this.matrix[ i - 1 ][ 1 ];
                        this.matrix[ i ][ 2 ] = this.matrix[ i - 1 ][ 2 ];
                        this.matrix[ i ][ 3 ] = this.matrix[ i - 1 ][ 3 ];
                    }
                } else {//Expecting Increase
                    if ((this.matrix[ i - 1 ][ 0 ] - this.matrix[ i ][ 0 ]) < 0) {
                        this.matrix[ i ][ 1 ] = this.matrix[ i - 1 ][ 1 ] + 1;
                        this.matrix[ i ][ 2 ] = 0;
                        this.matrix[ i ][ 3 ] = previousIndex;
                    } else {//Copy previous state to this index
                        this.matrix[ i ][ 1 ] = this.matrix[ i - 1 ][ 1 ];
                        this.matrix[ i ][ 2 ] = this.matrix[ i - 1 ][ 2 ];
                        this.matrix[ i ][ 3 ] = this.matrix[ i - 1 ][ 3 ];
                    }
                }
                previousIndex = i;
            }
        }
        return this.matrix[ this.matrix.length - 1 ][ 1 ];
    }

    private void showChoices() {

    }

    private void showMatrix() {
        System.out.println();
        System.out.println("Index    Number    Length    Inc/Dec   Previous Index");
        for (int i = 0; i < this.matrix.length; i++) {
            /* Spacing for the Index */
            int indexSpace = 8;
            int indexSpaceValue = i;
            while (indexSpaceValue / 10 > 0) {
                indexSpace--;
                indexSpaceValue = indexSpaceValue / 10;
            }
            StringBuilder indexSpaceSB = new StringBuilder();
            for (int space = 0; space < indexSpace; space++) {
                indexSpaceSB.append(" ");
            }

            /* Spacing for the Numbers */
            int numberSpace = 9;
            int numberSpaceValue = this.matrix[ i ][ 0 ];
            while (numberSpaceValue / 10 > 0) {
                numberSpace--;
                numberSpaceValue = numberSpaceValue / 10;
            }
            StringBuilder numberSpaceSB = new StringBuilder();
            for (int space = 0; space < numberSpace; space++) {
                numberSpaceSB.append(" ");
            }

            /* Spacing for the Length */
            int lengthSpace = 9;
            int lengthSpaceValue = this.matrix[ i ][ 1 ];
            while (lengthSpaceValue / 10 > 0) {
                lengthSpace--;
                lengthSpaceValue = lengthSpaceValue / 10;
            }
            StringBuilder lengthSpaceSB = new StringBuilder();
            for (int space = 0; space < lengthSpace; space++) {
                lengthSpaceSB.append(" ");
            }

            /* Spacing for the Inc/Dec */
            int incDecSpace = 9;
            int incDecSpaceValue = this.matrix[ i ][ 2 ];
            while (incDecSpaceValue / 10 > 0) {
                incDecSpace--;
                incDecSpaceValue = incDecSpaceValue / 10;
            }
            StringBuilder incDecSpaceSB = new StringBuilder();
            for (int space = 0; space < incDecSpace; space++) {
                incDecSpaceSB.append(" ");
            }

            System.out.println(i + indexSpaceSB.toString() + this.matrix[ i ][ 0 ] + numberSpaceSB.toString() + this.matrix[ i ][ 1 ] + lengthSpaceSB + this.matrix[ i ][ 2 ] + incDecSpaceSB.toString() + this.matrix[ i ][ 3 ]);
        }
    }
}
