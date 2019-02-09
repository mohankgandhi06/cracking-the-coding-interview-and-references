package z.reference.dynamicProgramming;

public class BHouseBuilding {
    public static void main(String[] args) {
        System.out.println("Head Recursion");
        buildLayerWithTailRecursion(4, 12);
        System.out.println();
        System.out.println("Tail Recursion");
        buildLayerWithHeadRecursion(3, 9);
    }

    public static void buildLayerWithHeadRecursion(int height, int breadth) {
        if (height == 0) return;
        buildLayerWithHeadRecursion(height - 1, breadth);
        Layer layer = new Layer(breadth);
        System.out.print(height+" ");
        for (Brick brick : layer.bricks) {
            System.out.print(brick.content + " ");
        }
        System.out.println();
    }

    public static void buildLayerWithTailRecursion(int height, int breadth) {
        if (height == 0) return;
        Layer layer = new Layer(breadth);
        System.out.print(height+" ");
        for (Brick brick : layer.bricks) {
            System.out.print(brick.content + " ");
        }
        System.out.println();
        buildLayerWithTailRecursion(height - 1, breadth);
    }
}

class Layer {
    protected Brick[] bricks;
    protected int breadth;

    /* TODO If we are going to leave gap for the door and window we want to give some variable for handling it */
    public Layer(int breadth) {
        this.bricks = new Brick[ breadth ];
        this.breadth = breadth;
        for (int i = 0; i < breadth; i++) {
            this.bricks[ i ] = new Brick();
        }
    }
}

class Brick {
    protected String content;

    public Brick() {
        this.content = "#";
    }
}