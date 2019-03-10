package p.chapter.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class GPermutationsWithoutDuplicate {
    public static void main(String[] args) {
        GPermutationsWithoutDuplicate game = new GPermutationsWithoutDuplicate();
        String input = "abdcfvg";
        List<String> listOfLists = new ArrayList<>();
        System.out.println("Input: " + input);
        game.solveWithBruteForce(input, listOfLists, 0, new StringBuilder());
        System.out.println("Permutations: ");
        for (String s : listOfLists) {
            System.out.println(s);
        }

        input = "abdc";
        System.out.println("Input: " + input);
        listOfLists = new ArrayList<>();
        game.solveWithBruteForce(input, listOfLists, 0, new StringBuilder());
        System.out.println("Permutations: ");
        for (String s : listOfLists) {
            System.out.println(s);
        }
    }

    private void solveWithBruteForce(String input, List<String> listOfLists, int currentIndex, StringBuilder stringBuilder) {
        if (currentIndex == input.length()) {
            listOfLists.add(stringBuilder.toString());
            return;
        }
        StringBuilder include = new StringBuilder(stringBuilder);
        /* Include the current character into the stringBuilder and proceed with the recursion */
        solveWithBruteForce(input, listOfLists, currentIndex + 1, include.append(input.charAt(currentIndex)));
        /* Exclude the current character into the stringBuilder and proceed with the recursion */
        solveWithBruteForce(input, listOfLists, currentIndex + 1, stringBuilder);
        return;
    }
}