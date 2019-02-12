package z.reference.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class UDynamicClosestPair {

    public Point[] points;
    public Point[] minimumPoint;
    public Point[] result;

    public UDynamicClosestPair(Point[] points) {
        this.points = points;
        this.minimumPoint = new Point[ 2 ];
        this.minimumPoint[ 0 ] = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.minimumPoint[ 1 ] = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.result = new Point[ 2 ];
    }

    public static void main(String[] args) {
        /*Point[] points = {new Point(1, 1), new Point(8, 1), new Point(2, 3), new Point(3, 0), new Point(3, 1)};*/

        /*Point[] points = {new Point(4, 4),
                new Point(-2, -2),
                new Point(-3, -4),
                new Point(-1, 3),
                new Point(2, 3),
                new Point(-4, 0),
                new Point(1, 1),
                new Point(-1, -1),
                new Point(3, -1),
                new Point(-4, 2),
                new Point(-2, 4)};*/

        Point[] points = {new Point(1, 0), new Point(1, 3), new Point(3, 1), new Point(2, 4), new Point(2, 6)};

        UDynamicClosestPair game = new UDynamicClosestPair(points);
        game.solve();
        game.showPoints();
    }

    private void solve() {
        /* STEP: sort into ascending order based on x co-ordinate */
        Arrays.sort(this.points, Comparator.comparing(Point::getX));
        this.result = findClosestPair(this.points, this.points.length);
    }

    private Point[] findClosestPair(Point[] points, int numOfPoints) {
        //Base Case: no of elements to be compared has to be less than 3. until then we keep splitting the Array
        if (numOfPoints <= 3) {
            return bruteForce(points, numOfPoints);
        }

        /* STEP: split into two partitions one on the left and one on the right */
        int middleIndex = numOfPoints / 2;
        Point middlePoint = points[ middleIndex ];

        /* STEP: find the minimum in each compartment */
        Point[] leftPoints = Arrays.copyOfRange(points, 0, middleIndex + 1);
        Point[] left = findClosestPair(leftPoints, leftPoints.length);

        Point[] rightPoints = Arrays.copyOfRange(points, middleIndex + 1, points.length);
        Point[] right = findClosestPair(rightPoints, rightPoints.length);

        /* STEP: find the minimum between two partitions */
        Point[] minimumOfLeftAndRight = findMinimumOfLeftAndRight(left, right);

        /* STEP: there can be a point in-between these two partitions i.e. one point on either
         * side which can be of a minimum when compared to the current minimum */
        /* Since we have sorted based on the x-axis we need to consider all the points which
         * cut across the partition */
        List<Point> midStripPointsList = new ArrayList<>();
        int j = 0;
        double minimumOfLeftAndRightDistance = distance(minimumOfLeftAndRight[ 0 ], minimumOfLeftAndRight[ 1 ]);
        for (int i = 0; i < numOfPoints; i++) {
            if (Math.abs(points[ i ].x - middlePoint.x) < minimumOfLeftAndRightDistance) {
                midStripPointsList.add(points[ i ]);
                j++;
            }
        }
        Point[] midStripPoints = new Point[ midStripPointsList.size() ];
        midStripPointsList.toArray(midStripPoints);

        Point[] minimumOfMiddleStrip = findMinimumInMiddleStrip(midStripPoints, middlePoint, minimumOfLeftAndRight);
        return findMinimumOfLeftAndRight(minimumOfLeftAndRight, minimumOfMiddleStrip);
    }

    private Point[] bruteForce(Point[] points, int numOfPoints) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < numOfPoints; ++i) {
            for (int j = i + 1; j < numOfPoints; ++j) {
                if (distance(points[ i ], points[ j ]) < min) {
                    min = distance(points[ i ], points[ j ]);
                    this.minimumPoint[ 0 ] = new Point(points[ i ].x, points[ i ].y);
                    this.minimumPoint[ 1 ] = new Point(points[ j ].x, points[ j ].y);
                }
            }
        }
        return this.minimumPoint;
    }

    private Point[] findMinimumInMiddleStrip(Point[] points, Point middlePoint, Point[] minimumOfLeftAndRight) {
        /* Setting up the minimum initial values */
        Point[] minimumPoint = new Point[]{minimumOfLeftAndRight[ 0 ], minimumOfLeftAndRight[ 1 ]};
        double min = distance(minimumOfLeftAndRight[ 0 ], minimumOfLeftAndRight[ 1 ]);

        /* Sorting the points based on Y co-ordinates */
        Arrays.sort(points, Comparator.comparing(Point::getY));

        // Pick all points one by one and try the next points till the difference
        // between y coordinates is smaller than d.
        // This is a proven fact that this loop runs at most 6 times
        for (int i = 0; i < points.length; ++i) {
            for (int j = i + 1; j < points.length && (points[ j ].y - points[ i ].y) < min; ++j) {
                //Here we are taking only the values where the points[j].y and points[i].y is lesser than current min value
                // owing to sorted previously jth element will be greater than the ith element
                if (distance(points[ i ], points[ j ]) < min) {
                    min = distance(points[ i ], points[ j ]);
                    minimumPoint = new Point[]{points[ i ], points[ j ]};
                }
            }
        }
        return minimumPoint;
    }

    private Point[] findMinimumOfLeftAndRight(Point[] left, Point[] right) {
        double leftDistance = distance(left[ 0 ], left[ 1 ]);
        double rightDistance = distance(right[ 0 ], right[ 1 ]);
        if (leftDistance < rightDistance) {
            return left;
        }
        return right;
    }

    private double distance(Point pointOne, Point pointTwo) {
        double x = Math.abs(pointOne.getX() - pointTwo.getX());
        double y = Math.abs(pointOne.getY() - pointTwo.getY());
        return Math.sqrt((x * x) + (y * y));
    }

    private void showPoints() {
        System.out.println("Distance: " + distance(this.result[ 0 ], this.result[ 1 ]));
        System.out.println("From: (" + this.result[ 0 ].x + ", " + this.result[ 0 ].y + ") To: (" + this.result[ 1 ].x + ", " + this.result[ 1 ].y + ")");
    }
}

class Point {
    protected int x;
    protected int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "x co-ordinate: " + x + " y co-ordinate: " + y;
    }
}