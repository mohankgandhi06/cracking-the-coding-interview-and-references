package e.chapter.stringArray;

public class FStringCompression {
    /* Question: Implement a method to perform basic string compression using the counts of repeated characters.
     * For Example, the string aabcccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller
     * than the original string, your method should return the original string. You can assume the string has only
     * uppercase and lowercase characters (a-z) */

    public static void main(String[] args) {
        System.out.println(usingArray("mohan"));
        System.out.println(usingArray("aabcchyccaaafffdf"));
    }

    /* Bad Compression */
    private static String badCompression(String inputString) {
        String resultString = new String();
        int count = 0;
        for (int i = 0; i < inputString.length(); i++) {
            count++;
            if (i + 1 >= inputString.length() || inputString.charAt(i) != inputString.charAt(i + 1)) {
                resultString = resultString + inputString.charAt(i) + count;
                count = 0;
            }
        }
        return resultString.length() >= inputString.length() ? inputString : resultString;
    }

    /* Slightly Better */
    private static String usingBuilder(String inputString) {
        StringBuilder resultString = new StringBuilder();
        int count = 0;
        for (int i = 0; i < inputString.length(); i++) {
            count++;
            if (i + 1 >= inputString.length() || inputString.charAt(i) != inputString.charAt(i + 1)) {
                resultString = resultString.append(inputString.charAt(i)).append(count);
                count = 0;
            }
        }
        return resultString.length() >= inputString.length() ? inputString : resultString.toString();
    }

    /* Even Better */
    /* The above two methods i.e. the string concatenation alone is very slow BigO(n^2) so on top of it if there are
     * many sequence then it is slower. In using the StringBuilder implementation the appending time is amortized and
     * so it is slightly better, however since the size of the builder (i.e., final compressed size is not calculated
     * initially) it will lead to more space being allocated by the builder. So we can optimize still by counting the
     * compression size required upfront and then creating the string builder and following the same steps like above */
    private static String usingOptimizedBuilder(String inputString) {
        int length = calculateCompressedStringSize(inputString);
        if (length > inputString.length()) {
            return inputString;
        }
        StringBuilder compressedString = new StringBuilder(length);
        int count = 0;
        for (int i = 0; i < inputString.length(); i++) {
            int k = 0;//This is just to escape the duplicate check which occurs in the IntelliJ
            count++;
            if (i + 1 >= inputString.length() || inputString.charAt(i) != inputString.charAt(i + 1)) {
                compressedString = compressedString.append(inputString.charAt(i)).append(count);
                count = 0;
            }
        }
        return compressedString.toString();
    }

    private static int calculateCompressedStringSize(String inputString) {
        int finalLength = 0;
        int count = 0;
        for (int i = 0; i < inputString.length(); i++) {
            count++;
            if (i + 1 >= inputString.length() || inputString.charAt(i) != inputString.charAt(i + 1)) {
                finalLength = finalLength + 1 + String.valueOf(count).length();
                //1 is for the character that is currently checked for
                //String.valueOf(count).length() is used because if
                // the count is two or three digits ten the corresponding space is needed in the final compressed string
                count = 0;
            }
        }
        return finalLength;
    }


    /* Earlier Implementations */
    public static String usingArray(String inputString) {
        int count = 1;
        int currentIndexPosition = 0;
        String[] chars = new String[ inputString.length() ];
        for (int i = 0; i < inputString.length() - 1; i++) {
            if (inputString.charAt(i) == inputString.charAt(i + 1)) {
                //When the current and the next character are same then count it
                count++;
            } else {
                //If we arrive at this block then it means that the current character and the next are
                //different. So we are checking if the indexPosition of the array formed is going to be
                //greater than the actual string itself. If so then return the string itself.
                //If block if satisfied it means that the character and the count can be noted in a array
                //and later converted back to string
                if (currentIndexPosition < chars.length - 1) {
                    chars[ currentIndexPosition ] = String.valueOf(inputString.charAt(i));
                    chars[ currentIndexPosition + 1 ] = String.valueOf(count);
                    currentIndexPosition = currentIndexPosition + 2;
                    count = 1;
                } else {
                    return inputString;
                }
            }
        }
        if (currentIndexPosition < chars.length - 1) {
            chars[ currentIndexPosition ] = String.valueOf(inputString.charAt(inputString.length() - 1));
            chars[ currentIndexPosition + 1 ] = String.valueOf(count);
        } else {
            return inputString;
        }
        StringBuilder outputS = new StringBuilder(chars.length);
        for (String s : chars) {
            if (s != null) {
                outputS.append(s);
            } else {
                break;
            }
        }
        return outputS.toString();
    }
}