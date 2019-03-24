package q.chapter.mediumDifficulty;

import java.util.ArrayList;
import java.util.List;

public class SPondSizes {

    public List<Location> locations;

    public SPondSizes() {
        this.locations = new ArrayList<>();
        this.locations.add(new Location(-1, -1));
        this.locations.add(new Location(-1, 0));
        this.locations.add(new Location(-1, +1));
        this.locations.add(new Location(0, -1));
        this.locations.add(new Location(0, +1));
        this.locations.add(new Location(+1, -1));
        this.locations.add(new Location(+1, 0));
        this.locations.add(new Location(+1, +1));
    }

    public static void main(String[] args) {
        SPondSizes pondSizes = new SPondSizes();
        int[][] matrix = {
                {0, 2, 1, 0},
                {0, 0, 0, 1},
                {1, 2, 0, 1},
                {0, 1, 0, 1}
        };

        List<Integer> sizes = pondSizes.solve(matrix);
        System.out.println("Pond Sizes are as follows: ");
        for (Integer i : sizes) {
            System.out.print(i + " ");
        }
        System.out.println();

        matrix = new int[][]{
                {0, 2, 1, 0},
                {0, 1, 0, 1},
                {1, 2, 0, 1},
                {0, 1, 0, 1}
        };

        sizes = pondSizes.solve(matrix);
        System.out.println("Pond Sizes are as follows: ");
        for (Integer i : sizes) {
            System.out.print(i + " ");
        }
        System.out.println();

        matrix = new int[][]{
                {0, 2, 1, 0},
                {0, 3, 0, 1},
                {1, 0, 0, 1},
                {0, 1, 0, 1}
        };

        sizes = pondSizes.solve(matrix);
        System.out.println("Pond Sizes are as follows: ");
        for (Integer i : sizes) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private List<Integer> solve(int[][] matrix) {
        boolean[][] visit = new boolean[ matrix.length ][ matrix[ 0 ].length ];
        List<Integer> list = new ArrayList<>();
        /* If Unvisited then visit it and change the flag alone if the value is not equal to zero
         * if the value is zero then first all its path and then */
        int count = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[ 0 ].length; column++) {
                if (!visit[ row ][ column ]) {
                    if (matrix[ row ][ column ] != 0) {//Just Visit them and mark true
                        visit[ row ][ column ] = true;
                    } else {
                        visit[ row ][ column ] = true;
                        count = 1 + visitPaths(row, column, visit, matrix);
                        list.add(count);
                    }
                }
            }
        }
        /*for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[ 0 ].length; column++) {
                System.out.print(visit[ row ][ column ] ? "T " : "F ");
            }
            System.out.println();
        }*/
        return list;
    }

    private int visitPaths(int row, int column, boolean[][] visit, int[][] matrix) {
        int count = 0;
        for (int index = 0; index < this.locations.size(); index++) {
            if (isValid(row + this.locations.get(index).xcoordinate, column + this.locations.get(index).ycoordinate, matrix)) {
                if (!visit[ row + this.locations.get(index).xcoordinate ][ column + this.locations.get(index).ycoordinate ]) {
                    if (matrix[ row + this.locations.get(index).xcoordinate ][ column + this.locations.get(index).ycoordinate ] != 0) {//Just Visit them and mark true
                        visit[ row + this.locations.get(index).xcoordinate ][ column + this.locations.get(index).ycoordinate ] = true;
                    } else {
                        visit[ row + this.locations.get(index).xcoordinate ][ column + this.locations.get(index).ycoordinate ] = true;
                        count += 1 + visitPaths(row + this.locations.get(index).xcoordinate, column + this.locations.get(index).ycoordinate, visit, matrix);
                    }
                }
            }
        }
        return count;
    }

    private boolean isValid(int row, int column, int[][] matrix) {
        if (row >= 0 && row < matrix.length && column >= 0 && column < matrix[ 0 ].length) return true;
        return false;
    }
}

class Location {
    protected int xcoordinate;
    protected int ycoordinate;

    public Location(int xcoordinate, int ycoordinate) {
        this.xcoordinate = xcoordinate;
        this.ycoordinate = ycoordinate;
    }
}