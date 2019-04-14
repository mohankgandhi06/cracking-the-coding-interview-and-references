package r.chapter.hardDifficulty;

public class AAddWithoutPlus {
    public static void main(String[] args) {
        AAddWithoutPlus addWithoutPlus = new AAddWithoutPlus();
        int numberOne = 14;
        int numberTwo = 23;
        System.out.println("Addition of " + numberOne + " and " + numberTwo + " gives: " + addWithoutPlus.add(numberOne, numberTwo));

        numberOne = 14;
        numberTwo = -3;
        System.out.println("\nAddition of " + numberOne + " and " + numberTwo + " gives: " + addWithoutPlus.add(numberOne, numberTwo));

        numberOne = 15;
        numberTwo = -25;
        System.out.println("\nAddition of " + numberOne + " and " + numberTwo + " gives: " + addWithoutPlus.add(numberOne, numberTwo));

        numberOne = 4;
        numberTwo = 3;
        System.out.println("\nAddition of " + numberOne + " and " + numberTwo + " gives: " + addWithoutPlus.add(numberOne, numberTwo));
    }

    private int add(int numberOne, int numberTwo) {
        /* System.out.println(Integer.toBinaryString(-numberOne + numberTwo)); */
        /* EXOR for binary current bit */
        /* Combination of OR and AND for binary carry bit */
        int result = add(numberOne, numberTwo, 0, 0, 0, 0);
        return result;
    }

    private int add(int numberOne, int numberTwo, int indexOne, int indexTwo, int carryBit, int result) {
        if (indexOne == 32 || indexTwo == 32) return result;
        int firstCurrentBit = (numberOne & (1 << indexOne)) >>> indexOne;
        int secondCurrentBit = (numberTwo & (1 << indexTwo)) >>> indexTwo;
        int current = firstCurrentBit ^ secondCurrentBit ^ carryBit;
        int carry = (firstCurrentBit & secondCurrentBit) == 1 ? 1 : ((firstCurrentBit | secondCurrentBit) & carryBit) == 1 ? 1 : 0;
        result = result | (current << indexOne);
        return add(numberOne, numberTwo, indexOne + 1, indexTwo + 1, carry, result);
    }
}