package z.reference.topCoder;

import java.util.BitSet;

public class JTwoDCheapestPath {

    public int[][] adjacencyMatrix;
    public int[] weight;//Amount which will be deducted if the vertex is included
    public int[] path;//Index of the final path
    public int money;
    Result bestPath;

    public JTwoDCheapestPath(int[][] adjacencyMatrix, int[] weight, int money) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.weight = weight;
        this.money = money;
        this.path = new int[ this.weight.length + 1 ];
        this.bestPath = new Result(0, this.path, 9999999);
    }

    public static void main(String[] args) {
        /*int[] weight = {10, 5, 15, 12, 13, 4, 2, 20, 6, 8, 10, 7, 2, 8};*/

        int[] weight = {10, 5, 15, 12, 13, 4, 2, 2, 6, 8, 10, 7, 2, 8};

        /*int[] weight = {10, 5, 15, 12, 13, 4, 2, 2, 99, 8, 10, 99, 2, 99};*/
        int start = 1;//Start Vertex's index
        int end = 12;//End Vertex's index
        int[][] adjacencyMatrix = {
                //    {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13},
                /*0*/ {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0},
                /*1*/ {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,  0,  0,  0},
                /*2*/ {1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0,  0,  0,  0},
                /*3*/ {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0},

                /*4*/ {0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0,  0,  0,  0},
                /*5*/ {0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0,  1,  0,  1},
                /*6*/ {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,  0,  0,  0},
                /*7*/ {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1,  1,  0,  0},

                /*8*/ {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0,  0,  1,  0},
                /*9*/ {0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0,  0,  0,  0},
                /*10*/{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,  0,  0,  0},
                /*11*/{0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0,  0,  1,  0},

                /*12*/{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,  1,  0,  1},
                /*13*/{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,  0,  1,  0}
        };
        int money = 100;
        JTwoDCheapestPath game = new JTwoDCheapestPath(adjacencyMatrix, weight, money);
        game.solve(start, end);
        game.showChoices();
    }

    private void solve(int start, int end) {
        this.path[ 0 ] = start;
        BitSet visited = new BitSet(this.weight.length);
        visited.set(start, true);
        Result result = new Result(this.money, this.path, 0);
        solve(visited, start, result, end);
    }

    private void solve(BitSet visited, int currentVertexIndex, Result result, int goalVertexIndex) {
        if (result.money < 0) return;
        //Goal Reached - Include the path if this is Max for money left and Min for steps taken
        if (currentVertexIndex == goalVertexIndex) {
            result.reached = true;
            this.bestPath = betterOfTheTwo(result);
            /*System.out.println();
            System.out.println("*************************************");
            System.out.println("!!! Goal Reached: ");
            System.out.print(" Length:  " + this.bestPath.indexFilledCurrently);
            System.out.print(" Path: ");
            for (int i : this.bestPath.path) {
                System.out.print(i + " ");
            }
            System.out.println(" money left " + this.bestPath.money);
            System.out.println("*************************************");
            System.out.println();*/
            return;
        }

        //Gather the adjacency matrix
        int[] adjacent = this.adjacencyMatrix[ currentVertexIndex ];
        //Just for Debugging
        /*for (int i : adjacent) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();*/

        for (int i = 0; i < adjacent.length; i++) {
            if (!result.reached) {
                BitSet tempVisited = (BitSet) visited.clone();
                Result tempResult = new Result(result);
                /*System.out.println("Visited: " + tempVisited + " money left: " + tempResult.money);*/
                if (adjacent[ i ] == 1 && !tempVisited.get(i)) {
                    //marking as visited
                    tempVisited.set(i, true);
                    //Populating the result object
                    tempResult.path[ tempResult.indexFilledCurrently + 1 ] = i;
                    tempResult.indexFilledCurrently = tempResult.indexFilledCurrently + 1;
                    tempResult.money = tempResult.money - this.weight[ i ];
                    //Recursive only for unvisited adjacent vertex
                    solve(tempVisited, i, tempResult, goalVertexIndex);
                }
            }
        }
    }

    private Result betterOfTheTwo(Result path) {
        /*System.out.println("Current Path: Length " + path.indexFilledCurrently + " Money: " + path.money);
        System.out.println("Best Path: Length " + this.bestPath.indexFilledCurrently + " Money: " + this.bestPath.money);*/
        if (path.indexFilledCurrently <= this.bestPath.indexFilledCurrently && path.money > this.bestPath.money) {
            return path;
        }
        return this.bestPath;
    }

    private void showChoices() {
        if (this.bestPath.indexFilledCurrently == 9999999) {
            System.out.println("No path available");
            return;
        }
        System.out.print("Path: ");
        for (int i : this.bestPath.path) {
            if (i == 0) break;
            System.out.print(i + " ");
        }
        System.out.println(" Money left " + this.bestPath.money);
    }
}

class Result {
    protected int money;
    protected int[] path;
    protected int indexFilledCurrently;
    protected boolean reached;

    public Result(int money, int[] path, int indexFilledCurrently) {
        this.money = money;
        this.path = path;
        this.indexFilledCurrently = indexFilledCurrently;
        this.reached = false;
    }

    public Result(Result result) {
        this.money = result.money;
        this.path = result.path.clone();
        this.indexFilledCurrently = result.indexFilledCurrently;
        this.reached = false;
    }
}