package i.chapter.bitManipulation;

public class FConversion {

    /* Conversion
    Question: Write a function to determine the number of bits you would need to flip to convert
    integer A to integer B

    Example
    Input:  29 (or: 11101), 15 (or: 01111)
    Output: 2 */

    public static void main(String[] args) {
        /*int a = 29;
        int b = 15;*/
        int a = Integer.parseInt("10001", 2);
        System.out.println("Integer A: " + Integer.toBinaryString(a));

        int b = Integer.parseInt("01111", 2);
        System.out.println("Integer B: " + Integer.toBinaryString(b));
        System.out.println("No of Flips Needed: " + conversion(a, b));
    }

    private static int conversion(int a, int b) {
        int c = a ^ b;
        int noOfFlipsNeeded = 0;
        char[] cArray = Integer.toBinaryString(c).toCharArray();
        /*System.out.println(Integer.toBinaryString(c));*/
        for (char z : cArray) {
            if (Integer.parseInt(String.valueOf(z), 2) == 1) {
                noOfFlipsNeeded++;
            }
        }
        return noOfFlipsNeeded;
    }
}
