package z.reference.topCoder;

import java.util.HashSet;

public class HTwoDAvoidRoad {

    private int width;
    private int height;
    private long[][] matrix;
    private HashSet<Block> blocks;

    public HTwoDAvoidRoad(int width, int height, String[] blocks) {
        this.width = width;
        this.height = height;
        this.matrix = new long[ height + 1 ][ width + 1 ];
        this.blocks = processBlocks(blocks);
        /*for (Block block : this.blocks) {
            System.out.println("x1: " + block.x1Height + " y1: " + block.y1Width + " x2: " + block.x2Height + " y2: " + block.y2Width);
        }
        System.out.println();*/
    }

    public static void main(String[] args) {
        /*int width = 3;
        int height = 3;
        *//*String[] blocks = {"0 0 1 0", "1 2 2 2", "1 1 2 1"};*//*
        String[] blocks = {"0 0 1 0", "1 1 2 1"};*/

        int width = 6;
        int height = 6;
        String[] blocks = {"0 0 0 1", "6 6 5 6"};

        /*int width = 35;
        int height = 31;
        String[] blocks = {};*/

        /*int width = 2;
        int height = 2;
        String[] blocks = {"0 0 1 0", "1 2 2 2", "1 1 2 1"};*/

        HTwoDAvoidRoad game = new HTwoDAvoidRoad(width, height, blocks);
        System.out.println("Total Path: " + game.solve());
        game.showMatrix();
    }

    private HashSet<Block> processBlocks(String[] blocks) {
        HashSet<Block> set = new HashSet();
        for (String block : blocks) {
            String[] array = block.split(" ");
            set.add(new Block(Integer.parseInt(array[ 0 ]), Integer.parseInt(array[ 1 ]), Integer.parseInt(array[ 2 ]), Integer.parseInt(array[ 3 ])));
            set.add(new Block(Integer.parseInt(array[ 2 ]), Integer.parseInt(array[ 3 ]), Integer.parseInt(array[ 0 ]), Integer.parseInt(array[ 1 ])));
        }
        return set;
    }

    private long solve() {
        this.matrix[ 0 ][ 0 ] = 1;
        for (int height = 0; height < this.matrix.length; height++) {
            for (int width = 0; width < this.matrix[ 0 ].length; width++) {
                if (height == 0 && width == 0) continue;
                long bottom = (!outOfBound(height - 1) && !blocked(height - 1, width, height, width)) ? this.matrix[ height - 1 ][ width ] : 0;
                long left = (!outOfBound(width - 1) && !blocked(height, width - 1, height, width)) ? this.matrix[ height ][ width - 1 ] : 0;
                this.matrix[ height ][ width ] = bottom + left;
            }
        }
        return this.matrix[ this.matrix.length - 1 ][ this.matrix[ 0 ].length - 1 ];
    }

    private boolean outOfBound(int axis) {
        return axis <= -1;
    }

    private boolean blocked(int x1Height, int y1Width, int x2Height, int y2Width) {
        for (Block block : this.blocks) {
            if (x1Height == block.x1Height && y1Width == block.y1Width && x2Height == block.x2Height && y2Width == block.y2Width) {
                return true;
            }
        }
        return false;
        /*return this.blocks.contains(new Block(x1Height, y1Width, x2Height, y2Width));*/
    }

    private void showMatrix() {
        System.out.println();
        System.out.println("Matrix formed by the Dynamic Programming is as follows: ");
        for (int i = this.matrix.length - 1; i >= 0; i--) {
            for (int j = 0; j < this.matrix[ 0 ].length; j++) {
                int indexSpace = 15;
                long indexSpaceValue = this.matrix[ i ][ j ];
                while (indexSpaceValue / 10 > 0) {
                    indexSpace--;
                    indexSpaceValue = indexSpaceValue / 10;
                }
                StringBuilder indexSpaceSB = new StringBuilder();
                for (int space = 0; space < indexSpace; space++) {
                    indexSpaceSB.append(" ");
                }

                System.out.print(this.matrix[ i ][ j ] + indexSpaceSB.toString());
            }
            System.out.println();
        }
    }
}

class Block {
    protected int x1Height;
    protected int y1Width;
    protected int x2Height;
    protected int y2Width;

    public Block(int x1Height, int y1Width, int x2Height, int y2Width) {
        this.x1Height = x1Height;
        this.y1Width = y1Width;
        this.x2Height = x2Height;
        this.y2Width = y2Width;
    }
}