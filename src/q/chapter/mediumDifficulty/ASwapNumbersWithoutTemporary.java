package q.chapter.mediumDifficulty;

public class ASwapNumbersWithoutTemporary {

    public static void main(String[] args) {
        ASwapNumbersWithoutTemporary swapNumber = new ASwapNumbersWithoutTemporary();
        int numOne = 23;
        int numTwo = 45;

        System.out.println("(Before Swap) numOne: " + numOne + " numTwo: " + numTwo);
        swapNumber.swap(numOne, numTwo);

        numOne = 48;
        numTwo = 21;
        System.out.println("\n(Before Swap) numOne: " + numOne + " numTwo: " + numTwo);
        swapNumber.swap(numOne, numTwo);

        numOne = -40;
        numTwo = 193;
        System.out.println("\n(Before Swap) numOne: " + numOne + " numTwo: " + numTwo);
        swapNumber.swap(numOne, numTwo);
    }

    private void swap(int numOne, int numTwo) {
        numTwo = numOne - numTwo;
        numOne = numOne - numTwo;
        numTwo = numOne + numTwo;
        System.out.println("(After Swap) numOne: " + numOne + " numTwo: " + numTwo);
    }
}