package i.chapter.bitManipulation;

public class DNextNumber {

    /* Next Number
    Question: Given a positive integer, print the next smallest and the next largest number
    that have the same number of 1 bits in their binary representation.*/

    public static void main(String[] args) {
        /*int largestNumber = nextLargestNumber(Integer.parseInt("10111", 2));*/
        /*int smallestNumber = nextSmallestNumber(Integer.parseInt("10001111100000011", 2));*/

        String input = "10001111100000011";
        int largestNumber = nextLargestNumber(Integer.parseInt(input, 2));
        System.out.println("Next Largest Number: " + Integer.toBinaryString(largestNumber));
        int smallestNumber = nextSmallestNumber(Integer.parseInt(input, 2));
        System.out.println("Next Smallest Number: " + Integer.toBinaryString(smallestNumber));
    }

    private static int nextLargestNumber(int input) {
        /*
        1) Get the non-trailing zero and then flip the digit.
        2) Move all the 1's until one 1 is removed in the process
        3) While finding the non-trailing zero count the no. of 1's

        1111111 i.e. ~0
        1111110 i.e. ~0 and << number of the digits
        0000001 i.e. ~(~0 and << number of the digits)
        (~mask & input) == 0
        */
        int temp = input;
        int i = 0;
        int mask = (~0) << 1;
        int noOfOnes = 0;
        while (temp != 0) {
            if ((input & (1 << i)) != 0) {//If the digit is 1
                noOfOnes++;
            } else {
                if ((input & (1 << i)) == 0 && ((~mask & input) != 0)) {//Non-trail Zero obtained
                    /*System.out.println("Non-Trail Zero's position: " + i);*/
                    mask = (~0) << i++;
                    break;
                }
            }
            mask = (~0) << i++;
            temp = temp >> 1;
        }
        /*System.out.println("No. of 1's before the non-trailing zero: " + noOfOnes);*/
        int movableDigit = ~mask & input;
        int modifiedInput = mask & input;
        modifiedInput = modifiedInput + (1 << i - 1);
        int afterRightShift = movableDigit >> (i - noOfOnes);
        /*System.out.println("Digits that needs to be right shifted: " + Integer.toBinaryString(movableDigit));
        System.out.println("Modified Input i.e. the value needs to be added after right shift: " + Integer.toBinaryString(modifiedInput));
        System.out.println("After right shift: " + Integer.toBinaryString(afterRightShift));*/
        return modifiedInput + afterRightShift;
    }

    private static int nextSmallestNumber(int input) {
        int temp = input;
        int q = 1;
        int mask = (~0) << q++;
        int noOfZeros = 0;
        while (temp != 0) {
            if ((temp & 1) == 0) {//If the digit is 0
                noOfZeros++;
            } else {
                if ((input & ~mask) != ~mask) {
                    /*System.out.println("Non-Trail One's position: " + (q - 1));
                    System.out.println("No. Of Zeros: " + noOfZeros);*/
                    break;
                }
            }
            mask = (~0) << q++;
            temp = temp >> 1;
        }
        int modifiedInput = mask & input;
        int noOfOnes = (q - 1) - noOfZeros;
        int number = (~(~0 << noOfOnes)) << (noOfZeros - 1);
        return modifiedInput + number;
    }
}