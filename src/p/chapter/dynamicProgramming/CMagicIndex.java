package p.chapter.dynamicProgramming;

import java.util.Arrays;

public class CMagicIndex {
    public static void main(String[] args) {
        CMagicIndex game = new CMagicIndex();
        int[] input = {-3, -2, -1, 0, 4, 6, 7, 8, 9};
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        System.out.println("Index: " + game.solve(input, 0));

        input = new int[]{-3, -2, -1, 0, 5, 6, 7, 8, 9};
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        System.out.println("Index: " + game.solve(input, 0));

        input = new int[]{-3, -2, -1, 0, 5, 5, 7, 8, 9};
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        System.out.println("Index: " + game.solveFollowUp(input, 0));

        input = new int[]{-3, -2, 0, 1, 4, 5, 7, 8, 9};
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        System.out.println("Index: " + game.solveFollowUp(input, 0));
    }

    private int solve(int[] input, int currentIndex) {
        /* This is for the sorted and unique digits. Here we are checking the input[currentIndex] > currentIndex
         * since if the current element if higher and since there is sorted and unique there is no possibility after it
         * so we are returning -1 stating that the magic index is not possible for the scenario */
        if (currentIndex == input.length || input[ currentIndex ] > currentIndex) return -1;
        /* Unable to find the magic since we would have returned already if the index is found */
        if (currentIndex == input[ currentIndex ]) return currentIndex;
        return solve(input, currentIndex + 1);
    }

    private int solveFollowUp(int[] input, int currentIndex) {
        if (currentIndex == input.length) return -1;
        /* Unable to find the magic since we would have returned already if the index is found */
        if (currentIndex == input[ currentIndex ]) return currentIndex;
        return solveFollowUp(input, currentIndex + 1);
    }
}