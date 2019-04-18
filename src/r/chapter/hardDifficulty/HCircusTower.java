package r.chapter.hardDifficulty;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HCircusTower {
    public static void main(String[] args) {
        List<HeightAndWeight> heightAndWeights = new ArrayList<>();

        heightAndWeights.add(new HeightAndWeight(65, 100));
        heightAndWeights.add(new HeightAndWeight(70, 150));
        heightAndWeights.add(new HeightAndWeight(56, 90));
        heightAndWeights.add(new HeightAndWeight(75, 190));
        heightAndWeights.add(new HeightAndWeight(60, 95));
        heightAndWeights.add(new HeightAndWeight(68, 110));

        /*heightAndWeights.add(new HeightAndWeight(65, 100));
        heightAndWeights.add(new HeightAndWeight(70, 150));
        heightAndWeights.add(new HeightAndWeight(56, 90));
        heightAndWeights.add(new HeightAndWeight(75, 190));
        heightAndWeights.add(new HeightAndWeight(60, 95));
        heightAndWeights.add(new HeightAndWeight(68, 110));
        heightAndWeights.add(new HeightAndWeight(70, 50));
        heightAndWeights.add(new HeightAndWeight(73, 160));
        heightAndWeights.add(new HeightAndWeight(70, 60));
        heightAndWeights.add(new HeightAndWeight(78, 90));
        heightAndWeights.add(new HeightAndWeight(60, 65));
        heightAndWeights.add(new HeightAndWeight(60, 90));*/

        HCircusTower game = new HCircusTower();
        System.out.println("Maximum pyramid Height possible is: " + game.longestTower(heightAndWeights));
    }

    private int longestTower(List<HeightAndWeight> heightAndWeights) {
        heightAndWeights.sort(new HeightEndWeightSorter().reversed());
        System.out.println("Sorted based on Height and Weight");
        for (HeightAndWeight heightAndWeight : heightAndWeights) {
            System.out.println(heightAndWeight.getHeight() + " " + heightAndWeight.getWeight());
        }
        System.out.println();
        List<HeightAndWeight> pyramid = new ArrayList<>();
        int result = solve(heightAndWeights, 0, 0, pyramid);
        return result;
    }

    private int solve(List<HeightAndWeight> heightAndWeights, int currentIndex, int currentPyramidHeight, List<HeightAndWeight> pyramid) {
        if (currentIndex == heightAndWeights.size()) return currentPyramidHeight;
        int include = 0;
        /* INCLUDE */
        if (pyramid.size() == 0 ||
                (pyramid.get(pyramid.size() - 1).getHeight() > heightAndWeights.get(currentIndex).getHeight() &&
                        pyramid.get(pyramid.size() - 1).getWeight() > heightAndWeights.get(currentIndex).getWeight())) {/* Height and Weight are strictly greater than */
            List<HeightAndWeight> resultTemp = new ArrayList<>();
            resultTemp.add(new HeightAndWeight(heightAndWeights.get(currentIndex).getHeight(), heightAndWeights.get(currentIndex).getWeight()));
            include = solve(heightAndWeights, currentIndex + 1, currentPyramidHeight + 1, resultTemp);
        }
        /* EXCLUDE */
        int exclude = solve(heightAndWeights, currentIndex + 1, currentPyramidHeight, pyramid);
        return Math.max(include, exclude);
    }
}

class HeightAndWeight {
    private int height;
    private int weight;

    public HeightAndWeight(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

class HeightEndWeightSorter implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        HeightAndWeight objectOne = (HeightAndWeight) o1;
        HeightAndWeight objectTwo = (HeightAndWeight) o2;
        if (objectOne.getHeight() > objectTwo.getHeight()) {
            return 1;
        } else if (objectOne.getHeight() == objectTwo.getHeight()) {
            if (objectOne.getWeight() > objectTwo.getWeight()) {
                return 1;
            } else if (objectOne.getWeight() == objectTwo.getWeight()) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}