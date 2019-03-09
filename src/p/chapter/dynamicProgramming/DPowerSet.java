package p.chapter.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class DPowerSet {
    public static void main(String[] args) {
        DPowerSet game = new DPowerSet();
        int[] input = new int[]{4, 5, 6, 8};
        List<List<Integer>> listOfLists = new ArrayList<>();
        game.solve(input, 0, new ArrayList<>(), listOfLists);
        System.out.println("Power Set");
        for (List<Integer> list : listOfLists) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }

        input = new int[]{4, 5, 6, 7, 1, 2, 1, 0};
        listOfLists = new ArrayList<>();
        game.solve(input, 0, new ArrayList<>(), listOfLists);
        System.out.println("Power Set");
        for (List<Integer> list : listOfLists) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    private List<List<Integer>> solve(int[] input, int currentIndex, List<Integer> currentList, List<List<Integer>> listOfLists) {
        if (currentIndex == input.length) {
            listOfLists.add(currentList);
            return listOfLists;
        }
        /* Include the currentIndex */
        List<Integer> include = new ArrayList<>(currentList);
        include.add(input[ currentIndex ]);
        solve(input, currentIndex + 1, include, listOfLists);
        /* Exclude the currentIndex */
        solve(input, currentIndex + 1, currentList, listOfLists);
        return listOfLists;
    }
}