package e.chapter.stringArray;

public class DPalindromePermutation {
    /* Question: Given a string, write a function to check if it is a permutation of a palindrome. A palindrome
     * is a word or phrase that is the same forwards and backwards. A permutation is a rearrangement of letters.
     * The palindrome does not need to be limited to just dictionary words. */

    public static void main(String[] args) {
        System.out.println("isPermutationAPalindrome: " + isPermutationOfPalindrome("toc a ciiot"));
    }

    /* Optimal Algorithm */
    private static boolean isPermutationOfPalindromeVector(String phrase) {
        int bitVector = createBitVector(phrase);
        return bitVector == 0 || isOnlyOneBitSet(bitVector);
    }

    private static int createBitVector(String phrase) {
        int bitVector = 0;
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (!(x < 0)) {
                bitVector = toggle(bitVector, x);
            }
        }
        return bitVector;
    }

    private static int toggle(int bitVector, int index) {
        int mask = 1 << index;
        if ((bitVector & mask) == 0) {
            bitVector = bitVector | mask;
        } else {
            bitVector = bitVector & ~(mask);
        }
        return bitVector;
    }

    private static boolean isOnlyOneBitSet(int bitVector) {
        /* To check if only one bit is set to 1.
         * Ex: 00010000 - 1 = 00001111
         *     00010000 & 00001111 == 0
         *
         *     00101000 - 1 = 00100111
         *     00101000 & 00100111 != 0 */
        return ((bitVector - 1) & bitVector) == 0;
    }

    /* Slightly tweaked algorithm */
    private static boolean isPermutationOfPalindrome(String phrase) {
        int countOdd = 0;
        int[] table = new int[ Character.getNumericValue('z') - Character.getNumericValue('a') + 2 ];//Here we are adding 2 since we need to address space characters as well
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[ x ]++;
                if (table[ x ] % 2 == 1) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }
        return countOdd <= 1;
    }

    private static int getCharNumber(char c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        if (a <= Character.getNumericValue(c) && Character.getNumericValue(c) <= z) {
            return Character.getNumericValue(c) - a;
        } else if (c == ' ') {
            return Character.getNumericValue('z') - Character.getNumericValue('a') + 1;
        }
        return -1;
    }

    /* Earlier Implementations */
    public static boolean isPermutationAPalindrome(String inputString) {
        String input = inputString.toLowerCase();
        PalindromeTable table = new PalindromeTable("26");
        int oddCount = 0;
        for (int i = 0; i < input.length(); i++) {
            int index = hashFunction(input.charAt(i));
            table.nodes[ index ].value++;
        }
        for (PalindromeNode node : table.nodes) {
            if (node.value % 2 == 1) {
                oddCount++;
                if (oddCount > 1) {
                    System.out.println("Palindrome Permutation Not Applicable since odd count is greater than 1");
                    return false;
                }
            }
        }
        return true;
    }

    private static int hashFunction(char character) {
        return character % 26;
    }
}

class PalindromeTable {
    public static int TABLE_SIZE;
    public PalindromeNode[] nodes;

    public PalindromeTable(String size) {
        this.TABLE_SIZE = Integer.parseInt(size);
        this.nodes = new PalindromeNode[ TABLE_SIZE ];
        for (int i = 0; i < TABLE_SIZE; i++) {
            nodes[ i ] = new PalindromeNode(0);
        }
    }
}

class PalindromeNode {
    public int value;
    public PalindromeNode next;

    public PalindromeNode() {

    }

    public PalindromeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public PalindromeNode getNext() {
        return next;
    }

    public void setNext(PalindromeNode next) {
        this.next = next;
    }
}