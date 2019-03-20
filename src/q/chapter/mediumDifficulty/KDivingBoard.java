package q.chapter.mediumDifficulty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KDivingBoard {
    public static void main(String[] args) {
        KDivingBoard game = new KDivingBoard();
        int shorterPlank = 5;
        int longerPlank = 8;
        int numberOfPlanks = 5;
        game.solve(shorterPlank, longerPlank, numberOfPlanks);
    }

    private void solve(int shorterPlank, int longerPlank, int numberOfPlanks) {
        System.out.println("Brute Force: ");
        solveWithBruteForce(shorterPlank, longerPlank, numberOfPlanks);
        System.out.println();
        System.out.println("Faster N Runtime: ");
        solveWithNRuntime(shorterPlank, longerPlank, numberOfPlanks);
    }

    private void solveWithBruteForce(int shorterPlank, int longerPlank, int numberOfPlanks) {
        Set<Integer> setOfLengths = new HashSet();
        calculateLengths(shorterPlank, longerPlank, 0, 0, numberOfPlanks, setOfLengths);
        System.out.println("Possible variations of length: ");
        for (Integer i : setOfLengths) {
            System.out.println("Length: " + i);
        }
    }

    private void calculateLengths(int shorterPlank, int longerPlank, int currentCount, int currentLength, int numberOfPlanks, Set setOfLengths) {
        if (currentCount == numberOfPlanks) {
            setOfLengths.add(currentLength);
            return;
        }
        calculateLengths(shorterPlank, longerPlank, currentCount + 1, currentLength + shorterPlank, numberOfPlanks, setOfLengths);
        calculateLengths(shorterPlank, longerPlank, currentCount + 1, currentLength + longerPlank, numberOfPlanks, setOfLengths);
    }

    private void solveWithNRuntime(int shorterPlank, int longerPlank, int numberOfPlanks) {
        int shorterPlankCount = 0;
        int longerPlankCount = 5;
        List<Integer> setOfLengths = new ArrayList<>();
        while (shorterPlankCount <= numberOfPlanks && longerPlankCount >= 0) {
            setOfLengths.add((shorterPlankCount * shorterPlank) + (longerPlankCount * longerPlank));
            shorterPlankCount++;
            longerPlankCount--;
        }
        System.out.println("Possible variations of length: ");
        for (Integer i : setOfLengths) {
            System.out.println("Length: " + i);
        }
    }
}