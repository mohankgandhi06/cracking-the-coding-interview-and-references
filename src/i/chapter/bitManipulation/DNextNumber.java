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

    /* Optimal Implementations */
    /* Using Bit Manipulation */
    private static int nextLargestNumberBitManipulation(int input) {
        int c = input;
        int c0 = 0;
        int c1 = 0;
        while ((c & 1) == 0 && c != 0) {
            c0++;
            c >>= 1;
        }
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }
        if (c0 + c1 == 31 || c0 + c1 == 0) {
            return -1;
        }
        int position = c0 + c1;
        input |= 1 << position;//Flip rightmost non-trailing zero
        input &= ((1 << position) - 1);//clear the bits to the right of position
        input |= (1 << (c1 - 1)) - 1;//Sending the one to the location where all the digits
        // to the right of it has been cleared. so -1 will again make all the digits to be 1
        return input;
    }

    private static int nextSmallestNumberBitManipulation(int input) {
        int c = input;
        int c1 = 0;
        int c0 = 0;
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }
        if (c == 0) return -1;
        while ((c & 1) == 0 && c != 0) {
            c0++;
            c >>= 1;
        }

        int p = c0 + c1;
        input &= ((-1) << (p + 1));//Clear all the digits including the non-trailing 1
        int mask = (1 << (c1 + 1)) - 1;//We will have the number 1's as needed
        // now we need to move them to the location as necessary i.e. near the non-trailing 1
        input |= (mask << c0 - 1);
        return input;
    }

    /* Using Arithmetic */
    private static int nextLargestArithmetic(int input) {
        int c = input;
        int c0 = 0;
        int c1 = 0;
        while ((c & 1) == 0 && c != 0) {
            c0++;
            c >>= 1;
        }
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }
        if (c0 + c1 == 31 || c0 + c1 == 0) {
            return -1;
        }
        //Set the trailing 0s to 1 and then adding 1 will change everything to 0's
        //(c0-1) + 1
        //Set the trailing zero's to 1 (c^(1)-1) - 1
        //solving the equation we get c0+(c^(1)-1) - 1
        return input + (1 << c0) + (1 << (c1 - 1)) - 1;
    }

    private static int nextSmallestArithmetic(int input) {
        int c = input;
        int c1 = 0;
        int c0 = 0;
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }
        if (c == 0) return -1;
        while ((c & 1) == 0 && c != 0) {
            c0++;
            c >>= 1;
        }
        //Set the trailing 1's to 0's c1-1 and then -1
        //(c1)-1
        //(c0^(1)-1)-1
        //solving the equation will get - c1 - (c0-1) + 1
        return input - (1 << c1) - (1 << (c0 - 1)) + 1;
    }

    /* Earlier Implementations */
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