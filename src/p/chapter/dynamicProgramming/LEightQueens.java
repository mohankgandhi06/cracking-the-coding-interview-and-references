package p.chapter.dynamicProgramming;

public class LEightQueens {

    public static void main(String[] args) {
        LEightQueens game = new LEightQueens();
        int queens = 8;
        int[][] board = new int[ queens ][ queens ];
        if (game.solve(queens, board)) {
            showBoard(board);
        } else {
            System.out.println("No Solution");
        }
    }

    private boolean solve(int number, int[][] board) {
        /* Backtrack */
        if (number == 0) return true;
        for (int i = 0; i < board.length; i++) {
            if (isValid(i, board.length - number, board)) {
                board[ i ][ board.length - number ] = 1;
                if (solve(number - 1, board)) {
                    return true;
                }
                /* Back track */
                board[ i ][ board.length - number ] = 0;
            }
        }
        return false;
    }

    private boolean isValid(int x, int y, int[][] board) {
        if (plusPath(x, y, board) && diagonalPath(x, y, board)) return true;
        return false;
    }

    private boolean plusPath(int x, int y, int[][] board) {
        /* In the path we only need to check for the same row and decreasing column
         * since we have not placed the queen yet in proceeding columns and we are
         * only keeping only one queen in each column */
        for (int i = 0; i < y; i++) {
            if (board[ x ][ i ] == 1) {
                return false;
            }
        }
        return true;
    }

    private boolean diagonalPath(int x, int y, int[][] board) {
        int a = x;
        int b = y;
        while (a > 0 && b > 0) {
            if (board[ a - 1 ][ b - 1 ] == 1) {
                return false;
            }
            a--;
            b--;
        }
        while (y > 0 && x < board.length - 1) {
            if (board[ x + 1 ][ y - 1 ] == 1) {
                return false;
            }
            x++;
            y--;
        }
        return true;
    }

    private static void showBoard(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[ 0 ].length; column++) {
                System.out.print(board[ row ][ column ] + " ");
            }
            System.out.println();
        }
    }
}