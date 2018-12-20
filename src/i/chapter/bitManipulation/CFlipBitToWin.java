package i.chapter.bitManipulation;

import java.util.ArrayList;

public class CFlipBitToWin {
    /* Flip Bit to Win
    Question: You have an integer and you can flip exactly one bit from a 0 to 1.
    Write code to find the length of the longest sequence of 1s you could create.

    Example:
    Input: 1775 (or: 11011101111)
    Output: 8 */

    public static void main(String[] args) {

        /*int input = Integer.parseInt("11011101111", 2);*/
        /*int input = Integer.parseInt("10011100111", 2);*/
        /*int input = Integer.parseInt("11000001111", 2);*/
        int input = Integer.parseInt("0111110001", 2);
        /*int input = Integer.parseInt("11000101111", 2);*/
        /*int input = Integer.parseInt("100101000110111", 2);*/
        /*int input = Integer.parseInt("110000001111", 2);*/
        /*int input = Integer.parseInt("111111", 2);*/
        int maximumLength = flipToWinOptimal(input);
        System.out.println("Longest Possible Sequence: " + maximumLength);
    }

    /* Optimal Implementations */
    /* BigO (b) where b is the length of the bits of sequence. here the space is also BigO (b)*/
    private static int flipToWinBruteForce(int input) {
        if (input == -1) return Integer.BYTES * 8;
        ArrayList<Integer> sequence = getAlternateSequence(input);
        return maxLength(sequence);
    }

    private static ArrayList<Integer> getAlternateSequence(int input) {
        ArrayList<Integer> sequence = new ArrayList<>();

        int searchingFor = 0;
        int counter = 0;

        for (int i = 0; i < Integer.BYTES; i++) {
            //input & 1 will fetch only the current bit. we are checking if it is equal to current bit under
            // consideration.
            if ((input & 1) != searchingFor) {
                sequence.add(counter);
                searchingFor = input & 1;//Flip the bit
                // if the current bit (input & 1) is not equal
                // then we are taking the current bit
                counter = 0;
            }
            counter++;
            input = input >>> 1;
        }
        sequence.add(counter);//Last sequence
        return sequence;
    }

    private static int maxLength(ArrayList<Integer> sequence) {
        int max = 1;
        //i=i+2 we are jumping all the zero locations and checking the previous and next 1's sequence
        for (int i = 0; i < sequence.size(); i = i + 2) {
            int zeroSequence = sequence.get(i);
            int oneSequenceRight = i - 1 >= 0 ? sequence.get(i - 1) : 0;
            int oneSequenceLeft = i + 1 < sequence.size() ? sequence.get(i + 1) : 0;
            int tempSequence = 0;
            if (zeroSequence == 1) {//Merge the left and right and store in max
                tempSequence = oneSequenceRight + 1 + oneSequenceLeft;
            } else if (zeroSequence > 1) {//Add one to the max of the left or right since only one sequence can be
                tempSequence = 1 + Math.max(oneSequenceLeft, oneSequenceRight);
            } else if (zeroSequence == 0) {//Since already the zero count is 0 we cannot flip anything more
                // so we are just taking the maximum of the left or right
                tempSequence = Math.max(oneSequenceLeft, oneSequenceRight);
            }
            max = Math.max(max, tempSequence);
        }
        return max;
    }

    /* In order to reduce the space complexity we are going for this approach */
    private static int flipToWinOptimal(int input) {
        /* we can worry about only the current and the previous sequence. so we check if the value */
        if (~input == 0) return Integer.BYTES * 8;
        int currentLength = 0;
        int previousLength = 0;
        int maxLength = 1;
        while (input != 0) {
            if ((input & 1) == 1) {
                currentLength++;
            } else if ((input & 1) == 0) {
                previousLength = (input & 2) == 0 ? 0 : currentLength;
                currentLength = 0;//reset the length
            }
            maxLength = Math.max(maxLength, previousLength + currentLength + 1);
            input >>>= 1;
        }
        return maxLength;
    }

    /* Earlier Implementations */
    private static int flipToWin(int input) {
        String inputString = Integer.toBinaryString(input);
        int max = 1;
        int currentLength = 0;
        int previousLength = 0;
        int negativeInput = binaryInverse(inputString, input);
        if (negativeInput == 0) {
            max = Integer.toBinaryString(input).length();
        }
        if (negativeInput != 0) {
            for (int i = 0; i < inputString.length(); i++) {
                if (inputString.charAt(i) == '1') {
                    currentLength++;
                } else {
                    previousLength = inputString.charAt(i - 1) == '0' ? 0 : currentLength;
                    currentLength = 0;
                }
                max = Math.max(previousLength + currentLength + 1, max);
            }
        }
        return max;
    }

    private static int flipToWinAlternate(int input) {
        /*
        flipToWin is the solution and this method is the approach which i tried to follow
        This method is only failing for the below as the input, because the when there is even amount of integers
        the zero is not getting counted
        int input = Integer.parseInt("110000001111", 2);
        */
        boolean immediateZeroFlag = false;
        boolean breakSequenceFlag = false;
        int longest = 0;
        int intermediateSequence = 0;
        int max = 0;
        String inputString = Integer.toBinaryString(input);
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == '1') {
                immediateZeroFlag = false;
                longest++;
                if (breakSequenceFlag) {
                    intermediateSequence++;
                }
            } else {
                if (!breakSequenceFlag) {//First Zero
                    longest++;
                    breakSequenceFlag = true;
                    immediateZeroFlag = true;
                } else {
                    //Second Zero
                    if (immediateZeroFlag) {
                        longest = 0;
                        breakSequenceFlag = false;
                        intermediateSequence = 0;
                    } else {
                        //Intermediate + 1 since we are already in the second zero and we need to count it for next possibility
                        longest = intermediateSequence + 1;
                        intermediateSequence = 0;
                    }
                    immediateZeroFlag = false;
                }
            }
            if (longest > max) {
                max = longest;
            }
        }
        return max;
    }


    private static int binaryInverse(String inputString, int input) {
        //Since the ~ is converting for the 32 bit we need to mask it with 1's until the original integer's binary length
        // and perform binary & operation to get the ~ value of the integer
        int maskLength = inputString.length();
        StringBuilder maskBuilder = new StringBuilder();
        for (int i = 0; i < maskLength; i++) {
            maskBuilder.append("1");
        }
        return Integer.parseInt(maskBuilder.toString(), 2) & (~input);
    }
}