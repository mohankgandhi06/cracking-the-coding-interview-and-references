package e.chapter.stringArray;

public class IStringRotation {
    /* Question: Assume you have a method isSubstring which checks if one word is a substring of another. Given two strings, s1 and s2,
     * write a code to check if s2 is a rotation of s1 using only one call to isSubstring
     * (e.g., "waterbottle" is a rotation of "erbottlewat")*/

    public static void main(String[] args) {
        System.out.println(isSubstring("waterbottle", "lewaterbott"));
    }

    /* Solution: I have wrongly interpreted the question. They said that the isSubstring() is a method that is already present.
     * It's work is that if we pass two string then it will check if the second parameter is a substring of the first.
     * Our work is to if second parameter is a rotation of the first.
     * So first we consider first string: waterbottle is of the order xy where x (wat) and y (erbottle). and the
     * second string erbottlewat is of the order yx where y(erbottle) x(wat), just that they are rotated.
     * So what we can do here is that take the first word and repeat it twice such that format becomes xyxy and pass it to the
     * isSubstring() such that it will find yx in the xyxy and return true and we can identify it is a proper rotation. */
    private static boolean isARotation(String inputStringOne, String inputStringTwo) {
        if (inputStringOne.length() == inputStringTwo.length() && inputStringOne.length() > 0) {
            String xyxy = inputStringOne + inputStringOne;
            return isSubstringCorrect(xyxy, inputStringTwo);
        }
        return false;
    }

    private static boolean isSubstringCorrect(String xyxy, String inputStringTwo) {
        //Assume that this method has been implemented
        return false;
    }

    /* Earlier Implementation */
    public static boolean isSubstring(String inputStringOne, String inputStringTwo) {
        for (int i = 0; i < inputStringTwo.length(); i++) {
            if (inputStringOne.charAt(0) == inputStringTwo.charAt(i)) {
                String result = inputStringTwo.substring(i, inputStringTwo.length()) + inputStringTwo.substring(0, i);
                if (result.equalsIgnoreCase(inputStringOne)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}