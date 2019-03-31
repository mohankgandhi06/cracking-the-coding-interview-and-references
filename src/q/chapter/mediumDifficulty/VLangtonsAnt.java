package q.chapter.mediumDifficulty;

public class VLangtonsAnt {

    public GridAndAntPosition gridAndAntPosition;

    public VLangtonsAnt(int[][] blackAndWhiteGrid) {
        this.gridAndAntPosition = new GridAndAntPosition(blackAndWhiteGrid, new Position(0, 0), Direction.RIGHT);
    }

    public static void main(String[] args) {
        int[][] blackAndWhiteGrid = new int[][]{
                {1, 1, 0, 0, 1, 0},
                {0, 1, 0, 1, 0, 1},
                {1, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 1, 1, 0, 1}
        };
        VLangtonsAnt antInGrid = new VLangtonsAnt(blackAndWhiteGrid);
        int movesToMake = 8;

        antInGrid.trackMovement(movesToMake);
    }

    private void trackMovement(int movesToMake) {
        makeKMoves(movesToMake);
        printKMoves(movesToMake);
    }

    private void makeKMoves(int moveRemaining) {
        if (moveRemaining == 0) return;
        if (this.gridAndAntPosition.blackAndWhiteGrid[ this.gridAndAntPosition.currentPosition.row ][ this.gridAndAntPosition.currentPosition.column ] == 1) {//Black Square
            /* STEP: Turn Left */
            turn(this.gridAndAntPosition, Direction.LEFT);
        } else {//White Square
            /* STEP: Turn Right */
            turn(this.gridAndAntPosition, Direction.RIGHT);
        }
        /* STEP: FLIP the current color */
        this.gridAndAntPosition.blackAndWhiteGrid[ this.gridAndAntPosition.currentPosition.row ][ this.gridAndAntPosition.currentPosition.column ] = this.gridAndAntPosition.blackAndWhiteGrid[ this.gridAndAntPosition.currentPosition.row ][ this.gridAndAntPosition.currentPosition.column ] ^ 1;
        /* STEP: Make a move in the direction*/
        move(this.gridAndAntPosition);
        makeKMoves(moveRemaining - 1);
    }

    private void turn(GridAndAntPosition gridAndAntPosition, String turnToMake) {
        if (gridAndAntPosition.facingDirection.equalsIgnoreCase(Direction.TOP)) {
            if (turnToMake.equalsIgnoreCase(Direction.LEFT)) {
                gridAndAntPosition.facingDirection = Direction.LEFT;
            } else {
                gridAndAntPosition.facingDirection = Direction.RIGHT;
            }
        } else if (gridAndAntPosition.facingDirection.equalsIgnoreCase(Direction.RIGHT)) {
            if (turnToMake.equalsIgnoreCase(Direction.LEFT)) {
                gridAndAntPosition.facingDirection = Direction.TOP;
            } else {
                gridAndAntPosition.facingDirection = Direction.DOWN;
            }
        } else if (gridAndAntPosition.facingDirection.equalsIgnoreCase(Direction.LEFT)) {
            if (turnToMake.equalsIgnoreCase(Direction.LEFT)) {
                gridAndAntPosition.facingDirection = Direction.DOWN;
            } else {
                gridAndAntPosition.facingDirection = Direction.TOP;
            }
        } else {
            if (turnToMake.equalsIgnoreCase(Direction.LEFT)) {
                gridAndAntPosition.facingDirection = Direction.RIGHT;
            } else {
                gridAndAntPosition.facingDirection = Direction.LEFT;
            }
        }
    }

    private void move(GridAndAntPosition gridAndAntPosition) {
        /* Move to the new position if possible other wise remain in the position if we are going out of index */
        if (gridAndAntPosition.facingDirection.equalsIgnoreCase(Direction.TOP)) {
            if (gridAndAntPosition.currentPosition.row - 1 >= 0) {
                gridAndAntPosition.currentPosition.row -= 1;
            }
        } else if (gridAndAntPosition.facingDirection.equalsIgnoreCase(Direction.RIGHT)) {
            if (gridAndAntPosition.currentPosition.column + 1 < gridAndAntPosition.blackAndWhiteGrid[ 0 ].length) {
                gridAndAntPosition.currentPosition.column += 1;
            }
        } else if (gridAndAntPosition.facingDirection.equalsIgnoreCase(Direction.DOWN)) {
            if (gridAndAntPosition.currentPosition.row + 1 < gridAndAntPosition.blackAndWhiteGrid.length) {
                gridAndAntPosition.currentPosition.row += 1;
            }
        } else {
            if (gridAndAntPosition.currentPosition.column - 1 >= 0) {
                gridAndAntPosition.currentPosition.column -= 1;
            }
        }
    }

    private void printKMoves(int movesToMake) {
        System.out.println("Final Grid after " + movesToMake + " moves from the starting position. ");
        for (int row = 0; row < this.gridAndAntPosition.blackAndWhiteGrid.length; row++) {
            for (int column = 0; column < this.gridAndAntPosition.blackAndWhiteGrid[ 0 ].length; column++) {
                System.out.print(this.gridAndAntPosition.blackAndWhiteGrid[ row ][ column ] + " ");
            }
            System.out.println();
        }
    }
}

class GridAndAntPosition {
    public int[][] blackAndWhiteGrid;
    public Position currentPosition;
    public String facingDirection;

    public GridAndAntPosition(int[][] blackAndWhiteGrid, Position currentPosition, String facingDirection) {
        this.blackAndWhiteGrid = blackAndWhiteGrid;
        this.currentPosition = currentPosition;
        this.facingDirection = facingDirection;
    }
}

class Direction {
    public static final String LEFT = "L";
    public static final String RIGHT = "R";
    public static final String TOP = "T";
    public static final String DOWN = "D";
}

class Position {
    public int row;
    public int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
}