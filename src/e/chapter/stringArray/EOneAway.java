package e.chapter.stringArray;

public class EOneAway {
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
