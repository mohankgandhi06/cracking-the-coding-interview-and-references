package i.chapter.bitManipulation;

public class BBinaryToString {
    /* Binary to String
    Question: Given a real number between 0 and 1 (e.g., 0.72) that is passed as a double, print the binary representation.
    If the number cannot be represented accurately in binary with at most 32 characters, print "ERROR" */

    public static void main(String[] args) {
        System.out.println("Final Value: " + fetchBinaryOfTheFraction(0.725));
    }

    private static String fetchBinaryOfTheFraction(Double input) {
        StringBuilder stringBuilder = new StringBuilder();
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
