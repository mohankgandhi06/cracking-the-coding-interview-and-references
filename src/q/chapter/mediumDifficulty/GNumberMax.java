package q.chapter.mediumDifficulty;

public class GNumberMax {
    public static void main(String[] args) {
        GNumberMax max = new GNumberMax();
        max.findGreatest(23, 45);
        System.out.println();
        max.findGreatest(-2147483647, -2147483648);
        System.out.println();
        max.findGreatest(48, 49);
    }

    private void findGreatest(int numberOne, int numberTwo) {
        int result = numberTwo - numberOne;
        int msb = (result & (1 << 31)) >>> 31;
        int greatest = numberOne * msb + numberTwo * (1 ^ msb);
        System.out.println("Greatest of " + numberOne + " and " + numberTwo + " is:\n" + greatest);
    }
}