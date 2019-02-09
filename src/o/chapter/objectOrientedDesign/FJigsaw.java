package o.chapter.objectOrientedDesign;

public class FJigsaw {
    /* Question: Implement an NxN jigsaw puzzle. Design the data structures and
     * explain an algorithm to solve the puzzle. You can assume that you have a
     * fitsWith method which, when passed two puzzle pieces, returns true if the
     * two edges belong together. */

    public static void main(String[] args) {
        JigsawPiece piece = new JigsawPiece(new int[]{0, -1, 1, 0});
    }
}

class Puzzle {
    private JigsawPiece[][] puzzle = new JigsawPiece[ 5 ][ 5 ];

    /* Here we can follow two passes algorithm.
     * In the first pass we can create a bucket of BitSet (nbits = number of pieces) with the following manner
     * LEFT +1    |     TOP +1      |       RIGHT +1    |       BOTTOM +1
     * LEFT 0     |     TOP 0       |       RIGHT 0     |       BOTTOM 0
     * LEFT -1    |     TOP -1      |       RIGHT -1    |       BOTTOM -1 */

    /* So in the above table when are going through the first pass of all the puzzles
     * we put each piece in the four columns */

    /* During the second pass we go to each location in the puzzle and determine what the left, top, right and bottom
     * has to be and then go to the corresponding bucket and gather all the possible digits (Here assumption is either
     * that the puzzle won't repeat). If the puzzle will repeat then if there is some way to check if the position for
     * the puzzle is correct. This is beyond the scope of the problem. So we take for granted that when we take a take
     * intersection of the bit vectors we will be given only a digit and so we are going to the proper puzzle piece for
     * each position */
}

class JigsawPiece {
    private int[][] piece = new int[ 5 ][ 5 ];

    //Outer positions are denoting the puzzle piece where it will protrude into next piece
    public JigsawPiece(int[] locationsState) {
        //LocationsState is an array which denoted the left[0] top[1] right[2] and bottom[3] in the order
        //If it is +1 it means it occupies the next position as well i.e. the fourth pixel position
        //If it is 0 it occupies only 3 spaces
        //If it is -1 it means it occupies one less
        //  ___    ___
        // |   |__|   |      - in the middle of the top row it is occupying one less -1
        // |          |__
        // |           __|   - in the middle of the right side it is occupying one more +1
        // |          |
        // |__________|      - in the bottom it is occupying only it position nothing more nothing less 0

        // JigsawPiece piece = new JigsawPiece(new int[]{0, -1, 1, 0}); this line of code will produce
        // a piece same as above

        //Prefill the normal fully self sufficient piece all the 3x3 if 1
        for (int i = 1; i < piece.length - 1; i++) {
            for (int j = 1; j < piece[ 0 ].length - 1; j++) {
                piece[ i ][ j ] = 1;
            }
        }

        int left = locationsState[ 0 ];
        int top = locationsState[ 1 ];
        int right = locationsState[ 2 ];
        int bottom = locationsState[ 3 ];

        if (left != 0) {
            if (left == 1) {
                piece[ 2 ][ 0 ] = 1;
            } else if (left == -1) {
                piece[ 2 ][ 1 ] = 0;
            }
        }

        if (top != 0) {
            if (top == 1) {
                piece[ 0 ][ 2 ] = 1;
            } else if (top == -1) {
                piece[ 1 ][ 2 ] = 0;
            }
        }

        if (right != 0) {
            if (right == 1) {
                piece[ 2 ][ piece[ 0 ].length - 1 ] = 1;
            } else if (right == -1) {
                piece[ 2 ][ piece[ 0 ].length - 2 ] = 0;
            }
        }

        if (bottom != 0) {
            if (bottom == 1) {
                piece[ piece.length - 1 ][ 2 ] = 1;
            } else if (bottom == -1) {
                piece[ piece.length - 2 ][ 2 ] = 0;
            }
        }

        /*for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[ 0 ].length; j++) {
                System.out.print(piece[ i ][ j ] + " ");
            }
            System.out.println();
        }*/
    }
}