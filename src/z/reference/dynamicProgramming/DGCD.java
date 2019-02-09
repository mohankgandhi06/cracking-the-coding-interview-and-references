package z.reference.dynamicProgramming;

public class DGCD {
    public static void main(String[] args) {

    }

    public static int gdcIterative(int number1, int number2) {
        while (number2 != 0) {
            int temp = number2;
            number2 = number1 % number2;
            number1 = temp;
        }
        return number1;
    }

    public static int gcdRecursive(int number1, int number2) {
        if (number2 == 0) return number1;
        return gcdRecursive(number2, number1 % number2);
    }
}
