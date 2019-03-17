package q.chapter.mediumDifficulty;

public class DTicTacToe {

    public static final char cross = 'x';
    public static final char o = 'o';
    public char[][] board;
    public int totalMovesAvailable;

    public DTicTacToe(int size) {
        this.board = new char[ size ][ size ];
        this.totalMovesAvailable = size * size;
    }

    public static void main(String[] args) {
        int size = 4;
        DTicTacToe game = new DTicTacToe(size);
        /* X Win Scenario */
        System.out.println("Game #1");
        game.move(1, 1, cross, 1);
        game.move(2, 3, o, 2);
        game.move(2, 2, cross, 3);
        game.move(2, 1, o, 4);
        game.move(0, 0, cross, 5);
        game.move(2, 0, o, 6);
        game.move(3, 3, cross, 7);

        game = new DTicTacToe(size);
        System.out.println("\nGame #2");
        /* O Win Scenario - Row Match */
        game.move(1, 1, cross, 1);
        game.move(2, 3, o, 2);
        game.move(2, 2, cross, 3);
        game.move(2, 1, o, 4);
        game.move(0, 0, cross, 5);
        game.move(3, 3, o, 6);
        game.move(2, 0, cross, 7);
        game.move(0, 3, o, 8);
        game.move(1, 2, cross, 9);
        game.move(1, 3, o, 10);

        game = new DTicTacToe(size);
        System.out.println("\nGame #3");
        /* O Win Scenario - Column Match */
        game.move(1, 1, cross, 1);
        game.move(2, 0, o, 2);
        game.move(1, 2, cross, 3);
        game.move(2, 1, o, 4);
        game.move(0, 0, cross, 5);
        game.move(2, 3, o, 6);
        game.move(3, 0, cross, 7);
        game.move(2, 2, o, 8);

        game = new DTicTacToe(size);
        System.out.println("\nGame #4");
        /* Draw Scenario */
        game.move(0, 0, cross, 1);
        game.move(0, 2, o, 2);
        game.move(0, 1, cross, 3);
        game.move(0, 3, o, 4);
        game.move(1, 0, cross, 5);
        game.move(1, 1, o, 6);
        game.move(1, 2, cross, 7);
        game.move(1, 3, o, 8);
        game.move(2, 1, cross, 9);
        game.move(2, 2, o, 10);
        game.move(2, 3, cross, 11);
        game.move(2, 0, o, 12);
        game.move(3, 2, cross, 13);
        game.move(3, 1, o, 14);
        game.move(3, 0, cross, 15);
        game.move(3, 3, o, 16);
    }

    private boolean move(int xcoordinate, int ycoordinate, char sign, int moveNumber) {
        this.board[ xcoordinate ][ ycoordinate ] = sign;
        if (moveNumber > totalMovesAvailable) return false;
        /* Considering there are no illegal moves being made i.e. like moving the already made move again */
        boolean won = false;
        int result = isDiagonalCheckRequired(xcoordinate, ycoordinate);
        if (result == -1) {//From Top Left to Bottom Right
            won = checkForwardDiagonal(sign);
        } else if (result == 1) {//From Top Right to Bottom Left
            won = checkBackwardDiagonal(sign);
        }
        //for result == 0 Not required, only check the plus path
        if (!won) {
            won = checkPlusPath(xcoordinate, ycoordinate, sign);
        }
        if (won) {
            System.out.println(sign + " Won the match");
        }
        if (moveNumber == totalMovesAvailable) {
            System.out.println("Match ends in a draw");
        }
        return false;
    }

    private int isDiagonalCheckRequired(int xcoordinate, int ycoordinate) {
        if (xcoordinate == ycoordinate) {//From Top Left to Bottom Right
            return -1;
        } else if (xcoordinate + ycoordinate == this.board.length - 1) {//From Top Right to Bottom Left
            return 1;
        } else {
            return 0;
        }
    }

    private boolean checkForwardDiagonal(char sign) {
        for (int i = 0; i < this.board.length; i++) {
            if (this.board[ i ][ i ] != sign) {
                return false;
            }
        }
        return true;
    }

    private boolean checkBackwardDiagonal(char sign) {
        int row = 0;
        int column = this.board.length - 1;
        while (row < this.board.length && column >= 0) {
            if (this.board[ row ][ column ] != sign) {
                return false;
            }
            row++;
            column--;
        }
        return true;
    }

    private boolean checkPlusPath(int xcoordinate, int ycoordinate, char sign) {
        boolean rowFlag = true;
        for (int row = 0; row < this.board.length; row++) {
            if (this.board[ row ][ ycoordinate ] != sign) {
                rowFlag = false;
                break;
            }
        }
        if (rowFlag) return true;
        for (int column = 0; column < this.board.length; column++) {
            if (this.board[ xcoordinate ][ column ] != sign) {
                return false;
            }
        }
        return true;
    }
}