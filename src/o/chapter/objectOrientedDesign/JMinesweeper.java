package o.chapter.objectOrientedDesign;

import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class JMinesweeper {
    public static void main(String[] args) {
        Board board = new Board(8);

        Location[] locations = new Location[]{
                new Location(1, 1),
                new Location(5, 2),
                new Location(2, 3),
                new Location(1, 5),
                new Location(5, 5),
                new Location(0, 6)
        };

        board.placeBombs(locations);
        //board.showMap();
        System.out.println();
        board.showConcealedMap();

        board.startGame();
    }
}

class Board {

    Scanner scanner = new Scanner(System.in);
    public boolean gameover;
    public int remainingSquares;
    public int bombs;

    private enum Piece {
        BOMB(-1), FLAG(10), QUESTION(11);

        private int value;

        Piece(int value) {
            this.value = value;
        }
    }

    private Place[][] board;

    public Board(int size) {
        this.board = new Place[ size ][ size ];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[ 0 ].length; j++) {
                this.board[ i ][ j ] = new Place();
            }
        }
        this.remainingSquares = size * size;
    }

    public void placeBombs(Location[] locations) {
        for (Location l : locations) {
            plantBomb(l);
            incrementSurrounding(l);
        }
        //System.out.println("Bombs: " + locations.length);
        this.bombs = locations.length;
    }

    private void plantBomb(Location location) {
        this.board[ location.xcoordinate ][ location.ycoordinate ].value = Piece.BOMB.value;
    }

    private void incrementSurrounding(Location location) {
        for (int i = location.xcoordinate - 1; i <= location.xcoordinate + 1; i++) {
            if (i >= 0 && i <= board[ 0 ].length - 1) {
                for (int j = location.ycoordinate - 1; j <= location.ycoordinate + 1; j++) {
                    if (j >= 0 && j <= board.length - 1) {
                        if ((i == location.xcoordinate && j == location.ycoordinate) ||
                                this.board[ i ][ j ].value == -1) {
                            continue;
                        }
                        this.board[ i ][ j ].value += 1;
                    }
                }
            }
        }
    }

    public void showMap() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[ 0 ].length; j++) {
                if (board[ i ][ j ].value == -1) {
                    System.out.print(board[ i ][ j ].value + "  ");
                } else {
                    System.out.print(" " + board[ i ][ j ].value + "  ");
                }
            }
            System.out.println();
        }
    }

    public void showConcealedMap() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[ 0 ].length; j++) {
                if (board[ i ][ j ].revealed) {
                    if (board[ i ][ j ].value == -1) {
                        System.out.print(board[ i ][ j ].value + "  ");
                    } else {
                        System.out.print(" " + board[ i ][ j ].value + "  ");
                    }
                } else {
                    System.out.print(" *  ");
                }
            }
            System.out.println();
        }
    }

    public void startGame() {
        //As of now for convenience we declared it like this we can create a base case for the game over or win condition
        while (!gameover && remainingSquares != bombs) {
            System.out.println();
            System.out.println("Remaining Locations: " + remainingSquares);
            System.out.println("Please select a location in the following format (1,2). Please type in the same format");
            String locationChosen = scanner.nextLine();
            String[] characters = locationChosen.split(",");
            Location location = new Location(Integer.parseInt(characters[ 0 ]), Integer.parseInt(characters[ 1 ]));
            ConcurrentLinkedQueue<Location> locationQueue = new ConcurrentLinkedQueue<Location>();
            locationQueue.offer(location);
            if (location.xcoordinate >= 0 && location.xcoordinate <= board.length && location.ycoordinate >= 0 && location.ycoordinate <= board.length) {
                revealLocations(location, locationQueue);
            }
        }
        if (!gameover) {
            System.out.println("You have won !!!! ");
        }
    }

    private void revealLocations(Location location, ConcurrentLinkedQueue<Location> locationConcurrentLinkedQueue) {
        if (board[ location.xcoordinate ][ location.ycoordinate ].value == -1) {
            System.out.println("Game Over. You have Landed in the Bomb Location");
            this.showMap();
            gameover = true;
            return;
        } else {
            while (!locationConcurrentLinkedQueue.isEmpty()) {
                Location queueLocation = locationConcurrentLinkedQueue.poll();
                if (board[ queueLocation.xcoordinate ][ queueLocation.ycoordinate ].value == 0) {
                    if (!board[ queueLocation.xcoordinate ][ queueLocation.ycoordinate ].visited && !board[ queueLocation.xcoordinate ][ queueLocation.ycoordinate ].revealed) {
                        remainingSquares--;
                    }
                    board[ queueLocation.xcoordinate ][ queueLocation.ycoordinate ].revealed = true;
                    board[ queueLocation.xcoordinate ][ queueLocation.ycoordinate ].visited = true;
                    loadSurroundingInQueue(queueLocation, locationConcurrentLinkedQueue);
                } else {
                    if (!board[ queueLocation.xcoordinate ][ queueLocation.ycoordinate ].visited && !board[ queueLocation.xcoordinate ][ queueLocation.ycoordinate ].revealed) {
                        remainingSquares--;
                    }
                    board[ queueLocation.xcoordinate ][ queueLocation.ycoordinate ].revealed = true;
                    board[ queueLocation.xcoordinate ][ queueLocation.ycoordinate ].visited = true;
                }
            }
            this.showConcealedMap();
        }
    }

    private void loadSurroundingInQueue(Location location, ConcurrentLinkedQueue<Location> locationConcurrentLinkedQueue) {
        for (int i = location.xcoordinate - 1; i <= location.xcoordinate + 1; i++) {
            if (i >= 0 && i <= board[ 0 ].length - 1) {
                for (int j = location.ycoordinate - 1; j <= location.ycoordinate + 1; j++) {
                    if (j >= 0 && j <= board.length - 1) {
                        if ((i == location.xcoordinate && j == location.ycoordinate) ||
                                this.board[ i ][ j ].visited || this.board[ i ][ j ].revealed) {
                            continue;
                        }
                        Location loc = new Location(i, j);
                        locationConcurrentLinkedQueue.offer(loc);
                    }
                }
            }
        }
    }
}

class Location {
    protected int xcoordinate;
    protected int ycoordinate;

    public Location(int xcoordinate, int ycoordinate) {
        this.xcoordinate = xcoordinate;
        this.ycoordinate = ycoordinate;
    }
}

class Place {
    public int value;
    public boolean revealed;
    public boolean visited;
}