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
        int result = insert(n, m, i, j);
        System.out.println(Integer.toBinaryString(result) + " : After Insertion");
    }

    private static int insert(int n, int m, int i, int j) {
        int intermediary = (n & (1 << i) - 1) | (n & (-1 << (j + 1)));
        /*System.out.println(Integer.toBinaryString(intermediary));*/
        int shifterM = m << i;
        return intermediary | shifterM;
    }

}
