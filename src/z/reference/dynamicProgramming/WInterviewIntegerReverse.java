package z.reference.dynamicProgramming;

public class WInterviewIntegerReverse {

    public int input;
    public int reverse;

    public WInterviewIntegerReverse(int input) {
        this.input = input;
    }

    public static void main(String[] args) {
        int input = 918211861;
        WInterviewIntegerReverse game = new WInterviewIntegerReverse(input);
        game.integerReversion();
        game.show();
    }

    private void integerReversion() {
        if (this.input <= 0) {
            System.out.println("Could not perform the reverse operation on int less than or equal to 0");
            return;
        }
        int tempInt = this.input;
        int digit = 0;
        while (tempInt > 0) {
            digit = tempInt % 10;
            tempInt = tempInt / 10;
            this.reverse = this.reverse * 10 + digit;
        }
    }

    private void show() {
        if (this.reverse != 0) {
            System.out.println("Reversed Integer is : " + this.reverse);
        }
    }
}
