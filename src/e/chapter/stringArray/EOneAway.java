package e.chapter.stringArray;

public class EOneAway {
    /* Question: There are three types of edits that can be performed on strings:
     * insert a character remove a character or replace a character.
     * Given two strings, write a function to check
     * if they are one edit (or zero edits) away. */

    private static int stringOneLength;
    private static int stringTwoLength;
    private static int compareLength;
    private static int count;

    public static void main(String[] args) {
        System.out.println("1: " + usingString("fall", "tall"));
        System.out.println("2: " + usingString("tal", "tall"));
        System.out.println("3: " + usingString("tblg", "tall"));
        System.out.println("4: " + usingString("snial", "smail"));
    }

    /* Optimal Implementation */
    private static boolean optimal(String inputStringOne, String inputStringTwo) {
        if (Math.abs(inputStringOne.length() - inputStringTwo.length()) > 1) {
            return false;
        }
        String longerString = inputStringOne.length() > inputStringTwo.length() ? inputStringOne : inputStringTwo;
        String shorterString = inputStringOne.length() > inputStringTwo.length() ? inputStringTwo : inputStringOne;

        int longerIndex = 0;
        int shorterIndex = 0;
        boolean differenceFound = false;
        while (longerIndex < longerString.length() && shorterIndex < shorterString.length()) {
            if (longerString.charAt(longerIndex) != shorterString.charAt(shorterIndex)) {
                if (differenceFound) {
                    return false;
                }
                differenceFound = true;
                if (longerString.length() == shorterString.length()) {
                    //Since Characters don't match it can be either that
                    //It has been replaced or it occurs in the next character of the longerString
                    //If length of the string are equal then they are replace operation. So we are increasing
                    // the shorterIndex. If length is not equal it means it will occur the next character so we
                    // are not incrementing the shorterIndex. Only incrementing longerIndex
                    shorterIndex++;
                }
            } else {//Match has been found. So we can increase the shorterIndex
                shorterIndex++;
            }
            longerIndex++;
        }
        return true;
    }

    /* Slightly cleaner Implementation */
    private static boolean slightlyCleaner(String inputStringOne, String inputStringTwo) {
        //If the length difference is greater than 1 then it is not at all going to be one swap away
        if (inputStringOne.length() == inputStringTwo.length()) {
            return checkForReplaceOperation(inputStringOne, inputStringTwo);
        } else if (inputStringOne.length() + 1 == inputStringTwo.length()) {//inputStringTwo is larger
            return checkForInsertionOrReplaceOperation(inputStringTwo, inputStringOne);
        } else if (inputStringOne.length() - 1 == inputStringTwo.length()) {//inputStringOne is larger
            return checkForInsertionOrReplaceOperation(inputStringOne, inputStringTwo);
        }
        return false;
    }

    private static boolean checkForReplaceOperation(String inputStringOne, String inputStringTwo) {
        boolean replacedAlready = false;
        for (int i = 0; i < inputStringOne.length(); i++) {
            if (inputStringOne.charAt(i) != inputStringTwo.charAt(i)) {
                if (replacedAlready) {
                    return false;
                }
                replacedAlready = true;
            }
        }
        return true;
    }

    private static boolean checkForInsertionOrReplaceOperation(String longerString, String shorterString) {
        int longerIndex = 0;
        int shorterIndex = 0;
        while (longerIndex < longerString.length() && shorterIndex < shorterString.length()) {
            if (longerString.charAt(longerIndex) != shorterString.charAt(shorterIndex)) {
                if (longerIndex != shorterIndex) {
                    return false;
                }
                longerIndex++;
            } else {
                shorterIndex++;
                longerIndex++;
            }
        }
        return true;
    }

    /* Earlier Implementations */
    public static boolean usingString(String inputStringOne, String inputStringTwo) {
        stringOneLength = inputStringOne.length();
        stringTwoLength = inputStringTwo.length();
        if (stringTwoLength < stringOneLength) {
            compareLength = stringOneLength - stringTwoLength;
        } else {
            compareLength = stringTwoLength - stringOneLength;
        }
        if (compareLength > 1) {
            return false;
        } else {
            //Deciding if it is a replace operation
            count = 0;
            if (compareLength == 0) {
                for (int i = 0; i < stringOneLength; i++) {
                    if (inputStringOne.charAt(i) != inputStringTwo.charAt(i)) {
                        if (count > 0) {
                            return false;
                        }
                        count++;
                    }
                }
                return true;
            }
            //Deciding the smallest length for insertion and removal operation
            count = 0;
            int i = 0;
            int j = 0;
            while (count < 2 && i < inputStringOne.length() && j < inputStringTwo.length()) {
                if (inputStringOne.charAt(i) != inputStringTwo.charAt(j)) {
                    count++;
                    if (inputStringOne.charAt(i) == inputStringTwo.charAt(j + 1)) {
                        j++;
                    } else if (inputStringOne.charAt(i + 1) == inputStringTwo.charAt(j)) {
                        i++;
                    }
                } else {
                    i++;
                    j++;
                }
            }
            if (count < 2) {
                return true;
            }
        }
        return false;
    }

    public void usingHashTable(String inputStringOne, String inputStringTwo) {
        //We could implement it, but it will be same like the string
    }

    public void usingArray(String inputStringOne, String inputStringTwo) {
        //We could implement it, but it will be same like the string
    }
}
