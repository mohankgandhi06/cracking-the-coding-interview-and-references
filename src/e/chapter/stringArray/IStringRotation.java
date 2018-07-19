package e.chapter.stringArray;

public class IStringRotation {
    public static void main(String[] args) {
        System.out.println(isSubstring("waterbottle", "lewaterbott"));
    }

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
