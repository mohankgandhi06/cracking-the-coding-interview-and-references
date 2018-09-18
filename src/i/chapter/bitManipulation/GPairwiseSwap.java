package i.chapter.bitManipulation;

public class GPairwiseSwap {

    /*Pairwise Swap
    Question: Write a program to swap odd and eve bits in an integer with as few instructions as possible
    (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on)*/

    public static void main(String[] args) {
        int a = Integer.parseInt("111101001", 2);
        System.out.println("Result: " + Integer.toBinaryString(pairwiseSwap(a)));
    }

    private static int pairwiseSwap(int a) {
        int result = 0;
        int integerLength = Integer.toBinaryString(a).length();
        StringBuilder maskRightShift = new StringBuilder();
        StringBuilder maskLeftShift = new StringBuilder();
        for (int i = 0; i < integerLength; i++) {
            if (i % 2 == 0) {
                maskRightShift.insert(0, "0");
                maskLeftShift.insert(0, "1");
            } else {
                maskRightShift.insert(0, "1");
                maskLeftShift.insert(0, "0");
            }
        }
        int integerRightShift = a & Integer.parseInt(maskRightShift.toString(), 2);
        integerRightShift = integerRightShift >> 1;
        int integerLeftShift = a & Integer.parseInt(maskLeftShift.toString(), 2);
        integerLeftShift = integerLeftShift << 1;
        result = integerRightShift | integerLeftShift;
        return result;
    }
}
