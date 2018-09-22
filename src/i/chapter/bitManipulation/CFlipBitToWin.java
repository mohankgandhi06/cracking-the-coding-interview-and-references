package i.chapter.bitManipulation;

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
        /*int input = Integer.parseInt("0111110001", 2);*/
        /*int input = Integer.parseInt("11000101111", 2);*/
        /*int input = Integer.parseInt("100101000110111", 2);*/
        int input = Integer.parseInt("110000001111", 2);
        /*int input = Integer.parseInt("111111", 2);*/
        int maximumLength = flipToWin(input);
        System.out.println("Longest Possible Sequence: " + maximumLength);
    }

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
        //Since the ~ is converting for the 32 bit we need to mask it with 1's untill the original integer's binary length
        // and perform binary & operation to get the ~ value of the integer
        int maskLength = inputString.length();
        StringBuilder maskBuilder = new StringBuilder();
        for (int i = 0; i < maskLength; i++) {
            maskBuilder.append("1");
        }
        return Integer.parseInt(maskBuilder.toString(), 2) & (~input);
    }
}