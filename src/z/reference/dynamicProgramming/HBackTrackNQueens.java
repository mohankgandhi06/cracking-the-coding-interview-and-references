package z.reference.dynamicProgramming;

public class HBackTrackNQueens {

    private static int[][] chessBoard;
    private static int numberOfQueens;

    public HBackTrackNQueens(int numberOfQueens) {
        this.chessBoard = new int[ numberOfQueens ][ numberOfQueens ];
        this.numberOfQueens = numberOfQueens;
    }

    public static void main(String[] args) {
        int numberOfQueens = 8;
        HBackTrackNQueens game = new HBackTrackNQueens(numberOfQueens);
        System.out.println(numberOfQueens + " Queens Problem: ");
        if (game.solve(0)) {
            showBoard();
        } else {
            System.out.println("No Solution.... ");
        }
    }

    private static void showBoard() {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[ 0 ].length; j++) {
                if (chessBoard[ i ][ j ] == 1) {
                    System.out.print(" Q ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

    private boolean solve(int columnIndex) {
        if (columnIndex == numberOfQueens) {
            return true;
        }
        for (int position = 0; position < chessBoard.length; position++) {
            if (isValid(position, columnIndex)) {
                chessBoard[ position ][ columnIndex ] = 1;

                if (solve(columnIndex + 1)) {
                    return true;
                }
                //Backtrack Step
                chessBoard[ position ][ columnIndex ] = 0;
            }
        }
        return false;
    }

    private boolean isValid(int rowIndex, int columnIndex) {
        //Check the row if there is any interference
        for (int i = 0; i < chessBoard.length; i++) {
            if (chessBoard[ rowIndex ][ i ] == 1 && i != columnIndex) {
                return false;
            }
        }
        //Check the column from left top to right bottom
        for (int i = rowIndex, j = columnIndex; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[ i ][ j ] == 1 && i != rowIndex && j != columnIndex) {
                return false;
            }
        }
        //Check the column from right top to left bottom
        for (int i = rowIndex, j = columnIndex; i < chessBoard.length && j >= 0; i++, j--) {
            if (chessBoard[ i ][ j ] == 1 && i != rowIndex && j != columnIndex) {
                return false;
            }
        }
        return true;
    }
}