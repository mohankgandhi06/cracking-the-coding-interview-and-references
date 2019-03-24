package q.chapter.mediumDifficulty;

public class QContiguousSequence {
    public static void main(String[] args) {
        QContiguousSequence game = new QContiguousSequence();
        int[] array = new int[]{3, -8, 6, -2, 4, -10};
        game.solveWithBruteForce(array);
        game.solveWithTabulation(array);
    }

    private void solveWithBruteForce(int[] array) {
        int result = solve(array, 0, 0);
        System.out.println("(Brute Force) Maximum Sum of Contiguous Array is: " + result);
    }

    private int solve(int[] array, int currentIndex, int currentSum) {
        if (currentIndex == array.length - 1) return currentSum;
        int include = solve(array, currentIndex + 1, currentSum + array[ currentIndex ]);
        int exclude = solve(array, currentIndex + 1, array[ currentIndex ]);
        return Math.max(include, exclude);
    }

    private void solveWithTabulation(int[] array) {
        int[] tabulation = new int[ array.length ];
        int maximum = 0;
        tabulation[ 0 ] = array[ 0 ];
        for (int i = 1; i < array.length; i++) {
            tabulation[ i ] = Math.max(array[ i ], array[ i ] + tabulation[ i - 1 ]);
            if (tabulation[ i ] > maximum) {
                maximum = tabulation[ i ];
            }
        }
        System.out.println("(Tabulation) Maximum Sum of Contiguous Array is: " + maximum);
    }
}