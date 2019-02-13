package z.reference.dynamicProgramming;

public class YInterviewDuplicateInteger {

    public int[] input;

    public YInterviewDuplicateInteger(int[] input) {
        this.input = input;
    }

    public static void main(String[] args) {
        int[] input = {2, 3, 4, 1, 5, 2, 1};
        YInterviewDuplicateInteger game = new YInterviewDuplicateInteger(input);
        game.solve();
    }

    private void solve() {
        int[] tempInput = input.clone();
        /* Here we are following the absolute method were we will check the index location of
         * certain value in a iterative manner and if checked we will flip it to negative and
         * move on to the next. When the same location is visited again it means the value of the
         * index from where we arrived at the particular place is a duplicate
         * NOTE: this is done in in-place and here the integer values will not be over the
         * size of the array */

        /* The logic behind this approach is that we are using the index locations like a
         * boolean value -ve means already visited this location during previous index
         * positions and we are keeping the values same but only making them negative
         *
         * Example: 2, 3, 4, 1, 5, 2, 1
         *          0  1  2  3  4  5  6
         * When we are checking the i=0 it will be like since the value is 2 we will go to the second index location
         * and flip it if not already flipped. If already flipped then it means we have a value 2 and that's why this
         * index location has been flipped
         *
         * In this example we will go to the second index and check it will be positive 4 so we flip it
         * Continuing on to the next index 1 the value is 3 so we go to the 3rd index location 1 and
         * check and flip it
         *
         * Current State: 2, 3, -4, -1, 5, 2, 1
         *
         * Now on to the next value 4 (NOTE we are taking only the absolute value here) and visit the
         * 4th index 5 and flip it.
         *
         * we proceed until the last two.
         * Current State(before visiting the 5th index): 2, -3, -4, -1, -5, -2, 1
         *
         * Upon visiting the 5th value index location we get to know that it is a duplicate since previously
         * it has been flipped
         * */
        for (int i = 0; i < tempInput.length; i++) {
            if (tempInput[ Math.abs(tempInput[ i ]) ] > 0) {
                tempInput[ Math.abs(tempInput[ i ]) ] = -tempInput[ Math.abs(tempInput[ i ]) ];
            } else {
                System.out.println("Duplicate element: " + Math.abs(tempInput[ i ]));
            }
        }
    }
}