package p.chapter.dynamicProgramming;

public class FTowerOfHanoi {
    public static void main(String[] args) {
        FTowerOfHanoi game = new FTowerOfHanoi();
        int n = 6;
        System.out.println("No. of Discs: " + n);
        game.solveBruteForce(n, "A", "B", "C");
    }

    private void solveBruteForce(int n, String fromRod, String middleRod, String toRod) {
        if (n == 1) {/* Final Base case i.e. only one rod of weight 1 is present which needs to be moved */
            System.out.println("Disc: " + n + " move from: " + fromRod + " to: " + toRod);
            return;
        }
        solveBruteForce(n - 1, fromRod, toRod, middleRod);
        System.out.println("Disc: " + n + " move from: " + fromRod + " to: " + toRod);
        solveBruteForce(n - 1, middleRod, fromRod, toRod);
    }
}