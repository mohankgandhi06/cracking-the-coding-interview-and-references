package z.reference.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class KBackTrackKnightTour {

    public int[][] chessBoard;
    public List<Move> moves;

    public KBackTrackKnightTour(int size) {
        this.chessBoard = new int[ size ][ size ];
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                this.chessBoard[ i ][ j ] = Integer.MIN_VALUE;
            }
        }
        this.moves = new ArrayList<>();
        this.moves.add(new Move(+2, +1));
        this.moves.add(new Move(+1, +2));
        this.moves.add(new Move(-1, +2));
        this.moves.add(new Move(-2, +1));
        this.moves.add(new Move(-2, -1));
        this.moves.add(new Move(-1, -2));
        this.moves.add(new Move(+1, -2));
        this.moves.add(new Move(+2, -1));
        this.chessBoard[ 0 ][ 0 ] = 1;
    }

    public static void main(String[] args) {
        KBackTrackKnightTour game = new KBackTrackKnightTour(6);
        if (game.solve(2, 0, 0)) {
            game.showSolution();
        } else {
            System.out.println("No Solution....");
        }
    }

    public boolean solve(int move, int xcoordinate, int ycoordinate) {
        if (move == chessBoard.length * chessBoard.length + 1) {
            return true;
        }
        for (int knightMoveIndex = 0; knightMoveIndex < moves.size(); knightMoveIndex++) {
            int xMove = xcoordinate + moves.get(knightMoveIndex).xcoordinate;
            int yMove = ycoordinate + moves.get(knightMoveIndex).ycoordinate;
            if (isValid(xMove, yMove)) {
                chessBoard[ xMove ][ yMove ] = move;

                if (solve(move + 1, xMove, yMove)) {
                    return true;
                }

                //Back Track
                chessBoard[ xMove ][ yMove ] = Integer.MIN_VALUE;
            }
        }
        return false;
    }

    public boolean isValid(int xcoordinate, int ycoordinate) {
        if ((xcoordinate < 0 || xcoordinate >= chessBoard.length)) return false;
        if ((ycoordinate < 0 || ycoordinate >= chessBoard.length)) return false;
        if (chessBoard[ xcoordinate ][ ycoordinate ] != Integer.MIN_VALUE) return false;
        return true;
    }

    public void showSolution() {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                System.out.print(this.chessBoard[ i ][ j ] + ((this.chessBoard[ i ][ j ] / 10 > 0) ? "   " : "    "));
            }
            System.out.println();
            System.out.println();
        }
    }
}

class Move {
    protected int xcoordinate;
    protected int ycoordinate;

    public Move(int xcoordinate, int ycoordinate) {
        this.xcoordinate = xcoordinate;
        this.ycoordinate = ycoordinate;
    }
}