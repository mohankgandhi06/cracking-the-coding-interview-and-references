package o.chapter.objectOrientedDesign;

public class HOthello {

    /* Question: Othello is played as follows: Each Othello piece */

    public static void main(String[] args) {
        /* Here assumptions are that the program only facilitates the game and does not intend
         * to play as opponent.
         * Algorithm:
         * 1) Create a Board thread
         * 2) Place white and black (2 pieces each) diagonally and allow black player to go first
         * 3) Create two player thread and provide the details as required and initially their count will be 2 each
         * 4) Now when the player makes a move check if it is valid and then perform the conversion of the color
         * 5) Once there are no moves determine the winner by the count */
    }

    class Player {
        private int id;
        private String name;
        private Piece.Color color;
        private int count;

        public Player(String name, Piece.Color color) {
            this.name = name;
            this.color = color;
            this.count = 2;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Piece.Color getColor() {
            return color;
        }

        public void setColor(Piece.Color color) {
            this.color = color;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    class Board implements Runnable {
        public Piece pieces[][];
        private Piece.Color turn;//We could use boolean to symbolize whose turn it is to play
        // but if we are using the color then it will be clear

        public Board() {
            this.pieces = new Piece[ 8 ][ 8 ];
            this.turn = null;
        }

        @Override
        public void run() {
            //Exit condition here will be that there are no moves available for both the players
            // here we are giving true it will again be some condition check to determine if there
            // are any valid moves available for the players
            if (true) {
                if (turn == null) {
                    turn = Piece.Color.BLACK;
                }
                //wait for black's turn or white's turn to make the move
                Result result = checkTheValidity();
                if (result.valid) {
                    for (int i : result.locations) {
                        flipColor(i);
                    }
                }
            }
        }

        private Result checkTheValidity() {
            //Result has both boolean to return if valid and the positions that needs to be changed
            //Validity : If the values are either
            // Piece.Color.WHITE,
            // Piece.Color.BLACK,
            // null - we can create a dynamic mask such that it covers the diagonal path from the players Piece position
            // (for diagonal X axis +1 and Y axis +1 or X axis -1 and Y axis -1)
            // (for plus path either one will remain in the same row or column while the other moves)
            // We have to if there is actually two pieces of the Current players turn and return the locations of the other
            // pieces that will be converted and also true
            return null;
        }

        private void flipColor(int position) {
            //Here for each location flip the current color and then decrease at one end and increase the count for
            // the other player
        }
    }

    class Result {
        //Since in Java we can return only one type we are wrapping the things that we need under result
        // i.e. boolean to check whether the movement made is valid and then the locations
        private boolean valid;
        private int[] locations;//Get a list of position that needs to be altered
    }
}

class Piece {
    public enum Color {BLACK, WHITE}
}