package e.chapter.stringArray;

import java.util.ArrayList;
import java.util.List;

public class HZeroMatrix {

    public static int[][] matrix;
    public static int length;
    public static int breadth;
    public static List<ZeroCoordinate> zeroCoordinateList = new ArrayList<ZeroCoordinate>();

    public static void main(String[] args) {
        length = 9;
        breadth = 8;
        int totalPlaces = 1;
        matrix = new int[ length ][ breadth ];
        for (int k = 0; k < length; k++) {
            for (int l = 0; l < breadth; l++) {
                matrix[ k ][ l ] = totalPlaces;
                totalPlaces++;
            }
        }
        matrix[ 1 ][ 3 ] = 0;
        matrix[ 6 ][ 3 ] = 0;
        //matrix[ 2 ][ 7 ] = 0;
        //matrix[ 0 ][ 4 ] = 0;
        matrix[ 6 ][ 7 ] = 0;
        showMatrix(matrix, length, breadth);
        //usingArray();
        usingInPlace();
        showMatrix(matrix, length, breadth);
    }

    /* Optimized Implementation */
    public static void usingInPlace() {
        /* First we check if the first row and the first column has any zero's
         * using 2 boolean values. So now we are going to use that first row and
         * column as identifier for the rest of the locations.
         * on completion of identification, we are first nullifying the row and columns
         * where the first row and column is zero. Finally we are checking the row and
         * column boolean values are true or false and act accordingly. */
        boolean rowHasZero = false;
        boolean columnHasZero = false;

        //BigO (n)
        for (int i = 0; i < matrix[ 0 ].length; i++) {//Row
            if (matrix[ 0 ][ i ] == 0) {
                rowHasZero = true;
            }
        }

        //BigO (n)
        for (int i = 0; i < matrix.length; i++) {//Column
            if (matrix[ i ][ 0 ] == 0) {
                columnHasZero = true;
            }
        }

        //BigO (n^2)
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[ 0 ].length; j++) {
                if (matrix[ i ][ j ] == 0) {
                    matrix[ i ][ 0 ] = 0;
                    matrix[ 0 ][ j ] = 0;
                }
            }
        }

        //Row Removal
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[ i ][ 0 ] == 0) {//If the row is zero then make entire row as zero
                nullThisRow(i);
            }
        }

        //Column Removal
        for (int i = 0; i < matrix[ 0 ].length; i++) {
            if (matrix[ 0 ][ i ] == 0) {//If the column is zero then make entire column as zero
                nullThisColumn(i);
            }
        }

        if (rowHasZero) nullThisRow(0);
        if (columnHasZero) nullThisColumn(0);
    }

    private static void nullThisRow(int rowIndex) {
        for (int i = 0; i < matrix[ 0 ].length; i++) {
            matrix[ rowIndex ][ i ] = 0;
        }
    }

    private static void nullThisColumn(int columnIndex) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[ i ][ columnIndex ] = 0;
        }
    }

    /* Earlier Implementation */
    public static void usingArray() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < breadth; j++) {
                if (matrix[ i ][ j ] == 0) {
                    ZeroCoordinate zeroCoordinate = new ZeroCoordinate(i, j);
                    zeroCoordinateList.add(zeroCoordinate);
                }
            }
        }
        for (ZeroCoordinate zeroCoordinate : zeroCoordinateList) {
            for (int i = 0; i < length; i++) {
                matrix[ i ][ zeroCoordinate.getyCoordinate() ] = 0;
            }
            for (int j = 0; j < breadth; j++) {
                matrix[ zeroCoordinate.getxCoordinate() ][ j ] = 0;
            }
        }
    }

    private static void showMatrix(int[][] matrix, int length, int breadth) {
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
}

class ZeroCoordinate {
    public int xCoordinate;
    public int yCoordinate;

    public ZeroCoordinate(int i, int j) {
        xCoordinate = i;
        yCoordinate = j;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }
}