package z.reference.topCoder;

import java.util.ArrayList;
import java.util.List;

public class ITwoDChessMetric {

    public Location[][] board;
    public int size;//Board Size
    public int[] start;//x-axis, y-axis
    public int[] finish;//x-axis, y-axis
    public int numMoves;//Total Moves
    public List<Position> positions;//Queue where the places which has been visited and are valid are stored
    public ArrayList<KnightKingMoves> moves;

    public enum KnightKingMoves {
        KING_TOP_LEFT(-1, -1), KING_TOP_CENTRE(-1, 0), KING_TOP_RIGHT(-1, 1),
        KING_MIDDLE_LEFT(0, -1), KING_MIDDLE_RIGHT(0, 1),
        KING_BOTTOM_LEFT(1, -1), KING_BOTTOM_CENTRE(1, 0), KING_BOTTOM_RIGHT(1, 1),
        KINGHT_TOP_LEFT(-2, -1), KNIGHT_TOP_RIGHT(-2, 1), KNIGHT_LEFT_TOP(-1, -2), KNIGHT_LEFT_BOTTOM(1, -2),
        KNIGHT_RIGHT_TOP(-1, 2), KNIGHT_RIGHT_BOTTOM(1, 2), KNIGHT_BOTTOM_LEFT(2, -1), KNIGHT_BOTTOM_RIGHT(2, 1);

        public int row;
        public int column;

        KnightKingMoves(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    public ITwoDChessMetric(int size, int[] start, int[] finish, int numMoves) {
        this.board = new Location[ size ][ size ];
        buildBoard(this.board);
        this.size = size;
        this.start = start;
        this.finish = finish;
        this.numMoves = numMoves;
        this.positions = new ArrayList<>();
        this.moves = new ArrayList<>();
        buildMoves(this.moves);
    }

    public static void main(String[] args) {
        /* Make Moves by moving it into the queue each time the next position.
         * Once we are going to make the final move erase the count present in
         * the end position and then make the final move */
        /* Queue is required for each iteration of the move... we can count down the moves and stop when it reaches 1
         * then perform the */
        /* TODO Here we need to check if the queue during the final move has to be ignored for the end position alone */

        /*int size = 8;
        int[] start = {2, 3};
        int[] finish = {6, 7};
        int numMoves = 5;*/

        /*int size = 3;
        int[] start = {0, 0};
        int[] finish = {1, 0};
        int numMoves = 1;*/

        /*int size = 3;
        int[] start = {0, 0};
        int[] finish = {1, 2};
        int numMoves = 1;*/

        /*int size = 3;
        int[] start = {0, 0};
        int[] finish = {2, 2};
        int numMoves = 1;*/

        /*int size = 3;
        int[] start = {0, 0};
        int[] finish = {0, 0};
        int numMoves = 2;*/

        /*int size = 100;
        int[] start = {0, 0};
        int[] finish = {0, 99};
        int numMoves = 50;*/

        /*int size = 13;
        int[] start = {3, 7};
        int[] finish = {11, 5};
        int numMoves = 4;*/

        /*int size = 3;
        int[] start = {0, 0};
        int[] finish = {2, 2};
        int numMoves = 20;*/

        int size = 5;
        int[] start = {3, 1};
        int[] finish = {1, 4};
        int numMoves = 3;

        /*int size = 10;
        int[] start = {5, 5};
        int[] finish = {9, 9};
        int numMoves = 4;*/

        ITwoDChessMetric game = new ITwoDChessMetric(size, start, finish, numMoves);
        /*game.showBoard();
        game.solve();
        game.showBoard();*/

        System.out.println("Paths: " + game.paths(size, start, finish, numMoves));
    }

    public long howMany(int n, int[] start, int[] end, int num) {
        int[] dx = new int[]{1, 0, -1, 0, 1, -1, -1, 1, -2, -2, -1, -1, 1, 1, 2, 2};
        int[] dy = new int[]{0, 1, 0, -1, 1, 1, -1, -1, -1, 1, -2, 2, -2, 2, -1, 1};

        long ways[][] = new long[ n ][ n ];
        ways[ start[ 0 ] ][ start[ 1 ] ] = 1;
        for (int i = 0; i < 3; i++) {
            long tmp[][] = new long[ n ][ n ];
            //STEP: Copying the ways matrix to temporary matrix
            for (int ia = 0; ia < n; ia++)
                for (int ib = 0; ib < n; ib++)
                    tmp[ ia ][ ib ] = ways[ ia ][ ib ];
            //STEP: Reinitialize the ways matrix for the next iteration of moves
            for (int ia = 0; ia < n; ia++)
                for (int ib = 0; ib < n; ib++)
                    ways[ ia ][ ib ] = 0;
            //STEP: Iterate through every matrix and determine how many pieces will be able to reach its position
            for (int ia = 0; ia < n; ia++) {
                for (int ib = 0; ib < n; ib++) {
                    for (int dir = 0; dir < dx.length; dir++) {
                        int nx = ia + dx[ dir ], ny = ib + dy[ dir ];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                            ways[ nx ][ ny ] += tmp[ ia ][ ib ];
                            //Here we are adding the paths of the position from where we are making the move to
                            // current location. since the current location [nx, ny] can be reached by coming from
                            // all the possible paths from where it is arriving.
                        }
                    }
                }
            }
            System.out.println();
            System.out.println("Ways Board");
            showBoard(ways);

            System.out.println();

            System.out.println("Temp Board");
            showBoard(tmp);
        }
        return ways[ end[ 0 ] ][ end[ 1 ] ];
    }

