package r.chapter.hardDifficulty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RShortestSupersequence {
    public static void main(String[] args) {
        RShortestSupersequence game = new RShortestSupersequence();
        int[] smallArray = new int[]{
                1, 5, 9
        };
        int[] largeArray = new int[]{
                7, 5, 9, 0, 2, 1, 3, 5, 7, 8, 9, 1, 1, 5, 8, 8, 9, 7
        };
        System.out.print("Find: ");
        Arrays.stream(smallArray).forEach(x -> System.out.print(x + " "));
        System.out.print("\nIn: ");
        Arrays.stream(largeArray).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.fetchShortestSupersequence(smallArray, largeArray);

        smallArray = new int[]{
                1, 5, 9
        };
        largeArray = new int[]{
                7, 5, 9, 0, 2, 1, 5, 7, 8, 9, 1, 5, 8, 8, 9, 7
        };
        System.out.print("\nFind: ");
        Arrays.stream(smallArray).forEach(x -> System.out.print(x + " "));
        System.out.print("\nIn: ");
        Arrays.stream(largeArray).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.fetchShortestSupersequence(smallArray, largeArray);

        smallArray = new int[]{
                1, 5, 9
        };
        largeArray = new int[]{
                7, 5, 9, 1, 2, 1, 3, 5, 7, 8, 9, 1, 1, 5, 8, 8, 9, 7
        };
        System.out.print("\nFind: ");
        Arrays.stream(smallArray).forEach(x -> System.out.print(x + " "));
        System.out.print("\nIn: ");
        Arrays.stream(largeArray).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.fetchShortestSupersequence(smallArray, largeArray);

        smallArray = new int[]{
                1, 5, 9
        };
        largeArray = new int[]{
                7, 5, 7, 9, 2, 1, 3, 5, 7, 8, 9, 1, 8, 6, 1, 5, 8, 9, 7
        };
        System.out.print("\nFind: ");
        Arrays.stream(smallArray).forEach(x -> System.out.print(x + " "));
        System.out.print("\nIn: ");
        Arrays.stream(largeArray).forEach(x -> System.out.print(x + " "));
        System.out.println();
        game.fetchShortestSupersequence(smallArray, largeArray);
    }

    private void fetchShortestSupersequence(int[] smallArray, int[] largeArray) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int number : smallArray) {
            map.put(number, false);
        }
        int[] range = new int[ 2 ];
        range[ 0 ] = 0;
        range[ 1 ] = Integer.MAX_VALUE;
        int[] result = solve(map, largeArray, 0, 0, 3, false, range);
        System.out.println("Shortest Range: " + result[ 0 ] + " to " + result[ 1 ]);
    }

    private int[] solve(HashMap<Integer, Boolean> map, int[] largeArray, int currentIndex, int startIndex, int matchesRemaining, boolean reset, int[] result) {
        if (currentIndex >= largeArray.length) {
            if (matchesRemaining == 0) {
                return result;
            } else {
                return new int[]{0, Integer.MAX_VALUE};
            }
        }
        if (matchesRemaining == 0) {
            result = new int[]{startIndex, currentIndex - 1};
            return result;
        }
        /* If the integer is found in the map for the first time then
         * change the Start Index to this Current Index and proceed until
         * the entire list is found count the total range.
         *    If the total range lesser than the currentLowest one then replace the range.
         *    If not then leave without replacing.
         * Even if the integer is found skip it and go to the next index
         * and see if much shorter range can be found. Don't forget to reset the */
        int[] include = new int[ 2 ];
        include[ 0 ] = 0;
        include[ 1 ] = Integer.MAX_VALUE;
        if (map.containsKey(largeArray[ currentIndex ]) && !map.get(largeArray[ currentIndex ])) {
            if (!reset) {/* First time */
                map.replace(largeArray[ currentIndex ], true);
                startIndex = currentIndex;
                reset = true;
                include = solve(map, largeArray, currentIndex + 1, startIndex, matchesRemaining - 1, reset, result);
            } else {/* Next time */
                map.replace(largeArray[ currentIndex ], true);
                include = solve(map, largeArray, currentIndex + 1, startIndex, matchesRemaining - 1, reset, result);
            }
        } else {
            if (reset) {
                include = solve(map, largeArray, currentIndex + 1, startIndex, matchesRemaining, reset, result);
            } else {
                reset(map);
                include = solve(map, largeArray, currentIndex + 1, 0, 3, false, result);
            }
        }
        reset(map);
        int[] exclude = solve(map, largeArray, currentIndex + 1, 0, 3, false, result);
        if ((include[ 1 ] - include[ 0 ]) <= (exclude[ 1 ] - exclude[ 0 ])) {
            if (include[ 1 ] - include[ 0 ] < result[ 1 ] - result[ 0 ]) {
                result = include;
            }
        } else {
            if (exclude[ 1 ] - exclude[ 0 ] < result[ 1 ] - result[ 0 ]) {
                result = exclude;
            }
        }
        return result;
    }

    private void reset(HashMap<Integer, Boolean> map) {
        for (Map.Entry<Integer, Boolean> entry : map.entrySet()) {
            entry.setValue(false);
        }
    }
}