package r.chapter.hardDifficulty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TVolumeOfHistogram {
    public static void main(String[] args) {
        TVolumeOfHistogram game = new TVolumeOfHistogram();
        int[] histogram = new int[]{0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 5, 0, 1, 0, 0};
        System.out.println("1) Histogram: \n\n\n\n");
        Arrays.stream(histogram).forEach(x -> System.out.print(x + " "));
        System.out.println();
        int volume = game.solve(histogram);
        System.out.println("\nHistogram Volume obtained by pouring water across: " + volume);

        histogram = new int[]{2, 0, 0, 4, 0, 0, 3, 8, 0, 3, 0, 5, 0, 1, 0, 0};
        System.out.println("\n\n2) Histogram: \n\n\n\n");
        Arrays.stream(histogram).forEach(x -> System.out.print(x + " "));
        System.out.println();
        volume = game.solve(histogram);
        System.out.println("\nHistogram Volume obtained by pouring water across: " + volume);

        histogram = new int[]{6, 0, 0, 5, 0, 0, 4, 3, 0, 8, 0, 0, 6, 0, 0};
        System.out.println("\n\n3) Histogram: \n\n\n\n");
        Arrays.stream(histogram).forEach(x -> System.out.print(x + " "));
        System.out.println();
        volume = game.solve(histogram);
        System.out.println("\nHistogram Volume obtained by pouring water across: " + volume);

        histogram = new int[]{6, 0, 0, 5, 0, 0, 4, 3, 0, 2, 0, 0, 1, 0, 0};
        System.out.println("\n\n4) Histogram: \n\n\n\n");
        Arrays.stream(histogram).forEach(x -> System.out.print(x + " "));
        System.out.println();
        volume = game.solve(histogram);
        System.out.println("\nHistogram Volume obtained by pouring water across: " + volume);
    }

    private int solve(int[] histogram) {
        List<Integer> increasingPointsFromLeft = new ArrayList<>();
        int currentGreatest = 0;
        int startBar = -1;
        int intermediateBar = 0;
        int leftSideVolume = 0;
        for (int i = 0; i < histogram.length; i++) {
            if (histogram[ i ] != 0 && histogram[ i ] >= currentGreatest) {
                if (startBar == -1) {
                    startBar = i;
                }
                if (currentGreatest != 0) {
                    int width = (i - startBar - 1);
                    int height = currentGreatest > histogram[ i ] ? histogram[ i ] : currentGreatest;
                    leftSideVolume += (width * height) - intermediateBar;
                    /* DONT FORGET TO RESET THE VALUES */
                    startBar = i;
                    intermediateBar = 0;
                }
                currentGreatest = histogram[ i ];
                increasingPointsFromLeft.add(histogram[ i ]);
            } else if (histogram[ i ] != 0 && currentGreatest != 0) {
                intermediateBar += histogram[ i ];
            }
        }
        /*System.out.println("Second Half(Can Start here): " + startBar);
        for (int i : increasingPointsFromLeft) {
            System.out.println(i + " ");
        }
        System.out.println("Left Side Volume: " + leftSideVolume);*/

        int endIndex = startBar;
        currentGreatest = 0;
        startBar = -1;
        intermediateBar = 0;
        int rightSideVolume = 0;
        List<Integer> increasingPointsFromRight = new ArrayList<>();

        for (int j = histogram.length - 1; j >= endIndex; j--) {
            int z = 0;//To skip the intellij duplicate code detection
            if (histogram[ j ] != 0 && histogram[ j ] >= currentGreatest) {
                int x = 0;//To skip the intellij duplicate code detection
                if (startBar == -1) {
                    startBar = j;
                }
                if (currentGreatest != 0) {
                    int c = 0;//To skip the intellij duplicate code detection
                    int width = (startBar - j - 1);
                    int height = currentGreatest > histogram[ j ] ? histogram[ j ] : currentGreatest;
                    rightSideVolume += (width * height) - intermediateBar;
                    /* DONT FORGET TO RESET THE VALUES */
                    startBar = j;
                    intermediateBar = 0;
                }
                currentGreatest = histogram[ j ];
                increasingPointsFromRight.add(histogram[ j ]);
            } else if (histogram[ j ] != 0 && currentGreatest != 0) {
                intermediateBar += histogram[ j ];
            }
        }
        /*for (int i : increasingPointsFromRight) {
            System.out.println(i + " ");
        }
        System.out.println("Left Side Volume: " + rightSideVolume);*/
        return leftSideVolume + rightSideVolume;
    }
}