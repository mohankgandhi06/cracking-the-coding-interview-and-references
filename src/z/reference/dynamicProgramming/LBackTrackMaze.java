package z.reference.dynamicProgramming;

public class LBackTrackMaze {

    public int[][] maze;
    public int[][] path;

    public LBackTrackMaze(int[][] maze) {
        this.maze = maze;
        this.path = new int[ maze.length ][ maze.length ];
    }

    public static void main(String[] args) {
        int[][] maze = {
                {1, 1, 1, 1, 1, 1},
                {0, 0, 0, 1, 1, 0},
                {1, 1, 1, 1, 0, 1},
                {1, 0, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 0},
                {1, 1, 1, 0, 1, 1}
        };
        LBackTrackMaze game = new LBackTrackMaze(maze);
        if (game.solve(0, 0)) {
            game.showPath();
        } else {
            System.out.println("No Solution...");
        }
    }

    public boolean solve(int x, int y) {
        if (goalReached(x, y)) {
            return true;
        }
        if (isValid(x, y)) {
            path[ x ][ y ] = 1;
            if (solve(x + 1, y)) {
                return true;
            }
            if (solve(x, y + 1)) {
                return true;
            }
            //Back Track Step
            path[ x ][ y ] = 0;
        }
        return false;
    }

    public boolean isValid(int x, int y) {
        if (x >= maze.length || y >= maze.length) return false;
        if (maze[ x ][ y ] == 0) return false;
        return true;
    }

    public void showPath() {
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path.length; j++) {
                System.out.print(path[ i ][ j ] + " ");
            }
            System.out.println();
        }
    }

    public boolean goalReached(int x, int y) {
        if (x == path.length - 1 && y == path.length - 1) {
            path[ x ][ y ] = 1;
            return true;
        }
        return false;
    }
}