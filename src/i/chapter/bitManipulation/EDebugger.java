package i.chapter.bitManipulation;

public class EDebugger {

    /* Debugger
    Question: Explain what the following code does ((n & (n-1)) == 0)*/

    public static void main(String[] args) {
        System.out.println("Outcome: " + debugger(4));
    }

    private static boolean debugger(int n) {
        //What the code does is that it takes the number and its previous and performs bitwise and operation
        //If the outcome is equal to 0 then it returns true, else false.
        //The method has been broken down to show each step

        //What it does is that the number and its previous number should not be having alternate digits i.e 1's and 0's
        //A = 1000, B should be equal to 0111 only then they will become 0000
        //A = 1010, B = 1001 then it will become 1000 once the bitwise and is executed, so only the power of 2 will be
        // satisfying these criteria. i.e.,this method checks if it is a power of two
        // Why it is only power of two is becaues of the fact that a number and its preceeding number used to
        // perform bitwise and and say for example we are using 9 and its previous number 8 for bitwise and
        // 1001 and 1000. since they are having common it will fail, the only case it will not fail is that
        // when all the previous number's previos digits are 1's which will only occur for powers of two
        // 2^0 = 1, 2^1 = 2....
        int a = n - 1;
        System.out.println(Integer.toBinaryString(a));
        int b = n & a;
        System.out.println(Integer.toBinaryString(b));
        return b == 0;
    }
}
