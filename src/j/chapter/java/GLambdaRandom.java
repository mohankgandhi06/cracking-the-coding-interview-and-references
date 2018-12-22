package j.chapter.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

        /* Optimal Implementations */
        Random random = new Random();
        int randomSize = random.nextInt(integerList.size());
        List<Integer> subset = integerList.stream().limit(randomSize).collect(Collectors.toList());
        System.out.println("Size: " + randomSize);
        System.out.println(subset);

        /* Using Predicate - Here we are taking the elements of the subset by flipping
         * the coin */
        getRandomSubset(integerList);
    }

    public static List<Integer> getRandomSubset(List<Integer> integerList) {
        Random booleanRandom = new Random();
        Predicate<Object> flipCoin = object -> {
            return booleanRandom.nextBoolean();
        };
        List<Integer> subset = integerList.stream().filter(flipCoin).collect(Collectors.toList());
        return subset;
    }
}

interface RandomSubset {
    public List<Integer> getRandomSubset(List<Integer> list);
}
