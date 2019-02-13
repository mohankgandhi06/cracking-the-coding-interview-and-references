package z.reference.dynamicProgramming;

public class XDynamicTwoEggsProblem {

    public int[][] matrix;
    public int eggs;
    public int floors;

    public XDynamicTwoEggsProblem(int eggs, int floors) {
        this.matrix = new int[ eggs + 1 ][ floors + 1 ];
        this.eggs = eggs;
        this.floors = floors;

        /* In the First Floor minimum drops needed would be only 1 for all the eggs */
        for (int i = 0; i < this.matrix.length; i++) {
            this.matrix[ i ][ 1 ] = 1;
        }

        /* As the floor increase if we are having only one egg then we need drop
         * it linearly alone since there is no other chance to detect the floor exactly
         * if we don't have eggs. So we can check only linearly and so we are filling them */
        for (int i = 0; i < this.matrix[ 0 ].length; i++) {
            this.matrix[ 1 ][ i ] = i;
        }
    }

    public static void main(String[] args) {
        int eggs = 3;
        int floors = 100;
        XDynamicTwoEggsProblem game = new XDynamicTwoEggsProblem(eggs, floors);
        game.solve();
        game.showMinimumDrops();
        game.showMatrix();
    }

    private void solve() {
        /* At Each drop we are calculating only the minimum no of drops which can
         * take place in worst case-scenario (if eggs dropped broke we will be left
         * with eggs-1 and floors-1 to be checked), (if the eggs dropped doesn't broke
         * we can reuse the eggs and then we need to check above floors) */

        /* IMPORTANT: We are considering the eggs to be from the index 0 so if
         * the value is 1 it means 2nd egg */
        for (int egg = 2; egg < this.matrix.length; egg++) {
            for (int floor = 2; floor < this.matrix[ 0 ].length; floor++) {
                /* this.matrix[egg][floor] = 1 (current drop) + Math.min(Math.max(dropped egg broke, dropped egg didn't broke), previous minimum drop value)
                 * Here we are taking the Math.max since the worst case scenario is always assumed to be the greatest possible value among the ones available
                 * so we are considering the max drop value between the egg which dropped broke and the egg which didn't break */
                int minimum = Integer.MAX_VALUE;
                /* This for loop is to consider all the values before it */
                for (int x = 1; x <= floor; x++) {
                    int eggBroke = this.matrix[ egg - 1 ][ x - 1 ];// It means the floor is somewhere below the current floor so we take the minimum drop value of the previous floor for one egg less
                    int eggDidNotBreak = this.matrix[ egg ][ floor - x ];//We are taking the values of the floor-x and so they point to the previous floor values from greater to smaller
                    minimum = Math.min(1 + (Math.max(eggBroke, eggDidNotBreak)), minimum);
                    this.matrix[ egg ][ floor ] = minimum;
                }
                /* or */
                /*this.matrix[ egg ][ floor ] = Integer.MAX_VALUE;
                for (int x = 1; x <= floor; x++) {
                    int maxDrops = 1 + Math.max(this.matrix[ egg - 1 ][ x - 1 ], this.matrix[ egg ][ floor - x ]);
                    if (maxDrops < this.matrix[ egg ][ floor ]) {
                        this.matrix[ egg ][ floor ] = maxDrops;
                    }
                }*/
            }
        }
    }

    private void showMinimumDrops() {
        System.out.println("Minimum no. of drops required to find out the critical floor(i.e. floor above which egg breaks): " + this.matrix[ this.matrix.length - 1 ][ this.matrix[ 0 ].length - 1 ]);
        System.out.println();
    }

    private void showMatrix() {
        System.out.println("Matrix: ");
        for (int i = 1; i < this.matrix.length; i++) {
            for (int j = 1; j < this.matrix[ 0 ].length; j++) {
                System.out.print(this.matrix[ i ][ j ] + (this.matrix[ i ][ j ] / 10 >= 1 ? " " : "  "));
            }
            System.out.println();
        }
    }
}
