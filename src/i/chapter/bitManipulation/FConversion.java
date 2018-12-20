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

    /* Optimal Implementations */
    private static int conversionOptimal(int a, int b) {
        int count = 0;
        for (int z = a ^ b; z > 0; z = z >> 1) {//Moving each digit to the right
            count += (z & 1);
        }
        return count;
    }

    private static int coversionTricky(int a, int b) {
        //z & (z-1) depends on the fact it will be like the power of 2 program which we encountered
        // earlier refer EDebugger program in the repository
        // if we are having 1000100 on our xor then there are two 1's now we just take bit wise and
        // of the number and it's previous number
        // 1000100
        // 1000011
        // 1000000 - One bit is cleared and the value is not equal to zero so count is increased.
        // here when the count is incremented plays a part. on first xor we are checking if it is
        // 0 if not then we are incrementing and also removing one digit before next process. so
        // when the last digit is processed on the previous iteration the value would have been
        // already incremented to include that change as well.
        int count = 0;
        for (int z = a ^ b; z != 0; z = z & (z - 1)) {
            count++;
        }
        return count;
    }

    /* Earlier Implementations */
    private static int conversion(int a, int b) {
        /* Even though the implementations are using the xor operation like what a
         * solution was, it was not implemented fully using bitwise operations, i.e.,
         * string are used. */
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
