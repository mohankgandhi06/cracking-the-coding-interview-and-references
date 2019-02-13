package z.reference.dynamicProgramming;

public class VInterviewPalindrome {

    public String input;
    public char[] inputArray;

    public VInterviewPalindrome(String input) {
        this.input = input;
        this.inputArray = input.toCharArray();
    }

    public static void main(String[] args) {
        String input = "li r il";
        VInterviewPalindrome game = new VInterviewPalindrome(input);
        System.out.println("Palindrome: (Y/N): " + (game.solve() ? "Y" : "N"));

    }

    private boolean solve() {
        int startIndex = 0;
        int endIndex = this.inputArray.length - 1;
        int midIndex = (startIndex + endIndex) / 2;

        int j = endIndex;
        for (int i = startIndex; i <= midIndex; i++) {
            if (this.inputArray[ i ] != this.inputArray[ j ]) {
                return false;
            }
            j--;
        }
        return true;
    }
}