    public long paths(int n, int[] start, int[] finish, int numMoves) {
        int[][] ways = new int[ n ][ n ];
        ways[ start[ 0 ] ][ start[ 1 ] ] = 1;
        for (int iteration = 0; iteration < numMoves; iteration++) {
            //STEP: copy over the ways array to a temporary array since we are going to iterate everything
            // from the ways array so we need to re-initialize to 0
            int[][] temp = new int[ n ][ n ];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    temp[ i ][ j ] = ways[ i ][ j ];
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ways[ i ][ j ] = 0;
                }
            }

            for (int row = 0; row < ways.length; row++) {
                for (int column = 0; column < ways[ 0 ].length; column++) {
                    for (int moves = 0; moves < this.moves.size(); moves++) {
                        int newX = row + this.moves.get(moves).row;
                        int newY = column + this.moves.get(moves).column;
                        if (newX >= 0 && newX < n && newY >= 0 && newY < n) {
                            ways[ newX ][ newY ] += temp[ row ][ column ];
                        }
                    }
                }
            }
        }
        return ways[ finish[ 0 ] ][ finish[ 1 ] ];
    }

    private void solve() {
        //IMPORTANT NOTE: This solve method will work but the process time will be very slow,
        // since the linked list(which was used earlier to the current array list)
        // will be increasing the size and copying the values internally each time there is a change.
        int movesLeft = this.numMoves;
        int[] currentPosition = start;
        this.positions.add(new Position(start[ 0 ], start[ 1 ]));
        while (movesLeft > 1) {
            List<Position> currentQueue = new ArrayList<>();
            while (!this.positions.isEmpty()) {
                Position curPos = this.positions.get(this.positions.size() - 1);
                this.positions.remove(this.positions.size() - 1);
                currentPosition[ 0 ] = curPos.x;
                currentPosition[ 1 ] = curPos.y;
                int count = 0;
                for (int movesToMake = 0; movesToMake < moves.size(); movesToMake++) {
                    KnightKingMoves current = moves.get(movesToMake);
                    if (((currentPosition[ 0 ] + current.row) >= 0 && (currentPosition[ 0 ] + current.row) < this.board.length)
                            && ((currentPosition[ 1 ] + current.column) >= 0 && (currentPosition[ 1 ] + current.column) < this.board[ 0 ].length)) {
                        /*if (movesLeft == 2 && (currentPosition[ 0 ] + current.row) == finish[ 0 ] && (currentPosition[ 1 ] + current.column) == finish[ 1 ]) {
                            continue;
                        } else {*/
                        /*if (!this.board[ currentPosition[ 0 ] + current.row ][ currentPosition[ 1 ] + current.column ].visited) {*/
                        count++;
                        this.board[ currentPosition[ 0 ] + current.row ][ currentPosition[ 1 ] + current.column ].paths += 1;
                        currentQueue.add(new Position(currentPosition[ 0 ] + current.row, currentPosition[ 1 ] + current.column));
                        /*}*/
                        /*}*/
                    }
                }
                this.board[ currentPosition[ 0 ] ][ currentPosition[ 1 ] ].paths += count;
                this.board[ currentPosition[ 0 ] ][ currentPosition[ 1 ] ].visited = true;
            }
            showBoard();
            this.positions = currentQueue;
            movesLeft--;
        }
        /* Last Move erase the finish position and go through the queue last time */
        this.board[ finish[ 0 ] ][ finish[ 1 ] ].paths = 0;
        this.board[ finish[ 0 ] ][ finish[ 1 ] ].visited = false;
        while (!this.positions.isEmpty()) {
            Position curPos = this.positions.get(this.positions.size() - 1);
            currentPosition[ 0 ] = curPos.x;
            currentPosition[ 1 ] = curPos.y;
            for (int movesToMake = 0; movesToMake < moves.size(); movesToMake++) {
                KnightKingMoves current = moves.get(movesToMake);
                if (((currentPosition[ 0 ] + current.row) >= 0 && (currentPosition[ 0 ] + current.row) < this.board.length)
                        && ((currentPosition[ 1 ] + current.column) >= 0 && (currentPosition[ 1 ] + current.column) < this.board[ 0 ].length)) {
                    if (!this.board[ currentPosition[ 0 ] + current.row ][ currentPosition[ 1 ] + current.column ].visited) {
                        this.board[ currentPosition[ 0 ] + current.row ][ currentPosition[ 1 ] + current.column ].paths++;
                    }
                }
            }
        }

        System.out.println();
        System.out.println("********************************");
        System.out.println("No of Paths: " + this.board[ finish[ 0 ] ][ finish[ 1 ] ].paths);
        System.out.println("********************************");
        System.out.println();
    }

    private void showBoard() {
        System.out.println();
        System.out.println("Chess Board is shown as follows: ");
        for (int row = 0; row < this.board.length; row++) {
            for (int column = 0; column < this.board[ 0 ].length; column++) {
                System.out.print(this.board[ row ][ column ].paths + " ");
            }
            System.out.println();
        }
    }

    private void showBoard(long[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[ 0 ].length; column++) {
                System.out.print(board[ row ][ column ] + " ");
            }
            System.out.println();
        }
    }

    private void buildMoves(ArrayList<KnightKingMoves> moves) {
        moves.add(KnightKingMoves.KING_TOP_LEFT);
        moves.add(KnightKingMoves.KING_TOP_CENTRE);
        moves.add(KnightKingMoves.KING_TOP_RIGHT);
        moves.add(KnightKingMoves.KING_MIDDLE_LEFT);
        moves.add(KnightKingMoves.KING_MIDDLE_RIGHT);
        moves.add(KnightKingMoves.KING_BOTTOM_LEFT);
        moves.add(KnightKingMoves.KING_BOTTOM_CENTRE);
        moves.add(KnightKingMoves.KING_BOTTOM_RIGHT);
        moves.add(KnightKingMoves.KINGHT_TOP_LEFT);
        moves.add(KnightKingMoves.KNIGHT_TOP_RIGHT);
        moves.add(KnightKingMoves.KNIGHT_LEFT_TOP);
        moves.add(KnightKingMoves.KNIGHT_LEFT_BOTTOM);
        moves.add(KnightKingMoves.KNIGHT_RIGHT_TOP);
        moves.add(KnightKingMoves.KNIGHT_RIGHT_BOTTOM);
        moves.add(KnightKingMoves.KNIGHT_BOTTOM_LEFT);
        moves.add(KnightKingMoves.KNIGHT_BOTTOM_RIGHT);
    }

    private void buildBoard(Location[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[ 0 ].length; column++) {
                board[ row ][ column ] = new Location(0, false);
            }
        }
    }
}

class Position {
    protected int x;
    protected int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Location {
    protected long paths;
    protected boolean visited;

    public Location(long paths, boolean visited) {
        this.paths = paths;
        this.visited = visited;
    }
}