package z.reference.dynamicProgramming;

public class JBackTrackColoring {

    public int[][] adjacencyMatrix;
    public int numberOfColors;
    public int numberOfVertices;
    public Color[] verticesColor;

    public JBackTrackColoring(int[][] adjacencyMatrix, int numberOfColors) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.numberOfColors = numberOfColors;
        this.numberOfVertices = adjacencyMatrix.length;
        this.verticesColor = new Color[ adjacencyMatrix.length ];
    }

    public enum Color {
        BLACK(0), WHITE(1), RED(2), BLUE(3), GREEN(3), YELLOW(4), ORANGE(5);
        private int code;
        private String color;

        Color(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
                /*{0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}*/
                {0, 1, 0, 1, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 1, 0},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 1, 0}
        };
        JBackTrackColoring game = new JBackTrackColoring(adjacencyMatrix, 3);
        game.showAdjacency();
        if (game.solve(0)) {
            game.showVertices();
        } else {
            System.out.println("No Solution...");
        }
    }

    private boolean solve(int vertexIndex) {
        if (vertexIndex == numberOfVertices) {
            return true;
        }
        for (int colorIndex = 0; colorIndex < numberOfColors; colorIndex++) {
            if (isValid(vertexIndex, colorIndex)) {
                if (getColor(colorIndex) != null) {
                    verticesColor[ vertexIndex ] = getColor(colorIndex);
                }
                if (solve(vertexIndex + 1)) {
                    return true;
                }
                //Backtrack Step
            }
        }
        return false;
    }

    private boolean isValid(int vertexIndex, int colorIndex) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[ vertexIndex ][ i ] == 1 && verticesColor[ i ] != null && verticesColor[ i ].code == colorIndex) {
                return false;
            }
        }
        return true;
    }

    public Color getColor(int code) {
        for (Color c : Color.values()) {
            if (c.code == code) {
                return c;
            }
        }
        return null;
    }

    private void showAdjacency() {
        System.out.println("Adjacency Matrix");
        System.out.print("     ");
        for (int k = 0; k < adjacencyMatrix.length; k++) {
            System.out.print(k + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            System.out.print("   " + i + " ");
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                System.out.print(adjacencyMatrix[ i ][ j ] + " ");
            }
            System.out.print("\n");
        }
    }

    private void showVertices() {
        for (int i = 0; i < verticesColor.length; i++) {
            System.out.println("Vertex: " + i + " Color: " + verticesColor[ i ].name());
        }
    }
}