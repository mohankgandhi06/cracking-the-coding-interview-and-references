package q.chapter.mediumDifficulty;

public class MBisectSquares {
    public static void main(String[] args) {
        MBisectSquares bisectSquares = new MBisectSquares();
        Square squareA = new Square(new Point(2, 8), new Point(4, 8), new Point(2, 6), new Point(4, 6));
        Square squareB = new Square(new Point(3, 5), new Point(6, 5), new Point(3, 2), new Point(6, 2));

        BisectLine bisectLine = bisectSquares.bisectEqually(squareA, squareB);
        System.out.println("Bisect Line: ");
        System.out.println("Point One: X:" + bisectLine.startingPoint.x + " Y: " + bisectLine.startingPoint.y);
        System.out.println("Point Two: X:" + bisectLine.endingPoint.x + " Y: " + bisectLine.endingPoint.y);


        squareA = new Square(new Point(3, 5), new Point(6, 5), new Point(3, 2), new Point(6, 2));
        squareB = new Square(new Point(8, 8), new Point(10, 8), new Point(8, 6), new Point(10, 6));

        bisectLine = bisectSquares.bisectEqually(squareA, squareB);
        System.out.println("\nBisect Line: ");
        System.out.println("Point One: X:" + bisectLine.startingPoint.x + " Y: " + bisectLine.startingPoint.y);
        System.out.println("Point Two: X:" + bisectLine.endingPoint.x + " Y: " + bisectLine.endingPoint.y);

        squareA = new Square(new Point(3, 5), new Point(6, 5), new Point(3, 2), new Point(6, 2));
        squareB = new Square(new Point(7, 8), new Point(9, 8), new Point(7, 6), new Point(9, 6));

        bisectLine = bisectSquares.bisectEqually(squareA, squareB);
        System.out.println("\nBisect Line: ");
        System.out.println("Point One: X:" + bisectLine.startingPoint.x + " Y: " + bisectLine.startingPoint.y);
        System.out.println("Point Two: X:" + bisectLine.endingPoint.x + " Y: " + bisectLine.endingPoint.y);

        squareA = new Square(new Point(3, 8), new Point(6, 8), new Point(3, 5), new Point(6, 5));
        squareB = new Square(new Point(4, 4), new Point(6, 4), new Point(4, 2), new Point(6, 2));

        bisectLine = bisectSquares.bisectEqually(squareA, squareB);
        System.out.println("\nBisect Line: ");
        System.out.println("Point One: X:" + bisectLine.startingPoint.x + " Y: " + bisectLine.startingPoint.y);
        System.out.println("Point Two: X:" + bisectLine.endingPoint.x + " Y: " + bisectLine.endingPoint.y);
    }

    private BisectLine bisectEqually(Square squareA, Square squareB) {
        if (squareA.topLeft.x > squareB.topLeft.x) swap(squareA, squareB);
        /*if (squareA.topRight.y < squareB.topLeft.y) swap(squareA, squareB);*/
        Point midPointA = getMidPoint(squareA);
        Point midPointB = getMidPoint(squareB);
        Line bisectLine = new Line(midPointA, midPointB);
        double xInterceptTop = 0;
        double yInterceptTop = 0;
        double xInterceptBottom = 0;
        double yInterceptBottom = 0;
        if (Math.abs(bisectLine.slope) >= 1) {//Bisect at the Diagonal
            Line topLine;
            Line bottomLine;
            if (bisectLine.slope > 0) {//Positive Slope
                topLine = new Line(squareA.bottomLeft, squareA.bottomRight);
                bottomLine = new Line(squareB.topLeft, squareB.topRight);
            } else {
                topLine = new Line(squareA.topLeft, squareA.topRight);
                bottomLine = new Line(squareB.bottomLeft, squareB.bottomRight);
            }
            xInterceptTop = (topLine.yintercept - bisectLine.yintercept) / (bisectLine.slope - topLine.slope);
            yInterceptTop = xInterceptTop * bisectLine.slope + bisectLine.yintercept;
            xInterceptBottom = (bottomLine.yintercept - bisectLine.yintercept) / (bisectLine.slope - bottomLine.slope);
            yInterceptBottom = xInterceptBottom * bisectLine.slope + bisectLine.yintercept;
        } else if (Math.abs(bisectLine.slope) < 1) {
            int z = 0;
            /*Line leftLine = new Line(squareA.topLeft, squareA.bottomLeft);
            Line rightLine = new Line(squareB.topRight, squareB.bottomRight);
            xInterceptTop = (leftLine.yintercept - bisectLine.yintercept) / (bisectLine.slope - leftLine.slope);
            yInterceptTop = xInterceptTop * bisectLine.slope + bisectLine.yintercept;
            xInterceptBottom = (rightLine.yintercept - bisectLine.yintercept) / (bisectLine.slope - rightLine.slope);
            yInterceptBottom = xInterceptBottom * bisectLine.slope + bisectLine.yintercept;*/
            xInterceptTop = squareA.topLeft.x;
            yInterceptTop = xInterceptTop * bisectLine.slope + bisectLine.yintercept;
            xInterceptBottom = squareB.topRight.x;
            yInterceptBottom = xInterceptBottom * bisectLine.slope + bisectLine.yintercept;
        }

        Point intersectionTop = new Point(xInterceptTop, yInterceptTop);
        Point intersectionBottom = new Point(xInterceptBottom, yInterceptBottom);
        return new BisectLine(intersectionTop, intersectionBottom);
    }

    private void swap(Square squareA, Square squareB) {
        Square tempSquare = new Square(squareB);
        transfer(squareB, squareA);
        transfer(squareA, tempSquare);
    }

    private void transfer(Square transferTo, Square transferFrom) {
        transferTo.topLeft.x = transferFrom.topLeft.x;
        transferTo.topLeft.y = transferFrom.topLeft.y;

        transferTo.topRight.x = transferFrom.topRight.x;
        transferTo.topRight.y = transferFrom.topRight.y;

        transferTo.bottomLeft.x = transferFrom.bottomLeft.x;
        transferTo.bottomLeft.y = transferFrom.bottomLeft.y;

        transferTo.bottomRight.x = transferFrom.bottomRight.x;
        transferTo.bottomRight.y = transferFrom.bottomRight.y;
    }

    private Point getMidPoint(Square square) {
        double squareXCoordinate = (square.topLeft.x + square.topRight.x) / 2;
        double squareYCoordinate = (square.topLeft.y + square.bottomLeft.y) / 2;
        Point midPoint = new Point(squareXCoordinate, squareYCoordinate);
        return midPoint;
    }
}

class Square {
    public Point topLeft;
    public Point topRight;
    public Point bottomLeft;
    public Point bottomRight;

    public Square(Point topLeft, Point topRight, Point bottomLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }

    public Square(Square oldSquare) {
        this.topLeft = new Point(oldSquare.topLeft.x, oldSquare.topLeft.y);
        this.topRight = new Point(oldSquare.topRight.x, oldSquare.topRight.y);
        this.bottomLeft = new Point(oldSquare.bottomLeft.x, oldSquare.bottomLeft.y);
        this.bottomRight = new Point(oldSquare.bottomRight.x, oldSquare.bottomRight.y);
    }
}

class BisectLine {
    public Point startingPoint;
    public Point endingPoint;

    public BisectLine(Point startingPoint, Point endingPoint) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }
}