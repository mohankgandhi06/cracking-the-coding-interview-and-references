package r.chapter.hardDifficulty;

import java.util.Arrays;

public class JMajorityElement {
    public static void main(String[] args) {
        JMajorityElement game = new JMajorityElement();
        int[] input = new int[]{
                5, 3, 3, 5, 2, 5, 5, 5, 3, 5
        };
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        int result = game.solve(input);
        System.out.println("Majority Element: (-1 is returned if no Majority) " + result);

        input = new int[]{
                2, 3, 1, 3, 5, 3, 4, 2, 3, 3, 3, 3, 3
        };
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        result = game.solve(input);
        System.out.println("Majority Element: (-1 is returned if no Majority) " + result);

        input = new int[]{
                2, 3, 5, 4, 3, 6, 7, 7, 3, 3, 3, 3, 3, 3
        };
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        result = game.solve(input);
        System.out.println("Majority Element: (-1 is returned if no Majority) " + result);

        input = new int[]{
                2, 1, 5, 4, 3, 6, 7, 7, 3, 3, 3, 3, 3, 3
        };
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        result = game.solve(input);
        System.out.println("Majority Element: (-1 is returned if no Majority) " + result);

        input = new int[]{
                2, 1, 5, 4, 3, 6, 7, 7, 3, 3, 3, 3, 4, 5
        };
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        result = game.solve(input);
        System.out.println("Majority Element: (-1 is returned if no Majority) " + result);

        input = new int[]{
                2, 1, 5, 4, 3, 6, 7, 3, 3, 3, 3, 3, 4, 5
        };
        System.out.println();
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        System.out.println();
        result = game.solve(input);
        System.out.println("Majority Element: (-1 is returned if no Majority) " + result);
    }

    private int solve(int[] input) {
        /* First Pass we will check if there is a possibility of Majority */
        /* In the second pass we check if the Majority element that we thought to be is actually one */
        int possibleMajorityElement = firstPass(input);
        /*System.out.println(possibleMajorityElement);*/
        boolean isAchieved = false;
        if (possibleMajorityElement != -1) {
            isAchieved = isMajorityAchieved(possibleMajorityElement, input);
        }
        if (isAchieved) {
            return possibleMajorityElement;
        }
        return -1;
    }

    private int firstPass(int[] input) {
        int index = 0;
        int element = input[ index ];
        int elementCount = 1;
        int unmatchedElementCount = 0;
        index++;
        while (index < input.length) {
            if (element == input[ index ]) {
                elementCount++;
            } else {
                unmatchedElementCount++;
            }
            if (unmatchedElementCount > elementCount && index + 1 < input.length) {
                element = input[ index + 1 ];
                elementCount = 1;
                unmatchedElementCount = 0;
                index++;
            }
            index++;
        }
        if (unmatchedElementCount > elementCount) {
            return -1;
        }
        return element;
    }

    private boolean isMajorityAchieved(int possibleMajorityElement, int[] input) {
        int majorityCount = 0;
        for (int i : input) {
            if (i == possibleMajorityElement) {
                majorityCount++;
            }
        }
        if (majorityCount > input.length / 2) {
            return true;
        }
        return false;
    }
}