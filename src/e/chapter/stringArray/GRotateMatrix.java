package e.chapter.stringArray;

public class GRotateMatrix {
    public static int[][] matrix;
    public static int length;
    public static int breadth;

    public static void main(String[] args) {
        length = 9;
        breadth = 9;
        int totalPlaces = 1;
        matrix = new int[ length ][ breadth ];
        for (int k = 0; k < length; k++) {
            for (int l = 0; l < breadth; l++) {
                matrix[ k ][ l ] = totalPlaces;
                totalPlaces++;
            }
        }
        showMatrix(matrix, length, breadth);
        rotateMatrix(0, length - 1);
        showMatrix(matrix, length, breadth);
    }

    public static void showMatrix(int[][] matrix, int length, int breadth) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < breadth; j++) {
                if ((matrix[ i ][ j ] / 10) >= 1) {
                    System.out.print(matrix[ i ][ j ] + "  ");
                } else {
                    System.out.print(matrix[ i ][ j ] + "   ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void rotateMatrix(int startPosition, int endPosition) {
        int tempOne = 0;
        int tempTwo = 0;
        while (startPosition < endPosition) {
            for (int i = startPosition; i < endPosition; i++) {
                tempOne = matrix[ i ][ endPosition ];
                matrix[ i ][ endPosition ] = matrix[ startPosition ][ i ];
                tempTwo = tempOne;
                tempOne = matrix[ endPosition ][ endPosition - (i-startPosition) ];
                matrix[ endPosition ][ endPosition - (i-startPosition) ] = tempTwo;
                tempTwo = tempOne;
                tempOne = matrix[ endPosition - (i-startPosition) ][ startPosition ];
                matrix[ endPosition - (i-startPosition) ][ startPosition ] = tempTwo;
                tempTwo = tempOne;
                matrix[ startPosition ][ i ] = tempTwo;
            }
            rotateMatrix(startPosition + 1, endPosition - 1);
            return;
        }
    }
}