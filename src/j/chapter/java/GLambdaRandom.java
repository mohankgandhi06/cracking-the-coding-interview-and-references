package j.chapter.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GLambdaRandom {
    /* Lambda Random:
     * Question: Using Lambda expressions, write a function a function
     * List<Integer> getRandomSubset(List<Integer> list) that returns a random subset of arbitrary size.
     * All subsets (including the empty set) should be equally likely to be chosen.*/
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(
                100, 200, 300, 303, 305, 400, 401, 500, 607, 609, 800, 801, 809, 904, 906, 911, 958, 999
        );
        RandomSubset randomSubset = (iList) -> {
            int subsetSize = new Random().nextInt((integerList.size() - 1) + 1);
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < subsetSize; i++) {
                subset.add(iList.get(i));
            }
            return subset;
        };

        System.out.println("Subset is as follows: ");
        List<Integer> result = randomSubset.getRandomSubset(integerList);
        for (int resultItem : result) {
            System.out.println(resultItem);
        }
    }
}

interface RandomSubset {
    public List<Integer> getRandomSubset(List<Integer> list);
}
