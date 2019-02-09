package z.reference.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class MBackTrackSudoku {

    public int[][] sudokuBoard;
    public static final int SIZE = 9;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 9;
    public static final int BOX_SIZE = 3;

    public MBackTrackSudoku(int[][] sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    public MBackTrackSudoku() {
        this.sudokuBoard = new int[ SIZE ][ SIZE ];
    }

    public static void main(String[] args) {
        List<InitialNumber> initialNumbers = new ArrayList<>();
        initialNumbers.add(new InitialNumber(0, 3, 2));
        initialNumbers.add(new InitialNumber(0, 4, 6));
        initialNumbers.add(new InitialNumber(0, 6, 7));
        initialNumbers.add(new InitialNumber(0, 8, 1));

        initialNumbers.add(new InitialNumber(1, 0, 6));
        initialNumbers.add(new InitialNumber(1, 1, 8));
        initialNumbers.add(new InitialNumber(1, 4, 7));
        initialNumbers.add(new InitialNumber(1, 7, 9));

        initialNumbers.add(new InitialNumber(2, 0, 1));
        initialNumbers.add(new InitialNumber(2, 1, 9));
        initialNumbers.add(new InitialNumber(2, 5, 4));
        initialNumbers.add(new InitialNumber(2, 6, 5));

        initialNumbers.add(new InitialNumber(3, 0, 8));
        initialNumbers.add(new InitialNumber(3, 1, 2));
        initialNumbers.add(new InitialNumber(3, 3, 1));
        initialNumbers.add(new InitialNumber(3, 7, 4));

        initialNumbers.add(new InitialNumber(4, 2, 4));
        initialNumbers.add(new InitialNumber(4, 3, 6));
        initialNumbers.add(new InitialNumber(4, 5, 2));
        initialNumbers.add(new InitialNumber(4, 6, 9));

        initialNumbers.add(new InitialNumber(5, 1, 5));
        initialNumbers.add(new InitialNumber(5, 5, 3));
        initialNumbers.add(new InitialNumber(5, 7, 2));
        initialNumbers.add(new InitialNumber(5, 8, 8));

        initialNumbers.add(new InitialNumber(6, 2, 9));
        initialNumbers.add(new InitialNumber(6, 3, 3));
        initialNumbers.add(new InitialNumber(6, 7, 7));
        initialNumbers.add(new InitialNumber(6, 8, 4));

        initialNumbers.add(new InitialNumber(7, 1, 4));
        initialNumbers.add(new InitialNumber(7, 4, 5));
        initialNumbers.add(new InitialNumber(7, 7, 3));
        initialNumbers.add(new InitialNumber(7, 8, 6));

        initialNumbers.add(new InitialNumber(8, 0, 7));
        initialNumbers.add(new InitialNumber(8, 2, 3));
        initialNumbers.add(new InitialNumber(8, 4, 1));
        initialNumbers.add(new InitialNumber(8, 5, 8));

        /*initialNumbers.add(new InitialNumber(0, 0, 3));
        initialNumbers.add(new InitialNumber(0, 1, 1));
        initialNumbers.add(new InitialNumber(0, 4, 4));
        initialNumbers.add(new InitialNumber(0, 8, 8));

        initialNumbers.add(new InitialNumber(1, 1, 8));
        initialNumbers.add(new InitialNumber(1, 2, 4));

        initialNumbers.add(new InitialNumber(2, 3, 5));
        initialNumbers.add(new InitialNumber(2, 5, 1));

        initialNumbers.add(new InitialNumber(3, 3, 6));
        initialNumbers.add(new InitialNumber(3, 4, 7));
        initialNumbers.add(new InitialNumber(3, 5, 9));

        initialNumbers.add(new InitialNumber(4, 0, 8));
        initialNumbers.add(new InitialNumber(4, 2, 9));
        initialNumbers.add(new InitialNumber(4, 4, 2));
        initialNumbers.add(new InitialNumber(4, 6, 6));
        initialNumbers.add(new InitialNumber(4, 8, 3));

        initialNumbers.add(new InitialNumber(5, 4, 6));
        initialNumbers.add(new InitialNumber(5, 5, 3));
        initialNumbers.add(new InitialNumber(5, 6, 7));

        initialNumbers.add(new InitialNumber(6, 2, 3));
        initialNumbers.add(new InitialNumber(6, 5, 2));

        initialNumbers.add(new InitialNumber(7, 6, 3));
        initialNumbers.add(new InitialNumber(7, 7, 6));

        initialNumbers.add(new InitialNumber(8, 0, 5));
        initialNumbers.add(new InitialNumber(8, 4, 3));
        initialNumbers.add(new InitialNumber(8, 7, 8));
        initialNumbers.add(new InitialNumber(8, 8, 9));*/

        int[][] sudokuboard = {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},

                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},

                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };

        MBackTrackSudoku game = new MBackTrackSudoku();
        SudokuSetup.setup(initialNumbers, game.sudokuBoard);
        game.showBoard();
        if (game.solve(0, 0)) {
            game.showBoard();
        } else {
            System.out.println("No Solution...");
        }
    }

    public boolean solve(int rowIndex, int columnIndex) {

        /* APPROACH #1: */
        /*if (rowIndex == SIZE && ++columnIndex == SIZE) {
            //In this if check we are incrementing the columnIndex at the end of the row i.e 9
            // So this will increment only when the row is complete. When we reach the 8th column and then
            // we reach the row 9th we are pre incrementing and checking if it both the row and column has
            // reached the end before going to the below steps. If we are not doing like this we need additional
            // if checks in order to avoid reaching the ArrayIndexOutOfBound exception for rowIndex = 0 columnIndex = 9
            return true;
        }
        if (rowIndex == SIZE) {
            rowIndex = 0;
        }*/
        /* APPROACH #2: */
        if (rowIndex == SIZE && columnIndex == SIZE) {
            //In this if check we are incrementing the columnIndex at the end of the row i.e 9
            // So this will increment only when the row is complete. When we reach the 8th column and then
            // we reach the row 9th we are pre incrementing and checking if it both the row and column has
            // reached the end before going to the below steps. If we are not doing like this we need additional
            // if checks in order to avoid reaching the ArrayIndexOutOfBound exception for rowIndex = 0 columnIndex = 9
            return true;
        }
        if (rowIndex == SIZE) {
            rowIndex = 0;
            columnIndex++;
            if (columnIndex == SIZE) {
                return true;
            }
        }
        if (this.sudokuBoard[ rowIndex ][ columnIndex ] != 0) {
            return solve(rowIndex + 1, columnIndex);
        }
        for (int value = MIN_NUMBER; value <= MAX_NUMBER; value++) {
            if (isValid(rowIndex, columnIndex, value)) {
                this.sudokuBoard[ rowIndex ][ columnIndex ] = value;
                if (solve(rowIndex + 1, columnIndex)) {
                    return true;
                }
                //Back Track Step
                this.sudokuBoard[ rowIndex ][ columnIndex ] = 0;
            }
        }
        return false;
    }

    public boolean isValid(int rowIndex, int columnIndex, int data) {
        for (int i = 0; i < 9; i++) {
            if (this.sudokuBoard[ rowIndex ][ i ] == data) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (this.sudokuBoard[ i ][ columnIndex ] == data)
                return false;
        }

        int rowOffset = (rowIndex / 3) * BOX_SIZE;
        int columnOffset = (columnIndex / 3) * BOX_SIZE;
        for (int i = 0; i < BOX_SIZE; i++) {
            for (int j = 0; j < BOX_SIZE; j++) {
                if (data == this.sudokuBoard[ i + rowOffset ][ j + columnOffset ]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void showBoard() {
        for (int i = 0; i < this.sudokuBoard.length; i++) {
            for (int j = 0; j < this.sudokuBoard[ 0 ].length; j++) {
                if ((j + 1) % 3 == 0 && j != 0) {
                    System.out.print(this.sudokuBoard[ i ][ j ] + "  ");
                } else {
                    System.out.print(this.sudokuBoard[ i ][ j ] + " ");
                }
            }
            if ((i + 1) % 3 == 0 && i != 0) {
                System.out.print("\n");
            }
            System.out.println();
        }
    }
}

class SudokuSetup {
    public static void setup(List<InitialNumber> initialNumbers, int[][] sudokuBoard) {
        for (InitialNumber i : initialNumbers) {
            sudokuBoard[ i.rowIndex ][ i.columnIndex ] = i.data;
        }
        return;
    }
}

class InitialNumber {
    public int rowIndex;
    public int columnIndex;
    public int data;

    public InitialNumber(int rowIndex, int columnIndex, int data) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.data = data;
    }
}