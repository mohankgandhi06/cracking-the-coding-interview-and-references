package p.chapter.dynamicProgramming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MStackOfBoxes {
    public static void main(String[] args) {
        MStackOfBoxes game = new MStackOfBoxes();
        List<Box> boxes = new ArrayList<>();
        boxes.add(new Box(6, 4, 5));
        boxes.add(new Box(10, 5, 8));
        boxes.add(new Box(12, 4, 4));
        boxes.add(new Box(6, 6, 3));
        boxes.add(new Box(3, 3, 3));
        boxes.add(new Box(4, 2, 2));
        boxes.add(new Box(2, 1, 4));
        boxes.add(new Box(2, 1, 2));
        boxes.add(new Box(7, 5, 4));
        boxes.add(new Box(7, 6, 7));
        boxes.add(new Box(9, 6, 5));
        boxes.add(new Box(8, 8, 7));
        game.solveWithBruteForce(boxes);
    }

    private void solveWithBruteForce(List<Box> boxes) {
        boxes.sort(Comparator.comparing(Box::getLength).thenComparing(Box::getBreadth).thenComparing(Box::getHeight).reversed());
        for (Box box : boxes) {
            System.out.println("Length: " + box.length + " Breadth: " + box.breadth + " Height: " + box.height);
        }
        int maxHeight = solve(boxes, 0, -1);
        System.out.println("\nMaximum Height which can be stacked using the boxes: " + maxHeight);
    }

    private int solve(List<Box> boxes, int currentIndex, int previousIndex) {
        if (currentIndex == boxes.size()) return 0;
        int include = 0;
        if (previousIndex == -1 || isPreviousBoxGreater(boxes, currentIndex, previousIndex)) {
            include = boxes.get(currentIndex).height + solve(boxes, currentIndex + 1, currentIndex);
        }
        int exclude = solve(boxes, currentIndex + 1, previousIndex);
        /*System.out.println(currentIndex+" Include: "+include+" Exclude: "+exclude);*/
        return Math.max(include, exclude);
    }

    private boolean isPreviousBoxGreater(List<Box> boxes, int currentIndex, int previousIndex) {
        if (boxes.get(previousIndex).length > boxes.get(currentIndex).length
                && boxes.get(previousIndex).breadth > boxes.get(currentIndex).breadth
                && boxes.get(previousIndex).height > boxes.get(currentIndex).height) {
            return true;
        }
        return false;
    }
}

class Box {
    public int length;
    public int breadth;
    public int height;

    public Box(int length, int breadth, int height) {
        this.length = length;
        this.breadth = breadth;
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public int getBreadth() {
        return breadth;
    }

    public int getHeight() {
        return height;
    }
}