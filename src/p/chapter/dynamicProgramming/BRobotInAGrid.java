package p.chapter.dynamicProgramming;

public class BRobotInAGrid {
    public static void main(String[] args) {
        int[][] board = new int[][]{
                /* 1 signifies that there is a block in the path */
                {0, 0, 0, 0, 1},
                {0, 1, 0, 0, 1},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0}
        };
        BRobotInAGrid game = new BRobotInAGrid();
        int[][] pathMatrix = new int[ board.length ][ board[ 0 ].length ];
        if (game.solve(board, pathMatrix, 0, 0)) {
            showMatrix(pathMatrix);
        }
    }

    private boolean solve(int[][] board, int[][] pathMatrix, int x, int y) {
        if (goalReached(board, x, y)) {
            pathMatrix[ x ][ y ] = 1;
            return true;
        }
        if (isValid(board, x, y)) {
            pathMatrix[ x ][ y ] = 1;
            if (solve(board, pathMatrix, x, y + 1)) {
                return true;
            }
            if (solve(board, pathMatrix, x + 1, y)) {
                return true;
            }
            pathMatrix[ x ][ y ] = 0;
        }
        return false;
    }

    private static void showMatrix(int[][] pathMatrix) {
        for (int i = 0; i < pathMatrix.length; i++) {
            for (int j = 0; j < pathMatrix[ 0 ].length; j++) {
                System.out.print(pathMatrix[ i ][ j ] + " ");
            }
            System.out.println();
        }
    }

    private boolean goalReached(int[][] matrix, int x, int y) {
        if (x == matrix.length - 1 && y == matrix[ 0 ].length - 1) return true;
        return false;
    }

    private boolean isValid(int[][] board, int x, int y) {
        if (board[ x ][ y ] == 0) return true;
        return false;
    }
}