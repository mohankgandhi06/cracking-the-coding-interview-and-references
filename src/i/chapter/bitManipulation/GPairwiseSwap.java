package i.chapter.bitManipulation;

public class GPairwiseSwap {

    /*Pairwise Swap
    Question: Write a program to swap odd and even bits in an integer with as few instructions as possible
    (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on)*/

    public static void main(String[] args) {
        int a = Integer.parseInt("111101001", 2);
        System.out.println("Result: " + Integer.toBinaryString(pairwiseSwap(a)));
    }

    /* Optimal Implementations */
    private static int pairwiseSwapOptimal(int a) {
        /* The logic here is that we need to get the odd bits separately using a mask or odd 1's
        and then even digits and then move them by 1 bit in opposite direction. here we need to
        shift the right side logically >>> since the negative integer will mean that each digit
        will become one after that like we do for -1 >> i
        Here we are assuming the bit as 32 bit integer */
        return (((a & 0xAAAAAAAA) >>> 1) | ((a & 0x55555555) << 1));
    }

    /* Earlier Implementations */
    private static int pairwiseSwap(int a) {
        /* The Lines of code are more than what a optimal solution offers */
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
        integerRightShift = integerRightShift >> 1;//Here we are using arithmetic right shift which is wrong
        // as the negative numbers will carry the one o each shift
        int integerLeftShift = a & Integer.parseInt(maskLeftShift.toString(), 2);
        integerLeftShift = integerLeftShift << 1;
        result = integerRightShift | integerLeftShift;
        return result;
    }
}