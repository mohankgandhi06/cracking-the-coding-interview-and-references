package p.chapter.dynamicProgramming;

public class JPaintFill {

    public enum Mask {
        LEFT(0, -1), RIGHT(0, +1), TOP(-1, 0), BOTTOM(+1, 0);

        public int xcoordinate;
        public int ycoordinate;

        Mask(int xcoordinate, int ycoordinate) {
            this.xcoordinate = xcoordinate;
            this.ycoordinate = ycoordinate;
        }
    }

    public static void main(String[] args) {
        JPaintFill game = new JPaintFill();
        int[][] screen = new int[][]{
                {4, 3, 2, 1, 1, 3},
                {6, 1, 1, 1, 1, 6},
                {8, 1, 1, 2, 3, 7},
                {1, 7, 4, 5, 6, 9},
                {4, 3, 2, 1, 8, 10}
        };

        System.out.println("Screen before Fill: ");
        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[ 0 ].length; j++) {
                System.out.print(screen[ i ][ j ] + " ");
            }
            System.out.println();
        }

        game.fill(screen, 1, 2, 10);

        System.out.println("\nAfter Fill: ");
        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[ 0 ].length; j++) {
                System.out.print(screen[ i ][ j ] + " ");
            }
            System.out.println();
        }
    }

    private void fill(int[][] screen, int pointX, int pointY, int newColor) {
        solve(screen, pointX, pointY, newColor, screen[ pointX ][ pointY ], new boolean[ screen.length ][ screen[ 0 ].length ]);
    }

    private void solve(int[][] screen, int pointX, int pointY, int newColor, int originalColor, boolean[][] visited) {
        if (canFill(pointX, pointY, screen, originalColor, visited)) {
            solve(screen, pointX + Mask.LEFT.xcoordinate, pointY + Mask.LEFT.ycoordinate, newColor, originalColor, visited);
            solve(screen, pointX + Mask.TOP.xcoordinate, pointY + Mask.TOP.ycoordinate, newColor, originalColor, visited);
            solve(screen, pointX + Mask.RIGHT.xcoordinate, pointY + Mask.RIGHT.ycoordinate, newColor, originalColor, visited);
            solve(screen, pointX + Mask.BOTTOM.xcoordinate, pointY + Mask.BOTTOM.ycoordinate, newColor, originalColor, visited);
            screen[ pointX ][ pointY ] = newColor;
        }
        return;
    }

    private boolean canFill(int pointX, int pointY, int[][] screen, int originalColor, boolean[][] visited) {
        if (pointX < 0 || pointY < 0 || pointX >= screen.length || pointY >= screen[ 0 ].length) return false;
        if (visited[ pointX ][ pointY ]) return false;
        visited[ pointX ][ pointY ] = true;
        if (screen[ pointX ][ pointY ] != originalColor) return false;
        return true;
    }

}