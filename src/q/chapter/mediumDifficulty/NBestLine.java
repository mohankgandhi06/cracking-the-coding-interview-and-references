package q.chapter.mediumDifficulty;

import java.util.*;

public class NBestLine {

    HashMap<Integer, List<Point>> slopeAndPoints;

    public static void main(String[] args) {
        /*Point aPoint = new Point(1, 3);
        Point bPoint = new Point(2, 4);
        Point cPoint = new Point(3, 6);
        Point dPoint = new Point(7, 0);
        Point ePoint = new Point(8, 6);
        Point fPoint = new Point(5, 4);
        Point gPoint = new Point(3, 8);
        Point hPoint = new Point(1, 5);
        Point iPoint = new Point(1, 7);
        Point jPoint = new Point(3, 7);*/

        Point aPoint = new Point(1, 3);
        Point bPoint = new Point(2, 4);
        Point cPoint = new Point(3, 6);
        Point dPoint = new Point(7, 0);
        Point ePoint = new Point(8, 6);
        Point fPoint = new Point(5, 4);
        Point gPoint = new Point(3, 8);
        Point hPoint = new Point(1, 5);
        Point iPoint = new Point(1, 7);
        Point jPoint = new Point(1, 9);

        /*Point aPoint = new Point(1, 3);
        Point bPoint = new Point(2, 3);
        Point cPoint = new Point(3, 3);
        Point dPoint = new Point(7, 0);
        Point ePoint = new Point(8, 6);
        Point fPoint = new Point(4, 3);
        Point gPoint = new Point(3, 8);
        Point hPoint = new Point(1, 5);
        Point iPoint = new Point(1, 7);
        Point jPoint = new Point(3, 7);*/

        List<Point> points = new ArrayList<>();
        points.add(aPoint);
        points.add(bPoint);
        points.add(cPoint);
        points.add(dPoint);
        points.add(ePoint);
        points.add(fPoint);
        points.add(gPoint);
        points.add(hPoint);
        points.add(iPoint);
        points.add(jPoint);

        NBestLine bestLine = new NBestLine();
        Set<Point> bestLinePoints = bestLine.findBestLine(points);
        System.out.println("\n---------------------------------------------------------");
        System.out.println("Best Line can be drawn using the following points: ");
        for (Point point : bestLinePoints) {
            System.out.print("(" + point.x + ", " + point.y + ") ");
        }
        System.out.println();
    }

    private Set<Point> findBestLine(List<Point> points) {
        /* Iterate through the list of points and take slope and intercept between two points for all the points. i.e. all the combination of points.
         * At each point use a hashmap <Key, Set<Point>> to group the points which have same slope and y intercept and finally determine the best line
         * by iterating the map and getting the set with the greatest length */
        HashMap<String, List<Set<Point>>> map = new HashMap<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Line line = new Line(points.get(i), points.get(j));
                StringBuilder key = new StringBuilder();
                key.append(line.slope).append(" ").append(line.yintercept);
                if (!map.containsKey(key.toString())) {
                    List<Set<Point>> list = new ArrayList<>();
                    Set<Point> matchingPoints = new HashSet<>();
                    matchingPoints.add(points.get(i));
                    matchingPoints.add(points.get(j));
                    list.add(matchingPoints);
                    map.put(key.toString(), list);
                } else {
                    if (map.get(key.toString()).contains(points.get(i))) {
                        System.out.println("Already point is present: " + points.get(i).x + ", " + points.get(i).y);
                    } else {
                        add(line, map, key, points, i);
                    }
                    if (map.get(key.toString()).contains(points.get(j))) {
                        System.out.println("Already point is present: " + points.get(j).x + ", " + points.get(j).y);
                    } else {
                        add(line, map, key, points, j);
                    }
                }
            }
        }
        System.out.println("Lines and their slopes and points are listed below: ");
        Set<Point> bestLine = new HashSet<>();
        int bestLineSize = 0;
        for (Map.Entry<String, List<Set<Point>>> entry : map.entrySet()) {
            System.out.println("\nLine: slope and y-intercept : " + entry.getKey());
            System.out.println("Points: ");
            for (Set<Point> set : entry.getValue()) {
                if (set.size() > bestLineSize) {
                    bestLineSize = set.size();
                    bestLine = set;
                }
                for (Point point : set) {
                    System.out.print("(" + point.x + ", " + point.y + ") ");
                }
                System.out.println();
            }
        }
        return bestLine;
    }

    private void add(Line line, HashMap<String, List<Set<Point>>> map, StringBuilder key, List<Point> points, int i) {
        if (Double.isInfinite(line.slope)) {
            boolean added = false;
            for (Set<Point> set : map.get(key.toString())) {
                if (set.iterator().hasNext()) {
                    Point point = set.iterator().next();
                    if (point.x == points.get(i).x) {
                        set.add(points.get(i));
                        added = true;
                        break;
                    }
                }
            }
            if (!added) {
                Set<Point> pointSet = new HashSet<>();
                pointSet.add(points.get(i));
                map.get(key.toString()).add(pointSet);
            }
        } else {
            map.get(key.toString()).get(0).add(points.get(i));
        }
    }
}