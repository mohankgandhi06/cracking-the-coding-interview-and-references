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
        breadth = 9;
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
        matrix[ 0 ][ 8 ] = 0;
        //matrix[ 6 ][ 7 ] = 0;
        showMatrix(matrix, length, breadth);
        usingArray();
        showMatrix(matrix, length, breadth);
    }

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
                matrix[ zeroCoordinate.getxCoordinate() ][ i ] = 0;
            }
            for (int j = 0; j < breadth; j++) {
                matrix[ j ][ zeroCoordinate.getyCoordinate() ] = 0;
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