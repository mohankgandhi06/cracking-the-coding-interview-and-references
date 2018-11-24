package n.chapter.sortingAndSearching;

public class ISortedMatrixSearch {
    /* Sorted Matrix Search
     * Question: Given an M x N matrix in which each row and each column is sorted in
     * ascending order, write a method to find an element. */

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18},
                {19, 20, 21, 22, 23, 24}
        };
        int elementToFind = -10;
        matrixSearch(matrix, elementToFind);
    }

    private static void matrixSearch(int[][] matrix, int elementToFind) {
        int rowStart = 0;
        int rowEnd = matrix.length - 1;//Row's Count
        int columnStart = 0;
        int columnEnd = matrix[ 0 ].length - 1;//Column's Count
        boolean found = search(matrix, elementToFind, rowStart, rowEnd, columnStart, columnEnd);
        if (!found) {
            System.out.println("Value " + elementToFind + " is not present in the matrix");
        }
    }

    private static boolean search(int[][] matrix, int elementToFind, int rowStart, int rowEnd, int columnStart, int columnEnd) {
        if (rowStart <= rowEnd && columnStart <= columnEnd) {
            int[] rowAndColumn = calculatePostition(matrix[ 0 ].length, rowStart, rowEnd, columnStart, columnEnd);
            int rowMid = rowAndColumn[ 0 ];
            int columnMid = rowAndColumn[ 1 ];
            if (rowMid >= 0 && columnMid >= 0) {
                if (matrix[ rowMid ][ columnMid ] == elementToFind) {
                    System.out.println("The Value " + elementToFind + " is at the following Location in the Matrix: \nRow " + rowMid + " Column " + columnMid);
                    return true;
                } else if (matrix[ rowMid ][ columnMid ] < elementToFind) {//Towards Right i.e. higher values than the current
                    if (columnMid == matrix[ 0 ].length - 1) {
                        return search(matrix, elementToFind, rowMid + 1, rowEnd, 0, columnEnd);
                    } else {
                        return search(matrix, elementToFind, rowMid, rowEnd, columnMid + 1, columnEnd);
                    }
                } else {
                    if (columnMid == 0) {
                        return search(matrix, elementToFind, rowStart, rowMid - 1, columnStart, matrix[ 0 ].length - 1);
                    } else {
                        return search(matrix, elementToFind, rowStart, rowMid, columnStart, columnMid - 1);
                    }
                }
            }
        }
        return false;
    }

    private static int[] calculatePostition(int length, int rowStart, int rowEnd, int columnStart, int columnEnd) {
        int totalNumber = ((length - columnStart) + (columnEnd + 1) + ((rowEnd - (rowStart + 1)) * length));
        int midOfTotal = totalNumber / 2;
        int columnPosition = 0;
        if (midOfTotal > 5) {
            columnPosition = columnStart + ((midOfTotal - 1) % 6);
        } else {
            columnPosition = columnStart + (midOfTotal % 6);
        }
        int rowPosition = rowStart + (midOfTotal - (length - 1 - columnStart)) / length;
        return new int[]{rowPosition, columnPosition};
    }
}