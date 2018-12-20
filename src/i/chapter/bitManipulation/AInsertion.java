package i.chapter.bitManipulation;

public class AInsertion {

    /* Insertion
    Question: You are given two 32-bit numbers, N and M, and two bit positions, i and j.
    Write a method to insert M into N such that M starts a bit j and ends at bit i. You
    can assume that the bits j through i have enough space to fit all of M. That is, if
    M = 1011, you can assume that there are at least 5 bits between j and i. You would not,
    for example, have j = 3 and i = 2, because M could not fully fit between bit 3 and 2. */

    public static void main(String[] args) {
        //Inputs Here
        int i = 2;
        int j = 6;
        int n = Integer.parseInt("10000000000", 2);
        int m = Integer.parseInt("10011", 2);

        System.out.println(Integer.toBinaryString(n) + " : Before Insertion");
        int result = insertOptimal(n, m, i, j);
        System.out.println(Integer.toBinaryString(result) + " : After Insertion");
    }

    /* Optimal Implementation */
    private static int insertOptimal(int n, int m, int i, int j) {
        //Clear out the bits from i through j
        int left = -1 << (j + 1);
        int right = (1 << i) - 1;
        int mask = left | right;
        //Move the integer m through the bits as required and perform or operation
        return (n & mask) | (m << i);
    }

    /* Earlier Implementation */
    private static int insert(int n, int m, int i, int j) {
        /* In this approach we didn't take into account the clearing of bits because if the shifting digit
         * contains 0 and the N is having 1 then or operation will return 1 instead of setting it to 0 */
        int intermediary = (n & (1 << i) - 1) | (n & (-1 << (j + 1)));
        /*System.out.println(Integer.toBinaryString(intermediary));*/
        int shifterM = m << i;
        return intermediary | shifterM;
    }

}
