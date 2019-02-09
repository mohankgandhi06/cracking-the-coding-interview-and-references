package z.reference.topCoder;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class COneDShortestPath {

    public int[] numbers;
    public int[][] adjacencyMatrix;
    public Set<Integer> set;
    public int[][] lengthAndPreviousIndexAndVertex;

    public COneDShortestPath(int[] numbers, int[][] adjacencyMatrix) {
        this.numbers = numbers;
        this.adjacencyMatrix = adjacencyMatrix;
        this.set = new HashSet<>();
        this.lengthAndPreviousIndexAndVertex = new int[ numbers.length ][ 3 ];
        //first dimension [] - Index of the vertices; second dimension [0] - Length; second dimension [1] - Previous vertex index
    }

    public static void main(String[] args) {
        int[] numbers = {25, 45, 30, 23, 50, 1, 6, 78, 90, 81, 100};
        int[][] adjacencyMatrix =
                {       /*       {25, 45, 30, 23, 50, 1, 6, 78, 90, 81, 100}        */
                        /*25*/   {0,  1,  1,  0,  0,  0, 0, 1,  0,  0,  0},
                        /*45*/   {1,  0,  1,  0,  0,  1, 0, 0,  0,  0,  0},
                        /*30*/   {1,  1,  0,  1,  1,  0, 0, 0,  0,  0,  0},
                        /*23*/   {0,  0,  1,  0,  0,  0, 0, 0,  1,  1,  0},
                        /*50*/   {0,  0,  1,  0,  0,  0, 1, 0,  0,  1,  1},
                        /*1*/    {0,  1,  0,  0,  0,  0, 0, 0,  0,  0,  0},
                        /*6*/    {0,  0,  0,  0,  1,  0, 0, 0,  0,  0,  0},
                        /*78*/   {1,  0,  0,  0,  0,  0, 0, 0,  0,  0,  0},
                        /*90*/   {0,  0,  0,  1,  0,  0, 0, 0,  0,  0,  0},
                        /*81*/   {0,  0,  0,  1,  1,  0, 0, 0,  0,  0,  1},
                        /*100*/  {0,  0,  0,  0,  1,  0, 0, 0,  0,  1,  0}
                };
        COneDShortestPath game = new COneDShortestPath(numbers, adjacencyMatrix);
        int startIndex = 8;
        int endIndex = 5;
        int resultIndex = game.solve(startIndex, endIndex);
        if (resultIndex != -1) {
            System.out.println("Shortest Path (No. of vertex between) " + startIndex + " and " + endIndex + " is " + game.lengthAndPreviousIndexAndVertex[ resultIndex ][ 0 ]);
        } else {
            System.out.println("No Path Exists between " + startIndex + " and " + endIndex);
        }
        System.out.println();
        game.showArray();
        game.showChoices(resultIndex);
    }

    private int solve(int startIndex, int endIndex) {
        this.lengthAndPreviousIndexAndVertex[ 0 ][ 0 ] = 0;
        this.lengthAndPreviousIndexAndVertex[ 0 ][ 1 ] = -1;
        this.lengthAndPreviousIndexAndVertex[ 0 ][ 2 ] = this.numbers[ startIndex ];
        this.set.add(this.numbers[ startIndex ]);
        int counter = 1;
        Queue<VertexAndPreviousIndex> queue = new LinkedList<>();//This Queue contains only the Index from the this.numbers array
        for (int i = 0; i < adjacencyMatrix[ startIndex ].length; i++) {
            if (this.adjacencyMatrix[ startIndex ][ i ] == 1) {
                if (this.set.add(this.numbers[ i ])) {
                    VertexAndPreviousIndex forOffer = new VertexAndPreviousIndex();
                    forOffer.vertexIndex = i;
                    forOffer.previousIndex = 0;
                    queue.offer(forOffer);
                }
            }
        }
        while (!queue.isEmpty()) {
            VertexAndPreviousIndex index = queue.poll();
            //Check if the destination is reached.
            // If so Populate the this.lengthAndPrevious... and break
            // else Populate and continue
            this.lengthAndPreviousIndexAndVertex[ counter ][ 0 ] = this.lengthAndPreviousIndexAndVertex[ index.previousIndex ][ 0 ] + 1;
            this.lengthAndPreviousIndexAndVertex[ counter ][ 1 ] = index.previousIndex;
            this.lengthAndPreviousIndexAndVertex[ counter ][ 2 ] = this.numbers[ index.vertexIndex ];
            if (index.vertexIndex == endIndex) {
                return counter;
            }

            //Get the current vertices's adjacent and add to the queue if not already visited
            for (int i = 0; i < adjacencyMatrix[ index.vertexIndex ].length; i++) {
                if (this.adjacencyMatrix[ index.vertexIndex ][ i ] == 1) {
                    if (this.set.add(this.numbers[ i ])) {
                        VertexAndPreviousIndex forOffer = new VertexAndPreviousIndex();
                        forOffer.vertexIndex = i;
                        forOffer.previousIndex = counter;
                        queue.offer(forOffer);
                    }
                }
            }
            counter++;
        }
        return -1;
    }

    private void showChoices(int index) {
        System.out.println();
        System.out.println("Choices Taken to reach the goal");
        int ind = index;
        while (ind != -1) {
            System.out.println("Choice: " + this.lengthAndPreviousIndexAndVertex[ ind ][ 2 ]);
            ind = this.lengthAndPreviousIndexAndVertex[ ind ][ 1 ];
        }
    }

    private void showArray() {
        for (int i = 0; i < this.lengthAndPreviousIndexAndVertex.length; i++) {
            if ((i / 10) >= 1)
                System.out.println(i + "   " + this.lengthAndPreviousIndexAndVertex[ i ][ 0 ] + "    " + this.lengthAndPreviousIndexAndVertex[ i ][ 1 ] + "   " + "(" + this.lengthAndPreviousIndexAndVertex[ i ][ 2 ] + ")");
            else
                System.out.println(i + "    " + this.lengthAndPreviousIndexAndVertex[ i ][ 0 ] + "    " + this.lengthAndPreviousIndexAndVertex[ i ][ 1 ] + "   " + "(" + this.lengthAndPreviousIndexAndVertex[ i ][ 2 ] + ")");
        }
    }
}

class VertexAndPreviousIndex {
    public int vertexIndex;
    public int previousIndex;
}