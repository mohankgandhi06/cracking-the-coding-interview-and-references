package e.chapter.stringArray;

public class FStringCompression {
    public static void main(String[] args) {
        System.out.println(usingArray("mohan"));
        System.out.println(usingArray("aabcchyccaaafffdf"));
    }

    public static String usingArray(String inputString) {
        int count = 1;
        int currentIndexPosition = 0;
        String[] chars = new String[ inputString.length() ];
        for (int i = 0; i < inputString.length() - 1; i++) {
            if (inputString.charAt(i) == inputString.charAt(i + 1)) {
                count++;
            } else {
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
        StringBuilder outputS = new StringBuilder();
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
