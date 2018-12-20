package i.chapter.bitManipulation;

public class BBinaryToString {
    /* Binary to String
    Question: Given a real number between 0 and 1 (e.g., 0.72) that is passed as a double, print the binary representation.
    If the number cannot be represented accurately in binary with at most 32 characters, print "ERROR" */

    public static void main(String[] args) {
        System.out.println("Final Value: " + fetchBinaryOfTheFraction(0.725));
    }

    /* Optimal Implementations */
    private static String binaryOptimalMultiplyMethod(Double input) {
        if (0 >= input || input >= 1) return "Error";
        StringBuilder result = new StringBuilder();
        result.append(".");
        while (input > 0) {
            if (result.length() > 32) return "Error";
            double value = input * 2;
            if (value >= 1) {
                result.append("1");
                input = value - 1;
                //Even though the number is 0.987 when multiplying
                // it will become 1.974 and so we are removing the 1 alone and having
                // the remaining digits
            } else {
                result.append("0");
                input = value;
            }
        }
        return result.toString();
    }

    private static String binaryOptimalFractionalMethod(Double input) {
        if (input >= 1 || input <= 0) {
            return "Error";
        }

        StringBuilder result = new StringBuilder();
        result.append(".");
        double fraction = 0.5;
        while (input > 0) {
            if (result.length() > 32) {
                return "Error";
            }
            if (input >= fraction) {
                result.append("1");
                input = input - fraction;
            } else {
                result.append("0");
            }
            fraction = fraction / 2;
        }
        return result.toString();
    }

    /* Earlier Implementations */
    private static String fetchBinaryOfTheFraction(Double input) {
        /* This is a proper implementation as expected */
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(".");
        Double value = input;
        while (value > 0 && stringBuilder.length() < 32) {
            int digit = (int) (value * 2) % 10;
            stringBuilder.append(digit);
            value = (value * 2) - digit;
            //For Debugging
            /*System.out.println("Digit: " + stringBuilder);*/
            /*System.out.println("Value: " + value);*/
        }
        if (stringBuilder.length() == 32 && value > 0) {
            return "Error";
        }
        return stringBuilder.toString();
    }
}
