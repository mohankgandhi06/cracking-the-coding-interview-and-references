package z.reference.dynamicProgramming;

public class IBackTrackHamiltonian {

    public int[][] adjacencyMatrix;
    public int vertexCount;
    public Vertex[] vertices;
    public int[] hamiltonianPath;

    public IBackTrackHamiltonian(int[][] adjacencyMatrix) {
        /* In this constructor we are using pre defined characters from a - z
         * if the vertex count is greater than 26 then the character repeats
         * but their index will be different */
        this.adjacencyMatrix = adjacencyMatrix;
        this.vertexCount = adjacencyMatrix.length;
        this.vertices = new Vertex[ adjacencyMatrix.length ];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            Vertex vertex = new Vertex();
            vertex.index = i;
            vertex.data = (char) (((('a' + i) % 97) % 26) + 97);
            vertices[ i ] = vertex;
        }
        this.hamiltonianPath = new int[ adjacencyMatrix.length ];
        this.hamiltonianPath[ 0 ] = 0;
    }

    public IBackTrackHamiltonian(int[][] adjacencyMatrix, int vertexCount, Vertex[] vertices) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.vertexCount = vertexCount;
        this.vertices = vertices;
    }

    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
                /*{0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}*/
                {0, 1, 1, 1, 0, 0},
                {1, 0, 1, 0, 1, 0},
                {1, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 0, 1},
                {0, 1, 1, 1, 1, 1}
        };
        IBackTrackHamiltonian game = new IBackTrackHamiltonian(adjacencyMatrix);
        if (game.solve(1)) {
            game.showPath();
        } else {
            System.out.println("No path exists...");
        }
    }

    public boolean solve(int position) {
        if (position == vertexCount) {
            if (adjacencyMatrix[ 0 ][ hamiltonianPath[ position - 1 ] ] == 1) {
                //adjacencyMatrix's 0th term points to the first vertex's position and the
                // hamiltonianPath's position-1 will point to the last index position
                return true;
            }
            return false;
        }
        for (int vertexIndex = 1; vertexIndex < vertexCount; vertexIndex++) {
            if (isValid(vertexIndex, position)) {
                hamiltonianPath[ position ] = vertexIndex;

                if (solve(position + 1)) {
                    return true;
                }

                //Backtracking Step
            }
        }
        return false;
    }

    public void showPath() {
        for (int i : hamiltonianPath) {
            System.out.println("Index: " + vertices[ i ].index + " Data: " + vertices[ i ].data);
        }
        System.out.println("Index: " + vertices[ 0 ].index + " Data: " + vertices[ 0 ].data);
    }

    public boolean isValid(int vertexIndex, int position) {
        //It should be an adjacent vertex
        if (adjacencyMatrix[ hamiltonianPath[ position - 1 ] ][ vertexIndex ] == 0) {
            return false;
        }
        //There should not be a duplicate
        for (int i = 0; i < position; i++) {
            if (hamiltonianPath[ i ] == vertexIndex) {
                return false;
            }
        }
        return true;
    }
}

class Vertex {
    /* Vertex class has been used here which can be made to hold varied Objects inside
     * This is done in order to make the hamiltonian problem extendable instead of plain
     * array index positions */
    public int index;
    public char data;
}