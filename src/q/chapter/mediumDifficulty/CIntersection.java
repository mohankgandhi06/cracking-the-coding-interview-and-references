package q.chapter.mediumDifficulty;

public class CIntersection {
    public static void main(String[] args) {
        CIntersection intersectionFinder = new CIntersection();
        Point oneStart = new Point(1, 6);
        Point oneEnd = new Point(6, 1);

        /*Point twoStart = new Point(3, 3);*/
        /*Point twoStart = new Point(4, 0);*/
        Point twoStart = new Point(3, 5);
        Point twoEnd = new Point(5, 8);

        Point intersection = intersectionFinder.intersection(oneStart, oneEnd, twoStart, twoEnd);
        if (intersection != null) {
            System.out.println("Intersection: ");
            System.out.println("X: " + intersection.x + " Y:" + intersection.y);
        } else {
            System.out.println("No Intersection found");
        }
    }

    public Point intersection(Point oneStart, Point oneEnd, Point twoStart, Point twoEnd) {
        /* Soring the points based on x position */
        if (oneStart.x > oneEnd.x) swap(oneStart, oneEnd);
        if (twoStart.x > twoEnd.x) swap(twoStart, twoEnd);
        if (oneStart.x > twoStart.x) {
            swap(oneStart, twoStart);
            swap(oneEnd, twoEnd);
        }
        Line oneLine = new Line(oneStart, oneEnd);
        Line twoLine = new Line(twoStart, twoEnd);

        if (oneLine.slope == twoLine.slope) {
            if (oneLine.yintercept == twoLine.yintercept && isBetween(oneStart, twoStart, oneEnd)) {
                return twoStart;
            }
            return null;
        }

        double xIntercept = (twoLine.yintercept - oneLine.yintercept) / (oneLine.slope - twoLine.slope);
        double yIntercept = xIntercept * oneLine.slope + oneLine.yintercept;
        Point intersection = new Point(xIntercept, yIntercept);
        if (isBetween(oneStart, intersection, oneEnd) && isBetween(twoStart, intersection, twoEnd)) {
            return intersection;
        }
        return null;
    }

    public boolean isBetween(double start, double middle, double end) {
        if (start > end) {
            return end <= middle && middle <= start;
        } else {
            return start <= middle && middle <= end;
        }
    }

    public boolean isBetween(Point start, Point middle, Point end) {
        return (isBetween(start.x, middle.x, end.x) && isBetween(start.y, middle.y, end.y));
    }

    public void swap(Point a, Point b) {
        double x = a.x;
        double y = a.y;
        a.setLocation(b.x, b.y);
        b.setLocation(x, y);
    }
}

class Line {
    public double slope;
    public double yintercept;

    public Line(Point start, Point end) {
        double deltaX = end.x - start.x;
        double deltaY = end.y - start.y;
        this.slope = deltaY / deltaX;
        this.yintercept = end.y - (end.x * slope);
    }
}

class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
}